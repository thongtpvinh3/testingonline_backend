package backend.testingonline.service.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.time.LocalTime;
import java.util.ArrayList;
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
import backend.testingonline.model.MultipleChoiceQuestion;
import backend.testingonline.model.Question;
import backend.testingonline.model.TempResultOfCandidate;
import backend.testingonline.model.Test;
import backend.testingonline.repository.CandidateRepository;
import backend.testingonline.repository.CandidateTestRepository;
import backend.testingonline.repository.MultipleChoiceQuestionRepository;
import backend.testingonline.repository.QuestionRepository;
import backend.testingonline.repository.TempResultRepository;
import backend.testingonline.repository.TestRepository;
import backend.testingonline.responeexception.ResponeObject;
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

	@Override
	public List<Test> getAllTest() {
		return testRepository.findAll();
	}

	@Override
	public Test getWithCode(String code) {
		return testRepository.findByCodeTest(code);
	}

	@Override
	public List<Test> findByLevel(Integer level) {
		return testRepository.findByLevel(level);
	}

//	-

	@Override
	public ResponseEntity<ResponeObject> deleteById(Integer id) {
		testRepository.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).body(new ResponeObject("OK", "Delete Success!", ""));
	}

	@Override
	public Test findById(Integer id) {
		return testRepository.getById(id);
	}

	@Override
	public Test updateTest(Integer id, Test test) {
		Test foundTest = testRepository.getById(id);

		foundTest.setLevel(test.getLevel());
		foundTest.setName(test.getName());
		foundTest.setQuestions(test.getQuestions());
		foundTest.setSubject(test.getSubject());
		foundTest.setCodeTest(test.getCodeTest());
		foundTest.setTime(test.getTime());

		return testRepository.save(foundTest);

	}

	@Override
	public ResponseEntity<ResponeObject> addQuestionTotest(Integer idTest, Integer idQuestion) {

		Question newQuestion = questionRepository.findById(idQuestion).get();
		Test foundTest = testRepository.getById(idTest);

		if (foundTest.getQuestions().size() == 0 && newQuestion.getLevel() == foundTest.getLevel()
				&& newQuestion.getSubject() == foundTest.getSubject()) {
			Set<Question> newList = foundTest.getQuestions();
			newList.add(newQuestion);
			foundTest.setQuestions(newList);
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponeObject("OK", "Add success !", testRepository.save(foundTest)));
		} else {
			if (newQuestion.getLevel() != foundTest.getLevel() || newQuestion.getSubject() != foundTest.getSubject()) {
				return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
						.body(new ResponeObject("FAILED", "Subject or Level is not equals", ""));
			}
			if (foundTest.getQuestions().contains(newQuestion) == false
					&& newQuestion.getLevel() == foundTest.getLevel()
					&& newQuestion.getSubject() == foundTest.getSubject()) {
				Set<Question> newList = foundTest.getQuestions();
				newList.add(newQuestion);
				foundTest.setQuestions(newList);
				return ResponseEntity.status(HttpStatus.OK)
						.body(new ResponeObject("OK", "Add success !", testRepository.save(foundTest)));
			} else {
				return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
						.body(new ResponeObject("FAILED", "Duplicate question", ""));
			}
		}
	}

	@Override
	public List<Test> findByName(String name) {
		return testRepository.findByName(name);
	}

	@Override
	public List<Test> findBySubject(Integer subject) {
		return testRepository.findBySubject(subject);
	}

	@Override
	public Set<Test> findByCandidateId(Integer id) {
		Candidate foundCandidate = candidateRepository.getById(id);
		return foundCandidate.getTests();
	}

	@Override
	public ResponseEntity<ResponeObject> addTestForCandidate(Integer idTest, Integer idCandidate) {
		Test newTest = testRepository.getById(idTest);
		Candidate foundCandidate = candidateRepository.getById(idCandidate);

		if (newTest.getTime() != null) {
			if (newTest.getLevel() != foundCandidate.getLevel()) {
				return ResponseEntity.status(HttpStatus.OK).body(new ResponeObject("FAILED", "Level khong phu hop!", ""));
			}

			Set<Test> newList = foundCandidate.getTests();
			if (foundCandidate.getTests().contains(newTest) == false) {
				newList.add(newTest);
				foundCandidate.setTests(newList);
				return ResponseEntity.status(HttpStatus.OK)
						.body(new ResponeObject("OK", "Add success !", candidateRepository.save(foundCandidate)));
			} else {
				return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
						.body(new ResponeObject("FAILED", "Bai test bi trung !", ""));
			}
		}
		return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
				.body(new ResponeObject("FAILED", "Bạn phải add thời gian vào đã chứ !", ""));
	}

	@Override
	public ResponseEntity<ResponeObject> setTestTime(Integer idTest, LocalTime time) {
		Test foundTest = testRepository.getById(idTest);
		foundTest.setTime(time);
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponeObject("OK", "Set test time success!", testRepository.save(foundTest)));
	}

