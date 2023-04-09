PRAGMA foreign_keys=OFF;
BEGIN TRANSACTION;
CREATE TABLE paint(pid INTEGER PRIMARY KEY AUTOINCREMENT, pname TEXT,price INTEGER, descirption TEXT, image TEXT);
INSERT INTO paint VALUES(1,'Blue',5000,'blue color','2131165409');
INSERT INTO paint VALUES(2,'Black',5000,'black color','2131165408');
INSERT INTO paint VALUES(3,'green',5000,'color','2131165410');
INSERT INTO paint VALUES(4,'grey',5000,'color','2131165411');
COMMIT;