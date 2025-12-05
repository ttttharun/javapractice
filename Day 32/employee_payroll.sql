# UC 1
CREATE DATABASE payroll_service;
SHOW DATABASES;
USE payroll_service;

# UC 2
CREATE TABLE employee_payroll (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    salary DECIMAL(10,2) NOT NULL,
    start_date DATE NOT NULL
);

# UC 3
INSERT INTO employee_payroll (name, salary, start_date)
values ('Abi', 1000000.00, '2018-01-03');

# UC 4
SELECT * FROM employee_payroll;

# UC 5
SELECT salary FROM employee_payroll
WHERE name = 'Abi';
SELECT salary FROM employee_payroll
WHERE start_date BETWEEN CAST ('2018-01-01' AS DATE) AND DATE(NOW());

# UC 6
ALTER TABLE employee_payroll
ADD column gender CHAR(1);
UPDATE employee_payroll
set gender = 'M'
WHERE name = 'Abi';

# UC 7
SELECT SUM(salary) FROM employee_payroll
WHERE gender = 'M' GROUP BY gender;
SELECT AVG(salary) FROM employee_payroll
WHERE gender = 'F' GROUP BY gender;
SELECT MIN(salary) FROM employee_payroll;
SELECT MAX(salary) FROM employee_payroll;
SELECT COUNT(salary) FROM employee_payroll;

# UC 8
ALTER TABLE employee_payroll
ADD phone_number VARCHAR(12)
AFTER name;
ALTER TABLE employee_payroll
ADD address VARCHAR(100)
AFTER phone_number;
ALTER TABLE employee_payroll
ADD department VARCHAR(50) NOT NULL
AFTER address;
ALTER TABLE employee_payroll
ALTER address SET DEFAULT 'TBD';

# UC 9
ALTER TABLE employee_payroll
ADD basic_pay DECIMAL(10,2) NOT NULL,
ADD deductions DECIMAL(10,2) NOT NULL,
ADD taxable_pay DECIMAL(10,2) NOT NULL,
ADD income_tax DECIMAL(10,2) NOT NULL,
ADD net_pay DECIMAL(10,2) NOT NULL;