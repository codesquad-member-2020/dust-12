import { getElements, getElement } from "../util/dom";

export const forecastBtnHandler = () => {
  getElement('.handle-btn').addEventListener('touchmove', (e) => {
    moveControllereBtn(e);
  });

  getElement('.controller button').addEventListener('click', (e) => {
    playControllerBtn(e);
  })
}

const moveControllereBtn = (e) => {
  const deviceWidth = window.innerWidth;
  const barWidth = deviceWidth - 60;
  const handleBtnX = e.touches[0].clientX - 50;

  const handleBtnPercentX = (handleBtnX / barWidth) * 100;

  if(handleBtnPercentX < 0 || handleBtnPercentX > 100 - ((17 / barWidth) * 100)) return;
  controllImg(handleBtnPercentX);
  getElement('.handle-btn').style.left = handleBtnPercentX + '%';
  getElement('.active-bar').style.width = handleBtnPercentX + '%';
}

const playControllerBtn = () => {
  const deviceWidth = window.innerWidth;
  const barWidth = deviceWidth - 60;

  let start = null;

  function step(timestamp) {
    if (!start) start = timestamp;
    const progress = timestamp - start;
    const handleBtnPercentX = Math.min(progress / 20, 100 - ((17 / barWidth) * 100));
    getElement('.handle-btn').style.left = handleBtnPercentX + '%';
    getElement('.active-bar').style.width = handleBtnPercentX + '%';
    controllImg(handleBtnPercentX)
    if (progress < 2000) {
      window.requestAnimationFrame(step);
    }
  }
  window.requestAnimationFrame(step);
}

const controllImg = (percentX) => {
  const imgLength = getElements('.dust-img').length - 1;
  const interval = 1 / imgLength * 100;
  const imgIndex = Math.ceil(percentX / interval);
  getElements('.dust-img').forEach((ele, idx) => {
    if(!idx) return;
    ele.style.display = 'none';
  });
  getElements('.dust-img')[imgIndex].style.display = 'block';
}
