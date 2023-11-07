
public class OptionalExample {
    public static void main(String[] args) {
        var user = getUser();

        switch (user) {
            case Optional.Some(var u) -> {
                System.err.println(u);
            }
            case Optional.None() -> {
                System.err.println("No user found.");
            }
        }
    }

    static Optional<User> getUser() {
        return new Optional.Some<>(
            new User("Edwing123", "edwing123@example.com")
        );
    }
}

record User(String name, String email) {}

sealed interface Optional<T> {
    record Some<T>(T value) implements Optional<T> {}
    record None() implements Optional {}
}