package com.br.dantas.app.infra.controllers;

import com.br.dantas.app.app.usecases.ISearchForClosePartners;
import com.br.dantas.app.infra.controllers.json.response.PartnerWithDistanceResponseJSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.net.HttpURLConnection;
import java.util.Collection;

@Api(tags = "Find closest partners")
@RequestMapping("/partner/distance")
@RestController
public class SearchForClosePartnersController {

    private final ISearchForClosePartners searchForClosePartners;

    public SearchForClosePartnersController(final ISearchForClosePartners searchForClosePartners) {
        this.searchForClosePartners = searchForClosePartners;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(tags = "Find closest partners", value = "Find the closest partners using the user's latitude and longitude and application settings")
    @ApiResponses(value = {
            @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Success", response = PartnerWithDistanceResponseJSON.class, responseContainer = "List")
    })
    public Collection<PartnerWithDistanceResponseJSON> execute(@RequestParam final double latitude, @RequestParam final double longitude) {
        final var response = searchForClosePartners.execute(latitude, longitude);
        return response.stream().map(PartnerWithDistanceResponseJSON::from).toList();
    }
}
