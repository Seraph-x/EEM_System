# EEM_System
## It's a simple enterprise employee management system which is based on openGaussDB and designed and accomplished by GPT .
### tables
* Employee
> * EmployeeID（员工ID）：唯一标识符，主键
> * FirstName（名字）：字符串类型
> * LastName（姓氏）：字符串类型
> * Email（电子邮件）：字符串类型
> * PhoneNumber（电话号码）：字符串类型
> * HireDate（入职日期）：日期类型
> * JobTitle（职位）：字符串类型
> * DepartmentID（部门ID）：外键，关联到 Departments 表

* Department
> * DepartmentID（部门ID）：唯一标识符，主键
> * DepartmentName（部门名称）：字符串类型
> * ManagerID（经理ID）：外键，关联到 Employees 表

* Salaries
> * SalaryID（薪资ID）：唯一标识符，主键
> * EmployeeID（员工ID）：外键，关联到 Employees 表
> * BaseSalary（基本薪资）：浮点数类型
> * Bonus（奖金）：浮点数类型
> * EffectiveDate（生效日期）：日期类型

* TimeOffRequests
> * RequestID（请求ID）：唯一标识符，主键
> * EmployeeID（员工ID）：外键，关联到 Employees 表
> * StartDate（开始日期）：日期类型
> * EndDate（结束日期）：日期类型
> * RequestStatus（请求状态）：字符串类型（例如：待审批、已批准、已拒绝）

### SQL(based on openGauss)
> * -- 创建 Employees 表
> 
CREATE TABLE Employees (
    EmployeeID SERIAL PRIMARY KEY,
    FirstName VARCHAR(50) NOT NULL,
    LastName VARCHAR(50) NOT NULL,
    Email VARCHAR(100) UNIQUE NOT NULL,
    PhoneNumber VARCHAR(20),
    HireDate DATE NOT NULL,
    JobTitle VARCHAR(100) NOT NULL,
    DepartmentID INTEGER
);

> * -- 创建 Departments 表
> 
CREATE TABLE Departments (
    DepartmentID SERIAL PRIMARY KEY,
    DepartmentName VARCHAR(100) NOT NULL,
    ManagerID INTEGER
);

> * -- 在 Employees 表中添加 DepartmentID 外键约束
> 
ALTER TABLE Employees
ADD FOREIGN KEY (DepartmentID) REFERENCES Departments(DepartmentID);

>  * -- 在 Departments 表中添加 ManagerID 外键约束
>  
ALTER TABLE Departments
ADD FOREIGN KEY (ManagerID) REFERENCES Employees(EmployeeID);

> * -- 创建 Salaries 表
> 
CREATE TABLE Salaries (
    SalaryID SERIAL PRIMARY KEY,
    EmployeeID INTEGER NOT NULL,
    BaseSalary NUMERIC(10, 2) NOT NULL,
    Bonus NUMERIC(10, 2),
    EffectiveDate DATE NOT NULL,
    FOREIGN KEY (EmployeeID) REFERENCES Employees(EmployeeID)
);

> * -- 创建 TimeOffRequests 表
> 
CREATE TABLE TimeOffRequests (
    RequestID SERIAL PRIMARY KEY,
    EmployeeID INTEGER NOT NULL,
    StartDate DATE NOT NULL,
    EndDate DATE NOT NULL,
    RequestStatus VARCHAR(20) NOT NULL,
    FOREIGN KEY (EmployeeID) REFERENCES Employees(EmployeeID)
);

