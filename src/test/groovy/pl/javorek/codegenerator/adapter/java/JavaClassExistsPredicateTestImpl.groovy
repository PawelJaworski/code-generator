package pl.javorek.codegenerator.adapter.java

import pl.javorek.codegenerator.application.CodePredicate

class JavaClassExistsPredicateTestImpl implements CodePredicate<JavaClassMetadata> {
    Set<JavaClassMetadata> existingClasses = new HashSet<>()

    @Override
    boolean test(JavaClassMetadata javaClassMetadata) {
        return existingClasses.contains(javaClassMetadata)
    }
}
