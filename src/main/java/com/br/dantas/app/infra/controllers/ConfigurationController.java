package com.br.dantas.app.infra.controllers;

import com.br.dantas.app.app.usecases.IGetConfig;
import com.br.dantas.app.app.usecases.IUpdateConfig;
import com.br.dantas.app.infra.controllers.advice.response.ErrorResponse;
import com.br.dantas.app.infra.controllers.json.request.ConfigRequestJSON;
import com.br.dantas.app.infra.controllers.json.response.ConfigResponseJSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.HttpURLConnection;

@Api(tags = "Configuration")
@RestController
@RequestMapping("/config")
public class ConfigurationController {

    private final IGetConfig getConfig;
    private final IUpdateConfig updateConfig;

    public ConfigurationController(final IGetConfig getConfig,final IUpdateConfig updateConfig) {
        this.getConfig = getConfig;
        this.updateConfig = updateConfig;
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(tags = "Configuration", value = "Update configuration")
    @ApiResponses(value = {
            @ApiResponse(code = HttpURLConnection.HTTP_CREATED, message = "Success"),
            @ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Error when update configuration", response = ErrorResponse.class)
    })
    public void update(@Valid @RequestBody final ConfigRequestJSON body){
        this.updateConfig.execute(body);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(tags = "Configuration", value = "Get configuration")
    @ApiResponses(value = {
            @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Success")
    })
    public ConfigResponseJSON get(){
        final var response = this.getConfig.execute();
        return ConfigResponseJSON.from(response);
    }
}
