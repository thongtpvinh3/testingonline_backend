package backend.testingonline.service.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import backend.testingonline.model.Candidate;
import backend.testingonline.model.CandidateTest;
import backend.testingonline.model.DateCandidate;
import backend.testingonline.model.Levels;
import backend.testingonline.model.MultipleChoiceQuestion;
import backend.testingonline.model.Question;
import backend.testingonline.model.QuestionType;
import backend.testingonline.model.Subject;
import backend.testingonline.model.TempResultOfCandidate;
import backend.testingonline.model.Test;
import backend.testingonline.repository.CandidateRepository;
import backend.testingonline.repository.CandidateTestRepository;
import backend.testingonline.repository.LevelRepository;
import backend.testingonline.repository.MultipleChoiceQuestionRepository;
import backend.testingonline.repository.QuestionRepository;
import backend.testingonline.repository.QuestionTypeRepository;
import backend.testingonline.repository.SubjectRepository;
import backend.testingonline.repository.TempResultRepository;
import backend.testingonline.repository.TestRepository;
import backend.testingonline.responseException.ResponseObject;
import backend.testingonline.service.TestService;

@Service
public class TestServiceImpl implements TestService {

	@Autowired
	private TestRepository testRepository;

	@Autowired
	private CandidateRepository candidateRepository;

	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private MultipleChoiceQuestionRepository multipleChoiceQuestionRepository;

	@Autowired
	private TempResultRepository tempResultRepository;

	@Autowired
	private CandidateTestRepository candidateTestRepository;

	@Autowired
	private SubjectRepository subjectRepository;

	@Autowired
	private LevelRepository levelRepository;

	@Autowired
	private QuestionTypeRepository questionTypeRepository;

	@Override
	public List<Test> getAllTest() {
		return testRepository.findAll();
	}

	@Override
	public List<Test> findByLevel(Levels level) {
		return testRepository.findByLevel(level);
	}

//	-

