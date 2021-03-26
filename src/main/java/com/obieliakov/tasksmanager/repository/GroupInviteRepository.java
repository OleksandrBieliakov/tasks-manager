package com.obieliakov.tasksmanager.repository;

import com.obieliakov.tasksmanager.model.GroupInvite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupInviteRepository extends JpaRepository<GroupInvite, Long> {
}
