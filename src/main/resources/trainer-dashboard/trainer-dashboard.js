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

var mockdata = {0:{"Associate Name": ["BatchAverage", "name2", "name3"]},
                1:{"Quiz Score": ["77", "89", "34"]},
                2:{"Exam Score": ["34", "77", "89"]},
                3:{"Project Score": ["23", "77", "34"]},
                4:{"Verbal Score": ["55", "77", "22"]}}

// We need to convert the data to row format if possible

// build the table based on the mock data
// Need confirmation on how the data looks when I call the endpoint -- clarify with Matthew Piazza

function build_table(tabledata) {
	let columns = [];
    let thead = document.getElementById("associate-table-head")
    let tr = document.createElement("tr")
	Object.keys(tabledata).forEach(outer=>{
        Object.keys(tabledata[outer]).forEach(x=>{
            console.log(x)
            let th = document.createElement("th")
            th.textContent = x;
            tr.appendChild(th);
            columns.push(tabledata[outer][x]);
        });
    });

    thead.appendChild(tr);

	let endi = columns[0].length;
	let endj = columns.length;
    let btbody = document.getElementById("batch-table-head");
	let tbody = document.getElementById("associate-table-data");
    let flag = true
	for(i = 0; i < endi; i++){

		let tr = document.createElement("tr");

        tr.setAttribute("onClick", "redirect()")
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

build_table(mockdata)


function myFunction() {
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


  function redirect() {
      window.location.href = "http://localhost"
  }