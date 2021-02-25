package com.imedia24.serverside.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.imedia24.serverside.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category ,Long> {

}
