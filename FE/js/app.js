import {onSuccessGeolocation, onErrorGeolocation} from '../server/http/dustState.js';

import {getElement, getElements} from './util/dom.js'

window.addEventListener('DOMContentLoaded', () => {
  navigator.geolocation.getCurrentPosition(onSuccessGeolocation, onErrorGeolocation);

})
