package com.example.ToyProject.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.example.ToyProject.dto.ProjectHistoryDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "project_history")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String key;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_key")
    private MemberEntity member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_key")
    private ProjectEntity project;

    private int status;

    public static ProjectHistoryEntity from(ProjectHistoryDTO dto, MemberEntity member, ProjectEntity project) {
        return ProjectHistoryEntity.builder()
                .member(member)
                .project(project)
                .status(dto.getStatus())
                .build();
    }
}
