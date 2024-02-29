import static java.lang.StringTemplate.STR;

/**
 * A text block is a multi-line string literal in Java.
 *
 * It's composed of an opening delimiter, a content, and a closing delimiter.
 *
 * The opening delimiter is a sequense of three double quotes ("""), followed by
 * zero or more white space, and followed by a line terminator "\n".
 *
 * Example:
 * var msg = """\n <- a newline here, meaning we begin adding content on the next line.
 *
 * The closing delimiter is composed of a sequense of three double quotes.
 * The content of the text block ends after the first quote of the closing
 * delimiter.
 *
 * The content of the text block can contain escaped newlines or escaped double quote,
 * however, this is not necessary nor recommended.
 *
 * Putting all together:
 *
 * var goodbyeMessage = """
 * We have lots of fun!
 * See you soon.
 * """;
 */
public class JEP378 {

    public static void main(String[] args) {
        var pages = new Page[]{
            new Page("Home"),
            new Page("About"),
            new Page("Contact")
        };

        for (var page : pages) {
            System.out.println(page.toHTML());
            System.out.println(page.toJSON());
        }

        // Invalid.
        // var welcomeMessage = """Hello World""";
        // More examples.
        var poemToRuth = """
        Our gathering was not destiny, sometimes we are
        the ones that have to look up to new adventures,
        and sometimes we are the ones that have to let go.

        I didn't want to let go of you, but I had to, I didn't
        feel prapared, I admit it, I was scared, I was afraid to
        not live up to your expectations.

        I was such a fool though. I gave up, instead of persisting,
        I gave up, instead of trying harder, I gave up.
        """;

        System.err.println(poemToRuth);

        // Text blocks interprest newlines as \n, meaning they are
        // preserved.
        //
        // Also, the closing delimiter can be on the same line as the
        // end of the content.
        var poemToFriends = """
        I don't have friends.""";

        System.err.println(poemToFriends);

        // We have to be careful when positioning the closing delimiter.
        // This is due to how the Java compiler processes the text block.
        // To be more specific, according to the JEP, the compiler will
        // process the text block in the following steps:
        // 1. Transform line terminators to LF.
        // 2. Eliminate accidental leading and trailing whitespace.
        // 3. Process escape sequenses.
        // For the closing delimiter, the step 2 is the one we have
        // to be careful about. The algorithm involded here is called "re-indentation".
        // Such algorithm is availble for us in the method [String::stripIndent].
        var goodnightMessage = "    This has indentation.   ";
        System.err.println(goodnightMessage);
        System.err.println(goodnightMessage.stripIndent() + " Actually, it doesn't have indentation anymore.");

        // New escape sequenses to control newlines and whitespace.
        // The \<line-terminator> explicitly suppresses the insertion of a newline character.
        var sadSingleLinePoem = """
                                This is a sad poem. \
                                Really sad. \
                                """;

        System.err.println(sadSingleLinePoem);

        // Explicit white space with \s.
        var whiteSpaceIsNotRemovedWithBackslashS = """
        \s\s\s      Hello World.             \
              This is nice. \s\s\s\s\s
        """;

        System.err.println(whiteSpaceIsNotRemovedWithBackslashS);
    }
}

interface OutputBuilder {
    public String toHTML();
    public String toJSON();
}

record Page(String title) implements OutputBuilder {
    @Override
    public String toHTML() {
        return STR."""
    <html>
        <head>
            <title>\{title}</title>
        </head>
        <body>
            <h1>\{title}</h1>
        </body>
    </html>
        """;
    }

    @Override
    public String toJSON() {
        return STR."""
        {
            "title": "\{title}"
        }
        """;
    }
}

/**
 * In summary, text blocks bring multi-line strings to Java,
 * as well as finer control for newlines and whitespace.
 * 
 * Text blocks come with a set of rules we have to be aware of.
 * Specially how they are proccessed by the compiler:
 * 
 * 1. Transform line terminators to LF.
 * 2. Eliminate accidental leading and trailing whitespace.
 * 3. Process escape sequenses.
 * 
 * We have to be careful with the closing delimiter, since it's
 * taken into account with step two of text block processing,
 * which is called "re-indentation". Here the closing delimiter
 * is taken into account when defining the "minumum prefix whitespace".
 * 
 * On the other hand, text blocks give us more control of newlines
 * and whitespace with the new escape sequenses:
 * 
 * 1. \<line-terminator> explicitly suppresses the insertion of a newline character.
 * 2. \s is used to explicitly insert a single space.
 */
