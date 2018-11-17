var API_URL = 'https://lpr.recoqnitics.com/detect'
var ACCESS_KEY = 'd5defde6a219192ad60f'
var SECRET_KEY = '1c6901cbf58677f5fc8e705f5e67f352b77ae6d6'

var SECONDS = 1000;
var MINUTES = SECONDS * 60;
var HOURS = MINUTES * 60;
var DAYS = HOURS * 24;
var YEARS = DAYS * 365;

function uploadPhoto() {
  var formData = new FormData(document.forms.namedItem('fileinfo'))
  formData.append('access_key', ACCESS_KEY)
  formData.append('secret_key', SECRET_KEY)

  // Method 1: pure javascript
  var xhr = new XMLHttpRequest()
  xhr.open('POST', API_URL)
  xhr.onload = () =>
    xhr.status === 200
      ? findCar(JSON.parse(xhr.response))
      : console.log(xhr.status)
  xhr.send(formData)
}


function findCar(data) {
  data["licensePlates"].forEach(licensePlate => {
    var carPlate = licensePlate["licensePlateNumber"]
    console.log(data)
    cars.forEach(car => {
      if(car.carPlate == carPlate){
        
        if(car.arrivalTime == null){
          car.setArrivalTime()
          var formattedHours = ("0" + car.arrivalTime.getHours()).slice(-2);
          var formattedMinutes = ("0" + car.arrivalTime.getMinutes()).slice(-2);
          var formattedSeconds = ("0" + car.arrivalTime.getSeconds()).slice(-2);
          insertRow(car.carPlate, formattedHours + ':' + formattedMinutes + ':' + formattedSeconds, '-');
          return
        }
        if(car.leaveTime == null){
          car.setLeaveTime()
          var formattedHours = ("0" + car.leaveTime.getHours()).slice(-2);
          var formattedMinutes = ("0" + car.leaveTime.getMinutes()).slice(-2);
          var formattedSeconds = ("0" + car.leaveTime.getSeconds()).slice(-2);
          insertRow(car.carPlate, '-', formattedHours + ':' + formattedMinutes + ':' + formattedSeconds);
          car.arrivalTime = null;
          car.leaveTime = null;
          return
        }
  
      }
    });
  });
  

}

function Car (driverName, carPlate, carModel, noPhone, arrivalTime, leaveTime){
  this.driverName = driverName;
  this.carPlate = carPlate;
  this.carModel = carModel;
  this.noPhone = noPhone;
  this.arrivalTime = null;
  this.leaveTime = null;

  this.setArrivalTime = function() {

     
      this.arrivalTime = getCurrentTime();
      console.log('Success set arrival time');
      console.log(this.arrivalTime);
      
  }

  this.setLeaveTime = function() {
    
    this.leaveTime = getCurrentTime();
    console.log('Success set leave time');
    console.log(this.leaveTime);
    this.calculateFare();
  }

  this.calculateFare = function(){
    var duration = this.leaveTime - this.arrivalTime;
    var sec = duration / SECONDS;

    var fare = sec * 0.01667;
    var fare_RM = 'RM' + fare.toFixed(2);
    console.log(fare_RM);
    document.getElementById("demo").innerHTML += "Duration: " + duration/1000 + " minutes. " + this.carPlate + " need to paid " + fare_RM + "<br />";

  }
}
  
function getCurrentTime(){
  var date = new Date();
  
  return date;
}
var car1 = new Car('Tan Ha Ha', 'PEN15', 'Honda', '0176253926');
var car2 = new Car('Tan He He', 'MAH41', 'Toyota', '0175383281');
var car3 = new Car('suibian la', 'PKB8932', 'Proton', '0171666666');
var car4 = new Car('suibian la', 'BJY6688', 'Proton', '0171666666');

var cars = [];

cars.push(car1);
cars.push(car2);
cars.push(car3);
cars.push(car4);

function PreviewImage() {
  var oFReader = new FileReader();
  oFReader.readAsDataURL(document.getElementById("uploadImage").files[0]);

  oFReader.onload = function (oFREvent) {
      document.getElementById("uploadPreview").src = oFREvent.target.result;
  };
};

function insertRow(carPlate, arrivalTime, leaveTime) {

  var table = document.getElementById("myTable");
  var row = table.insertRow(table.rows.length);
  var cell1 = row.insertCell(0);
  var cell2 = row.insertCell(1);
  var cell3 = row.insertCell(2);
  cell1.innerHTML = carPlate;
  cell2.innerHTML = arrivalTime;
  cell3.innerHTML = leaveTime;

}