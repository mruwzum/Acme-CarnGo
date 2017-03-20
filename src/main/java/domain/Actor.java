package domain;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Columns;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;
import security.UserAccount;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.Collection;


@Entity
@Access(AccessType.PROPERTY)
public abstract  class Actor extends DomainEntity implements Commentable {

    // Attributes ------------------------------------------------------------
    private String name;
    private String surname;
    private String email;
    private String phone;
    private UserAccount userAccount;
    public Collection<Comment> comments;


    // Relationships ---------------------------------------------------------

    private Collection<Message> sendMessages = new ArrayList<>();
    private Collection<Message> recivedMessages  = new ArrayList<>();


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


    @Override
    @OneToMany(cascade = CascadeType.PERSIST, targetEntity = Comment.class)
    public Collection<Comment> getComment() {
        return comments;
    }

    @Override
    public void setComment(Collection<Comment> comment) {
        this.comments=comment;
    }


    @OneToMany(cascade = CascadeType.PERSIST, targetEntity = Message.class)
    public Collection<Message> getSendMessages() {
        return sendMessages;
    }

    public void setSendMessages(Collection<Message> sendMessages) {
        this.sendMessages = sendMessages;
    }


    @OneToMany(cascade = CascadeType.PERSIST, targetEntity = Message.class)
    public Collection<Message> getRecivedMessages() {
        return recivedMessages;
    }

    public void setRecivedMessages(Collection<Message> recivedMessages) {
        this.recivedMessages = recivedMessages;
    }

    @Override
    public String toString() {
        return name + " " +  surname;
    }
}





