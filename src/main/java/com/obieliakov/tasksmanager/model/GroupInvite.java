package com.obieliakov.tasksmanager.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table(name = "group_invite",
        uniqueConstraints = {@UniqueConstraint(
                name = "unique_invite_group_by_user_to_user",
                columnNames = {"group_id", "by_app_user_id", "to_app_user_id"})})
@Getter
@Setter
public class GroupInvite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "time_created", nullable = false)
    private ZonedDateTime timeCreated = ZonedDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "group_id", foreignKey = @ForeignKey(name = "fk_invite_group"))
    private Group group;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "by_app_user_id", foreignKey = @ForeignKey(name = "fk_invite_by_user"))
    private AppUser byAppUser;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "to_app_user_id", foreignKey = @ForeignKey(name = "fk_invite_to_user"))
    private AppUser toAppUser;
}
