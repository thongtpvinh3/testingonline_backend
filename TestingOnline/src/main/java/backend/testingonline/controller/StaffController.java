package backend.testingonline.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import backend.testingonline.model.Candidate;
import backend.testingonline.model.Question;
import backend.testingonline.model.Test;
import backend.testingonline.responeexception.ResponeObject;
import backend.testingonline.service.CandidateService;
import backend.testingonline.service.QuestionService;
import backend.testingonline.service.StaffService;
import backend.testingonline.service.TestService;
import url.URL;

@RestController
@RequestMapping(path = URL.STAFF)
public class StaffController {

	@Autowired
	private StaffService staffService;

	@Autowired
	private CandidateService candidateService;

	@Autowired
	private TestService testService;

	@Autowired
	private QuestionService questionService;

	@GetMapping(URL.STAFF_TO_STAFFVIEW)
	public String toStaffView(HttpServletRequest req, Model model) {
		HttpSession session = req.getSession();
		model.addAttribute("staff", session.getAttribute("staff"));
		return "staffhome";
	}

//----------------------CANDIDATE--------------------------------------------------------

	@GetMapping(URL.STAFF_GET_LIST_CANDIDATE)
	List<Candidate> getAllCandidate(HttpServletRequest req, Model model) {
		HttpSession session = req.getSession();
		session.setAttribute("listcandidate", candidateService.findAll());
		model.addAttribute("listcandidate", session.getAttribute("staff"));
		return candidateService.findAll();
	}

	// ADD CANDIDATE
	@PostMapping(URL.STAFF_ADD_CANDIDATE)
	ResponseEntity<ResponeObject> addCandidate(@RequestBody Candidate newCandidate) {
		return candidateService.save(newCandidate);
	}

	// DELETE A CANDIDATE
	@DeleteMapping(URL.STAFF_DELETE_CANDIDATE)
	ResponseEntity<ResponeObject> deleteCandidate(@PathVariable Integer id) {
		return candidateService.deleteWithId(id);
	}
// --------------------TEST-------------------------------------------------------------------------------------
	
	// ADD TEST
	@GetMapping(URL.STAFF_TO_TESTVIEW)
	public String toTestView() {
		return "testview";
	}

	@GetMapping(URL.STAFF_GETALL_TEST)
	public List<Test> getAllTest(HttpServletRequest req) {
		HttpSession session = req.getSession();
		session.setAttribute("listtest", testService.getAllTest());
		return testService.getAllTest();
	}

	@GetMapping(URL.STAFF_GET_TEST_BY_LELVEL)
	List<Test> getTestByLevel(@PathVariable Integer level) {
		return testService.findByLevel(level);
	}

	@PostMapping(URL.STAFF_ADD_TEST)
	public ResponseEntity<ResponeObject> addTest(@RequestBody Test newTest) {
		return staffService.createTest(newTest);
	}

	@DeleteMapping(URL.STAFF_DELETE_TEST_BY_ID)
	public ResponseEntity<ResponeObject> deleteTestbyId(@PathVariable Integer id) {
		return testService.deleteById(id);
	}

	@PutMapping("/addquestiontotest/{idTest}/{idQuestion}")
	public ResponseEntity<ResponeObject> addQuestionToTest(@PathVariable Integer idTest, @PathVariable Integer idQuestion) {
		return testService.addQuestionTotest(idTest, idQuestion);
	}
	
	@PutMapping("/updatetest/{id}")
	public ResponseEntity<ResponeObject> updateTest(@PathVariable Integer id,@RequestBody Test test) {
		Test foundTest = testService.findById(id);
		if (foundTest == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
					new ResponeObject("FALSE", "Khong tim thay id: "+id, "")
					);
		}
		return ResponseEntity.status(HttpStatus.OK).body(
				new ResponeObject("OK", "update thanh cong", testService.updateTest(id,test))
				);
	}
	

//	@GetMapping("/getAllStaff")
//	List<Staff> getAllStaff() {
//		return staffRepository.findAll();
//	}

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

//	@PutMapping("/updatecandidate/{id}")
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
}
