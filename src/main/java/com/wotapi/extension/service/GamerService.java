package com.wotapi.extension.service;

import com.wotapi.extension.model.Gamer;
import com.wotapi.extension.repository.GamerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GamerService {

    private GamerRepository gamerRepository;

    @Autowired
    public void setGamerRepository(GamerRepository gamerRepository) {
        this.gamerRepository = gamerRepository;
    }

    public Gamer getGamerById(Integer id) {
        return gamerRepository.findById(id).orElseThrow();
    }

    public Gamer getGamerByName(String name) {
        return gamerRepository.findByName(name);
    }
}
