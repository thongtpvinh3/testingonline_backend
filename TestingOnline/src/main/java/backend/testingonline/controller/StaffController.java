package backend.testingonline.controller;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import backend.testingonline.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import backend.testingonline.repository.EssayQuestionRepository;
import backend.testingonline.repository.MultipleChoiceQuestionRepository;
import backend.testingonline.repository.TempResultRepository;
import backend.testingonline.responseException.ResponseObject;
import backend.testingonline.service.CandidateService;
import backend.testingonline.service.LevelService;
import backend.testingonline.service.QuestionService;
import backend.testingonline.service.StaffService;
import backend.testingonline.service.SubjectService;
import backend.testingonline.service.TestService;
import backend.testingonline.service.UploadFileService;
import url.URL;

@CrossOrigin(origins = "*")
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
	
	@Autowired
	private UploadFileService uploadFileService;

	@PostMapping("/create")
	@PreAuthorize("hasRole('ROLE_admin')")
	public ResponseEntity<ResponseObject> createStaff(@RequestBody Staff newStaff) {
//		Authentication auth = SecurityContextHolder.getContext().get
		return staffService.createStaff(newStaff);
	}

	@GetMapping("/xyz")
	public String xyz(HttpServletRequest req) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String staff = String.valueOf(req.getUserPrincipal());
		System.out.println(auth);
		return staff;
	}

	
