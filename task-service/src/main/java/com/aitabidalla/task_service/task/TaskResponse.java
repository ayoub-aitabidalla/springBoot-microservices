package com.aitabidalla.task_service.task;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class TaskResponse {
    private String title;
    private String description;
    private String status;
    private String userName;
}
