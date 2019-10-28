package com.car.rental.locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@Configuration
public class LocaleConfiguration extends WebMvcConfigurerAdapter {

	/**
	 * Spring-specific MessageSource implementation that accesses resource
	 * bundles using specified basenames, participating in the Spring
	 * ApplicationContext's resource loading. In contrast to the JDK-based
	 * ResourceBundleMessageSource, this class uses Properties instances as its
	 * custom data structure for messages, loading them via a
	 * PropertiesPersister strategy from Spring Resource handles. This strategy
	 * is not only capable of reloading files based on timestamp changes, but
	 * also of loading properties files with a specific character encoding. It
	 * will detect XML property files as well.
	 * 
	 * In contrast to ResourceBundleMessageSource, this class supports reloading
	 * of properties files through the "cacheSeconds" setting, and also through
	 * programmatically clearing the properties cache. Since application servers
	 * typically cache all files loaded from the classpath, it is necessary to
	 * store resources somewhere else (for example, in the "WEB-INF" directory
	 * of a web app). Otherwise changes of files in the classpath will not be
	 * reflected in the application.
	 * 
	 * Note that the base names set as "basenames" property are treated in a
	 * slightly different fashion than the "basenames" property of
	 * ResourceBundleMessageSource. It follows the basic ResourceBundle rule of
	 * not specifying file extension or language codes, but can refer to any
	 * Spring resource location (instead of being restricted to classpath
	 * resources). With a "classpath:" prefix, resources can still be loaded
	 * from the classpath, but "cacheSeconds" values other than "-1" (caching
	 * forever) will not work in this case.
	 * 
	 * This MessageSource implementation is usually slightly faster than
	 * ResourceBundleMessageSource, which builds on ResourceBundle - in the
	 * default mode, i.e. when caching forever. With "cacheSeconds" set to 1,
	 * message lookup takes about twice as long - with the benefit that changes
	 * in individual properties files are detected with a maximum delay of 1
	 * second. Higher "cacheSeconds" values usually do not make a significant
	 * difference
	 * 
	 * @return MessageSource instance
	 */
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasenames("classpath:/i18n/messages",
				"classpath:/i18n/errors",
				"classpath:/i18n/fields",
				"classpath:/mails/messages/emails",
				"classpath:/images");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

	/**
	 *  <p>
	  * Interceptor that allows for changing the current locale on every request,
	  * via a configurable request parameter (default parameter name: "locale").
	  * Here we've changed to language.
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName("language");
		registry.addInterceptor(localeChangeInterceptor);
	}
}
