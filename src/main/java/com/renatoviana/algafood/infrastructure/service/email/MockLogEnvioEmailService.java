package com.renatoviana.algafood.infrastructure.service.email;

import com.renatoviana.algafood.domain.service.EnvioEmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class MockLogEnvioEmailService implements EnvioEmailService {

    @Autowired
    private ProcessadorEmailTemplate processadorEmailTemplate;

    @Override
    public void enviar(EnvioEmailService.Mensagem mensagem) {

        String corpo = processadorEmailTemplate.processarTemplate(mensagem);

        log.info("[MOCK E-MAIL] Para: {}\n{}", mensagem.getDestinatarios(), corpo);
    }
}
