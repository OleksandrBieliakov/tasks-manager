package com.obieliakov.tasksmanager.model;


import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "role",
        uniqueConstraints = {@UniqueConstraint(
                name = "unique_role_title_group",
                columnNames = {"title", "group_id"})})
@Data
@ToString
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "group_id", foreignKey = @ForeignKey(name = "fk_role_group"))
    private Group group;

    @OneToMany(mappedBy = "role")
    private Set<GroupMembership> groupMemberships;
}
