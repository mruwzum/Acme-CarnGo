package converters;

import domain.Message;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Component
@Transactional
public class MessageToStringConverter implements Converter<Message, String> {

	@Override
	public String convert(Message administrator) {
		Assert.notNull(administrator);
		String result;
		result = String.valueOf(administrator.getId());
		return result;
	}

}