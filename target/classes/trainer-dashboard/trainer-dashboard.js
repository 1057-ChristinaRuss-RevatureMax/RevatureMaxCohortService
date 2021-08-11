function changeTab(evt, tab) {
    // Declare all variables
    var i, tabcontent, tablinks;
  
    // Get all elements with class="tabcontent" and hide them
    tabcontent = document.getElementsByClassName("tabcontent");
    for (i = 0; i < tabcontent.length; i++) {
      tabcontent[i].style.display = "none";
    }
  
    // Get all elements with class="tablinks" and remove the class "active"
    tablinks = document.getElementsByClassName("tablinks");
    for (i = 0; i < tablinks.length; i++) {
      tablinks[i].className = tablinks[i].className.replace(" active", "");
    }
  
    // Show the current tab, and add an "active" class to the button that opened the tab
    document.getElementById(tab).style.display = "block";
    evt.currentTarget.className += " active";
  }



// This is where the javascript would go
// Select the elements that contains the tab
const tabs = document.querySelectorAll('[data-tab-target]');
const tabContent = document.querySelectorAll('[data-tab-content]');
// Loops through the elements every time it's click, show the content
tabs.forEach(tab => {
    tab.addEventListener('click', () =>{
        // Get the tab element dashboard, statistics, settings
        const target = document.querySelector(tab.dataset.tabTarget);
        // Remove active class
        tabContent.forEach(tabContent => {
            tabContent.classList.remove('active')
        });
        tabs.forEach(tab => {
            tab.classList.remove('active')
        });
        tab.classList.add("active");
        target.classList.add("active");
    });
});


// Instead of mockdata, we will be using a fetch request to call the endpoint from the Python Team
// Matthew Piazza knows more about this endpoint

// When we make a call to this endpoint, we will get back an email for each associate as well.
// For Python team to pull this data, we need to send them the batch ID we get from a cookie.

// To get the cookie, use document.cookie -- then we need to parse the cookie and pull the batch ID out. 

// javalin-cookie-store=eyJiYXRjaElEIjoiVFItMTAyMSJ9; batchID=TR-1021

let cookie = "javalin-cookie-store=eyJiYXRjaElEIjoiVFItMTAyMSJ9; batchID=TR-1021"

if (cookie.includes("batchID")){
    console.log(cookie.slice(cookie.search("batchID")))
}

// This is how we will parse the cookie so that we can return it in the fetch request


var mockdata = {0:{"Associate Name": ["BatchAverage", "name2", "name3"]},
                1:{"Quiz Score": ["77", "89", "34"]},
                2:{"Exam Score": ["34", "77", "89"]},
                3:{"Project Score": ["23", "77", "34"]},
                4:{"Verbal Score": ["55", "77", "22"]},
                5:{"Email": ["BATCH", "EMAIL1", "EMAIL2"]}}
// We need to convert the data to row format if possible

// build the table based on the mock data
// Need confirmation on how the data looks when I call the endpoint -- clarify with Matthew Piazza

function build_dashboard_table(tabledata) {
	let columns = [];
    let emails = [];
    let thead = document.getElementById("associate-table-head")
    let tr = document.createElement("tr")
	Object.keys(tabledata).forEach(outer=>{
        Object.keys(tabledata[outer]).forEach(x=>{
            if(x != "Email"){
                console.log(x)
                let th = document.createElement("th")
                th.textContent = x;
                tr.appendChild(th);
                columns.push(tabledata[outer][x]);
            }
            else {
                console.log("I am inside the else")
                console.log(x)
                emails.push(tabledata[outer][x])
            }
        });
    });

    thead.appendChild(tr);
	console.log(emails)
	let endi = columns[0].length;
	let endj = columns.length;
    let btbody = document.getElementById("batch-table-head");
	let tbody = document.getElementById("associate-table-data");
    let flag = true
	for(i = 0; i < endi; i++){
        
		let tr = document.createElement("tr");
        console.log(emails[0][i])
        // tr.setAttribute("onClick", "redirect(" +  "\"" + emails[0][i] + "\")")
        statsTab = document.getElementById("statistics-tab")
        dashTab = document.getElementById("dashboard-tab")
        statsTabBtn = document.getElementById("stats-tab-btn")
        dashTabBtn = document.getElementById("dash-tab-btn")
        tr.setAttribute("onClick", "statsTab.setAttribute(\"class\", \"active\"); dashTab.setAttribute(\"class\", \"none\"); statsTabBtn.setAttribute(\"class\", \"tab active\"); dashTabBtn.setAttribute(\"class\", \"tab\");")
        let btr = document.createElement("tr")
		tr.id = "row"+i;
		for(j = 0; j < endj; j++){
            if(i !== 0){
                
               
                let td = document.createElement("td")
			    td.textContent = columns[j][i] 
			    tr.appendChild(td)
            }
            else{
                let btd = document.createElement("td")
                btd.textContent = columns[j][i]
                btr.appendChild(btd)
            }
			
		}
        if(flag){
            btbody.appendChild(btr)
            flag = false;
        }
		tbody.appendChild(tr);
    };
    
}


