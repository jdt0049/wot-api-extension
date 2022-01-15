package com.wotapi.extension.controller;

import com.wotapi.extension.model.Clan;
import com.wotapi.extension.service.ClanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClanController {

    private ClanService clanService;

    @Autowired
    public void setClanService(ClanService clanService) {
        this.clanService = clanService;
    }

    @GetMapping("/clan/id")
    public Clan getClanById(Integer clanId) {
        return clanService.getClanById(clanId);
    }

    @GetMapping("/clan/name")
    public Clan getClanByName(String clanName) {
        return clanService.getClanByName(clanName);
    }

    @GetMapping("/clan/tag")
    public Clan getClanByTag(String clanTag) {
        return clanService.getClanByTag(clanTag);
    }
}
