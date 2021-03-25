package com.obieliakov.tasksmanager.dataloader;

import com.obieliakov.tasksmanager.model.AppUser;
import com.obieliakov.tasksmanager.repository.AppUserRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AppUsersLoader implements Loader<AppUser> {

    public static final String LOGIN_NAME = "login_name";
    public static final String FIRST_NAME = "first_name";
    public static final String LAST_NAME = "last_name";

    private final AppUserRepository repository;

    public AppUsersLoader(AppUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<AppUser> generate(String tag, int number, int from) {
        List<AppUser> users = new ArrayList<>();
        for (int i = from; i < from + number; i++) {
            AppUser user = new AppUser();
            user.setLoginName(format(tag, LOGIN_NAME, i));
            users.add(user);
        }
        return users;
    }

    @Override
    public void load(List<AppUser> entities) {
        repository.saveAll(entities);
    }

    public void setFirstNames(List<AppUser> users, String tag, int from) {
        int number = from;
        for (AppUser appUser : users) {
            appUser.setFirstName(format(tag, FIRST_NAME, number));
            number++;
        }
    }

    public void setLastNames(List<AppUser> users, String tag, int from) {
        int number = from;
        for (AppUser appUser : users) {
            appUser.setLastName(format(tag, LAST_NAME, number));
            number++;
        }
    }

    public List<AppUser> generateAndInit(String tag, int number, int from) {
        List<AppUser> appUsers = generate(tag, number, from);
        setFirstNames(appUsers, tag, from);
        setLastNames(appUsers, tag, from);
        return appUsers;
    }
}
