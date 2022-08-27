package pl.javorek.codegenerator.adapter.java

import pl.javorek.codegenerator.application.CodeGenerator

class JavaMethodGeneratorTestImpl implements CodeGenerator<JavaMethodMetadata> {
    final List<JavaMethodMetadata> generatedMethods = new ArrayList<>()

    @Override
    void generate(JavaMethodMetadata codeMetadata) {
        generatedMethods.add(codeMetadata)
    }
}
