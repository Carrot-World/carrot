
function afterReplyInsert(id, content, time) {
        
const commentbox = document.querySelector("div.comment-box");
commentbox.innerHTML += `
		<div class="accordion" id="accordionPanelsStayOpenExample">
	        <div class="accordion-item">
	          <h2 class="accordion-header">
	            <button class="accordion-button" type="button" data-bs-toggle="collapse"
	              data-bs-target="#panelsStayOpen-collapseOne" aria-expanded="true"
	              aria-controls="panelsStayOpen-collapseOne">
	              댓글 ${id}<br />
	              댓글 ${content}<br/>
	              ${time}
	            </button>
	          </h2>
	          <div id="panelsStayOpen-collapseOne" class="accordion-collapse collapse show">
	            <div class="accordion-body">
	              <strong>대댓글 내용 </strong>
	            </div>
	          </div>
        </div>
`
}