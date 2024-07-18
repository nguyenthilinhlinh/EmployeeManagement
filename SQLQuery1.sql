use master
go

drop database if exists LeaveManagements
create database LeaveManagements
go

use LeaveManagements
go



drop table if exists Employees
CREATE TABLE Employees (
    EmployeeID INT PRIMARY KEY IDENTITY(1,1),
    Name NVARCHAR(100) NOT NULL,
    Email NVARCHAR(100) NOT NULL,
	Image NVARCHAR(250),
    Password NVARCHAR(100) NOT NULL,
    Role NVARCHAR(50) NOT NULL CHECK (Role IN ('Employee', 'Manager')),
	Status bit, 
);
go


drop table if exists LeaveRequests
CREATE TABLE LeaveRequests (
    RequestID INT PRIMARY KEY IDENTITY(1,1),
    EmployeeID INT FOREIGN KEY REFERENCES Employees(EmployeeID),
    LeaveType NVARCHAR(50) NOT NULL,
    StartDate DATE NOT NULL,
    EndDate DATE NOT NULL,
    Reason NVARCHAR(255),
    StatusLR NVARCHAR(50) NOT NULL CHECK (StatusLR IN ('Pending', 'Approved', 'Rejected','Watching')),
    ApproverID INT FOREIGN KEY REFERENCES Employees(EmployeeID),
    SubmissionDate DATETIME NOT NULL DEFAULT GETDATE(),
	Status bit, 
);
go


drop table if exists LeaveHistory
CREATE TABLE LeaveHistory (
    HistoryID INT PRIMARY KEY IDENTITY(1,1),
    EmployeeID INT FOREIGN KEY REFERENCES Employees(EmployeeID),
    LeaveType NVARCHAR(50) NOT NULL,
    StartDate DATE NOT NULL,
    EndDate DATE NOT NULL,
    Reason NVARCHAR(255),
    ApprovalStatus NVARCHAR(50) NOT NULL CHECK (ApprovalStatus IN ('Pending', 'Approved', 'Rejected')),
    ApproverID INT FOREIGN KEY REFERENCES Employees(EmployeeID),
    SubmissionDate DATETIME NOT NULL DEFAULT GETDATE(),
    ApprovalDate DATETIME,
	Status bit, 
);
go

create or alter proc selectLHEmp
	@EmployeeID INT = null

as
begin
	SELECT r.*, e.Name 
	FROM Employees e  
	JOIN LeaveHistory r ON e.EmployeeID = r.EmployeeID
    WHERE e.EmployeeID = @EmployeeID ;

end
go

create or alter proc selectLHAndEmp
	@EmployeeID INT = NULL

as
begin
	IF @EmployeeID IS NULL
        SELECT * FROM Employees;
    ELSE
        SELECT * FROM Employees WHERE EmployeeID = @EmployeeID;

end
go

create or alter proc selectLeaveRequests
    @RequestID INT = NULL,
    @EmployeeID INT = NULL
AS
BEGIN
    IF @RequestID IS NOT NULL
        SELECT * FROM LeaveRequests WHERE RequestID = @RequestID;
    ELSE IF @EmployeeID IS NOT NULL
        SELECT * FROM LeaveRequests WHERE EmployeeID = @EmployeeID;
    ELSE
        SELECT * FROM LeaveRequests 
		Where Status = 1;
END
go

create or alter proc selectLeaveHistory
    @HistoryID INT = NULL,
    @EmployeeID INT = NULL
AS
BEGIN
    IF @HistoryID IS NOT NULL
        SELECT * FROM LeaveHistory WHERE HistoryID = @HistoryID;
    ELSE IF @EmployeeID IS NOT NULL
        SELECT * FROM LeaveHistory WHERE EmployeeID = @EmployeeID;
    ELSE
        SELECT * FROM LeaveHistory;
END
go

create or alter proc UpdateStatus
    @requestsID INT = NULL
AS
BEGIN
   Update LeaveRequests 
   Set  Status =  0
   Where RequestID=@requestsID;
END
go

CREATE OR ALTER PROC selectLogin
@Email NVARCHAR(100),
@Password NVARCHAR(100)
AS
BEGIN
SELECT EmployeeID, Name, Email, Role, Status
    FROM Employees
WHERE Email = @Email AND Password = @Password;
END
GO

create or alter proc selectEmpID
	@EmployeeID INT = null

as
begin
	SELECT *
	FROM Employees 
    WHERE EmployeeID = @EmployeeID ;

end
go


