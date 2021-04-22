package com.obieliakov.tasksmanager.controller;

import com.obieliakov.tasksmanager.dto.comment.CommentDto;
import com.obieliakov.tasksmanager.dto.comment.NewCommentDto;
import com.obieliakov.tasksmanager.service.CommentService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/comments")
@SecurityRequirement(name = "identity")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("{id}")
    public CommentDto commentById(@PathVariable Long id) {
        return commentService.commentById(id);
    }

    @PostMapping
    public CommentDto createComment(@RequestBody NewCommentDto newCommentDto) {
        return commentService.createComment(newCommentDto);
    }
}
