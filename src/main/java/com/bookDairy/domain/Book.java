package com.bookDairy.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

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

		//TODO find how store image in mongo: with GridFsTemplate (File image) or like binary (Binary image) or
	// save only link to image ign file system (or cloud)
//	private File image;
//	private Binary image;

	@DBRef //If you do not use @DBRef here, there will be an infinite number of nested entries in the document
	private List<Record> recordList;

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

	public List<Record> getRecordList() {
		return recordList;
	}

	public void setRecordList(List<Record> recordList) {
		this.recordList = recordList;
	}
}
