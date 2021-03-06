package com.test.postcode.testcases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import org.apache.log4j.Logger;
import org.junit.Test;
import com.google.gson.Gson;
import com.test.postcode.common.Constants;
import com.test.postcode.common.Validator;
import com.test.postcode.model.post.PostResultsForPostCode;
import com.test.postcode.service.CreatePostCodeService;

public class PostalCodePostTest extends CreatePostCodeService {

    final static Logger log = Logger.getLogger(PostalCodePostTest.class);

    Validator validate = new Validator();

    /*
     * Below test validate the successful scenario.
     */
    @Test
    public void test_postPostalcode_success() {
        ArrayList<String> postcodeList = new ArrayList<String>();
        postcodeList.add("S56UR");

        // construct url and render random post Code
        String response = postPostalCode(Constants.GET_URL, postcodeList);

        // parse
        PostResultsForPostCode postCoderesponse = new Gson().fromJson(response, PostResultsForPostCode.class);
        log.info("postCoderesponse ->" + response);

        // assert
        assertNotNull(postCoderesponse);
        assertFalse(postCoderesponse.getResult().isEmpty());
        assertEquals(200, postCoderesponse.getStatus());

        for (int i = 0; i < postCoderesponse.getResult().size(); i++) {
            validate.validatePostCodeResults(postCoderesponse.getResult().get(i).getResult());
            validate.validatePostCodes(postCoderesponse.getResult().get(i).getResult().getCodes());

        }
    }

    /*
     * Below test validate the successful scenario. Multiple post codes
     */
    @Test
    public void test_post_multiplePostalcode_success() {
        ArrayList<String> postcodeList = new ArrayList<String>();

        // Add 3 post codes in list
        postcodeList.add("S56UR");
        postcodeList.add("BH6 5JZ");

        // construct url and render random post Code
        String response = postPostalCode(Constants.GET_URL, postcodeList);

        // parse
        PostResultsForPostCode postCoderesponse = new Gson().fromJson(response, PostResultsForPostCode.class);
        log.info("postCoderesponse ->" + response);

        // assert
        assertNotNull(postCoderesponse);
        assertFalse(postCoderesponse.getResult().isEmpty());
        assertEquals(200, postCoderesponse.getStatus());
        for (int i = 0; i < postCoderesponse.getResult().size(); i++) {
            validate.validatePostCodeResults(postCoderesponse.getResult().get(i).getResult());
            validate.validatePostCodes(postCoderesponse.getResult().get(i).getResult().getCodes());
            validate.validateQueryValue(postCoderesponse.getResult().get(i).getQuery(), postcodeList);

        }
    }

    /*
     * Below test validate the negative scenario.Invalid post code
     */
    @Test
    public void test_postPostalcode_invalid() {
        ArrayList<String> postcodeList = new ArrayList<String>();

        // Add 3 post codes in list
        postcodeList.add(Constants.INVALID_POST_CODE);

        // construct url and render random post Code
        String response = postPostalCode(Constants.GET_URL, postcodeList);

        // parse
        PostResultsForPostCode postCoderesponse = new Gson().fromJson(response, PostResultsForPostCode.class);
        log.info("postCoderesponse ->" + response);

        // assert
        assertNotNull(postCoderesponse);
        assertFalse(postCoderesponse.getResult().isEmpty());
        assertEquals(200, postCoderesponse.getStatus());
        for (int i = 0; i < postCoderesponse.getResult().size(); i++) {
            if (null != postCoderesponse.getResult().get(i).getResult()) {
                assertNull(postCoderesponse.getResult().get(i).getResult());
                validate.validateQueryValue(postCoderesponse.getResult().get(i).getQuery(), postcodeList);
            } else {
                log.info("No data restults retreived for the postCode ->"
                        + postCoderesponse.getResult().get(i).getQuery());
            }
        }
    }

    /*
     * Below test validate the negative scenario.empty post code
     */
    @Test
    public void test_postPostalcode_empty() {
        ArrayList<String> postcodeList = new ArrayList<String>();

        // construct url and render random post Code
        String response = postPostalCode(Constants.GET_URL, postcodeList);

        // parse
        PostResultsForPostCode postCoderesponse = new Gson().fromJson(response, PostResultsForPostCode.class);
        log.info("postCoderesponse ->" + response);

        // assert
        assertNotNull(postCoderesponse);
        assertEquals(200, postCoderesponse.getStatus());
        for (int i = 0; i < postCoderesponse.getResult().size(); i++) {
            assertTrue(postCoderesponse.getResult().isEmpty());
        }
    }
}
