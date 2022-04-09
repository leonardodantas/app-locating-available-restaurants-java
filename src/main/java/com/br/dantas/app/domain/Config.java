package com.br.dantas.app.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Document("configs")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Config implements Serializable {

    @Id
    private String id;
    private double maxDistance;
    private int quantityPartners;
    private LocalDateTime date;

    private Config(final double maxDistance,final int quantityPartners) {
        this.id = UUID.randomUUID().toString();
        this.maxDistance = maxDistance;
        this.quantityPartners = quantityPartners;
        this.date = LocalDateTime.MIN;
    }

    public static Config of(final double maxDistance, final int quantityPartners) {
        return new Config(maxDistance, quantityPartners);
    }
}
