package com.olikester.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.olikester.service.LiniusService;

import reactor.core.publisher.Mono;

@RestController
public class ProxyController {
    static final String DISCOVER_ENDPOINT = "/discover";
    static final String ENRICH_ASSETS_ENDPOINT = "/enrich/assets";
    static final String ENRICH_JOBS_ENDPOINT = "/enrich/jobs";
    static final String SEARCH_ENDPOINT = "/search";
    
    @Autowired
    private LiniusService liniusService;
    
    @GetMapping(SEARCH_ENDPOINT)
    public Mono<ResponseEntity<String>> search(@RequestParam Map<String, String> requestParams){
	return liniusService.search(null, requestParams);
    }        
}
