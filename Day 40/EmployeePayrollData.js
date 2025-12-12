class EmployeePayrollData {
    // Properties
    id;
    salary;
    gender;
    startDate;

    // Constructor
    // constructor(id, name, salary) {
    //     this.id = id;
    //     this.name = name;
    //     this.salary = salary;
    // }

    constructor(...params) {
        this.id = params[0];
        this.name = params[1];
        this.salary = params[2];
        this.gender = params[3];
        this.startDate = params[4];
    }

    // Getters and Setters
    get name() { return this._name;}

    set name(name) { 
        let nameRegex = RegExp('^[A-Z]{1}[a-zA-Z\\s]{2,}$');
        if (nameRegex.test(name)) {
            this._name = name;
        } else {
            throw 'Name is Incorrect!';
        }
    }
    set id(id) {
        if (id > 0) {
            this._id = id;
        } else {
            throw 'ID must be positive!';
        }
    }
    set salary(salary) {
        if (salary >= 0) {
            this._salary = salary;
        } else {
            throw 'Salary must be non-negative!';
        }
    }
    set gender(gender) {
        if (gender === 'M' || gender === 'F') {
            this._gender = gender;
        } else {
            throw 'Gender must be M or F!';
        }
    }

    // toString Method
    toString() {
        const options = { year: 'numeric', month: 'long', day: 'numeric' };
        const empDate = this.startDate === undefined ? "undefined" :
            this.startDate.toLocaleDateString("en-US", options);
        return "id=" + this.id + ", name='" + this.name + "', salary=" + this.salary
        + ", gender=" + this.gender + ", startDate=" + empDate;
    }
}

let empData = new EmployeePayrollData(1, "John Doe", 50000);
console.log(empData.toString());
empData.name = "My name is jeff";
empData.id = 69;
console.log(empData.toString());
let newEmpData = new EmployeePayrollData(2, "Jane Smith", 60000, "F", new Date());
console.log(newEmpData.toString());
// Testing name validation
try {
    newEmpData.name = "jo";
} catch (e) {
    console.error(e);
}
// Testing id validation
try {
    newEmpData.id = -5;
} catch (e) {
    console.error(e);
}
// Testing salary validation
try {
    newEmpData.salary = -1000;
} catch (e) {
    console.error(e);
}
