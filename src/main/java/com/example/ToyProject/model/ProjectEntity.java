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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Project")
public class ProjectEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String projectkey;

    private String name;
    private String info;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "boss", referencedColumnName = "key")
    private MemberEntity boss;

    private int expected;
    private int status;
    private int field;
    private Date sdate;
    private Date edate;
    
}