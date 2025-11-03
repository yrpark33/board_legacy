<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/includes/header.jsp" %>
	<div class="row justify-content-center">
		<div class="col-lg-12">
			<div class="card shadow mt-3 mb-4">
				<div class="card-header py-3">
					<h6 class="m-0 fw-bold text-primary">게시물 등록</h6>
				</div>
				<div class="card-body">
					<form action="/board/register" method="post" class="p-3">
						
						<div class="mb-3">
							<label class="form-label">제목</label>
							<input type="text" class="form-control" name="title" />
						</div>
						
						<div class="mb-3">
							<label class="form-label">내용</label>
							<textarea class="form-control" name="content" rows="3"></textarea>
						</div>
						
						<div class="mb-3">
							<label class="form-label">작성자</label>
							<input type="text" class="form-control" name="writer" />
						</div>
						
						<div class="d-flex justify-content-end">
							<button type="submit" class="btn btn-primary btn-lg me-2">등록</button>
							<a href="/board/list"><button type="button" class="btn btn-warning btn-lg">취소</button></a>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
<%@ include file="/WEB-INF/views/includes/footer.jsp" %>