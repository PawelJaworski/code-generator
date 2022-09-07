package pl.javorek.codegenerator.application;

/**
 * Code generation abstraction.
 * @param <T>
 */
@FunctionalInterface
public interface CodeGenerator<T> {
  void generate(T codeMetadata);
}
