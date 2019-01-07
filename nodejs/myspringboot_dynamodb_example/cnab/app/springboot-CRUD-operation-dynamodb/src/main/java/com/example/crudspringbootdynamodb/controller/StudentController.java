package com.example.crudspringbootdynamodb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.crudspringbootdynamodb.dao.StudentDAO;
import com.example.crudspringbootdynamodb.entity.Student;


@RestController
public class StudentController {

	@Autowired
	private StudentDAO dao;

	@RequestMapping(path="/catalogService/Students", method=RequestMethod.GET)
	public @ResponseBody List<Student> findAll() {
		return dao.findAll();
	}
	
	@RequestMapping(path="/catalogService/Students/{id}", method=RequestMethod.GET)
	public @ResponseBody Student findById(@PathVariable String id) {
		return dao.findById(id);
	}

	@RequestMapping(path="/catalogService/Students/delete/{id}", method=RequestMethod.DELETE)
	public void deleteById(@PathVariable String id) {
		dao.delete(id);
	}

	@RequestMapping(path="/catalogService/Students/add", method=RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody Student addCatalogEntry(@RequestBody Student student) {
		Student newItem = dao.save(student);
		return newItem;
	}
	
	@RequestMapping(path="/catalogService/Students/update/{id}", method=RequestMethod.PUT, headers="Accept=application/json")
	public ResponseEntity<Student> updateCatalogEntry(@PathVariable String id, @RequestBody Student student) {
		Student studentRecord = dao.findById(id);
		
		if (studentRecord == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND) ;
		}
		
		studentRecord.setFirstName(student.getFirstName());
		studentRecord.setLastName(student.getLastName());
		studentRecord.setAge(student.getAge());
		dao.save(studentRecord);

		return new ResponseEntity<Student>(studentRecord, HttpStatus.OK);
	}
}
