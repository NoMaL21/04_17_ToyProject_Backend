package com.example.ToyProject.dto;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.ToyProject.model.MemberEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {
	private String token;
	private String memberkey;
	private String email;
	private String membername;
	private String password;
	private String phone;
	private int field;
	
	public static MemberEntity toEntity(MemberDTO memberDTO, PasswordEncoder passwordEncoder) {
	    return MemberEntity.builder()
	        .memberkey(memberDTO.getMemberkey())
	        .email(memberDTO.getEmail())
	        .membername(memberDTO.getMembername())
	        .password(passwordEncoder.encode(memberDTO.getPassword())) // 패스워드는 인코딩해서 저장
	        .phone(memberDTO.getPhone())
	        .field(memberDTO.getField())
	        .build();
	}
}
