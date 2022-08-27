package pl.javorek.codegenerator.adapter.java

import pl.javorek.codegenerator.application.CodeGenerator
import spock.lang.Specification

class ThreeTierCodeGenerationTest extends Specification {
    static final USE_CASE = "TestUseCase"
    static final FACADE_CONTEXT = "TestContext"
    static final BASE_PACKAGE = "base.package"

    CodeGenerator<JavaClassMetadata> generator
    JavaClassGeneratorTestImpl javaClassGenerator
    JavaMethodGeneratorTestImpl javaMethodGenerator

    def setup() {
        javaClassGenerator = new JavaClassGeneratorTestImpl()
        javaMethodGenerator = new JavaMethodGeneratorTestImpl()
        def config = CommandFacadeGenerationConfig.builder()
                .javaClassGenerator(javaClassGenerator)
                .javaMethodGenerator(javaMethodGenerator)
                .build()
        generator = config.generator
    }

    def "when no class exists generate facade class"() {
        given:
        def classMetadata = CqrsCommandMetadata.builder()
                .basePackage(BASE_PACKAGE)
                .useCase(USE_CASE)
                .facadeContext(FACADE_CONTEXT)
                .build()
        when:
        generator.generate(classMetadata)
        then:
        def expectedClass = JavaClassMetadata.builder()
                .classPackage("base.package.testcontext.application")
                .className("${FACADE_CONTEXT}Facade")
                .build()
        javaClassGenerator.generatedClasses == [ expectedClass ]
    }

    def "when no method exists generate method"() {
        given:
        def classMetadata = CqrsCommandMetadata.builder()
                .basePackage(BASE_PACKAGE)
                .useCase(USE_CASE)
                .facadeContext(FACADE_CONTEXT)
                .build()
        when:
        generator.generate(classMetadata)
        then:
        def expectedMethod = JavaMethodMetadata.builder()
                .classQualifiedName("base.package.testcontext.application.TestContextFacade")
                .methodName("testUseCase")
                .build()
        javaMethodGenerator.generatedMethods == [ expectedMethod ]
    }
}
