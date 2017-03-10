package domain;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by daviddelatorre on 10/3/17.
 */
@Entity
@Access(AccessType.PROPERTY)
public class Comment extends DomainEntity {

    // Attributes ------------------------------------------------------------
    private String title, text;
    private Date postedMoment;
    private int numberOfStars, objectiveId;


    //Constructor
    public Comment() {
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
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @NotNull
    public Date getPostedMoment() {
        return postedMoment;
    }

    public void setPostedMoment(Date postedMoment) {
        this.postedMoment = postedMoment;
    }

    @Range(min=0, max=5)
    @NotNull
    public int getNumberOfStars() {
        return numberOfStars;
    }

    public void setNumberOfStars(int numberOfStars) {
        this.numberOfStars = numberOfStars;
    }

    @NotNull
    public int getObjectiveId() {
        return objectiveId;
    }

    public void setObjectiveId(int objectiveId) {
        this.objectiveId = objectiveId;
    }
}
