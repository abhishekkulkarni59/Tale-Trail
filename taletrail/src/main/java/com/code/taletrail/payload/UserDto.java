package com.code.taletrail.payload;

import com.code.taletrail.model.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

        private int userId;

        @NotEmpty
        @Size(min = 4, message = "Username must be min of 4 characters")
        private String name;

        @Email(message = "Enter valid email address")
        private String email;

        @NotEmpty
        @Size(min = 4, max = 10, message = "Enter password having length between 4 to 10 characters")
        private String password;

        @NotNull
        private String about;

        private Set<RoleDto> roles = new HashSet<>();

}
