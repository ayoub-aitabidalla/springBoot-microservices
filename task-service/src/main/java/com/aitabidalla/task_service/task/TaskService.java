package com.aitabidalla.task_service.task;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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



}
