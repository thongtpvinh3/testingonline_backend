package backend.testingonline.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import backend.testingonline.model.Candidate;
import backend.testingonline.service.CandidateService;
import backend.testingonline.service.StaffService;
import url.URL;

@CrossOrigin(origins = "*")
@RestController
public class AppController { 

	@Autowired
	private StaffService staffService;
	
	@Autowired
	private CandidateService candidateService;
	
//	@Autowired
//	private AuthenticationManager authenticationManager;
//	
//	@Autowired
//	private JwtUtil jwtUtil;
	
	@GetMapping(URL.CANDIDATE_JOIN_TEST)
	public Object joinTestWithCode(@RequestParam Integer code,HttpServletRequest req) {
		if (candidateService.findById(code) != null) {
			if (candidateService.findById(code).getIsDone() == 1) {
				return "làm xong rồi mà ???";
			}
			HttpSession session = req.getSession();
			Candidate candidate = candidateService.findById(code);
			session.setAttribute("candidate", candidate);
			return candidate;
		}
		return "redirect:/testingonline";
	}
	
//	@PostMapping("/auth/login")
//	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
//		try {
//			authenticationManager.authenticate(
//					new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
//					);
//		} catch (BadCredentialsException e) {
//			throw new Exception("Invalid username or password",e);
//		}
//		final UserDetails userDetails = impl.loadUserByUsername(authenticationRequest.getUsername());
//		final String jwt = jwtUtil.generateToken(userDetails);
//		
//		return ResponseEntity.ok(new AuthenticationResponse(jwt));
//	}

	@PostMapping(value = URL.STAFF_CHECK_LOGIN)
	public String checkLogin(@RequestParam String username, @RequestParam String password, HttpServletRequest req) {
		
		boolean checkLogin = staffService.login(username, password);
		System.out.println(checkLogin);
		if (checkLogin) {
			HttpSession session = req.getSession();
			session.setAttribute("staff", staffService.findByUsernameAndPassword(username, password));
			return "redirect:/staff/home";
		} else {
			return "redirect:/login";
		}
	}
}
