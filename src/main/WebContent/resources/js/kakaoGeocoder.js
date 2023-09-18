const geocoder = new kakao.maps.services.Geocoder();

function currLocBtnHandler() {
  navigator.geolocation.getCurrentPosition((position) => {
    const lat = position.coords.latitude;
    const lng = position.coords.longitude;
    geocoder.coord2RegionCode(lng, lat, render)
  })
}

async function render(result) {
  const addressObj = result[0].region_type === 'B' ? result[0] : result[1];
  const loc1 = addressObj.region_1depth_name;
  const loc2 = addressObj.region_2depth_name;
  const loc3 = addressObj.region_3depth_name;

  const loc2List = await getChildLoc("/api/loc/get2", {loc1})
  const loc3List = await getChildLoc("/api/loc/get3", {loc1, loc2});

  let html2 = "<option></option>";
  let html3 = "<option></option>";
  loc2List.forEach(loc => html2 += `<option value="${loc}">${loc}</option>`);
  loc3List.forEach(loc => html3 += `<option value="${loc}">${loc}</option>`);

  document.querySelector("#loc2").innerHTML = html2;
  document.querySelector("#loc3").innerHTML = html3;

  document.querySelector('#loc1').value = loc1;
  document.querySelector("#loc2").value = loc2;
  document.querySelector("#loc3").value = loc3;


}

function getChildLoc(url, data) {
  return new Promise((resolve, reject) => {
    $.ajax({url, data, method: "get", dataType:"json"}).done((data) => {
      resolve(data);
    })
  });
}