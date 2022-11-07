package com.example.demo.repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.StudentModel;


@Repository
public interface StudentRepositary extends JpaRepository<StudentModel,Long>{

}
