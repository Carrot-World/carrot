class ChatRoom {
  constructor(roomId, userName, lastMessage, lastTime, unReadCnt) {
    this.roomId = roomId;
    this.userName = userName;
    // 마지막 메세지 내용
    this.lastMessage = lastMessage;
    // 마지막 메세지
    this.lastTime = lastTime;
    this.unReadCnt = unReadCnt;
    this.lastTimeMillis = new Date().getTime();
  }
}

class ChatMessage {
  constructor(writer, content, time) {
    this.writer = writer;
    this.content = content;
    this.time = time;
  }
}

const input = document.querySelector("#chatInput");
const sendBtn = document.querySelector("#send");
const chatHeader = document.querySelector(".chat-header");
const messageArea = document.querySelector("#messageArea");
const roomArea = document.querySelector("#roomArea");
const userName = document.querySelector("h2#userName").textContent;
const userId = document.querySelector("h2#userName").getAttribute("userId");
const chatContainer = document.querySelector(".chat-container");

const chatRooms = new Map();
const chatRoomElements = document.querySelectorAll("div.chat-room");
for (let i=0; i<chatRoomElements.length; i++) {
  const chatRoomElement = chatRoomElements[i];
  const roomId = Number(chatRoomElement.getAttribute("roomId"));
  const userName = chatRoomElement.querySelector("span.chat-user").textContent.trim();
  const lastMessage = chatRoomElement.querySelector("span.last-message").textContent.trim();
  const lastTime = chatRoomElement.querySelector("span.last-message-time").textContent.trim();
  const unReadCntBadge = chatRoomElement.querySelector("span.unReadCnt");
  let unReadCnt = 0;

  if (i === 0 && unReadCntBadge !== null) {
    unReadCntBadge.remove();
  }
  else if (unReadCntBadge !== null) {
    unReadCnt = Number(unReadCntBadge.textContent.trim());
  }
  chatRooms.set(roomId, new ChatRoom(roomId, userName, lastMessage, lastTime, unReadCnt));
}

let currRoomId = Number(chatContainer.getAttribute("roomId"));



const client = Stomp.over(new WebSocket(`ws://${location.host}/api/conn`));
client.connect({}, () => {
  chatRooms.forEach(room => {
    client.subscribe("/socket/message/"+room.roomId, onMessage);
  });
  client.subscribe("/socket/sub/"+userId, onSub);
  client.subscribe("/socket/reject/"+userId, onReject);
  client.subscribe("/socket/roomchange/"+userId, onRoomChange);
  if (currRoomId !== -1) {
    client.send("/socket/read/"+currRoomId, {}, {});
  }
});



const onMessage = (m) => {
  const {roomId, writerName, content, time} = JSON.parse(m.body);
  if (currRoomId === roomId) {
    messageArea.innerHTML += getMessageHtml(writerName, content, time);
    messageArea.scrollTop = messageArea.scrollHeight;
    chatRooms.get(roomId).lastMessage = content;
    chatRooms.get(roomId).lastTime = time;
    chatRooms.get(roomId).lastTimeMillis = new Date().getTime();
    // 소켓을 통해 서버에 읽었다고 알림
    if (writerName !== userName) {
      client.send("/socket/read/"+currRoomId, {}, {});
    }
  }
  else {
    chatRooms.get(roomId).lastMessage = content;
    chatRooms.get(roomId).lastTime = time;
    chatRooms.get(roomId).lastTimeMillis = new Date().getTime();
    chatRooms.get(roomId).unReadCnt++;
  }
  renderRoomArea();
}

const onSub = (m) => {
  const {roomId, writerName, content, time, destinationName} = JSON.parse(m.body);
  const partnerName = (writerName === userName) ? destinationName : writerName;
  chatRooms.set(roomId, new ChatRoom(roomId, partnerName, content, time, writerName === userName ? 0 : 1));
  if (currRoomId === -1) {
    renderChatArea(roomId, [{writerName, content, time}], partnerName);
    chatRooms.get(roomId).unReadCnt = 0;
    messageArea.scrollTop = messageArea.scrollHeight;
  }
  renderRoomArea();
  sendBtn.onclick = sendBtnHandler;
  client.subscribe("/socket/message/"+roomId,onMessage);
}

