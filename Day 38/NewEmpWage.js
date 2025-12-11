// UC 1
{
    const IS_ABSENT = 0;
    let empCheck = Math.floor(Math.random() * 10) % 2;
    console.log(empCheck);
    if (empCheck == IS_ABSENT) {
        console.log("Employee is absent. exiting.");
        return;
    } else {
        console.log("Employee is present");
    }
}

// UC 2
const IS_PART_TIME = 1;
const IS_FULL_TIME = 2;
const PART_TIME_HOURS = 4;
const FULL_TIME_HOURS = 8;
const WAGE_PER_HOUR = 20;
{
    let empHrs = 0;
    let empCheck = Math.floor(Math.random() * 10) % 3;
    switch(empCheck) {
        case IS_PART_TIME:
            empHrs = PART_TIME_HOURS;
            break;
        case IS_FULL_TIME:
            empHrs = FULL_TIME_HOURS;
            break;
        default:
            empHrs = 0;
    }
    let empWage = empHrs * WAGE_PER_HOUR;
    console.log("Employee wage: " + empWage);
}

// // UC 3
function getWorkingHours(empCheck) {
    switch(empCheck) {
        case IS_PART_TIME:
            return PART_TIME_HOURS;
        case IS_FULL_TIME:
            return FULL_TIME_HOURS;
        default:
            return 0;
    }
}

let empCheck = Math.floor(Math.random() * 10) % 3;
var empHrs = getWorkingHours(empCheck);
let empWage = empHrs * WAGE_PER_HOUR;
console.log("Employee wage: " + empWage);

// UC 4
const WORKING_DAYS_IN_MONTH = 20;
function calculatingWagePerDay() {
    let empCheck = Math.floor(Math.random() * 10) % 3;
    let empHrs = getWorkingHours(empCheck);
    return empHrs * WAGE_PER_HOUR;
} 
let totalWage = 0;
for (var i = 0; i< WORKING_DAYS_IN_MONTH; i++) {
    totalWage += calculatingWagePerDay();
}
console.log("Wage for a month: " + totalWage);

// UC 5
const TOTAL_WORKING_HRS = 160;
const MAX_DAYS = 20;
const IS_ABSENT = 0;
let empHrs = 0;
let daysWorked = 0;
while(empHrs <= TOTAL_WORKING_HRS && daysWorked < MAX_DAYS) {
    let empCheck = Math.floor(Math.random() * 10) % 3;
    empHrs += getWorkingHours(empCheck);
    if(empCheck != 0) daysWorked++;
}
console.log("Hours worked: " + empHrs + ", Days worked: " + daysWorked);
console.log("Wage: " + empHrs * WAGE_PER_HOUR);