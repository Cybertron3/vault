package com.tijori.vault;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "tijori")
@Data
public class TijoriEntity {

    @Id
    @Column(unique = true)
    private UUID id = UUID.randomUUID();

    @Column(name = "user_id")
    private String userId;

    @Column
    private String key;

    @Column
    private String salt;
}
