package com.example.crudspringbootdynamodb;

import java.math.BigDecimal;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ResourceInUseException;
import com.example.crudspringbootdynamodb.dao.StudentDAO;
import com.example.crudspringbootdynamodb.entity.Student;

@SpringBootApplication
public class CrudSpringbootDynamodbApplication {

	private DynamoDBMapper dynamoDBMapper;

    @Autowired
    private AmazonDynamoDB amazonDynamoDB;
    
	@Autowired
	StudentDAO dao;
	
	@PostConstruct
	public void init() {
		
		createStudentTable();
		Student student1 = new Student("Rajesh", "Bandaru", 26);
		dao.save(student1);
		
		Student student2 = new Student("shru", "Mish", 25);
		dao.save(student2);
		
		Student student3 = new Student("Mahesh", "Bandaru", 29);
		dao.save(student3);;	
	}

	public void createStudentTable(){
        try {
            dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);

            CreateTableRequest tableRequest = dynamoDBMapper.generateCreateTableRequest(Student.class);

            tableRequest.setProvisionedThroughput(new ProvisionedThroughput(1L, 1L));

            amazonDynamoDB.createTable(tableRequest);
        } catch (ResourceInUseException e) {
            
        }
	}

	public static void main(String[] args) {
		SpringApplication.run(CrudSpringbootDynamodbApplication.class, args);
	}
}
