package converters;

import domain.Request;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Component
@Transactional
public class RequestToStringConverter implements Converter<Request, String> {

	@Override
	public String convert(Request administrator) {
		Assert.notNull(administrator);
		String result;
		result = String.valueOf(administrator.getId());
		return result;
	}

}