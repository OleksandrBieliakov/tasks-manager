package com.obieliakov.tasksmanager.dto.appUser;

import com.obieliakov.tasksmanager.model.AppUser;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class NewAppUserDto {

    @NotNull
    @Size(min = AppUser.NAMES_MIN_LENGTH, max = AppUser.NAMES_MAX_LENGTH)
    private String loginName;

    @NotNull
    @Email
    private String email;

    @Size(min = AppUser.NAMES_MIN_LENGTH, max = AppUser.NAMES_MAX_LENGTH)
    private String firstName;

    @Size(min = AppUser.NAMES_MIN_LENGTH, max = AppUser.NAMES_MAX_LENGTH)
    private String lastName;

    public void trim() {
        if (loginName != null) {
            loginName = loginName.trim();
        }
        if (email != null) {
            email = email.trim();
        }
        if (firstName != null) {
            firstName = firstName.trim();
        }
        if (lastName != null) {
            lastName = lastName.trim();
        }
    }
}
