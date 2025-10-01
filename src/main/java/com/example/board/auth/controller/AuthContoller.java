package com.example.board.auth.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.board.auth.dto.UserDTO;
import com.example.board.auth.service.AuthService;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/")
public class AuthContoller {
    // 회원가입 진행
    // 아이디 - 중복검사
    // 비밀번호 - 암호화
    // 이름

    @Autowired
    AuthService authService;

    @GetMapping("/joinPg")
    public String joinForm() {
        return "joinPg";
    }

    @GetMapping("/loginPg")
    public String loginForm() {
        return "loginPg";
    }

    @PostMapping("/join")
    public String join(UserDTO userDTO, RedirectAttributes rttr) {
        String msg = "";
        if (authService.join(userDTO)) {
            msg = "회원가입이 완료되었습니다";
            rttr.addFlashAttribute("msg", msg);
            return "redirect:/loginPg";
        } else {
            msg = "다시 시도해주세요";
            rttr.addFlashAttribute("msg", msg);
        }

        return "redirect:/joinForm";
    }

    @PostMapping("/join/checkId")
    public ResponseEntity<Map<String, String>> checkId(@RequestParam("username") String username) {
        Map<String, String> body = new HashMap<>();

        if (!authService.checkId(username)) {
            body.put("msg", "사용가능한 아이디 입니다");
            return ResponseEntity.ok(body); // 200
        } else {
            body.put("msg", "중복된 아이디 입니다");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(body); // 중복, 409리턴
        }
    }

    @PostMapping("/login")
    public String login(UserDTO userDTO, HttpSession session, RedirectAttributes rttr) {
        String msg = "";
        UserDTO user = authService.login(userDTO);

        if (user != null) {
            session.setAttribute("userId", user.getUserId());
            session.setAttribute("name", user.getName());
            return "redirect:/";
        } else {
            msg = "아이디 및 비밀번호를 확인해주세요";
            rttr.addFlashAttribute("msg", msg);
        }
        return "redirect:/loginPg";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");

        if (userId != null) {
            session.removeAttribute("userId");
            session.removeAttribute("name");
        }

        return "redirect:/";
    }

}
