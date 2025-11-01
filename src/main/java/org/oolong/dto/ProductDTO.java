package org.oolong.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
	
	private Integer pno;
	private String pname;
	private String pdesc;
	private int price;
	private boolean sale;
	private String writer;
	private LocalDateTime regDate;
	private LocalDateTime modDate;
	
	private List<ProductImageDTO> imageList;
	
	
	public void addImage(String uuid, String fileName) {
		
		if(imageList == null) {
			imageList = new ArrayList<>();
		}
		
		ProductImageDTO imageDTO = ProductImageDTO.builder().pno(this.pno).uuid(uuid).fileName(fileName)
					.ord(this.imageList.size()).build();
		
		imageList.add(imageDTO);
		
		
	}
	
	public void clearImages() {
		this.imageList.clear();
	}
	
	
}
