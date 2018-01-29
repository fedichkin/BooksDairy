package com.bookDairy.domain;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

/**
 * Created by Maryna Kontar.
 */
@Document(collection = "book")
public class Book {

	@Id
	private Long id;
	private String title;
	private String author;
	private String about;
	@DBRef
	private User user;

//TODO Image find how store image in mongo: with GridFsTemplate (File image) or like binary (Binary image) or
// save only link to image ign file system (or cloud)
//	private File image;
//	private Binary image;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
