//http://ec2-18-221-102-250.us-east-2.compute.amazonaws.com
function get_associate_data(email){
  console.log(email)
let port = 6500;
let host = "http://ec2-18-221-102-250.us-east-2.compute.amazonaws.com";
  //+cookie.slice(cookie.search("batchID"))+
let endpoint = "/associate/TR-1190/spider/" + email; //TODO select endpoint for associate dashboard data
let url = host + ':' + port + endpoint;
  console.log(url)
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
          console.log("Hello?")
    associateData = response_dict;
          let rowDict = {}
          let rowArr = []
          console.log(associateData)
      
          for(let j = 0; j<Object.keys(associateData["data"]["Week #"]).length; j++){
              
              rowArr.push(associateData["data"]["Assessment Type"][j])
              // console.log(associateData["data"]["Assessment Type"][j])
              rowArr.push("Week " + associateData["data"]["Week #"][j])
              rowArr.push(associateData["data"]["My Score"][j])
              rowArr.push(associateData["data"]["Batch Averages"][j])
              rowDict[j] = rowArr
              rowArr = []
              // console.log(rowDict)
          }

          // create a new row, and add the data to the row
          let tbody = document.getElementById("stat_table");
          
          Object.keys(rowDict).forEach(row=>{
              let tr = document.createElement('tr');
              let j = 0;
              var next_week = "";
              
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


              // let port = 5000;
              // let host = "http://ec2-18-221-102-250.us-east-2.compute.amazonaws.com";
              // //+cookie.slice(cookie.search("batchID"))+
              // //# localhost:5000/qa/notes/trainee/SF-2274
              // let endpoint = "/qa/notes/trainee/SF-2274/weekly"; //TODO select endpoint for associate dashboard data
              // let url = host + ':' + port + endpoint;
              // console.log(url)
              // fetch(url, {
              //     method: 'GET',
              //     mode: 'cors',
              // }).then(response =>{
              //     console.log('reply:')
              //     console.log(response)
              //     return response.json()
              // }).catch(err =>{
              //     console.log('mistakes were made:')
              //     console.log(err)
              // }).then(response_dict =>{

              //     console.log('final step:')
              //     console.log(response_dict)
              //     console.log(typeof(response_dict))
              //     if (typeof(response_dict) != 'undefined'){
              //         qcData = response_dict;
              //          //     let flag = false;
              //         var qctr;
              //         // let qcDatalen = Object.keys(qcData).length;
              //         // let nextWeek = 0;
              //         Object.keys(qcData).forEach(function(key) {
              //             // console.log(qcDatalen)
              //             // if(qcDatalen > Number(key)){
              //             //     nextWeek = Number(key) + 1;
              //             // }
                              
              //             // console.log(key)    
              //             // console.log(nextWeek)

              //             let qcWeek  = qcData[key]["week"].replace(/\D/g,'');
              //             let temp = next_week.replace(/\D/g,'');
              //             // console.log("This is the QC Week " + qcWeek)
              //             // console.log("This is the next week" + temp)
              //             if (temp == (Number(qcWeek)+1)) {
              //                 // console.log(qcData[key]["week"])
              //                 // console.log(next_week)
              //                 flag = true;
              //                 qctr = document.createElement("tr");
              //                 // Object.keys(qcData[key]).forEach(function(innerkey) {
              //                 // 	var qctd = document.createElement("td")
              //                 // 	if(innerkey !== "Notes"){
              //                 // 		qctd.textContent = qcData[key][innerkey]
                                      
              //                 // 		qctr.appendChild(qctd)
              //                 // 	}
              //                 // });

              //                 var qctd1 = document.createElement("td")
              //                 qctd1.textContent = qcData[key]["type"]
              //                 qctr.appendChild(qctd1)

              //                 var qctd2 = document.createElement("td")
              //                 qctd2.textContent = qcData[key]["week"]
              //                 qctr.appendChild(qctd2)

              //                 var qctd3 = document.createElement("td")
              //                 qctd3.textContent = qcData[key]["score"]
              //                 qctr.appendChild(qctd3)

              //                 var qctd4 = document.createElement("td")
              //                 qctd4.textContent = qcData[key]["average"]
              //                 qctr.appendChild(qctd4)

              //                 delete qcData[key]
              //             }
              //         });
              //         if(flag == true){
              //             let tbody = document.getElementById("stat_table");
              //             tbody.appendChild(qctr)
              //         }
              //     }  
              //})
         
          });
      }
  })
}

