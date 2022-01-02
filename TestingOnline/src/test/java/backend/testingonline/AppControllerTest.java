package backend.testingonline;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

public class AppControllerTest extends AbstractTest {
	
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
		
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(url).contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE).content(urlParam)).andReturn();
		int httpStatusCode = mvcResult.getResponse().getStatus();
		assertEquals(httpStatusCode, 200);
	}
	
	@Test
	@Order(2)
	public void testJoinTest() throws Exception{
		String url = "/jointest";
		String urlParam ="code=ENG1";
		
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(url).contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE).content(urlParam)).andReturn();
		int httpStatusCode = mvcResult.getResponse().getStatus();
		assertEquals(httpStatusCode, 200);
	}
	
//	@Test
//	@Order(2)
//	public void testGetAllCandidate1() throws Exception {
//		String url = "/staff/listcandidate";
//		
//		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(url)).andReturn();
//		int httpStatusCode = mvcResult.getResponse().getStatus();
//		assertEquals(httpStatusCode, 200);
//		String content = mvcResult.getResponse().getContentAsString();
//		List<Candidate> candidateList = super.mapFromJson(content, List.class);
//		assertTrue(candidateList.size() > 0);
//		System.out.println(candidateList);
//	}
}