//	@Override
//	public ResponseEntity<ResponeObject> setDateTest(Integer idTest, LocalDateTime dateTest) {
//		Test foundTest = testRepository.getById(idTest);
//		if (dateTest.isAfter(LocalDateTime.now())) {
//			return ResponseEntity.status(HttpStatus.OK)
//					.body(new ResponeObject("FAILED", "Thoi gian khong hop le!", ""));
//		} else {
//			return ResponseEntity.status(HttpStatus.OK)
//					.body(new ResponeObject("OK", "Set date test success!", testRepository.save(foundTest)));
//		}
//	}

	@Override
	public Double reviewMCQuestion(Integer idTest, Integer idCandidate) {
		Test foundTest = testRepository.getById(idTest);
		Set<Question> thisTestQuestion = foundTest.getQuestions();
		List<TempResultOfCandidate> result = tempResultRepository.getAnswerOfCandidateInTest(idCandidate, 0, idTest);
		int count = 0;
		int rightResult = 0;

		for (Question q : thisTestQuestion) {
			if (q.getType() == 0) {
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
	public ResponseEntity<ResponeObject> reviewEssayQuestion(Integer idTest, Integer idCandidate, Double mark) {
		Test foundTest = testRepository.getById(idTest);
		Set<Question> listQ = foundTest.getQuestions();
		CandidateTest candidateTest = candidateTestRepository.findByCandidateIdAndTestId(idCandidate, idTest);
		int count = 0;
		for (Question q : listQ) {
			if (q.getType() == 1) {
				count++;
			}
		}

		Double oneQuestionMark = 100.0 / count;
		DecimalFormat df = new DecimalFormat("##.##");
		Double tmp = Double.parseDouble(df.format(oneQuestionMark));

		if (mark > tmp) {
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
					.body(new ResponeObject("FAILED!", "Diem vuot qua max cau hoi! (" + tmp + ")", ""));
		} else {
			Double x = candidateTest.getMarks() + mark;
			candidateTest.setMarks(x);
			candidateTestRepository.save(candidateTest);
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponeObject("OK", "Cham Diem thanh cong!", testRepository.save(foundTest)));
		}
	}

	@Override
	public List<Candidate> getCandidateOfTest(Integer idTest) {
		return testRepository.getById(idTest).getCandidates();
	}

	@Override
	public void setTestIsDone(Integer idTest, Integer idCandidate) {

	}

	@Override
	public Test addNewQuestion(Integer idTest, Question newQuestion) {
		Test foundTest = testRepository.getById(idTest);
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
		int subject = 1;
		List<Question> presentList = questionRepository.findAll();
		List<Question> listQuestions = new ArrayList<>();
		FileInputStream file = new FileInputStream(xlsFilePath);
		Workbook wb = getWorkbook(file, xlsFilePath);
		Iterator<Sheet> sheet = wb.iterator();
		DataFormatter formatter = new DataFormatter();
		// sheet
		while (sheet.hasNext()) {
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
				newQuestion.setType(0);
				newQuestion.setSubject(subject);
				newQuestion.setLevel(1);
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
			subject++;
			continue;
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

//	public static void main(String[] args) throws IOException {
//		final String filename = "C:/Users/thong/OneDrive/Desktop/a.xlsx";
//		final List<Question> newList = addQuestionInXlsFile(filename);
//	}

}
