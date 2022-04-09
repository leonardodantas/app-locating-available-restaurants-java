package com.br.dantas.app.infra.controllers;

import com.br.dantas.app.app.usecases.IFIndPartner;
import com.br.dantas.app.infra.controllers.advice.response.ErrorResponse;
import com.br.dantas.app.infra.controllers.json.request.DocumentRequesJson;
import com.br.dantas.app.infra.controllers.json.response.PartnerDetailsResponseJSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.HttpURLConnection;

@Api(tags = "Find partner according to parameter")
@RequestMapping("/partner")
@RestController
public class FindPartnerController {

    private final IFIndPartner findPartner;

    public FindPartnerController(final IFIndPartner partnerJSON) {
        this.findPartner = partnerJSON;
    }

    @GetMapping("/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(tags = "Find partner according to parameter", value = "Use id as parameter")
    @ApiResponses(value = {
            @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Success", response = PartnerDetailsResponseJSON.class),
            @ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Partner not found", response = ErrorResponse.class)
    })
    public PartnerDetailsResponseJSON byId(@PathVariable final String id){
        final var response = findPartner.byId(id);
        return PartnerDetailsResponseJSON.from(response);
    }

    @PostMapping("/document")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(tags = "Find partner according to parameter", value = "Use cpf as parameter")
    @ApiResponses(value = {
            @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Success", response = PartnerDetailsResponseJSON.class),
            @ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Partner not found", response = ErrorResponse.class)
    })
    public PartnerDetailsResponseJSON byDocument(@Valid @RequestBody final DocumentRequesJson document){
        final var response = findPartner.byDocument(document);
        return PartnerDetailsResponseJSON.from(response);
    }
}
