package pl.javorek.codegenerator.adapter.java

import pl.javorek.codegenerator.application.CodeGenerator
import pl.javorek.codegenerator.application.CodePredicate
import spock.lang.Specification

class ThreeTierCodeGenerationTest extends Specification {
    static final USE_CASE = "TestUseCase"
    static final FACADE_CONTEXT = "TestContext"
    static final BASE_PACKAGE = "base.package"

    CodeGenerator<JavaClassMetadata> generator
    JavaClassGeneratorTestImpl javaClassGenerator
    JavaMethodGeneratorTestImpl javaMethodGenerator
    JavaClassExistsPredicateTestImpl isClassExists

    def setup() {
        javaClassGenerator = new JavaClassGeneratorTestImpl()
        javaMethodGenerator = new JavaMethodGeneratorTestImpl()
        isClassExists = new JavaClassExistsPredicateTestImpl()
        def config = CommandFacadeGenerationConfig.builder()
                .javaClassGenerator(javaClassGenerator)
                .javaMethodGenerator(javaMethodGenerator)
                .isClassExists(isClassExists)
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

    def "when class already exists then facade class isn't generated"() {
        given:
        def cqrsMetadata = CqrsCommandMetadata.builder()
                .basePackage(BASE_PACKAGE)
                .useCase(USE_CASE)
                .facadeContext(FACADE_CONTEXT)
                .build()

        def classMetadata = JavaClassMetadata.builder()
                .classPackage("base.package.testcontext.application")
                .className("${FACADE_CONTEXT}Facade")
                .build()
        isClassExists.existingClasses
                .add( classMetadata )
        when:
        generator.generate(cqrsMetadata)
        then:
        javaClassGenerator.generatedClasses == [ ]
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
