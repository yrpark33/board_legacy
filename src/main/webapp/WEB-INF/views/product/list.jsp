<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/includes/header.jsp" %>
<style>
	.deleted-row {
		
		background-color: #f0f0f0;
		color: #888;
		text-decoration: line-through;
		font-style: 'italic';
	
	}
	
	.deleted-row img {
		opacity: 0.4;
	}
</style>


	<div class="row justify-content-center">
		<div class="col-lg-12">
			<div class="card shadow mt-3 mb-4">
				<div class="card-header py-3">
					<h6 class="m-0 fw-bold text-primary">제품 목록</h6>
				</div>
				<div class="card-body">
					<div class="d-flex justify-content-start mb-3">
						<a href="/product/register"><button type="button" class="btn btn-primary">제품 등록</button></a>
					</div>
					<table class="table table-bordered" id="dataTable">
						<thead>
							<tr>
								<th>제품번호</th>
								<th>제품명</th>
								<th>가격</th>
								<th>판매자</th>
							</tr>
						</thead>
						<tbody class="tbody">
							<c:forEach items="${dto.productDTOList}" var="product">
								<tr data-pno="${product.pno}" class="${not product.sale ? 'deleted-row' : ''}">
									<td><c:out value="${product.pno}"/></td>
									<td>
										<a href="/product/read/${product.pno}"><c:out value="${product.pname}"/></a>
										<img src="/images/thumbs/s_${product.uuid}_${product.fileName}" />
									</td>
									<td><c:out value="${product.price}"/></td>
									<td><c:out value="${product.writer}"/></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					
					
					<div class="d-flex justify-content-center">
						<ul class="pagination">
							<c:if test="${dto.prev}">
								<li class="page-item">
									<a href="" class="page-link" tabindex="-1">이전</a>
								</li>
							</c:if>
							<c:forEach items="${dto.pageNums}" var="num">
								<li class="page-item ${dto.page == num ? 'active' : ''}">
									<a class="page-link" href="${num}">${num}</a>
								</li>
							</c:forEach>
							<c:if test="${dto.next}">
								<li class="page-item">
									<a class="page-link" href="">다음</a>
								</li>
							</c:if>
						</ul>
					</div>
					
				</div>
			</div>
		</div>
	</div>
	
	
	<div class="modal fade" id="myModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h1 class="modal-title fs-5" id="exampleModalLabel">Modal title</h1>
	        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	      </div>
	      <div class="modal-body">
	        상품이 등록되었습니다.
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
	        <button type="button" class="btn btn-primary">Save changes</button>
	      </div>
	    </div>
	  </div>
	</div>
	
<script type="text/javascript" defer="defer">
	
	const registered = '${registered}'
	const removed = '${removed}'
	
	
	const myModal = new bootstrap.Modal(document.getElementById("myModal"))
	
	
	if(registered) {
		
		myModal.show()
		
	}
	
	if(removed) {
		
		document.querySelector(".modal-body").innerHTML = `\${removed}번 제품이 삭제되었습니다.`
		myModal.show()
		
	}
	
	const pagingDiv = document.querySelector(".pagination")
	
	pagingDiv.addEventListener("click", function(e) {
		
		e.preventDefault()
		
		const target = e.target
		const targetPage = target.getAttribute("href")
		
		if(!targetPage) {
			return
		}
		
		const size = ${dto.size} || 10
		
		const params = new URLSearchParams({
			page: targetPage,
			size: size
		})
		
		self.location = `/product/list?\${params.toString()}`
		
		
	})
	
</script>
	
<%@ include file="/WEB-INF/views/includes/footer.jsp" %>