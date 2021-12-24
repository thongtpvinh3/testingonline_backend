package backend.testingonline.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import backend.testingonline.service.StaffService;

@Controller
public class AppController {

	@Autowired
	private StaffService staffService;

	@GetMapping("/login")
	public String toLoginView() {
		return "login";
	}

	@PostMapping("/checklogin")
	public String checkLogin(@RequestParam String username, @RequestParam String password, HttpServletRequest req, HttpServletResponse resp) {
		
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

	@PostMapping("/logout")
	public String logout(HttpServletRequest req, HttpServletResponse resp) {
		HttpSession session = req.getSession();
		session.setAttribute("staff", null);
		return "redirect:/login";
	}

}
