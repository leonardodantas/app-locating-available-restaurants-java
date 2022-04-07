package com.br.dantas.app.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Getter
@NoArgsConstructor
@Builder
@Document("partner")
public class Partner {

    @Id
    private String id;
    private String tradingName;
    private String ownerName;
    private String document;
    @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
    private CoverageArea coverageArea;
    @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
    private Address address;

    private Partner(String tradingName, String ownerName, CoverageArea coverageArea, Address address) {
        this.tradingName = tradingName;
        this.ownerName = ownerName;
        this.coverageArea = coverageArea;
        this.address = address;
    }

    private Partner(String id, String ownerName, String tradingName, String document, CoverageArea coverageArea, Address address) {
        this.id = id;
        this.ownerName = ownerName;
        this.tradingName = tradingName;
        this.document = document;
        this.coverageArea = coverageArea;
        this.address = address;
    }

    public static Partner of(String tradingName, String ownerName, CoverageArea coverageArea, Address address){
        return new Partner(tradingName, ownerName, coverageArea, address);
    }

    public Partner withId(String id) {
        return new Partner(
                id,
                this.ownerName,
                this.tradingName,
                this.document,
                this.coverageArea,
                this.address
        );
    }

    public String getDocumetOnlyNumbers(){
        return this.document.replace(".", "").replace("/", "");
    }

    public double getLatitude(){
        return new ArrayList<>(this.address.getCoordinates()).get(1);
    }

    public double getLongitude(){
        return new ArrayList<>(this.address.getCoordinates()).get(0);
    }
}
