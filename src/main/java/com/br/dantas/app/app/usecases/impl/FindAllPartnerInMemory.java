package com.br.dantas.app.app.usecases.impl;

import com.br.dantas.app.app.repository.IPartnerRepository;
import com.br.dantas.app.app.usecases.IFIndAllPartner;
import com.br.dantas.app.domain.Partner;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service("inJsonFindAll")
public class FindAllPartnerInMemory implements IFIndAllPartner {

    private final IPartnerRepository partnerRepository;

    public FindAllPartnerInMemory(@Qualifier("inJson")final IPartnerRepository partnerRepository) {
        this.partnerRepository = partnerRepository;
    }

    @Override
    public Collection<Partner> execute(){
        return partnerRepository.findAll();
    }

    @Override
    public Page<Partner> execute(final int page, final int size) {
        return partnerRepository.findAll(page, size);
    }


}
