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
    public ResponseEntity<String> markTaskAsCompleted(@PathVariable Long taskId) {
       String resp =  taskService.markTaskAsCompleted(taskId);
        return ResponseEntity.ok(resp);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId) {
        taskService.deleteTask(taskId);
        return ResponseEntity.noContent().build();
    }


}
