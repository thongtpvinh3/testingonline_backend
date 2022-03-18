package backend.testingonline.controller;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.testingonline.model.Candidate;
import backend.testingonline.model.TempResultOfCandidate;
import backend.testingonline.model.Test;
import backend.testingonline.responeexception.ResponeObject;
import backend.testingonline.service.CandidateService;
import backend.testingonline.service.TempResultService;
import backend.testingonline.service.TestService;
import backend.testingonline.service.impl.RedisCandidateDoTestCache;
import url.URL;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(path = URL.CANDIDATE)
public class CandidateController {

	@Autowired
	private TestService testService;

	@Autowired
	private TempResultService tempResultService;
	
	@Autowired
	private CandidateService candidateService;

	private final RedisCandidateDoTestCache valueCache;

	public CandidateController(final RedisCandidateDoTestCache valueCache) {
		this.valueCache = valueCache;
	}

	@GetMapping(URL.CANDIDATE_GET_A_TEST)
	public Test toCandidateTestView(@PathVariable String code,@PathVariable("idCandidate") Integer idCandidate,HttpServletRequest req) {
		HttpSession session = req.getSession();
		session.setAttribute("test", testService.getWithCode(code));
		return candidateService.joinTestByCode(code,idCandidate);
	}
	
	@GetMapping(URL.CANDIDATE_GET_ALL_TEST)
	public Set<Test> getAllCandidateTest(HttpServletRequest req) {
		HttpSession session = req.getSession();
		Candidate candidate = (Candidate) session.getAttribute("candidate");
		Set<Test> candidateTest = candidate.getTests();
		return candidateTest;
	}

	@GetMapping(URL.CANDIDATE_GET_TEST)
	public Test getTest(HttpServletRequest req) {
		HttpSession session = req.getSession();
		Test thisTest = (Test) session.getAttribute("test");
		return thisTest;
	}

	@PostMapping("/doingtest")
	public void cacheAnswer(HttpServletRequest req, @RequestBody TempResultOfCandidate tempAns) {
		HttpSession session = req.getSession();
		Test thisTest = (Test) session.getAttribute("test");
		Candidate candidate = (Candidate) session.getAttribute("candidate");
		int idCandidate = candidate.getId();

		valueCache.cache("testtime", LocalTime.now().toSecondOfDay()); 
		int timenow = (int) valueCache.getCachedValue("testtime");
		int testtime = thisTest.timeToSecond();
		int timestart = thisTest.getDateTest().toLocalTime().toSecondOfDay();
		if (timenow - timestart <= testtime) {
			if (tempAns.getType() == 0) {
				valueCache.saveMultiple(tempAns);
			} else {
				valueCache.saveEssay(tempAns);
			}
		} else {
			List<TempResultOfCandidate> finalRes = new ArrayList<>();
			Map<Integer, TempResultOfCandidate> finalRes1 = (Map<Integer, TempResultOfCandidate>) valueCache
					.getHashCacheAns("ans");
			for (Map.Entry<Integer, TempResultOfCandidate> e : finalRes1.entrySet()) {
				finalRes.add(e.getValue()); 
			}
			tempResultService.saveAll(finalRes);
			testService.setTestIsDone(thisTest.getId(),idCandidate);
			valueCache.delete("ans");
		}
	}

	@GetMapping("/getcacheans")
	public Map<Integer, TempResultOfCandidate> getTempAns() {
		return (Map<Integer, TempResultOfCandidate>) valueCache.getHashCacheAns("ans");
	}

	@PostMapping(URL.CANDIDATE_SUBMIT)
	public ResponseEntity<ResponeObject> setTestIsDone(@PathVariable Integer idTest, HttpServletRequest req) {
		HttpSession session = req.getSession();
		Candidate candidate = (Candidate) session.getAttribute("candidate");
		int idCandidate = candidate.getId();
		testService.setTestIsDone(idTest,idCandidate);
		List<TempResultOfCandidate> tempAnsResult = new ArrayList<>();
		Map<Integer, TempResultOfCandidate> finalRes1 = (Map<Integer, TempResultOfCandidate>) valueCache
				.getHashCacheAns("ans");
		for (Map.Entry<Integer, TempResultOfCandidate> e : finalRes1.entrySet()) {
			tempAnsResult.add(e.getValue());
		}
		tempResultService.saveAll(tempAnsResult);
		valueCache.delete("ans");
		session.setAttribute("test", null);
		return ResponseEntity.status(HttpStatus.OK).body(new ResponeObject("OK", "Nop bai thanh cong", ""));
	}

	@PutMapping(URL.CANDIDATE_LOGOUT)
	public String logout(HttpServletRequest req) {
		HttpSession session = req.getSession();
		session.setAttribute("candidate", null);
		session.setAttribute("test", null);
		return "redirect:/testingonline";
	}

}
