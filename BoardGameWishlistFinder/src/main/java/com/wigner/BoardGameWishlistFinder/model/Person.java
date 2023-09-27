package com.wigner.BoardGameWishlistFinder.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wigner.BoardGameWishlistFinder.annotations.ConfirmValuesMatch;
import com.wigner.BoardGameWishlistFinder.annotations.Password;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.GenericGenerator;

@Entity
@ConfirmValuesMatch.list({
        @ConfirmValuesMatch(
                field = "password",
                fieldMatch = "confirmPassword",
                message = "Passwords do not match!"
        ),
        @ConfirmValuesMatch(
                field = "email",
                fieldMatch = "confirmEmail",
                message = "Email do not match!"
        )
})
public class Person extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native")
    private int personId;

    @NotBlank(message = "Name must not be blank!")
    @Size(min = 3, message = "Name must be at least 3 characters long")
    private String name;

    @NotBlank(message = "Email must not be blank!")
    @Email(message = "Please provide a valid email!")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "Confirm Email must not be blank!")
    @Email(message = "Please provide a valid email!")
    @Transient
    @JsonIgnore
    private String confirmEmail;

    @NotBlank(message = "Password must not be blank!")
    @Size(min = 9, message = "Password must be at least 9 characters long")
    @Password
    @JsonIgnore
    private String password;

    @NotBlank(message = "Confirm Password must not be blank!")
    @Size(min = 9, message = "Confirm password must be at least 9 characters long")
    @Transient
    @JsonIgnore
    private String confirmPassword;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST, targetEntity = Roles.class)
    @JoinColumn(name = "role_id", referencedColumnName = "roleId", nullable = false)
    private Roles role;

    public int getPersonId() {
        return personId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getConfirmEmail() {
        return confirmEmail;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setConfirmEmail(String confirmEmail) {
        this.confirmEmail = confirmEmail;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }
}
