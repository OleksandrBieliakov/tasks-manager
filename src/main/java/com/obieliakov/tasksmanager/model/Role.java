package com.obieliakov.tasksmanager.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "role",
        uniqueConstraints = {@UniqueConstraint(
                name = "unique_role_title_group",
                columnNames = {"title", "group_id"})})
@Getter
@Setter
public class Role {

    public static final int TITLE_MIN_LENGTH = 1;
    public static final int TITLE_MAX_LENGTH = 100;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "group_id", foreignKey = @ForeignKey(name = "fk_role_group"))
    private Group group;

    /*@OneToMany(mappedBy = "role")
    private List<GroupMembership> groupMemberships;*/

    @ManyToMany(mappedBy = "roles")
    private List<GroupMembership> groupMemberships;
}
