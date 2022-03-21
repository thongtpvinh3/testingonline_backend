package backend.testingonline.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import backend.testingonline.model.Candidate;
import backend.testingonline.model.EssayQuestion;
import backend.testingonline.model.Levels;
import backend.testingonline.model.MultipleChoiceQuestion;
import backend.testingonline.model.Question;
import backend.testingonline.model.Subject;
import backend.testingonline.model.TempResultOfCandidate;
import backend.testingonline.model.Test;
import backend.testingonline.repository.EssayQuestionRepository;
import backend.testingonline.repository.MultipleChoiceQuestionRepository;
import backend.testingonline.repository.TempResultRepository;
import backend.testingonline.responeexception.ResponeObject;
import backend.testingonline.service.CandidateService;
import backend.testingonline.service.LevelService;
import backend.testingonline.service.QuestionService;
import backend.testingonline.service.StaffService;
import backend.testingonline.service.SubjectService;
import backend.testingonline.service.TestService;
import url.URL;

@CrossOrigin(origins = "http://localhost:3000")
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

	@Autowired
	private LevelService levelService;

	@Autowired
	private SubjectService subjectService;

	@Autowired
	private MultipleChoiceQuestionRepository multipleChoiceQuestionRepository;

	@Autowired
	private EssayQuestionRepository essayQuestionRepository;

	@Autowired
	private TempResultRepository tempResultRepository;

	@GetMapping(URL.STAFF_TO_STAFFVIEW)
	public String toStaffView(HttpServletRequest req, Model model) {
		HttpSession session = req.getSession();
		model.addAttribute("staff", session.getAttribute("staff"));
		return "staffhome";
	}

	@PostMapping(URL.STAFF_LOGOUT)
	public String logout(HttpServletRequest req, HttpServletResponse resp) {
		HttpSession session = req.getSession();
		session.setAttribute("staff", null);
		return "redirect:/login";
	}
	
	// Get test have candidate id

//----------------------CANDIDATE--------------------------------------------------------

	@GetMapping(URL.STAFF_GET_LIST_CANDIDATE)
	List<Candidate> getAllCandidate(HttpServletRequest req, Model model) {
		HttpSession session = req.getSession();
		session.setAttribute("listcandidate", candidateService.findAll());
		model.addAttribute("listcandidate", session.getAttribute("staff"));
		return candidateService.findAll();
	}

	// ADD CANDIDATE
	@PostMapping(value = URL.STAFF_ADD_CANDIDATE)
	ResponseEntity<ResponeObject> addCandidate(@RequestBody Candidate newCandidate) {
		return candidateService.save(newCandidate);
	}

	// DELETE A CANDIDATE
	@DeleteMapping(URL.STAFF_DELETE_CANDIDATE)
	ResponseEntity<ResponeObject> deleteCandidate(@PathVariable Integer id) {
		return candidateService.deleteWithId(id);
	}
	
	@GetMapping("/listcandidate/{idCandidate}")
	public Candidate getCandidateById(@PathVariable Integer idCandidate) {
		return candidateService.findById(idCandidate);
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

	@GetMapping(URL.SATFF_GET_TEST_BY_NAME)
	List<Test> getTestByName(@PathVariable String name) {
		return testService.findByName(name);
	}

	@GetMapping(URL.SATFF_GET_TEST_BY_CODE)
	Test getTestByCode(@PathVariable String code) {
		return testService.getWithCode(code);
	}

	@GetMapping(URL.SATFF_GET_TEST_BY_SUBJECT)
	List<Test> getTestBySubject(@PathVariable Integer subject) {
		return testService.findBySubject(subject);
	}

	@GetMapping(URL.SATFF_GET_TEST_BY_ID)
	Test getTestbyId(@PathVariable Integer id, Model model) {
		model.addAttribute("candidates", testService.getCandidateOfTest(id));
		return testService.findById(id);
	}

	@GetMapping(URL.STAFF_GET_TEST_BY_LELVEL)
	List<Test> getTestByLevel(@PathVariable Integer level) {
		return testService.findByLevel(level);
	}

	@GetMapping(URL.SATFF_GET_TEST_BY_CANDIDATEID)
	Set<Test> getTestByCandidateId(@PathVariable Integer id) {
		return testService.findByCandidateId(id);
	}

	@PostMapping(URL.STAFF_ADD_TEST)
	public ResponseEntity<ResponeObject> addTest(@RequestBody Test newTest) {
		return staffService.createTest(newTest);
	}

	@DeleteMapping(URL.STAFF_DELETE_TEST_BY_ID)
	public ResponseEntity<ResponeObject> deleteTestbyId(@PathVariable Integer id) {
		return testService.deleteById(id);
	}

	@PutMapping(URL.STAFF_ADD_QUESTION_TO_TEST)
	public ResponseEntity<ResponeObject> addQuestionToTest(@PathVariable Integer idTest,
			@PathVariable Integer idQuestion) {
		return testService.addQuestionTotest(idTest, idQuestion);
	}

	@PutMapping(URL.STAFF_UPDATE_TEST)
	public ResponseEntity<ResponeObject> updateTest(@PathVariable Integer id, @RequestBody Test test) {
		Test foundTest = testService.findById(id);
		if (foundTest == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ResponeObject("FALSE", "Khong tim thay id: " + id, ""));
		} else {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponeObject("OK", "update thanh cong", testService.updateTest(id, test)));
		}
	}

	@PutMapping(URL.STAFF_ADD_TEST_FOR_CANDIDATE)
	public ResponseEntity<ResponeObject> addTestForCandidate(@PathVariable Integer idTest,
			@PathVariable Integer idCandidate) {
		return testService.addTestForCandidate(idTest, idCandidate);
	}

