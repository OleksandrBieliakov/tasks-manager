package com.obieliakov.tasksmanager.dto.appUser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

// added to practice custom hql query creation with projection to AppUserShortDto
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppUserShortDto {

    private UUID id;
    private String name;

    public AppUserShortDto(UUID id, String loginName, String firstName, String lastName) {
        this.id = id;
        setName(loginName, firstName, lastName);
    }

    public void setName(String loginName, String firstName, String lastName) {
        StringBuilder name = new StringBuilder();
        if (firstName == null && lastName == null) {
            name.append(loginName);
        } else {
            if (firstName != null) {
                name.append(firstName);
            }
            if (lastName != null) {
                if (firstName != null) {
                    name.append(" ");
                }
                name.append(lastName);
            }
        }
        this.name = name.toString();
    }
}
