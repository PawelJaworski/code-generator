package pl.javorek.codegenerator.adapter.java;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class CqrsCommandMetadata {
  String basePackage;
  String facadeContext;
  String useCase;
}
