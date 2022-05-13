// Función que devuelve cada carta
card = (
  img,
  treeName,
  description,
  commonName,
  otherNames,
  ecology,
  distribution,
  native
) => {
  return `
    <div class="col-lg-4 mb-4">
        <div class="card border-dark rounded-3">
              <div class="figure">
                <img class="card-img-top"
                  src="${img}"
                  alt="${treeName}">
              </div>              
              
            <div class="card-body">
                <h2 class="card-title my-2">
                    ${treeName}
                </h2>
                <h5 class="card-subtitle my-2 mb-2">
                    ${commonName}
                </h5>
                <p class="card-text paragraph">
                    ${description}
                </p>
                <a href="" class="btn btn-outline-primary">
                    Ver más
                </a>
            </div>
            <div class="details">
                <ul class="details list-group list-group-flush rounded-3">
                    <li class="list-group-item"><b>Otros nombres: </b>${otherNames}</li>
                    <li class="list-group-item"><b>Ecología: </b>${ecology}</li>
                    <li class="list-group-item"><b>Distribución: </b>${distribution}
                    </li>
                    <li class="list-group-item"><b>Autóctona: </b>${native}</li>
                </ul>
            </div>
        </div>
    </div>
    `;
};

// Base de datos de árboles

var req = new XMLHttpRequest();
req.open("GET", "./js/page/database.json", false);
req.send(null);
var trees = JSON.parse(req.responseText).trees;

let listOfCards = ""; // Variable para almacenar todo el script de las cartas

// Se recorre toda la base de datos
// Por cada objeto de la base de datos se llamada a la función que crea las cartas
// Cada script se concatena con lo anterior hasta tener todas las cartas en la variable listOfCards
for (var i in trees) {
  listOfCards = listOfCards.concat(
    card(
      trees[i].img,
      trees[i].name,
      trees[i].description.slice(0, 100) + "...",
      trees[i].commonName.slice(0, 50),
      trees[i].otherNames.slice(0, 50) + "...",
      trees[i].ecology.slice(0, 50) + "...",
      trees[i].distribution.slice(0, 50) + "...",
      trees[i].native
    ) + "\n"
  );
}

// Una vez teniendo todo el scripts con todas las cartas, se insertan en el apartado de la página
document.getElementById("cards-container").innerHTML = listOfCards;
