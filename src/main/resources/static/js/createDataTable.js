function createOuterTable(data){
    let currentIndex = 0;
    let mainTable = "";
    let tableHead = "<thead><tr><th>Identifier</th><th>Name</th><th></th></tr></thead>"
    mainTable += tableHead;
    for (let key in data) {
        console.log(data[key]);
        let Id = data[key].ingredientId;
        let Name = data[key].commonName;
        let valid = data[key].valid;
        currentIndex += 1;
        let tableBody = "";
        if(valid){
            tableBody = `<tbody>
                            <tr>
                                <td>${Id}</td>
                                <td><a href="/ingredient/${Id}">${Name}</a></td>
                                <td><a id="button${currentIndex}" for="collapse${currentIndex}" data-toggle="collapse" href="#collapse${currentIndex}" aria-expanded="true" aria-controls="collapse${currentIndex}" class="btn btn-info" onClick="fetchReplacementData('${Name}', ${currentIndex}, ${Id})">View Replacements</a></td>
                            </tr>
                            <tr id="rowResult${currentIndex}" style="display: none;">       
                                <td colspan="3">   
                                    <div id="collapse${currentIndex}" class="collapse">            
                                        <div id="replacementDisplay${currentIndex}"></div>
                                    </div>
                                </td> 
                            </tr>
                        </tbody>`
        } else {
            tableBody = `<tbody>
                            <tr>
                                <td>${Id}</td>
                                <td><a href="/ingredient/${Id}">${Name}</a></td>
                                <td style="color: #727272">No Data For Ingredient</td>
                            </tr>
                            <tr></tr>
                         </tbody>`
        }

        mainTable += tableBody;
    }
    return {mainTable, currentIndex};
}


function validateIngredient(Id) {
    return fetch("/validate/" + Id)
        .then((response) => {
            return response.json().then((data) => {
                return data;
            }).catch((err) => {
                console.log(err);
            })
        });
}

function fetchReplacementData(term, index, id){
    let row = document.querySelector("#rowResult" + index);
    row.style.display = 'table-row';

    let button = document.querySelector("#button" + index);
    if (button.innerHTML === "View Replacements") {
        $(button).toggleClass('btn btn-info')
        $(button).addClass('btn btn-primary');
        button.innerHTML = "Hide Replacements";
    } else {
        button.innerHTML = "View Replacements";
        $(button).toggleClass('btn btn-primary');
        $(button).addClass('btn btn-info')
    }

    let replacementURL = 'replacement/search?term=' + term;
    fetch(replacementURL)
        .then(response => response.json())
        .then(data => createInnerReplacementTable(data, index, id))
        .catch(error => {
            console.log("Error! " + error);
        });
}



function createInnerReplacementTable(data, index, id){

    let display = document.querySelector("#replacementDisplay" + index);
    let table = "<table class=\"table table-striped\">";
    let header =   "<thead> " +
        "<tr> " +
        "<th>Ingredient</th>" + " <th title='This percentage shows how similar the molecular patterns are between the 2 ingredients. " +
        "The top 10 matching ingredients are shown.'>Percentage of Similarity</th> " +
        "</tr> " +
        "</thead>";
    table += header;
    for (let key in data) {
        let roundedPercentage = Math.round(data[key] * 100) / 100 + " %";
        table += `<tr><td><a href="/ingredient/${id}">${key}</a></td><td>${roundedPercentage}</td><td></td></tr>`;
    }
    table += "</table>";
    display.innerHTML = table;
}