package com.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Visit {
    private String pageId;
    private Integer month;
    private Integer year;
    private Long visitCount;
}
