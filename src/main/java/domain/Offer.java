package domain;

import javax.persistence.*;
import java.util.Collection;

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


    @ManyToOne
    public Customer getOwnerO() {
        return ownerO;
    }

    public void setOwnerO(Customer ownerO) {
        this.ownerO = ownerO;
    }
}
