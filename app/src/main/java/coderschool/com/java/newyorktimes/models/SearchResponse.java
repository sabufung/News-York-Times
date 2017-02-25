package coderschool.com.java.newyorktimes.models;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by BuuPV on 2/25/2017.
 */

public class SearchResponse {
    private ResponseQuery response;
    private String status;
    private String copyright;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public ResponseQuery getResponse() {
        return response;
    }

    public void setResponse(ResponseQuery response) {
        this.response = response;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}
