package com.obieliakov.tasksmanager.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.List;

@Entity
@Table(name = "user_group")
@Getter
@Setter
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Group {

    public static final int NAME_MIN_LENGTH = 1;
    public static final int NAME_MAX_LENGTH = 100;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "g_name", nullable = false, length = NAME_MAX_LENGTH)
    private String name;

    @Column(name = "time_created", nullable = false)
    private ZonedDateTime timeCreated = ZonedDateTime.now();

    @OneToMany(mappedBy = "group")
    private List<GroupMembership> groupMemberships;

    @OneToMany(mappedBy = "group")
    private List<Role> roles;

    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @OneToMany(mappedBy = "group")
    private List<Task> tasks;

    @OneToMany(mappedBy = "group")
    private List<GroupInvite> invites;

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
