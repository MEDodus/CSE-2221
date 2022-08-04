import components.map.Map;
import components.map.Map1L;
import components.sequence.Sequence;
import components.sequence.Sequence1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Program that creates a glossary given a text input file of terms and
 * definitions given specifications by Cy.
 *
 * @author Michael Dodus
 *
 */
public final class GlossaryMap {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private GlossaryMap() {
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
    public static Map<String, String> getDictionary(SimpleReader file) {
        Sequence<String> contents = new Sequence1L<>();
        final int top = 0;
        while (!file.atEOS()) {
            // Reads every line in the file.
            String line = file.nextLine();
            contents.add(top, line);
        }
        /*
         * Call a method to combine lines for definitions with multiple lines.
         */
        combineLines(contents);
        Map<String, String> dictionary = createMap(contents);
        return dictionary;
    }

    /**
     * Updates an {@code Sequence} entries to combine lines from in.content as
     * specified in the project description into one definition element for each
     * term in a {@code Sequence}.
     *
     * @param entries
     *            Sequence of all String entries from in.content
     * @updates entries
     * @ensures entries will only contain non-empty Strings and the Strings are
     *          either a term or definition.
     */
    public static void combineLines(Sequence<String> entries) {
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
                String defLine = line;
                while (temp.length() > 0 && defLine.length() > 0) {
                    defLine = temp.remove(temp.length() - 1);
                    def += defLine;
                }
                entries.add(top, def);
            }
        }
    }

    /**
     * Returns a {@code Map<String, String>} with a unique key to hold the term
     * and a value to hold the definition.
     *
     * @param termsAndDefs
     *            A {@code Sequence<String>} to get all terms and definitions
     *            from.
     * @updates termsAndDefs
     * @requires termsAndDefs.length() > 1 and termsAndDefs.length() % 2 == 0
     *           (even)
     * @return dictionary
     * @ensures dictionary is map with all terms mapped to a key (definition)
     */
    public static Map<String, String> createMap(Sequence<String> termsAndDefs) {
        Map<String, String> dictionary = new Map1L<>();
        while (termsAndDefs.length() > 1) {
            String value = termsAndDefs.remove(termsAndDefs.length() - 1); // term
            String key = termsAndDefs.remove(termsAndDefs.length() - 1); // definition
            dictionary.add(key, value);
        }
        return dictionary;
    }

    /**
     * Removes the smallest lexographic value in {@code Map<String, String>} and
     * returns it to a {@code Map.Pair<String, String>}.
     *
     * @param dictionary
     *            A {@code Map<String, String>} with keys and values mapping out
     *            terms and definitions.
     * @updates dictionary
     * @requires dictionary.size() > 0
     * @return min
     * @ensures min = the smallest lexographically sorted word in dictionary.
     */
    public static Map.Pair<String, String> removeMin(
            Map<String, String> dictionary) {
        // Use a temporary Map so that we can store a new updated Map set each iteration.
        Map<String, String> temp = dictionary.newInstance();
        temp.transferFrom(dictionary);
        while (temp.size() > 1) {
            Map.Pair<String, String> first = temp.removeAny();
            Map.Pair<String, String> second = temp.removeAny();
            if (first.value().compareTo(second.value()) > 0) { // First > Second
                dictionary.add(first.key(), first.value());
                temp.add(second.key(), second.value());
            } else if (first.value().compareTo(second.value()) < 0) { // First < Second
                dictionary.add(second.key(), second.value());
                temp.add(first.key(), first.value());
            } else {
                dictionary.add(first.key(), first.value());
                dictionary.add(second.key(), second.value());
            }
        }
        Map.Pair<String, String> min = temp.removeAny();
        return min;
    }

    /**
     * Builds main index.html page (out.content) and side pages given a
     * {@code Sequence} of terms and definitions.
     *
     * @param folderName
     *            relative or absolute directory path to write to
     * @requires folderName is a valid absolute or relative path and
     *           dictionary.size() > 0
     * @ensures out.content = valid html index page with proper formatting using
     *          terms and definitions from terms and defs
     */
    public static void buildIndex(String folderName,
            Map<String, String> dictionary) {
        SimpleWriter index = new SimpleWriter1L(folderName + "/index.html");
        final String[] header = { "<!DOCTYPE html>", "<html>", "<head>",
                "<title>Glossary</title>", "</head>" };
        // Prints index page header.
        for (int i = 0; i < header.length; i++) {
            index.println(header[i]);
        }

        final String[] body = { "<body>", "<h1>", "Glossary",
                "<hr size=\"1\" width=\"100%\" color=\"black\">", "</h1>",
                "<h2>", "Index", "</h2>" };
        // Prints index page body.
        for (int i = 0; i < body.length; i++) {
            index.println(body[i]);
        }
        index.println("<ul>");
        Map<String, String> temp = dictionary.newInstance();
        while (dictionary.size() > 0) {
            /*
             * Removes the smallest lexograhic term on each iteration and prints
             * it.
             */
            Map.Pair<String, String> lexo = removeMin(dictionary);
            index.println("<li>");
            // Prints hyperlink for the index page to the term page
            index.println("<a href=\"" + lexo.value() + ".html\">");
            index.println(lexo.value());
            index.println("</a>");
            index.println("</li>");
            temp.add(lexo.key(), lexo.value());
        }
        dictionary.transferFrom(temp);
        index.println("</ul>");
        index.println("</body>");
        index.println("</html>");
        index.close();
    }

    /**
     * Builds a html file for every term in dictionary with valid formatting and
     * hyperlink requirements.
     *
     * @param folderName
     *            folder directory to write html files to.
     * @param dictionary
     *            {@code Map<String, String>} dictionary to read terms from and
     *            write definitions.
     * @ensures term pages built are of valid formatting and contain zero or
     *          more hyperlinks in their definitions for the term.
     */
    public static void buildTermPages(String folderName,
            Map<String, String> dictionary) {
        for (Map.Pair<String, String> term : dictionary) {
            String[] header = { "<!DOCTYPE html>", "<html>", "<head>",
                    "<title>" + term.value() + "</title>", "</head>" };
            SimpleWriter termPage = new SimpleWriter1L(
                    folderName + "/" + term.value() + ".html");
            // Print out the header for the html document.
            for (int i = 0; i < header.length; i++) {
                termPage.println(header[i]);
            }

            String[] body = { "<body>", "<h1 style=\"color:red\">", "<em>",
                    term.value(), "</em>", "</h1>", "<p>", term.key(), "</p>",
                    "<hr size=\"1\" width=\"100%\" color=\"black\">" };
            final int defIdx = 7;
            /*
             * Print out the entire body. When we get to the term we are going
             * to print out, we will call printHyperlinks to format the
             * hyperlinks properly.
             */
            for (int i = 0; i < body.length; i++) {
                if (i == defIdx) {
                    printHyperlinks(termPage, dictionary, term.key());
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
            termPage.close();
        }
    }

    /**
     * Prints hyperlinks for words within {code Map<String, String>} dictionary
     * in any given {@code String} def.
     *
     * @param termPage
     *            SimpleWriter to write into our html file.
     * @param dictionary
     *            A {@code Map<String, String>} to read terms from.
     * @param def
     *            Any given definition in dictionary that may be checked for
     *            containing term values in dictionary.
     * @requires dictionary.size() > 0
     * @ensures termPage.html will contain a definition with hyperlinks to any
     *          other term in dictionary so long as the term is the exact length
     *          as it appears in dictionary and there is whitespace around it.
     */
    public static void printHyperlinks(SimpleWriter termPage,
            Map<String, String> dictionary, String def) {
        String foundTerm = "";
        String[] splitWords = def.split(" "); // Split into all separate words
        for (int i = 0; i < splitWords.length; i++) {
            // Check every term in the dictionary
            for (Map.Pair<String, String> term : dictionary) {
                // If the term is in the definition and there is only whitespace around it.
                if (splitWords[i].contains(term.value())
                        && splitWords[i].length() == term.value().length()) {
                    foundTerm = term.value();
                }
            }
            if (foundTerm.length() > 0) {
                // Print links to other pages.
                termPage.println("<a href=\"" + foundTerm + ".html\">"
                        + foundTerm + "</a>");
                termPage.println(" ");
            } else {
                termPage.println(splitWords[i] + " ");
            }
            foundTerm = "";
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
        String fileName = in.nextLine();
        SimpleReader read = new SimpleReader1L(fileName);
        out.print("Enter a relative or absolute path to the folder "
                + "you would like to output to: ");
        String folderName = in.nextLine();
        /*
         * Close in reader here we are done with it.
         */
        in.close();

        out.println("Building Glossary. . .");
        // Get all string entries in file.
        Map<String, String> dictionary = getDictionary(read);
        buildIndex(folderName, dictionary);
        buildTermPages(folderName, dictionary);
        out.println("Glossary successfully built, Exiting program. . .");
        /*
         * Close out writer here we are done with it.
         */
        out.close();

    }
}
