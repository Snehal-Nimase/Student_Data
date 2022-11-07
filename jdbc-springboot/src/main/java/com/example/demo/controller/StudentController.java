package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.StudentModel;
import com.example.demo.repositary.StudentRepositary;

@RestController
@RequestMapping("/")
public class StudentController {
	
	@Autowired //creates object of class
	private StudentRepositary studentRepositary;
	
   @GetMapping("/getEndpoint")
   public String Hello() {
	   return ("Hello ");
   }
   @GetMapping("/getStudents")
	public List<StudentModel> getAllEmployees(){
		return studentRepositary.findAll();
	}
   
   //Second way of defining EndPoints 
   @GetMapping("/getStudentData/{id}")
   public StudentModel getStudentById(@PathVariable Long id) throws Exception {
	   return studentRepositary.findById(id).orElseThrow(() -> new Exception("Student data for this ID  is not present " + id));
   }
   
   @PostMapping("/addingStudentData")
   public StudentModel addStudentData(@RequestBody StudentModel student) {
	   return studentRepositary.save(student);
   }
   
   //Updtae Api
   @PutMapping("/updateStudentData/{id}")
   public StudentModel updtaeSTudentData(@PathVariable Long id,@RequestBody StudentModel studentData) throws Exception {
	   StudentModel studentDataFromDB = studentRepositary.findById(id).orElseThrow(() -> new Exception("student data with this is id is not Present : " + id));
	   studentDataFromDB.setFname(studentData.getFname());
	   studentDataFromDB.setLname(studentData.getLname());
	   studentDataFromDB.setStandard(studentData.getStandard());
	   studentDataFromDB.setEmailid(studentData.getEmailid());
	   return studentRepositary.save(studentDataFromDB);
   }
   
   //Delete Api
   @DeleteMapping("/deleteStudentData/{id}")
   public String deleteStudentDataById(@PathVariable Long id) throws Exception {
	   String result = "Data will get Delete";
	   StudentModel studentDataFromDB = studentRepositary.findById(id).orElseThrow(() -> new Exception("student data with this is id is not Present : " + id));
	
	   studentRepositary.delete(studentDataFromDB);
	   result = "Student Data Deleted!!!!";
	   return result;
	   
	   
	
   }

   
   
}
