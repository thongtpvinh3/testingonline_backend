	package backend.testingonline.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.testingonline.model.TempResultOfCandidate;
import backend.testingonline.model.Test;
import backend.testingonline.responeexception.ResponeObject;
import backend.testingonline.service.TempResultService;
import backend.testingonline.service.TestService;
import url.URL;

@RestController
@RequestMapping(path = URL.CANDIDATE)
public class CandidateController {
	
	@Autowired
	private TestService testService;
	
	@Autowired
	private TempResultService tempResultService;

	@GetMapping(URL.CANDIDATE_ALL_TEST)
	public String toCandidateTestView(HttpServletRequest req, Model model) {
		HttpSession session = req.getSession();
		model.addAttribute(session.getAttribute("test"));
		return "testpage";
	}
	
	@GetMapping(URL.CANDIDATE_GET_TEST)
	public Test getTest(HttpServletRequest req) {
		HttpSession session = req.getSession();
		Test thisTest = (Test) session.getAttribute("test");
		return thisTest;
	}
	
	@PostMapping(URL.CANDIDATE_SUBMIT)
	public ResponseEntity<ResponeObject> setTestIsDone(@PathVariable Integer idTest, @RequestBody List<TempResultOfCandidate> tempResultOfCandidate) {
		testService.setTestIsDone(idTest);
		tempResultService.saveAll(tempResultOfCandidate);
		return ResponseEntity.status(HttpStatus.OK).body(
				new ResponeObject("OK", "Nop bai thanh cong", "")
				);
	}
	
	@PostMapping(URL.CANDIDATE_LOGOUT)
	public String logout(HttpServletRequest req) {
		HttpSession session = req.getSession();
		session.setAttribute("test", null);
		return "redirect:/testingonline";
	}
}
