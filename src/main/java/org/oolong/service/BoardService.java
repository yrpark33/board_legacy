package org.oolong.service;

import java.util.List;

import org.oolong.dto.BoardDTO;
import org.oolong.dto.BoardListPagingDTO;
import org.oolong.mapper.BoardMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
@Transactional
public class BoardService {
	
	private final BoardMapper boardMapper;
	
	
	
	public BoardListPagingDTO getList(int page, int size, String typeStr, String keyword) {
		
		page = page <= 0 ? 1 : page;
		size = (size > 100 || size < 10) ? 10 : size;
		
		int skip = (page - 1) * size;
		
		String[] types = typeStr != null ? typeStr.split("") : null;
		
		List<BoardDTO> list = boardMapper.listSearch(skip, size, types, keyword);
		int total = boardMapper.listCountSearch(types, keyword);
		
		return new BoardListPagingDTO(list, total, page, size, typeStr, keyword);
	}
	
	public Long register(BoardDTO dto) {
		
		boardMapper.insert(dto);
		
		return dto.getBno();
	}
	
	public BoardDTO read(Long bno) {
		
		return boardMapper.selectOne(bno);
		
	}
	
	public void remove(Long bno) {
		
		boardMapper.delete(bno);
	
	}
	
	public void modify(BoardDTO dto) {
		boardMapper.update(dto);
	}
	
	

}
