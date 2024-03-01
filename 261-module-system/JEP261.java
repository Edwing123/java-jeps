/**
 * JEP 261: Module system.
 *
 * Modules in Java are introduced to better organized Java applications.
 * A module is composed of code and data. Where the code is represented
 * by a set of packages, and data is represented by resources (images, video, config, etc).
 *
 * You develop your Java application as a module, this module, through a module declaration file,
 * declares the modules it requires as dependencies, once you require a module, you can
 * use the EXPORTED packages of that module.
 *
 * This brings such a strict encapsulation mechanism to Java, where you declare
 * what you require (from the perspective of a consumer), and also you define
 * what other modules can consume from you (from the perspective of a producer).
 *
 * This is basic high level explanation.
 *
 * Now let's talk about "phases".
 *
 * When developing in Java, we go through the two main phases of a program:
 *
 * 1. Compilation.
 * 2. Execution.
 *
 * #1 is accomplished with [javac], and #2 with [java].
 *
 * With modules, a new phase is introduced: linking, it goes between #1 and #2.
 *
 * The JEP puts this concisely:
 *
 * > We add the notion of link time, an optional phase between the two in
 * > which a set of modules can be assembled and optimized into a custom run-time image.
 *
 * ## Module paths
 *
 * The module path is the means which the module system uses to find
 * modules. The module path is a sequence of paths to module definitions.
 *
 * A module definition is either a:
 *
 * 1. A modular JAR file or JMOD file that contains a compiled module difinitions.
 * 2. Or, a directory whose name follows the convention of module naming (dev.foo.bar), and
 *    inside that directory there is a hierarchy of packages.
 *
 * For the second case, the directory can be a compiled "module definition", that is,
 * it contains a compiled "module-info.class" file and compiled class files and resources.
 * Or it can be a source module definition, that is, it contains a "module-info.java" file,
 * and Java source code and resources.
 *
 * The module path, as I said before, it's a sequence of paths separated by the platform
 * path separator, on most platforms (UNIX-like) that is a ":", and on Windows, that is a ";".
 *
 * The JEP continues explaining about "Root modules", and to be honest, it requires me
 * to know about class paths and more about how packages work in order to understand it.
 * So for now I'll give it a shadow read, at least to understand the basic idea.
 *
 * Alright, Java applications are composed of modules, and modules have dependencies upon
 * other modules, this resolves in the creation of the modules graph, such graph has roots,
 * and those roots are the so-called "Root modules".
 */

public class JEP261 {
    public static void main(String... args) {
        System.err.println("The Java module system.");
    }
}