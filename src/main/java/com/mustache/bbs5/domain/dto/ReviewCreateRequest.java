package com.mustache.bbs5.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ReviewCreateRequest {
    private Integer hospitalId;
    private String userName;
    private String title;
    private String content;
}
