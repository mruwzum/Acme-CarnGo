package domain;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Date;

/**
 * Created by daviddelatorre on 10/3/17.
 */
@Entity
@Access(AccessType.PROPERTY)
public abstract class Trip extends DomainEntity implements Commentable {

    // Attributes ------------------------------------------------------------
    private String title, description, originAddress, destinationAddress;
    private Date tripDate;
    private Double coordXValue, coordYValue;
    private char coordXL, coordYL;
    private boolean banned;

    //Relationship
    public Collection<Comment> comments;
    public Collection<Application> applications;



    //Constructor
    public Trip(){
        super();
    }

    @NotBlank
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @NotBlank
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @NotBlank
    public String getOriginAddress() {
        return originAddress;
    }

    public void setOriginAddress(String originAddress) {
        this.originAddress = originAddress;
    }

    @NotBlank
    public String getDestinationAddress() {
        return destinationAddress;
    }

    public void setDestinationAddress(String destinationAddress) {
        this.destinationAddress = destinationAddress;
    }



    @NotNull
    @Temporal(TemporalType.DATE)
    public Date getTripDate() {
        return tripDate;
    }

    public void setTripDate(Date tripDate) {
        this.tripDate = tripDate;
    }


    public Double getCoordXValue() {
        return coordXValue;
    }

    public void setCoordXValue(Double coordXValue) {
        this.coordXValue = coordXValue;
    }

    public Double getCoordYValue() {
        return coordYValue;
    }

    public void setCoordYValue(Double coordYValue) {
        this.coordYValue = coordYValue;
    }

    public char getCoordXL() {
        return coordXL;
    }

    public void setCoordXL(char coordXL) {
        this.coordXL = coordXL;
    }

    public char getCoordYL() {
        return coordYL;
    }

    public void setCoordYL(char coordYL) {
        this.coordYL = coordYL;
    }


    public boolean isBanned() {
        return banned;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }


    @Override
    @OneToMany(cascade = CascadeType.PERSIST, targetEntity = Comment.class)
    public Collection<Comment> getComment() {
        return comments;
    }

    @Override
    public void setComment(Collection<Comment> comment) {
        this.comments=comment;
    }


    @OneToMany(cascade = CascadeType.PERSIST, targetEntity = Application.class)
    public Collection<Application> getApplications() {
        return applications;
    }

    public void setApplications(Collection<Application> applications) {
        this.applications = applications;
    }

}
