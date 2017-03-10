package domain;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by daviddelatorre on 10/3/17.
 */
@Entity
@Access(AccessType.PROPERTY)
public class Request extends Trip{



    // Relationships ---------------------------------------------------------
    public Collection<Comment> comments;
    public Customer owner;
    public Collection<Application> applications;



    //Constructor
    public Request(){
        super();
    }



    @Override
    @OneToMany(cascade = CascadeType.PERSIST)
    public Collection<Comment> getComment() {
        return comments;
    }

    @Override
    public void setComment(Collection<Comment> comment) {
        this.comments=comment;
    }

    @OneToOne(optional = false)
    public Customer getOwner() {
        return owner;
    }

    public void setOwner(Customer owner) {
        this.owner = owner;
    }
    @OneToMany(cascade = CascadeType.PERSIST, targetEntity = Application.class)
    public Collection<Application> getApplications() {
        return applications;
    }

    public void setApplications(Collection<Application> applications) {
        this.applications = applications;
    }
}
