package com.ptit.mobie.taskfamily_service.repository;

import com.ptit.mobie.taskfamily_service.model.PersonalTask;
import com.ptit.mobie.taskfamily_service.model.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalTaskRepository extends JpaRepository<PersonalTask,Integer> {
    Page<PersonalTask> findAll(Pageable pageable);
}