function get_chart_data(email){
  console.log(email)
let port = 6500;
let host = "http://ec2-18-221-102-250.us-east-2.compute.amazonaws.com";
  //+cookie.slice(cookie.search("batchID"))+
let endpoint = "/associate/TR-1190/" + email + "/weekly"; //TODO select endpoint for associate dashboard data
let url = host + ':' + port + endpoint;
  console.log(url)
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
    let chartdata = response_dict
          let count = 0;
          let associateweekly = []
          let batchweekly = []

          console.log(chartdata["chartData"]["Associate Exam Score"][i])
          for(let i = 0; i<chartdata["chartData"]["Associate Exam Score"].length; i++){
              let exam = Number(chartdata["chartData"]["Associate Exam Score"][i])
              
              if(exam != 0){
                  count++
              }
              let other = Number(chartdata["chartData"]["Associate Other Score"][i])
              if(other != 0){
                  count++
              }
              let presentation = Number(chartdata["chartData"]["Associate Presentation Score"][i])
              if(presentation != 0){
                  count++
              }
              let project = Number(chartdata["chartData"]["Associate Project Score"][i])
              if(project != 0){
                  count++
              }
              let verbal = Number(chartdata["chartData"]["Associate Verbal Score"][i])
              if(verbal != 0){
                  count++
              }
              if(count!=0){
                  associateweekly.push((exam+other+presentation+project+verbal)/count)
              }
              else{
                  associateweekly.push(0.00)
              }
              
              count = 0;
              

              let batchexam = Number(chartdata["chartData"]["Average Exam Score"][i])
              if(batchexam != 0){
                  count++
              }
              let batchother = Number(chartdata["chartData"]["Average Other Score"][i])
              if(batchother != 0){
                  count++
              }
              let batchpresentation = Number(chartdata["chartData"]["Average Presentation Score"][i])
              if(batchpresentation != 0){
                  count++
              }
              let batchproject = Number(chartdata["chartData"]["Average Project Score"][i])
              if(batchproject != 0){
                  count++
              }
              let batchverbal = Number(chartdata["chartData"]["Average Verbal Score"][i])
              if(batchverbal != 0){
                  count++
              }
              if(count!=0){
                  batchweekly.push((batchexam+batchother+batchpresentation+batchproject+batchverbal)/count)
              }
              else {
                  batchweekly.push(0.00)
              }
          }
          // console.log(batchweekly)

          // console.log(associateweekly)

          let chartstuff = {}
          chartstuff["Associate"] = associateweekly
          chartstuff["Batch"] = batchweekly
          console.log(chartstuff)
          loadData(chartstuff)

  }
})

}

// function get_qc_data(email){
//     console.log(email)
// 	let port = 5000;
// 	let host = "http://ec2-18-221-102-250.us-east-2.compute.amazonaws.com";
//     //+cookie.slice(cookie.search("batchID"))+
//     //# localhost:5000/qa/notes/trainee/SF-2274
// 	let endpoint = "/qa/notes/trainee/SF-2274/weekly"; //TODO select endpoint for associate dashboard data
// 	let url = host + ':' + port + endpoint;
//     console.log(url)
// 	fetch(url, {
// 		method: 'GET',
// 		mode: 'cors',
// 	}).then(response =>{
// 		console.log('reply:')
// 		console.log(response)
// 		return response.json()
// 	}).catch(err =>{
// 		console.log('mistakes were made:')
// 		console.log(err)
// 	}).then(response_dict =>{

// 		console.log('final step:')
// 		console.log(response_dict)
// 		console.log(typeof(response_dict))
// 		if (typeof(response_dict) != 'undefined'){
// 			return response_dict
// 		}
// 	})
// }

