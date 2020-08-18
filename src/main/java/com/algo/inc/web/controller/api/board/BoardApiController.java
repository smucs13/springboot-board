package com.algo.inc.web.controller.api.board;

import com.algo.inc.web.dto.board.BoardResponseDto;
import com.algo.inc.web.dto.board.BoardSaveRequestDto;
import com.algo.inc.web.dto.board.BoardUpdateRequestDto;
import com.algo.inc.web.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/api/board")
public class BoardApiController {

    private final BoardService boardService;

    // Read All, 게시글 전체 불러오기
    @GetMapping("/getBoardList")
    public List<BoardResponseDto> getBoardList() {
        return boardService.getBoardList();
    }

    // Read, id로 게시글 불러오기, 댓글도 같이 BoardResponseDto 안에 list로 불러오기
    @GetMapping("/{id}")
    public BoardResponseDto findById(@PathVariable Long id) {
        return boardService.findById(id);
    }

    // Create, 게시글 생성
    @PostMapping
    public Long registerBoard(@RequestBody BoardSaveRequestDto boardSaveRequestDto, Authentication authentication) {
        return boardService.registerBoard(boardSaveRequestDto, (UserDetails) authentication.getPrincipal());
    }

    // Update, 게시글 수정, 세션 필요 없음
    @PutMapping("/{id}")
    public void updateBoard(@PathVariable Long id,
                            @RequestBody BoardUpdateRequestDto boardUpdateRequestDto) {
        boardService.updateBaord(id, boardUpdateRequestDto);
    }

    // Delete, 게시글 삭제, TODO : 댓글도 같이 사라지는지 확인
   @DeleteMapping("/{id}")
    public void deleteBoardById(@PathVariable Long id){
        boardService.deleteBoardById(id);
   }
}
