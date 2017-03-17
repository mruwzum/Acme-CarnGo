package domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;

/**
 * Created by daviddelatorre on 10/3/17.
 */
@Entity
@Access(AccessType.PROPERTY)
public class Offer extends Trip implements Commentable{


    private Customer ownerO;
    private Collection<Comment> comment;

    // Relationships ---------------------------------------------------------



    //Constructor
    public Offer(){
        super();
    }


    @NotNull
    @ManyToOne
    public Customer getOwnerO() {
        return ownerO;
    }

    public void setOwnerO(Customer ownerO) {
        this.ownerO = ownerO;
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
