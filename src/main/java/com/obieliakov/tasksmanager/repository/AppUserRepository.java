package com.obieliakov.tasksmanager.repository;

import com.obieliakov.tasksmanager.model.AppUser;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AppUserRepository extends PagingAndSortingRepository<AppUser, Long> {
}
