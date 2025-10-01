package com.example.board.board.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.board.board.dto.BoardDTO;
import com.example.board.board.entity.Board;
import com.example.board.board.repository.BoardRepository;
import com.example.board.common.util.EntityDtoMapper;

@Service
public class BoardService {

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    EntityDtoMapper entityDtoMapper;

    public List<BoardDTO> getAllList() {
        List<Board> listBoard = boardRepository.findAll();
        List<BoardDTO> list = new ArrayList<>();

        for (Board b : listBoard) { // BoardDTO로 변환
            BoardDTO boardDTO = entityDtoMapper.toBoardDTO(b);
            list.add(boardDTO);
        }

        return list;
    }
}
