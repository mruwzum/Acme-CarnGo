package converters;

import domain.Offer;
import org.springframework.core.convert.converter.Converter;
import org.springframework.util.Assert;

/**
 * Created by daviddelatorre on 22/12/16.
 */
public class OfferToStringConverter implements Converter<Offer, String> {


    @Override
    public String convert(Offer comment) {
        Assert.notNull(comment);
        String result;
        result = String.valueOf(comment.getId());
        return result;
    }
}
