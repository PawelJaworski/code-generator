package pl.javorek.codegenerator.adapter.java;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class JavaMethodMetadata {
  String classQualifiedName;
  String methodName;
}
