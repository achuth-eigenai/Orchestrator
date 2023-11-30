package com.eigenai.orchestrator;

import lombok.Builder;
import lombok.Data;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class OrchestratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrchestratorApplication.class, args);
	}
}

@RestController
class OrchestratorController {
	@GetMapping("create-model")
	public CreateOutput createModel(CreateInput createInput) {
		return CreateOutput.builder().status("Success").build();
	}
}

interface MLModel  {
}

@Data
class CreateInput implements MLModel {
	private String source;
}

@Builder
class CreateOutput implements MLModel {
	private String status;
}
