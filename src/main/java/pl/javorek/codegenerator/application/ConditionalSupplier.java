package pl.javorek.codegenerator.application;

import java.util.Optional;

public interface ConditionalSupplier<T> {
    Optional<T> get(T value);
}
