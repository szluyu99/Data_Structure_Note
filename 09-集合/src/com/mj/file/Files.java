package com.mj.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.IOException;

public class Files {
	
	/**
	 * 读取文件内容
	 * @param file
	 * @return
	 */
	public static FileInfo read(String file) {
		if (file == null) return null;
		FileInfo info = new FileInfo();
		StringBuilder sb = new StringBuilder();
		try (FileReader reader = new FileReader(file);
				BufferedReader br = new BufferedReader(reader)) {
            String line;
            while ((line = br.readLine()) != null) {
            	sb.append(line).append("\n");
            	info.setLines(info.getLines() + 1);
            }
            int len = sb.length();
            if (len > 0) {
                sb.deleteCharAt(len - 1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
		info.setFiles(info.getFiles() + 1);
		info.setContent(sb.toString());
		return info;
	}
	
	/**
	 * 读取文件夹下面的文件内容
	 * @param dir
	 * @param extensions
	 * @return
	 */
	public static FileInfo read(String dir, String[] extensions) {
		if (dir == null) return null;
		
		File dirFile = new File(dir);
		if (!dirFile.exists()) return null;

		FileInfo info = new FileInfo();
		dirFile.listFiles(new FileFilter() {
			public boolean accept(File subFile) {
				String subFilepath = subFile.getAbsolutePath();
				if (subFile.isDirectory()) {
					info.append(read(subFilepath, extensions));
				} else if (extensions != null && extensions.length > 0) {
					for (String extension : extensions) {
						if (subFilepath.endsWith("." + extension)) {
							info.append(read(subFilepath));
							break;
						}
					}
				} else {
					info.append(read(subFilepath));
				}
				return false;
			}
		});
		return info;
	}
}
