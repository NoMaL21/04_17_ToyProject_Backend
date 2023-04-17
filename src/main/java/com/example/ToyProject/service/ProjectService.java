package com.example.ToyProject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ToyProject.model.ProjectEntity;
import com.example.ToyProject.persistence.ProjectRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProjectService {
	
	@Autowired
	private ProjectRepository repository;
	
	public List<ProjectEntity>create(final ProjectEntity entity){
		//Validations
		validate(entity);
		repository.save(entity);
		//return repository.findById(entity.getId());
		return repository.findAll();
	}
	
	public List<ProjectEntity>retrieve(){
		return repository.findAll();
	}
	
	public List<ProjectEntity>update(final ProjectEntity entity){
		//Validations
		validate(entity);
		if (repository.existsById(entity.getKey())) {
			repository.save(entity);
		}
		else {
			throw new RuntimeException("Unknown id");
		}
		
		//return repository.findById(entity.getId());
		return repository.findAll();
	}
	
	public List<ProjectEntity> delete(final ProjectEntity entity) {
		if(repository.existsById(entity.getKey())) {
			repository.deleteById(entity.getKey());
		}
		else {
			throw new RuntimeException("id does not exist");
		}
		
		return repository.findAll();
	}
	
	public void validate(final ProjectEntity entity) {
		if(entity ==null) {
			log.warn("Entity cannot be null.");
			throw new RuntimeException("Entity cannot be null.");
		}
	}
}