function get_dashboard_data(){
let port = 6500;
let host = "http://ec2-18-221-102-250.us-east-2.compute.amazonaws.com";
  //+cookie.slice(cookie.search("batchID"))+
let endpoint = "/trainer/TR-1190"; //TODO select endpoint for associate dashboard data
let url = host + ':' + port + endpoint;
  console.log(url)
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
          console.log(response_dict)
          let tabledata = response_dict;
          let flag = true
          let columns = [];
          let emails = [];
          let thead = document.getElementById("associate-table-head")
          let btbody = document.getElementById("batch-table-head");
          let tbody = document.getElementById("associate-table-data");
          let tr = document.createElement("tr")
          console.log(tabledata)
          Object.keys(tabledata).forEach(outer=>{
              console.log(outer)
              if(outer != "Email"){
                  let th = document.createElement("th")
                  th.textContent = outer;
                  tr.appendChild(th);
              }

          });

          thead.appendChild(tr)
          let btr = document.createElement("tr");
          for(let i = 0; i<Object.keys(tabledata["Email"]).length; i++){
                  let tr=document.createElement("tr")
                  statsTab = document.getElementById("statistics-tab")
                  dashTab = document.getElementById("dashboard-tab")
                  statsTabBtn = document.getElementById("stats-tab-btn")
                  dashTabBtn = document.getElementById("dash-tab-btn")
                  console.log(emails)
                  tr.setAttribute("onClick", "statsTab.setAttribute(\"class\", \"active\"); dashTab.setAttribute(\"class\", \"none\"); statsTabBtn.setAttribute(\"class\", \"tab active\"); dashTabBtn.setAttribute(\"class\", \"tab\");  clear_data(); get_associate_data(\"" + tabledata["Email"][i] + "\"); get_chart_data(\"" + tabledata["Email"][i] + "\");")

                  if(i !== 0){
                      let td1 = document.createElement("td")
                      td1.textContent = tabledata["Associate Name"][i]
                      
                      let td2 = document.createElement("td")
                      td2.textContent = tabledata["Exam Score"][i].toFixed(2)
                      
                      let td3 = document.createElement("td")
                      td3.textContent = tabledata["Presentation Score"][i].toFixed(2)
                      
                      let td4 = document.createElement("td")
                      td4.textContent = tabledata["Project Score"][i].toFixed(2)
                      
                      let td5 = document.createElement("td")
                      td5.textContent = tabledata["Verbal Score"][i].toFixed(2)

                      tr.appendChild(td1)
                      tr.appendChild(td2)
                      tr.appendChild(td3)
                      tr.appendChild(td4)
                      tr.appendChild(td5)
                  }
                  else{
                      let btd1 = document.createElement("td")
                      btd1.textContent = tabledata["Associate Name"][i]
                      
                      let btd2 = document.createElement("td")
                      btd2.textContent = tabledata["Exam Score"][i].toFixed(2)
                      
                      let btd3 = document.createElement("td")
                      btd3.textContent = tabledata["Presentation Score"][i].toFixed(2)
                      
                      let btd4 = document.createElement("td")
                      btd4.textContent = tabledata["Project Score"][i].toFixed(2)
                      
                      let btd5 = document.createElement("td")
                      btd5.textContent = tabledata["Verbal Score"][i].toFixed(2)

                      btr.appendChild(btd1)
                      btr.appendChild(btd2)
                      btr.appendChild(btd3)
                      btr.appendChild(btd4)
                      btr.appendChild(btd5)
                      
                  }
                      
                  if(flag){
                      btbody.appendChild(btr)
                      flag = false;
                  }
                  tbody.appendChild(tr);

          }
      }
  })
}
   


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
              5:{"Email": ["BATCH", "mock18.associate737560f9-bc2a-4bcb-b2b0-7413c333d623@mock.com", "EMAIL2"]}}
// We need to convert the data to row format if possible

// build the table based on the mock data
// Need confirmation on how the data looks when I call the endpoint -- clarify with Matthew Piazza



