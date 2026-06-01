package com.example.demo.cache;

import com.example.demo.entity.ConfigJournalAppEntity;
import com.example.demo.reposiratory.ConfigJournalAppRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class AppCache
{
    @Autowired
    private ConfigJournalAppRepository configJournalAppRepository;

    public Map<String,String> APP_CACHE;

    @PostConstruct
    public  void init()
    {
        List<ConfigJournalAppEntity> all=configJournalAppRepository.findAll();
        for (ConfigJournalAppEntity configJournalAppEntity:all)
        {
            APP_CACHE.put(configJournalAppEntity.getKey(),configJournalAppEntity.getValue());
        }

    }

}
