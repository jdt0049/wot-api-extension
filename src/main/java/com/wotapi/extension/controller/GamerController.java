package com.wotapi.extension.controller;

import com.wotapi.extension.model.Gamer;
import com.wotapi.extension.service.GamerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GamerController {

    private GamerService gamerService;

    @Autowired
    public void setGamerService(GamerService gamerService) {
        this.gamerService = gamerService;
    }

    @GetMapping("/gamer/accountid")
    public Gamer getGamerById(Integer accountId) {
        return gamerService.getGamerById(accountId);
    }

    @GetMapping("/gamer/name")
    public Gamer getGamerByName(String name) {
        return gamerService.getGamerByName(name);
    }
}