build_dashboard_table(mockdata)


function associateFunction() {
    // Declare variables
    var input, filter, table, tr, td, i, txtValue;
    input = document.getElementById("searchBox");
    filter = input.value.toUpperCase();
    table = document.getElementById("associate-table");
    tr = table.getElementsByTagName("tr");
    console.log(tr)
    // Loop through all table rows, and hide those who don't match the search query
    //Add counter before the for loop 
    let count = 0;
    table.style.display = ""
    for (i = 1; i < tr.length; i++) {
      //for(j =0; j< tr[i].getElementsByTagName("td").length; j++){
      td = tr[i].getElementsByTagName("td")[0];
      console.log(td)
      // Try just grabbing the child element
      
      if (td) {
        txtValue = td.textContent || td.innerText;
        if (txtValue.toUpperCase().indexOf(filter) > -1) {
          tr[i].style.display = "";
          count++;
        } else {
          tr[i].style.display = "none";
        }
      }
    }
    if(count == 0){
        table.style.display = "none"
    }
    // If the counter is 0 after the for loop display none
  }

  function statsFunction() {
    // Declare variables
    var input, filter, table, tr, td, i, txtValue;
    input = document.getElementById("searchBox");
    filter = input.value.toUpperCase();
    table = document.getElementById("statistics-table");
    tr = table.getElementsByTagName("tr");
  
    // Loop through all table rows, and hide those who don't match the search query
    for (i = 0; i < tr.length; i++) {
      //for(j =0; j< tr[i].getElementsByTagName("td").length; j++){
      td1 = tr[i].getElementsByTagName("td")[0];
      td2 = tr[i].getElementsByTagName("td")[1];
      td3 = tr[i].getElementsByTagName("td")[2];
      td4 = tr[i].getElementsByTagName("td")[3];
      // Try just grabbing the child element
      
      if (td1 || td2 || td3 || td4) {
        txtValue1 = td1.textContent || td1.innerText;
        txtValue2 = td2.textContent || td2.innerText;
        txtValue3 = td3.textContent || td3.innerText;
        txtValue4 = td4.textContent || td4.innerText;
        if (txtValue1.toUpperCase().indexOf(filter) > -1 || txtValue2.toUpperCase().indexOf(filter) > -1 || txtValue3.toUpperCase().indexOf(filter) > -1 || txtValue4.toUpperCase().indexOf(filter) > -1) {
          tr[i].style.display = "";
        } else {
          tr[i].style.display = "none";
        }
      }
     // }
    }
  }


  function redirect(email) {
      window.location.href = "http://localhost/trainer-dashboard/" + email
  }













  function get_qc_data() {
    // let url = "Zach's QC endpoint";
	// const response = await fetch(url);
    // let qcData = await response.json();
    
		let qcData = {"1": {"Average": "Good", "Notes": "This is a Qc note on week 1", "Score": "Good", "Type": "QC", "week": "Week 1"}, 
	  "2": {"Average": "Average", "Notes": "This is a Qc note on week 2", "Score": "Good", "Type": "QC","week": "Week 2"}}
	  return qcData
}

function build_statistics_table(data){
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
			build_statistics_table(response_dict["data"]);
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

build_statistics_table(dict);
