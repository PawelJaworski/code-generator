package pl.javorek.codegenerator.application;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Try {
  public static Result failureOnException(Runnable runnable) {
        try {
          runnable.run();
          return new Success<>();
        } catch(Exception e) {
          log.error("Error occurred: ", e);
          return Failure.builder()
            .error(e.getMessage())
            .build();
        }
  }
}
