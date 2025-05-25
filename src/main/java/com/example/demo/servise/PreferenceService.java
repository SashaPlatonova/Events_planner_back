package com.example.demo.servise;

import com.example.demo.entity.ClientPreference;
import com.example.demo.entity.Preference;
import com.example.demo.entity.PreferenceRequest;
import com.example.demo.repository.ClientPrefRepo;
import com.example.demo.repository.PreferenceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PreferenceService {

    @Autowired
    private PreferenceRepo repository;
    @Autowired
    private ClientPrefRepo prefRepository;

    public List<Preference> getAll() {
        return (List<Preference>) repository.findAll();
    }

    public List<Preference> getByClient(int id){
        List<ClientPreference> clientPreferences = prefRepository.getByClient(id);
        List<Preference> preferences = new ArrayList<>();
        for (ClientPreference clientPreference : clientPreferences) {
            preferences.add(clientPreference.getPreference());
        }
        return preferences;
    }

    public void addClientPreference(PreferenceRequest request){
        prefRepository.updatePrefList(request.getClient().getId());
        if(!request.getPreferences().isEmpty()) {
            for (Preference preference : request.getPreferences()) {
                prefRepository.addPreference(request.getClient().getId(), preference.getId());
            }
        }

    }

    public void addPref(Preference preference){
        repository.save(preference);
    }
}
