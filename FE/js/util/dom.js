export function getElement(target) {
  return document.querySelector(target);
}

export function getElements(target) {
  return document.querySelectorAll(target);
}

export function classAdd(target, className) {
  getElement(target).classList.add(className);
}

export function classRemove(target, className) {
  getElement(target).classList.remove(className);
}

export function hasClass(target, className) {
  return getElement(target).classList.contains(className);
}

export function show(target) {
  getElement(target).style.display = 'block';
}

export function hide(target) {
  getElement(target).style.display = 'none';
}
