package com.obieliakov.tasksmanager.dto.appUser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AppUserShortDto {

    private Long id;
    private String name;

    public AppUserShortDto(Long id, String loginName, String firstName, String lastName) {
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
