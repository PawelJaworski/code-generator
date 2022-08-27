package pl.javorek.codegenerator.adapter.java;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pl.javorek.codegenerator.application.CodeGenerator;
import pl.javorek.codegenerator.application.Try;

@Slf4j
@Builder
@RequiredArgsConstructor
public class CommandFacadeGenerationConfig {
  private final CodeGenerator<JavaClassMetadata> javaClassGenerator;

  public CodeGenerator<CqrsCommandMetadata> getGenerator() {
    return cqrsCommandMetadata -> {
      Try.failureOnException(() -> {
        generateFacade(cqrsCommandMetadata);
      });
    };
  }

  private void generateFacade(CqrsCommandMetadata metadata) {
    var basePackage = metadata.getBasePackage();
    var javaClassMetadata = JavaClassMetadata.builder()
      .classPackage(String.format("%s.%s.application", basePackage, metadata.getFacadeContext().toLowerCase()))
      .className(String.format("%sFacade", metadata.getFacadeContext()))
      .build();

    log.info("Generating CommandFacade: {}", javaClassMetadata);
    javaClassGenerator.generate(javaClassMetadata);
  }
}
