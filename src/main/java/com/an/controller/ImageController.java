package com.an.controller;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.an.message.ResponseMessage;
import com.an.model.Comment;
import com.an.model.Image;
import com.an.service.CommentHardcodedService;
import com.an.service.FilesStorageService;
import com.an.service.ImageHardcodedService;

// Allow CORS request from 3000 port
@CrossOrigin(origins = { "http://localhost:3000" })
@RestController
public class ImageController {
	// Create instances of services
	@Autowired
	FilesStorageService storageService;

	@Autowired
	private ImageHardcodedService imageManagementService;

	// Return all images
	@GetMapping("image")
	public List<Image> getAllImages() {
		return imageManagementService.findAll();
	}

	// Return a particular images
	@GetMapping("/image/{id}")
	public Image getImage(@PathVariable long id) {
		return imageManagementService.findById(id);
	}	
	
	// Upload a new image
	@PostMapping("/image")
	public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file,
			@RequestParam("filename") String name) {
		String message = "";
		try {
			// Upload file
			storageService.save(file);		

			// Get filename
			String filename = file.getOriginalFilename().toString();
			
			// Load file
			Resource filePath = storageService.load(filename);
			
			// Create image URL
		    String url = MvcUriComponentsBuilder.fromMethodName(ImageController.class, "getFile", filename.toString()).build().toString();
		      
		    // Create image object 
			Image image = new Image();
			image.setfilename(filename);
			image.setfilepath(url.toString());
			
			// Save image with URL
			imageManagementService.save(image);
		  	message = "Image uploaded successfully: " + file.getOriginalFilename();
		  	return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage(message));
		} catch (Exception e) {
			message = "Could not upload the file: " + file.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
	    }
	}
	
	// Download a image using filename
	@GetMapping("/files/{filename:.+}")
	public ResponseEntity<Resource> getFile(@PathVariable String filename) {
	    // Load file using name
		Resource file = storageService.load(filename);
		
		// Return file
	    return ResponseEntity.ok()
	        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
	}	  

	// Delete a image using id
	@DeleteMapping("/image/{id}")
	public ResponseEntity<Void> deleteImage(@PathVariable long id) {
		
		// Fetch image details using id
		Image image = imageManagementService.findById(id);
		File file = new File(image.getfilepath());
		
		// Delete the uploaded file 
        if(file.delete()) { 
            System.out.println(file.getName() + " is deleted!");
        } else {
            System.out.println("Delete operation is failed.");
        }

        // Delete image record
		if (imageManagementService.deleteById(id) != null) {
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.notFound().build();
	}
	
}