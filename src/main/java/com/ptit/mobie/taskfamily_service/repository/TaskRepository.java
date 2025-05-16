package com.ptit.mobie.taskfamily_service.repository;

import com.ptit.mobie.taskfamily_service.enums.TaskStatus;
import com.ptit.mobie.taskfamily_service.model.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Integer> {
    Page<Task> findAll(Pageable pageable);
    List<Task> findAllByStatusAndDeadlineBefore(TaskStatus status, LocalDate deadline);
}
