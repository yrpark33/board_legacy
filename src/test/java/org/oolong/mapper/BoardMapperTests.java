package org.oolong.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.oolong.dto.BoardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import lombok.extern.log4j.Log4j2;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j2
public class BoardMapperTests {
	
	@Autowired BoardMapper boardMapper;
	
//	@Test
	public void testInsert() {
		
		BoardDTO boardDTO = BoardDTO.builder().title("title").content("content").writer("user00").build();
		
		int insertCount = boardMapper.insert(boardDTO);
		log.info("----------------------------");
		log.info("insertCount: " + insertCount);
	}
	
//	@Test
	public void testInsert2() {
		
		BoardDTO boardDTO = BoardDTO.builder().title("title").content("content").writer("user00").build();
		
		int insertCount = boardMapper.insert(boardDTO);
		log.info("-----------------------------");
		log.info("insertCount: " + insertCount);
		log.info("=============================");
		log.info("BNO: " + boardDTO.getBno());
	}
	
//	@Test
	public void testSelectOne() {
		
		Long bno = 2L;
		
		BoardDTO board = boardMapper.selectOne(bno);
		log.info("board: " + board);
	}
	
//	@Test
	public void testDelete() {
		
		Long bno = 2L;
		int deleteCount = boardMapper.delete(bno);
		log.info("--------------------");
		log.info("deleteCount: " + deleteCount);
		
	}
	
//	@Test
	public void testUpdate() {
		
		BoardDTO boardDTO = BoardDTO.builder().bno(2L).title("Update Title").content("Update Content").build();
		int updateCount = boardMapper.update(boardDTO);
		log.info("--------------------");
		log.info("updateCount: " + updateCount);
		
	}
	
	
//	@Test
	public void testList() {
		boardMapper.list().forEach(log::info);
	}
	
//	@Test
	public void testList2() {
		
		int page = 2;
		int count = 10;
		int skip = (page - 1) * count;
		
		boardMapper.list2(skip, count).forEach(log::info);
		
	}
	
	
	@Test
	public void testListSearch() {
		
		int page = 2;
		
		int count = 10;
		
		int skip = (page - 1) * count;
		
		String[] types = new String[] {"T", "C", "W"};
		
		String keyword = "Test";
		
		boardMapper.listSearch(skip, count, types, keyword);
		
		
		
	}
	
	
	
}
