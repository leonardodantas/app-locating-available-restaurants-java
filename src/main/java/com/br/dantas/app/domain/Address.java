package com.br.dantas.app.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Address {

    private String type;
    private Collection<Double> coordinates;


}
