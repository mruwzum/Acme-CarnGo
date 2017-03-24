package converters;

import domain.Application;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import repositories.ApplicationRepository;

@Component
@Transactional
public class StringToApplicationConverter implements Converter<String, Application> {

	@Autowired
	ApplicationRepository applicationRepository;

	@Override
	public Application convert(String text) {
		Application result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = applicationRepository.findOne(id);
			}
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;

	}

}
