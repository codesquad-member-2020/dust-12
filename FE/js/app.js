import {requestDustState} from '../server/dustState.js';
import {css} from '../css/style.css';

import {getElement, show, hide, classRemove, classAdd} from './util/dom.js'

window.addEventListener('DOMContentLoaded', () => {
  requestDustState();
  getElement('.header').addEventListener('click', (e) => tabHandle(e));
});

const tabHandle = (e) => {
  const getHash = e.target.getAttribute('href');

  switch (getHash) {
    case '#dust' :
      classRemove('.tab-forecast', 'active');
      classAdd('.tab-dust', 'active');
      show('.dust');
      hide('.forecast');
      break;
    case '#forecast' :
      classRemove('.tab-dust', 'active');
      classAdd('.tab-forecast', 'active');
      show('.forecast');
      hide('.dust');
      break;
  }
}
