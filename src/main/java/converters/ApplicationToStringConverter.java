package converters;

import domain.Application;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Component
@Transactional
public class ApplicationToStringConverter implements Converter<Application, String> {

	@Override
	public String convert(Application actor) {
		Assert.notNull(actor);
		String result;
		result = String.valueOf(actor.getId());
		return result;
	}

}