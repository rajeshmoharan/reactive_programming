package org.rajesh.users.presentation;


import lombok.*;

import java.util.UUID;

@AllArgsConstructor @NoArgsConstructor @Setter @Getter
public class UserRest {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
}
