package com.wotapi.extension.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table
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
