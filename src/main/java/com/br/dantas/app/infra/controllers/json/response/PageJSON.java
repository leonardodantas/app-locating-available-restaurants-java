package com.br.dantas.app.infra.controllers.json.response;

import java.util.Collection;

public record PageJSON<T>(Collection<T> content, int page, int size, int totalElements, int totalPage) {

}
