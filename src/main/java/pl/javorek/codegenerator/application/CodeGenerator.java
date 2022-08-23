package pl.javorek.codegenerator.application;

public interface CodeGenerator<T> {
  Result generate(T codeMetadata);
}