//	@PostMapping(value = "/settesttime/{idTest}", consumes = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<ResponeObject> setTestTime(@PathVariable Integer idTest, @RequestBody LocalTime time) {
//		return testService.setTestTime(idTest, time);
//	}
//
//	@PostMapping(value = "/setdatetest/{idTest}", consumes = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<ResponeObject> setDateTest(@PathVariable Integer idTest,
//			@RequestBody LocalDateTime dateTest) {
//		return testService.setDateTest(idTest, dateTest);
//	}

	@PutMapping(URL.STAFF_REVIEW_MC_QUESTION)
	public Double reviewMCQuestion(@PathVariable Integer idTest, @PathVariable Integer idCandidate) {
		return testService.reviewMCQuestion(idTest, idCandidate);
	}

	@PutMapping(URL.STAFF_REVIEW_ESSAY_QUESTION)
	public ResponseEntity<ResponeObject> reviewEssayQuestion(@PathVariable Integer idTest, @PathVariable Integer idCandidate,
			@PathVariable Double mark) {
		return testService.reviewEssayQuestion(idTest,idCandidate, mark);
	}

	@PutMapping(URL.STAFF_SET_MARK_FOR_CANDIDATE)
	public ResponseEntity<ResponeObject> setMark(@PathVariable Integer idCandidate) {
		return candidateService.setMark(idCandidate);
	}

	@GetMapping(URL.STAFF_GET_ALL_RESULT)
	public List<TempResultOfCandidate> getAllRes() {
		return tempResultRepository.findAll();
	}

//-------------------------QUESTION-----------------------------------------------------------------------------

	@GetMapping(URL.STAFF_GETALL_QUESTION)
	public List<Question> getAllQuestion(HttpServletRequest req, Model model) {
		HttpSession session = req.getSession();
		session.setAttribute("lisquestion", questionService.findAll());
		model.addAttribute("listquestion", session.getAttribute("listquestion"));
		return questionService.findAll();
	}

	@GetMapping(URL.STAFF_GET_QUESTION_BY_ID)
	public Question getQuestionById(@PathVariable Integer id) {
		return questionService.findById(id);
	}

	@GetMapping(URL.STAFF_GET_QUESTION_BY_TYPE)
	public List<Question> getQuestionByType(@PathVariable Integer type) {
		return questionService.findByType(type);
	}

	@GetMapping(URL.STAFF_GET_QUESTION_BY_SUBJECT)
	public List<Question> getQuestionBySubject(@PathVariable Integer subject) {
		return questionService.findBySubject(subject);
	}

	@GetMapping(URL.STAFF_GET_QUESTION_BY_LEVEL)
	public List<Question> getQuestionByLevel(@PathVariable Integer level) {
		return questionService.findByLevel(level);
	}

	@GetMapping(URL.STAFF_GET_QUESTION_BY_TESTID)
	public Set<Question> getQuestionByTestId(@PathVariable Integer idTest) {
		return questionService.getByTestId(idTest);
	}

	@PostMapping(URL.STAFF_ADD_QUESTION)
	public ResponseEntity<ResponeObject> addQuestion(@RequestBody Question newQuestion) {
		return questionService.save(newQuestion);
	}

	@DeleteMapping(URL.STAFF_DELETE_QUESTION)
	public ResponseEntity<ResponeObject> deleteQuestionbyId(@PathVariable Integer id) {
		return questionService.deleteById(id);
	}

	@PutMapping(URL.STAFF_EDIT_QUESTION)
	public ResponseEntity<ResponeObject> editQuestion(@PathVariable Integer id, @RequestBody Question newQuestion) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponeObject("OK", "update thanh cong", questionService.editQuestion(id, newQuestion)));
	}

	@PutMapping(URL.STAFF_ADD_ANS_TO_QUESTION)
	public ResponseEntity<ResponeObject> addAnswerToTest(@PathVariable Integer idAnswer,
			@PathVariable Integer idQuestion) {
		return questionService.addAnswerToQuestion(idAnswer, idQuestion);
	}