//----------------------CANDIDATE--------------------------------------------------------

	@GetMapping(URL.STAFF_GET_LIST_CANDIDATE)
	List<Candidate> getAllCandidate(HttpServletRequest req) {
		return candidateService.findAll();
	}

	@GetMapping("/candidate/phone={phone}")
	public List<Candidate> findCandidateByPhone(@PathVariable("phone") String phone) {
		return candidateService.findByEmail(phone);
	}
	
	@GetMapping("/candidate/avatar/files/{fileName:.+}")
	ResponseEntity<byte[]> readAvatar(@PathVariable String fileName) {
		try {
			byte[] bytes = uploadFileService.readImgContent(fileName);
			return ResponseEntity.ok()
					.contentType(MediaType.IMAGE_JPEG)
					.body(bytes);
		} catch (Exception e) {
			return ResponseEntity.noContent().build();
		}
	}
	
	@GetMapping("/candidate/done")
	public List<Candidate> getCandidateByDone() {
		return candidateService.findByIsDone();
	}


	// ADD CANDIDATE
	@PostMapping(value = URL.STAFF_ADD_CANDIDATE)
	ResponseEntity<ResponseObject> addCandidate(@RequestBody Candidate newCandidate) {
		return candidateService.save(newCandidate);
	}
	
	//Add candidate have avatar
	@PostMapping(value = "/addcandidate2", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
	Candidate addCandidate2(@RequestPart("candidate") String candidate, @RequestPart("file") MultipartFile file ) throws Exception {
		Candidate candidateJson = candidateService.getJson(candidate, file);
		return candidateJson;
	}
	
	@PutMapping("/candidate/update/{id}")
	ResponseEntity<ResponseObject> updateCandidate(@PathVariable("id") Integer id, @RequestBody Candidate candidate) {
		return candidateService.updateCandidate(id,candidate);
	}

	// DELETE A CANDIDATE
	@DeleteMapping(URL.STAFF_DELETE_CANDIDATE)
	ResponseEntity<ResponseObject> deleteCandidate(@PathVariable Integer id) {
		return candidateService.deleteWithId(id);
	}
	
	@GetMapping("/listcandidate/{idCandidate}")
	public Candidate getCandidateById(@PathVariable Integer idCandidate) {
		return candidateService.findById(idCandidate);
	}

// --------------------TEST-------------------------------------------------------------------------------------

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

	@PostMapping(URL.SATFF_GET_TEST_BY_SUBJECT)
	List<Test> getTestBySubject(@RequestBody Subject subject) {
		return testService.findBySubject(subject);
	}

	@GetMapping(URL.SATFF_GET_TEST_BY_ID)
	Test getTestbyId(@PathVariable Integer id) {
//		model.addAttribute("candidates", testService.getCandidateOfTest(id));
		return testService.findById(id);
	}

	@PostMapping(URL.STAFF_GET_TEST_BY_LELVEL)
	List<Test> getTestByLevel(@RequestBody Levels level) {
		return testService.findByLevel(level);
	}

	@GetMapping(URL.SATFF_GET_TEST_BY_CANDIDATEID)
	Set<Test> getTestByCandidateId(@PathVariable Integer id) {
		return testService.findByCandidateId(id);
	}
	
	@PostMapping(URL.STAFF_ADD_TEST)
	public ResponseEntity<ResponseObject> addTest(@RequestBody Test newTest) {
		return staffService.createTest(newTest);
	}

	@DeleteMapping(URL.STAFF_DELETE_TEST_BY_ID)
	public ResponseEntity<ResponseObject> deleteTestbyId(@PathVariable Integer id) {
		return testService.deleteById(id);
	}

	@PutMapping(URL.STAFF_ADD_QUESTION_TO_TEST)
	public ResponseEntity<ResponseObject> addQuestionToTest(@PathVariable Integer idTest,
			@PathVariable Integer idQuestion) {
		return testService.addQuestionTotest(idTest, idQuestion);
	}

	@PutMapping(URL.STAFF_UPDATE_TEST)
	public ResponseEntity<ResponseObject> updateTest(@PathVariable Integer id, @RequestBody Test test) {
		Test foundTest = testService.findById(id);
		if (foundTest == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ResponseObject("FALSE", "Khong tim thay id: " + id, ""));
		} else {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject("OK", "update thanh cong", testService.updateTest(id, test)));
		}
	}
	
	@PutMapping("/addquestiontotest/{idTest}")
	public Test addNewQuestionToTest(@PathVariable("idTest") Integer idTest,@RequestBody Question newQuestion) {
		return testService.addNewQuestion(idTest, newQuestion);
	}

	@PutMapping(URL.STAFF_ADD_TEST_FOR_CANDIDATE)
	public ResponseEntity<ResponseObject> addTestForCandidate(@PathVariable Integer idTest,
			@PathVariable Integer idCandidate) {
		return testService.addTestForCandidate(idTest, idCandidate);
	}
	
	@GetMapping("/candidate/bydate/outofdate")
	public List<Candidate> getOutOfDateCandidate() {
		return testService.getOutOfDateCandidate();
	}
	
	@GetMapping("/candidate/bydate/today")
	public List<Candidate> getTodayCandidate() {
		return testService.getTodayCandidate();
	}
	
	@GetMapping("/candidate/bydate/undue")
	public List<Candidate> getUndueCandidate() {
		return testService.getUndueCandidate();
	}
	
	@GetMapping("/candidate/outofdate")
	public Set<DateCandidate> getOutOfDateTest() {
		return testService.getOutOfDateTest();
	}
	
	@GetMapping("/candidate/today")
	public DateCandidate getTodayTest() {
		return testService.getTodayTest();
	}

	@GetMapping("/candidate/undue")
	public Set<DateCandidate> getUndueTest() {
		return testService.getUndueTest();
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
	public ResponseEntity<ResponseObject> reviewEssayQuestion(@PathVariable Integer idTest, @PathVariable Integer idCandidate,
			@PathVariable Double mark) {
		return testService.reviewEssayQuestion(idTest,idCandidate, mark);
	}

	@PutMapping(URL.STAFF_SET_MARK_FOR_CANDIDATE)
	public ResponseEntity<ResponseObject> setMark(@PathVariable Integer idCandidate) {
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

	@PostMapping(URL.STAFF_GET_QUESTION_BY_SUBJECT)
	public List<Question> getQuestionBySubject(@RequestBody Subject subject) {
		return questionService.findBySubject(subject);
	}

	@PostMapping(URL.STAFF_GET_QUESTION_BY_LEVEL)
	public List<Question> getQuestionByLevel(@RequestBody Levels level) {
		return questionService.findByLevel(level);
	}

	@GetMapping(URL.STAFF_GET_QUESTION_BY_TESTID)
	public Set<Question> getQuestionByTestId(@PathVariable Integer idTest) {
		return questionService.getByTestId(idTest);
	}

	@PostMapping(URL.STAFF_ADD_QUESTION)
	public ResponseEntity<ResponseObject> addQuestion(@RequestBody Question newQuestion) {
		return questionService.save(newQuestion);
	}

	@DeleteMapping(URL.STAFF_DELETE_QUESTION)
	public ResponseEntity<ResponseObject> deleteQuestionbyId(@PathVariable Integer id) {
		return questionService.deleteWithId(id);
	}

	@PutMapping(URL.STAFF_EDIT_QUESTION)
	public ResponseEntity<ResponseObject> editQuestion(@PathVariable Integer id, @RequestBody Question newQuestion) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseObject("OK", "update thanh cong", questionService.editQuestion(id, newQuestion)));
	}

	@PutMapping(URL.STAFF_ADD_ANS_TO_QUESTION)
	public ResponseEntity<ResponseObject> addAnswerToTest(@PathVariable Integer idAnswer,
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
	public ResponseEntity<ResponseObject> addMultipleChoiceAnswerForQuestion(@PathVariable Integer idQuestion,
			@RequestBody MultipleChoiceQuestion ans) {
		if (questionService.findById(idQuestion).getType().getId() == 1) {
			return questionService.addMultipleAnswer(idQuestion, ans);
		}
		return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
				.body(new ResponseObject("FAILED", "Khong dung loai cau hoi !", ""));
	}

	@DeleteMapping(URL.STAFF_DELETE_MC_ANSWER_FROM_QUESTION)
	public ResponseEntity<ResponseObject> deleteMultipleAnswerFromQuestion(
			@PathVariable Integer idAnswer) {
		return questionService.deleteMultipleAnswerFromQuestion(idAnswer);
	}

	@GetMapping(URL.STAFF_GET_ALL_MC_ANSWER)
	public List<MultipleChoiceQuestion> getAllMCAnswer() {
		return multipleChoiceQuestionRepository.findAll();
	}

	@PutMapping(URL.STAFF_UPDATE_MC_ANSWER_FOR_QUESTION)
	public ResponseEntity<ResponseObject> updateMCAnswerForQuestion(@PathVariable Integer idAnswer,
			@RequestBody MultipleChoiceQuestion answer) {
		return questionService.updateMCAnswer(idAnswer, answer);
	}

	@PostMapping(URL.STAFF_ADD_E_ASNSWER_FOR_QUESTION)
	public ResponseEntity<ResponseObject> addEssayAnswerForQuestion(@PathVariable Integer idQuestion,
			@RequestBody EssayQuestion ans) {
		if (questionService.findById(idQuestion).getType().getId() == 2) {
			return questionService.addEssayAnswer(idQuestion, ans);
		}
		return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
				.body(new ResponseObject("FAILED", "Khong dung loai cau hoi !", ""));
	}

	@PutMapping(URL.STAFF_UPDATE_ESSAY_ANSWER)
	public ResponseEntity<ResponseObject> updateEssayAnswerForQuestion(@PathVariable Integer idQuestion,
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
	ResponseEntity<ResponseObject> addNewLevel(@RequestBody Levels level) {
		return levelService.save(level);
	}

	@DeleteMapping(URL.STAFF_DELETE_LEVEL)
	ResponseEntity<ResponseObject> deleteLevel(@PathVariable Integer id) {
		return levelService.deleteById(id);
	} 

//-----------------------------Subject------------------------------------------

	@GetMapping(URL.STAFF_GET_ALL_SUBJECT)
	public List<Subject> getallSubject() {
		return subjectService.getAll();
	}

	@PostMapping(URL.STAFF_ADD_NEW_SUBJECT)
	ResponseEntity<ResponseObject> addNewSubject(@RequestBody Subject subject) {
		return subjectService.save(subject);
	}

	@DeleteMapping(URL.STAFF_DELETE_SUBJECT)
	ResponseEntity<ResponseObject> deleteSubjectById(@PathVariable Integer id) {
		return subjectService.deleteById(id);
	}
	
//-----------------------------OTHER-------------------------------------------

	@PostMapping("/addtype")
	public ResponseEntity<ResponseObject> addType(@RequestBody QuestionType questionType) {
		return staffService.creaeteType(questionType);
	}
	
	@PutMapping("/importdata")
	List<Question> importData(@RequestParam("file") MultipartFile file) throws Exception {
		System.out.println(file.getOriginalFilename());
		Path storageFolder = Paths.get("uploads");
		uploadFileService.upload(file);
		String originalName = file.getOriginalFilename();
//		String fileSuffix = FilenameUtils.getExtension(originalName);
//		String generatedFileName = UUID.randomUUID().toString().replace("-","");
//		generatedFileName = generatedFileName+"."+fileSuffix;
		Path filePath = storageFolder.resolve(Paths.get(originalName)).normalize().toAbsolutePath();
		return testService.addQuestionInXlsFile(filePath.toString());
	}
	
	@PutMapping("/fixisdone/{idCandidate}")
	ResponseEntity<ResponseObject> fixIsDone(@PathVariable("idCandidate") Integer idCandidate) {
		return candidateService.fixIsDone(idCandidate);
	}
	
	@DeleteMapping("/deleteresult/{idCandidate}")
	void deleteResult(@PathVariable("idCandidate") Integer idCandidate) {
		tempResultRepository.deleteResult(idCandidate);
	}

	@PostMapping("/search")
	public Set<Object> resultSearch(@RequestBody ForSearch conditional) {
		return staffService.search(conditional);
	}
}
