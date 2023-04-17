package com.example.ToyProject.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ToyProject.model.MemberEntity;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, String> {
	MemberEntity findByEmail(String email);
	Boolean existsByEmail(String email);
	MemberEntity findByEmailAndPassword(String email, String pasword);
	MemberEntity findByKey(String key);
}
