package com.app.bolt.security.service;

import com.app.bolt.security.domain.Member;
import com.app.bolt.security.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterMemberService {
    private final MemberRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegisterMemberService(PasswordEncoder passwordEncoder, MemberRepository repository) {
        this.passwordEncoder = passwordEncoder;
        this.repository = repository;
    }

    public Long join(String userid, String pw) {
        Member member = Member.createUser(userid, pw, passwordEncoder);
        validateDuplicateMember(member);
        repository.save(member);

        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        repository.findByUserid(member.getUserid())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }
}