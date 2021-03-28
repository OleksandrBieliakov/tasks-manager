package com.obieliakov.tasksmanager.dto;

import com.obieliakov.tasksmanager.model.AppUser;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@ToString
public class UpdatedAppUserInfoDto {

    @NotNull
    private Long id;

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
