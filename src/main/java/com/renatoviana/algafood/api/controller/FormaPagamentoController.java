package com.renatoviana.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
	public List<FormaPagamentoOutputDTO> listar() {
		List<FormaPagamento> formasPagamentos = formaPagamentoRepository.findAll();
		
		return formaPagamentoOutputDTOAssembler.toCollectionDTO(formasPagamentos);
	}

	@GetMapping("/{formaPagamentoId}")
	public FormaPagamentoOutputDTO buscar(@PathVariable Long formaPagamentoId) {
		FormaPagamento formaPagamento = cadastroFormaPagamentoService.buscarOuFalhar(formaPagamentoId);
		
		return formaPagamentoOutputDTOAssembler.toDTO(formaPagamento);
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