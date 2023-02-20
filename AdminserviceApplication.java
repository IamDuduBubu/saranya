
package com.tns.Adminservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AdminserviceApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(AdminserviceApplication.class, args);
	}
}


package com.tns.Adminservice;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Admin
{
	private Integer admin_id;
	private String admin_name;
	
	
	public Admin() 
	{
		super();
	}
	
	public Admin(Integer admin_id, string admin_name)
	{
		super();
		Admin_id = admin_id;
		Admin_name = admin_name;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getadmin_id() {
		return S_id;
	}
	public void setadmin_id(Integer s_id) {
		S_id = s_id;
	}
	public String getS_name() {
		return admin_name;
	}
	public void setS_name(String s_name)
	{
		admin_name = s_name;
	}
	@Override
	public String toString()
	{
		return "Admin[Admin id:"+S_id+" Admin name"+S_name+"]";
	}
}



package com.tns.Adminservice;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Admin_Service_Repository extends JpaRepository<Student, Integer> 
{

}


package com.tns.Adminservice;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
@Transactional
public class Admin_Service 
{
	@Autowired
	private Admin_Service_Repository repo;
	
	public List<Admin> listAll()
	{
		return repo.findAll();
	}
	
	public void save(student stud)
	{
		repo.save(Admin);
	}
	
	public Admin get(Integer s_id)
	{
		return repo.findById(s_id).get();
	}
	public void delete(Integer s_id)
	{
		repo.deleteById(s_id);
	}
	
}


package com.tns.Adminservice;

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
public class Admin_service_Controller
{
	@Autowired(required=true)
	private Admin_Service service;
	
	@GetMapping("/Adminservice")
	public java.util.List<Student> list()
	{
		return service.listAll();
	}
	
	@GetMapping("/Adminservice/{s_id}")
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
	@PostMapping("/Adminservice")
	public void add(@RequestBody Student stud)
	{
		service.save(Admin);
	}
	
	@PutMapping("/Adminservice/{s_id}")
	public ResponseEntity<?> update(@RequestBody Student stud, @PathVariable Integer S_id)
	{
		Student existstud=service.get(S_id);
		service.save(existstud);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("/Adminservice/{s_id}")
	public void delete(@PathVariable Integer s_id)
	{
		service.delete(s_id);
	}
}
