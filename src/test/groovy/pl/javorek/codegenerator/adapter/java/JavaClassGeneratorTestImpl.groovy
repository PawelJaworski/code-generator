package pl.javorek.codegenerator.adapter.java

import javax.annotation.processing.Generated
import lombok.Getter
import lombok.Value
import pl.javorek.codegenerator.application.CodeGenerator
import pl.javorek.codegenerator.application.Result
import pl.javorek.codegenerator.application.Success

class JavaClassGeneratorTestImpl implements CodeGenerator<JavaClassMetadata> {
    final List<JavaClassMetadata> generatedClasses = new ArrayList<>()

    @Override
    Result generate(JavaClassMetadata codeMetadata) {
        generatedClasses.add(codeMetadata)
        return new Success()
    }
}
