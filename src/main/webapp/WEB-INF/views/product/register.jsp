<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/includes/header.jsp" %>

	<div class="row justify-content-center">
		<div class="col-lg-12">
			<div class="card shadow mt-3 mb-4">
				<div class="card-header py-3">
					<h6 class="m-0 fw-bold text-primary">제품 등록</h6>
				</div>
				<div class="card-body">
					<form action="/product/register" method="post" class="p-3" enctype="multipart/form-data">
						<div class="mb-3">
							<label class="form-label">제품명</label>
							<input type="text" name="pname" class="form-control" />
						</div>
						<div class="mb-3">
							<label class="form-label">제품 설명</label>
							<textarea class="form-control" name="pdesc" rows="3"></textarea>
						</div>
						<div class="mb-3">
							<label class="form-label">가격</label>
							<input type="number" class="form-control" name="price" />
						</div>
						<div class="mb-3">
							<label class="form-label">제품 사진</label>
							<input type="file" name="files" class="form-control" multiple="multiple"/>
						</div>
						<div class="mb-3">
							<label class="form-label">판매자</label>
							<input type="text" class="form-control" name="writer" />
						</div>
						<div class="d-flex justify-content-end">
							<button type="submit" class="btn btn-primary me-1">등록</button>
							<a href="/product/list"><button type="button" class="btn btn-warning">취소</button></a>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

<%@ include file="/WEB-INF/views/includes/footer.jsp" %>