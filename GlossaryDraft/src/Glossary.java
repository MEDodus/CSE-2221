import java.util.Comparator;

import components.queue.Queue;
import components.queue.Queue1L;
import components.sequence.Sequence;
import components.sequence.Sequence1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author Michael Dodus
 *
 */
public final class Glossary {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Glossary() {
    }

    /**
     * Returns a {@code Sequence<String>} of terms and definitions from file
     * {@code fileName}.
     *
     * @param file
     *            file input stream {in.content} to read terms and definitions
     *            from
     * @return contents all term and definition entries in file
     * @requires {in.content} is properly formatting as specified in the project
     *           description.
     * @ensures for all t in contents: t is a term or definition defined in the
     *          project description.
     */
    private static Sequence<String> getEntries(SimpleReader file) {
        Sequence<String> contents = new Sequence1L<>();
        final int top = 0;
        while (!file.atEOS()) {
            String line = file.nextLine();
            contents.add(top, line);
        }
        /*
         * Call a method to combine lines for definitions with multiple lines.
         */
        combineLines(contents);
        return contents;
    }

    /**
     * Updates an {@code Sequence} entries to combine lines from in.content as
     * specified in the project description into one definition element for each
     * term in a {@code Sequence}.
     *
     * @param entries
     *            Sequence of all String entries from in.content
     * @updates entries
     */
    private static void combineLines(Sequence<String> entries) {
        final int top = 0;
        Sequence<String> temp = entries.newInstance();
        temp.transferFrom(entries);
        while (temp.length() > 0) {
            String line = temp.remove(temp.length() - 1);
            // Terms contains no whitespace and not on a blank line.
            if (!line.equals("") && !line.contains(" ")) {
                entries.add(top, line);
                // Definition does not contain a blank line.
            } else if (!line.equals("")) {
                String def = line;
                // Concatenate def string until it holds the entire definition.
                while (temp.length() > 0
                        && temp.entry(temp.length() - 1).contains(" ")) {
                    def += temp.remove(temp.length() - 1);
                }
                entries.add(top, def);
            }
        }
    }

    /**
     * Updates {@code #Sequence} entries to hold only terms and returns a
     * {@code Sequence} defs with only definitions.
     *
     * @param entries
     *            Sequence of all term and definition entries ensured by
     *            combineLines
     * @return defs Sequence of all definitions in order specified in the
     *         project description
     * @require entries.length() >= 2
     * @updates entries
     * @ensures entries = #entries \ defs and defs = definitions in the glossary
     */
    private static Sequence<String> getDefsAndTerms(Sequence<String> entries) {
        Sequence<String> defs = new Sequence1L<>();
        Sequence<String> temp = entries.newInstance();
        temp.transferFrom(entries);
        while (temp.length() > 1) {
            /*
             * Adds every other entry to defs or entries since they are in
             * cyclical order.
             */
            entries.add(0, temp.remove(temp.length() - 1));
            defs.add(0, temp.remove(temp.length() - 1));
        }
        return defs;
    }

    /**
     * Builds main index.html page (out.content) and side pages given a
     * {@code Sequence} of terms and definitions.
     *
     * @param folderName
     *            relative or absolute directory path to write to
     * @param terms
     *            Sequence of terms in the glossary
     * @param defs
     *            Sequence of definition in the glossary
     * @requires folderName is a valid absolute or relative path and
     *           terms.length() >= 1 and defs.length() >= 1
     * @ensures out.content = valid html pages with proper formatting using
     *          terms and definitions from terms and defs
     */
    private static void buildHTML(String folderName, Sequence<String> terms,
            Sequence<String> defs) {
        SimpleWriter index = new SimpleWriter1L(folderName + "/index.html");
        final String[] header = { "<!DOCTYPE html>", "<html>", "<head>",
                "<title>Glossary</title>", "</head>" };
        for (int i = 0; i < header.length; i++) {
            index.println(header[i]);
        }

        final String[] body = { "<body>", "<h1>", "Glossary",
                "<hr size=\"1\" width=\"100%\" color=\"black\">", "</h1>",
                "<h2>", "Index", "</h2>" };
        for (int i = 0; i < body.length; i++) {
            index.println(body[i]);
        }

        index.println("<ul>");
        for (int i = terms.length() - 1; i >= 0; i--) {
            String term = terms.entry(i);
            String def = defs.entry(i);
            index.println("<li>");
            index.println("<a href=\"" + term + ".html\">");
            index.println(term);
            index.println("</a>");
            index.println("</li>");
            // Side term pages.
            buildTermPages(
                    new SimpleWriter1L(folderName + "/" + term + ".html"), term,
                    def, terms);
        }
        index.println("</ul>");
        index.println("</body>");
        index.println("</html>");
    }

