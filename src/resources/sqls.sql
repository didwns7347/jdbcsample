create table BIT_MemberLevel(
	levelname varchar(20),
	low int,
	high int,
	PRIMARY KEY(levelname)
);
create table BIT_Member(
	memberid varchar(20) not null ,
	name varchar(20) not null,
	level varchar(20) default 'normal' not null,
	PRIMARY KEY(memberid) ,
	FOREIGN KEY (level) REFERENCES bit_MemberLevel (levelname)
);
create table BIT_Drink(
	drinkid int not null  auto_increment,
	name varchar(20) not null,
	price int not null,
	count int not null default 0 ,
	PRIMARY KEY(drinkid)
	create table BIT_BuyDrink(
	buydrinkid int not null  auto_increment,
	memberid varchar(20) not null ,
	drinkid int not null ,
	count int default 0 ,
	totalmoney int default 0,
	PRIMARY KEY(buydrinkid),
	FOREIGN KEY (memberid) REFERENCES bit_Member(memberid),
	FOREIGN KEY (drinkid) REFERENCES bit_Drink(drinkid)