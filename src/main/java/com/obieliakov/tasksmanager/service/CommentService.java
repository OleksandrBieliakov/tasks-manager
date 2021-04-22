package com.obieliakov.tasksmanager.service;

import com.obieliakov.tasksmanager.dto.comment.CommentDto;
import com.obieliakov.tasksmanager.dto.comment.NewCommentDto;
import com.obieliakov.tasksmanager.dto.comment.UpdateCommentDto;
import com.obieliakov.tasksmanager.model.AppUser;
import com.obieliakov.tasksmanager.model.Comment;

import java.util.UUID;

public interface CommentService {

    boolean isCommentAddedByAppUser(Comment comment, AppUser appUser);

    void verifyCommentAddedByAppUser(Comment comment, UUID appUserId);

    Comment commentModelById(Long id);

    CommentDto commentById(Long id);

    CommentDto createComment(NewCommentDto newCommentDto);

    CommentDto updateComment(Long id, UpdateCommentDto updateCommentDto);

    void deleteComment(Long id);
}
