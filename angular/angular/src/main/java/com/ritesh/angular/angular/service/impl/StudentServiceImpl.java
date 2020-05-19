package com.ritesh.angular.angular.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ritesh.angular.angular.model.Student;
import com.ritesh.angular.angular.repo.StudentRepository;
import com.ritesh.angular.angular.service.IStudentService;

@Service
public class StudentServiceImpl implements IStudentService {
	@Autowired
	private StudentRepository repo;

	@Override
	public Integer saveStudent(Student s) {

		return repo.save(s).getStdId();
	}

	@Override
	public List<Student> getAllStudents() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public Optional<Student> getOneStudent(Integer id) {
		// TODO Auto-generated method stub
		return repo.findById(id);
	}

	@Override
	public boolean isExist(Integer id) {

		return repo.existsById(id);
	}

	@Override
	public void deleteStudent(Integer id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);

	}

}
