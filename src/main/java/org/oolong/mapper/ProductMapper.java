package org.oolong.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.oolong.dto.ProductDTO;
import org.oolong.dto.ProductListDTO;

public interface ProductMapper {
	
	int insert(ProductDTO productDTO);
	int insertImages(ProductDTO productDTO);
	ProductDTO selectOne(@Param("pno") Integer pno);
	int deleteOne(@Param("pno") Integer pno);
	int deleteImages(@Param("pno") Integer pno);
	int updateOne(ProductDTO productDTO);
	List<ProductListDTO> list(@Param("skip") int skip, @Param("count") int count);
	int listCount();
}