const onRoomChange = (m) => {
  const roomId = Number(m.headers.roomId);
  const messages = JSON.parse(m.body);
  const otherUserName = m.headers.sellerName === userName ? m.headers.buyerName : m.headers.sellerName;
  chatRooms.get(roomId).unReadCnt = 0;
  renderChatArea(roomId, messages, otherUserName);
  renderRoomArea();
}

const onReject = (m) => {
  alert("상대 유저가 퇴장하여 메세지를 보낼수가 없습니다.");
}

function renderRoomArea() {
  let arr = [];
  chatRooms.forEach(room => arr.push(room));
  arr.sort((a,b) => {
    if (a.lastTime === b.lastTime) {
      return a.lastTimeMillis < b.lastTimeMillis ? 1 : -1;
    }
    return a.lastTime < b.lastTime ? 1 : -1
  });
  let html = `<h2 class="username" id="userName">${userName}</h2>`
  for (const room of arr) {
    html += getRoomHtml(room);
  }
  roomArea.innerHTML = html;
}

function renderChatArea(roomId, messages, otherUserName) {
  chatHeader.innerHTML = getChatHeaderHtml(otherUserName, roomId);
  messageArea.innerHTML = getMessageAreaHtml(messages);

  currRoomId = roomId;
  chatContainer.setAttribute("roomId", roomId);
}

function getMessageHtml(writer, content, time) {
  return `
  <div class="message-wrapper ${writer === userName ? 'my' : ''}">
    <div class="card">
      <div class="card-body">
        <div class="card-title">${writer}</div>
        <div class="card-text">${content}</div>
        <div class="card-time">${time}</div>
      </div>
    </div>
  </div>
  `;
}

function getRoomHtml(room) {
  return `
   <div class="chat-room" roomId="${room.roomId}"  onclick="sendRoomChange(${room.roomId})">
      <div class="chat-room-header">
        <span class="chat-user">
          ${room.userName}
        </span>
        <span class="last-message-time">
          ${room.lastTime}
        </span>
      </div>
      <div class="chat-room-content">
        <span class="last-message">${room.lastMessage}</span>
        ${room.unReadCnt > 0 ? `<span class="badge bg-danger float-end">${room.unReadCnt}</span>` : ''}
      </div>
    </div>
  `;
}

function getChatHeaderHtml(otherUserName, roomId) {
  return `
  <h2>${otherUserName}</h2>
  ${roomId === -1 ? '' : '<button class=\"btn red-btn\" onclick=\"exitRoom()\">나가기</button>'}
  `
}

function getMessageAreaHtml(messages) {
  let html = '';
  for (let i=0; i<messages.length; i++) {
    const m = messages[i];
    html += getMessageHtml(m.writerName, m.content, m.time);
  }
  return html;
}

// 일반 메세지 전송
function sendBtnHandler() {
  let content = input.value;
  if (content.trim().length > 0) {
    content = content.trim().replaceAll("<", "&lt;").replaceAll(">", "&gt;");
    client.send("/socket/send/"+currRoomId, {}, JSON.stringify({content}));
    input.value = '';
  }
}

// 새로운방 생성
function sendBtnHandler2(destinationId, postId) {
  let content = input.value;
  if (content.trim().length > 0) {
    content = content.trim().replaceAll("<", "&lt;").replaceAll(">", "&gt;");
    const data = {destinationId, postId, content}
    client.send("/socket/newroom", {}, JSON.stringify(data));
    input.value = '';
  }
}

function sendRoomChange(roomId) {
  client.send("/socket/room/"+roomId, {}, {});
}

function exitRoom() {
  if (currRoomId === -1) {
    return;
  }
  client.unsubscribe("/socket/message/"+currRoomId, {});
  client.send("/socket/exit/"+currRoomId, {}, {});
  chatRooms.delete(currRoomId);
  renderRoomArea();
  renderChatArea(-1, [], '');
  const firstRoom = document.querySelector(".chat-room");
  if (firstRoom !== null) {
    const roomId = Number(firstRoom.getAttribute("roomId"));
    sendRoomChange(roomId);
  }
  else {
    renderChatArea(-1, [], '');
  }

}