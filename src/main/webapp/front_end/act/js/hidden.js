const { body } = document;
const img = document.getElementsByClassName("nav-top-img")[0];

img.addEventListener("mouseover", function() {
  const weather = document.querySelector(".weather-div");
  const icon = document.querySelector(".icon");
  weather.style.visibility = "visible";
  icon.style.visibility = "visible";
  
});
img.addEventListener("mouseleave", function() {
  const weather = document.querySelector(".weather-div");
  const icon = document.querySelector(".icon");
  weather.style.visibility = "hidden";
  icon.style.visibility = "hidden";
  
});
