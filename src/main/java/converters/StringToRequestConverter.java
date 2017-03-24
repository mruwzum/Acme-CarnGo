package converters;

import domain.Request;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import repositories.RequestRepository;

@Component
@Transactional
public class StringToRequestConverter implements Converter<String, Request> {

	@Autowired
	RequestRepository requestRepository;

	@Override
	public Request convert(String text) {
		Request result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = requestRepository.findOne(id);
			}
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;

	}

}
