package org.rajesh.users.presentation;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class CreateUserRequest {
    @NotEmpty(message = "First name is required")
    @Size(min = 2,max = 50, message = "First name must be at least 2 characters")
    private String firstName;

    @NotEmpty(message = "Last name is required")
    @Size(min = 2,max = 50, message = "Last name must be at least 2 characters")
    private String lastName;

    @NotEmpty(message = "Email is required")
    @Email(message = "Please provide a valid email address")
    private String email;

    @NotEmpty(message = "Password is required")
    @Size(min = 8,max = 16, message = "Password must be at least 8 characters")
    private String password;
}