	@Override
	public ResponseEntity<ResponseObject> deleteById(Integer id) {
		boolean exits = testRepository.existsById(id);
		if (exits) {
			testRepository.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("OK", "Delete Success!", ""));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new ResponseObject("FAILED", "Cannot find candidate delete", ""));
	}

	@Override
	public Test findById(Integer id) {
		return testRepository.getById(id);
	}

	@Override
	public Test updateTest(Integer id, Test test) {
		try {
			Test foundTest = testRepository.getById(id);

			foundTest.setLevel(test.getLevel());
			foundTest.setName(test.getName());
			foundTest.setQuestions(test.getQuestions());
			for(Question q: test.getQuestions()) {
				for (MultipleChoiceQuestion mc: q.getMultipleChoiceQuestions()) {
					mc.setQuestion(q);
				}
			}
			foundTest.setSubject(test.getSubject());
			foundTest.setTimes(test.getTimes());
			foundTest.setCandidates(test.getCandidates());
			foundTest.setDisplayCandidate(test.getDisplayCandidate());

			return testRepository.save(foundTest);
		} catch (Exception e) {
			throw new RuntimeException("Khong duoc!!");
		}
	}

	@Override
	public ResponseEntity<ResponseObject> addQuestionTotest(Integer idTest, Integer idQuestion) {

		Question newQuestion = questionRepository.findById(idQuestion).get();
		Test foundTest = testRepository.findById(idTest).get();

		if (foundTest.getQuestions().size() == 0 && newQuestion.getLevel() == foundTest.getLevel()
				&& newQuestion.getSubject() == foundTest.getSubject()) {
			Set<Question> newList = foundTest.getQuestions();
			newList.add(newQuestion);
			foundTest.setQuestions(newList);
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject("OK", "Add success !", testRepository.save(foundTest)));
		} else {
			if (newQuestion.getLevel() != foundTest.getLevel() || newQuestion.getSubject() != foundTest.getSubject()) {
				return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
						.body(new ResponseObject("FAILED", "Subject or Level is not equals", ""));
			}
			if (foundTest.getQuestions().contains(newQuestion) == false
					&& newQuestion.getLevel() == foundTest.getLevel()
					&& newQuestion.getSubject() == foundTest.getSubject()) {
				Set<Question> newList = foundTest.getQuestions();
				newList.add(newQuestion);
				foundTest.setQuestions(newList);
				return ResponseEntity.status(HttpStatus.OK)
						.body(new ResponseObject("OK", "Add success !", testRepository.save(foundTest)));
			} else {
				return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
						.body(new ResponseObject("FAILED", "Duplicate question", ""));
			}
		}
	}

	@Override
	public List<Test> findByName(String name) {
		return testRepository.findByName(name);
	}

	@Override
	public List<Test> findBySubject(Subject subject) {
		return testRepository.findBySubject(subject);
	}

	@Override
	public Set<Test> findByCandidateId(Integer id) {
		Candidate foundCandidate = candidateRepository.getById(id);
		return foundCandidate.getTests();
	}

	@Override
	public ResponseEntity<ResponseObject> addTestForCandidate(Integer idTest, Integer idCandidate) {
		Test newTest = testRepository.getById(idTest);
		Candidate foundCandidate = candidateRepository.getById(idCandidate);

		if (newTest.getTimes() != null) {
			if (newTest.getLevel() != foundCandidate.getLevel()) {
				return ResponseEntity.status(HttpStatus.OK)
						.body(new ResponseObject("FAILED", "Level khong phu hop!", ""));
			}

			Set<Test> newList = foundCandidate.getTests();
			if (!foundCandidate.getTests().contains(newTest)) {
				newList.add(newTest);
				foundCandidate.setTests(newList);
				return ResponseEntity.status(HttpStatus.OK)
						.body(new ResponseObject("OK", "Add success !", candidateRepository.save(foundCandidate)));
			} else {
				return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
						.body(new ResponseObject("FAILED", "Bai test bi trung !", ""));
			}
		}
		return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
				.body(new ResponseObject("FAILED", "Bạn phải add thời gian vào đã chứ !", ""));
	}

	@Override
	public Double reviewMCQuestion(Integer idTest, Integer idCandidate) {
		Test foundTest = testRepository.getById(idTest);
		Set<Question> thisTestQuestion = foundTest.getQuestions();
		List<TempResultOfCandidate> result = tempResultRepository.getAnswerOfCandidateInTest(idCandidate, 1, idTest);
		int count = 0;
		int rightResult = 0;

		for (Question q : thisTestQuestion) {
			if (q.getType().getId() == 1) {
				count++;
			}
		}

		for (TempResultOfCandidate res : result) {
			if (multipleChoiceQuestionRepository.findWithIdAndisTrue(res.getIdAnswer(),
					Integer.parseInt(res.getAnswer())) != null && res.getIdTest() == idTest
					&& res.getIdCandidate() == idCandidate) {
				rightResult++;
			}
		}

		System.out.println("\n" + "tong so cau hoi trong bai test: " + count);
		System.out.println("\n" + "tong so cau dung trong bai test: " + rightResult);

		Double oneQuestionMark = 100.0 / count;
		DecimalFormat df = new DecimalFormat("##.##");
		Double tmp = Double.parseDouble(df.format(oneQuestionMark));
		System.out.println("\n" + tmp + "\n");

		Double lastResult = tmp * rightResult;
		CandidateTest candidateTest = candidateTestRepository.findByCandidateIdAndTestId(idCandidate, idTest);
		candidateTest.setMarks(lastResult + candidateTest.getMarks());
		candidateTestRepository.save(candidateTest);
		testRepository.save(foundTest);

		return lastResult;
	}

	@Override
	public ResponseEntity<ResponseObject> reviewEssayQuestion(Integer idTest, Integer idCandidate, Double mark) {
		Test foundTest = testRepository.getById(idTest);
		Set<Question> listQ = foundTest.getQuestions();
		CandidateTest candidateTest = candidateTestRepository.findByCandidateIdAndTestId(idCandidate, idTest);
		int count = 0;
		for (Question q : listQ) {
			if (q.getType().getId() == 2) {
				count++;
			}
		}

		Double oneQuestionMark = 100.0 / count;
		DecimalFormat df = new DecimalFormat("##.##");
		Double tmp = Double.parseDouble(df.format(oneQuestionMark));

		if (mark > tmp) {
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
					.body(new ResponseObject("FAILED!", "Diem vuot qua max cau hoi! (" + tmp + ")", ""));
		} else {
			Double x = candidateTest.getMarks() + mark;
			candidateTest.setMarks(x);
			candidateTestRepository.save(candidateTest);
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject("OK", "Cham Diem thanh cong!", testRepository.save(foundTest)));
		}
	}


	@Override
	public Test addNewQuestion(Integer idTest, Question newQuestion) {
		Test foundTest = testRepository.getById(idTest);
		newQuestion.setLevel(foundTest.getLevel());
		newQuestion.setSubject(foundTest.getSubject());
		Set<Question> thisListQuestion = foundTest.getQuestions();
		questionRepository.save(newQuestion);
		for (MultipleChoiceQuestion m : newQuestion.getMultipleChoiceQuestions()) {
			m.setQuestion(newQuestion);
		}
		thisListQuestion.add(newQuestion);
		return testRepository.save(foundTest);
	}

	@Override
	public List<Question> addQuestionInXlsFile(String xlsFilePath) throws IOException {

		final int COLUMN_INDEX_CONTENT = 1;
		final int COLUMN_INDEX_ANSWER_A = 2;
		final int COLUMN_INDEX_ANSWER_B = 3;
		final int COLUMN_INDEX_ANSWER_C = 4;
		final int COLUMN_INDEX_ANSWER_D = 5;
		final int COLUMN_INDEX_TRUE = 6;
		Integer idSubject = 1;
		Levels level = levelRepository.findById(1).get();
		QuestionType type = questionTypeRepository.findById(1).get();
		List<Question> presentList = questionRepository.findAll();
		List<Question> listQuestions = new ArrayList<>();
		FileInputStream file = new FileInputStream(xlsFilePath);
		Workbook wb = getWorkbook(file, xlsFilePath);
		Iterator<Sheet> sheet = wb.iterator();
		DataFormatter formatter = new DataFormatter();
		// sheet
		while (sheet.hasNext()) {
			System.out.println(idSubject);
			Subject subject = subjectRepository.findById(idSubject).get();
			Iterator<Row> row = sheet.next().iterator();
			// row
			while (row.hasNext()) {
				Row nextRow = row.next();
				if (nextRow.getRowNum() == 0) {
					continue;
				}
				Iterator<Cell> cellIterator = nextRow.cellIterator();
				Question newQuestion = new Question();
				List<MultipleChoiceQuestion> m = new ArrayList<>();
				MultipleChoiceQuestion A = new MultipleChoiceQuestion();
				MultipleChoiceQuestion B = new MultipleChoiceQuestion();
				MultipleChoiceQuestion C = new MultipleChoiceQuestion();
				MultipleChoiceQuestion D = new MultipleChoiceQuestion();
				newQuestion.setType(type);
				newQuestion.setSubject(subject);
				newQuestion.setLevel(level);
				// cell
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					if (cell.getColumnIndex() == 0) {
//						if() break;
						continue;
					} else {
						int columnIndex = cell.getColumnIndex();
						switch (columnIndex) {
						case COLUMN_INDEX_CONTENT:
							newQuestion.setContent((String) cell.getStringCellValue());
//							System.out.println("\n" + newQuestion.toString() + "\n");
//							System.out.println("\n" + cell.getStringCellValue() + "\n");
							continue;
						case COLUMN_INDEX_ANSWER_A:
							A.setAnswer(formatter.formatCellValue(cell));
//							System.out.println(cell.getStringCellValue());
							continue;
						case COLUMN_INDEX_ANSWER_B:
							B.setAnswer(formatter.formatCellValue(cell));
//							System.out.println(cell.getStringCellValue());
							continue;
						case COLUMN_INDEX_ANSWER_C:
							C.setAnswer(formatter.formatCellValue(cell));
//							System.out.println(cell.getStringCellValue());
							continue;
						case COLUMN_INDEX_ANSWER_D:
							D.setAnswer(formatter.formatCellValue(cell));
//							System.out.println(cell.getStringCellValue());
							continue;
						case COLUMN_INDEX_TRUE:
//							System.out.println(cell.getStringCellValue());
							if (cell.getStringCellValue().equals("A")) {
								A.setIsTrue(1);
								B.setIsTrue(0);
								C.setIsTrue(0);
								D.setIsTrue(0);
							}
							if (cell.getStringCellValue().equals("B")) {
								A.setIsTrue(0);
								B.setIsTrue(1);
								C.setIsTrue(0);
								D.setIsTrue(0);
							}
							if (cell.getStringCellValue().equals("C")) {
								A.setIsTrue(0);
								B.setIsTrue(0);
								C.setIsTrue(1);
								D.setIsTrue(0);
							}
							if (cell.getStringCellValue().equals("D")) {
								A.setIsTrue(0);
								B.setIsTrue(0);
								C.setIsTrue(0);
								D.setIsTrue(1);
							}
						}
						m.add(A);
						m.add(B);
						m.add(C);
						m.add(D);
					}
					System.out.println("\n\n\nxong 1 hang\n\n\n");
					System.out.println("\n" + presentList.contains(newQuestion) + "\n");
					if (presentList.contains(newQuestion) == false) {
						questionRepository.save(newQuestion);
						for (MultipleChoiceQuestion ans : m) {
							ans.setQuestion(newQuestion);
						}
						multipleChoiceQuestionRepository.saveAll(m);
						listQuestions.add(newQuestion);
					}
				}
			}
			idSubject++;
		}
		wb.close();
		file.close();
		return listQuestions;
	}

	private static Workbook getWorkbook(InputStream inputStream, String excelFilePath) throws IOException {
		Workbook workbook = null;
		if (excelFilePath.endsWith("xlsx")) {
			workbook = new XSSFWorkbook(inputStream);
		} else {
			throw new IllegalArgumentException("The specified file is not Excel file");
		}
		return workbook;
	}

	@Override
	public Set<DateCandidate> getOutOfDateTest() {
		Set<DateCandidate> foundCandidate = new HashSet<>();
		Set<LocalDate> dates = new HashSet<>();
		List<Candidate> allCandidate = candidateRepository.findAll();
		for (Candidate c : allCandidate) {
			if (c.getDates().isAfter(LocalDate.now())) {
				continue;
			} else {
				dates.add(c.getDates());
				System.out.println(dates);
			}
		}
		for (LocalDate d : dates) {
			foundCandidate.add(new DateCandidate(d));
			System.out.println(foundCandidate);
		}
		for (DateCandidate dc : foundCandidate) {
			Set<Candidate> can = new HashSet<>();
			for (Candidate c : allCandidate) {
				if (c.getDates().equals(dc.getDate())) {
					can.add(c);
				}
			}
			dc.setCandidates(can);
		}
		return foundCandidate;
	}

	@Override
	public DateCandidate getTodayTest() {
		DateCandidate foundCandidate = new DateCandidate();
		foundCandidate.setDate(LocalDate.now());
		Set<Candidate> can = new HashSet<>();
		List<Candidate> allCandidate = candidateRepository.findAll();
		for (Candidate c : allCandidate) {
			LocalDate date = c.getDates();
			if (date.equals(LocalDate.now())) {
				can.add(c);
			}
		}
		foundCandidate.setCandidates(can);
		return foundCandidate;
	}

	@Override
	public Set<DateCandidate> getUndueTest() {
		Set<DateCandidate> foundCandidate = new HashSet<>();
		Set<LocalDate> dates = new HashSet<>();
		List<Candidate> allCandidate = candidateRepository.findAll();
		for (Candidate c : allCandidate) {
			if (c.getDates().isBefore(LocalDate.now())) {
				System.out.println(LocalDate.now());
				continue;
			} else {
				dates.add(c.getDates());
				System.out.println(dates);
			}
		}
		for (LocalDate d : dates) {
			foundCandidate.add(new DateCandidate(d));
			System.out.println(foundCandidate);
		}
		for (DateCandidate dc : foundCandidate) {
			Set<Candidate> can = new HashSet<>();
			for (Candidate c : allCandidate) {
				if (c.getDates().equals(dc.getDate())) {
					can.add(c);
				}
			}
			dc.setCandidates(can);
		}
		return foundCandidate;
	}

	@Override
	public List<Candidate> getOutOfDateCandidate() {
		List<Candidate> can = new ArrayList<>();
		for (Candidate c: candidateRepository.findAll()) {
			if (c.getDates().isBefore(LocalDate.now())) {
				can.add(c);
			}
		}
		return can;
	}

	@Override
	public List<Candidate> getTodayCandidate() {
		List<Candidate> can = new ArrayList<>();
		for (Candidate c: candidateRepository.findAll()) {
			if (c.getDates().equals(LocalDate.now())) {
				can.add(c);
			}
		}
		return can;
	}

	@Override
	public List<Candidate> getUndueCandidate() {
		List<Candidate> can = new ArrayList<>();
		for (Candidate c: candidateRepository.findAll()) {
			if (c.getDates().isAfter(LocalDate.now())) {
				can.add(c);
			}
		}
		return can;
	}

}
