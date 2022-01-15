package com.wotapi.extension.repository;

import com.wotapi.extension.model.Gamer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GamerRepository extends JpaRepository<Gamer, Integer> {
}
