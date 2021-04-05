package com.obieliakov.tasksmanager.dto.appUser;

import com.obieliakov.tasksmanager.model.AppUser;
import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class UpdateAppUserInfoDto {

    @Size(min = AppUser.NAMES_MIN_LENGTH, max = AppUser.NAMES_MAX_LENGTH)
    private String firstName;

    @Size(min = AppUser.NAMES_MIN_LENGTH, max = AppUser.NAMES_MAX_LENGTH)
    private String lastName;

    public void trim() {
        if (firstName != null) {
            firstName = firstName.trim();
        }
        if (lastName != null) {
            lastName = lastName.trim();
        }
    }
}
