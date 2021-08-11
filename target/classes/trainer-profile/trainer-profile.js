// get all the page elements we need to manipulate
const editContactInfo = document.getElementById("editContactInfo")
const cancelContactInfo = document.getElementById("cancelContactInfo")
const submitContactInfo = document.getElementById("submitContactInfo")
const editBio = document.getElementById("editBio")
const cancelBio = document.getElementById("cancelBio")
const submitBio = document.getElementById("submitBio")
const editTrainingInfo = document.getElementById("editTrainingInfo")
const cancelTrainingInfo = document.getElementById("cancelTrainingInfo")
const submitTrainingInfo = document.getElementById("submitTrainingInfo")
const firstName = document.getElementById("userFirstName")
const lastName = document.getElementById("userLastName")
const email = document.getElementById("userEmail")
const bio = document.getElementById("userBio")
const specialization = document.getElementById("userSpecialization")
const userLocation = document.getElementById("userLocation")
const contactInfoForm = document.getElementById("updateContactInfo")
const bioForm = document.getElementById("updateBio")
const trainingInfoForm = document.getElementById("updateTrainingInfo")
const newFirstName = document.getElementById("editFirstName")
const newLastName = document.getElementById("editLastName")
const newEmail = document.getElementById("editEmail")
const newBio = document.getElementById("newBio")
const newSpecialization = document.getElementById("editUserSpecialization")
const newLocation = document.getElementById("editUserLocation")

// setup event handlers for buttons
editContactInfo.onclick = setupContactInfoForm
cancelContactInfo.onclick = resetContactInfoForm
editBio.onclick = setupBioForm
cancelBio.onclick = resetBioForm
editTrainingInfo.onclick = setupTrainingInfoForm
cancelTrainingInfo.onclick = resetTrainingInfoForm

// get the info for the current user
getTrainerInfo()

contactInfoForm.onsubmit = function (form) {
    // stop the regular form submission
    form.preventDefault()

    let newInfo = {}

    // get the info from the form that we submitted
    newInfo["firstName"] = this["firstName"].value
    newInfo["lastName"] = this["lastName"].value
    newInfo["emailAddress"] = this["emailAddress"].value

    // get the rest of the info from the elements on the page
    newInfo["bio"] = bio.innerText
    newInfo["technology"] = specialization.innerText
    newInfo["location"] = userLocation.innerText

    // construct an HTTP request
    let xhr = new XMLHttpRequest()
    xhr.open(this.method, this.action, true)
    xhr.setRequestHeader('Content-Type', 'application/json; charset=UTF-8')

    // send the info as JSON
    xhr.send(JSON.stringify(newInfo))

    xhr.onloadend = function () {
        // update the info on the page
        getTrainerInfo()

        // reset the form elements
        resetContactInfoForm()
    }
}

bioForm.onsubmit = function (form) {
    // stop the regular form submission
    form.preventDefault()

    let newInfo = {}

    // get the info from the form that we submitted
    newInfo["bio"] = this["bio"].value
    
    // get the rest of the info from the elements on the page
    newInfo["firstName"] = firstName.innerText
    newInfo["lastName"] = lastName.innerText
    newInfo["emailAddress"] = email.innerText
    newInfo["technology"] = specialization.innerText
    newInfo["location"] = userLocation.innerText

    // construct an HTTP request
    let xhr = new XMLHttpRequest()
    xhr.open(this.method, this.action, true)
    xhr.setRequestHeader('Content-Type', 'application/json; charset=UTF-8')

    // send the info as JSON
    xhr.send(JSON.stringify(newInfo))

    xhr.onloadend = function () {
        // update the info on the page
        getTrainerInfo()

        // reset the form elements
        resetBioForm()
    }
}

trainingInfoForm.onsubmit = function (form) {
    // stop the regular form submission
    form.preventDefault()

    let newInfo = {}

    // get the info from the form that we submitted
    newInfo["technology"] = this["specialization"].value
    newInfo["location"] = this["location"].value

    // get the rest of the info from the elements on the page
    newInfo["firstName"] = firstName.innerText
    newInfo["lastName"] = lastName.innerText
    newInfo["emailAddress"] = email.innerText
    newInfo["bio"] = bio.innerText
    

    // construct an HTTP request
    let xhr = new XMLHttpRequest()
    xhr.open(this.method, this.action, true)
    xhr.setRequestHeader('Content-Type', 'application/json; charset=UTF-8')

    // send the info as JSON
    xhr.send(JSON.stringify(newInfo))

    xhr.onloadend = function () {
        // update the info on the page
        getTrainerInfo()

        // reset the form elements
        resetTrainingInfoForm()
    }
}

async function getTrainerInfo() {
    let url = "http://18.209.14.19:9001/edittrainerprofile"
    let response = await fetch(url)
    let trainerInfo = await response.json()
    firstName.innerText = trainerInfo["firstName"]
    newFirstName.value = trainerInfo["firstName"]
    lastName.innerText = trainerInfo["lastName"]
    newLastName.value = trainerInfo["lastName"]
    email.innerText = trainerInfo["emailAddress"]
    newEmail.value = trainerInfo["emailAddress"]
    bio.innerText = trainerInfo["bio"]
    bio.style.height = "fit-content"
    newBio.value = trainerInfo["bio"]
    specialization.innerText = trainerInfo["specialization"]
    newSpecialization.value = trainerInfo["specialization"]
    userLocation.innerText = trainerInfo["location"]
    newLocation.value = trainerInfo["location"]
}

function menuToggle() {
    const toggleMenu = document.querySelector('.menu');
    toggleMenu.classList.toggle('active');
}

function setupContactInfoForm() {
    editContactInfo.style.display = "none"
    cancelContactInfo.style.display = "inline"
    submitContactInfo.style.display = "inline"
    firstName.style.display = "none"
    newFirstName.style.display = "inline"
    newFirstName.value = firstName.innerText
    lastName.style.display = "none"
    newLastName.style.display = "inline"
    newLastName.value = lastName.innerText
    email.style.display = "none"
    newEmail.style.display = "inline"
    newEmail.value = email.innerText
}

function resetContactInfoForm() {
    cancelContactInfo.style.display = "none"
    editContactInfo.style.display = "inline"
    submitContactInfo.style.display = "none"
    firstName.style.display = "inline"
    newFirstName.style.display = "none"
    lastName.style.display = "inline"
    newLastName.style.display = "none"
    email.style.display = "inline"
    newEmail.style.display = "none"
}

function setupBioForm() {
    editBio.style.display = "none"
    submitBio.style.display = "inline"
    cancelBio.style.display = "inline"
    bio.style.display = "none"
    newBio.style.display = "block"
    newBio.innerText = bio.innerText
}

function resetBioForm() {
    cancelBio.style.display = "none"
    submitBio.style.display = "none"
    editBio.style.display = "inline"
    bio.style.display = "block"
    newBio.style.display = "none"
}

function setupTrainingInfoForm() {
    editTrainingInfo.style.display = "none"
    submitTrainingInfo.style.display = "inline"
    cancelTrainingInfo.style.display = "inline"
    specialization.style.display = "none"
    userLocation.style.display = "none"
    newSpecialization.style.display = "inline"
    newSpecialization.value = specialization.innerText
    newLocation.style.display = "inline"
    newLocation.value = userLocation.innerText
}

function resetTrainingInfoForm() {
    editTrainingInfo.style.display = "inline"
    submitTrainingInfo.style.display = "none"
    cancelTrainingInfo.style.display = "none"
    specialization.style.display = "inline"
    userLocation.style.display = "inline"
    newSpecialization.style.display = "none"
    newLocation.style.display = "none"
}