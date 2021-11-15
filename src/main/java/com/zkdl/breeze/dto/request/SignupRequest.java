package com.zkdl.breeze.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
public class SignupRequest {
    @NotBlank(message = "Username cant not be null")
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank(message = "Email cant not be null")
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank(message = "Address cant not be null")
    private String address;

    @JsonProperty("first_name")
    @NotBlank(message = "First name cant not be null")
    private String firstName;

    @JsonProperty("last_name")
    @NotBlank(message = "Last name cant not be null")
    private String lastName;

    @JsonProperty("phone")
    @NotBlank(message = "Phone cant not be null")
    private String phone;

    private Set<String> role;

    @NotBlank(message = "Password cant not be null")
    @Size(min = 6, max = 40)
    private String password;

    public Set<String> getRole() {
        return this.role;
    }

    public void setRole(Set<String> role) {
        this.role = role;
    }
}