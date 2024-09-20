package com.aitabidalla.task_service.task;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin("*")
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
    @PutMapping("/{taskId}/complete")
    public ResponseEntity<TaskResponse> markTaskAsCompleted(@PathVariable Long taskId) {
        return ResponseEntity.ok(taskService.markTaskAsCompleted(taskId));
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId) {
        taskService.deleteTask(taskId);
        return ResponseEntity.noContent().build();
    }


}
