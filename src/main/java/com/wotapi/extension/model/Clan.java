package com.wotapi.extension.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@Entity
public class Clan {

    @Id
    int clanId;
    String clanName;
    String clanTag;
    String clanImageUrl;
    @OneToMany
    List<Gamer> gamers;
}
