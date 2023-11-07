
/**
 * @author edwing123
 * 
 * Record patterns in Java let us deconstruct
 * record class based on pattern matching.
 */

public class JEP440 {
    public static void main(String... args) {
        // Record patterns laverage type patterns (those used in instanceof and switch patterns)
        // to deconstruct record classes into their components.

        Object javaBooksPurchase = new PurchaseOrder("Edwin", "Mastering Lambda Expressions", 1);

        if (javaBooksPurchase instanceof PurchaseOrder(var name, var item, var quantity)) {
            System.err.println(
                STR."""
                Your purchase summary.
                name: \{name}.
                item: \{item}.
                quantity: \{quantity}.
                """
            );
        }

        // It doesn't seem that useful in this example, to be honest.
        // Though for now I'll keep referring to the proposed
        // examples from the JEP + adding my creative touch.

        // obj doesn't neccesarly have to be an `Object` in order
        // to do record pattern matching.
        var rubyBooksPurchase = new PurchaseOrder("Edwin", "Mastering Ruby", 1);

        if (rubyBooksPurchase instanceof PurchaseOrder(var name, var item, var quantity)) {
            System.err.println(
                STR."""
                Your purchase summary.
                name: \{name}.
                item: \{item}.
                quantity: \{quantity}.
                """
            );
        }

        // Though it seems weird that you have to use an if statement here.
        // It's not neccessary, but if we use a block here, we can easily
        // have more than one statemet.

        // This is another way to do it.
        var pythonBooksPurchase = new PurchaseOrder("Edwin", "Mastering Python", 1);
        var msg = pythonBooksPurchase instanceof PurchaseOrder(var name, var item, var quantity) ?
            STR."""
            Your purchase summary.
            name: \{name}.
            item: \{item}.
            quantity: \{quantity}.
            """
            : "No purchase order found.";

        System.err.println(msg);

        // How come the pattern variables are accessible there?
        // To accomplish this, the Java compiler performs a
        // process called flow analysis, which determines the
        // scope of the pattern variables.
        //
        // In the example above, the pattern variables are accesible
        // in the true branch of the ternary conditional, however,
        // they cannot be accessed in the false branch.

        // Record patterns allow nesting of type patterns.
        var characters = new Character[]{
            new Character("Edwin", 220, new Position(10, 10)),
            new Character("Ruth", 120, new Position(10, 10)),
            new Character("Michi", 167, new Position(10, 10))
        };

        for (var character : characters) {
            if (character instanceof Character(var name, _, Position(var x, var y))) {
                System.err.println(
                    STR."""
                    Character \{name} is at position (\{x}, \{y}).
                    """
                );
            }

            if (character instanceof Character c) {
                System.err.println(c);
            }

            // There is more to learn about this feature, especially
            // the myriad of terms that are used to describe it.
            // As well as the rules that govern it.
        }
    }
}

record PurchaseOrder(String name, String item, int quantity) {}

record Position(int x, int y) {}

record Character(String name, int level, Position position) {}