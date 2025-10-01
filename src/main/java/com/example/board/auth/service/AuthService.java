package com.example.board.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.board.auth.dto.UserDTO;
import com.example.board.auth.entity.User;
import com.example.board.auth.repository.UserRepository;
import com.example.board.common.util.EntityDtoMapper;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntityDtoMapper entityDtoMapper;

    // 중복아이디면 true, 아니면 false 반환
    public boolean checkId(String username) {
        return userRepository.existsByUsername(username);
    }

    // 회원가입
    public boolean join(UserDTO userDTO) {
        boolean flag = false;
        User userMapped = entityDtoMapper.toUser(userDTO);

        try {
            userRepository.save(userMapped);
            flag = true;
        } catch (Exception e) {
            flag = false;
        }

        return flag;
    }

    // 로그인
    public UserDTO login(UserDTO userDTO) {

        User userMapped = entityDtoMapper.toUser(userDTO);
        User user = userRepository.findByUsernameAndPassword(userMapped.getUsername(), userMapped.getPassword());

        if (user == null) {
            return null;
        }

        return entityDtoMapper.toUserDTO(user);
    }
}
