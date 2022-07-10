package com.an.restdemo;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.an.restdemo.*;

//@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:3001", "http://localhost:4200" })
@RestController
public class ImageResource {

	private static String imageDirectory = System.getProperty("user.dir") + "/images/";

	@Autowired
	private ImageHardcodedService imageManagementService;

	@Autowired
	private CommentHardcodedService commentManagementService;

	@GetMapping("image")
	public List<Image> getAllImages() {
		return imageManagementService.findAll();
	}

	@GetMapping("/image/{id}")
	public Image getImage(@PathVariable long id) {
		return imageManagementService.findById(id);
	}
	
	@PostMapping("/image")
    public ResponseEntity<?> uploadImage(@RequestParam("file")MultipartFile file,
                                         @RequestParam("filename") String name) {
    	       
        makeDirectoryIfNotExist(imageDirectory);
        Path fileNamePath = Paths.get(imageDirectory,
                name.concat(".").concat(FilenameUtils.getExtension(file.getOriginalFilename())));
        try {
            Files.write(fileNamePath, file.getBytes());
            Image image = new Image();
            image.setfilename(name);
            image.setfilepath(fileNamePath.toString());
    		Image createdImage = imageManagementService.save(image);
            return ResponseEntity.status(HttpStatus.CREATED).body(imageManagementService.findById(createdImage.getId()));
        } catch (IOException ex) {
        	return new ResponseEntity<String>("Image upload failed", HttpStatus.BAD_REQUEST);
        }
    }

	@DeleteMapping("/image/{id}")
	public ResponseEntity<Void> deleteImage(@PathVariable long id) {
		Image image = imageManagementService.deleteById(id);

		if (image != null) {
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("comment")
	public List<Comment> getAllComments() {
		return commentManagementService.findAll();
	}	

	@GetMapping("/comment/{id}")
	public Comment getComment(@PathVariable long id) {
		return commentManagementService.findById(id);
	}
	
	@PostMapping("/comment")
	public ResponseEntity<Comment> createComment(@RequestBody Comment comment) {
		Comment createdComment = commentManagementService.save(comment);
        return ResponseEntity.status(HttpStatus.CREATED).body(commentManagementService.findById(createdComment.getId()));
	}

	@DeleteMapping("/comment/{id}")
	public ResponseEntity<Void> deleteComment(@PathVariable long id) {
		Comment comment = commentManagementService.deleteById(id);
		if (comment != null) {
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.notFound().build();
	}
	
    private void makeDirectoryIfNotExist(String imageDirectory) {
        File directory = new File(imageDirectory);
        if (!directory.exists()) {
            directory.mkdir();
        }
    }
	
}