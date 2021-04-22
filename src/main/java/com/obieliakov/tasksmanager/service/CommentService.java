package com.obieliakov.tasksmanager.service;

import com.obieliakov.tasksmanager.dto.comment.CommentDto;
import com.obieliakov.tasksmanager.dto.comment.NewCommentDto;
import com.obieliakov.tasksmanager.dto.comment.UpdateCommentDto;
import com.obieliakov.tasksmanager.model.Comment;

public interface CommentService {

    Comment commentModelById(Long id);

    CommentDto commentById(Long id);

    CommentDto createComment(NewCommentDto newCommentDto);

    CommentDto updateComment(UpdateCommentDto updateCommentDto);

    void deleteComment(Long id);
}
