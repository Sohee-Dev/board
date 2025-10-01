package com.example.board.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.board.board.dto.BoardDTO;
import com.example.board.board.service.BoardService;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    BoardService boardService;

    @GetMapping("/list")
    public String boardList(Model model) {
        List<BoardDTO> boardList = boardService.getAllList();
        model.addAttribute("boardList", boardList);

        return "board/boardIndex";
    }

}
