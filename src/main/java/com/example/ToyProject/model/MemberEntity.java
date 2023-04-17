package com.example.ToyProject.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.ToyProject.dto.MemberDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Member")
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "memberkey")
    private String memberkey;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "membername")
    private String membername;
    
    @Column(name = "password")
    private String password;
    
    private String phone;
    private int field;

    public MemberDTO toDTO() {
        return MemberDTO.builder()
            .memberkey(memberkey)
            .email(email)
            .membername(membername)
            .password(password)
            .phone(phone)
            .field(field)
            .build();
    }

    public static MemberEntity fromDTO(MemberDTO dto) {
        return MemberEntity.builder()
            .email(dto.getEmail())
            .membername(dto.getMembername())
            .password(dto.getPassword())
            .phone(dto.getPhone())
            .field(dto.getField())
            .build();
    }
}
