package com.resource;

import com.models.ServiceResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/v1/api")
public interface VisitReaderResource {
    @ApiOperation(value = "Reads visitor's visit to page.", response = ResponseEntity.class)
    @GetMapping(value = "/visit", produces = "application/json")
    ResponseEntity<ServiceResponse> storeVisit(@RequestHeader HttpHeaders httpHeaders
            , @RequestParam("page_id") String pageId
            , @RequestParam("fetch_mode") String fetchMode
            , @RequestParam("month") Integer month
            , @RequestParam("year") Integer year);
}

