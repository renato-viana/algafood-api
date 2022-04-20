package com.renatoviana.algafood.api.controller;

import com.renatoviana.algafood.api.assembler.CidadeInputDTODisassembler;
import com.renatoviana.algafood.api.assembler.CidadeOutputDTOAssembler;
import com.renatoviana.algafood.api.exceptionhandler.Problem;
import com.renatoviana.algafood.api.model.dto.input.CidadeInputDTO;
import com.renatoviana.algafood.api.model.dto.output.CidadeOutputDTO;
import com.renatoviana.algafood.domain.exception.EstadoNaoEncontradoException;
import com.renatoviana.algafood.domain.exception.NegocioException;
import com.renatoviana.algafood.domain.model.Cidade;
import com.renatoviana.algafood.domain.repository.CidadeRepository;
import com.renatoviana.algafood.domain.service.CadastroCidadeService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "Cidades")
@RestController
@RequestMapping("/cidades")
public class CidadeController {

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private CadastroCidadeService cadastroCidadeService;

	@Autowired
	private CidadeOutputDTOAssembler cidadeOutputDTOAssembler;

	@Autowired
	private CidadeInputDTODisassembler cidadeInputDTODisassembler;

	@ApiOperation("Lista as cidades")
	@GetMapping
	public List<CidadeOutputDTO> listar() {
		List<Cidade> cidades = cidadeRepository.findAll();

		return cidadeOutputDTOAssembler.toCollectionDTO(cidades);
	}

	@ApiOperation("Busca uma cidade por ID")
	@ApiResponses({
			@ApiResponse(code = 400, message = "ID da cidade inválido", response = Problem.class),
			@ApiResponse(code = 404, message = "Cidade não econtrada", response = Problem.class)
	})
	@GetMapping("/{cidadeId}")
	public CidadeOutputDTO buscar(
            @ApiParam(value = "ID de uma cidade", example = "1")
            @PathVariable Long cidadeId) {
		Cidade cidade = cadastroCidadeService.buscarOuFalhar(cidadeId);

		return cidadeOutputDTOAssembler.toDTO(cidade);
	}

	@ApiOperation("Cadastra uma cidade")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Cidade cadastrada")
	})
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CidadeOutputDTO adicionar(
            @ApiParam(name = "corpo", value = "Representação de uma nova cidade")
			@RequestBody @Valid CidadeInputDTO cidadeInput) {
		try {
			Cidade cidade = cidadeInputDTODisassembler.toDomainObject(cidadeInput);

			cidade = cadastroCidadeService.salvar(cidade);

			return cidadeOutputDTOAssembler.toDTO(cidade);
		} catch (EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

	@ApiOperation("Atualiza uma cidade por ID")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Cidade atualizada"),
			@ApiResponse(code = 404, message = "Cidade não econtrada", response = Problem.class)
	})
	@PutMapping("/{cidadeId}")
	public CidadeOutputDTO Atualizar(
            @ApiParam(value = "ID de uma cidade", example = "1")
            @PathVariable Long cidadeId,
            @ApiParam(name = "corpo", value = "Representação de uma cidade com os novos dados")
            @RequestBody @Valid CidadeInputDTO cidadeInput) {

		try {
			Cidade cidadeAtual = cadastroCidadeService.buscarOuFalhar(cidadeId);

			cidadeInputDTODisassembler.copyToDoaminObject(cidadeInput, cidadeAtual);

			cidadeAtual = cadastroCidadeService.salvar(cidadeAtual);

			return cidadeOutputDTOAssembler.toDTO(cidadeAtual);
		} catch (EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}

	}

	@ApiOperation("Exclui uma cidade por ID")
	@ApiResponses({
			@ApiResponse(code = 204, message = "Cidade excluída"),
			@ApiResponse(code = 404, message = "Cidade não econtrada", response = Problem.class)
	})
	@DeleteMapping("/{cidadeId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(
            @ApiParam(value = "ID de uma cidade", example = "1")
            @PathVariable Long cidadeId) {
		cadastroCidadeService.excluir(cidadeId);
	}
}
