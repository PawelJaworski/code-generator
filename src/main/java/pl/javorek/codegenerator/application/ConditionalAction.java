package pl.javorek.codegenerator.application;

import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class ConditionalAction<T> implements Function<T, ConditionalAction.ConditionalResult<T>> {
    private final Predicate<T> predicate;
    private final Consumer<T> consumer;

    public ConditionalAction(Predicate<T> predicate, Consumer<T> consumer) {
        this.predicate = predicate;
        this.consumer = consumer;
    }

    public static <T> ThenAction<T> ifFalse(Predicate<T> predicate) {
        return new ThenAction<>(predicate.negate());
    }

    public static <T> ThenAction<T> ifTrue(Predicate<T> predicate) {
        return new ThenAction<>(predicate);
    }

    @Override
    public ConditionalResult<T> apply(T value) {
        if (!predicate.test(value)) {
            return new ConditionalResult<>(Optional.empty());
        }
        consumer.accept(value);
        return  new ConditionalResult<>(Optional.of(value));

    }

    @RequiredArgsConstructor
    public static class ThenAction<T> {
        private final Predicate<T> predicate;

        public ConditionalAction<T> then(Consumer<T> supplier) {
            return new ConditionalAction<>(predicate, supplier);
        }
    }

    @RequiredArgsConstructor
    public static class ConditionalResult<T> {
        private final Optional<T> value;

        public void orElse(Consumer<T> elseConsumer) {
            value.ifPresent(elseConsumer::accept);
        }
    }
}
