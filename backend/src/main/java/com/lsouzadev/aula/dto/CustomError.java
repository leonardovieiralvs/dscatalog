package com.lsouzadev.aula.dto;

import lombok.Builder;

import java.time.Instant;
import java.util.List;

@Builder
public record CustomError(Instant timestamp, Integer status, String errorMessage, List<ErroCampo> errors, String path) {
}
