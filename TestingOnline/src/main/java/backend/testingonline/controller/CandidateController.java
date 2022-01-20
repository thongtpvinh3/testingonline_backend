package backend.testingonline.controller;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import backend.testingonline.service.impl.RedisCandidateDoTestCache;
import url.URL;

@RestController
@RequestMapping(path = URL.CANDIDATE)
public class CandidateController {

	@Autowired
	private TestService testService;

	@Autowired
	private TempResultService tempResultService;

	private final RedisCandidateDoTestCache valueCache;

	public CandidateController(final RedisCandidateDoTestCache valueCache) {
		this.valueCache = valueCache;
	}

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

	@PostMapping("/doingtest")
	public void cacheAnswer(HttpServletRequest req, @RequestBody TempResultOfCandidate tempAns) {
		HttpSession session = req.getSession();
		Test thisTest = (Test) session.getAttribute("test");

		valueCache.cache("testtime", LocalTime.now().toSecondOfDay());
		int timenow = (int) valueCache.getCachedValue("testtime");
		int testtime = thisTest.timeToSecond();
		int timestart = thisTest.getDateTest().toLocalTime().toSecondOfDay();
		if (timenow - timestart <= testtime) {
//			
			valueCache.save(tempAns);
		} else {
			List<TempResultOfCandidate> finalRes = new ArrayList<>();
			Map<Integer, TempResultOfCandidate> finalRes1 = (Map<Integer, TempResultOfCandidate>) valueCache
					.getHashCacheAns("ans");
			for (Map.Entry<Integer, TempResultOfCandidate> e : finalRes1.entrySet()) {
				finalRes.add(e.getValue());
			}
			tempResultService.saveAll(finalRes);
			testService.setTestIsDone(thisTest.getId());
			valueCache.delete("ans");
		}
	}

	@GetMapping("/getcacheans")
	public Map<Integer, TempResultOfCandidate> getTempAns() {
		return (Map<Integer, TempResultOfCandidate>) valueCache.getHashCacheAns("ans");
	}

	@PostMapping(URL.CANDIDATE_SUBMIT)
	public ResponseEntity<ResponeObject> setTestIsDone(@PathVariable Integer idTest) {
		testService.setTestIsDone(idTest);
		List<TempResultOfCandidate> tempAnsResult = new ArrayList<>();
		Map<Integer, TempResultOfCandidate> finalRes1 = (Map<Integer, TempResultOfCandidate>) valueCache
				.getHashCacheAns("ans");
		for (Map.Entry<Integer, TempResultOfCandidate> e : finalRes1.entrySet()) {
			tempAnsResult.add(e.getValue());
		}
		tempResultService.saveAll(tempAnsResult);
		valueCache.delete("ans");
		return ResponseEntity.status(HttpStatus.OK).body(new ResponeObject("OK", "Nop bai thanh cong", ""));
	}

	@PostMapping(URL.CANDIDATE_LOGOUT)
	public String logout(HttpServletRequest req) {
		HttpSession session = req.getSession();
		session.setAttribute("test", null);
		return "redirect:/testingonline";
	}
}
