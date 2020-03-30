package com.mj.file;

public class FileInfo {
	private int lines;
	private int files;
	private String content = "";
	
	public String[] words() {
		return content.split("[^a-zA-Z]+");
	}
	
	public int getFiles() {
		return files;
	}

	public void setFiles(int files) {
		this.files = files;
	}

	public int getLines() {
		return lines;
	}
	
	public void setLines(int lines) {
		this.lines = lines;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public FileInfo append(FileInfo info) {
		if (info != null && info.lines > 0) {
			this.files += info.files;
			this.lines += info.lines;
			this.content = new StringBuilder(this.content)
					.append("\n")
					.append(info.content)
					.toString();
		}
		return this;
	}
}
