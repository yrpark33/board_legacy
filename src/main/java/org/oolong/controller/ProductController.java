package org.oolong.controller;

import java.io.IOException;
import java.util.List;

import org.oolong.dto.ProductDTO;
import org.oolong.dto.ProductListPagingDTO;
import org.oolong.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("product")
@Log4j2
@RequiredArgsConstructor
public class ProductController {
	
	private final ProductService productService;
	
	
	
	@GetMapping("register")
	public void registerGET() {
		log.info("product register GET");
	}
	
	@PostMapping("register")
	public String register(ProductDTO productDTO, @RequestParam("files") MultipartFile[] files, RedirectAttributes rttr) throws IOException {
		
		log.info("-----------------");
		log.info(productDTO);
		log.info(files);
		
		productService.register(productDTO, files);
		
		rttr.addFlashAttribute("registered", productDTO.getPno());
		
		return "redirect:/product/list";
		
	}
	
	@GetMapping("list")
	public void list(@RequestParam(name = "page", defaultValue = "1") int page, 
					@RequestParam(name = "size", defaultValue = "10") int size, Model model) {
		
		ProductListPagingDTO dto = productService.getList(page, size);
		model.addAttribute("dto", dto);
		
		
	}
	
	@GetMapping("read/{pno}")
	public String read(@PathVariable("pno") Integer pno, Model model) {
		
		model.addAttribute("product", productService.read(pno));
		
		return "/product/read";
		
	}
	
	
	@GetMapping("modify/{pno}")
	public String modifyGET(@PathVariable("pno") Integer pno, Model model) {
		
		model.addAttribute("product", productService.read(pno));
		
		return "/product/modify";
	}
	
	@PostMapping("remove")
	public String remove(@RequestParam("pno") Integer pno, RedirectAttributes rttr) {
		productService.remove(pno);
		rttr.addFlashAttribute("removed", pno);
		
		return "redirect:/product/list";
		
	}
	
	@PostMapping("modify")
	public String modify(ProductDTO productDTO, @RequestParam("files") MultipartFile[] files, @RequestParam("oldImages") String[] oldImages, @RequestParam(name = "removedImages", required = false) List<String> removedImages) throws Exception {
		
		productService.modify(productDTO, removedImages, oldImages, files);
		
		return "redirect:/product/read/" + productDTO.getPno();
		
	}
	
	
}
