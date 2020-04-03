import {drwaDustSection} from '../templates/dust.js';
import {dustEventHandle} from '../js/view/dustEvent.js';
import {drwaForecastSection} from '../templates/forecast.js';
import {forecastBtnHandler} from '../js/view/forecastEvent.js';

export const requestDustState = async () => {
  const station = await fetchJSON(`http://52.7.37.34:8080/location?wgsX=127.02928809999999&wgsY=37.4923615`);
  const dustState = await fetchJSON(`http://52.7.37.34:8080/dust?stationName=${station.stationName}`);
  const forecastState = await fetchJSON(`http://52.7.37.34:8080/forecast`);
  
  drwaDustSection({dustState, station});
  drwaForecastSection(forecastState);
  dustEventHandle(dustState);
  forecastBtnHandler();
}

const fetchJSON = (url) => {
  return fetch(url).then((response) => response.json());
}
