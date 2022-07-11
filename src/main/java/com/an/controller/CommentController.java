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
public class CommentController {
	// Create instances of services
	@Autowired
	private CommentHardcodedService commentManagementService;

	// Return all comments
	@GetMapping("comment")
	public List<Comment> getAllComments() {
		return commentManagementService.findAll();
	}	

	// Get a particular comment by id
	@GetMapping("/comment/{id}")
	public Comment getComment(@PathVariable long id) {
		return commentManagementService.findById(id);
	}

	// Create a new comment
	@PostMapping("/comment")
	public ResponseEntity<Comment> createComment(@RequestBody Comment comment) {
		// Save comment
		Comment createdComment = commentManagementService.save(comment);
        return ResponseEntity.status(HttpStatus.CREATED).body(commentManagementService.findById(createdComment.getId()));
	}

	// Delete a particular comment by id
	@DeleteMapping("/comment/{id}")
	public ResponseEntity<Void> deleteComment(@PathVariable long id) {
		// Delete comment
		Comment comment = commentManagementService.deleteById(id);
		if (comment != null) {
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.notFound().build();
	}
}