package com.obieliakov.tasksmanager.repository;

import com.obieliakov.tasksmanager.model.Comment;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CommentRepository extends PagingAndSortingRepository<Comment, Long> {
}
