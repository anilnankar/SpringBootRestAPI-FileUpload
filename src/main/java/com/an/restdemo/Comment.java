package com.an.restdemo;

public class Comment {
	private Long id;
	private String email;
	private String time;
	private String description;
	private Integer imageId;
	private Integer pointId;

	public Comment() {

	}

	public Comment(long id, String email, String time, String description, Integer imageId, Integer pointId) {
		super();
		this.id = id;
		this.email = email;
		this.time = time;
		this.description = description;
		this.imageId = imageId;
		this.pointId = pointId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getemail() {
		return email;
	}

	public void setemail(String email) {
		this.email = email;
	}

	public String gettime() {
		return time;
	}

	public void settime(String time) {
		this.time = time;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getpointId() {
		return pointId;
	}

	public void setpointId(Integer pointId) {
		this.pointId = pointId;
	}
	
	public Integer getimageId() {
		return imageId;
	}

	public void setimageId(Integer imageId) {
		this.imageId = imageId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((time == null) ? 0 : time.hashCode());
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
		
		Comment other = (Comment) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} 
		else if (!description.equals(other.description))
			return false;
		
		if (id == null) {
			if (other.id != null)
				return false;
		}
		else if (!id.equals(other.id))
			return false;
		
		if (email == null) {
			if (other.email != null)
				return false;
		}
		else if (!email.equals(other.email))
			return false;

		if (time == null) {
			if (other.time != null)
				return false;
		}
		else if (!time.equals(other.time))
			return false;
		

		if (pointId == null) {
			if (other.pointId != null)
				return false;
		}
		else if (!pointId.equals(other.pointId))
			return false;
		
		return true;
	}

}