package com.ray.crm.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/images")
public class ImageController {
	
	@GetMapping(path = "/customer/{customerId}", produces = MediaType.IMAGE_JPEG_VALUE)
	public byte[] getProfileImage(@PathVariable("customerId") String customerId) throws IOException {
		String path = "/Users/ray/Dropbox/Aptech/C2202L/JavaEE/crm/src/main/resources/images";
		
		if (!Files.exists(Paths.get(path + File.separator + "customer_" + customerId + ".jpg"))) {
			return Files.readAllBytes(Paths.get(path + File.separator + "customer_8.jpg"));
		}
		
		return Files.readAllBytes(Paths.get(path + File.separator + "customer_" + customerId + ".jpg"));
	}
}
