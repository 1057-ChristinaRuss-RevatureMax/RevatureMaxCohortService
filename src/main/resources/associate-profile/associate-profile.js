// get all the page elements we need to manipulate
const editContactInfo = document.getElementById("editContactInfo")
const cancelContactInfo = document.getElementById("cancelContactInfo")
const submitContactInfo = document.getElementById("submitContactInfo")
const editBio = document.getElementById("editBio")
const cancelBio = document.getElementById("cancelBio")
const submitBio = document.getElementById("submitBio")
const editFavTechnologies = document.getElementById("editFavTechnologies")
const cancelFavTechnologies = document.getElementById("cancelFavTechnologies")
const submitFavTechnologies = document.getElementById("submitFavTechnologies")
const firstName = document.getElementById("userFirstName")
const lastName = document.getElementById("userLastName")
const email = document.getElementById("userEmail")
const bio = document.getElementById("userBio")
const tech1 = document.getElementById("tech1")
const tech2 = document.getElementById("tech2")
const tech3 = document.getElementById("tech3")
const preference = document.getElementById("userPreference")
const contactInfoForm = document.getElementById("updateContactInfo")
const bioForm = document.getElementById("updateBio")
const favTechnologiesForm = document.getElementById("updateFavTechnologies")
const newFirstName = document.getElementById("editFirstName")
const newLastName = document.getElementById("editLastName")
const newEmail = document.getElementById("editEmail")
const newBio = document.getElementById("newBio")
const newTech1 = document.getElementById("editTech1")
const newTech2 = document.getElementById("editTech2")
const newTech3 = document.getElementById("editTech3")
const newPreference = document.getElementById("editPreference")

// setup event handlers for buttons
editContactInfo.onclick = setupContactInfoForm
cancelContactInfo.onclick = resetContactInfoForm
editBio.onclick = setupBioForm
cancelBio.onclick = resetBioForm
editFavTechnologies.onclick = setupFavTechnologiesForm
cancelFavTechnologies.onclick = resetFavTechnologiesForm

// get the info for the current user
getAssociateInfo()

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
    newInfo["favoriteTechnologies"] = tech1.innerText + '~' + tech2.innerText + '~' + tech3.innerText
    newInfo["preference"] = preference.innerText

    // construct an HTTP request
    let xhr = new XMLHttpRequest()
    xhr.open(this.method, this.action, true)
    xhr.setRequestHeader('Content-Type', 'application/json; charset=UTF-8')

    // send the info as JSON
    xhr.send(JSON.stringify(newInfo))

    xhr.onloadend = function () {
        // update the info on the page
        getAssociateInfo()

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
    newInfo["favoriteTechnologies"] = tech1.innerText + '~' + tech2.innerText + '~' + tech3.innerText
    newInfo["preference"] = preference.innerText

    // construct an HTTP request
    let xhr = new XMLHttpRequest()
    xhr.open(this.method, this.action, true)
    xhr.setRequestHeader('Content-Type', 'application/json; charset=UTF-8')

    // send the info as JSON
    xhr.send(JSON.stringify(newInfo))

    xhr.onloadend = function () {
        // update the info on the page
        getAssociateInfo()

        // reset the form elements
        resetBioForm()
    }
}

favTechnologiesForm.onsubmit = function (form) {
    // stop the regular form submission
    form.preventDefault()

    let newInfo = {}

    // get the info from the form that we submitted
    newInfo["favoriteTechnologies"] = this["tech1"].value + '~' + this["tech2"].value + '~' + this["tech3"].value
    newInfo["preference"] = this["preference"].value

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
        getAssociateInfo()

        // reset the form elements
        resetFavTechnologiesForm()
    }
}

async function getAssociateInfo() {
    let url = "http://localhost:9001/editassociateprofile"
    let response = await fetch(url)
    console.log(response)
    let associateInfo = await response.json()
    console.log("associate info = " + associateInfo)
    firstName.innerText = associateInfo["firstName"]
    newFirstName.value = associateInfo["firstName"]
    lastName.innerText = associateInfo["lastName"]
    newLastName.value = associateInfo["lastName"]
    email.innerText = associateInfo["emailAddress"]
    newEmail.value = associateInfo["emailAddress"]
    bio.innerText = associateInfo["bio"]
    bio.style.height = "fit-content"
    newBio.value = associateInfo["bio"]
    let favTechs = associateInfo["favoriteTechnologies"].split("~", 3)
    tech1.innerText = favTechs[0]
    newTech1.value = favTechs[0]
    tech2.innerText = favTechs[1]
    newTech2.value = favTechs[1]
    tech3.innerText = favTechs[2]
    newTech3.value = favTechs[2]
    preference.innerText = associateInfo["preference"]
    newPreference.value = associateInfo["preference"]
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

function setupFavTechnologiesForm() {
    editFavTechnologies.style.display = "none"
    submitFavTechnologies.style.display = "inline"
    cancelFavTechnologies.style.display = "inline"
    tech1.style.display = "none"
    tech2.style.display = "none"
    tech3.style.display = "none"
    preference.style.display = "none"
    newTech1.style.display = "block"
    newTech1.value = tech1.innerText
    newTech2.style.display = "block"
    newTech2.value = tech2.innerText
    newTech3.style.display = "block"
    newTech3.value = tech3.innerText
    newPreference.style.display = "inline"
    newPreference.value = preference.innerText
}

function resetFavTechnologiesForm() {
    editFavTechnologies.style.display = "inline"
    submitFavTechnologies.style.display = "none"
    cancelFavTechnologies.style.display = "none"
    tech1.style.display = "block"
    tech2.style.display = "block"
    tech3.style.display = "block"
    preference.style.display = "inline"
    newTech1.style.display = "none"
    newTech2.style.display = "none"
    newTech3.style.display = "none"
    newPreference.style.display = "none"
}