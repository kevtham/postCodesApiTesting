package com.test.postcode.model.post;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PostResultsForPostCode {

    private int status;
    private List<PostSubResultsForPostCode> result;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<PostSubResultsForPostCode> getResult() {
        return result;
    }

    public void setResult(List<PostSubResultsForPostCode> result) {
        this.result = result;
    }

}
