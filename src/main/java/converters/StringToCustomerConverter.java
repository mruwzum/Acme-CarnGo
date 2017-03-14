package converters;

import domain.Comment;
import domain.Customer;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import repositories.CommentRepository;
import repositories.CustomerRepository;

@Component
@Transactional
public class StringToCustomerConverter implements Converter<String, Customer> {

	@Autowired
	CustomerRepository customerRepository;

	@Override
	public Customer convert(String text) {
		Customer result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = customerRepository.findOne(id);
			}
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;

	}

}