INSERT INTO Employees (Name, Email, Password, Image, Role,	Status)
VALUES 
    ('User', 'user', '1234567', null, 'Employee', 1),
    ('Admin', 'admin', '1234567', null, 'Manager', 1),
    ('Le Van C', 'levanc@example.com', 'password123', null, 'Employee', 1),
    ('Pham Thi D', 'phamthid@example.com', 'password123', null, 'Manager', 1),
    ('Hoang Van E', 'hoangvane@example.com', 'password123', null, 'Employee', 1),
    ('Ngo Thi F', 'ngothif@example.com', 'password123', null, 'Employee', 1),
    ('Do Van G', 'dovang@example.com', 'password123', null, 'Manager', 1),
    ('Bui Thi H', 'buithih@example.com', 'password123', null, 'Manager', 1),
    ('Vu Van I', 'vuvani@example.com', 'password123', null, 'Employee', 1),
    ('Trinh Thi K', 'trinhthik@example.com', 'password123', null, 'Employee',1)

go

INSERT INTO LeaveRequests (EmployeeID, LeaveType, StartDate, EndDate, Reason, StatusLR, ApproverID, SubmissionDate, Status)
VALUES
    (1, N'Nghỉ bệnh', '2024-07-01', '2024-07-05', 'Bị cảm cúm', 'Pending', 3, GETDATE(), 1),
    (2, N'Nghỉ cá nhân', '2024-07-10', '2024-07-12', 'Công việc gia đình', 'Pending', 3, GETDATE(),1),
    (3, N'Nghỉ thai sản', '2024-08-01', '2024-12-01', 'Sinh con', 'Pending', 4, GETDATE(),1),
    (4, N'Nghỉ phép năm', '2024-09-15', '2024-09-20', 'Du lịch', 'Pending', 5, GETDATE(),1),
    (5, N'Nghỉ không lương', '2024-10-01', '2024-10-10', 'Đi du học', 'Pending', 6, GETDATE(),1),
    (6, N'Nghỉ phép năm', '2024-07-05', '2024-07-09', 'Đi du lịch', 'Pending', 7, GETDATE(),1),
    (7, 'Nghỉ bệnh', '2024-07-15', '2024-07-18', 'Bị cúm', 'Pending', 8, GETDATE(),1),
    (8, 'Nghỉ cá nhân', '2024-08-10', '2024-08-12', 'Công việc gia đình', 'Pending', 9, GETDATE(),1),
    (9, 'Nghỉ thai sản', '2024-09-01', '2024-12-01', 'Sinh con', 'Pending', 10, GETDATE(),1),
    (10, 'Nghỉ phép năm', '2024-10-20', '2024-10-25', 'Du lịch', 'Pending', 1, GETDATE(),1);
go 



INSERT INTO LeaveHistory (EmployeeID, LeaveType, StartDate, EndDate, Reason, ApprovalStatus, ApproverID, SubmissionDate, ApprovalDate, Status)
VALUES
    (1, 'Nghỉ bệnh', '2024-01-10', '2024-01-15', 'Cảm cúm', 'Approved', 3, '2024-01-05', '2024-01-06',1 ),
    (2, 'Nghỉ cá nhân', '2024-02-20', '2024-02-22', 'Việc gia đình', 'Approved', 3, '2024-02-15', '2024-02-16', 1),
    (3, 'Nghỉ thai sản', '2024-03-01', '2024-06-01', 'Sinh con', 'Approved', 4, '2024-02-25', '2024-02-26', 1),
    (4, 'Nghỉ phép năm', '2024-04-10', '2024-04-15', 'Du lịch', 'Rejected', 5, '2024-04-05', '2024-04-06', 1),
    (5, 'Nghỉ không lương', '2024-05-01', '2024-05-10', 'Đi du học', 'Pending', 6, '2024-04-25', NULL, 1),
    (6, 'Nghỉ phép năm', '2024-06-10', '2024-06-14', 'Du lịch', 'Approved', 7, '2024-06-01', '2024-06-02', 1),
    (7, 'Nghỉ bệnh', '2024-07-15', '2024-07-20', 'Bị cúm', 'Approved', 8, '2024-07-10', '2024-07-11', 1),
    (8, 'Nghỉ cá nhân', '2024-08-01', '2024-08-03', 'Việc gia đình', 'Rejected', 9, '2024-07-25', '2024-07-26', 1),
    (9, 'Nghỉ thai sản', '2024-09-10', '2024-12-10', 'Sinh con', 'Approved', 10, '2024-09-01', '2024-09-02',1 ),
    (10, 'Nghỉ phép năm', '2024-10-20', '2024-10-25', 'Du lịch', 'Pending', 1, '2024-10-10', NULL, 1);
go