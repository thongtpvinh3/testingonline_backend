package backend.testingonline;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import backend.testingonline.model.Candidate;
import backend.testingonline.model.Question;

public class StaffControllerTest extends AbstractTest {

	@Override
	@Before
	public void setUp() {
		super.setUp();
	}

	@Test
	@Order(1)
	public void testLogin() throws Exception {
		String url = "/checklogin";

		String urlParam = "username=luubi&password=123";

		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(url)
				.contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE).content(urlParam)).andReturn();
		int httpStatusCode = mvcResult.getResponse().getStatus();
		assertEquals(httpStatusCode, 200);
	}

	@Test
	public void testGetAllCandidate() throws Exception {
		String url = "/staff/listcandidate";

		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(url)).andReturn();
		int httpStatusCode = mvcResult.getResponse().getStatus();
		assertEquals(httpStatusCode, 200);
		String content = mvcResult.getResponse().getContentAsString();
		List<Candidate> candidateList = super.mapFromJson(content, List.class);
		assertTrue(candidateList.size() > 0);
		System.out.println(candidateList);
	}

	@Test
	public void testAddCandidate() throws Exception {
		String url = "/staff/addcandidate";

		Candidate newCandidate = new Candidate("newbie", "012345848", "new@gmail.com");
		String inputJson = super.mapToJson(newCandidate);

		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(url).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();
		int httpStatusCode = mvcResult.getResponse().getStatus();
		assertEquals(httpStatusCode, 200);
		System.out.println();
	}

	@Test
	public void testDeleteCandidate() throws Exception {
		String url = "/staff/delete/{id}";

		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(url, "1")).andReturn();
		int httpStatusCode = mvcResult.getResponse().getStatus();
		assertEquals(httpStatusCode, 200);
		System.out.println();
	}

	@Test
	public void testGetAllTest() throws Exception {
		String url = "/staff/getalltest";

		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(url)).andReturn();
		int httpStatusCode = mvcResult.getResponse().getStatus();
		assertEquals(httpStatusCode, 200);
		String content = mvcResult.getResponse().getContentAsString();
		List<backend.testingonline.model.Test> testList = super.mapFromJson(content, List.class);
		assertTrue(testList.size() > 0);
		System.out.println(testList);
	}

//	@Test
//	public void testAddTest() throws Exception {
//		String url = "/staff/addtest";
//
//		backend.testingonline.model.Test newTest = new backend.testingonline.model.Test(2, 2, "bai test coding lv2", 0,
//				"CODE125");
//		String inputJson = super.mapToJson(newTest);
//
//		MvcResult mvcResult = mvc.perform(
//				MockMvcRequestBuilders.post(url).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
//				.andReturn();
//		int httpStatusCode = mvcResult.getResponse().getStatus();
//		assertEquals(httpStatusCode, 200);
//		System.out.println();
//	}

	@Test
	public void testAddTestForCandidate() throws Exception {
		String url = "/addtestforcandidate/1/2";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(url)).andReturn();
		int httpStatusCode = mvcResult.getResponse().getStatus();
		assertEquals(httpStatusCode, 200);
	}

	@Test
	public void testDeleteTest() throws Exception {
		String url = "/staff/deletetest/{id}";

		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(url, "1")).andReturn();
		int httpStatusCode = mvcResult.getResponse().getStatus();
		assertEquals(httpStatusCode, 200);
		System.out.println();
	}

	@Test
	public void testAddAnswerToQuestion() throws Exception {
		String url = "/staff/addanswertoquestion/1/2";

		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(url)).andReturn();
		int httpStatuscode = mvcResult.getResponse().getStatus();
		assertEquals(httpStatuscode, 200);
	}

	@Test
	public void testAddQuestion() throws Exception {
		String url = "/staff/addquestion";

		Question newQuestion = new Question();
		String inputJson = super.mapToJson(newQuestion);

		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(url).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();
		int httpStatusCode = mvcResult.getResponse().getStatus();
		assertEquals(httpStatusCode, 200);
	}

	@Test
	public void testaddMCAnswerToQuestion() throws Exception {
		String url = "/addmultiplechoiceanswer/1";
		
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(url)).andReturn();
		int httpStatusCode = mvcResult.getResponse().getStatus();
		assertEquals(httpStatusCode, 200);
	}
	
	@Test
	public void testAddEssayAnswerToQuestion() throws Exception {
		String url = "/addessayanswer/6";
		
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(url)).andReturn();
		int htppStatusCode = mvcResult.getResponse().getStatus();
		assertEquals(htppStatusCode, 200);
	}
	
//	@Test
//	public void 
}
