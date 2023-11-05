
public class JEP395 {

    public static void main(String[] args) {
        var javaOffer = new Offer(1, "", "Java Developer", "1000", new Company(1, "Java", "USA"));
        System.err.println(javaOffer);
    }
}

/**
 * Records are a new kind of class in Java that help in modeling plain
 * data.class
 *
 * A record is a special Java class that models data transparently. A record is
 * composed of three essensial parts: 1. A name: the name of the record class.
 * 2. A header: the fields the record will have. 3. A body: the body of the
 * record.
 *
 * The header is composed of the fields or state that the record will represent.
 * In fact, the name for each "field" is called a component.
 *
 * For each component, the Java compiler will: 1. Create a private final field
 * with the same name and type. 2. Create a public accessor method with the same
 * name and type.
 *
 * The header is also known as the state description.
 *
 * Also, the Java compiler will create a constructor that has the same signature
 * as the header, and will initialize the fields with the values passed to the
 * constructor when creating a new instance of the record. This is called the
 * canonical constructor.
 *
 * And finally, the compiler will generate appropriate implementations for the
 * equals(), hashCode(), and toString() methods.
 */
record Point(int x, int y) {

}

/**
 * For the above record, this is how it would look like:
 */
// record Point(int x, int y) {
//     // Implicitly declared fields
//     private final int x;
//     private final int y;
//     // Other implicit declarations elided ...
//     // Implicitly declared canonical constructor
//     Point(int x, int y) {
//         this.x = x;
//         this.y = y;
//     }
// }
record Company(int id, String name, String location) {

}

record Offer(int id, String title, String description, String salary, Company company) {
    Offer {
        validateNotEmpty(title, "title");
        validateNotEmpty(description, "description");
    }

    private static void validateNotEmpty(String value, String field) {
        if (value.length() == 0) {
            throw new IllegalArgumentException(STR."\{field} cannot be empty.");
        }
    }
}
