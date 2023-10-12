package com.app.bolt.security.controller;

import com.app.bolt.security.dto.MemberLoginDto;
import com.app.bolt.security.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class LoginController {
    private final MemberService memberService;

    public LoginController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/login-process")
    public String login(MemberLoginDto dto) {
        boolean isValidMember = memberService.isValidMember(dto.getUserid(), dto.getPw());
        if (isValidMember)
            return "dashboard";
        return "login";
    }

    @PostMapping("/logout")
    public String logout() {
        return "login";
    }
}