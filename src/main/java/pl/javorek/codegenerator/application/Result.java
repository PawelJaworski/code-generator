package pl.javorek.codegenerator.application;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public interface Result<T> {
  T getValue();

  List<String> getErrors();

  default boolean isFailure() {
    return !isSuccess();
  }

  default boolean isSuccess() {
    return false;
  }

  default <P>Result<T> doOnSuccess(Function<P, T> onSuccess) {
    return this;
  }

  default void doOnFailure(Consumer<List<String>> onError) {
  }
}
