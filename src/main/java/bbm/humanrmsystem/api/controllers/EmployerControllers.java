package bbm.humanrmsystem.api.controllers;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import bbm.humanrmsystem.business.abstracts.EmployerService;
import bbm.humanrmsystem.core.utilities.results.DataResult;
import bbm.humanrmsystem.core.utilities.results.Result;

import bbm.humanrmsystem.entities.concretes.Employer;

@RestController
@RequestMapping("/hrms/employer")
@CrossOrigin
public class EmployerControllers {
	
	private EmployerService employerService;
	
	@Autowired
	public EmployerControllers(EmployerService employerService) {
		super();
		this.employerService = employerService;
	}
	
	
	@GetMapping("/getAll")
	public DataResult<List<Employer>> employerGetAll(){
		return this.employerService.getAll();
	}
	
	@PostMapping("/register")
	public Result employerAdd(@RequestBody Employer employer) {
		
		String siteURL = "http://localhost:8080/hrms/employer";
		return this.employerService.register(employer, siteURL);
		
	}
	
	@PostMapping("/isActive")
	public Result updateIsActive(int id, boolean isAvtice) {
		return this.employerService.updateIsActive(id, isAvtice);
	}
	
	@GetMapping("/verify")
	public Result verifyAccount(@RequestParam("emailVerificationCode") String code) {
		
		return this.employerService.updateEmailVerification(code);

	}
	
	

}
