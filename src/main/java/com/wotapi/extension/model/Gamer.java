package com.wotapi.extension.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@Table
@Entity
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Gamer {

    @Id
    @Column
    int accountId;
    @Column
    String nickname;
    @Column
    Integer famePoints;
    @ToString.Exclude
    @JoinColumn(name = "clanId")
    @ManyToOne(targetEntity = Clan.class, cascade = CascadeType.ALL)
    Clan clan;

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
