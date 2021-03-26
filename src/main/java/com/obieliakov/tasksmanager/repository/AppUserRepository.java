package com.obieliakov.tasksmanager.repository;

import com.obieliakov.tasksmanager.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
}
