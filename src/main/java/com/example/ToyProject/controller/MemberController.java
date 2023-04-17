package com.example.ToyProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ToyProject.dto.ResponseDTO;
import com.example.ToyProject.dto.MemberDTO;
import com.example.ToyProject.model.MemberEntity;
import com.example.ToyProject.security.TokenProvider;

import com.example.ToyProject.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/auth")
public class MemberController {
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private TokenProvider tokenProvider;
	
	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	@PostMapping("/signup")
	public ResponseEntity<?>registerMember(@RequestBody MemberDTO MemberDTO){
		try {
			MemberEntity Member = MemberEntity.builder()
					.email(MemberDTO.getEmail())
					.membername(MemberDTO.getMembername())
					.password(passwordEncoder.encode(MemberDTO.getPassword()))
					.build();
			
			MemberEntity registeredMember = memberService.create(Member);
			MemberDTO responseMemberDTO = MemberDTO.builder()
					.email(registeredMember.getEmail())
					.key(registeredMember.getKey())
					.membername(registeredMember.getMembername())
					.build();
			return ResponseEntity.ok().body(responseMemberDTO);
		}catch(Exception e) {
			ResponseDTO responseDTO = ResponseDTO.builder().error(e.getMessage()).build();
			
			return ResponseEntity.badRequest().body(responseDTO);
		}
	}
	
	@PostMapping("/signin")
	public ResponseEntity<?>authenticate(@RequestBody MemberDTO MemberDTO){
		MemberEntity Member = memberService.getByCredentials(
				MemberDTO.getEmail(), 
				MemberDTO.getPassword(), 
				passwordEncoder);
		
		if(Member != null) {
			final String token = tokenProvider.create(Member);
			final MemberDTO responseMemberDTO = MemberDTO.builder()
					.email(Member.getEmail())
					.key(Member.getKey())
					.token(token)
					.build();
			
			return ResponseEntity.ok().body(responseMemberDTO);
		}else {
			ResponseDTO responseDTO = ResponseDTO.builder()
					.error("Login failed")
					.build();
			return ResponseEntity.badRequest().body(responseDTO);
		}
		
	}
}