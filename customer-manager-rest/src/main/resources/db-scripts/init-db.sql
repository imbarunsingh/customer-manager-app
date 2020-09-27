CREATE database customer_manager;
use customer_manager;

create table address (
id bigint not null auto_increment,
 created_by varchar(255),
 created_date datetime(6),
 last_modified_by varchar(255), 
 last_modified_date datetime(6), 
 city varchar(150) not null, 
 country varchar(20) not null, 
 landmark varchar(255) not null, 
 latitude double precision, 
 longitude double precision, 
 pin_code integer not null, 
 state varchar(150) not null, 
 street_address_line_1 varchar(500) not null, 
 street_address_line_2 varchar(500), 
 customer_id bigint not null, 
 primary key (id)) engine=InnoDB;

create table customer (id bigint not null auto_increment, 
created_by varchar(255), 
created_date datetime(6), 
last_modified_by varchar(255), 
last_modified_date datetime(6), 
email_id varchar(50) not null, 
first_name varchar(255) not null, 
gender varchar(10) not null, 
last_name varchar(255) not null, 
phone_number bigint not null, 
primary key (id)) engine=InnoDB;

create table orders (id bigint not null auto_increment, 
created_by varchar(255), 
created_date datetime(6), 
last_modified_by varchar(255), 
last_modified_date datetime(6), 
order_status varchar(20) not null, 
product_name varchar(255) not null, 
product_price double precision not null, 
customer_id bigint not null, 
primary key (id)) engine=InnoDB;

alter table customer add constraint UK_p1nyof8six1aupbuhnlax3tkk unique (email_id);
alter table customer add constraint UK_rosd2guvs3i1agkplv5n8vu82 unique (phone_number);

alter table address add constraint FK93c3js0e22ll1xlu21nvrhqgg foreign key (customer_id) references customer (id);

alter table orders add constraint FK624gtjin3po807j3vix093tlf foreign key (customer_id) references customer (id);