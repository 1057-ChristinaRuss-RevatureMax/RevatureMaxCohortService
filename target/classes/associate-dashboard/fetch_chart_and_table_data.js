
function get_qc_data() {
    // let url = "Zach's QC endpoint";
	// const response = await fetch(url);
    // let qcData = await response.json();
    
		let qcData = {"1": {"Average": "Good", "Notes": "This is a Qc note on week 1", "Score": "Good", "Type": "QC", "week": "Week 1"}, 
	  "2": {"Average": "Average", "Notes": "This is a Qc note on week 2", "Score": "Good", "Type": "QC","week": "Week 2"}}
	  return qcData
}

function build_table(data){
	let thead_row = document.getElementById("stat_table_head");
	let columns = [];
	Object.keys(data).forEach(x=>{
		// use keys as column header names! fun!
		let th = document.createElement("th")
		th.textContent = x; //TODO maybe? make a dictionary that points from these names to names we want
		thead_row.appendChild(th);
		
		// pull out columns, we need rows later
		columns.push(data[x]);
		
	});

	//fill in table with "data"
	let endi = columns[0].length;
	let endj = columns.length;
	let tbody = document.getElementById("stat_table");
	for(i = 0; i < endi; i++){
		let tr = document.createElement("tr");
		tr.id = "row"+i;
		
		for(j = 0; j < endj; j++){
			let td = document.createElement("td")
			
		
			var current_week = columns[1][i]
			td.textContent = columns[j][i] // i & j swapped! we were given columns!
			tr.appendChild(td)
		}
		tbody.appendChild(tr);
		// This is where we need to append the QC row
		// Make a call to our function

		qcData = get_qc_data()
		//QC   Week 1    Good   Average
		let flag = false;
		var qctr;
		Object.keys(qcData).forEach(function(key) {
			if (qcData[key]["week"] == current_week) {
				flag = true;
				qctr = document.createElement("tr");
				Object.keys(qcData[key]).forEach(function(innerkey) {
					var qctd = document.createElement("td")
					if(innerkey !== "Notes"){
						qctd.textContent = qcData[key][innerkey]
						qctr.appendChild(qctd)
					}
				});
			}
		  });
		  if(flag == true){
			  let tbody = document.getElementById("stat_table");
			  tbody.appendChild(qctr)
		  }
	}
}

function get_table(){
	let port = 5000;
	let host = "https://localhost";
	let endpoint = "/test/"; //TODO select endpoint for associate dashboard data
	let url = host + ':' + port + endpoint;
	fetch(url, {
		method: 'GET',
		mode: 'cors',
	}).then(response =>{
		console.log('reply:')
		console.log(response)
		return response.json()
	}).catch(err =>{
		console.log('mistakes were made:')
		console.log(err)
	}).then(response_dict =>{

		console.log('final step:')
		console.log(response_dict)
		console.log(typeof(response_dict))
		if (typeof(response_dict) != 'undefined'){
			build_table(response_dict["data"]);
		}
	})
}

const dummyData = { 'associate': [0, 10, 20, 30, 40, 50, 60 ,70, 80, 90], 'batch':[0, 10, 30, 20, 40, 60, 50], 'henry':[100, 100, 100, 100, 100, 100, 100, 100]};
// function loadData() must be called after chart is declared in js file
// to change color of chart you only have change these color arrays
const fillColor = ['#72A4C299', '#FCB41499', '#F2692599'];
const lineColor = ['#72A4C2', '#FCB414', '#F26925'];

var ctx = document.getElementById('gradesChart').getContext('2d');
var myChart = new Chart(ctx, {
    type: 'line',
    data:{
        labels: ['Week 1', 'Week 2', 'Week 3', 'Week 3', 'Week 4', 'Week 5', 
            'Week 6', 'Week 7', 'Week 8', 'Week 9', 'Week 10', 'Week 11'],
        datasets: ""},
        
    options: {
        legend: {
            display: false
        },
        scales: {
            y: {
                beginAtZero: true,
            }
        }
    }
});

loadData(dummyData)

function loadData(dataList){
    var i = 0;
    for(label in dataList){
        let setLabel = label; // ledgend label
        let setData =  Object.values(dataList[label]);  // data to be graphed
        let setColor = fillColor[i]; // color of this datas background
        let setBorderColor = lineColor[i]; // color of ths datas trace
        let parsedData = {label: setLabel, data: setData, backgroundColor: setColor, borderColor: setBorderColor, borderWidth: 1, hidden: false,};
        
        myChart.data.datasets.push(parsedData);
        buildLegend(label, lineColor[i]);
        i++;
    };
    myChart.update();
}

function buildLegend(label, color){
    const legendChart = document.getElementById("chart-legend"); // This should be the container for the legend
    const legendItem = document.createElement('div');
    legendItem.setAttribute("class", "key-item");
    
    const button = document.createElement("button");
    button.onclick = function () { editChart(label)};
    legendItem.appendChild(button);
    button.style.backgroundColor = color;

    const labelTitle = document.createElement("p");
    labelTitle.innerHTML= label;
    labelTitle.onclick = function () { editChart(label)};
    legendItem.appendChild(labelTitle);

    legendChart.appendChild(legendItem);
}

function editChart(id){;
    myChart.data.datasets.forEach(function(ds) {
        if(ds.label == id)
            {ds.hidden = !ds.hidden;}
        console.log(id);
      });
        myChart.update();
}


/* the following is for testing purposes, remove when endpoint is mocked/ready... */
dict = {};
["label","date","score","batchAverage"].forEach(x=>{
	dict[x]=[];
	for(i = 0; i < 10; i++){
		if(x != "date"){
			dict[x].push("such a " + x + " " + i);
		}
		else{
			dict[x].push("Week "+ i);
		}
	};
});


build_table(dict);



// This can be used to hide data within the chart options . . .

// data:
// {
//         datasets: [
//         {
//             data: [1,2,3],
//             label: 'My First Dataset',
//             hidden: true,
//         },
//         ],
// }

document.getElementById("hidden").addEventListener('change', function() {
	if (this.checked) {
		chart.options.hidden = 'false'
		chart.update()
	} else {
		chart.options.hidden = 'true'
		chart.update()
	}
  });

