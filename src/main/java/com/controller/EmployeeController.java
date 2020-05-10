package com.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.bean.EmployeeBean;

@RestController
public class EmployeeController {

	@Autowired
	RestTemplate restTemplate;
	
	
	@RequestMapping("/emp")
	public void getAllEmployee() {
		
		
		final String url = "https://springp1boot.herokuapp.com/viewEmployee";
		
		String result = restTemplate.getForObject(url, String.class);
		System.out.println(result);		
		System.out.println("*****************");
		
		/*
		 * HttpHeaders headers = new HttpHeaders();
		 * headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		 */

		ResponseEntity<EmployeeBean[]> responseEntity  = restTemplate.getForEntity(url, EmployeeBean[].class);
		
		List<EmployeeBean> employees = Arrays.asList(responseEntity.getBody());
		
		
		for(EmployeeBean emp :employees) {
			System.out.println("");
			System.out.print(" "+emp.geteId());
			System.out.print(" "+emp.geteName());
			
		}
		
		
		
		
		
	}
	
	@RequestMapping("/getDataById")
	public void getDataById() {
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("eId", "54");
		EmployeeBean employeeBean = restTemplate.getForObject("https://springp1boot.herokuapp.com/viewEmployee/{eId}", EmployeeBean.class,params);
		
		System.out.println(employeeBean.geteId());
		System.out.println(employeeBean.geteName());
		
	}
	
	@RequestMapping(value = "/addemp")
	public void createEmployee() {
		
		EmployeeBean employeeBean = new EmployeeBean();
		employeeBean.seteId(101);
		employeeBean.seteName("rudresh");
		
		restTemplate.postForObject("https://springp1boot.herokuapp.com/addEmployee", employeeBean, EmployeeBean.class);
		//req,res
		System.out.println("data addedd..");
		
		
}

	@DeleteMapping(value = "/delet/{id}")
	public void deleteEmployee(@PathVariable (value = "id")int id) {
		System.out.println(id);
		restTemplate.delete("https://springp1boot.herokuapp.com/deletEmployee/+{id}");
		//req,res
		System.out.println("dataremov..");
	}
	
	
}
