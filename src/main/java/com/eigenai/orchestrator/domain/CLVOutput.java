package com.eigenai.orchestrator.domain;

import lombok.Builder;

@Builder
public class CLVOutput implements MLModelData {
    private Status status;
}