function associateFunction() {
  // Declare variables
  var input, filter, table, tr, td, i, txtValue;
  input = document.getElementById("searchBox");
  filter = input.value.toUpperCase();
  table = document.getElementById("associate-table");
  tr = table.getElementsByTagName("tr");

  // Loop through all table rows, and hide those who don't match the search query
  //Add counter before the for loop 
  let count = 0;
  table.style.display = ""
  for (i = 1; i < tr.length; i++) {
    //for(j =0; j< tr[i].getElementsByTagName("td").length; j++){
    td = tr[i].getElementsByTagName("td")[0];

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



function get_qc_data() {
  // let url = "Zach's QC endpoint";
// const response = await fetch(url);
  // let qcData = await response.json();
  
  let qcData = {"1": {"average": "Good", "notes": "This is a Qc note on week 1", "score": "Good", "type": "QC", "week": "Week 1"}, 
  "2": {"average": "Average", "notes": "This is a Qc note on week 2", "score": "Good", "type": "QC","week": "Week 2"}}
  return qcData
}


function build_statistics_table(email) {
  
  associateData = get_associate_data(email);
  qcData = get_qc_data()
  let rowDict = {}
  let rowArr = []
  
  for(let j = 0; j<Object.keys(associateData["data"]["Week #"]).length; j++){
      
      rowArr.push(associateData["data"]["Assessment Type"][j])
      // console.log(associateData["data"]["Assessment Type"][j])
      rowArr.push("Week " + associateData["data"]["Week #"][j])
      rowArr.push(associateData["data"]["My Score"][j])
      rowArr.push(associateData["data"]["Batch Averages"][j])
      rowDict[j] = rowArr
      rowArr = []
      // console.log(rowDict)
  }

  // create a new row, and add the data to the row
  let tbody = document.getElementById("stat_table");
  
  Object.keys(rowDict).forEach(row=>{
      let tr = document.createElement('tr');
      let j = 0;
      var next_week = "";
      
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

      // console.log(qcData)
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
          // console.log("This is the QC Week " + qcWeek)
          // console.log("This is the next week" + temp)
    if (temp == (Number(qcWeek)+1)) {
              // console.log(qcData[key]["week"])
              // console.log(next_week)
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


// function get_table(){
// 	let port = 5000;
// 	let host = "https://localhost";
// 	let endpoint = "/test/"; //TODO select endpoint for associate dashboard data
// 	let url = host + ':' + port + endpoint;
// 	fetch(url, {
// 		method: 'GET',
// 		mode: 'cors',
// 	}).then(response =>{
// 		console.log('reply:')
// 		console.log(response)
// 		return response.json()
// 	}).catch(err =>{
// 		console.log('mistakes were made:')
// 		console.log(err)
// 	}).then(response_dict =>{

// 		console.log('final step:')
// 		console.log(response_dict)
// 		console.log(typeof(response_dict))
// 		if (typeof(response_dict) != 'undefined'){
// 			build_statistics_table(response_dict["data"]);
// 		}
// 	})
// }

const dummyData = { 'associate': [0, 10, 20, 30, 40, 50, 60 ,70, 80, 90], 'batch':[0, 10, 30, 20, 40, 60, 50], 'henry':[100, 100, 100, 100, 100, 100, 100, 100]};
// function loadData() must be called after chart is declared in js file
// to change color of chart you only have change these color arrays
const fillColor = ['#72A4C299', '#FCB41499', '#F2692599'];
const lineColor = ['#72A4C2', '#FCB414', '#F26925'];

var ctx = document.getElementById('gradesChart').getContext('2d');


var myChart;

function loadData(dataList){

  myChart = new Chart(ctx, {
      type: 'line',
      data:{
          labels: ['Week 1', 'Week 2', 'Week 3', 'Week 4', 'Week 5', 
              'Week 6', 'Week 7', 'Week 8', 'Week 9', 'Week 10'],
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
  var i = 0;
  console.log(dataList)
  for(label in dataList){
      if(label != "chartData"){
          let setLabel = label; // ledgend label
          let setData =  Object.values(dataList[label]);  // data to be graphed
          let setColor = fillColor[i]; // color of this datas background
          let setBorderColor = lineColor[i]; // color of ths datas trace
          let parsedData = {label: setLabel, data: setData, backgroundColor: setColor, borderColor: setBorderColor, borderWidth: 1, hidden: false,};
          myChart.data.datasets.clear_data
          myChart.data.datasets.push(parsedData);
          buildLegend(label, lineColor[i]);
          i++;
      }

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

// async function get_batch_data() {
//     let url = ""
//     if (cookie.includes("batchID")){
//         console.log(cookie.slice(cookie.search("batchID")))
//         url = "http://localhost:5000/grades/reports/" +cookie.slice(cookie.search("batchID"))+ "/spider";
//     }
//     else {
//         url = "http://localhost:5000/grades/reports/BatchID/spider"
//     }
// 	const response = await fetch(url);
//     let ids = await response.json();
//     console.log(ids);

// }




let temp = {
"batch_grades": [
  {
  "assessmentType": "Hadoop", 
  "score": 52.05628009982731, 
  "traineeId": "restOfBatch", 
  "week": 1, 
  "weight": 100.0
  }, 
  {
  "assessmentType": "C#", 
  "score": 55.81805974504222, 
  "traineeId": "restOfBatch", 
  "week": 1, 
  "weight": 100.0
  }, 
  {
  "assessmentType": "Docker", 
  "score": 61.55324546150539, 
  "traineeId": "restOfBatch", 
  "week": 1, 
  "weight": 100.0
  }, 
  {
  "assessmentType": "Spring Boot", 
  "score": 49.749899796817616, 
  "traineeId": "restOfBatch", 
  "week": 1, 
  "weight": 100.0
  }, 
  {
  "assessmentType": "REST", 
  "score": 45.390597301980726, 
  "traineeId": "restOfBatch", 
  "week": 2, 
  "weight": 100.0
  }, 
  {
  "assessmentType": "SQL", 
  "score": 42.5692092568978, 
  "traineeId": "restOfBatch", 
  "week": 3, 
  "weight": 100.0
  },
    {
  "assessmentType": "SQL", 
  "score": 42.5692092568978, 
  "traineeId": "restOfBatch", 
  "week": 3, 
  "weight": 100.0
  }
]
}


let batchData = {
  "batch_grades": [
    {
      "assessmentType": "Hadoop", 
      "score": 42.056, 
      "traineeId": "TR-1146", 
      "week": 1, 
      "weight": 100.0
    }, 
    {
      "assessmentType": "C#", 
      "score": 55.818, 
      "traineeId": "TR-1146", 
      "week": 1, 
      "weight": 100.0
    }, 
    {
      "assessmentType": "Docker", 
      "score": 61.553, 
      "traineeId": "TR-1146", 
      "week": 1, 
      "weight": 100.0
    }, 
    {
      "assessmentType": "Spring Boot", 
      "score": 49.749, 
      "traineeId": "TR-1146", 
      "week": 1, 
      "weight": 100.0
    }, 
    {
      "assessmentType": "REST", 
      "score": 45.390, 
      "traineeId": "TR-1146", 
      "week": 2, 
      "weight": 100.0
    }, 
    {
      "assessmentType": "SQL", 
      "score": 42.569, 
      "traineeId": "TR-1146", 
      "week": 3, 
      "weight": 100.0
    },
    {
  "assessmentType": "SQL", 
  "score": 42.5692092568978, 
  "traineeId": "restOfBatch", 
  "week": 3, 
  "weight": 100.0
  }]
  }
// build_statistics_table(temp, batchData);

  

// let chartdata = {
// 	"chartData": {
// 	  "Associate Exam Score": [
// 		48.45003128051758, 
// 		48.14583969116211, 
// 		47.84164810180664, 
// 		47.53745651245117, 
// 		47.2332649230957, 
// 		38.0053768157959, 
// 		28.777488708496094, 
// 		0, 
// 		0
// 	  ], 
// 	  "Associate Other Score": [
// 		65.72003936767578, 
// 		85.99601745605469, 
// 		81.89466349283855, 
// 		77.79330952962239, 
// 		73.69195556640625, 
// 		0, 
// 		0, 
// 		0, 
// 		0
// 	  ], 
// 	  "Associate Presentation Score": [
// 		0, 
// 		0, 
// 		0, 
// 		43.534488677978516, 
// 		45.13601048787435, 
// 		18.64165687561035, 
// 		48.339054107666016, 
// 		0, 
// 		0
// 	  ], 
// 	  "Associate Project Score": [
// 		29.688993453979492, 
// 		46.215118408203125, 
// 		72.59832763671875, 
// 		82.6733627319336, 
// 		68.84568786621094, 
// 		0, 
// 		0, 
// 		0, 
// 		0
// 	  ], 
// 	  "Associate Verbal Score": [
// 		0, 
// 		28.77274513244629, 
// 		30.618759155273438, 
// 		0, 
// 		90.08526611328125, 
// 		3.0095479488372803, 
// 		0, 
// 		0, 
// 		0
// 	  ], 
// 	  "Average Exam Score": [
// 		49.808984729376704, 
// 		50.183863855559714, 
// 		50.558742981742725, 
// 		50.93362210792574, 
// 		51.30850123410875, 
// 		48.79655301638625, 
// 		46.28460479866374, 
// 		0, 
// 		0
// 	  ], 
// 	  "Average Other Score": [
// 		51.435200133106925, 
// 		40.5952634296634, 
// 		44.14399643919685, 
// 		47.6927294487303, 
// 		51.24146245826375, 
// 		0, 
// 		0, 
// 		0, 
// 		0
// 	  ], 
// 	  "Average Presentation Score": [
// 		0, 
// 		0, 
// 		0, 
// 		45.17336277663708, 
// 		46.01144779180036, 
// 		58.04101878946478, 
// 		47.68761782212691, 
// 		0, 
// 		0
// 	  ], 
// 	  "Average Project Score": [
// 		63.00162055275657, 
// 		45.3531190698797, 
// 		48.616078934208915, 
// 		44.226270364089444, 
// 		49.677677696401425, 
// 		0, 
// 		0, 
// 		0, 
// 		0
// 	  ], 
// 	  "Average Verbal Score": [
// 		0, 
// 		52.43995692513206, 
// 		50.39589304273779, 
// 		0, 
// 		46.41534863818776, 
// 		52.396158478476785, 
// 		0, 
// 		0, 
// 		0
// 	  ]
// 	}
//   }
//   loadData(chartdata)
function build_chart_data(email){
  chartdata = get_chart_data(email)
  let count = 0;
  let associateweekly = []
  let batchweekly = []
  for(let i = 0; i<chartdata["chartData"]["Associate Exam Score"].length; i++){
      let exam = chartdata["chartData"]["Associate Exam Score"][i]
      if(exam != 0){
          count++
      }
      let other = chartdata["chartData"]["Associate Other Score"][i]
      if(other != 0){
          count++
      }
      let presentation = chartdata["chartData"]["Associate Presentation Score"][i]
      if(presentation != 0){
          count++
      }
      let project = chartdata["chartData"]["Associate Project Score"][i]
      if(project != 0){
          count++
      }
      let verbal = chartdata["chartData"]["Associate Verbal Score"][i]
      if(verbal != 0){
          count++
      }
      if(count!=0){
          associateweekly.push((exam+other+presentation+project+verbal)/count)
      }
      
      count = 0;
      

      let batchexam = chartdata["chartData"]["Average Exam Score"][i]
      if(batchexam != 0){
          count++
      }
      let batchother = chartdata["chartData"]["Average Other Score"][i]
      if(batchother != 0){
          count++
      }
      let batchpresentation = chartdata["chartData"]["Average Presentation Score"][i]
      if(batchpresentation != 0){
          count++
      }
      let batchproject = chartdata["chartData"]["Average Project Score"][i]
      if(batchproject != 0){
          count++
      }
      let batchverbal = chartdata["chartData"]["Average Verbal Score"][i]
      if(batchverbal != 0){
          count++
      }
      if(count!=0){
          batchweekly.push((batchexam+batchother+batchpresentation+batchproject+batchverbal)/count)
      }
  }
  // console.log(batchweekly)

  // console.log(associateweekly)

  let chartstuff = {}
  chartstuff["Associate"] = associateweekly
  chartstuff["Batch"] = batchweekly

  loadData(chartstuff)
}

function clear_data(){
  try{
      let stattable = document.getElementById("stat_table")
      console.log("This is a first child")
      console.log(stattable.firstChild)
      while (stattable.firstChild) {
        stattable.removeChild(stattable.lastChild);
      }
  
      let legend = document.getElementById("chart-legend")
      while (legend.firstChild) {
          legend.removeChild(legend.lastChild);
      }
      myChart.destroy()
  }
  catch {
      console.log("Lol doesn't matter")
  }



}



get_dashboard_data()