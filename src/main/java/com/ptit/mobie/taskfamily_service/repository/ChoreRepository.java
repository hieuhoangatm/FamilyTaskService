package com.ptit.mobie.taskfamily_service.repository;

import com.ptit.mobie.taskfamily_service.model.Chore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChoreRepository extends JpaRepository<Chore,Integer> {
}
