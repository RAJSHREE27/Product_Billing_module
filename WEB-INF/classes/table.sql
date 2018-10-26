create table LoginUser(username varchar2(20), password varchar2(10));

insert into LoginUser values('khushbu', '222');
insert into LoginUser values('jagdish', '333');
insert into LoginUser values('hem', '444');

commit;

//----------------------------------------------------------------------
create table UserData(
	itemId number(10) primary key,
	item_name varchar2(20),
	item_type varchar2(20) not null,
	item_price number(9,3) 
);

commit;		// all tables merged into a single table;

insert into UserData values( 11, 'Jeans', 'wearOns',1000.77);
insert into UserData values( 12, 'T-shirt', 'wearOns',800.87);
insert into UserData values( 13, 'Jackets', 'wearOns',5000.684);
insert into UserData values( 14, 'Skirt', 'wearOns',756.457);

insert into UserData values( 21, 'Biography', 'books',500.43);
insert into UserData values( 22, 'Novel', 'books',643.70);
insert into UserData values( 23, 'Story', 'books',222.45);
insert into UserData values( 24, 'Poetry', 'books',453.78);

insert into UserData values( 31, 'Iphone', 'electronics',40000);
insert into UserData values( 32, 'Induction Heater', 'electronics',5000.8);
insert into UserData values( 33, 'Iron', 'electronics',2000);
insert into UserData values( 34, 'Tablet', 'electronics',9000.8);

commit;



//----------------------------------------

