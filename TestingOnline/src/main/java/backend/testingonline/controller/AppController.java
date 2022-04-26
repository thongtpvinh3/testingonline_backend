package backend.testingonline.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import backend.testingonline.jwt.AuthenticationRequest;
import backend.testingonline.jwt.AuthenticationResponse;
import backend.testingonline.jwt.JwtUtil;
import backend.testingonline.model.Candidate;
import backend.testingonline.service.CandidateService;
import backend.testingonline.service.impl.StaffSecurityService;
import url.URL;

@CrossOrigin(origins = "*")
@RestController
public class AppController {

	@Autowired
	private StaffSecurityService securityService;

	@Autowired
	private CandidateService candidateService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtUtil;

	@GetMapping(URL.CANDIDATE_JOIN_TEST)
	public Object joinTestWithCode(@RequestParam Integer code, HttpServletRequest req) {
		if (candidateService.findById(code) != null) {
			if (candidateService.findById(code).getIsDone() == 1) {
				return "làm xong rồi mà ???";
			}
			HttpSession session = req.getSession();
			Candidate candidate = candidateService.findById(code);
			session.setAttribute("candidate", candidate);
			return candidate;
		}
		return "redirect:/";
	}

	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
					);
			System.out.println("Trang");
		} catch (BadCredentialsException e) {
			throw new Exception("Invalid username or password",e);
		}
		final UserDetails userDetails = securityService.loadUserByUsername(authenticationRequest.getUsername());
		final String jwt = jwtUtil.generateToken(userDetails);
		
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}
}
