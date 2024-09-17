package com.aitabidalla.task_service.task;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@RequiredArgsConstructor
public class TaskResponse {
    private long id;
    private String title;
    private String description;
    private String status;
    private String userName;
    private LocalDate date;
}
