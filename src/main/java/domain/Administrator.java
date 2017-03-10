package domain;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by daviddelatorre on 10/3/17.
 */
@Entity
@Access(AccessType.PROPERTY)
public class Administrator extends Actor {

    // Relationships ---------------------------------------------------------
    public Collection<Comment> comments;

    //Constructor
    public Administrator(){
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

}
