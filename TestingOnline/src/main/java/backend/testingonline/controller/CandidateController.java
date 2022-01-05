	package backend.testingonline.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.testingonline.responeexception.ResponeObject;
import backend.testingonline.service.TestService;
import url.URL;

@RestController
@RequestMapping(URL.CANDIDATE)
public class CandidateController {
	
	@Autowired
	private TestService testService;

	@GetMapping(URL.CANDIDATE_ALL_TEST)
	public String toCandidateTestView(HttpServletRequest req, Model model) {
		HttpSession session = req.getSession();
		model.addAttribute(session.getAttribute("test"));
		return "testpage";
	}
	
	@PutMapping(URL.CANDIDATE_SUBMIT)
	public ResponseEntity<ResponeObject> setTestIsDone(@PathVariable Integer id) {
		testService.setTestIsDone(id);
		return ResponseEntity.status(HttpStatus.OK).body(
				new ResponeObject("OK", "Set trang thai thanh cong", "")
				); 
	}
}
