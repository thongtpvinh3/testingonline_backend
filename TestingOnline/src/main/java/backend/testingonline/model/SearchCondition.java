package backend.testingonline.model;

public enum SearchCondition {
    CANDIDATE_BYNAME,
    CANDIDATE_BYPOSTION,
    CANDIDATE_BYLEVEL,
    CANDIDATE_BYDATE,
    CANDIDATE_BYEMAIL,
    CANDIDATE_BYPHONE,
    TEST_BYNAME,
    TEST_BYLEVEL,
    TEST_BYSUBJECT;

    public String getSearchCondition() {
        return this.name();
    }
}
