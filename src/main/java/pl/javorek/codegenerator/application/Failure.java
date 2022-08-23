package pl.javorek.codegenerator.application;

import java.util.List;
import lombok.Value;

@Value
public class Failure<T> implements Result<T> {
  private List<String> errors;

  @Override
  public T getValue() {
    throw new IllegalStateException("Cannot get value of Failure.");
  }
}
