package url;

public class URL {
	
	public static final String ALL_HOMEPAGE = "/testingonline"; 
	public static final String STAFF = "/staff";
	public static final String STAFF_TO_STAFFVIEW = "/home";
	public static final String STAFF_TO_LOGIN = "/login";
	public static final String STAFF_CHECK_LOGIN = "/checklogin";
	public static final String STAFF_LOGOUT = "/logout";
	public static final String STAFF_GET_LIST_CANDIDATE = "/listcandidate";
	public static final String STAFF_ADD_CANDIDATE = "/addcandidate";
//	public static final String STAFF_UPDATE_CANDIDATE = "/"
	public static final String STAFF_DELETE_CANDIDATE = "/delete/{id}";
	public static final String STAFF_ADD_TEST = "/addtest";
	public static final String STAFF_TO_TESTVIEW = "/test";
	public static final String STAFF_GETALL_TEST = "/getalltest";
	public static final String STAFF_GET_TEST_BY_LELVEL = "/getalltest/{level}";
	public static final String STAFF_DELETE_TEST_BY_ID = "/deletetest/{id}";
	public static final String STAFF_ADD_QUESTION_TO_TEST = "/addquestiontotest/{idTest}/{idQuestion}";
	public static final String STAFF_UPDATE_TEST = "/updatetest/{id}";
	public static final String STAFF_GETALL_QUESTION = "/getallquestion";
	public static final String STAFF_ADD_QUESTION = "/addquestion";
	public static final String STAFF_DELETE_QUESTION = "/deletequestion/{id}";
	public static final String STAFF_EDIT_QUESTION = "/editquestion/{id}";
	public static final String CANDIDATE_JOIN_TEST = "/jointest";
	public static final String CANDIDATE = "/testpage";
	public static final String CANDIDATE_ALL_TEST = "/test/{id}";
}
