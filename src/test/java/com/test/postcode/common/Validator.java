package com.test.postcode.common;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import org.apache.log4j.Logger;

import com.test.postcode.model.Codes;
import com.test.postcode.model.Result;
import com.test.postcode.model.post.Query;

public class Validator {
    final static Logger log = Logger.getLogger(Validator.class);

    public void validatePostCodeResults(Result results) {
        log.info("Attempting to validate the PostCodeResults");

        if(null!=results) {
            assertNotNull(results.getPostcode());
            assertNotNull(results.getQuality());
            assertNotNull(results.getEastings());
            assertNotNull(results.getNorthings());
            assertNotNull(results.getCountry());
            assertNotNull(results.getNhs_ha());
            assertNotNull(results.getLongitude());
            assertNotNull(results.getLatitude());
            assertNotNull(results.getEuropean_electoral_region());
            assertNotNull(results.getPrimary_care_trust());
            assertTrue(null != results.getRegion() || null == results.getRegion());
            assertNotNull(results.getLsoa());
            assertNotNull(results.getMsoa());
            assertNotNull(results.getIncode());
            assertNotNull(results.getOutcode());
            assertNotNull(results.getParliamentary_constituency());
            assertTrue(null != results.getParish() || null == results.getParish());
            assertNotNull(results.getAdmin_district());
            assertNotNull(results.getAdmin_ward());
            assertNotNull(results.getCcg());
            assertNotNull(results.getNuts());
        } else {
            log.info("Result dont have values ***");
        }
    }

    public void validatePostCodes(Codes codes) {
        log.info("Attempting to validate the PostCodes");

        if(null != codes) {
            assertNotNull(codes.getAdmin_district());
            assertNotNull(codes.getAdmin_county());
            assertNotNull(codes.getAdmin_ward());
            assertNotNull(codes.getParliamentary_constituency());
            assertNotNull(codes.getParish());
            assertNotNull(codes.getCcg());
            assertNotNull(codes.getCcg_id());
            assertNotNull(codes.getCed());
            assertNotNull(codes.getNuts());
        } else {
            log.info("Codes dont have values ***");
        }
    }

    public void validateQueryValue(String queryName, List<String> postCodeList) {
        if(null!=queryName) {
            boolean queryMatch = postCodeList.stream().
                    anyMatch(p -> p.equalsIgnoreCase(queryName));
            assertTrue(queryMatch);
        } else {
            log.info("postCode Query dont have values ***");
        }
    }
    
    public void validateGeoQuery(Query query) {
        if(null !=query) {
            assertNotNull(query.getLatitude());
            assertNotNull(query.getLongitude());
        }else {
            log.info("Geo Query dont have values ***");
        }
    }
}
