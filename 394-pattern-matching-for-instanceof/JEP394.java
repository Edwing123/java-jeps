
public class JEP394 {
    public static void main(String[] args) {
        // When working with Java, it's common to
        // have code checking if a particular object
        // is an instance of a class.
        Object obj = "Hello";

        if (obj instanceof String) {
            var str = (String)obj;
            System.err.println(STR."The value is \{str}.");
        }

        // This involves testing with [instanceof] and
        // performing a cast.

        // Java enhances the instanceof operator to take in a "type pattern":
        // it involves a predicate and a pattern variable.
        var isEmpty = obj instanceof String str && str.length() > 0;

        System.err.println(isEmpty);

        // When learning about this, there are several concepts
        // around it, for example: flow analysis, scope, etc.
        // Since this is a little introduction to learn about
        // this feature, for now I'll leave it here and learn
        // more about it later.
    }
}