package com.br.dantas.app.infra.controllers;

import com.br.dantas.app.app.usecases.ISearchForPartnerThatOperatesInTheCurrentArea;
import com.br.dantas.app.infra.controllers.json.response.PartnerResponseJSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.net.HttpURLConnection;

@Api(tags = "Search for a partner who works in the current area")
@RequestMapping("/partner")
@RestController
public class SearchForPartnerThatOperatesInTheCurrentAreaController {

    private final ISearchForPartnerThatOperatesInTheCurrentArea searchForPartnerThatOperatesInTheCurrentArea;

    public SearchForPartnerThatOperatesInTheCurrentAreaController(final ISearchForPartnerThatOperatesInTheCurrentArea searchForPartnerThatOperatesInTheCurrentArea) {
        this.searchForPartnerThatOperatesInTheCurrentArea = searchForPartnerThatOperatesInTheCurrentArea;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(tags = "Search for a partner who works in the current area", value = "Search for the partner that serves in the current area through a latitude and longitude")
    @ApiResponses(value = {
            @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Success", response = PartnerResponseJSON.class, responseContainer = "List")
    })
    public PartnerResponseJSON execute(@RequestParam final double latitude, @RequestParam final double longitude) {
        final var response = this.searchForPartnerThatOperatesInTheCurrentArea.execute(latitude, longitude);
        return PartnerResponseJSON.from(response);
    }
}
