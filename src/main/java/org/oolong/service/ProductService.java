package org.oolong.service;


import java.io.IOException;
import java.util.List;

import org.oolong.dto.ProductDTO;
import org.oolong.dto.ProductListDTO;
import org.oolong.dto.ProductListPagingDTO;
import org.oolong.mapper.ProductMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class ProductService {
	
	private final ProductMapper productMapper;
	private final FileService fileService;
	
	
	@Transactional
	public Integer register(ProductDTO productDTO, MultipartFile[] files) throws IOException {
		
		List<String> uploadNames = fileService.uploadFiles(files);
		
		try {
			productMapper.insert(productDTO);
		
		
				if(!uploadNames.isEmpty()) {
		
					uploadNames.forEach(name -> {
						
						int idx = name.indexOf("_");
						
						String uuid = name.substring(0, idx);
						String fileName = name.substring(idx + 1);
						
						log.info(uuid);
						log.info(fileName);
						
						productDTO.addImage(uuid, fileName);
					
					
					});
					

					productMapper.insertImages(productDTO);
					
				}
				
		}	catch (Exception e) {
			fileService.deleteFiles(uploadNames);
			throw e;
		}
		
		return productDTO.getPno();
		
	}
	
	
	public ProductListPagingDTO getList(int page, int size) {
		
		page = page <= 0 ? 1 : page;
		size = (size < 10 || size >= 100) ? 10 : size;
		
		int skip = (page - 1) * size;
		
		List<ProductListDTO> productDTOList  = productMapper.list(skip, size);
		int totalCount = productMapper.listCount();
		
		return new ProductListPagingDTO(productDTOList, totalCount, page, size);
		
		
	}
	
	public ProductDTO read(Integer pno) {
		
		return productMapper.selectOne(pno);
		
	}
	
	@Transactional
	public void remove(Integer pno) {
		
		productMapper.deleteOne(pno);
		
	}
	
	@Transactional
	public void modify(ProductDTO productDTO, List<String> removedImages, String[] oldImages, MultipartFile[] files) throws Exception {
		
		//catch에서 접근할 수 있게 바깥에 선언
		List<String> newFileNames = null;
		
		try {
			
			
			newFileNames = fileService.uploadFiles(files);
			
			
			//fileService.uploadFiles(files) 성공여부 확인
			log.info("새 파일 업로드 완료: {}", newFileNames);
			
			productMapper.deleteImages(productDTO.getPno());
			
			
			productMapper.updateOne(productDTO);
			
			
			
			
			
			
			if(oldImages != null && oldImages.length > 0) {
				
				for(String oldImage : oldImages) {
					
					int idx = oldImage.indexOf("_");
					String uuid = oldImage.substring(0, idx);
					String fileName = oldImage.substring(idx + 1);
					
					productDTO.addImage(uuid, fileName);
					
				}
				
			}
			
			if(newFileNames != null && !newFileNames.isEmpty()) {
				
				for(String newFileName : newFileNames) {
					
					int idx = newFileName.indexOf("_");
					String uuid = newFileName.substring(0, idx);
					String fileName = newFileName.substring(idx+1);
					
					productDTO.addImage(uuid, fileName);
					
				}
				
			}
			
			
			productMapper.insertImages(productDTO);
		
			
			if(removedImages != null && !removedImages.isEmpty()) {
				
				fileService.deleteFiles(removedImages);
				
			}
			
		} catch(Exception e) {
			
			log.error("상품 수정 중 오류 발생, 업로드 된 새 파일 삭제 수행", e);
			
			if(newFileNames != null && !newFileNames.isEmpty()) {
				
				try {
					
					fileService.deleteFiles(newFileNames);
				} catch(Exception ex) {
					log.error("롤백 중 업로드 파일 삭제 실패", ex);
				}
			}
			
			throw e;
		}
		
		
		
		
		
		
		
	}
	
	
	
	
}
