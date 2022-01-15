package com.wotapi.extension.controller;

import com.wotapi.extension.model.ClanWarsEvent;
import com.wotapi.extension.service.ClanWarsEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClanWarsEventController {

    private ClanWarsEventService clanWarsEventService;

    @Autowired
    public void setClanWarsEventService(ClanWarsEventService clanWarsEventService) {
        this.clanWarsEventService = clanWarsEventService;
    }

    @GetMapping("clanwars/events/active")
    public ClanWarsEvent getActiveClanWarsEvent() {
        return clanWarsEventService.getActiveClanWarsEvent();
    }
}
