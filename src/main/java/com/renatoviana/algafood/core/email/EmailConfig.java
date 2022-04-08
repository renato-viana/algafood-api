package com.renatoviana.algafood.core.email;

import com.renatoviana.algafood.domain.service.EnvioEmailService;
import com.renatoviana.algafood.infrastructure.service.email.MockLogEnvioEmailService;
import com.renatoviana.algafood.infrastructure.service.email.MockSandboxEnvioEmailService;
import com.renatoviana.algafood.infrastructure.service.email.SmtpEnvioEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmailConfig {
    @Autowired
    private EmailProperties emailProperties;

    @Bean
    public EnvioEmailService envioEmailService() {

        switch (emailProperties.getImpl()) {
            case LOG:
                return new MockLogEnvioEmailService();
            case SANDBOX:
                return new MockSandboxEnvioEmailService();
            case SMTP:
                return new SmtpEnvioEmailService();
            default:
                return null;
        }
    }
}
