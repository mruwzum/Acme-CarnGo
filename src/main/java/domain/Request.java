package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

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

    @NotNull
    @ManyToOne
    public Customer getOwnerR() {
        return ownerR;
    }

    public void setOwnerR(Customer ownerR) {
        this.ownerR = ownerR;
    }
}
