package domain;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Collection;
import java.util.Date;

/**
 * Created by daviddelatorre on 10/3/17.
 */
@Entity
@Access(AccessType.PROPERTY)
public class Message extends DomainEntity {


    // Attributes ------------------------------------------------------------
    private String subject,body;
    private Date sentDate;
    private Collection<String> attachments;

    // Relationships ---------------------------------------------------------

    private String senderEmail;
    private String receiverEmail;

    //Constructor
    public Message(){
        super();
    }


    @NotBlank
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @NotBlank
    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
    @Temporal(TemporalType.DATE)
    public Date getSentDate() {
        return sentDate;
    }

    public void setSentDate(Date sentDate) {
        this.sentDate = sentDate;
    }

    @ElementCollection(targetClass = String.class)
    public Collection<String> getAttachments() {
        return attachments;
    }

    public void setAttachments(Collection<String> attachments) {
        this.attachments = attachments;
    }


    @NotBlank
    @Email
    public String getSenderEmail() {
        return senderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }
    @NotBlank
    @Email
    public String getReceiverEmail() {
        return receiverEmail;
    }

    public void setReceiverEmail(String receiverEmail) {
        this.receiverEmail = receiverEmail;
    }
}
