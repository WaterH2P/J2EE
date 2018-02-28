package edu.nju.userfile.model;

import java.io.Serializable;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Stock
 *
 */
@Entity
@Table(name="files")
public class File implements Serializable {


	private static final long serialVersionUID = -1343255243959494483L;


	public File() {
		super();
	}

	private int id;
	private String fileName;
	private String fileSubject;
	private String filePath;
	private String fileType;
	private User user;


	@ManyToOne(optional=true)
	@JoinColumn(name="fileOwner")
	public User getUser(){
	return user;
	}
	public void setUser(User user) {
		this.user=user;
		}
	public void setId(int fileID) {
		this.id = fileID;
	}
	@Id
	public int getId() {
		return id;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileSubject(String fileSubject) {
		this.fileSubject = fileSubject;
	}
	public String getFileSubject() {
		return fileSubject;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public String getFileType() {
		return fileType;
	}

	
}
