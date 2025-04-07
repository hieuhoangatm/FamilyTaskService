package com.ptit.mobie.taskfamily_service.repository;

import com.ptit.mobie.taskfamily_service.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task,Integer> {
}
