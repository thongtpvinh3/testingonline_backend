package backend.testingonline.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import backend.testingonline.service.CandidateService;
import backend.testingonline.service.StaffService;
import backend.testingonline.service.TestService;
import url.URL;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class AppController { 

	@Autowired
	private StaffService staffService;
	
	@Autowired
	private CandidateService candidateService;
	
	public AppController(CandidateService candidateService) {
		this.candidateService = candidateService;
	}
	
	@Autowired
	private TestService testService;
	
	@GetMapping(URL.ALL_HOMEPAGE)
	public String toWebPage() {
		return "homepage";
	}
	
	@PostMapping(URL.CANDIDATE_JOIN_TEST)
	public String joinTestWithCode(@RequestParam String code,HttpServletRequest req) {
		if (candidateService.joinTestByCode(code)) {
			HttpSession session = req.getSession();
			session.setAttribute("test", testService.getWithCode(code));
			
			return "redirect:/testpage";
		}
		return "redirect:/testingonline";
	}

	@GetMapping(URL.STAFF_TO_LOGIN)
	public String toLoginView() {
		return "login";
	}

	@PostMapping(value = URL.STAFF_CHECK_LOGIN)
	public String checkLogin(@RequestParam String username, @RequestParam String password, HttpServletRequest req) {
		
		boolean checkLogin = staffService.login(username, password);
		System.out.println(checkLogin);
		if (checkLogin) {
			HttpSession session = req.getSession();
			session.setAttribute("staff", staffService.findByUsernameAndPassword(username, password));
//			model.addAttribute("staff", staffService.findByUsernameAndPassword(username, password));
			return "redirect:/staff/home";
		} else {
//			model.addAttribute("ERROR", "Sai ten tai khoan hoac mat khau!");
			return "redirect:/login";
		}
	}
}
