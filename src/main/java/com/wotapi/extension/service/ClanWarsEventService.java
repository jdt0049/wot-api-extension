package com.wotapi.extension.service;

import com.wotapi.extension.model.ClanWarsEvent;
import com.wotapi.extension.repository.ClanWarsEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClanWarsEventService {

    private ClanWarsEventRepository clanWarsEventRepository;

    @Autowired
    public void setClanWarsEventRepository(ClanWarsEventRepository clanWarsEventRepository) {
        this.clanWarsEventRepository = clanWarsEventRepository;
    }

    public ClanWarsEvent getActiveClanWarsEvent() {
        return clanWarsEventRepository.findByIsActive(true);
    }
}
