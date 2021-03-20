package com.obieliakov.tasksmanager.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.Set;

@Entity
@Table(name = "app_user",
        uniqueConstraints = {@UniqueConstraint(
                name = "unique_app_user_login_name",
                columnNames = {"login_name"})})
@Data
@ToString
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login_name", nullable = false)
    private String loginName;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "time_registered", nullable = false)
    private ZonedDateTime timeRegistered = ZonedDateTime.now();

    @OneToMany(mappedBy = "user")
    private Set<GroupMembership> groupMemberships;

    @OneToMany(mappedBy = "addedBy")
    private Set<Task> tasksAdded;

    @OneToMany(mappedBy = "assignedBy")
    private Set<Assignment> assignmentsMade;

    @OneToMany(mappedBy = "assignedTo")
    private Set<Assignment> assignments;

    @OneToMany(mappedBy = "byUser")
    private Set<GroupInvite> groupInvitesSent;

    @OneToMany(mappedBy = "toUser")
    private Set<GroupInvite> groupInvitesReceived;

    @OneToMany(mappedBy = "updatedBy")
    private Set<StatusUpdate> statusesUpdated;

    @OneToMany(mappedBy = "addedBy")
    private Set<Comment> commentsAdded;
}
