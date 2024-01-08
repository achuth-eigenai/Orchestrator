package com.eigenai.orchestrator.controller;

import com.eigenai.orchestrator.vo.S3FileUploadResponse;
import com.eigenai.orchestrator.service.S3FileUploadService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;

@RestController
@RequestMapping("/api/v1/documents")
@Tag(name = "Documents")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DocumentsController {
    @Autowired
    private S3FileUploadService s3FileUploadService;

    /**
     * get url from s3 to upload file.
     * @param userId
     * @return
     */
    @GetMapping("get-url-to-upload-file-onto-s3/{userId}")
    public S3FileUploadResponse getUrlToUploadOntoS3(@PathVariable("userId") String userId) {
        return s3FileUploadService.uploadFileToS3(userId);
    }
}
