package com.olikester.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.olikester.model.LiniusAccessToken;
import com.olikester.service.LiniusService;

/**
 * Proxy controller class for Linius REST services. 
 * 
 * Forwards requests on to our Linius service class, then returns the response back. 
 * 
 * @author Oliver Reynolds
 *
 */
@RestController
public class ProxyController {
    static final String DISCOVER_ENDPOINT = "/discover";
    static final String ENRICH_ASSETS_ENDPOINT = "/enrich/assets";
    static final String ENRICH_JOBS_ENDPOINT = "/enrich/jobs";
    static final String SEARCH_ENDPOINT = "/search";
    
    @Autowired
    private LiniusService liniusService;
    @Autowired
    private LiniusAccessToken liniusAccessToken;
    
    @GetMapping(SEARCH_ENDPOINT)
    public ResponseEntity<String> search(@RequestParam MultiValueMap<String, String> requestParams){
	return liniusService.search(liniusAccessToken, requestParams);
    }        
}
