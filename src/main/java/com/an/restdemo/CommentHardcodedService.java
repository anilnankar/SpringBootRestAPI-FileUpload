package com.an.restdemo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

@Service
public class CommentHardcodedService {

	private static List<Comment> comments = new ArrayList<>();
	private static long idCounter = 0;

	static {
		comments.add(new Comment(++idCounter, "anil@gmail.com", "1657281923844", "Test", 1,  68500232));
		comments.add(new Comment(++idCounter, "test@gmail.com", "1657281908141", "Hi", 2, 84700520));
	}

	public List<Comment> findAll() {
		return comments;
	}

	public Comment save(Comment comment) {
		if (comment.getId() == null || comment.getId() == -1 || comment.getId() == 0) {
			comment.setId(++idCounter);
			comments.add(comment);
		} else {
			deleteById(comment.getId());
			comments.add(comment);
		}
		return comment;
	}

	public Comment deleteById(long id) {
		Comment comment = findById(id);

		if (comment == null)
			return null;

		if (comments.remove(comment)) {
			return comment;
		}

		return null;
	}

	public Comment findById(long id) {
		for (Comment comment : comments) {
			if (comment.getId() == id) {
				return comment;
			}
		}

		return null;
	}
}