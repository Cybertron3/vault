package com.tijori.vault;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "tijori")
public class TijoriEntity {

    @Id
    @Column
    private UUID id;

    @Column(name = "user_id")
    private String userId;

    @Column
    private String key;

    @Column
    private String salt;
}
