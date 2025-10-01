package com.example.board.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.board.board.dto.BoardDTO;
import com.example.board.board.service.BoardService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @GetMapping("/newBoardPg")
    public String newBoardPg() {

        return "board/newBoard";
    }

    @PostMapping("/newBoard")
    public String newBoard(BoardDTO boardDTO, Model model) {
        String msg = boardService.newBoard(boardDTO);
        model.addAttribute("msg", msg);
        return "redirect:/board/list";
    }

    @GetMapping("/detail")
    public String getMethodName(@RequestParam Long bno, Model model) {

        return new String();
    }

}
