package pl.javorek.codegenerator.application;

public interface CodeGenerator<T> {
  void generate(T codeMetadata);
}
