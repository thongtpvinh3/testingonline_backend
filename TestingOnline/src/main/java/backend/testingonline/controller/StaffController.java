package backend.testingonline.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import backend.testingonline.model.Candidate;
import backend.testingonline.repository.CandidateRepository;
import backend.testingonline.repository.StaffRepository;
import backend.testingonline.responeexception.ResponeObject;

@Controller
@RequestMapping(path = "/staff")
public class StaffController {

	@Autowired
	private StaffRepository staffRepository;

	@Autowired
	private CandidateRepository candidateRepository;

	@GetMapping("/home")
	public String toStaffView(HttpServletRequest req, Model model) {
		HttpSession session = req.getSession();
		model.addAttribute("staff", session.getAttribute("staff"));
		return "staffhome";
	}

	@GetMapping("/listcandidate")
	List<Candidate> getAllCandidate(HttpServletRequest req, Model model) {
		HttpSession session = req.getSession();
		session.setAttribute("listcandidate", candidateRepository.findAll());
		model.addAttribute("listcandidate", session.getAttribute("staff"));
		return candidateRepository.findAll();
	}
	
	@PostMapping("/addcandidate")
	void addCandidate() {
		
	}
//	@PostMapping("/addcandidate")
//	ResponseEntity<ResponeObject> addCandidate(@RequestBody Candidate newCandidate) {
//
//		List<Candidate> foundCandidateEmail = candidateRepository.findByEmail(newCandidate.getEmail());
//		List<Candidate> foundCandidatePhone = candidateRepository.findByPhone(newCandidate.getPhone());
//		if (foundCandidateEmail.size() > 0 || foundCandidatePhone.size() > 0) {
//			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
//					.body(new ResponeObject("failed", "Email bi trung!", ""));
//		}
//		return ResponseEntity.status(HttpStatus.OK)
//				.body(new ResponeObject("ok", "Add success", staffRepository.save(newCandidate)));
//	}
	
//	@GetMapping("/getAllStaff")
//	List<Staff> getAllStaff() {
//		return staffRepository.findAll();
//	}
//
//	@GetMapping("/{id}")
//	ResponseEntity<ResponeObject> findById(@PathVariable Integer id) {
//
//		Optional<Staff> foundStaff = staffRepository.findById(id);
//		return (foundStaff.isPresent())
//				? ResponseEntity.status(HttpStatus.OK).body(new ResponeObject("ok", "Thanh Cong", foundStaff))
//				: ResponseEntity.status(HttpStatus.NOT_FOUND)
//						.body(new ResponeObject("failed", "hong tim thay id: " + id, ""));
////		return staffRepository.findById(id).orElseThrow(()-> new RuntimeException("Cannot find staff with id = "+ id));
//	}
//

//
//	
//	@PutMapping("/{id}")
//	ResponseEntity<ResponeObject> updateCandidateTestTime(@RequestBody Candidate newCandidate,@PathVariable Integer id, @PathVariable Calendar calendar) {
//		calendar = Calendar.getInstance();
//		calendar.set(2021, 12, 30);
//		Candidate updateCandidate = candidateRepository.findById(id).map(candidate -> {
//			candidate.setTestTime(newCandidate.getTestTime());
//			return candidateRepository.save(candidate);
//		}).orElseGet(()-> {
//			newCandidate.setId(id);
//			return candidateRepository.save(newCandidate);
//		});
//		
//		return ResponseEntity.status(HttpStatus.OK).body(
//				new ResponeObject("OK","Cap nhat thanh cong",updateCandidate)
//				);
//	}
//	
//	@PutMapping("/{id}")
//	ResponseEntity<ResponeObject> updateCandidateTestTime(@RequestBody Candidate newCandidate,@PathVariable Integer id) {
//		
//		Candidate updateCandidate = candidateRepository.findById(id).map(candidate -> {
//			candidate.setName(newCandidate.getName());
//			return candidateRepository.save(candidate);
//		}).orElseGet(()-> {
//			newCandidate.setId(id);
//			return candidateRepository.save(newCandidate);
//		});
//		
//		return ResponseEntity.status(HttpStatus.OK).body(
//				new ResponeObject("OK","Cap nhat thanh cong",updateCandidate)
//				);
//	}
}
