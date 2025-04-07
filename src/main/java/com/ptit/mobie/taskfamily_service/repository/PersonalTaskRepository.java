package com.ptit.mobie.taskfamily_service.repository;

import com.ptit.mobie.taskfamily_service.model.PersonalTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalTaskRepository extends JpaRepository<PersonalTask,Integer> {
}
