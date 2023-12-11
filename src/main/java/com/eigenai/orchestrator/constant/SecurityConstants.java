package com.eigenai.orchestrator.constant;

/**
 * The type Security constants.
 */
public class SecurityConstants {
    /**
     * The constant IS_ADMIN.
     */
    public static final String IS_SHOPIFY = "hasAuthority('SCOPE_eigenai-resource-server/shopify-app')";
    /**
     * The constant IS_USER.
     */
    public static final String IS_USER = "hasRole('ROLE_USER')";
}
