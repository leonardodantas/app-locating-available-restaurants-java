package com.br.dantas.app.app.usecases.impl;

import com.br.dantas.app.app.repository.IPartnerRepository;
import com.br.dantas.app.app.usecases.IFIndAllPartner;
import com.br.dantas.app.domain.Partner;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Primary
@Service
public class FindAllPartner implements IFIndAllPartner {

    private final IPartnerRepository partnerRepository;

    public FindAllPartner(@Qualifier("mongoRepository")final IPartnerRepository partnerRepository) {
        this.partnerRepository = partnerRepository;
    }

    @Override
    @Cacheable(value = "partnersList")
    public Collection<Partner> execute(){
        return partnerRepository.findAll();
    }

    @Override
    @Cacheable(value = "partnersPage")
    public Page<Partner> execute(final int page, final int size) {
        return partnerRepository.findAll(page, size);
    }


}
