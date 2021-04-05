package com.obieliakov.tasksmanager.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table(name = "assignment",
        uniqueConstraints = {@UniqueConstraint(
                name = "unique_assignment_task_to",
                columnNames = {"task_id", "assigned_to_app_user_id"})})
@Getter
@Setter
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "time_assigned", nullable = false)
    private ZonedDateTime timeAssigned = ZonedDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "task_id", foreignKey = @ForeignKey(name = "fk_assignment_task"))
    private Task task;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "assigned_by_app_user_id", foreignKey = @ForeignKey(name = "fk_assignment_user_by"))
    private AppUser assignedBy;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "assigned_to_app_user_id", foreignKey = @ForeignKey(name = "fk_assignment_user_to"))
    private AppUser assignedTo;
}
