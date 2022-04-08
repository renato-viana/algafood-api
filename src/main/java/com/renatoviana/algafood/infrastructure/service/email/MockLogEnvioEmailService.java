package com.renatoviana.algafood.infrastructure.service.email;

import com.renatoviana.algafood.domain.service.EnvioEmailService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MockLogEnvioEmailService extends SmtpEnvioEmailService {

    @Override
    public void enviar(EnvioEmailService.Mensagem mensagem) {

        String corpo = processarTemplate(mensagem);

        log.info("[MOCK E-MAIL] Para: {}\n{}", mensagem.getDestinatarios(), corpo);
    }
}
