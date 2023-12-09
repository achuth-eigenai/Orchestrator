package com.eigenai.orchestrator.controller;

import com.eigenai.orchestrator.domain.ChurnModel;
import com.eigenai.orchestrator.vo.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.eigenai.orchestrator.domain.Status.SUCCESS;

/**
 * The type Churn model controller.
 */
@RestController
@RequestMapping("v1/churn")
public class ChurnModelController {
    /**
     * Create or update churn model response entity.
     *
     * @param churnModel the churn model
     * @return the response entity
     */
    @PostMapping("/create-update-model")
    public ResponseEntity<Response> createOrUpdateChurnModel(@RequestBody ChurnModel churnModel) {
        return ResponseEntity.ok(Response.builder().status(SUCCESS).build());
    }

    /**
     * Generate churn model response entity.
     *
     * @param churnModel the churn model
     * @return the response entity
     */
    @PostMapping("/generate-model")
    public ResponseEntity<Response> generateChurnModel(@RequestBody ChurnModel churnModel) {
        return ResponseEntity.ok(Response.builder().status(SUCCESS).build());
    }

    /**
     * Download predictions response entity.
     *
     * @param churnModel the churn model
     * @return the response entity
     */
    @PostMapping("/download-predictions")
    public ResponseEntity<Response> downloadPredictions(@RequestBody ChurnModel churnModel) {
        return ResponseEntity.ok(Response.builder().status(SUCCESS).build());
    }

    /**
     * Deploy churn model response entity.
     *
     * @param churnModel the churn model
     * @return the response entity
     */
    @PostMapping("/deploy")
    public ResponseEntity<Response> deployChurnModel(@RequestBody ChurnModel churnModel) {
        return ResponseEntity.ok(Response.builder().status(SUCCESS).build());
    }

    /**
     * Delete model response entity.
     *
     * @param churnModel the churn model
     * @return the response entity
     */
    @PostMapping("/delete")
    public ResponseEntity<Response> deleteModel(@RequestBody ChurnModel churnModel) {
        return ResponseEntity.ok(Response.builder().status(SUCCESS).build());
    }
}