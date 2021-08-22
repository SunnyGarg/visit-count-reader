package com.utils;

import com.exceptions.TokopediaException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class ResourceValidator {

    public void validateValue(String key, String value) {
        if (Boolean.FALSE.equals(StringUtils.hasText(value))) {
            throw new TokopediaException(key + " is required.", HttpStatus.BAD_REQUEST);
        }
    }

    public void validateValue(String key, Integer value) {
        if (value == null) {
            throw new TokopediaException(key + " is required.", HttpStatus.BAD_REQUEST);
        }
        if (value <= 0) {
            throw new TokopediaException(key + " should be greater than zero.", HttpStatus.BAD_REQUEST);
        }
    }

    public void validateGetPageVisitRequest(String pageId, String fetchMode
            , Integer month, Integer year) {
        validateValue("Page Id", pageId);
        validateValue("Fetch Id", fetchMode);

        if (!fetchMode.equalsIgnoreCase("MONTH")) {
            throw new TokopediaException("Invalid Fetch mode.", HttpStatus.BAD_REQUEST);
        }

        validateValue("Month", month);
        validateValue("Year", year);

        if (month > 0 && month <= 12) {
        } else {
            throw new TokopediaException("Invalid month passed.", HttpStatus.BAD_REQUEST);
        }
    }
}
