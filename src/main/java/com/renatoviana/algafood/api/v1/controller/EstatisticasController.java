package com.renatoviana.algafood.api.v1.controller;

import com.renatoviana.algafood.api.v1.helper.ResourceLinkHelper;
import com.renatoviana.algafood.api.v1.openapi.controller.EstatisticasControllerOpenApi;
import com.renatoviana.algafood.core.security.CheckSecurity;
import com.renatoviana.algafood.domain.filter.VendaDiariaFilter;
import com.renatoviana.algafood.domain.model.dto.VendaDiaria;
import com.renatoviana.algafood.domain.service.VendaJasperReportsService;
import com.renatoviana.algafood.domain.service.VendaQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/v1/estatisticas")
public class EstatisticasController implements EstatisticasControllerOpenApi {

    @Autowired
    private VendaQueryService vendaQueryService;

    @Autowired
    private VendaJasperReportsService vendaJasperReportsService;

    @Autowired
    private ResourceLinkHelper resourceLinkHelper;

    @CheckSecurity.Estatisticas.PodeConsultar
    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public EstatisticasModelResponse estatisticas() {
        var estatisticasModelResponse = new EstatisticasModelResponse();

        estatisticasModelResponse.add(resourceLinkHelper.linkToEstatisticasVendasDiarias("vendas-diarias"));

        return estatisticasModelResponse;
    }

    @CheckSecurity.Estatisticas.PodeConsultar
    @Override
    @GetMapping(path = "vendas-diarias", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro,
                                                    @RequestParam(required = false, defaultValue = "+00:00") String timeOffset) {
        return vendaQueryService.consultarVendasDiarias(filtro, timeOffset);
    }

    @CheckSecurity.Estatisticas.PodeConsultar
    @Override
    @GetMapping(path = "vendas-diarias", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> consultarVendasDiariasPdf(VendaDiariaFilter filtro,
                                                            @RequestParam(required = false, defaultValue = "+00:00") String timeOffset) {

        byte[] bytesPdf = vendaJasperReportsService.emitirVendasDiarias(filtro, timeOffset);

        var headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=vendas-diarias.pdf");

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .headers(headers)
                .body(bytesPdf);
    }

    public static class EstatisticasModelResponse extends RepresentationModel<EstatisticasModelResponse> {
    }

}
