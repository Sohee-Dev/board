package com.example.board.common.util;

import org.springframework.stereotype.Component;

import com.example.board.auth.dto.UserDTO;
import com.example.board.auth.entity.User;
import com.example.board.board.dto.BoardDTO;
import com.example.board.board.entity.Board;

@Component
public class EntityDtoMapper {

    public UserDTO toUserDTO(User user) {
        return UserDTO.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .name(user.getName())
                .password(user.getPassword())
                .build();
    }

    public User toUser(UserDTO userDTO) {
        return User.builder()
                .userId(userDTO.getUserId())
                .username(userDTO.getUsername())
                .name(userDTO.getName())
                .password(userDTO.getPassword())
                .build();
    }

    public BoardDTO toBoardDTO(Board board) {
        return BoardDTO.builder()
                .bno(board.getBno())
                .title(board.getTitle())
                .content(board.getContent())
                .writer(board.getUser().getUserId())
                .build();
    }

    public Board toBoard(BoardDTO boardDTO) {
        return Board.builder()
                .bno(boardDTO.getBno())
                .title(boardDTO.getTitle())
                .content(boardDTO.getContent())
                .build();
    }
}
