package pl.javorek.codegenerator.adapter.java;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import pl.javorek.codegenerator.application.CodeGenerator;
import pl.javorek.codegenerator.application.Result;

@Builder
@RequiredArgsConstructor
public class CommandFacadeGenerationConfig {
  private final CodeGenerator<JavaClassMetadata> javaClassGenerator;

  public CodeGenerator<CqrsCommandMetadata> getGenerator() {
    return cqrsCommandMetadata -> {

      return generateFacade(cqrsCommandMetadata);
    };
  }

  private Result generateFacade(CqrsCommandMetadata metadata) {
    var basePackage = metadata.getBasePackage();
    var javaClassMetadata = JavaClassMetadata.builder()
      .classPackage(String.format("%s.%s.application", basePackage, metadata.getFacadeContext().toLowerCase()))
      .className(metadata.getFacadeContext())
      .build();
    return javaClassGenerator.generate(javaClassMetadata);

  }
}
