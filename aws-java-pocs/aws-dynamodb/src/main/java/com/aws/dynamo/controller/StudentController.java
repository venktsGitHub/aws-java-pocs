package com.aws.dynamo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.aws.dynamo.entity.Student;
import com.aws.dynamo.repository.StudentRepository;

@RestController
public class StudentController {

	@Autowired
	StudentRepository sRepository;
	
	@GetMapping("/students/{id}")
	public Student getStudents(@PathVariable("id") String id) {
		return sRepository.getStudentById(id);
	}
	
	@PostMapping("/students")
	public Student postStudent(@RequestBody Student stu) {
	
		System.out.println("here");
		
		return sRepository.save(stu);
	}
	
	double f=Math.pow(0, 0); 
	
}
