package com.wotapi.extension.service;

import com.wotapi.extension.model.Gamer;
import com.wotapi.extension.repository.GamerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GamerService {

    private GamerRepository gamerRepository;

    @Autowired
    public void setGamerRepository(GamerRepository gamerRepository) {
        this.gamerRepository = gamerRepository;
    }

    public Gamer insertGamer(Gamer gamer) {
        return gamerRepository.save(gamer);
    }

    public Optional<Gamer> findGamerById(int id) {
        return gamerRepository.findById(id);
    }

    public List<Gamer> findAllGamers() {
        return gamerRepository.findAll();
    }

    public Gamer getGamerById(Integer id) {
        return gamerRepository.findById(id).orElseThrow();
    }

    public Gamer getGamerByName(String name) {
        return gamerRepository.findByNickname(name);
    }
}
