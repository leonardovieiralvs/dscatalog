package com.lsouzadev.aula.dto;

import lombok.Builder;

import java.time.Instant;

@Builder
public record CustomError(Instant timestamp, Integer status, String error, String path) {
}
