package com.obieliakov.tasksmanager.mapper;

import com.obieliakov.tasksmanager.dto.comment.CommentDto;
import com.obieliakov.tasksmanager.dto.comment.CommentShortDto;
import com.obieliakov.tasksmanager.model.Comment;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
        uses = AppUserWithPrivacyMapper.class,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CommentMapper {

    CommentDto commentToCommentDto(Comment comment);

    CommentShortDto commentToCommentShortDto(Comment comment);
}
