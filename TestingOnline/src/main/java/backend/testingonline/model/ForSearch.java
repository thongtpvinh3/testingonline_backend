package backend.testingonline.model;

import java.io.Serializable;
import java.util.Map;

public class ForSearch implements Serializable {

    private Map<SearchCondition, String> condition;

    public Map<SearchCondition, String> getCondition() {
        return condition;
    }

    public void setCondition(Map<SearchCondition, String> condition) {
        this.condition = condition;
    }
}
