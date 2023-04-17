package com.example.ToyProject.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.ToyProject.model.ProjectEntity;

@Repository
public interface ProjectRepository extends JpaRepository<ProjectEntity, String>{
	
	@Query("SELECT t FROM ProjectEntity t")
	List<ProjectEntity>findAll();
}
