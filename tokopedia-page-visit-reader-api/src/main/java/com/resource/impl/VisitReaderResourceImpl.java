package com.resource.impl;

import com.exceptions.TokopediaException;
import com.models.ServiceResponse;
import com.resource.VisitReaderResource;
import com.service.IVisitReaderService;
import com.utils.Constants;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Visit Read Resource")
public class VisitReaderResourceImpl implements VisitReaderResource {
    private final Logger logger = LoggerFactory.getLogger(VisitReaderResourceImpl.class);

    private final IVisitReaderService visitReaderService;

    public VisitReaderResourceImpl(@Autowired IVisitReaderService visitReaderService) {
        this.visitReaderService = visitReaderService;
    }

    @Tag(name = "Visit Read Resource")
    @Override
    public ResponseEntity<ServiceResponse> storeVisit(HttpHeaders httpHeaders, String pageId, String fetchMode, Integer month, Integer year) {
        try {
            return ResponseEntity.accepted().body(ServiceResponse.builder()
                    .status(Constants.RESPONSE_STATUS.SUCCESS.name())
                    .data(visitReaderService.readVisitCount(pageId, fetchMode, month, year))
                    .build());
        } catch (TokopediaException tokopediaException) {
            logger.error("Error while storing visitor's visit.", tokopediaException);
            return new ResponseEntity<>(ServiceResponse.builder()
                    .status(Constants.RESPONSE_STATUS.FAILURE.name())
                    .data(tokopediaException.getMessage())
                    .build(), tokopediaException.getHttpStatusCode());
        }
    }
}
