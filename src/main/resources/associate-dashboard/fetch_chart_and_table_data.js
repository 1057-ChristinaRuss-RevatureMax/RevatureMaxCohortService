//http://localhost:5000/trainer/TR-1146
// This is the endpoint to populate the trainer table. It will also give emails to use for calling the other endpoints

//http://localhost:5000/reports/TR-1146/mock18.associate737560f9-bc2a-4bcb-b2b0-7413c333d623@mock.com/weekly
// This is the endpoint to get the associate data for the chart


// ASSOCIATE VIEW ENDPOINTS
//http://localhost:5000/grades/reports/TR-1146/spider/mock18.associate737560f9-bc2a-4bcb-b2b0-7413c333d623@mock.com
// This is the endpoint to get the associate data for the associate table

//http://localhost:5000/grades/reports/TR-1146/spider
// This is the endpoint to get the batch data for the associate table batch average


//http://localhost:5000/trainer/individual/grades/mock18.associate737560f9-bc2a-4bcb-b2b0-7413c333d623@mock.com
// This is for the trainer dashboard table 

function get_associate_data(email){
    // console.log(email)
	// let port = 5000;
	// let host = "http://localhost";
    // //+cookie.slice(cookie.search("batchID"))+
	// let endpoint = "/associate/TR-1190/spider/" + email; //TODO select endpoint for associate dashboard data
	// let url = host + ':' + port + endpoint;
    // console.log(url)
	// fetch(url, {
	// 	method: 'GET',
	// 	mode: 'cors',
	// }).then(response =>{
	// 	console.log('reply:')
	// 	console.log(response)
	// 	return response.json()
	// }).catch(err =>{
	// 	console.log('mistakes were made:')
	// 	console.log(err)
	// }).then(response_dict =>{

	// 	console.log('final step:')
	// 	console.log(response_dict)
	// 	console.log(typeof(response_dict))
	// 	if (typeof(response_dict) != 'undefined'){
	// 		return response_dict
	// 	}
	// })
    return {
        "data": {
          "Assessment Type": [
            "Unix", 
            "Hive", 
            "AWS", 
            "REST", 
            "CSS", 
            "DevOps", 
            "HTML", 
            "Spring Cloud", 
            "GCP", 
            "NoSQL", 
            "JavaScript", 
            "Jenkins", 
            "Helm", 
            "REST", 
            "Java", 
            "React", 
            "J2EE", 
            "Python", 
            "SOAP", 
            "Hibernate", 
            "Hadoop", 
            "Git"
          ], 
          "Batch Averages": [
            "56.65", 
            "54.32", 
            "54.01", 
            "47.09", 
            "38.30", 
            "46.73", 
            "56.35", 
            "45.62", 
            "47.61", 
            "46.52", 
            "48.73", 
            "42.74", 
            "49.65", 
            "44.77", 
            "44.37", 
            "51.57", 
            "55.70", 
            "50.90", 
            "54.93", 
            "65.52"
          ], 
          "My Score": [
            "53.10", 
            "45.62", 
            "58.02", 
            "42.46", 
            "46.33", 
            "42.64", 
            "50.11", 
            "52.81", 
            "56.86", 
            "61.01", 
            "46.98", 
            "61.38", 
            "50.49", 
            "60.54", 
            "56.33", 
            "35.76", 
            "54.04", 
            "46.77", 
            "60.41", 
            "38.98", 
            "46.49", 
            "45.91"
          ], 
          "Week #": [
            1, 
            1, 
            1, 
            1, 
            2, 
            2, 
            3, 
            3, 
            3, 
            3, 
            4, 
            4, 
            4, 
            5, 
            5, 
            5, 
            6, 
            6, 
            6, 
            7, 
            7, 
            7
          ], 
          "Weight": [
            100, 
            100, 
            100, 
            100, 
            100, 
            100, 
            100, 
            100, 
            100, 
            100, 
            100, 
            100, 
            100, 
            100, 
            100, 
            100, 
            100, 
            100, 
            100, 
            100, 
            100, 
            100
          ]
        }
      }
      
}

function get_qc_data(email){
    // console.log(email)
	// let port = 5000;
	// let host = "http://localhost";
    // //+cookie.slice(cookie.search("batchID"))+
    // //# localhost:5000/qa/notes/trainee/SF-2274
	// let endpoint = "/qa/notes/trainee/SF-2274/weekly"; //TODO select endpoint for associate dashboard data
	// let url = host + ':' + port + endpoint;
    // console.log(url)
	// fetch(url, {
	// 	method: 'GET',
	// 	mode: 'cors',
	// }).then(response =>{
	// 	console.log('reply:')
	// 	console.log(response)
	// 	return response.json()
	// }).catch(err =>{
	// 	console.log('mistakes were made:')
	// 	console.log(err)
	// }).then(response_dict =>{

	// 	console.log('final step:')
	// 	console.log(response_dict)
	// 	console.log(typeof(response_dict))
	// 	if (typeof(response_dict) != 'undefined'){
	// 		return response_dict
	// 	}
	// })

    return {"1": {"average": "Good", "notes": "This is a Qc note on week 1", "score": "Good", "type": "QC", "week": "Week 1"}, 
    "2": {"average": "Average", "notes": "This is a Qc note on week 2", "score": "Good", "type": "QC","week": "Week 2"}}
}


