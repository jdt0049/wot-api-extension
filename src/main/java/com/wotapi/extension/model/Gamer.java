package com.wotapi.extension.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Gamer {
    @Id
    int accountId;
    Integer famePoints;
    FameInvestLevels fameInvestLevel;
}
