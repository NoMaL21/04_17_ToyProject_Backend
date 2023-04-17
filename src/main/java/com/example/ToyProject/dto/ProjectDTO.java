package com.example.ToyProject.dto;


import java.util.Date;

import com.example.ToyProject.model.MemberEntity;
import com.example.ToyProject.model.ProjectEntity;
import com.example.ToyProject.service.MemberService;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectDTO {
    private String projectkey;
    private String name;
    private String info;
    private int field;
    private int expected;
    private int status;
    private Date sdate;
    private Date edate;
    private String boss; // MemberDTO의 key 변수를 참조

    public ProjectDTO(final ProjectEntity entity) {
        this.projectkey = entity.getProjectkey();
        this.name = entity.getName();
        this.info = entity.getInfo();
        this.field = entity.getField();
        this.expected = entity.getExpected();
        this.status = entity.getStatus();
        this.sdate = entity.getSdate();
        this.edate = entity.getEdate();
        this.boss = entity.getBoss().getMemberkey(); // MemberDTO의 key 변수에서 값을 받아와서 할당
    }

    public static ProjectEntity toEntity(final ProjectDTO dto, final MemberEntity bossEntity) {
        return ProjectEntity.builder()
            .projectkey(dto.getProjectkey())
            .name(dto.getName())
            .info(dto.getInfo())
            .field(dto.getField())
            .expected(dto.getExpected())
            .status(dto.getStatus())
            .sdate(dto.getSdate())
            .edate(dto.getEdate())
            .boss(bossEntity)
            .build();
    }
}