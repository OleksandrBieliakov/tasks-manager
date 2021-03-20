package com.obieliakov.tasksmanager.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.Set;

@Entity
@Table(name = "user_group")
@Data
@ToString
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "g_name", nullable = false)
    private String name;

    @Column(name = "time_created", nullable = false)
    private ZonedDateTime timeCreated = ZonedDateTime.now();

    @OneToMany(mappedBy = "group")
    private Set<GroupMembership> groupMemberships;

    @OneToMany(mappedBy = "group")
    private Set<Role> roles;

    @OneToMany(mappedBy = "group")
    private Set<Task> tasks;

    @OneToMany(mappedBy = "group")
    private Set<GroupInvite> invites;

    /*
    If there were no additional columns in the table 'group_membership' then it would be possible to create many to
    many relationship without creating the separate GroupMembership model as follows. It would allow to directly use
    a collection of user's groups from the AppUser model and a collection of group members from the Group model.

    @ManyToMany
    @JoinTable(name = "group_membership",
            joinColumns = {@JoinColumn(name = "group_id", foreignKey = @ForeignKey(name = "fk_group_app_user"))},
            inverseJoinColumns = {@JoinColumn(name = "app_user_id", foreignKey = @ForeignKey(name = "fk_app_user_group"))})
    private Set<AppUser> users;
    */
}
