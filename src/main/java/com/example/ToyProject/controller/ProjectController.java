package com.example.ToyProject.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ToyProject.dto.ResponseDTO;
import com.example.ToyProject.dto.MemberDTO;
import com.example.ToyProject.dto.ProjectDTO;
import com.example.ToyProject.model.MemberEntity;
import com.example.ToyProject.model.ProjectEntity;
import com.example.ToyProject.service.MemberService;
import com.example.ToyProject.service.ProjectService;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("Project")
public class ProjectController {
	
	@Autowired
	private ProjectService service;
	
	@Autowired
	private MemberService memberservice;
	
	@PostMapping
	public ResponseEntity<?>createProject(@AuthenticationPrincipal String userId, @RequestBody ProjectDTO dto){
	    try {
	        //post localhost:8080/Project
	        log.info("Log:createProject entrance");
	        
	        // MemberEntity bossEntity = memberservice.findByKey(dto.getBoss());
	        MemberEntity bossEntity = memberservice.findByKey(dto.getBoss());
	        log.info("Log:boss dto => entity ok!");
	        
	        ProjectEntity entity = ProjectDTO.toEntity(dto, bossEntity);
	        log.info("Log:dto => entity ok!");
	        
	        List<ProjectEntity> entities = service.create(entity);
	        log.info("Log:service.create ok!");
	        
	        List<ProjectDTO> dtos = entities.stream().map(ProjectDTO::new).collect(Collectors.toList());
	        log.info("Log:entities => dtos ok!");
	        
	        ResponseDTO<ProjectDTO> response = ResponseDTO.<ProjectDTO>builder().data(dtos).build();
	        log.info("Log:responsedto ok!");
	        
	        return ResponseEntity.ok().body(response);
	    } catch (Exception e) {
			String error=  e.getMessage();
			ResponseDTO<ProjectDTO> response = ResponseDTO.<ProjectDTO>builder().error(error).build();
			return ResponseEntity.badRequest().body(response);
		}
	}
	
	@GetMapping
	public ResponseEntity<?>retrieveProjectList(@AuthenticationPrincipal String userId){
		
		List<ProjectEntity> entities = service.retrieve();
		List<ProjectDTO> dtos = entities.stream().map(ProjectDTO::new).collect(Collectors.toList());
		
		ResponseDTO<ProjectDTO> response = ResponseDTO.<ProjectDTO>builder().data(dtos).build();
		
		return ResponseEntity.ok().body(response);
	}
	
	@PutMapping
	public ResponseEntity<?>updateProject(@AuthenticationPrincipal String userId, @RequestBody ProjectDTO dto){
		try {
	        MemberEntity bossEntity = memberservice.findByKey(dto.getBoss());
	        
	        ProjectEntity entity = ProjectDTO.toEntity(dto, bossEntity);
			
			List<ProjectEntity> entities = service.update(entity);
			
			List<ProjectDTO> dtos = entities.stream().map(ProjectDTO::new).collect(Collectors.toList());
			
			ResponseDTO<ProjectDTO> response = ResponseDTO.<ProjectDTO>builder().data(dtos).build();
			
			return ResponseEntity.ok().body(response);
		}catch (Exception e) {
			String error = e.getMessage();
			ResponseDTO<ProjectDTO> response = ResponseDTO.<ProjectDTO>builder().error(error).build();
			return ResponseEntity.badRequest().body(response);
		}
	}
	
	@DeleteMapping
	public ResponseEntity<?> deleteProject(@AuthenticationPrincipal String userId, @RequestBody ProjectDTO dto){
		try {
	        MemberEntity bossEntity = memberservice.findByKey(dto.getBoss());
	        
	        ProjectEntity entity = ProjectDTO.toEntity(dto, bossEntity);
			
			List<ProjectEntity> entities = service.delete(entity);
			
			List<ProjectDTO> dtos = entities.stream().map(ProjectDTO::new).collect(Collectors.toList());
			
			ResponseDTO<ProjectDTO> response = ResponseDTO.<ProjectDTO>builder().data(dtos).build();
			
			return ResponseEntity.ok().body(response);
		}catch (Exception e) {
			String error = e.getMessage();
			ResponseDTO<ProjectDTO> response = ResponseDTO.<ProjectDTO>builder().error(error).build();
			return ResponseEntity.badRequest().body(response);
		}
	}
}
