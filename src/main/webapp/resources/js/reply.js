	let currentPage = 1
	let currentSize = 10


	function getReplies(pageNum, goLast) {
			
		$.ajax({
			type: "GET",
			url: `/replies/${bno}/list`,
			data: {
				page: pageNum || currentPage,
				size: currentSize
			},
			dataType: "json",
			success: function(data) {
				console.log(data)
				
				const {totalCount, page, size} = data
				
				if(goLast && totalCount > page * size) {
					const lastPage = Math.ceil(totalCount / size)
					getReplies(lastPage)
				} else {
					currentPage = page
					currentSize = size
					
					printReplies(data)
				
				}
				
				
				
			},
			error: function(err) {
				console.log("댓글 목록 불러오기 실패: ", err)
			}
			
		})
			
	}
	
	
	function printReplies(data) {
		
		const {replyDTOList, totalCount, page, size, prev, next, start, end, pageNums} = data
		
		const replyList = document.querySelector(".replyList")
		
		let liStr = ``
		
		for(const replyDTO of replyDTOList) {
			
			liStr += `<li class="list-group-item" data-rno="${replyDTO.rno}">
							<div class="d-flex justify-content-between">
								<div>
									<strong>${replyDTO.rno}</strong> - ${replyDTO.replyText}
								</div>
								<div class="text-muted small">
									${replyDTO.regDate}
								</div>
							</div>
							<div class="mt-1 text-secondary small">
								${replyDTO.replier}
							</div>
						</li>`
			
		}
		
		replyList.innerHTML = liStr
		
		
		let pagingStr = ''
		
		if(prev) {
			pagingStr += `<li class="page-item">
							<a class="page-link" href="${start - 1}" tabindex="-1">이전</a>
						</li>`
		}
		
		for(let i of pageNums) {
			pagingStr += `<li class="page-item ${i === page ? 'active' : ''}">
							<a class="page-link" href="${i}">${i}</a>
						</li>`
		}
		
		if(next) {
			pagingStr += `<li class="page-item">
							<a class="page-link" href="${end + 1}">다음</a>
						</li>`
		}
		
		
		document.querySelector(".pagination").innerHTML = pagingStr
		
	}
	
	
	
	
	$(document).ready(function() {
  
		getReplies(1, true)
		
		$(".addReplyBtn").on("click", function(e) {
			
			e.preventDefault();

		    const formData = {
		      bno: $("input[name='bno']").val(),
		      replier: $("input[name='replier']").val(),
		      replyText: $("textarea[name='replyText']").val()
		    };

		    $.ajax({
		      type: "POST",
		      url: "/replies",
		      data: formData,  
		      success: function(res) {
		        alert(res.result + '번 댓글이 등록되었습니다.');
		        $("#replyForm")[0].reset();
		        getReplies(1, true)
		        
		        
		      },
		      error: function(err) {
		        console.error("댓글 등록 실패:", err);
		        alert("댓글 등록 중 오류가 발생했습니다.");
		      }
		    })
			
			
		})
		
		$(".pagination").on("click", function(e) {
			
			e.preventDefault()
			
			const target = e.target
			
			const href = target.getAttribute("href")
			
			
			if(!href) {
				
				return
				
			}
			
			
			getReplies(href, false)
			
			
		})
		
		const replyModal = new bootstrap.Modal(document.querySelector("#replyModal"))
		const replyModForm = document.querySelector("#replyModForm")
		
		$(".replyList").on("click", function(e) {
			
			const targetLi = e.target.closest("li")
			const rno = targetLi.getAttribute("data-rno")
			
			
			if(!rno) {
				return
			}
			
			$.ajax({
				type: "GET",
				url: `/replies/${rno}`,
				dataType: "json",
				success: function(data) {
					
					
					
					 if(data.delFlag === false) {
						replyModForm.querySelector("input[name='rno']").value = data.rno
						replyModForm.querySelector("input[name='replyText']").value = data.replyText
						replyModal.show()
					
					} else {
						alert("삭제된 댓글은 조회할 수 없습니다.")
					}
					
					
					
				},
				error: function(err) {
					console.error("댓글 상세 불러오기 실패:", err)
				}
			})
			
		})
		
		
		$(".btnReplyRem").on("click", function(e) {
			
			e.preventDefault()
			
			const formData = new FormData(replyModForm)
			
			const rno = formData.get("rno")
			
			console.log(rno)
			
			
			$.ajax({
				type: "DELETE",
				url: `/replies/${rno}`,
				success: function(res) {
					/*alert(res.result + `번 댓글이 삭제되었습니다.`)*/
					replyModal.hide()
					getReplies(currentPage)
				},
				error: function(err) {
					console.error("댓글 삭제 실패:", err)
				}
				
			})
			
			
		})
		
		
		$(".btnReplyMod").on("click", function(e) {
			
			e.preventDefault()
			
			
			$.ajax({
				type: "PUT",
				url: `/replies`,
				data: JSON.stringify({
					rno: $("input[name='rno']").val(),
					replyText: $("input[name='replyText']").val()
				}),
				contentType: "application/json",
				success: function(res) {
					console.log(res.result)
					replyModal.hide()
					getReplies(currentPage)
				},
				error: function(err) {
					console.error("댓글 수정 실패:", err)
				}
			})
			
			
			
		})
		
		

	});