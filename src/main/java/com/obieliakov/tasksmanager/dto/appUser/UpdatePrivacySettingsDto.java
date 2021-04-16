package com.obieliakov.tasksmanager.dto.appUser;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UpdatePrivacySettingsDto {

    @NotNull
    private Boolean publicEmail;

    @NotNull
    private Boolean publicFirstLastName;
}
