package pl.javorek.codegenerator.adapter.java;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class JavaClassMetadata {
  String classPackage;
  String className;
}
