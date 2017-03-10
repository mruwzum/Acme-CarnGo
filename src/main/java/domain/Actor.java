package domain;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;
import security.UserAccount;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Collection;

/**
 * Created by daviddelatorre on 10/3/17.
 */

@Entity
@Access(AccessType.PROPERTY)
public abstract  class Actor extends DomainEntity implements Commentable {

    // Attributes ------------------------------------------------------------
    private String name;
    private String surname;
    private String email;
    private String phone;
    private UserAccount userAccount;

    // Relationships ---------------------------------------------------------

    private Collection<Message> messages;

    // Constructors -----------------------------------------------------------
    public Actor() {
        super();
    }


    @NotBlank
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @NotBlank
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @NotBlank
    @Email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Pattern(regexp = "(\\+\\d{1,3}[ -.])?(\\(?\\d+\\)?[ -.]?)+")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    @NotNull
    @Valid
    @OneToOne(cascade = CascadeType.ALL, optional = false)
    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    @OneToMany(cascade = CascadeType.PERSIST)
    public Collection<Message> getMessages() {
        return messages;
    }

    public void setMessages(Collection<Message> messages) {
        this.messages = messages;
    }
}





