package com.gmail.example.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gmail.example.model.Student;

@RestController
public class StudentController {
     @Autowired
	JavaMailSender javaMailSender;
	
	@PostMapping("/sendMail")
	public ResponseEntity<Object> sendEmail(@RequestBody Student student){
		
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setFrom("#########");//input the sender email Id or read it from properties file
		sm.setTo(student.getEmail());
		sm.setSubject("Welcome to Java SpringBoot Application");
		sm.setText("Hello "+student.getName() +"\n\n Welcome to the Java Springboot Mail Example.");
		javaMailSender.send(sm);
		
		return generateResponse("Email Sent to the mail "+student.getEmail(), HttpStatus.OK, student);
	}
	
	public ResponseEntity<Object> generateResponse(String msg, HttpStatus st , Object response){
		Map<String, Object> mp = new HashMap<String, Object>();
		
		mp.put("message", msg);
		mp.put("status", st.value());
		mp.put("data",response);
		
		return  new ResponseEntity<Object>(mp,st);
	}
}
