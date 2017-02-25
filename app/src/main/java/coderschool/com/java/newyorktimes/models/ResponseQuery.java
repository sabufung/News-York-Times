package coderschool.com.java.newyorktimes.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by BuuPV on 2/25/2017.
 */

public class ResponseQuery {
    private List<Doc> docs;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public List<Doc> getDocs() {
        return docs;
    }

    public void setDocs(List<Doc> docs) {
        this.docs = docs;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}
