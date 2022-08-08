package pl.javorek.codegenerator.adapter.example;

import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import pl.javorek.codegenerator.application.CodeGenerator;

@Slf4j
@Service
@RequiredArgsConstructor
class CodeGeneratorExample implements CodeGenerator<ConfigExample> {
  private final ConfigExample configExample;

  @Override
  public void generateCode() {
    log.info("Code generator initialized: {}.{}", configExample.codePackage, configExample.context);
  }
}
