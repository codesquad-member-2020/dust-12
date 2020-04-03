import { getElement, getElements } from "../util/dom.js";
import { getDataDay, getDataTime } from '../../templates/dust.js';
import { STATE } from '../constant/dust.js';

export const dustEventHandle = (dustState) => {
  getElement('.dust-graph').addEventListener('scroll', (e) => {
    // console.log(e.currentTarget.scrollTop)
    const target = e.currentTarget;
    const scrollValue = target.scrollTop;
    const targetGap = (target.scrollHeight - target.offsetHeight) / 24;

    getElements('.graph-wrap').forEach((ele, idx) => {
      if(scrollValue >= targetGap * idx) {
        resetClassName(getElements('.graph-wrap'), 'on');
        setTargetDustState(idx, dustState);
        return ele.classList.add('on');
      }
    });
  });
}

const resetClassName = (targets, className) => {
  targets.forEach((ele) => {
    ele.classList.remove(className)
  })
}

const setTargetDustState = (targetIndex, dustState) => {
  getElement('.dust-state').className = `dust-state ${STATE[dustState[targetIndex].pm10Grade1h][2]}`;
  getElement('.state-emoji').textContent = STATE[dustState[targetIndex].pm10Grade1h][0];
  getElement('.state-msg').textContent = STATE[dustState[targetIndex].pm10Grade1h][1];
  getElement('.state-info .figure').innerHTML = `${dustState[targetIndex].pm10Value}<span>&micro;g/m³</span>`;
  getElement('.state-info .time').textContent = `${getDataDay(dustState[targetIndex].dataTime)} ${getDataTime(dustState[targetIndex].dataTime)}`;
}
