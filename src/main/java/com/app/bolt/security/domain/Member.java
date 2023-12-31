package com.app.bolt.security.domain;

import jakarta.persistence.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String userid;

    private String pw;

    //추후에 여러 권한일 시에는 배열로
    private String roles;

    private Member(Long id, String userid, String pw, String roleUser) {
        this.id = id;
        this.userid = userid;
        this.pw = pw;
        this.roles = roleUser;
    }

    protected Member() {}

    public static Member createUser(String userId, String pw, PasswordEncoder passwordEncoder) {

        return new Member(null, userId, passwordEncoder.encode(pw), "USER");
    }

    public Long getId() {
        return id;
    }

    public String getUserid() {
        return userid;
    }

    public String getPw() {
        return pw;
    }

    public String getRoles() {
        return roles;
    }
}