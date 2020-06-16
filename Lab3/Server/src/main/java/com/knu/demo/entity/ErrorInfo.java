package com.knu.demo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
@NoArgsConstructor
public class ErrorInfo {
    private long timestamp;
    private String message;
    private String developerMessage;
}