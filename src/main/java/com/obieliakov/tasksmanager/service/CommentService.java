package com.obieliakov.tasksmanager.service;

import com.obieliakov.tasksmanager.dto.comment.CommentDto;
import com.obieliakov.tasksmanager.dto.comment.NewCommentDto;
import com.obieliakov.tasksmanager.dto.comment.UpdateCommentDto;

public interface CommentService {

    CommentDto commentById(Long id);

    CommentDto createComment(NewCommentDto newCommentDto);

    CommentDto updateComment(UpdateCommentDto updateCommentDto);

    void deleteComment(Long id);
}
