package pl.javorek.codegenerator.adapter.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.InputStream;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
@Slf4j
public class ConfigExample {
  private static final ObjectMapper MAPPER = new ObjectMapper();

  final String codePackage;
  final String context;

  ConfigExample(Environment environment) {
    var file = environment.getRequiredProperty("config.json.file");
    var asJsonNode = readFile(file);
    this.codePackage = asJsonNode.get("codePackage")
      .asText();
    this.context = asJsonNode.get("context")
      .asText();
  }

  @SneakyThrows
  private JsonNode readFile(String filename) {
    File directory = new File(filename);
    return MAPPER.readTree(directory);
  }
}
