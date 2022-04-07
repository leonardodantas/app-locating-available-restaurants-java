package com.br.dantas.app.infra.controllers;

import com.br.dantas.app.app.repository.IPartnerRepository;
import com.br.dantas.app.app.usecases.IFIndAllPartner;
import com.br.dantas.app.domain.Partner;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.net.HttpURLConnection;
import java.util.Collection;

@Api(tags = "Generate data")
@RestController
@RequestMapping("/generate")
public class GenerateDataController {

    private final IFIndAllPartner findAllPartner;
    private final IPartnerRepository partnerRepository;

    public GenerateDataController(@Qualifier("inMemoryFindAll") final IFIndAllPartner findAllPartner, @Qualifier("mongoRepository")final IPartnerRepository partnerRepository) {
        this.findAllPartner = findAllPartner;
        this.partnerRepository = partnerRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(tags = "Generate data", value = "Generate valid data for the application")
    @ApiResponses(value = {
            @ApiResponse(code = HttpURLConnection.HTTP_CREATED, message = "Success")
    })
    public void execute(){
        Collection<Partner> partners = findAllPartner.execute();
        partners.forEach(partnerRepository::save);
    }
}
