package com.example.ToyProject.dto;

import java.util.Date;

import com.example.ToyProject.model.MemberEntity;
import com.example.ToyProject.model.ProjectEntity;
import com.example.ToyProject.model.ProjectHistoryEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectHistoryDTO {

    private String historykey;
    private String memberKey;
    private String projectKey;
    private int status;

    public static ProjectHistoryDTO from(ProjectHistoryEntity entity) {
        return ProjectHistoryDTO.builder()
                .memberKey(entity.getMember().getMemberkey())
                .projectKey(entity.getProject().getProjectkey())
                .status(entity.getStatus())
                .build();
    }

    public static ProjectHistoryEntity toEntity(ProjectHistoryDTO dto, MemberEntity member, ProjectEntity project) {
        return ProjectHistoryEntity.builder()
                .member(member)
                .project(project)
                .status(dto.getStatus())
                .build();
    }
}