    /**
     * Builds term.html pages for all terms in {@code Sequence} of terms.
     *
     * @param termPage
     *            output stream to write to html file
     * @param term
     *            String term to create a given title heading and matching link
     * @param def
     *            String def to write a given definition into the file
     * @param terms
     *            Sequence terms to return if for any t in terms there may be
     *            one contained in def.
     * @requires index.html exists and terms.length() >= 1 and for all t in
     *           terms there exists a termPage.html
     * @ensures termPage.html = is properly formatted html that may contain
     *          links to other #termPage.html
     */
    private static void buildTermPages(SimpleWriter termPage, String term,
            String def, Sequence<String> terms) {
        final String[] header = { "<!DOCTYPE html>", "<html>", "<head>",
                "<title>" + term + "</title>", "</head>" };
        for (int i = 0; i < header.length; i++) {
            termPage.println(header[i]);
        }
        final String[] body = { "<body>", "<h1 style=\"color:red\">", "<em>",
                term, "</em>", "</h1>", "<p>", def, "</p>",
                "<hr size=\"1\" width=\"100%\" color=\"black\">" };

        // Index of def is 7 in body.
        final int defIdx = 7;
        for (int i = 0; i < body.length; i++) {
            if (i == defIdx) {
                /*
                 * Queue to return all indexes of words in terms contained in
                 * def from smallest index to largest.
                 */
                Queue<Integer> sorted = pos(terms, def);
                if (sorted.length() != 0) {
                    /*
                     * Print hyperlinks for all words in terms that are
                     * contained in def.
                     */
                    printHyperlinks(termPage, terms, def, sorted);
                } else {
                    termPage.println(def);
                }
            } else {
                termPage.println(body[i]);
            }
        }
        termPage.println("Return to ");
        termPage.println("<a href=\"index.html\">");
        termPage.println("index");
        termPage.println("</a>");
        termPage.println("</body>");
        termPage.println("</html>");
    }

    /**
     * Returns int position of substring {@code String} term in {@code Sequence}
     * terms given {@code String} def.
     *
     * @param terms
     *            Sequence<String> holding all the terms in the glossary
     * @param def
     *            String holding any given definition in the glossary
     * @requires terms.length() >= 1
     * @return position integer index of a contained or not contained term in
     *         def
     * @ensures iff entry in terms is in def position = the position of term in
     *          terms else position = -1
     */
    private static Queue<Integer> pos(Sequence<String> terms, String def) {
        Queue<Integer> foundTerms = new Queue1L<>();
        for (int i = terms.length() - 1; i >= 0; i--) {
            /*
             * Check if the entry is a subset of def. However we won't check if
             * the word in def is a subset of entry until we print the page
             */
            if (def.contains(terms.entry(i))) {
                foundTerms.enqueue(def.indexOf(terms.entry(i)));
            }
        }
        Comparator<Integer> order = new IntegerSort();
        // Sorts from smallest to largest
        foundTerms.sort(order);
        return foundTerms;
    }

    /**
     * Prints split string with linked terms into the according termPage.html.
     */
    private static void printHyperlinks(SimpleWriter termPage,
            Sequence<String> terms, String def, Queue<Integer> idx) {

        String defCopy = def + " ";
        int x = 0;
        String word = "";
        while (idx.length() > 0) {
            // Gets our new string indexing on each iteration.
            x = idx.dequeue() - x - word.length();

            /*
             * Grab the string before the string of characters that both terms
             * and def contain.
             */
            String b4Word = defCopy.substring(0, x);

            // String with the word and any other part of the string included.
            String wordPlusAftr = defCopy.substring(x);

            // Grab only the word itself.
            word = defCopy.substring(x, x + wordPlusAftr.indexOf(" "));
            if (word.endsWith(",")) {
                word = word.substring(0, word.length() - 1);
            }
            /*
             * One last check on the word.
             */
            boolean trulyContains = false;
            int i = 0;
            while (!trulyContains && i < terms.length()) {
                if (terms.entry(i).contains(word)) {
                    trulyContains = true;
                }
                i++;
            }

            // Set the definition string to after the word we just retrieved.
            defCopy = defCopy.substring(x + word.length()); // grab after the word
            /*
             * Hyperlink the word only if the word is a subset of the term and
             * the term is a subset of the word i.e. word.equals(term)
             */
            if (trulyContains) {
                termPage.println(b4Word);
                termPage.println("<a href=\"" + word + ".html\">");
                termPage.println(word);
                termPage.println("</a>");
            } else {
                termPage.println(b4Word);
                termPage.println(word);
            }
        }
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        /*
         * In main we will get the folder that the user wants to output to as
         * well as the file name to read from. We do not need to ask the user
         * what they want to call the separate html files as specified in the
         * project description. In addition, we will use SimpleWriter and
         * SimpleReader for basic file i/o operations.
         */
        out.print(
                "Enter a relative or absolute path to the file you would like to read: ");
        String fileName = "doc/terms2.txt"; // in.nextLine();
        SimpleReader read = new SimpleReader1L(fileName);
        out.print("Enter a relative or absolute path to the folder "
                + "you would like to output to: ");
        String folderName = "data"; //in.nextLine();

        /*
         * Close in reader and out writer here we are done with them.
         */
        in.close();
        out.close();

        // Get all string entries in file.
        Sequence<String> terms = getEntries(read);

        // Separate and combine entries to get only the definitions and terms.
        Sequence<String> defs = getDefsAndTerms(terms);

        // Build all html pages.
        buildHTML(folderName, terms, defs);
    }

}
