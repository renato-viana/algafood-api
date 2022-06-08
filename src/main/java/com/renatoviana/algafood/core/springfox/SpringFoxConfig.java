package com.renatoviana.algafood.core.springfox;

import com.fasterxml.classmate.TypeResolver;
import com.renatoviana.algafood.api.exceptionhandler.Problem;
import com.renatoviana.algafood.api.v1.model.response.*;
import com.renatoviana.algafood.api.v1.openapi.model.*;
import com.renatoviana.algafood.api.v2.model.response.CidadeModelResponseV2;
import com.renatoviana.algafood.api.v2.model.response.CozinhaModelResponseV2;
import com.renatoviana.algafood.api.v2.openapi.model.CidadesModelResponseV2OpenApi;
import com.renatoviana.algafood.api.v2.openapi.model.CozinhasModelResponseV2OpenApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Links;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.AlternateTypeRule;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLStreamHandler;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SpringFoxConfig implements WebMvcConfigurer {

    @Autowired
    private TypeResolver typeResolver;

    @Bean
    public Docket apiDocketV1() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("V1")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.renatoviana.algafood.api"))
                .paths(PathSelectors.ant("/v1/**"))
                .build()
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, globalGetResponseMessages())
                .globalResponseMessage(RequestMethod.POST, globalPostPutResponseMessages())
                .globalResponseMessage(RequestMethod.PUT, globalPostPutResponseMessages())
                .globalResponseMessage(RequestMethod.DELETE, globalDeleteResponseMessages())
                .additionalModels(typeResolver.resolve(Problem.class))
                .ignoredParameterTypes(ServletWebRequest.class, URL.class, URI.class, URLStreamHandler.class,
                        Resource.class, File.class, InputStream.class)
                .directModelSubstitute(Pageable.class, PageableModelResponseOpenApi.class)
                .directModelSubstitute(Links.class, LinksModelResponseOpenApi.class)
                .alternateTypeRules(rules())
                .apiInfo(apiInfoV1())
                .tags(tags()[0], tags());
    }

    @Bean
    public Docket apiDocketV2() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("V2")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.renatoviana.algafood.api"))
                .paths(PathSelectors.ant("/v2/**"))
                .build()
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, globalGetResponseMessages())
                .globalResponseMessage(RequestMethod.POST, globalPostPutResponseMessages())
                .globalResponseMessage(RequestMethod.PUT, globalPostPutResponseMessages())
                .globalResponseMessage(RequestMethod.DELETE, globalDeleteResponseMessages())
                .additionalModels(typeResolver.resolve(Problem.class))
                .ignoredParameterTypes(ServletWebRequest.class, URL.class, URI.class, URLStreamHandler.class,
                        Resource.class, File.class, InputStream.class)
                .directModelSubstitute(Pageable.class, PageableModelResponseOpenApi.class)
                .directModelSubstitute(Links.class, LinksModelResponseOpenApi.class)
                .alternateTypeRules(AlternateTypeRules.newRule(
                        typeResolver.resolve(PagedModel.class, CozinhaModelResponseV2.class),
                        CozinhasModelResponseV2OpenApi.class))
                .alternateTypeRules(AlternateTypeRules.newRule(
                        typeResolver.resolve(CollectionModel.class, CidadeModelResponseV2.class),
                        CidadesModelResponseV2OpenApi.class))
                .apiInfo(apiInfoV2())
                .tags(new Tag("Cidades", "Gerencia as cidades"),
                        new Tag("Cozinhas", "Gerencia as cozinhas"));
    }

    private List<ResponseMessage> globalGetResponseMessages() {
        return Arrays.asList(
                new ResponseMessageBuilder()
                        .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .message("Erro interno do servidor")
                        .responseModel(new ModelRef("Problema"))
                        .build(),
                new ResponseMessageBuilder()
                        .code(HttpStatus.NOT_ACCEPTABLE.value())
                        .message("Recurso não possui representação que poderia ser aceita pelo consumidor")
                        .build()
        );
    }

    private List<ResponseMessage> globalPostPutResponseMessages() {
        return Arrays.asList(
                new ResponseMessageBuilder()
                        .code(HttpStatus.BAD_REQUEST.value())
                        .message("Requisição inválida (erro do cliente)")
                        .responseModel(new ModelRef("Problema"))
                        .build(),
                new ResponseMessageBuilder()
                        .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .message("Erro interno no servidor")
                        .responseModel(new ModelRef("Problema"))
                        .build(),
                new ResponseMessageBuilder()
                        .code(HttpStatus.NOT_ACCEPTABLE.value())
                        .message("Recurso não possui representação que poderia ser aceita pelo consumidor")
                        .build(),
                new ResponseMessageBuilder()
                        .code(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value())
                        .message("Requisição recusada porque o corpo está em um formato não suportado")
                        .responseModel(new ModelRef("Problema"))
                        .build()
        );
    }

    private List<ResponseMessage> globalDeleteResponseMessages() {
        return Arrays.asList(
                new ResponseMessageBuilder()
                        .code(HttpStatus.BAD_REQUEST.value())
                        .message("Requisição inválida (erro do cliente)")
                        .responseModel(new ModelRef("Problema"))
                        .build(),
                new ResponseMessageBuilder()
                        .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .message("Erro interno no servidor")
                        .responseModel(new ModelRef("Problema"))
                        .build()
        );
    }

    private Tag[] tags() {
        return new Tag[]{
                new Tag("Cidades", "Gerencia as cidades"),
                new Tag("Cozinhas", "Gerencia as cozinhas"),
                new Tag("Grupos", "Gerencia os grupos"),
                new Tag("Pedidos", "Gerencia os pedidos"),
                new Tag("Formas de pagamento", "Gerencia as formas de pagamento"),
                new Tag("Restaurantes", "Gerencia os restaurantes"),
                new Tag("Produtos", "Gerencia os produtos de restaurante"),
                new Tag("Estados", "Gerencia os estados"),
                new Tag("Estatísticas", "Estatísticas da AlgaFood"),
                new Tag("Usuários", "Gerenciaros usuários"),
                new Tag("Permissões", "Gerencia as permissões")
        };
    }

    private AlternateTypeRule[] rules() {
        return new AlternateTypeRule[]{
                AlternateTypeRules.newRule(
                        typeResolver.resolve(PagedModel.class, CozinhaModelResponse.class),
                        CozinhasModelResponseOpenApi.class),
                AlternateTypeRules.newRule(
                        typeResolver.resolve(Page.class, PedidoResumoModelResponse.class),
                        PedidosResumoModelResponseOpenApi.class),
                AlternateTypeRules.newRule(
                        typeResolver.resolve(CollectionModel.class, CidadeModelResponse.class),
                        CidadesModelResponseOpenApi.class),
                AlternateTypeRules.newRule(
                        typeResolver.resolve(CollectionModel.class, EstadoModelResponse.class),
                        EstadosModelResponseOpenApi.class),
                AlternateTypeRules.newRule(
                        typeResolver.resolve(CollectionModel.class, FormaPagamentoModelResponse.class),
                        FormasPagamentoModelResponseOpenApi.class),
                AlternateTypeRules.newRule(
                        typeResolver.resolve(CollectionModel.class, GrupoModelResponse.class),
                        GruposModelResponseOpenApi.class),
                AlternateTypeRules.newRule(
                        typeResolver.resolve(CollectionModel.class, PermissaoModelResponse.class),
                        PermissoesModelResponseOpenApi.class),
                AlternateTypeRules.newRule(
                        typeResolver.resolve(PagedModel.class, PedidoResumoModelResponse.class),
                        PedidosResumoModelResponseOpenApi.class),
                AlternateTypeRules.newRule(
                        typeResolver.resolve(CollectionModel.class, ProdutoModelResponse.class),
                        ProdutosModelResponseOpenApi.class),
                AlternateTypeRules.newRule(
                        typeResolver.resolve(CollectionModel.class, RestauranteBasicoModelResponse.class),
                        RestaurantesBasicoModelResponseOpenApi.class),
                AlternateTypeRules.newRule(
                        typeResolver.resolve(CollectionModel.class, UsuarioModelResponse.class),
                        UsuariosModelResponseOpenApi.class)
        };
    }

    public ApiInfo apiInfoV1() {
        return new ApiInfoBuilder()
                .title("AlgaFood API (Depreciada)")
                .description("API aberta para clientes e restaurantes.<br>"
                        + "<strong>Essa versão da API está depreciada e deixará de existir a partir de 01/01/2023. "
                        + "Use a versão mais atual da API.")
                .version("1")
                .contact(new Contact(
                        "Renato Borges Viana",
                        "https://github.com/renato-viana/algafood-api.git",
                        "renatoviana30@gmail.com"))
                .build();
    }

    public ApiInfo apiInfoV2() {
        return new ApiInfoBuilder()
                .title("AlgaFood API")
                .description("API aberta para clientes e restaurantes")
                .version("2")
                .contact(new Contact(
                        "Renato Borges Viana",
                        "https://github.com/renato-viana/algafood-api.git",
                        "renatoviana30@gmail.com"))
                .build();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");

    }
}
