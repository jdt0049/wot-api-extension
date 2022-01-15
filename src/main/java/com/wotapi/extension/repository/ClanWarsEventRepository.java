package com.wotapi.extension.repository;

import com.wotapi.extension.model.ClanWarsEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClanWarsEventRepository extends JpaRepository<ClanWarsEvent, String> {
    ClanWarsEvent findByIsActive(Boolean isActive);
}
