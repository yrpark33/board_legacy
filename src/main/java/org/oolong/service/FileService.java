package org.oolong.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnails;

@Service
@Log4j2
public class FileService {
	
	@Value("${upload.path}")
	private String uploadPath;
	
	
	public List<String> uploadFiles(MultipartFile[] files) throws IOException {
			
			List<String> uploadNames = new ArrayList<>();
			
			if(files == null || files.length == 0) {
				return uploadNames;
			}
			
			
			
			for(MultipartFile file : files) {
				
				if(file.isEmpty()) {
					continue;
				}
				
				String fileName = file.getOriginalFilename();
				
				String uploadName = UUID.randomUUID().toString() + "_" + fileName;
				
				File targetFile = new File(uploadPath, uploadName);
				
				try (
					InputStream in = file.getInputStream();
					OutputStream out = new FileOutputStream(targetFile);	
				) {
					FileCopyUtils.copy(in, out);
					
					uploadNames.add(uploadName);
				} catch(Exception e) {
					log.error(e.getMessage());
					throw new RuntimeException(e.getMessage());
				}
				
				
				if(file.getContentType().startsWith("image")) {
					try {
						
						File thumbDir = new File(uploadPath, "thumbs");
						
						if(!thumbDir.exists()) thumbDir.mkdirs();
						
						Thumbnails.of(targetFile).size(200, 200).toFile(new File(thumbDir, "s_" + uploadName));
						
					} catch(IOException e) {
						e.printStackTrace();
					}
				}
				
			}
			
			return uploadNames;
			
	}
	
	public void deleteFiles(List<String> fileNames) {
		
		try {
			
			File uploadPath = new File(this.uploadPath);
			File thumbDir = new File(this.uploadPath, "thumbs");
			
			for(String fileName : fileNames) {
				
				File targetFile = new File(uploadPath, fileName);
				targetFile.delete();
				
				File targetThumb = new File(thumbDir, "s_" + fileName);
				targetThumb.delete();
				
			}
			
		} catch(Exception e) {
			log.error("파일 삭제 중 오류 발생", e);
		}
		
	}
	
	
	
	
}
