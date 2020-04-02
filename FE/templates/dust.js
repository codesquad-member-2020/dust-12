import { getElement } from "../../js/util/dom";

//미세먼지 상태 templates 상단

const stateRender = (data) => {
  return  `
    <div class="dust-state good">
      <h1>미세먼지 앱</h1>
      <span class="state-emoji">${data.state[0]}</span>
      <strong class="state-msg">${data.state[1]}</strong>
      <div class="state-info">
        <div class="figure">${data.figure}&micro;g/m³</div>
        <div class="time">${data.date} ${data.time}</div>
      </div>
      <p class="position"><strong>${data.position}</strong> 측정소 기준</p>
    </div>
  `
}

const drwaDustSection = (url) => {
  fetch(url).then((response) => {
    const data = JSON.parse(response);
    getElement('section').innerHTML = stateRender(data);
  })
}
