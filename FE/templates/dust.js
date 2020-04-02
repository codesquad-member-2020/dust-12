import { getElement, getElements } from '../js/util/dom.js';
import { STATE } from '../js/constant/dust.js'

//미세먼지 상태 templates 상단

const stateRender = ({dustState, station}) => {
  let graphHTML = '';

  dustState.forEach((item) => {
    graphHTML += `
      <div class="graph-wrap">
        <div class="graph ${STATE[item.pm10Grade1h][2]}" style="width:${Math.min(Number(item.pm10Value) / 2, 100)}%">${item.pm10Value}</div>
      </div>
    `;
  });

  return  `
    <div class="dust-state ${STATE[dustState[0].pm10Grade1h][2]}">
      <h2>미세먼지 앱</h2>
      <span class="state-emoji">${STATE[dustState[0].pm10Grade1h][0]}</span>
      <strong class="state-msg">${STATE[dustState[0].pm10Grade1h][1]}</strong>
      <div class="state-info">
        <div class="figure">${dustState[0].pm10Value}<span>&micro;g/m³</span></div>
        <div class="time">${getDataDay(dustState[0].dataTime)} ${getDataTime(dustState[0].dataTime)}</div>
      </div>
      <p class="position"><strong>${station.stationName}</strong> 측정소 기준</p>
    </div>
    <div class="dust-graph">${graphHTML}</div>
  `
}

export const drwaDustSection = ({dustState, station}) => {
  getElement('.dust').innerHTML = stateRender({dustState, station});
  getElement('.dust-graph').firstElementChild.classList.add('on');
}

export const getDataDay = (dataTime) => {
  const today = new Date().getDay();
  const dataDay = new Date(dataTime).getDay();
  if(today > dataDay) return '어제';
  return '오늘';
}

export const getDataTime = (date) => {
  return date.split(' ')[1];
}
