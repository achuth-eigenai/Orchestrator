package com.eigenai.orchestrator.controller;

import com.eigenai.orchestrator.domain.CLVInput;
import com.eigenai.orchestrator.domain.CLVOutput;
import com.eigenai.orchestrator.domain.Status;
import com.eigenai.orchestrator.model.User;
import com.eigenai.orchestrator.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/model")
public class ModelController {
	@PostMapping("/create-clv")
	public Status createModel(CLVInput CLVInput) {
		return Status.SUCCESS;
	}
	@PostMapping("predict-clv")
	public CLVOutput predictModel(CLVInput CLVInput) {
		return CLVOutput.builder().status(Status.SUCCESS).build();
	}

}