### insert datas in the above tables
> * INSERT INTO Employees (FirstName, LastName, Email, PhoneNumber, HireDate, JobTitle, Departmentid)
VALUES ('John', 'Doe', 'johndoe@example.com', '123-456-7890', '2020-01-01', 'Software Engineer', 1),
('Jane', 'Doe', 'janedoe@example.com', '123-456-7891', '2020-01-02', 'Product Manager', 1),
('Bob', 'Smith', 'bobsmith@example.com', '123-456-7892', '2020-01-03', 'Software Engineer', 2),
('Alice', 'Johnson', 'alicejohnson@example.com', '123-456-7893', '2020-01-04', 'Software Engineer', 2),
('Tom', 'Wilson', 'tomwilson@example.com', '123-456-7894', '2020-01-05', 'QA Engineer', 3),
('Emily', 'Brown', 'emilybrown@example.com', '123-456-7895', '2020-01-06', 'UI Designer', 3),
('David', 'Lee','avidlee@example.com', '123-456-7896', '2020-01-07', 'Software Engineer', 4),
('Sarah', 'Kim', 'sarahkim@example.com', '123-4567897', '2020-01-08', 'Product Manager', 4),
('Michael', 'Chen', 'michaelchen@example.com', '123-456-7898', '2020-01-09', 'Software Engineer', 5),
('Karen', 'Wang', 'karenwang@example.com', '123-456-7899', '2020-01-10', 'QA Engineer', 5),
('Alex', 'Johnson', 'alexjohnson@example.com', '123-456-7900', '2020-01-11', 'Software Engineer', 1),
('Olivia', 'Davis', 'oliviadavis@example.com', '123-456-7901', '2020-01-12', 'Product Manager', 1),
('Ethan', 'Wilson', 'ethanwilson@example.com', '123-456-7902', '2020-01-13', 'Software Engineer', 2),
('Sophia', 'Brown', 'sophiabrown@example.com', '123-456-7903', '2020-01-14', 'Software Engineer', 2),
('William', 'Lee', 'williamlee@example.com', '123-456-7904', '2020-01-15', 'QA Engineer', 3),
('Ava', 'Kim', 'avakim@example.com', '123-456-7905', '2020-01-16', 'UI Designer', 3),
('James', 'Chen', 'jameschen@example.com', '123-456-7906', '2020-01-17', 'Software Engineer', 4),
('Mia', 'Wang', 'miawang@example.com', '123-456-7907', '2020-01-18', 'Product Manager', 4),
('Benjamin', 'Garcia', 'benjamingarcia@example.com', '123-456-7908', '2020-01-19', 'Software Engineer', 5),
('Is', 'Martinez', 'isabellamartinez@example.com', '123-456-7909', '2020-01-20', 'QA Engineer', 5);

> * INSERT INTO Salaries (EmployeeID, BaseSalary, Bonus, EffectiveDate) SELECT EmployeeID, 50000 + RANDOM() * 30000, 1000 + RANDOM() * 5000, CURRENT_DATE - INTERVAL '1 year' FROM Employees;

> * INSERT INTO TimeOffRequests (EmployeeID, StartDate, EndDate, RequestStatus) SELECT EmployeeID, CURRENT_DATE + INTERVAL '1 month', CURRENT_DATE + INTERVAL '1 month' + INTERVAL '1 week', 'Approved' FROM Employees LIMIT 50;

> * INSERT INTO Departments (DepartmentName) VALUES ('Human Resources'), ('Finance'), ('IT'), ('Marketing'), ('Sales');

### relationship 
* Employees表和Departments表之间是一对多的关系，即一个部门可以有多个员工，但一个员工只能属于一个部门。这个关系通过Employees表中的DepartmentID列和Departments表中的ID列建立。
* Departments表和Employees表之间也是多对一的关系，即一个员工可以是一个部门的经理，但一个部门只能有一个经理。这个关系通过Departments表中的ManagerID列和Employees表中的EmployeeID列建立。
* Salaries表和Employees表之间是一对多的关系，即一个员工可以有多个薪资记录，但一个薪资记录能属于一个员工。这个关系通过Salaries表中的EmployeeID列和Employees表中的EmployeeID列建立。
* TimeOffRequests表和Employees表之间也是一对多的关系，即一个员工可以有多个请假记录，但一个请假记录只能属于一个员工这个关系通过TimeOffRequests表中的EmployeeID列和Employees表中的EmployeeID列建立





