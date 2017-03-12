package domain;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by daviddelatorre on 10/3/17.
 */
@Entity
@Access(AccessType.PROPERTY)
public class Customer extends Actor{

    // Relationships ---------------------------------------------------------


    public Collection<Offer> offers;
    public Collection<Request> requests;


    //Constructor
    public Customer(){
        super();
    }



    @OneToMany(cascade = CascadeType.PERSIST, targetEntity = Offer.class, mappedBy = "ownerO")
    public Collection<Offer> getOffers() {
        return offers;
    }

    public void setOffers(Collection<Offer> offers) {
        this.offers = offers;
    }

    @OneToMany(cascade = CascadeType.PERSIST, targetEntity = Request.class, mappedBy = "ownerR")
    public Collection<Request> getRequests() {
        return requests;
    }

    public void setRequests(Collection<Request> requests) {
        this.requests = requests;
    }
}
