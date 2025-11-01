package org.oolong.dto;

import lombok.Data;

@Data
public class ProductListDTO {
	
	private Integer pno;
	private String pname;
	private int price;
	private String writer;
	private boolean sale;
	private String fileName;
	private String uuid;
	
	
}
