/* postDetail.js */

const nickname = document.querySelector("div.section").getAttribute("nickname");
const townpostid = document.querySelector("div.section").getAttribute("postid");

/* 게시글 수정 */
function btnEdit(num) {
	location.href = "/page/editpost/" + num;
}

/* 게시글 삭제 */
function btnDelete(num) {
	$.ajax({
		url: '/api/post/delete',           // 요청할 서버url
		dataType: 'text',       // 데이터 타입 (html, xml, json, text 등등)
		data: { 'id': num },
		success: function (result) { // 결과 성공 콜백함수
			console.log(result);
			alert('게시글이 삭제되었습니다.');
			window.location.href = result;
		},
		error: function (jqXHR, textStatus, errorThrown) { // 결과 에러 콜백함수
			console.log(jqXHR);  //응답 메시지
			console.log(textStatus); //"error"로 고정인듯함
			console.log(errorThrown);
		}
	})
}

var header = $("meta[name='_csrf_header']").attr('content');
var token = $("meta[name='_csrf']").attr('content');

/* 댓글 등록 */
function btnInsert(num) {
	const repl_content = document.querySelector('#reply-form').value
	let comment = {
		'townPostId': num,
		'parent': '0',
		'content': repl_content
	};

	$.ajax({
		url: '/api/post/insertreply',
		type: 'POST',
		dataType: 'json',
		contentType: 'application/json',
		data: JSON.stringify(comment),
		beforeSend: function (xhr) {
			xhr.setRequestHeader(header, token)
		},
		success: (data) => {
			console.log(data);
			document.querySelector('#reply-form').value = '';
			afterReplyInsert(data.nickname, repl_content, data.time, data.id)
		},
		error: () => {
			alert('등록 실패')
		}
	})
}

/* 댓글 삭제 */
function btnDeleteReply(num) {


	let numbers = {
		'id': num,
		'postid': townpostid
	};

	$.ajax({
		url: '/api/post/deletereply',
		type: 'POST',
		dataType: 'json',
		contentType: 'application/json',
		data: JSON.stringify(numbers),
		beforeSend: function (xhr) {
			xhr.setRequestHeader(header, token)
		},
		success: () => location.reload(),
		error: () => {
			alert('삭제 실패')
		}
	})


}

/* 대댓글 등록 */
function btnInsertReply(num) {
	const re_repl_content = document.querySelector('#re-reply-form').value

	let comment = {
		'townPostId': townpostid,
		'parent': num,
		'content': re_repl_content
	};

	$.ajax({
		url: '/api/post/insertreply',
		type: 'POST',
		dataType: 'json',
		contentType: 'application/json',
		data: JSON.stringify(comment),
		beforeSend: function (xhr) {
			xhr.setRequestHeader(header, token)
		},
		success: (data) => {
			console.log(data);
			document.querySelector('#re-reply-form').value = '';
			afterReReplyInsert(data.nickname, re_repl_content, data.time, data.id, data.parent)
			/* location.reload(); */
		},
		error: () => {
			alert('등록 실패')
		}
	})
}


function afterReplyInsert(nickname, content, time, id) { //댓글 등록 후 -> 

	const commentbox = document.querySelector("div.reply-container");
	commentbox.innerHTML += `
        <div class="reply-card">
						<div class="reply-header">
							<h5>${nickname}</h5>
							<span class="reply-time">${time}</span>
						</div>
						<div class="reply-content">
							<pre>
							${content}
							</pre>
						</div>
						<div class="reply-footer">
							<button class="btn orange-btn" onclick="btnReReplyList('${id}')">답글</button>
							<button class="btn red-btn" onclick="btnDeleteReply('${id}')">삭제</button>
						</div>
					</div>
`
}

function afterReReplyInsert(nickname, content, time, id, parent) { //대댓글 등록 후 ->

	const commentbox = document.querySelector("#reply"+parent);
	commentbox.innerHTML += `
        <div class="reply-card">
						<div class="reply-header">
							<h5>${nickname}</h5>
							<span class="reply-time">${time}</span>
						</div>
						<div class="reply-content">
							<pre>
							${content}
							</pre>
						</div>
						<div class="reply-footer">
							<button class="btn red-btn" onclick="btnDeleteReply('${id}')">삭제</button>
						</div>
					</div>
`
}


const createNestedReplyElement = (replyEl, id) => {

	console.log(id);

	$.ajax({
		url: '/api/post/rereplylist',
		type: 'POST',
		dataType: 'json',
		contentType: 'application/json',
		data: JSON.stringify({
			parent: id
		}),
		beforeSend: function (xhr) {
			xhr.setRequestHeader(header, token)
		},
		success: (data) => {
			const nestedReplyEl = document.createElement("div");
			nestedReplyEl.classList.add("reply-card-nested-list");
			data.forEach((e) => {
				nestedReplyEl.innerHTML += getNestedReplyHtml(e);
			})
			nestedReplyEl.innerHTML += `<label class="form-label">댓글작성</label>`
			nestedReplyEl.innerHTML += `<textarea class="form-control" id="re-reply-form"></textarea>`
			nestedReplyEl.innerHTML += `<div class="reply-form-button-wrapper">`
			nestedReplyEl.innerHTML += `<button class="btn orange-btn" onclick="btnInsertReply('${id}')">댓글쓰기</button>`
			nestedReplyEl.innerHTML += `</div>`
			replyEl.appendChild(nestedReplyEl);
			// console.log(data);
			// let html = "";
			// data.forEach((e) => {
			// 	html += `<div class="reply-card nested">`
			// 	html += `<div class="reply-header">`
			// 	html += `<h5>${e.nickname}</h5>`
			// 	html += `<span class="reply-time">${ new Date(e.created_at) } </span>`
			// 	html += `</div>`
			// 	html += `<div class="reply-content">`
			// 	html += `<pre>${e.content}</pre>`
			// 	html += `</div>`
			// 	html += `<div class="reply-footer nested">`
			// 	html += `<button class="btn red-btn" onclick="btnDeleteReply('${e.id}')">삭제</button>`
			// 	html += `</div>`
			// 	html += `</div>`
			// })

			// html += `<label class="form-label">댓글작성</label>`
			// html += `<textarea class="form-control" id="re-reply-form"></textarea>`
			// html += `<div class="reply-form-button-wrapper">`
			// html += `<button class="btn orange-btn" onclick="btnInsertReply(${id})">댓글쓰기</button>`
			// html += `</div>`

			// $(element).parent().parent().find(".reply-card-nested-list").append(html);
		}
	})
}

function btnReReplyList(id) {
	const replyEl = document.querySelector("#reply"+id);

	const nestedListEl = replyEl.querySelector(".reply-card-nested-list");
	console.log(nestedListEl);
	if (nestedListEl === null) {
		// ajax를통해 답글정보를 받는다.
		// 화면에 추가
		createNestedReplyElement(replyEl, id);
	}
	else {
		nestedListEl.remove();
	}
}

function getNestedReplyHtml(e) {
	return `
	<div class="reply-card nested">
		<div class="reply-header">
			<h5>${e.nickname}</h5>
			<span class="reply-time">${ new Date(e.created_at) }</span>
		</div>
		<div class="reply-content"><pre>${e.content}</pre></div>
		<div class="reply-footer nested">
			${e.nickname === nickname ? `<button class="btn red-btn" onclick="btnDeleteReply('${e.id}')">삭제</button>` : '' }
		</div>
	</div>
	`
}