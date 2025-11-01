<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/includes/header.jsp" %>
	<div class="row justify-content-center">
		<div class="col-lg-12">
			<div class="card shadow mt-3 mb-4">
				<div class="card-header py-3">
					<h6 class="m-0 fw-bold text-primary">제품 조회</h6>
				</div>
				<div class="card-body">
					<div class="mb-3 input-group input-group-lg">
						<span class="input-group-text">제품 번호</span>
						<input type="text" class="form-control" value="<c:out value='${product.pno}'/>" readonly/>
					</div>
					<div class="mb-3 input-group input-group-lg">
						<span class="input-group-text">제품명</span>
						<input type="text" class="form-control" value="<c:out value='${product.pname}'/>" readonly/>
					</div>
					<div class="mb-3 input-group input-group-lg">
						<span class="input-group-text">제품 설명</span>
						<textarea class="form-control" rows="3" readonly><c:out value="${product.pdesc}"/></textarea>
					</div>
					<div class="mb-3 input-group input-group-lg">
						<span class="input-group-text">가격</span>
						<input type="text" class="form-control" value="<c:out value='${product.price}'/>" readonly />
					</div>
					<div class="mb-3 input-group input-group-lg">
						<span class="input-group-text">판매자</span>
						<input type="text" class="form-control" value="<c:out value='${product.writer}'/>" readonly/>
					</div>
					
					<div class="float-end">
						<a href="/product/list" class="btn">
							<button type="button" class="btn btn-info btnList">목록</button>
						</a>
						<a href="/product/modify/${product.pno}" class="btn">
							<button type="button" class="btn btn-warning btnModify">수정/삭제</button>
						</a>
					</div>
					
				</div>
			</div>
		</div>
	</div>
	
	<div class="mb-3">
		<label class="form-lable fw-bold">제품 사진</label>
		<div class="row mt-2">
			<c:forEach items="${product.imageList}" var="image">
				<div class="col-md-3 mb-3">
					<div class="card">
						<a href="/images/${image.uuid}_${image.fileName}" target="_blank">
							<img src="/images/${image.uuid}_${image.fileName}" class="card-img-top img-fluid" alt="Product Imgae">
						</a>
					</div>
				</div>		
			</c:forEach>
		</div>
	</div>
	
<%@ include file="/WEB-INF/views/includes/footer.jsp" %>