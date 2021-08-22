package com.service;

import com.exceptions.TokopediaException;
import com.models.Visit;

public interface IVisitReaderService {
    Visit readVisitCount(String pageId, String fetchMode
            , Integer month, Integer year) throws TokopediaException;
}
