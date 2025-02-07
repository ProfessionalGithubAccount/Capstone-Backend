package com.application.model;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Professor 
{
	@Id
	private Integer professorId;
	@ElementCollection
	@CollectionTable(name = "user_courses", joinColumns = @JoinColumn(name = "user_id"))
	@Column(name = "course_id")
	private List<Integer> courseIds;
	private String email;
	private String professorname;
	private String degreecompleted;
	private String department;
	private String experience;
	private String mobile;
	private String gender;
	private String password;
	
}