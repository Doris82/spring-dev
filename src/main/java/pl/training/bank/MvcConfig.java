package pl.training.bank;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pl.training.bank.common.mapper.Mapper;
import pl.training.bank.common.mapper.ModelMapperAdapter;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "pl.training.bank")
@Import(BankConfig.class)
public class MvcConfig implements WebMvcConfigurer {

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("text");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
    @Bean
    public Mapper mapper() {
        return new ModelMapperAdapter();
    }
}