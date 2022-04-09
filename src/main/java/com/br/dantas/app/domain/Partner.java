package com.br.dantas.app.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;

@Getter
@NoArgsConstructor
@Document("partner")
@Builder
@AllArgsConstructor
public class Partner implements Serializable {

    @Id
    private String id;
    private String tradingName;
    private String ownerName;
    private String document;
    private String documentOnlyNumber;
    @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
    private CoverageArea coverageArea;
    @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
    private Address address;

    private Partner(final String tradingName, final String ownerName, final CoverageArea coverageArea, final Address address) {
        this.tradingName = tradingName;
        this.ownerName = ownerName;
        this.coverageArea = coverageArea;
        this.address = address;
    }

    private Partner(final String id, final String ownerName, final String tradingName, final String document, final CoverageArea coverageArea, final Address address) {
        this.id = id;
        this.ownerName = ownerName;
        this.tradingName = tradingName;
        this.document = document;
        this.coverageArea = coverageArea;
        this.address = address;
    }

    public static Partner of(final String tradingName, final String ownerName, final CoverageArea coverageArea, final Address address){
        return new Partner(tradingName, ownerName, coverageArea, address);
    }

    public Partner withId(final String id) {
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
