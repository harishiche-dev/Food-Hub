package com.tron.FoodHub.service;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;

@Service
public class CloudinaryImageServiceImpl implements CloudinaryImageService{
	
	@Autowired
	private Cloudinary  cloudinary;

	@Override
	public Map upload(MultipartFile file) {
		try {
			Map data = this.cloudinary.uploader().upload(file.getBytes(),Map.of());
			return data;
		} catch (IOException e) {
			throw new RuntimeException("image uploading failed..");
		}
	}
}
