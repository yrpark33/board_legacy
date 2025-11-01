<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/includes/header.jsp" %>
	<div class="row justify-content-center">
		<div class="col-lg-12">
			<div class="card shadow mt-3 mb-4">
				<div class="card-header py-3">
					<h6 class="m-0 fw-bold text-primary">Board Read</h6>
				</div>
				<div class="card-body">
				
					<div class="mb-3 input-group input-group-lg">
						<span class="input-group-text">Bno</span>
						<input type="text" class="form-control" value="<c:out value='${board.bno}'/>" readonly />
					</div>
					<div class="mb-3 input-group input-group-lg">
						<span class="input-group-text">Title</span>
						<input type="text" class="form-control" value="<c:out value='${board.title}'/>" readonly />
					</div>
					<div class="mb-3 input-group input-group-lg">
						<span class="input-group-text">Content</span>
						<textarea class="form-control" rows="3" readonly><c:out value="${board.content}"/></textarea>
					</div>
					<div class="mb-3 input-group input-group-lg">
						<span class="input-group-text">Writer</span>
						<input type="text" class="form-control" value="<c:out value='${board.writer}'/>" readonly />
					</div>
					<div class="mb-3 input-group input-group-lg">
						<span class="input-group-text">RegDate</span>
						<input type="text" class="form-control" value="<c:out value='${board.createdDate}'/>" readonly />
					</div>
					
					<div class="float-end">
						<a href="/board/list" class="btn">
							<button type="button" class="btn btn-info btnList">LIST</button>
						</a>
						
						<c:if test="${!board.delFlag}">
							<a href="/board/modify/${board.bno}" class="btn">
								<button type="button" class="btn btn-warning btnModify">MODIFY</button>
							</a>
						</c:if>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<div class="col-lg-12">
		<div class="card shadow mb-4">
			<div class="m-4">
				<form id="replyForm" class="mt-4">
					<input type="hidden" name="bno" value="<c:out value='${board.bno}'/>"/>
					
					<div class="mb-3 input-group input-group-lg">
						<span class="input-group-text">Replier</span>
						<input type="text" class="form-control" name="replier" required/>
					</div>
					
					<div class="mb-3 input-group input-group-lg">
						<span class="input-group-text">ReplyText</span>
						<textarea class="form-control" name="replyText" rows="3" required></textarea>
					</div>
					
					<div class="text-end">
						<button type="submit" class="btn btn-primary addReplyBtn">Submit Reply</button>
					</div>
					
				</form>
			</div>
		</div>
	</div>
	
	
	<div class="col-lg-12">
		<div class="card shadow mb-4">
			<div class="m-4">
				<!-- 댓글 목록 -->
				<ul class="list-group replyList">
					<li class="list-group-item">
						<div class="d-flex justify-content-between">
							<div>
								<strong>번호</strong> - 댓글 내용
							</div>
							<div class="text-muted small">
								작성일
							</div>
						</div>
						<div class="mt-1 text-secondary small">
							작성자
						</div>
					</li>
				</ul>
				
				<div aria-label="댓글 페이지 네비게이션" class="mt-4">
					<ul class="pagination justify-content-center">
						<li class="page-item disabled">
							<a class="page-link" href="#" tabindex="-1">이전</a>
						</li>
						<li class="page-item">
							<a class="page-link" href="#">1</a>
						</li>
						<li class="page-item">
							<a class="page-link" href="#">2</a>
						</li>
						<li class="page-item">
							<a class="page-link" href="#">3</a>
						</li>
						<li class="page-item">
							<a class="page-link" href="#">다음</a>
						</li>
					</ul>
				</div>
			
			</div>
		</div>
	</div>
	
	
<div class="modal fade" id="replyModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="replyModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h1 class="modal-title fs-5" id="replyModalLabel">댓글 수정/삭제</h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <form id="replyModForm">
        	<input type="hidden" name="rno" value="33"/>
        	<div class="mb-3">
        		<label for="replyText" class="form-label">댓글 내용</label>
        		<input type="text" class="form-control" name="replyText" id="replyText" value="Reply Text" />
        	</div>
        </form>
        
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary btnReplyMod">수정</button>
        <button type="button" class="btn btn-danger ">삭제</button>
      	<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
      </div>
    </div>
  </div>
</div>
	
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script>
	const bno = ${board.bno}
</script>
<script src="${pageContext.request.contextPath}/resources/js/reply.js"></script>
<%@ include file="/WEB-INF/views/includes/footer.jsp" %>