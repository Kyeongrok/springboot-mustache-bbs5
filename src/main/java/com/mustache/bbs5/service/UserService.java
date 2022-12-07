package com.mustache.bbs5.service;

import com.mustache.bbs5.domain.User;
import com.mustache.bbs5.domain.dto.UserDto;
import com.mustache.bbs5.enums.UserRole;
import com.mustache.bbs5.exceptions.ErrorCode;
import com.mustache.bbs5.exceptions.HospitalReviewAppException;
import com.mustache.bbs5.repository.UserRepository;
import com.mustache.bbs5.utils.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    public User getUserByUserName(String userName) {
        User user = userRepository.findByUserName(userName)
                .orElseThrow(() -> new HospitalReviewAppException(ErrorCode.USER_NOT_FOUNDED, ""));
        return user;
    }

    @Value("${jwt.token.secret}")
    private String secretKey;



    private Long expiredTimeMs = 1000 * 60 * 60l;


    public UserDto join(String userName, String password) {
        // 중복 Check
        userRepository.findByUserName(userName).ifPresent(user -> {
            throw new HospitalReviewAppException(ErrorCode.DUPLICATED_USER_NAME, String.format("UserName %s is duplicated", userName));
        });

        // 위에서 에러가 안났다면 회원가입(DB에 저장)
        User user = User.builder()
                .userName(userName)
                .password(encoder.encode(password))
                .role(UserRole.USER)
                .build();

        User savedUser = userRepository.save(user);

        return UserDto.fromEntity(savedUser);
    }
    public String login(String userName, String password) {
        return JwtTokenUtil.createToken(userName, secretKey, expiredTimeMs);
    }
}

