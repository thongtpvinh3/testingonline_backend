package backend.testingonline.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import backend.testingonline.model.*;
import backend.testingonline.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import backend.testingonline.repository.QuestionTypeRepository;
import backend.testingonline.repository.StaffRepository;
import backend.testingonline.repository.TestRepository;
import backend.testingonline.responseException.ResponseObject;
import backend.testingonline.service.StaffService;

@Service
public class StaffServiceImpl implements StaffService {

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private TestRepository testRepository;

    @Autowired
    private QuestionTypeRepository questionTypeRepository;

    @Override
    public Optional<Candidate> findById(int id) {
        // TODO Auto-generated method stub
        return Optional.empty();
    }

//	@Override
//	public Staff findByUsernameAndPassword(String username, String password) {
//		return staffRepository.findByUsernameAndPassword(username, password);
//	}
//
//	@Override
//	public boolean login(String username, String password) {
//		// TODO Auto-generated method stub
//		Staff optionalStaff = staffRepository.findByUsernameAndPassword(username, password);
//		if (optionalStaff != null && optionalStaff.getPassword().equals(password)) {
//			System.out.println(optionalStaff.toString());
//			return true;
//		}
//		return false;
//	}

    @Override
    public ResponseEntity<ResponseObject> createTest(Test newTest) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseObject("OK", "Add success!", testRepository.save(newTest)));
    }

    @Override
    public ResponseEntity<ResponseObject> creaeteType(QuestionType questionType) {
        try {
            return ResponseEntity.ok(new ResponseObject("OK!", "them thanh cong loai cau hoi: " + questionType.getName(), questionTypeRepository.save(questionType)));

        } catch (Exception e) {
            throw new RuntimeException("Failed", e);
        }
    }

    @Override
    public Set<Object> search(ForSearch conditional) {
        Map<SearchCondition, String> condi = conditional.getCondition();
        Set<Object> result = new HashSet<>();
        List<Candidate> candidates = candidateRepository.findAll();
        List<Test> tests = testRepository.findAll();
        result.addAll(candidates);
        result.addAll(tests);
        for (Map.Entry<SearchCondition, String> e : condi.entrySet()) {
            String cSearch = e.getValue();
            switch (e.getKey()) {
                case CANDIDATE_BYDATE:
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    List<Candidate> fCan = candidateRepository.findByDates(LocalDate.parse(cSearch,formatter));
                    result.removeIf(o -> !fCan.contains(o));
                    continue;
                case CANDIDATE_BYNAME:
                    List<Candidate> fCan1 = candidateRepository.findByName(cSearch);
                    result.removeIf(o -> !fCan1.contains(o));
                    continue;
                case CANDIDATE_BYPOSITION:
                    List<Candidate> fCan2 = candidateRepository.findByPosition(cSearch);
                    result.removeIf(o -> !fCan2.contains(o));
                    continue;
                case CANDIDATE_BYEMAIL:
                    List<Candidate> fCan3 = candidateRepository.findByEmail(cSearch);
                    result.removeIf(o -> !fCan3.contains(o));
                    continue;
                case CANDIDATE_BYLEVEL:
                    List<Candidate> fCan4 = candidateRepository.findByLevel(Integer.parseInt(cSearch));
                    result.removeIf(o -> !fCan4.contains(o));
                    continue;
                case CANDIDATE_BYPHONE:
                    List<Candidate> fCan5 = candidateRepository.findByPhone(cSearch);
                    result.removeIf(o -> !fCan5.contains(o));
                    continue;
                case TEST_BYLEVEL:
                    List<Test> fTest = testRepository.getByLevelId(Integer.parseInt(cSearch));
					result.removeIf(o -> !fTest.contains(o));
                    continue;
                case TEST_BYNAME:
                    List<Test> fTest1 = testRepository.findByName(cSearch);
					result.removeIf(o -> !fTest1.contains(o));
                    continue;
                case TEST_BYSUBJECT:
                    List<Test> fTest2 = testRepository.getBySubjectId(Integer.parseInt(cSearch));
					result.removeIf(o -> !fTest2.contains(o));
            }
        }
        return result;
    }

    @Override
    public ResponseEntity<ResponseObject> createStaff(Staff newStaff) {
        try {
            return ResponseEntity.ok(new ResponseObject("OK", "Them thanh cong", staffRepository.save(newStaff)));
        } catch (Exception e) {
            throw new RuntimeException("Failed", e);
        }
    }
}
