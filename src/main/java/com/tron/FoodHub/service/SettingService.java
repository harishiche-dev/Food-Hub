package com.tron.FoodHub.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tron.FoodHub.entity.Setting;
import com.tron.FoodHub.repo.SettingRepository;

@Service
public class SettingService {
	
	@Autowired
	private SettingRepository settingRepository;
	
    public Setting saveSetting(Setting setting) {
        return settingRepository.save(setting);
    }
	
    public Setting updateSetting(long id, Setting setting) {
        Setting existingSetting = settingRepository.findById((int) id).orElseThrow(() -> new RuntimeException("Setting not found"));
        existingSetting.setBusinessName(setting.getBusinessName());
        existingSetting.setBusinessMobile(setting.getBusinessMobile());
        existingSetting.setBusinessEmail(setting.getBusinessEmail());
        existingSetting.setBusinessAddress(setting.getBusinessAddress());
        existingSetting.setBusinessGstNumber(setting.getBusinessGstNumber());
        existingSetting.setBusinessLogo(setting.getBusinessLogo());
        return settingRepository.save(existingSetting);
    }
     
    public Setting getSetting() {
        List<Setting> settings = settingRepository.findAll();
        return settings.isEmpty() ? null : settings.get(0);
    }
    
    public Setting getSettingById(long id) {
        return settingRepository.findById((int) id).orElseThrow(() -> new RuntimeException("Setting not found"));
    }
}
