# Capstone

### Drake Hunt

This is the capstone to my program at Southwest Applied Technology College. This is a budget app to help you track your income and expenses. 

There are a couple of things that need to happen to have this program run. The big one is MYSQL. Make sure that MYSQL is installed and running on your computer. Here is a link https://dev.mysql.com/doc/refman/8.0/en/windows-installation.html to help with that. You will then need to create a four column table. Here is the command to create that table: 

create table BudgetTable(ID INT NOT NULL  AUTO_INCREMENT PRIMARY KEY, userName VARCHAR(20) NOT NULL, passWord VARCHAR(20) NOT NULL, income DECIMAL(9,4));

You will also need this file mysql-connector-java-8.0.13.jar in your library to run the code. You can find this file in the finalProject folder. After you have done everything above start by running the BudgetServer, then run the Main. 

There will be a login screen. You can create user. The username is not case sensitive but the password is. After you login you can enter your expenses. When you save, it will create a file that will have the name of the database ID as id.wow (Example 1.wow). When you log back in it will show all the expenses. 
