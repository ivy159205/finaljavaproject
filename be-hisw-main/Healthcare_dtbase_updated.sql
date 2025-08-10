CREATE DATABASE Healthcare;
GO
USE Healthcare;
GO
CREATE TABLE [User](
	[user_id] INT IDENTITY(1,1) PRIMARY KEY, -- IDENTITY(seed, increment),
	username NVARCHAR(255),
	[password] NVARCHAR(255),
	email NVARCHAR(255),
	phone_number NVARCHAR(255),
	gender NVARCHAR(255),
	dob DATE,
	[role] NVARCHAR(255)
);

CREATE TABLE DailyLog(
	log_id INT IDENTITY(1, 1) PRIMARY KEY,
	[user_id] INT,
	log_date DATE,
	note NVARCHAR(255),
	FOREIGN KEY([user_id]) REFERENCES [User]([user_id])
);

CREATE TABLE MetricType(
	metric_id INT IDENTITY(1, 1) PRIMARY KEY,
	[name] NVARCHAR(255),
	unit NVARCHAR(255)
);

CREATE TABLE HealthRecord(
	health_record_id INT IDENTITY(1,1) PRIMARY KEY, -- Đã đổi thành INT IDENTITY(1,1)
	[log_id] INT,
	metric_id INT,
	[value] NVARCHAR(255),
	FOREIGN KEY([log_id]) REFERENCES [DailyLog]([log_id]),
	FOREIGN KEY(metric_id) REFERENCES MetricType(metric_id)
);

CREATE TABLE [Target](
	target_id INT IDENTITY(1, 1) PRIMARY KEY,
	[user_id] INT,
	title NVARCHAR(255),
	[status] NVARCHAR(255),
	[start_date] DATE,
	end_date DATE,
	FOREIGN KEY([user_id]) REFERENCES [User]([user_id])
);

CREATE TABLE TargetDetail(
	detail_id INT IDENTITY(1, 1) PRIMARY KEY,
	target_id INT,
	metric_id INT,
	comparison_type NVARCHAR(255),
	target_value NVARCHAR(255),
	aggregation_type NVARCHAR(255), -- daily, last, sum
	FOREIGN KEY(target_id) REFERENCES [Target](target_id),
	FOREIGN KEY(metric_id) REFERENCES MetricType(metric_id)
);

ALTER TABLE [User]
ADD weight FLOAT,
    height FLOAT;
