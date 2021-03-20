package com.obieliakov.tasksmanager.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.Set;

@Entity
@Table(name = "task")
@Data
@ToString
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "time_added", nullable = false)
    private ZonedDateTime timeAdded = ZonedDateTime.now();

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private TaskStatus status = TaskStatus.PENDING;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "group_id", foreignKey = @ForeignKey(name = "fk_task_group"))
    private Group group;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "added_by_app_user_id", foreignKey = @ForeignKey(name = "fk_task_app_user_added"))
    private AppUser addedBy;

    @OneToMany(mappedBy = "task")
    private Set<Assignment> assignments;

    @OneToMany(mappedBy = "task")
    private Set<StatusUpdate> statusUpdates;

    @OneToMany(mappedBy = "task")
    private Set<Comment> comments;
}
