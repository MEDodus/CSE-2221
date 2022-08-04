import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class XMLTreeExploration {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private XMLTreeExploration() {
    }

    /**
     * Output information about the middle child of the given {@code XMLTree}.
     *
     * @param xt
     *            the {@code XMLTree} whose middle child is to be printed
     * @param out
     *            the output stream
     * @updates out.content
     * @requires <pre>
     * [the label of the root of xt is a tag]  and
     * [xt has at least one child]  and  out.is_open
     * </pre>
     * @ensures <pre>
     * out.content = #out.content * [the label of the middle child
     *  of xt, whether the root of the middle child is a tag or text,
     *  and if it is a tag, the number of children of the middle child]
     * </pre>
     */
    private static void printMiddleNode(XMLTree xt, SimpleWriter out) {
        int children = xt.numberOfChildren();
        int middle = children / 2;
        final int odd = 1;
        final int even = 2;
        XMLTree midNode = xt.child(middle);
        if (children % even == odd) {
            midNode = xt.child(middle);
        } else {
            middle = (children / 2) + 1;
            midNode = xt.child(middle);
        }
        out.println(midNode.label());

        if (midNode.isTag()) {
            out.println("Middle node is a tag.");
            out.println("Number of children is " + midNode.numberOfChildren());
        } else {
            out.println("Middle node is text.");
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
         * Put your main program code here
         */
//
        XMLTree xml = new XMLTree1(
                "http://web.cse.ohio-state.edu/software/2221/web-sw1/extras/instructions/xmltree-model/columbus-weather.xml");

        xml.display();
//        // out.print(xml.toString());
//        boolean isTag = xml.isTag();
//        String label = xml.label();
//
//        if (isTag) {
//            // out.println("The root is a tag");
//        }
//        // out.println(isTag);
//        // out.println(label);
//
//        int numChild = xml.numberOfChildren();
//        // out.println(numChild);
//
//        xml.display();
//
//        XMLTree results = xml.child(0);
//        // out.println(results);
//
//        XMLTree channel = results.child(0);
//        // out.println(channel);
//        // out.print(channel.numberOfChildren());
//
//        XMLTree title = channel.child(1);
//        // out.println(title);
//
//        XMLTree titleText = title.child(0);
//        // out.println(titleText);
//
//        XMLTree titleText2 = xml.child(0).child(0).child(1).child(0);
//        // out.println(titleText2);
//
//        XMLTree astronomy = channel.child(10);
//        out.println(astronomy);
//
//        boolean hasSunset = astronomy.hasAttribute("sunset");
//        boolean hasMidday = astronomy.hasAttribute("midday");
//        out.println(hasSunset);
//        out.println(hasMidday);

        printMiddleNode(xml.child(0).child(0), out);

        SimpleWriter html = new SimpleWriter1L("index.html");

        html.println("<!DOCTYPE html>");
        html.println("<html>");
        html.println("<head>");
        html.println("<title>");
        html.println("My website");
        html.println("</title>");
        html.println("</head>");
        html.println("</html>");
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
        html.close();
    }

}
