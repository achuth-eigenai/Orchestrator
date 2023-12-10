package com.eigenai.orchestrator.vo;

import com.eigenai.orchestrator.domain.Status;
import lombok.Builder;
import lombok.Data;

/**
 * The type Response.
 */
@Data
public class UserLoginRequest {
    private String username;
    private String password;
}
