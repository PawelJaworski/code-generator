package pl.javorek.codegenerator.adapter.java

import pl.javorek.codegenerator.application.CodeGenerator

class JavaClassGeneratorTestImpl implements CodeGenerator<JavaClassMetadata> {
    final List<JavaClassMetadata> generatedClasses = new ArrayList<>()

    @Override
    void generate(JavaClassMetadata codeMetadata) {
        generatedClasses.add(codeMetadata)
    }
}
