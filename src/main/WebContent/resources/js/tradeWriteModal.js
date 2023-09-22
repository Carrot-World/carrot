const writeModal = new bootstrap.Modal("#writeModal");
const header = $("meta[name='_csrf_header']").attr('content');
const token = $("meta[name='_csrf']").attr('content');

function writeModalOpen(tradeId) {
  $.ajax({
    url: "/api/trade/writeModal/"+tradeId,
  }).done((data) => {
    document.querySelector("#writeModal .modal-content").innerHTML = data;
    writeModal.show();
  })
}

function updateTrade(tradeId, type) {
  const value = document.querySelector("#writeForm").value;
  if (value.length === 0) {
    return;
  }
  const content = value.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
  $.ajax({
    url: "/api/trade/updateTrade",
    method: "put",
    data: JSON.stringify({tradeId, type, content}),
    contentType: "application/json",
    beforeSend: (xhr) => xhr.setRequestHeader(header, token)
  }).done(() => {
    location.reload();
  })
}

