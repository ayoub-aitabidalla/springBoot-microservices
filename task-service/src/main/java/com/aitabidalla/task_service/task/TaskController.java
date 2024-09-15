package com.aitabidalla.task_service.task;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/task")
public class TaskController {
    private final TaskService taskService;

    @PostMapping("/add")
    public TaskResponse createTask(@RequestBody Task task)
    {
        return taskService.createTask(task);
    }
    @GetMapping("/{userId}")
    public List<TaskResponse> getTasksByUserId(@PathVariable long userId)
    {
        return taskService.getAllTasksByUserId(userId);
    }


}
