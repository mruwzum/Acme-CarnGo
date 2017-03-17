package domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;

/**
 * Created by daviddelatorre on 10/3/17.
 */
@Entity
@Access(AccessType.PROPERTY)
public class Request extends Trip implements Commentable{

    private Customer ownerR;
    private Collection<Comment> comment;

    // Relationships ---------------------------------------------------------





    //Constructor
    public Request(){
        super();
    }

    @NotNull
    @ManyToOne
    public Customer getOwnerR() {
        return ownerR;
    }

    public void setOwnerR(Customer ownerR) {
        this.ownerR = ownerR;
    }

    @Override
    @OneToMany(cascade = CascadeType.PERSIST)
    public Collection<Comment> getComment() {
        return comment;
    }
    @Override
    public void setComment(Collection<Comment> comments) {
        this.comment = comments;
    }
}
