package com.renatoviana.algafood.core.email;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Validated
@Getter
@Setter
@Component
@ConfigurationProperties("algafood.email")
public class EmailProperties {

    @NotNull
    private String remetente;

    private Implementacao impl = Implementacao.LOG;

    private Sandbox sandbox = new Sandbox();

    @Getter
    @Setter
    public class Sandbox {

        private String destinatario;

    }

    public enum Implementacao {
        SMTP, LOG, SANDBOX
    }

}
