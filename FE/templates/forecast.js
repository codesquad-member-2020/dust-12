import { getElement} from '../js/util/dom.js';

const stateRender = (forecastState) => {
  return  `
    <h2>미세먼지 예보</h2>
    <div class="forecast-img">
        <div class="dust-img first-child"><img src="${forecastState.imageUrl1}" alt=""></div>
        <div class="dust-img"><img src="${forecastState.imageUrl1}" alt=""></div>
        <div class="dust-img"><img src="${forecastState.imageUrl2}" alt=""></div>
        <div class="dust-img"><img src="${forecastState.imageUrl3}" alt=""></div>
    </div>
    <p class="forecast-msg">${forecastState.informOverall}</p>
    <p class="regional-grade">${forecastState.informGrade}</p>
    <div class="controller">
        <button type="button"><span class="blind">재생</span></button>
        <div class="bar">
            <div class="active-bar"></div>
            <div class="handle-btn"></div>
        </div>
    </div>
  `
}

export const drwaForecastSection = (forecastState) => {
  getElement('.forecast').innerHTML = stateRender(forecastState); 
}



