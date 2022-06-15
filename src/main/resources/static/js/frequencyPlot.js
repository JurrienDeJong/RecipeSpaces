/*
 * author: Rose Hazenberg
 */

let frequency = {};
let recipes = {};

let recipeID;
let label;

function frequencyPlotViewer() {
    if (recipesExact.length !== 1) {
        for (let i = 0; i < htmlData.length; i++) {
            frequency[htmlData[i].name] = htmlData[i].count
            recipes[htmlData[i].name] = htmlData[i].recipe
        }

        function sortDictornary(dict) {
            const items = Object.keys(dict).map(function (key) {
                return [key, dict[key]];
            });

            items.sort(function (first, second) {
                return second[1] - first[1];
            });

            let sortedDict = {}, useKey, useValue
            $.each(items, function (k, v) {
                useKey = v[0]
                useValue = v[1]
                sortedDict[useKey] = useValue
            })
            return (sortedDict)
        }

        const sortedFrequency = sortDictornary(frequency);

        let pointBackgroundColors = [];

        const recipeData = {
            labels: Object.keys(sortedFrequency),
            datasets: [
                {
                    borderWidth: 1,
                    barThickness: 6,
                    data: Object.values(sortedFrequency),
                    backgroundColor: pointBackgroundColors,
                    hoverBackgroundColor: 'orange'
                }
            ],
        }

        const recipe = document.getElementById('myChart');
        const myChart = new Chart(recipe, {
            type: 'bar',
            data: recipeData,
            valueField: 'count',
            argumentField: 'name',
            options: {
                events: ['click'],
                legend: {
                    display: false
                },
                title: {
                    display: true,
                    text: `Total number per ingredient of all recipes of ${recipeName}`
                },
                scaleShowValues: true,
                scales: {
                    xAxes: [{
                        scaleLabel: {
                            display: true,
                            labelString: 'Ingredient'
                        },
                        ticks: {
                            display: true,
                            autoSkip: false,
                            maxRotation: 90,
                            minRotation: 90,
                        },
                        gridLines: {
                            display: false
                        }
                    }],
                    yAxes: [{
                        scaleLabel: {
                            display: true,
                            labelString: 'Frequency'
                        },
                        ticks: {
                            min: 0
                        },
                        gridLines: {
                            display: false
                        }
                    }]
                },
                plugins: {
                    tooltip: {
                        events: ['click']
                    }
                },
                onClick: (evt) => {
                    let activePoints = myChart.getElementsAtEventForMode(evt, 'point', myChart.options);
                    label = myChart.data.labels[activePoints[0]._index];
                },
                tooltips: {
                    enabled: false,
                    custom: function (tooltipModel) {
                        let tooltipEl = document.getElementById('custom-tooltip');
                        if (!tooltipEl) {
                            tooltipEl = document.createElement('div')
                            tooltipEl.id = 'custom-tooltip'

                            tooltipEl.innerHTML = '<table></table>'

                            document.body.appendChild(tooltipEl)
                        }

                        if (tooltipModel.opacity === 0) {
                            tooltipEl.style.opacity = 0;
                            return;
                        }
                        tooltipEl.classList.remove('above', 'below', 'no-transform',)

                        if (tooltipModel.yAlign) {
                            tooltipEl.classList.add(tooltipModel.yAlign);
                        } else {
                            tooltipEl.classList.add('no-transform');
                        }

                        function getBody(bodyItem) {
                            return bodyItem.lines;
                        }

                        if (tooltipModel.body) {
                            let titleLines = tooltipModel.title || []
                            let bodyLines = tooltipModel.body.map(getBody)

                            let innerHTML = '<thead>'

                            titleLines.forEach(function (title) {
                                innerHTML += `<tr><th>
                                               ${recipeName} recipes containing ${title}
                                               <div class="col-md-12" style="padding-left: 0">
                                                    <div class="info">
                                                        <i class="icon-info-sign" style="color: orange"></i>
                                                            <span class="extra-info">
                                                                Below there are the recipes which contain ${title}. These are clickable, which brings you to the recipe of ${recipeName} containing ${title}.
                                                                <br> In the list below is the ingredient that is clicked colored in orange and the other ingredient below to the recipe which is defined in the button.
                                                            </span>
                                                    </div>
                                               </div>
                                             </th></tr>`
                            })

                            innerHTML += '</thead><tbody>'

                            bodyLines.forEach(function (body, i) {
                                let colors = tooltipModel.labelColors[i]
                                let style = `
                                    background : ${colors.backgroundColor};
                                    border-color : ${colors.borderColor};
                                    border-width : 2px;
                                    `
                                let span = '<span style="' + style + '"></span>'
                                innerHTML += `<tr><td>Total number${span} ${body} / ${recipesExact.length} of ${recipeName}</td></tr>`
                            })

                            innerHTML += '</tbody>'

                            for (let i = 0; i < Object.keys(recipes).length; i++) {
                                if (Object.keys(recipes)[i] === label) {
                                    for (recipeID of Object.values(recipes)[i].split(",")) {

                                        fetchIngredients(recipeID);

                                        innerHTML += `<table>
                                                        <tr>
                                                        <td> 
                                                        <button class="button-style" id="${recipeID}" onclick="window.open(\'/recipe/${recipeID}\')">` + recipeName  + ": " + recipeID + `</button>
                                                        </td>
                                                        <ul id="${recipeID}" class="myList"></ul>
                                                        </tr>
                                                    </table>`
                                    }
                                }
                            }

                            let tableRoot = tooltipEl.querySelector('table')
                            tableRoot.innerHTML = innerHTML
                        }

                        let position = this._chart.canvas.getBoundingClientRect()

                        tooltipEl.classList.add('custom-tooltip')

                        tooltipEl.style.opacity = 1
                        tooltipEl.style.left = position.left + window.pageXOffset + tooltipModel.caretX + 'px'
                        tooltipEl.style.top = position.top + window.pageYOffset + tooltipModel.caretY + 'px'
                    }
                }
            }
        });

        for (let i = 0; i < myChart.data.datasets[0].data.length; i++) {
            if (myChart.data.datasets[0].data[i] > Math.round(recipesExact.length * 0.7)) {
                pointBackgroundColors.push("#2471A3");
            } else {
                pointBackgroundColors.push("#239B56");
            }
        }

        myChart.update();
    } else {
        console.log("There is no plot to show.")
        var elem = document.getElementById("chart");
        elem.parentNode.removeChild(elem);
    }

    async function fetchIngredients(id) {
        return await fetch(`/recipe/${id}/ingredients/False`)
            .then(response => response.json())
            .then(data => showIngredientsList(data, id))
            .catch(error => console.log((error)));
    }

    function showIngredientsList(data, id) {
        const list  = document.getElementsByClassName('myList');
        const recipeID = document.getElementById(id).id;

        for (let i = 0; i < list.length; i++) {
            data.forEach(item => {
                let li = document.createElement('li');
                if (list[i].id === recipeID) {
                    if (item.commonName === label) {
                        li.innerHTML = `<mark style="background-color: orange; color: black;">${item.commonName}</mark>`;
                        list[i].appendChild(li);
                    }
                    else {
                        li.innerText = item.commonName;
                        list[i].appendChild(li);
                    }
                }
            });
        }
    }
}