package com.tron.FoodHub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tron.FoodHub.entity.Setting;
import com.tron.FoodHub.service.CloudinaryService;
import com.tron.FoodHub.service.SettingService;

@RestController
@RequestMapping("/setting")
public class SettingController {
    
    @Autowired
    private SettingService settingService;
    
    @Autowired
    private CloudinaryService cloudinaryService;
    
    @GetMapping
    public ResponseEntity<Setting> getSetting() {
        Setting setting = settingService.getSetting();
        return ResponseEntity.ok(setting);
    }
    
    @PostMapping
    public ResponseEntity<Setting> createSetting(@RequestParam("businessName") String businessName,
                                                 @RequestParam("businessMobile") long businessMobile,
                                                 @RequestParam("businessEmail") String businessEmail,
                                                 @RequestParam("businessAddress") String businessAddress,
                                                 @RequestParam("businessGstNumber") String businessGstNumber,
                                                 @RequestParam("businessLogo") MultipartFile businessLogoFile,
                                                 @RequestParam("coverImage") MultipartFile coverImageFile) {
        try {
            String businessLogoUrl = cloudinaryService.uploadFile(businessLogoFile);
            String coverImageUrl = cloudinaryService.uploadFile(coverImageFile);

            Setting setting = new Setting();
            setting.setBusinessName(businessName);
            setting.setBusinessMobile(businessMobile);
            setting.setBusinessEmail(businessEmail);
            setting.setBusinessAddress(businessAddress);
            setting.setBusinessGstNumber(businessGstNumber);
            setting.setBusinessLogo(businessLogoUrl);
            setting.setCoverImage(coverImageUrl);

            Setting savedSetting = settingService.saveSetting(setting);
            return ResponseEntity.ok(savedSetting);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping
    public ResponseEntity<Setting> updateSetting(@RequestParam("businessName") String businessName,
                                                 @RequestParam("businessMobile") long businessMobile,
                                                 @RequestParam("businessEmail") String businessEmail,
                                                 @RequestParam("businessAddress") String businessAddress,
                                                 @RequestParam("businessGstNumber") String businessGstNumber,
                                                 @RequestParam("businessLogo") MultipartFile businessLogoFile,
                                                 @RequestParam("coverImage") MultipartFile coverImageFile) {
        try {
            Setting setting = settingService.getSetting();
            if (setting != null) {
                String businessLogoUrl = cloudinaryService.uploadFile(businessLogoFile);
                String coverImageUrl = cloudinaryService.uploadFile(coverImageFile);

                setting.setBusinessName(businessName);
                setting.setBusinessMobile(businessMobile);
                setting.setBusinessEmail(businessEmail);
                setting.setBusinessAddress(businessAddress);
                setting.setBusinessGstNumber(businessGstNumber);
                setting.setBusinessLogo(businessLogoUrl);
                setting.setCoverImage(coverImageUrl);

                Setting updatedSetting = settingService.saveSetting(setting);
                return ResponseEntity.ok(updatedSetting);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
}
