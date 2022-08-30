package pl.javorek.codegenerator.application;

/**
 * Code generation abstraction.
 * @param <T>
 */
public interface CodeGenerator<T> {
  void generate(T codeMetadata);
}
