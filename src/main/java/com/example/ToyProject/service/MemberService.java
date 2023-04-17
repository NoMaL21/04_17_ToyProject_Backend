package com.example.ToyProject.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.ToyProject.persistence.MemberRepository;
import com.example.ToyProject.model.MemberEntity;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MemberService {
	@Autowired
	private MemberRepository MemberRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public MemberEntity create(final MemberEntity MemberEntity) {
		if(MemberEntity == null|| MemberEntity.getEmail() ==null) {
			throw new RuntimeException("Invalid arguments");
		}
		final String email = MemberEntity.getEmail();
		if(MemberRepository.existsByEmail(email)) {
			log.warn("Email already exists {}", email);
			throw new RuntimeException("Email already exists");
		}
		
		return MemberRepository.save(MemberEntity);
	}
	
	public MemberEntity getByCredentials(final String email, final String password, final PasswordEncoder encoder) {
		
		final MemberEntity originalMember = MemberRepository.findByEmail(email);
		
		if(originalMember != null && encoder.matches(password, originalMember.getPassword())) {
			return originalMember;
		}
		
		return null;
	}
	
	public MemberEntity findByKey(final String key) {
		final MemberEntity originalMember = MemberRepository.findByKey(key);
		
		if(originalMember != null && passwordEncoder.matches(key, originalMember.getMemberkey())) {
			return originalMember;
		}
		
		return null;
	}
}
