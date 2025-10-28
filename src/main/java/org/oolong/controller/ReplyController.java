package org.oolong.controller;

import java.util.Map;

import org.oolong.dto.ReplyDTO;
import org.oolong.dto.ReplyListPagingDTO;
import org.oolong.service.ReplyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("replies")
public class ReplyController {

	private final ReplyService replyService;
	
	
	@PostMapping
	public ResponseEntity<Map<String, Long>> add(ReplyDTO replyDTO) {
		
		replyService.add(replyDTO);
		
		return ResponseEntity.ok(Map.of("result", replyDTO.getRno()));
		
	}
	
	
	@GetMapping("{bno}/list")
	public ResponseEntity<ReplyListPagingDTO> list(@PathVariable("bno") Long bno,
													@RequestParam(name = "page", defaultValue = "1") int page,
													@RequestParam(name = "size", defaultValue = "10") int size) {
		
		log.info("bno: " + bno);
		log.info("page: " + page);
		log.info("size: " + size);
		
		log.info("ReplyController.list: " + replyService.listOfBoard(bno, page, size));
		
		return ResponseEntity.ok(replyService.listOfBoard(bno, page, size));
	
	}
	
	@GetMapping("{rno}")
	public ResponseEntity<ReplyDTO> read(@PathVariable("rno") Long rno) {
		
		log.info("rno: " + rno);
		
		return ResponseEntity.ok(replyService.getOne(rno));
		
	}
	
	
	@DeleteMapping("{rno}")
	public ResponseEntity<Map<String, Long>> remove(@PathVariable("rno") Long rno) {
		
		replyService.remove(rno);
		
		return ResponseEntity.ok(Map.of("result", rno));
		
	}
	
	@PutMapping
	public ResponseEntity<Map<String, String>> modify(@RequestBody ReplyDTO replyDTO) {
		
		replyService.modify(replyDTO);
		
		return ResponseEntity.ok(Map.of("result", "modified"));
		
	}
	
}
