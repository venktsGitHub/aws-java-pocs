package com.aws.dynamo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.aws.dynamo.entity.Student;

@Repository
public class StudentRepository {
		
	@Autowired
	private DynamoDBMapper dbMapper;
	
	
	public Student save(Student stu) {
		dbMapper.save(stu);
		return stu;
	}
	
	public Student getStudentById(String id) {
		
	return dbMapper.load(Student.class, id);
	
	}
	
	public boolean delete(String id) {
		Student stu =getStudentById(id);
		if(stu!=null)
		{
			dbMapper.delete(stu);
			return true;
		}
		
		return false;
	} 
	
	public Student update(Student student, String id) {
		
		dbMapper.save(student, new DynamoDBSaveExpression()
				.withExpectedEntry("studentId", new ExpectedAttributeValue(
						new AttributeValue().withS(id)
						)));
		return student;
	}
	
		
}
