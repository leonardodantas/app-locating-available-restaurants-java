package com.br.dantas.app.infra.controllers;

import com.br.dantas.app.app.usecases.IFIndAllPartner;
import com.br.dantas.app.infra.controllers.json.response.PartnerDetailsResponseJSON;
import com.br.dantas.app.infra.controllers.json.response.PartnerResponseJSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.net.HttpURLConnection;
import java.util.Collection;

@Api(tags = "Get all Partners")
@RequestMapping("/partners")
@RestController
public class FindAllPartnerController {

    private final IFIndAllPartner findAllPartner;

    public FindAllPartnerController(final IFIndAllPartner findAllPartner) {
        this.findAllPartner = findAllPartner;
    }

    @GetMapping("/list")
    @ApiOperation(tags = "Get all Partners", value = "Returns all partners in a single list", response = PartnerDetailsResponseJSON.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Success")
    })
    public Collection<PartnerDetailsResponseJSON> list(){
        final var response = findAllPartner.execute();
        return response.stream().map(PartnerDetailsResponseJSON::from).toList();
    }

    @GetMapping("/page")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(tags = "Get all Partners", value = "Returns paginated partners")
    @ApiResponses(value = {
            @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Success", response = PartnerResponseJSON.class, responseContainer = "List")
    })
    public Collection<PartnerResponseJSON> page(@RequestParam final int page, @RequestParam final int size){
        final var response = findAllPartner.execute(page, size);
        return response.map(PartnerResponseJSON::from).stream().toList();
    }
}
