package com.wotapi.extension.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@Entity
public class Clan {

    @Id
    @Column
    int clanId;
    @Column
    String clanName;
    @Column
    String clanTag;
    @Column
    String clanImageUrl;
    @Column
    @OneToMany
    List<Gamer> gamers;
}
