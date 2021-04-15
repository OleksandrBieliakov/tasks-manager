package com.obieliakov.tasksmanager.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "app_user",
        uniqueConstraints = {@UniqueConstraint(
                name = "unique_app_user_login_name",
                columnNames = {"login_name"})})
@Getter
@Setter
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AppUser {

    public static final int NAMES_MIN_LENGTH = 1;
    public static final int NAMES_MAX_LENGTH = 50;
    public static final int EMAIL_MAX_LENGTH = 320;

    @Id
    private UUID id;

    @Column(name = "login_name", nullable = false, length = NAMES_MAX_LENGTH)
    private String loginName;

    @Column(name="email", nullable = false, length = EMAIL_MAX_LENGTH)
    private String email;

    @Column(name = "first_name", nullable = false, length = NAMES_MAX_LENGTH)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = NAMES_MAX_LENGTH)
    private String lastName;

    @Column(name = "public_email", nullable = false)
    private Boolean publicEmail;

    @Column(name = "public_first_last_name", nullable = false)
    private Boolean publicFirstLastName;

    @Column(name = "time_registered", nullable = false)
    private ZonedDateTime timeRegistered = ZonedDateTime.now();

    @OneToMany(mappedBy = "appUser")
    private List<GroupMembership> groupMemberships;

    @OneToMany(mappedBy = "addedBy")
    private List<Task> tasksAdded;

    @OneToMany(mappedBy = "assignedBy")
    private List<Assignment> assignmentsMade;

    @OneToMany(mappedBy = "assignedTo")
    private List<Assignment> assignments;

    @OneToMany(mappedBy = "byAppUser")
    private List<GroupInvite> groupInvitesSent;

    @OneToMany(mappedBy = "toAppUser")
    private List<GroupInvite> groupInvitesReceived;

    @OneToMany(mappedBy = "updatedBy")
    private List<StatusUpdate> statusesUpdated;

    @OneToMany(mappedBy = "addedBy")
    private List<Comment> commentsAdded;
}
