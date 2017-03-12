package domain;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by daviddelatorre on 10/3/17.
 */
@Entity
@Access(AccessType.PROPERTY)
public class Request extends Trip{

    public Customer ownerR;


    // Relationships ---------------------------------------------------------





    //Constructor
    public Request(){
        super();
    }


    @ManyToOne
    public Customer getOwnerR() {
        return ownerR;
    }

    public void setOwnerR(Customer ownerR) {
        this.ownerR = ownerR;
    }
}
