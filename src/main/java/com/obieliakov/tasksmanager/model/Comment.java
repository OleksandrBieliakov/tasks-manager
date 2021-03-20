package com.obieliakov.tasksmanager.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table(name = "comment")
@Data
@ToString
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "message")
    private String message;

    @Column(name = "time_added", nullable = false)
    private ZonedDateTime timeAdded = ZonedDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "task_id", foreignKey = @ForeignKey(name = "fk_comment_task"))
    private Task task;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "added_by_app_user_id", foreignKey = @ForeignKey(name = "fk_comment_app_user_added"))
    private AppUser addedBy;
}
