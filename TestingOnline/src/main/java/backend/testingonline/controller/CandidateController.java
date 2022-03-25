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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import backend.testingonline.model.Candidate;
import backend.testingonline.model.TempResultOfCandidate;
import backend.testingonline.model.Test;
import backend.testingonline.responeexception.ResponeObject;
import backend.testingonline.service.CandidateService;
import backend.testingonline.service.QuestionService;
import backend.testingonline.service.TempResultService;
import backend.testingonline.service.impl.RedisCandidateDoTestCache;
import url.URL;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(path = URL.CANDIDATE)
public class CandidateController {

	@Autowired
	private TempResultService tempResultService;
	
	@Autowired
	private CandidateService candidateService;
	
	@Autowired
	private QuestionService questionService;

	private final RedisCandidateDoTestCache valueCache;

	public CandidateController(final RedisCandidateDoTestCache valueCache) {
		this.valueCache = valueCache;
	}
	
	@GetMapping("/")
	@ResponseBody
	public Candidate toTestPage(HttpServletRequest req) {
		Candidate candidate = (Candidate) req.getSession().getAttribute("candidate");
		return candidate;
	}

//	@GetMapping(URL.CANDIDATE_GET_A_TEST)
//	public Test toCandidateTestView(@PathVariable String code,@PathVariable("idCandidate") Integer idCandidate,HttpServletRequest req) {
//		HttpSession session = req.getSession();
//		session.setAttribute("test", testService.getWithCode(code));
//		return candidateService.joinTestByCode(code,idCandidate);
//	}
	
	@GetMapping(URL.CANDIDATE_GET_ALL_TEST)
	public Set<Test> getAllCandidateTest(HttpServletRequest req) {
		HttpSession session = req.getSession();
		Candidate candidate = (Candidate) session.getAttribute("candidate");
		Set<Test> candidateTest = candidate.getTests();
		session.setAttribute("listtest", candidateTest);
		
		return candidateService.joinAllTest(candidate.getId());
	}

	@PostMapping("/doingtest/{idQuestion}")
	public void cacheAnswer(HttpServletRequest req, @RequestBody TempResultOfCandidate tempAns,@PathVariable Integer idQuestion) {
		HttpSession session = req.getSession();
		Candidate candidate = (Candidate) session.getAttribute("candidate");
		Set<Test> listTest = (Set<Test>) candidate.getTests();
		Integer idCandidate = candidate.getId();
		tempAns.setIdCandidate(idCandidate);
		tempAns.setAnswer("1");
		if (questionService.findById(idQuestion).getType() == 0) {
			tempAns.setType(0);
		} else {
			tempAns.setType(1);
		}
		valueCache.cache("testtime", LocalTime.now().toSecondOfDay()); 
		int timenow = (int) valueCache.getCachedValue("testtime");
		int testtime = candidate.CalculatorTotalTime(listTest);
		int timestart = candidate.getDates().toLocalTime().toSecondOfDay();
		if (timenow - timestart <= testtime) {
			if (tempAns.getType() == 0) {
				valueCache.saveMultiple(tempAns,idCandidate,idQuestion);
			} else {
				valueCache.saveEssay(tempAns,idCandidate,idQuestion);
			}
		} else {
			List<TempResultOfCandidate> finalRes = new ArrayList<>();
			@SuppressWarnings("unchecked")
			Map<String, TempResultOfCandidate> finalRes1 = (Map<String, TempResultOfCandidate>) valueCache
					.getHashCacheAns("ans");
			for (Map.Entry<String, TempResultOfCandidate> e : finalRes1.entrySet()) {
				String thisKey = e.getKey();
				String[] str = thisKey.split(":");
				if (str[1].equals(idCandidate.toString())) {
					finalRes.add(e.getValue());
				}
			}
			tempResultService.saveAll(finalRes);
			candidateService.setIsDone(idCandidate);
			valueCache.delete("ans",idCandidate);
		}
	}

	@GetMapping("/getcacheans")
	@ResponseBody
	@SuppressWarnings("unchecked")
	public Map<String, TempResultOfCandidate> getTempAns() {
		return (Map<String, TempResultOfCandidate>) valueCache.getHashCacheAns("ans");
	}

	@PostMapping(URL.CANDIDATE_SUBMIT)
	public ResponseEntity<ResponeObject> setTestIsDone(HttpServletRequest req) {
		HttpSession session = req.getSession();
		Candidate candidate = (Candidate) session.getAttribute("candidate");
		Integer idCandidate = candidate.getId();
		candidateService.setIsDone(idCandidate);
		List<TempResultOfCandidate> finalRes = new ArrayList<>();
		@SuppressWarnings("unchecked")
		Map<String, TempResultOfCandidate> finalRes1 = (Map<String, TempResultOfCandidate>) valueCache
				.getHashCacheAns("ans");
		for (Map.Entry<String, TempResultOfCandidate> e : finalRes1.entrySet()) {
			String thisKey = e.getKey();
			String[] str = thisKey.split(":");
			if (str[1].equals(idCandidate.toString())) {
				finalRes.add(e.getValue());
			}
		}
		tempResultService.saveAll(finalRes);
		candidateService.setIsDone(idCandidate);
		valueCache.delete("ans",idCandidate);
		session.setAttribute("test", null);
		return ResponseEntity.status(HttpStatus.OK).body(new ResponeObject("OK", "Nop bai thanh cong", ""));
	}
	
	@PostMapping("/delallcache")
	public void deleteAllCache() {
		valueCache.deleteAll();
	}

	@PutMapping(URL.CANDIDATE_LOGOUT)
	public String logout(HttpServletRequest req) {
		HttpSession session = req.getSession();
		session.setAttribute("candidate", null);
		session.setAttribute("test", null);
		return "redirect:/testingonline";
	}

}
