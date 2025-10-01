package com.example.board.board.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.board.auth.entity.User;
import com.example.board.auth.repository.UserRepository;
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

    @Autowired
    UserRepository userRepository;

    public List<BoardDTO> getAllList() {
        List<Board> listBoard = boardRepository.findAll();
        List<BoardDTO> list = new ArrayList<>();

        for (Board b : listBoard) { // BoardDTO로 변환
            BoardDTO boardDTO = entityDtoMapper.toBoardDTO(b);
            list.add(boardDTO);
        }

        return list;
    }

    public String newBoard(BoardDTO boardDTO) {
        Board board = entityDtoMapper.toBoard(boardDTO);

        if (board != null) {
            Optional<User> op_user = userRepository.findById(boardDTO.getWriter());
            User user = op_user.get();
            board.setUser(user);
            boardRepository.save(board);
            return "게시글 등록이 완료되었습니다";
        }
        return "오류 발생";
    }

    public BoardDTO boardDetail(Long bno) {
        Optional<Board> op_board = boardRepository.findById(bno);
        Board board = op_board.get();

        BoardDTO boardDTO = entityDtoMapper.toBoardDTO(board);
        return boardDTO;
    }

    public String boardUpdate(BoardDTO boardDTO) {
        Board board = entityDtoMapper.toBoard(boardDTO);

        if (board != null) {
            Optional<User> op_user = userRepository.findById(boardDTO.getWriter());
            User user = op_user.get();
            board.setUser(user);
            boardRepository.save(board);
            return "게시글 수정이 완료되었습니다";
        }
        return "오류 발생";
    }

    public String boardDelete(Long bno) {
        if (bno != null) {
            boardRepository.deleteById(bno);
            return "게시글 삭제 완료";
        }
        return "삭제 할 수 없습니다";
    }

}
