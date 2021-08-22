package com.service.impl;

import com.exceptions.TokopediaException;
import com.models.Visit;
import com.service.IVisitReaderService;
import com.utils.ResourceValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class VisitReaderServiceImpl implements IVisitReaderService {
    private ResourceValidator resourceValidator;
    private RedisTemplate<String, Object> redisTemplate;

    private static final String UNDER_SCORE = "_";
    private static final String VISIT_SUFFIX = "visit";
    private static final String VISIT_COUNT_TEXT = "visit_count";

    public VisitReaderServiceImpl(@Autowired RedisTemplate<String, Object> redisTemplate
            , @Autowired ResourceValidator resourceValidator) {
        this.redisTemplate = redisTemplate;
        this.resourceValidator = resourceValidator;
    }

    @Override
    public Visit readVisitCount(String pageId, String fetchMode
            , Integer month, Integer year) throws TokopediaException {
        resourceValidator.validateGetPageVisitRequest(pageId
                , fetchMode, month, year);

        Long visitCount = 0L;

        String hash = new StringBuffer(pageId).append(UNDER_SCORE)
                .append("" + year + (month < 10 ? "0" + month : month)).append(UNDER_SCORE).append(VISIT_SUFFIX).toString();
        String hashKey = VISIT_COUNT_TEXT;

        if (redisTemplate.opsForHash().hasKey(hash, hashKey)) {
            visitCount = (Long) redisTemplate.opsForHash().get(hash, hashKey);
        }
        return Visit.builder()
                .pageId(pageId)
                .month(month)
                .year(year)
                .visitCount(visitCount)
                .build();
    }
}
