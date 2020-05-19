package com.ritesh.angular.angular.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ritesh.angular.angular.model.Message;
import com.ritesh.angular.angular.model.Student;
import com.ritesh.angular.angular.service.impl.StudentServiceImpl;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/rest/student")
public class StudentRestController {
	@Autowired
	private StudentServiceImpl service;

	@PostMapping("/save")
	public ResponseEntity<Message> saveStudent(@RequestBody Student student) {
		ResponseEntity<Message> resp = null;
		try {
			Integer id = service.saveStudent(student);
			resp = new ResponseEntity<Message>(new Message(("SUCCESS"), id + "Saved"), HttpStatus.CREATED);
		} catch (Exception e) {
			resp = new ResponseEntity<Message>(new Message(("FAIL"), "Unable to Save"), HttpStatus.OK);
		}

		return resp;

	}

	@GetMapping("/all")
	public ResponseEntity<?> getAllStudent() {
		ResponseEntity<?> resp = null;
		try {
			List<Student> list = service.getAllStudents();
			if (list != null && !list.isEmpty()) {
				resp = new ResponseEntity<List<Student>>(list, HttpStatus.OK);
			}
			else {
				resp = new ResponseEntity<String>("No Data Found", HttpStatus.OK);
				System.out.println("HttpStatus.OK "+HttpStatus.OK);
				System.out.println(resp);
			}

		} catch (Exception e) {
			resp = new ResponseEntity<String>("Unable to Feach data", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return resp;
	}

	@GetMapping("/one/{id}")
	public ResponseEntity<?> getOneStudent(@PathVariable Integer id) {
		ResponseEntity<?> resp = null;
		try {
			Optional<Student> opt = service.getOneStudent(id);
			if (opt.isPresent()) {
				resp = new ResponseEntity<Student>(opt.get(), HttpStatus.OK);
				}
			else {
				resp = new ResponseEntity<Message>(new Message(("unableToFetch"), id + "ofthisid"), HttpStatus.NOT_FOUND);
				//(new Message(("SUCCESS"), id + "Saved"), HttpStatus.CREATED);
			}
		} catch (Exception e) {
			resp = new ResponseEntity<String>("Unable to Fetch Data", HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}

		return resp;

	}

	@DeleteMapping("remove/{id}")
	public ResponseEntity<Message> deleteStudent(@PathVariable Integer id) {
		ResponseEntity<Message> resp = null;
		try {
			boolean exist = service.isExist(id);
			if (exist) {
				service.deleteStudent(id);
				resp = new ResponseEntity<Message>(new Message(("SUCCSESS"), id + "-removed"), HttpStatus.OK);
			} else {
				resp = new ResponseEntity<Message>(new Message("FAIL", id + "-Not Exist"), HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			resp = new ResponseEntity<Message>(new Message("FAIL", "Unable to Delete"),
					HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return resp;

	}

	@PutMapping("/update")
	public ResponseEntity<String> updateStudent(@RequestBody Student student) {
		ResponseEntity<String> resp = null;
		try {
			boolean exist = service.isExist(student.getStdId());
			if (exist) {
				service.saveStudent(student);
				resp = new ResponseEntity<String>(student.getStdId() + "-Updated", HttpStatus.OK);
			} else {
				resp = new ResponseEntity<String>(student.getStdId() + "-Not Exist", HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			resp = new ResponseEntity<String>("Unable to Update", HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return resp;

	}
}