function build_statistics_table(email) {
    console.log(email)
    associateData = get_associate_data(email);
    qcData = get_qc_data()
    let rowDict = {}
    let rowArr = []
    
    for(let j = 0; j<Object.keys(associateData["data"]["Week #"]).length; j++){
        
        rowArr.push(associateData["data"]["Assessment Type"][j])
        console.log(associateData["data"]["Assessment Type"][j])
        rowArr.push("Week " + associateData["data"]["Week #"][j])
        rowArr.push(associateData["data"]["My Score"][j])
        rowArr.push(associateData["data"]["Batch Averages"][j])
        rowDict[j] = rowArr
        rowArr = []
        console.log(rowDict)
    }

    // create a new row, and add the data to the row
    let tbody = document.getElementById("stat_table");
    
    Object.keys(rowDict).forEach(row=>{
        let tr = document.createElement('tr');
        let j = 0;
        var next_week = "";
        console.log(Object.keys(rowDict).length)
        Object.keys(rowDict[row]).forEach(rowData=>{
            // This if makes it so we do not include the weight, or the trainer ID "resOfBatch"
            let td = document.createElement('td')
            if(rowData == 1 && Object.keys(rowDict).length>Number(row)+1){
                next_week = rowDict[Number(row)+1][rowData]
            }
            td = document.createElement('td')
            td.textContent = rowDict[row][rowData]
            tr.appendChild(td)
        });
        // Need to append the batch score to the tr
        tbody.appendChild(tr)

        console.log(qcData)
		//QC   Week 1    Good   Average
		let flag = false;
		var qctr;
        // let qcDatalen = Object.keys(qcData).length;
        // let nextWeek = 0;
		Object.keys(qcData).forEach(function(key) {
            // console.log(qcDatalen)
            // if(qcDatalen > Number(key)){
            //     nextWeek = Number(key) + 1;
            // }
                
            // console.log(key)    
            // console.log(nextWeek)

            let qcWeek  = qcData[key]["week"].replace(/\D/g,'');
            let temp = next_week.replace(/\D/g,'');
            console.log("This is the QC Week " + qcWeek)
            console.log("This is the next week" + temp)
			if (temp == (Number(qcWeek)+1)) {
                console.log(qcData[key]["week"])
                console.log(next_week)
				flag = true;
				qctr = document.createElement("tr");
				// Object.keys(qcData[key]).forEach(function(innerkey) {
				// 	var qctd = document.createElement("td")
				// 	if(innerkey !== "Notes"){
				// 		qctd.textContent = qcData[key][innerkey]
                        
				// 		qctr.appendChild(qctd)
				// 	}
				// });

                var qctd1 = document.createElement("td")
                qctd1.textContent = qcData[key]["type"]
                qctr.appendChild(qctd1)

                var qctd2 = document.createElement("td")
                qctd2.textContent = qcData[key]["week"]
                qctr.appendChild(qctd2)

                var qctd3 = document.createElement("td")
                qctd3.textContent = qcData[key]["score"]
                qctr.appendChild(qctd3)

                var qctd4 = document.createElement("td")
                qctd4.textContent = qcData[key]["average"]
                qctr.appendChild(qctd4)

                delete qcData[key]
			}
		  });
		  if(flag == true){
			  let tbody = document.getElementById("stat_table");
			  tbody.appendChild(qctr)
		  }
    });
    
    
}


const dummyData = { 'associate': [53.10, 
45.62, 
58.02, 
42.46, 
46.33, 
42.64, 
50.11, 
52.81, 
56.86, 
61.01, 
46.98, 
61.38, 
50.49, 
60.54, 
56.33, 
35.76, 
54.04, 
46.77, 
60.41, 
38.98, 
46.49, 
45.91], 'batch':[56.65, 
54.32, 
54.01, 
47.09, 
38.30, 
46.73, 
56.35, 
45.62, 
47.61, 
46.52, 
48.73, 
42.74, 
49.65, 
44.77, 
44.37, 
51.57, 
55.70, 
50.90, 
54.93, 
65.52]};
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


build_statistics_table("email from cookie")