import {drwaDustSection} from '../templates/dust.js';
import {dustEventHandle} from '../js/view/dustEvent.js';

export const getLocationState = () => {
  if(!navigator.geolocation) return alert('GPS 사용이 거부되었습니다.');

  navigator.geolocation.getCurrentPosition((position) => {
    const latitudeLongitude = {
      wgsX: position.coords.longitude,
      wgsY: position.coords.latitude
    }
    return requestDustState(latitudeLongitude);
  }, (error) => {
    alert(error.code, ' GPS 사용이 거부되었습니다.');
  });
}


const requestDustState = async (position) => {
  console.log(position);
  // const station = await fetchJSON(`http://52.7.37.34:8080/location?wgsX=${position.wgsX}&wgsY=${position.wgsY}`);
  // const dustState = await fetchJSON(`http://52.7.37.34:8080/dust?stationName=${station}`);
  // const station = {"stationName":"화랑로"};
  // const dustState = await fetchJSON(`http://localhost:8080/templates/mockdata.json`);
  
  const station = await fetchJSON(`http://localhost:8080/location?wgsX=${position.wgsX}&wgsY=${position.wgsY}`);
  const dustState = await fetchJSON(`http://localhost:8080/dust?stationName=${station}`);

  drwaDustSection({dustState, station});
  dustEventHandle(dustState);
}

const fetchJSON = (url) => {
  return fetch(url).then((response) => response.json());
}








// export const onSuccessGeolocation = async (position) => {
//   const latitudeLongitude = {
//     wgsX: position.coords.longitude,
//     wgsY: position.coords.latitude
//   }
//   console.log(latitudeLongitude);
//   return await getDustState(latitudeLongitude);
// }

// export const onErrorGeolocation = (error) => {
//   debugger;
//   console.log(error.code)
// }





// const getDustState = async (position) => {
//   await requestDustState(position).then((data) => {
//     fetch(`http://52.7.37.34:8080/dust?${new URLSearchParams(data)}`).then((response) => {
//       response.json();
//     }).then((data) => drwaDustSection(data));
//   });
  // return await fetch(`http://localhost:8080/templates/mockdata.json`).then(response => {
  //   // console.log(response)
  //   return response.json();
  // }).then((data) => drwaDustSection(data));
// }

// export const requestDustState = async (position) => {
//   console.log(`localhost:8080/?${new URLSearchParams(position)}`)
//   return await fetch(`http://52.7.37.34:8080/location?${new URLSearchParams(position)}`).then(response => {
//     return response.json();
//   });
//   // return await fetch(`http://localhost:8080/templates/mockdata.json`).then(response => {
//   //   return response.json();
//   // });
// }