//	@GetMapping("/type/{id}")
//	String testType(@PathVariable Integer id) {
//		if (questionService.getType(id) == 0) {
//			return "Multiple Choice Question!";
//		}
//		return "Essay Question";
//	}

//------------------------------ANSWER------------------------------------------

	@PostMapping(URL.STAFF_ADD_MCQ_FOR_QUESTION)
	public ResponseEntity<ResponeObject> addMultipleChoiceAnswerForQuestion(@PathVariable Integer idQuestion,
			@RequestBody MultipleChoiceQuestion ans) {
		if (questionService.findById(idQuestion).getType() == 0) {
			return questionService.addMultipleAnswer(idQuestion, ans);
		}
		return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
				.body(new ResponeObject("FAILED", "Khong dung loai cau hoi !", ""));
	}

	@DeleteMapping(URL.STAFF_DELETE_MC_ANSWER_FROM_QUESTION)
	public ResponseEntity<ResponeObject> deleteMultipleAnswerFromQuestion(
			@PathVariable Integer idAnswer) {
		return questionService.deleteMultipleAnswerFromQuestion(idAnswer);
	}

	@GetMapping(URL.STAFF_GET_ALL_MC_ANSWER)
	public List<MultipleChoiceQuestion> getAllMCAnswer() {
		return multipleChoiceQuestionRepository.findAll();
	}

	@PutMapping(URL.STAFF_UPDATE_MC_ANSWER_FOR_QUESTION)
	public ResponseEntity<ResponeObject> updateMCAnswerForQuestion(@PathVariable Integer idAnswer,
			@RequestBody MultipleChoiceQuestion answer) {
		return questionService.updateMCAnswer(idAnswer, answer);
	}

	@PostMapping(URL.STAFF_ADD_E_ASNSWER_FOR_QUESTION)
	public ResponseEntity<ResponeObject> addEssayAnswerForQuestion(@PathVariable Integer idQuestion,
			@RequestBody EssayQuestion ans) {
		if (questionService.findById(idQuestion).getType() == 1) {
			return questionService.addEssayAnswer(idQuestion, ans);
		}
		return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
				.body(new ResponeObject("FAILED", "Khong dung loai cau hoi !", ""));
	}

	@PutMapping(URL.STAFF_UPDATE_ESSAY_ANSWER)
	public ResponseEntity<ResponeObject> updateEssayAnswerForQuestion(@PathVariable Integer idQuestion,
			@RequestBody EssayQuestion answer) {
		return questionService.updateEssayAnswer(idQuestion, answer);
	}

	@GetMapping(URL.STAFF_GET_ALL_ESSAY_ANSWER)
	public List<EssayQuestion> getAllEssayAnswer() {
		return essayQuestionRepository.findAll();
	}
	
	@PutMapping("/removequestion/{idQuestion}/{idTest}")
	public void removeQuestionFromTest(@PathVariable("idQuestion") Integer idQuestion,@PathVariable("idTest") Integer idTest) {
		questionService.removeQuestionFromTest(idQuestion,idTest);
	}

//------------------------------Level-------------------------------------------

	@GetMapping(URL.STAFF_GET_ALL_LEVEL)
	public List<Levels> getAllLevels() {
		return levelService.getAll();
	}

	@PostMapping(URL.STAFF_ADD_NEW_LEVEL)
	ResponseEntity<ResponeObject> addNewLevel(@RequestBody Levels level) {
		return levelService.save(level);
	}

	@DeleteMapping(URL.STAFF_DELETE_LEVEL)
	ResponseEntity<ResponeObject> deleteLevel(@PathVariable Integer id) {
		return levelService.deleteById(id);
	} 

//-----------------------------Subject------------------------------------------

	@GetMapping(URL.STAFF_GET_ALL_SUBJECT)
	public List<Subject> getallSubject() {
		return subjectService.getAll();
	}

	@PostMapping(URL.STAFF_ADD_NEW_SUBJECT)
	ResponseEntity<ResponeObject> addNewSubject(@RequestBody Subject subject) {
		return subjectService.save(subject);
	}

	@DeleteMapping(URL.STAFF_DELETE_SUBJECT)
	ResponseEntity<ResponeObject> deleteSubjectById(@PathVariable Integer id) {
		return subjectService.deleteById(id);
	}
}
