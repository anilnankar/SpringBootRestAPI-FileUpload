package com.an.restdemo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

@Service
public class ImageHardcodedService {

	private static List<Image> images = new ArrayList<>();
	private static long idCounter = 0;

	static {
		images.add(new Image(++idCounter, "a.jpg", "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__480.jpg"));
		images.add(new Image(++idCounter, "b.jpg", "https://images.unsplash.com/photo-1453728013993-6d66e9c9123a?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mnx8dmlld3xlbnwwfHwwfHw%3D&w=1000&q=80"));
	}

	public List<Image> findAll() {
		return images;
	}

	public Image save(Image image) {
		if (image.getId() == null || image.getId() == -1 || image.getId() == 0) {
			image.setId(++idCounter);
			images.add(image);
		} else {
			deleteById(image.getId());
			images.add(image);
		}
		return image;
	}

	public Image deleteById(long id) {
		Image image = findById(id);

		if (image == null)
			return null;

		if (images.remove(image)) {
			return image;
		}

		return null;
	}

	public Image findById(long id) {
		for (Image image : images) {
			if (image.getId() == id) {
				return image;
			}
		}

		return null;
	}
}