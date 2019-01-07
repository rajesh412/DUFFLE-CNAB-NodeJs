package com.example.crudspringbootdynamodb.dao;

import java.util.List;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import com.example.crudspringbootdynamodb.entity.Student;

@EnableScan
public interface StudentDAO extends CrudRepository<Student, String>{
	
	Student findById(String id);
	
	List<Student> findAll();
	

}
