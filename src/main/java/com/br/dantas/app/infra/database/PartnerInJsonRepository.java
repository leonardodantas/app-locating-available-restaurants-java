package com.br.dantas.app.infra.database;

import com.br.dantas.app.app.repository.IPartnerRepository;
import com.br.dantas.app.domain.Partner;
import com.br.dantas.app.domain.Pdvs;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component("inJson")
public class PartnerInJsonRepository implements IPartnerRepository {

    private final JSONParser parser;
    private final ObjectMapper objectMapper;
    private final static String PDVS = "pdvs";
    private final Gson gson;

    @Value("${location.file.json}")
    private String locationFile;

    public PartnerInJsonRepository(final JSONParser parser, final  ObjectMapper objectMapper, final  Gson gson) {
        this.parser = parser;
        this.objectMapper = objectMapper;
        this.gson = gson;
    }

    @Override
    public Collection<Partner> findAll() {
        Collection<Partner> partnerJSON;
        try {
            final var jsonObject = getJsonObject();
            partnerJSON =  objectMapper.readValue(jsonObject.get(PDVS).toString(), new TypeReference<List<Partner>>(){});
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e.getMessage());
        }
        return partnerJSON;
    }

    @Override
    public Optional<Partner> findById(final String id) {
        final var partners = this.findAll();
        return partners.stream()
                .filter(partner -> partner.getId().equals(id))
                .findFirst();
    }

    @Override
    public Optional<Partner> findByDocument(final String document) {
        final var partners = this.findAll();
        return partners.stream()
                .filter(partner -> partner.getDocumetOnlyNumbers().equals(document))
                .findFirst();
    }

    @Override
    public Page<Partner> findAll(final int page, final int size) {
        final var pageable = PageRequest.of(page, size);
        final var partners = this.findAll();
        return new PageImpl<Partner>(partners.stream().toList(), pageable, partners.size());
    }

    @Override
    public Partner save(final Partner partner) {
        final var partnerToSave = partner.withId(this.getNextId());
        this.saveInJson(partnerToSave);
        return partnerToSave;
    }

    @Override
    public Optional<Partner> searchClosePartnersWithCoverageArea(final double latitude, final double longitude) {
        return Optional.empty();
    }

    @Override
    public Collection<Partner> findByLatLong(final double latitude,final double longitude) {
        return Collections.EMPTY_LIST;
    }

    private void saveInJson(final Partner partner) {
        final var partners = this.findAll();
        partners.add(partner);
        final var pdvs = new Pdvs(partners);
        final var json = gson.toJsonTree(pdvs).toString();
        try {
            final var file = new FileWriter(locationFile);
            file.write(json);
            file.close();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    private String getNextId(){
        final var partners = this.findAll();
        final var lastIdx = partners.size() - 1;
        final var id = Integer.parseInt(partners.stream().toList().get(lastIdx).getId()) + 1;
        return String.valueOf(id);
    }

    private JSONObject getJsonObject()  {
        try {
            return (JSONObject) parser.parse(new FileReader(
                    this.locationFile));
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}