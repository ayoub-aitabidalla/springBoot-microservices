package com.aitabidalla.task_service.task;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final RestTemplate restTemplate;

    private String getUsernameByUserId(long userId) {
        return restTemplate.getForObject("http://USER-SERVICE/user/" + userId, String.class);
    }

    private TaskResponse convertTaskToTaskResponse(Task task, String username) {
        TaskResponse taskResponse = new TaskResponse();
        taskResponse.setUserName(username);
        BeanUtils.copyProperties(task, taskResponse);
        return taskResponse;
    }

    public TaskResponse createTask(Task task) {
        Optional<Task> existingTask = taskRepository.findByUserIdAndTitle(task.getUserId(), task.getTitle());
        if (existingTask.isPresent()) {
            throw new IllegalArgumentException("Task with the same title already exists for this user.");
        }
        task.setDate(LocalDate.now());
        task.setStatus("To Do");
        Task savedTask = taskRepository.save(task);
        String username = getUsernameByUserId(task.getUserId());
        return convertTaskToTaskResponse(savedTask, username);
    }

    public List<TaskResponse> getAllTasksByUserId(long userId) {
        List<Task> tasks = taskRepository.findAllByUserId(userId);
        String username = getUsernameByUserId(userId);
        List<TaskResponse> taskResponses = new ArrayList<>();
        for (Task task : tasks) {
            taskResponses.add(convertTaskToTaskResponse(task, username));
        }
        return taskResponses;
    }


    public TaskResponse markTaskAsCompleted(Long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("Task not found with id: " + taskId));

        task.setStatus("Done");
        Task updatedTask = taskRepository.save(task);
        return convertTaskToTaskResponse(updatedTask, getUsernameByUserId(updatedTask.getUserId()));

    }

    public void deleteTask(Long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("Task not found with id: " + taskId));

        taskRepository.delete(task);
    }




}
