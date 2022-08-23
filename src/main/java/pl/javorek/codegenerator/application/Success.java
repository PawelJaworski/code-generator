package pl.javorek.codegenerator.application;

import java.util.List;
import lombok.Value;

@Value
public class Success<T> implements Result<T> {
  T value;

  public Success() {
    this.value = null;
  }

  @Override
  public List<String> getErrors() {
    throw new IllegalStateException("Cannot get errors of success operation " + this.value);
  }

  @Override
  public boolean isSuccess() {
    return true;
  }

  @Override
  public boolean isFailure() {
    return false;
  }
}
