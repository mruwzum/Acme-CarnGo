package domain;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by daviddelatorre on 10/3/17.
 */
@Entity
@Access(AccessType.PROPERTY)
public class Request extends Trip{

    // Attributes ------------------------------------------------------------
    private RequestStatus status;

    // Relationships ---------------------------------------------------------
    public Collection<Comment> comments;


    //Constructor
    public Request(){
        super();
    }

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
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
}
