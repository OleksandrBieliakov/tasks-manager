package com.obieliakov.tasksmanager.repository;

import com.obieliakov.tasksmanager.model.AppUser;
import com.obieliakov.tasksmanager.model.Group;
import com.obieliakov.tasksmanager.model.GroupInvite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GroupInviteRepository extends JpaRepository<GroupInvite, Long> {

    Optional<GroupInvite> findByGroupAndAndToAppUserAndByAppUser(Group group, AppUser toAppUser, AppUser byAppUser);
}
