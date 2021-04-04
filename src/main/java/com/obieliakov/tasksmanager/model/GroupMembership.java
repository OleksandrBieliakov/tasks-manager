package com.obieliakov.tasksmanager.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table(name = "group_membership",
        uniqueConstraints = {@UniqueConstraint(
                name = "unique_membership_group_user",
                columnNames = {"group_id", "app_user_id"})})
@Data
@ToString
public class GroupMembership {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "active", nullable = false)
    private boolean active = true;

    @Column(name = "first_time_joined", nullable = false)
    private ZonedDateTime firstTimeJoined = ZonedDateTime.now();

    @Column(name = "last_time_joined", nullable = false)
    private ZonedDateTime lastTimeJoined = ZonedDateTime.now();

    @Column(name = "last_time_left")
    private ZonedDateTime lastTimeLeft;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "group_id", foreignKey = @ForeignKey(name = "fk_membership_group"))
    private Group group;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "app_user_id", foreignKey = @ForeignKey(name = "fk_membership_user"))
    private AppUser appUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", foreignKey = @ForeignKey(name = "fk_membership_role"))
    private Role role;
}
