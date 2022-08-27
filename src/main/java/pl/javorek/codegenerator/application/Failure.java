package pl.javorek.codegenerator.application;

import java.util.List;
import lombok.Builder;
import lombok.Singular;
import lombok.Value;

@Builder
@Value
public class Failure<T> implements Result<T> {
  @Singular
  private List<String> errors;

  @Override
  public T getValue() {
    throw new IllegalStateException("Cannot get value of Failure.");
  }
}
