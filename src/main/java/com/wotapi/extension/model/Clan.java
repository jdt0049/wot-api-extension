package com.wotapi.extension.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Table
@Entity
@NoArgsConstructor
@AllArgsConstructor
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
    @OneToMany(targetEntity=Gamer.class, mappedBy="clan")
    List<Gamer> gamers;
}
