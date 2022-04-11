package com.renatoviana.algafood.api.controller;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.renatoviana.algafood.api.assembler.FormaPagamentoInputDTODisassembler;
import com.renatoviana.algafood.api.assembler.FormaPagamentoOutputDTOAssembler;
import com.renatoviana.algafood.api.model.dto.input.FormaPagamentoInputDTO;
import com.renatoviana.algafood.api.model.dto.output.FormaPagamentoOutputDTO;
import com.renatoviana.algafood.domain.model.FormaPagamento;
import com.renatoviana.algafood.domain.repository.FormaPagamentoRepository;
import com.renatoviana.algafood.domain.service.CadastroFormaPagamentoService;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

@RestController
@RequestMapping("/formas-pagamento")
public class FormaPagamentoController {

    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;

    @Autowired
    private CadastroFormaPagamentoService cadastroFormaPagamentoService;

    @Autowired
    private FormaPagamentoOutputDTOAssembler formaPagamentoOutputDTOAssembler;

    @Autowired
    private FormaPagamentoInputDTODisassembler formaPagamentoInputDTODisassembler;

    @GetMapping
    public ResponseEntity<List<FormaPagamentoOutputDTO>> listar(ServletWebRequest request) {
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

        List<FormaPagamentoOutputDTO> formasPagamentosOutputDTO =
                formaPagamentoOutputDTOAssembler.toCollectionDTO(formasPagamentos);

        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS).cachePublic())
                .eTag(eTag)
                .body(formasPagamentosOutputDTO);
    }

    @GetMapping("/{formaPagamentoId}")
    public ResponseEntity<FormaPagamentoOutputDTO> buscar(@PathVariable Long formaPagamentoId, ServletWebRequest request) {
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

        FormaPagamentoOutputDTO formaPagamentoOutputDTO = formaPagamentoOutputDTOAssembler.toDTO(formaPagamento);

        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))
                .eTag(eTag)
                .body(formaPagamentoOutputDTO);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FormaPagamentoOutputDTO adicionar(@RequestBody @Valid FormaPagamentoInputDTO formaPagamentoInput) {
        FormaPagamento formaPagamento = formaPagamentoInputDTODisassembler.toDomainObject(formaPagamentoInput);

        formaPagamento = cadastroFormaPagamentoService.salvar(formaPagamento);

        return formaPagamentoOutputDTOAssembler.toDTO(formaPagamento);
    }

    @PutMapping("/{formaPagamentoId}")
    public FormaPagamentoOutputDTO atualizar(@PathVariable Long formaPagamentoId,
                                             @RequestBody @Valid FormaPagamentoInputDTO formaPagamentoInput) {

        FormaPagamento formaPagamentoAtual = cadastroFormaPagamentoService.buscarOuFalhar(formaPagamentoId);

        formaPagamentoInputDTODisassembler.copyToDomainObject(formaPagamentoInput, formaPagamentoAtual);

        formaPagamentoAtual = cadastroFormaPagamentoService.salvar(formaPagamentoAtual);

        return formaPagamentoOutputDTOAssembler.toDTO(formaPagamentoAtual);
    }

    @DeleteMapping("/{formaPagamentoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long formaPagamentoId) {
        cadastroFormaPagamentoService.excluir(formaPagamentoId);
    }
}
