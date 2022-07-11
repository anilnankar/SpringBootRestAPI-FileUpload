package com.an.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import com.an.model.Image;

@Service
public class ImageHardcodedService {

	private static List<Image> images = new ArrayList<>();
	private static long idCounter = 0;

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