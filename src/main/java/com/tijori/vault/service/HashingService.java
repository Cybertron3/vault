package com.tijori.vault.service;

import com.tijori.vault.TijoriEntity;

public interface HashingService {
    String getHashValue(String key, String salt);

    void setSaltAndKeyValues(TijoriEntity entity, String password);
}
