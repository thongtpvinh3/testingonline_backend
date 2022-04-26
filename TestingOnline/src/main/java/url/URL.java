package url;

public class URL {

//-----------------------HOMEPAGE------------------------------------------------
	public static final String ALL_HOMEPAGE = "/testingonline"; 
	public static final String STAFF = "/staff";
	public static final String STAFF_TO_STAFFVIEW = "/home";
	public static final String STAFF_TO_LOGIN = "/login";
	public static final String STAFF_CHECK_LOGIN = "/checklogin";
	public static final String STAFF_LOGOUT = "/logout";
	
//------------------------STAFF--------------------------------------------------------	
	public static final String STAFF_GET_LIST_CANDIDATE = "/listcandidate";
	public static final String STAFF_ADD_CANDIDATE = "/addcandidate";
	public static final String STAFF_DELETE_CANDIDATE = "/delete/{id}";
	public static final String STAFF_ADD_TEST = "/addtest";
	public static final String STAFF_TO_TESTVIEW = "/test";
	public static final String STAFF_GETALL_TEST = "/getalltest";
	public static final String STAFF_GET_TEST_BY_LELVEL = "/gettestbylevel";
	public static final String SATFF_GET_TEST_BY_NAME = "/gettestbyname/{name}";
	public static final String SATFF_GET_TEST_BY_CODE = "/gettestbycode/{code}";
	public static final String SATFF_GET_TEST_BY_SUBJECT = "/gettestbysubject";
	public static final String SATFF_GET_TEST_BY_ID = "/gettestbyid/{id}";
	public static final String SATFF_GET_TEST_BY_DONE = "/gettestbydone/{done}";
	public static final String SATFF_GET_TEST_BY_CANDIDATEID = "/gettestbycandidateid/{id}";
	public static final String STAFF_DELETE_TEST_BY_ID = "/deletetest/{id}";
	public static final String STAFF_ADD_QUESTION_TO_TEST = "/addquestiontotest/{idQuestion}/{idTest}";
	public static final String STAFF_ADD_TEST_FOR_CANDIDATE = "/addtestforcandidate/{idTest}/{idCandidate}";
	public static final String STAFF_UPDATE_TEST = "/updatetest/{id}";
	public static final String STAFF_GETALL_QUESTION = "/getallquestion";
	public static final String STAFF_GET_QUESTION_BY_ID = "/getquestionbyid/{id}";
	public static final String STAFF_GET_QUESTION_BY_TYPE = "/getquestionbytype/{type}";
	public static final String STAFF_GET_QUESTION_BY_SUBJECT = "/getquestionbysubject";
	public static final String STAFF_GET_QUESTION_BY_LEVEL = "/getquestionbylevel";
	public static final String STAFF_GET_QUESTION_BY_TESTID = "/getquestionbytest/{idTest}";
	public static final String STAFF_ADD_QUESTION = "/addquestion";
	public static final String STAFF_DELETE_QUESTION = "/deletequestion/{id}";
	public static final String STAFF_EDIT_QUESTION = "/editquestion/{id}";
	public static final String STAFF_REVIEW_MC_QUESTION = "/reviewmcquestion/{idTest}/{idCandidate}";
	public static final String STAFF_REVIEW_ESSAY_QUESTION = "/reviewessayquestion/{idTest}/{idQuestion}/{mark}";
	public static final String STAFF_SET_MARK_FOR_CANDIDATE = "/setmark/{idCandidate}";
	public static final String STAFF_GET_ALL_RESULT = "/getallres";
	public static final String STAFF_ADD_ANS_TO_QUESTION = "/addanswertoquestion/{idAnswer}/{idQuestion}";
	public static final String STAFF_ADD_MCQ_FOR_QUESTION = "/addmultiplechoiceanswer/{idQuestion}";
	public static final String STAFF_DELETE_MC_ANSWER_FROM_QUESTION = "/deletemultipleanswer/{idAnswer}";
	public static final String STAFF_GET_ALL_MC_ANSWER = "/getallmcanswer";
	public static final String STAFF_UPDATE_MC_ANSWER_FOR_QUESTION = "/updatemcanswer/{idAnswer}";
	public static final String STAFF_ADD_E_ASNSWER_FOR_QUESTION = "/addessayanswer/{idQuestion}";
	public static final String STAFF_UPDATE_ESSAY_ANSWER = "/updateessayanswer/{idQuestion}";
	public static final String STAFF_GET_ALL_ESSAY_ANSWER = "/getallessayanswer";
	public static final String STAFF_GET_ALL_LEVEL = "/getalllevel";
	public static final String STAFF_ADD_NEW_LEVEL = "/addnewlevel";
	public static final String STAFF_DELETE_LEVEL = "/deletelevel/{id}";
	public static final String STAFF_GET_ALL_SUBJECT = "/getallsubject";
	public static final String STAFF_ADD_NEW_SUBJECT = "/addsubject";
	public static final String STAFF_DELETE_SUBJECT = "/deletesubject/{id}";
	
//-----------------------CANDIDATE---------------------------------------------------------------
	public static final String CANDIDATE_JOIN_TEST = "/jointest";
	public static final String CANDIDATE = "/testpage";
	public static final String CANDIDATE_GET_ALL_TEST = "/alltest";
	public static final String CANDIDATE_GET_A_TEST = "/test/{code}/{idCandidate}";
	public static final String CANDIDATE_SUBMIT = "/submit";
	public static final String CANDIDATE_GET_TEST = "/gettest";
	public static final String CANDIDATE_LOGOUT = "/logout";
}
