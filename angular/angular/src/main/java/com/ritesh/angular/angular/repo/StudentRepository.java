package com.ritesh.angular.angular.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ritesh.angular.angular.model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer>{

}
