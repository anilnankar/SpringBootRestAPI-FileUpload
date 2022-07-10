package com.an.restdemo;

public class Image {
	private Long id;
	private String filename;
	private String filepath;

	public Image() {

	}

	public Image(long id, String filename, String filepath) {
		super();
		this.id = id;
		this.filename = filename;
		this.filepath = filepath;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getfilename() {
		return filename;
	}

	public void setfilename(String filename) {
		this.filename = filename;
	}

	public String getfilepath() {
		return filepath;
	}

	public void setfilepath(String filepath) {
		this.filepath = filepath;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((filename == null) ? 0 : filename.hashCode());
		result = prime * result + ((filepath == null) ? 0 : filepath.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		Image other = (Image) obj;	
		if (id == null) {
			if (other.id != null)
				return false;
		}
		else if (!id.equals(other.id))
			return false;
		
		if (filename == null) {
			if (other.filename != null)
				return false;
		}
		else if (!filename.equals(other.filename))
			return false;

		if (filepath == null) {
			if (other.filepath != null)
				return false;
		}
		else if (!filepath.equals(other.filepath))
			return false;
		
		return true;
	}

}