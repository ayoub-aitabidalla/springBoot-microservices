package com.aitabidalla.task_service.task;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findAllByUserId(long userId);

    Optional<Task> findByUserIdAndTitle(long userId, String title);

}
