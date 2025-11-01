<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/includes/header.jsp" %>
	<div class="row justify-content-center">
		<div class="col-lg-12">
			<div class="card shadow mt-3 mb-4">
				<div class="card-header py-3">
					<h6 class="m-0 fw-bold text-primary">제품 수정</h6>
				</div>
				<div class="card-body">
					<form id="form1" action="/product/modify/${product.pno}" method="post" enctype="multipart/form-data">
						<div class="mb-3 input-group input-group-lg">
							<span class="input-group-text">제품 번호</span>
							<input type="text" class="form-control" name="pno" value="<c:out value='${product.pno}' />" readonly />
						</div>
						<div class="mb-3 input-group input-group-lg">
							<span class="input-group-text">제품명</span>
							<input type="text" class="form-control" name="pname" value="<c:out value='${product.pname}'/>" />
						</div>
						<div class="mb-3 input-group input-group-lg">
							<span class="input-group-text">제품 설명</span>
							<textarea class="form-control" name="pdesc" rows="3"><c:out value="${product.pdesc}"/></textarea>
						</div>
						<div class="mb-3 input-group input-group-lg">
							<span class="input-group-text">가격</span>
							<input type="number" class="form-control" name="price" value="<c:out value='${product.price}'/>" />
						</div>
						
						<div class="mb-3">
							<input type="file" name="files" multiple="multiple" class="form-control" />
						</div>
						
						<div class="mb-3 input-group input-group-lg">
							<span class="input-group-text">판매자</span>
							<input type="text" class="form-control" name="writer" value="<c:out value='${product.writer}'/>" readonly />
						</div>
						
						<div class="float-end">
							<button type="button" class="btn btn-info btnList">목록</button>
							<button type="button" class="btn btn-warning btnModify">수정</button>
							<button type="button" class="btn btn-danger btnRemove">삭제</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	
	<div class="mb-3 productImages">
		<label class="form-label fw-bold">제품 사진</label>
		<div class="row mt-2">
			<c:forEach items="${product.imageList}" var="image">
				<div class="col-md-3 mb-3">
					<div class="card">
						<a href="/images/${image.uuid}_${image.fileName}" target="_blank">
							<img src="/images/${image.uuid}_${image.fileName}" class="card-img-top img-fluid" alt="Product Image">
						</a>
						<button type="button" class="btn btn-danger btn-sm position-absolute top-0 end-0 m-2 delete-img-btn" data-file="${image.uuid}_${image.fileName}">삭제</button>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
	
<script>
	
	const form = document.getElementById("form1")

	
	document.querySelector(".btnRemove").addEventListener("click", (e) => {
		
		e.preventDefault()
		form.action = `/product/remove`
		form.submit()
		
		
	})
	
	document.querySelector(".productImages").addEventListener("click", (e) => {
		
		e.preventDefault()
		
		const target = e.target
		const fileName = target.getAttribute("data-file")
		
		if(!fileName) {
			return
		}
		
		const divObj = target.closest(".col-md-3")
		
		const str = `<input type='hidden' name='removedImages' value='\${fileName}' />`
		
		
		form.querySelector('div:last-child').insertAdjacentHTML("beforeend", str);
		
		
		
		divObj.remove()
		
		
	})
	
	
	document.querySelector(".btnList").addEventListener("click", (e) => {
		e.preventDefault()
		
		form.action = "/product/list"
		form.method = "get"
		
		form.submit()
	})
	
	document.querySelector(".btnModify").addEventListener("click", (e) => {
		
		e.preventDefault()
		
		form.action = "/product/modify"
		form.method = "post"
		
		const imageArr = document.querySelectorAll('.productImages button')
		
		if(imageArr) {
		
			let str = ''
			
			for(let image of imageArr) {
				
					const imageFile = image.getAttribute("data-file")
				
					str += `<input type='hidden' value='\${imageFile}' name='oldImages'/>`
					
				
			}
			
			
			form.querySelector("div:last-child").insertAdjacentHTML("beforeend", str)
			
		
		}
		
		
		form.submit()
		
		
	})
	
	
	
	
</script>
<%@ include file="/WEB-INF/views/includes/footer.jsp" %>