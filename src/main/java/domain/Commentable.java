package domain;

import java.util.Collection;

/**
 * Created by daviddelatorre on 10/3/17.
 */
public interface Commentable {



    Collection<Comment> getComment();

    void setComment(Collection<Comment> comment);

}
