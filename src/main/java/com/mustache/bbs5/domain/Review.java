package com.mustache.bbs5.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="hospital_id")
    private Integer hospitalId;
    private String title;
    private String content;
    private String userName;

    public static Review of(Integer hospitalId, String title, String content, String userName) {
        Review review = new Review();
        review.setHospitalId(hospitalId);
        review.setTitle(title);
        review.setContent(content);
        review.setUserName(userName);
        return review;
    }
}