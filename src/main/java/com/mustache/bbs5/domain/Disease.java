package com.mustache.bbs5.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Disease {
    @Id
    private String code;
    private String name;
}
