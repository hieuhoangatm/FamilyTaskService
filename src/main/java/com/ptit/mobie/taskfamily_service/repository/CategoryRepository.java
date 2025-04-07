package com.ptit.mobie.taskfamily_service.repository;

import com.ptit.mobie.taskfamily_service.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {
}
