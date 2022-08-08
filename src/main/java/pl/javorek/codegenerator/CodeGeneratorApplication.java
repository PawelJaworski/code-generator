package pl.javorek.codegenerator;

import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.javorek.codegenerator.adapter.example.ConfigExample;
import pl.javorek.codegenerator.application.CodeGenerator;

@SpringBootApplication
@RequiredArgsConstructor
public class CodeGeneratorApplication {
	private final CodeGenerator<ConfigExample> codeGenerator;

	public static void main(String[] args) {
		SpringApplication.run(CodeGeneratorApplication.class, args);
	}

	@PostConstruct
	void postConstruct() {
		codeGenerator.generateCode();
	}
}
