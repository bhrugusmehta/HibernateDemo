This is Hibernate demo, I have used Mysql as database.
so you have to first create "company" database and have to create table "sales".

sales table structure:
id primary key auto increment
name string(30)
item string(100)
Date date
amount int(11)

Then open HibernateConfig.java file from configure folder,
set your "username" and "password" for your database(company).

then do run.

1 for List out the sales 
2 for Create new sales 
3 for Delete sales by id 
4 for Update sales by id (Only amount will be ask to update) 
0 for To exit 
