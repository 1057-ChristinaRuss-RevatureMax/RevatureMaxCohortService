/*
<td>Assessment label</td>
<td>Date</td>
<td>Score</td>
<td>Batch Average Score</td>
*/


// need to create a function that will take in the QC scores and assign them a weight. Then it will
// add that data into the table under th respective week they belong




	// "1": {
	// 	"Average": "Good",
	// 	"Notes": "This is a Qc note on week 1",
	// 	"Score": "Good",
	// 	"Type": "QC"
	//   },
	//   "2": {
	// 	"Average": "Average",
	// 	"Notes": "This is a Qc note on week 2",
	// 	"Score": "Good",
	// 	"Type": "QC"
	//   },




function get_qc_data() {
    // let url = "Zach's QC endpoint";
	// const response = await fetch(url);
    // let qcData = await response.json();

		let qcData = {"1": {"Average": "Good", "Notes": "This is a Qc note on week 1", "Score": "Good", "Type": "QC", "week": "Week 1"},
	  "2": {"Average": "Average", "Notes": "This is a Qc note on week 2", "Score": "Good", "Type": "QC","week": "Week 1"}}
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
				//console.log("I am inside the outer loop")
				Object.keys(qcData[key]).forEach(function(innerkey) {
					//console.log("I am inside the inner loop")
					var qctd = document.createElement("td")
					console.log(qcData[key][innerkey])
					if(innerkey !== "Notes"){
						//console.log("I am inside the if")
						//console.log(qcData[key][innerkey])
						//console.log(typeof qcData[key][innerkey])
						qctd.textContent = qcData[key][innerkey]
						qctr.appendChild(qctd)
					}
				});
			}
		  });
		  if(flag == true){
			  //console.log("Why am I not appending")
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
/*
{
  "data": {
    [
      aColumn : [
        avalue0,
        avalue1,
        avalue2
      ],
      bColumn : [
        bvalue0,
        bvalue1,
        bvalue2
      ],
      cColumn : [...],
      dColumn : [...],
      eColumn : [...],
      fColumn : [...]
    ]
  },
  "chartData": {
  legend0: [
    Y0,
    Y0 + (0.33 * (Y3 - Y0)),
    Y0 + (0.66 * (Y3 - Y0)),
    Y3,
    Y4,
    Y4 + (0.5 * (Y6 - Y4)),
    Y6
  ],
  legend1: [...],
  legend2: [...]
  }
}
*/
		console.log('final step:')
		console.log(response_dict)
		console.log(typeof(response_dict))
		if (typeof(response_dict) != 'undefined'){
			graphIt(response_dict["chartData"]);
			build_table(response_dict["data"]);
		}
	})
}

async function loadData(dataList){
    var chartData = [];
    const color = ['#eb4030', '#eb4030', '#eb4030'];
    const bColor = ['rgb(255, 99, 132)', 'rgb(255, 99, 132)', 'rgb(255, 99, 132)'];
    for(label in dataList){
        let i = 0;
        let setLabel = label; // ledgend label
        let setData =  Object.values(dataList[label]);  // data to be graphed
        let setColor = color[i]; // color of this datas background
        let setBorderColor = bColor[i]; // color of ths datas trace
        let parsedData = {label: setLabel, data: setData, backgroundColor: setColor, borderColor: setBorderColor, borderWidth: 1,};
        i++;
        chartData.push(parsedData);
    };
    return chartData;
}


async function graphIt(data){
	var Data = await loadData(data);
	console.log(Data[0])

	// Data[0].hidden = "true";

    var ctx = document.getElementById('gradesChart').getContext('2d');
    var myChart = new Chart(ctx, {
        type: 'line',
        data:{
            labels: ['Week 1', 'Week 2', 'Week 3', 'Week 3', 'Week 4', 'Week 5',
                'Week 6', 'Week 7', 'Week 8', 'Week 9', 'Week 10', 'Week 11'],
            datasets: Data,},

		options: {
			normalized: true,
			maintainAspectRatio: false,
			scales: {
				y: {
					beginAtZero: true,
				}
			}
		}
    });
	return myChart
 }

// let mockdata = { 'associate': [0, 10, 20, 30, 40, 50, 60 ,70, 80, 90], 'batch':[0, 10, 30, 20, 40, 60, 50], 'henry':[100, 100, 100, 100, 100, 100, 100, 100]}

let mockdata = { 'associate': [0, 10, 20, 30, 40, 50, 60 ,70, 80, 90]}
var chart = graphIt(mockdata);

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