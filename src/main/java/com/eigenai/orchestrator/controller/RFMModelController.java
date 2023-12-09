package com.eigenai.orchestrator.controller;

import com.eigenai.orchestrator.domain.RFMModel;
import com.eigenai.orchestrator.vo.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.eigenai.orchestrator.domain.Status.SUCCESS;

/**
 * The type Rfm model controller.
 */
@RestController
@RequestMapping("v1/rfm")
public class RFMModelController {
    /**
     * Perform rfm analysis response entity.
     *
     * @param rfmModel the rfm model
     * @return the response entity
     */
    @PostMapping("/perform-analysis")
    public ResponseEntity<Response> performRFMAnalysis(@RequestBody RFMModel rfmModel) {
        return ResponseEntity.ok(Response.builder().status(SUCCESS).build());
    }

    /**
     * Download results response entity.
     *
     * @param rfmModel the rfm model
     * @return the response entity
     */
    @PostMapping("/download-results")
    public ResponseEntity<Response> downloadResults(@RequestBody RFMModel rfmModel) {
        return ResponseEntity.ok(Response.builder().status(SUCCESS).build());
    }

    /**
     * Automate response entity.
     *
     * @param rfmModel the rfm model
     * @return the response entity
     */
    @PostMapping("/automate")
    public ResponseEntity<Response> automate(@RequestBody RFMModel rfmModel) {
        return ResponseEntity.ok(Response.builder().status(SUCCESS).build());
    }
}