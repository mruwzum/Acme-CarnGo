package converters;

import domain.Customer;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Component
@Transactional
public class CustomerToStringConverter implements Converter<Customer, String> {

	@Override
	public String convert(Customer comment) {
		Assert.notNull(comment);
		String result;
		result = String.valueOf(comment.getId());
		return result;
	}

}