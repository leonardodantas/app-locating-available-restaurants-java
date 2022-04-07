package com.br.dantas.app.infra.controllers;

import com.br.dantas.app.app.usecases.IAddPartner;
import com.br.dantas.app.infra.controllers.advice.response.ErrorResponse;
import com.br.dantas.app.infra.controllers.json.request.PartnerRequestJSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.HttpURLConnection;

@Api(tags = "Create Partner")
@RequestMapping("/partner")
@RestController
public class AddPartnerController {

    private final IAddPartner addPartner;

    public AddPartnerController(final IAddPartner addPartner) {
        this.addPartner = addPartner;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(tags = "Create Partner", value = "Add a new partner to the database")
    @ApiResponses(value = {
            @ApiResponse(code = HttpURLConnection.HTTP_CREATED, message = "Success"),
            @ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Error when saving new partner", response = ErrorResponse.class)
    })
    public void execute(@RequestBody @Valid final PartnerRequestJSON body) {
        this.addPartner.execute(body);
    }

}
