	package backend.testingonline.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import backend.testingonline.service.TestService;

@Controller
@RequestMapping("/testpage")
public class CandidateController {
	
	@Autowired
	private TestService testService;

	@GetMapping("/test")
	public String toCandidateTestView(HttpServletRequest req, Model model) {
		HttpSession session = req.getSession();
		model.addAttribute(session.getAttribute("test"));
		return "testpage";
	}
}
