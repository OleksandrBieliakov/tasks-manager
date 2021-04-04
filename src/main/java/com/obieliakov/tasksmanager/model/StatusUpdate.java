package com.obieliakov.tasksmanager.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table(name = "status_update")
@Data
@ToString
public class StatusUpdate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = Task.STATUS_LENGTH)
    private TaskStatus status;

    @Column(name = "time_updated", nullable = false)
    private ZonedDateTime timeUpdated = ZonedDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "task_id", foreignKey = @ForeignKey(name = "fk_status_update_task"))
    private Task task;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "updated_by_app_user_id", foreignKey = @ForeignKey(name = "fk_status_update_app_user_updated"))
    private AppUser updatedBy;
}
