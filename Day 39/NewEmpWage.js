// All constant variables
const IS_PART_TIME = 1;
const IS_FULL_TIME = 2;
const PART_TIME_HOURS = 4;
const FULL_TIME_HOURS = 8;
const WAGE_PER_HOUR = 20;
const TOTAL_WORKING_HRS = 160;
const MAX_DAYS = 20;
const IS_ABSENT = 0;
const WORKING_DAYS_IN_MONTH = 20;

// UC 1
// {
//     const IS_ABSENT = 0;
//     let empCheck = Math.floor(Math.random() * 10) % 2;
//     console.log(empCheck);
//     if (empCheck == IS_ABSENT) {
//         console.log("Employee is absent. exiting.");
//         return;
//     } else {
//         console.log("Employee is present");
//     }
// }

// // UC 2
// {
//     let empHrs = 0;
//     let empCheck = Math.floor(Math.random() * 10) % 3;
//     switch(empCheck) {
//         case IS_PART_TIME:
//             empHrs = PART_TIME_HOURS;
//             break;
//         case IS_FULL_TIME:
//             empHrs = FULL_TIME_HOURS;
//             break;
//         default:
//             empHrs = 0;
//     }
//     let empWage = empHrs * WAGE_PER_HOUR;
//     console.log("Employee wage: " + empWage);
// }

// // // UC 3
// function getWorkingHours(empCheck) {
//     switch(empCheck) {
//         case IS_PART_TIME:
//             return PART_TIME_HOURS;
//         case IS_FULL_TIME:
//             return FULL_TIME_HOURS;
//         default:
//             return 0;
//     }
// }

// let empCheck = Math.floor(Math.random() * 10) % 3;
// var empHrs = getWorkingHours(empCheck);
// let empWage = empHrs * WAGE_PER_HOUR;
// console.log("Employee wage: " + empWage);

// // UC 4
// function calculatingWagePerDay() {
//     let empCheck = Math.floor(Math.random() * 10) % 3;
//     let empHrs = getWorkingHours(empCheck);
//     return empHrs * WAGE_PER_HOUR;
// } 
// let totalWage = 0;
// for (var i = 0; i< WORKING_DAYS_IN_MONTH; i++) {
//     totalWage += calculatingWagePerDay();
// }
// console.log("Wage for a month: " + totalWage);

// // UC 5
// let empHrs = 0;
// let daysWorked = 0;
// while(empHrs <= TOTAL_WORKING_HRS && daysWorked < MAX_DAYS) {
//     let empCheck = Math.floor(Math.random() * 10) % 3;
//     empHrs += getWorkingHours(empCheck);
//     if(empCheck != 0) daysWorked++;
// }
// console.log("Hours worked: " + empHrs + ", Days worked: " + daysWorked);
// console.log("Wage: " + empHrs * WAGE_PER_HOUR);

// UC 6
let dailyWageArray = new Array();

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

function calculatingWagePerDay() {
    let empCheck = Math.floor(Math.random() * 10) % 3;
    let empHrs = getWorkingHours(empCheck);
    return empHrs * WAGE_PER_HOUR;
}

let totalWage = 0;
for (var i = 0; i < MAX_DAYS; i++) {
    let dailyWage = calculatingWagePerDay();
    dailyWageArray.push(dailyWage);
    totalWage += dailyWage;
}

console.log("Daily wage array: " + dailyWageArray);
console.log("UC 6 - Total wage: " + totalWage);

// UC 7A
let totEmpWage = 0;
function sum(dailyWage) {
    totEmpWage += dailyWage;
}
dailyWageArray.forEach(sum);
console.log("UC 7A - Total wage (forEach): " + totEmpWage);

// UC 7B
let dayCounter = 0;
function mapDayWithWage(dailyWage) {
    dayCounter++;
    return "Day: " + dayCounter + " = " + dailyWage;
}
let mapDayWithWageArr = dailyWageArray.map(mapDayWithWage);
console.log("UC 7B - Daily wage map.")
console.log(mapDayWithWageArr);

// UC 7C
function fullTimeWage(dailyWage) {
    return dailyWage.includes("160");
}
let fullDayWageArr = mapDayWithWageArr.filter(fullTimeWage);
console.log("UC 7C - Daily wage filter when full time wage earned.")
console.log(fullDayWageArr);

// UC 7D
function findFullTimeWage(dailyWage) {
    return dailyWage.includes("160");
}
console.log("UC 7D - First time full time was earned on the " + mapDayWithWageArr.find(findFullTimeWage)); 

// UC 7E
function isAllFullTimeWage(dailyWage) {
    return dailyWage.includes("160");
}
console.log("UC 7E - Check all element have full time wage: " + mapDayWithWageArr.every(isAllFullTimeWage));

// UC 7F
function isAnyPartTimeWage(dailyWage) {
    return dailyWage.includes("80");
}
console.log("UC 7F - Check if any part time: " + mapDayWithWageArr.some(isAnyPartTimeWage));

// UC 7G
function workingDays(dailyWage) {
    return dailyWage.includes("160") || dailyWage.includes("80");
}
let daysWorked = mapDayWithWageArr.filter(workingDays).length;
console.log("Number of days worked: " + daysWorked);