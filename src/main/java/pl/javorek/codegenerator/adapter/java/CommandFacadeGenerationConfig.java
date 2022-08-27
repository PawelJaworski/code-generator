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
  private final CodeGenerator<JavaMethodMetadata> javaMethodGenerator;

  public CodeGenerator<CqrsCommandMetadata> getGenerator() {
    return cqrsCommandMetadata -> {
      Try.failureOnException(() -> {
        generateFacade(cqrsCommandMetadata);
        generateCommandMethod(cqrsCommandMetadata);
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

  private void generateCommandMethod(CqrsCommandMetadata metadata) {
    var basePackage = metadata.getBasePackage();
    var javaClassMetadata = JavaClassMetadata.builder()
      .classPackage(String.format("%s.%s.application", basePackage, metadata.getFacadeContext().toLowerCase()))
      .className(String.format("%sFacade", metadata.getFacadeContext()))
      .build();

      var methodName = decapitalizeString(metadata.getUseCase());
      var javaMethodMetadata = JavaMethodMetadata.builder()
        .classQualifiedName(javaClassMetadata.getClassPackage() + "." + javaClassMetadata.getClassName())
          .methodName(methodName)
          .build();
      javaMethodGenerator.generate(javaMethodMetadata);
  }

  public static String decapitalizeString(String string) {
    return string == null || string.isEmpty() ? "" :
      Character.toLowerCase(string.charAt(0)) + string.substring(1);
  }
}
