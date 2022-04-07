package com.br.dantas.app.app.models;

import com.br.dantas.app.app.exceptions.InitialIndexUnavailableException;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collection;

@Getter
public class Page<T> {

    private Collection<T> content;
    private final int page;
    private final int size;
    private final int totalElements;
    private final int totalPage;

    public Page(Collection<T> content, int page, int size) {
        this.totalElements = content.size();
        this.content = content;
        this.page = page;
        this.size = size;
        this.totalPage = this.totalElements / this.size;
        genetareContentWithInitialIndexAndFinalIndex();
    }

    public void genetareContentWithInitialIndexAndFinalIndex(){

        var initialIndex = 0;
        var finalIndex = 0;

        if((page * size) > totalElements) {
            throw new InitialIndexUnavailableException();
        }

        for(var currentPage = 0; currentPage <= page ; currentPage++) {
            if(currentPage != 0 ){
                initialIndex += size;
            }
            finalIndex += size;
        }

        if(finalIndex > totalElements) {
            finalIndex = totalElements;
        }

        ArrayList<T> contentList = new ArrayList<>(this.content);

        this.content = contentList.subList(initialIndex, finalIndex);

    }
}
