package com.wotapi.extension.service;

import com.wotapi.extension.model.Clan;
import com.wotapi.extension.repository.ClanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClanService {

    private ClanRepository clanRepository;

    @Autowired
    public void setClanRepository(ClanRepository clanRepository) {
        this.clanRepository = clanRepository;
    }

    public Clan getClanById(Integer id) {
       return clanRepository.findById(id).orElseThrow();
    }

    public Clan getClanByName(String name) {
        return clanRepository.findByClanName(name);
    }

    public Clan getClanByTag(String tag) {
        return clanRepository.findByClanTag(tag);
    }
}
