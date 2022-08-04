package com.renatoviana.algafood.api.v1.model.request;

import com.renatoviana.algafood.core.validation.FileContentType;
import com.renatoviana.algafood.core.validation.FileSize;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
public class FotoProdutoModelRequest {

    @Schema(description = "Arquivo da foto do produto (máximo 2MB, apenas JPG e PNG)")
    @NotNull
    @FileSize(max = "500KB")
    @FileContentType(allowed = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    private MultipartFile arquivo;

    @Schema(description = "Descrição da foto do produto", required = true)
    @NotBlank
    private String descricao;

}
