

package com.tns.Certificateservice;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class certificate
{
	private Integer S_id;
	private String S_name;
	
	
	public certificate() 
	{
		super();
	}
	
	public certificate(Integer s_id, String s_name)
	{
		super();
		S_id = s_id;
		S_name = s_name;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getS_id() {
		return S_id;
	}
	public void setS_id(Integer s_id) {
		S_id = s_id;
	}
	public String getS_name() {
		return S_name;
	}
	public void setS_name(String s_name)
	{
		S_name = s_name;
	}
	@Override
	public String toString()
	{
		return "certificate[Student id:"+S_id+" Student name"+S_name+];
	}

package com.tns.certificateservice;

import org.springframework.data.jpa.repository.JpaRepository;

public interface certificate_Service_Repository extends JpaRepository<Student, Integer> 
{

}


package com.tns.certificateservice;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
@Transactional
public class certificate_Service 
{
	@Autowired
	private certificate_Service_Repository repo;
	
	public List<Student> listAll()
	{
		return repo.findAll();
	}
	
	public void save(Student stud)
	{
		repo.save(stud);
	}
	
	public certificate get(Integer s_id)
	{
		return repo.findById(s_id).get();
	}
	public void delete(Integer s_id)
	{
		repo.deleteById(s_id);
	}
	
}


package com.tns.certificateservice;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class certificate_service_Controller
{
	@Autowired(required=true)
	private certificate_Service service;
	
	@GetMapping("/certificateservice")
	public java.util.List<Student> list()
	{
		return service.listAll();
	}
	
	@GetMapping("/certificateservice/{s_id}")
	public ResponseEntity<Student> get(@PathVariable Integer S_id)
	{
		try
		{
			Student stud=service.get(S_id);
			return new ResponseEntity<Student>(stud,HttpStatus.OK);
		}
		catch(NoResultException e)
		{
			return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
		}
	}
	@PostMapping("/certificateservice")
	public void add(@RequestBody Student stud)
	{
		service.save(stud);
	}
	
	@PutMapping("/certificateservice/{s_id}")
	public ResponseEntity<?> update(@RequestBody Student stud, @PathVariable Integer S_id)
	{
		certificate existstud=service.get(S_id);
		service.save(existstud);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("/certificateservice/{s_id}")
	public void delete(@PathVariable Integer s_id)
	{
		service.delete(s_id);
	}
}









