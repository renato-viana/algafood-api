package com.renatoviana.algafood.api.controller;

import com.renatoviana.algafood.api.model.request.FormaPagamentoModelRequest;
import com.renatoviana.algafood.api.model.response.FormaPagamentoModelResponse;
import com.renatoviana.algafood.api.modelmapper.assembler.FormaPagamentoModelResponseAssembler;
import com.renatoviana.algafood.api.modelmapper.disassembler.FormaPagamentoModelRequestDisassembler;
import com.renatoviana.algafood.api.openapi.controller.FormaPagamentoControllerOpenApi;
import com.renatoviana.algafood.domain.model.FormaPagamento;
import com.renatoviana.algafood.domain.repository.FormaPagamentoRepository;
import com.renatoviana.algafood.domain.service.CadastroFormaPagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

import javax.validation.Valid;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(path = "/formas-pagamento", produces = MediaType.APPLICATION_JSON_VALUE)
public class FormaPagamentoController implements FormaPagamentoControllerOpenApi {

    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;

    @Autowired
    private CadastroFormaPagamentoService cadastroFormaPagamentoService;

    @Autowired
    private FormaPagamentoModelResponseAssembler formaPagamentoModelResponseAssembler;

    @Autowired
    private FormaPagamentoModelRequestDisassembler formaPagamentoModelRequestDisassembler;

    @GetMapping
    public ResponseEntity<List<FormaPagamentoModelResponse>> listar(ServletWebRequest request) {
        ShallowEtagHeaderFilter.disableContentCaching(request.getRequest());

        String eTag = "0";

        OffsetDateTime dataUltimaAtualizacao = formaPagamentoRepository.getDataUltimaAtualizacao();

        if (dataUltimaAtualizacao != null) {
            eTag = String.valueOf(dataUltimaAtualizacao.toEpochSecond());
        }

        if (request.checkNotModified(eTag)) {
            return null;
        }

        List<FormaPagamento> formasPagamentos = formaPagamentoRepository.findAll();

        List<FormaPagamentoModelResponse> formasPagamentosOutputDTO =
                formaPagamentoModelResponseAssembler.toCollectionModelResponse(formasPagamentos);

        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS).cachePublic())
                .eTag(eTag)
                .body(formasPagamentosOutputDTO);
    }

    @GetMapping("/{formaPagamentoId}")
    public ResponseEntity<FormaPagamentoModelResponse> buscar(@PathVariable Long formaPagamentoId, ServletWebRequest request) {
        ShallowEtagHeaderFilter.disableContentCaching(request.getRequest());

        String eTag = "0";

        OffsetDateTime dataAtualizacao = formaPagamentoRepository
                .getDataAtualizacaoById(formaPagamentoId);

        if (dataAtualizacao != null) {
            eTag = String.valueOf(dataAtualizacao.toEpochSecond());
        }

        if (request.checkNotModified(eTag)) {
            return null;
        }

        FormaPagamento formaPagamento = cadastroFormaPagamentoService.buscarOuFalhar(formaPagamentoId);

        FormaPagamentoModelResponse formaPagamentoOutputDTO = formaPagamentoModelResponseAssembler.toModelResponse(formaPagamento);

        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))
                .eTag(eTag)
                .body(formaPagamentoOutputDTO);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FormaPagamentoModelResponse adicionar(@RequestBody @Valid FormaPagamentoModelRequest formaPagamentoInput) {
        FormaPagamento formaPagamento = formaPagamentoModelRequestDisassembler.toDomainObject(formaPagamentoInput);

        formaPagamento = cadastroFormaPagamentoService.salvar(formaPagamento);

        return formaPagamentoModelResponseAssembler.toModelResponse(formaPagamento);
    }

    @PutMapping("/{formaPagamentoId}")
    public FormaPagamentoModelResponse atualizar(@PathVariable Long formaPagamentoId,
                                                 @RequestBody @Valid FormaPagamentoModelRequest formaPagamentoInput) {

        FormaPagamento formaPagamentoAtual = cadastroFormaPagamentoService.buscarOuFalhar(formaPagamentoId);

        formaPagamentoModelRequestDisassembler.copyToDomainObject(formaPagamentoInput, formaPagamentoAtual);

        formaPagamentoAtual = cadastroFormaPagamentoService.salvar(formaPagamentoAtual);

        return formaPagamentoModelResponseAssembler.toModelResponse(formaPagamentoAtual);
    }

    @DeleteMapping("/{formaPagamentoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long formaPagamentoId) {
        cadastroFormaPagamentoService.excluir(formaPagamentoId);
    }
}
