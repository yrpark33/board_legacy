package org.oolong.mapper;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.oolong.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.log4j.Log4j2;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j2
public class ProductMapperTests {
	
	@Autowired ProductMapper productMapper;
	
	
//	@Test
	@Transactional
	@Commit
	public void testInsert() {
		
		ProductDTO product = ProductDTO.builder().pname("Product").pdesc("Product Desc").writer("user1").price(4000).build();
		
		productMapper.insert(product);
		
		product.addImage(UUID.randomUUID().toString(), "test1.jpg");
		product.addImage(UUID.randomUUID().toString(), "test2.jpg");
		
		productMapper.insertImages(product);
		
		
	}
	
	
//	@Test
	public void testSelectOne() {
		Integer pno = 2;
		ProductDTO productDTO = productMapper.selectOne(pno);
		log.info(productDTO);
		productDTO.getImageList().forEach(log::info);
		
	}
	
	@Transactional
	@Commit
//	@Test
	public void testUpdateOne() {
		
		ProductDTO productDTO = ProductDTO.builder().pno(1).pname("Updated Product").pdesc("update").price(6000).build();
		
		productDTO.addImage(UUID.randomUUID().toString(), "test3.jpg");
		productDTO.addImage(UUID.randomUUID().toString(), "test4.jpg");
		productDTO.addImage(UUID.randomUUID().toString(), "test5.jpg");
		
		productMapper.deleteImages(productDTO.getPno());
		productMapper.updateOne(productDTO);
		productMapper.insertImages(productDTO);
		
	}
	
	@Transactional
	@Commit
//	@Test
	public void testInsertDummies() {
		
		for(int i = 0; i < 45; i++) {
			
			ProductDTO productDTO = ProductDTO.builder().pname("Product" + i).pdesc("Product Desc" + i).writer("user" + (i % 10)).price(4000).build();
			
			productMapper.insert(productDTO);
			
			productDTO.addImage(UUID.randomUUID().toString(), i + "_test_1.jpg");
			productDTO.addImage(UUID.randomUUID().toString(), i + "_test__2.jpg");
			
			log.info("---------------------");
			log.info(productDTO.getImageList());
			
			
			productMapper.insertImages(productDTO);
			
		}
		
	}
	
	
	@Test
	public void testList() {
		
		
		productMapper.list(0, 10).forEach(log::info);
		log.info(productMapper.listCount());
		
		
	}
	
	
}
