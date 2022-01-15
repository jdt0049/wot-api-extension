package com.wotapi.extension.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Data
@Entity
public class ClanWarsEvent {

    @Id
    @Column
    String eventName;
    @Column
    @ElementCollection
    List<String> fronts;
    @Column
    String apiReferenceName;
    @Column
    Boolean isActive;
}
