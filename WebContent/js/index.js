const main = () => {
  const buttonSearch = document.querySelector("#btnSearch");
  buttonSearch.addEventListener("click", (event) => {
    event.preventDefault();
    let inputSearch = document.querySelector("#inputSearch1");
    if (inputSearch.classList.contains("d-none")) {
      inputSearch.classList.remove("d-none");
    } else {
      inputSearch.classList.add("d-none");
    }
  });
};
var myCarousel = document.querySelector("#mycarousel");
var carousel = new bootstrap.Carousel(myCarousel, {
  interval: 2000,
  wrap: true,
});

// var details = document.getElementsByClassName("details");

function show_hide() {
  var details = document.getElementsByClassName("details");
  if (document.getElementById("show-hide").checked) {
    for (var i = 0; i < details.length; i++) {
      details[i].style.display = "inline";
    }
  } else {
    for (var i = 0; i < details.length; i++) {
      details[i].style.display = "none";
    }
  }
}

// Llamada de funciones
main();
