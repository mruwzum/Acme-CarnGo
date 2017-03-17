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
public class Offer extends Trip{


    public Customer ownerO;

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
}
