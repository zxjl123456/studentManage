create database nchu;

create table adminuser
(
  username varchar(20) primary key,
  password varchar(20)
);

insert into adminuser values ('admin', '123456');

update adminuser
set password = '111'
where username = 'admin';

create table student (
  studentId   varchar(20) primary key,
  studentName varchar(25)  not null,
  department  varchar(255) not null,
  sex         varchar(20)  not null,
  birthday    date
);

select
  studentId,
  studentName,
  sex,
  birthday,
  department
from student
where studentId like "150412%"

-- 创建课程表
create table Course (
  courseNum  varchar(20) primary key,
  courseName varchar(255),
  grade      float
);

-- 创建选课表
create table CourseSelection (
  studentId varchar(20),
  courseNum varchar(20),
  grade     float,
  primary key (studentId, courseNum)
);

create view studentAndCourse(studentId,studentName,courseNum,courseName,grade)
  as
  select student.studentId,studentName,course.courseNum,courseName,courseSelection.grade
from student,course,courseSelection
where student.studentId = courseSelection.studentId and Course.courseNum = CourseSelection.courseNum ;