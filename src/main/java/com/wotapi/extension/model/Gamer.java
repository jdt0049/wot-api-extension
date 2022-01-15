package com.wotapi.extension.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Gamer {

    @Id
    @Column
    int accountId;
    @Column
    String name;
    @Column
    Integer famePoints;

    //TODO set proper invest levels
    public FameInvestLevels getFameInvestLevel() {
        if (famePoints == null) return FameInvestLevels.LEVEL_0;
        if (famePoints < 100) return FameInvestLevels.LEVEL_0;
        else if (famePoints < 1000) return FameInvestLevels.LEVEL_1;
        else if (famePoints < 2000) return FameInvestLevels.LEVEL_2;
        else if (famePoints < 3000) return FameInvestLevels.LEVEL_3;
        else if (famePoints < 4000) return FameInvestLevels.LEVEL_4;
        else if (famePoints < 5000) return FameInvestLevels.LEVEL_5;
        else if (famePoints < 6000) return FameInvestLevels.LEVEL_6;
        else if (famePoints < 7000) return FameInvestLevels.LEVEL_7;
        else return FameInvestLevels.LEVEL_7;
    }
}
