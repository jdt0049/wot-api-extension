package com.wotapi.extension.repository;

import com.wotapi.extension.model.Clan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClanRepository extends JpaRepository<Clan, Integer> {
}
