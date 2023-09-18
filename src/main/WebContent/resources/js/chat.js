class ChatRoom {
  constructor(roomId, userName, lastMessage, lastTime, unReadCnt) {
    this.roomId = roomId;
    this.userName = userName;
    // 마지막 메세지 내용
    this.lastMessage = lastMessage;
    // 마지막 메세지
    this.lastTime = lastTime;
    this.unReadCnt = unReadCnt;
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
const messageArea = document.querySelector("#messageArea");
const roomArea = document.querySelector("#roomArea");
const userName = document.querySelector("h2#userName").textContent;

const chatRooms = new Map();
const chatRoomElements = document.querySelectorAll("div.chat-room");
for (let i=0; i<chatRoomElements.length; i++) {
  const chatRoomElement = chatRoomElements[i];
  const roomId = Number(chatRoomElement.getAttribute("roomId"));
  const userName = chatRoomElement.querySelector("span.chat-user").textContent;
  const lastMessage = chatRoomElement.querySelector("span.last-message").textContent;
  const lastTime = chatRoomElement.querySelector("span.last-message-time").textContent;
  const unReadCntBadge = chatRoomElement.querySelector("span.unReadCnt");
  let unReadCnt = 0;
  if (unReadCntBadge) {
    unReadCnt = unReadCntBadge.textContent;
  }
  chatRooms.set(roomId, new ChatRoom(roomId, userName, lastMessage, lastTime, unReadCnt));
}

let currRoomId = Number(document.querySelector(".chat-container").getAttribute("roomId"));



const client = Stomp.over(new WebSocket(`ws://${location.host}/api/conn`));
client.connect({}, () => {
  chatRooms.forEach(room => {
    client.subscribe("/socket/message/"+room.roomId, onRecieve)
  });
});

sendBtn.onclick = sendBtnHandler;


const onRecieve = (m) => {
  const {roomId, writer, content, time} = JSON.parse(m.body);
  if (currRoomId === roomId) {
    messageArea.innerHTML += getMessageHtml( writer, content, time);
    messageArea.scrollTop = messageArea.scrollHeight;
    chatRooms.get(roomId).lastMessage = content;
    chatRooms.get(roomId).lastTime = time;
    // 소켓을 통해 서버에 읽었다고 알림
  }
  else {
    chatRooms.get(roomId).lastMessage = content;
    chatRooms.get(roomId).lastTime = time;
    chatRooms.get(roomId).unReadCnt++;
  }
  let arr = [];
  chatRooms.forEach(room => arr.push(room));
  arr.sort((a,b) => a.lastTime > b.lastTime ? 1 : 0);
  renderRoomArea(arr);
}

function renderRoomArea(arr) {
  let html = `<h2 class="username" id="userName">${userName}</h2>`
  for (const room of arr) {
    html += getRoomHtml(room);
  }
  roomArea.innerHTML = html;
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
   <div class="chat-room" roomId="${room.roomId}">
      <div class="chat-room-header">
        <span class="chat-user">
          ${room.userName}
        </span>
        <span class="last-message-time">
          ${room.time}
        </span>
      </div>
      <div class="chat-room-content">
        <span class="last-message">${room.lastMessage}</span>
        ${room.unReadCnt > 0 ? `<span class="badge bg-danger float-end">${room.unReadCnt}</span>` : ''}
      </div>
    </div>
  `;
}

function sendBtnHandler() {
  client.send("/socket/send/"+currRoomId, {}, JSON.stringify({content: input.value}));
  input.value = '';
}