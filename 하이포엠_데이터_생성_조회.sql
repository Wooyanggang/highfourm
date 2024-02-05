use highfourm;
select * from company;
select * from users;
select * from orders;
select * from file;
select * from product;
select * from order_detail;
select * from work_performance;
select * from production_plan;
select * from monthly_production_plan;
select * from process;
select * from required_material;
select * from material;
select * from stock_management;
select * from material_stock;
select * from material_history;

##insert
insert into company values(1, 'Samsung'); 
insert into company values(2, 'LG'); 

insert into users(user_name, emp_no, position, birth, email, company_id) values ('홍길동', 1000, '사원', '1999-01-01', 'hong333@gmail.com', 1);
insert into users(user_name, emp_no, position, birth, email, company_id, register_state) values ('박보검', 1001, '대리', '1990-01-01', 'park1@gmail.com', 2, 'Y');
insert into users values (null, 'bbb@gmail.com', '$2a$10$lWxWaykWnxXBLsohj.rcZeoeBvEwjuc4gybe.RzpBWK40JfIiTyNS', '하이포엠', 01, '총관리자', '1990-01-01', 'aaa@gmail.com', 2, 'Y', 'ADMIN');

insert into stock_management values(null, '정량');
insert into stock_management values(null, '정기');
insert into stock_management values(null, '수동');


insert product(product_id,product_name,write_date,update_date)
values('fb-01','황금 붕어빵','2021-2-9','2021-10-9');
insert product(product_id,product_name,write_date,update_date)
values('fb-02','피자 붕어빵','2022-2-9','2022-10-9');
insert product(product_id,product_name,write_date,update_date)
values('fb-03','슈크림 붕어빵','2022-3-9','2022-3-9');
insert product(product_id,product_name,write_date,update_date)
values('fb-04','마라 붕어빵','2023-2-9','2023-2-9');

insert process(product_id, process_id, sequence, process_name, time_unit, standard_work_time, output_unit) 
values
('fb-01','aw-01','1','반죽','min','8','EA'),
('fb-01','aw-02','2','굽기','min','5','EA'),
('fb-01','aw-03','3','포장','min','10','EA'),
('fb-02','aw-04','1','반죽','min','12','EA'),
('fb-02','aw-05','2','굽기','min','5','EA'),
('fb-02','aw-06','3','포장','min','10','EA'),
('fb-03','aw-07','1','반죽','min','7','EA'),
('fb-03','aw-08','2','굽기','min','5','EA'),
('fb-03','aw-09','3','포장','min','10','EA'),
('fb-04','aw-10','1','반죽','min','8','EA'),
('fb-04','aw-11','2','굽기','min','5','EA'),
('fb-04','aw-12','3','포장','min','10','EA');

insert into material 
values
('mt-01','밀가루','kg'),
('mt-02','우유','L'),
('mt-03','전란액','L'),
('mt-04','설탕','kg'),
('mt-05','소금','kg'),
('mt-06','베이킹 파우더','kg'),
('mt-07','팥앙금','kg'),
('mt-08','피자앙금','kg'),
('mt-09','슈크림','kg'),
('mt-10','마라앙금','kg'),
('mt-11','식용유','L'),
('mt-12','올리고당','L'),
('mt-13','붕어빵 포장지','EA'),
('mt-14','종이 박스','EA');

insert into material_stock
values
('mt-01', 1, 10000, 100000, 7, 0),
('mt-02', 1, 10000, 100000, 8, 0),
('mt-03', 2, 10000, 60000, 7, 0),
('mt-04', 2, 10000, 30000, 5, 0),
('mt-05', 1,10000, 300000, 6, 0),
('mt-06', 1,1500, 30000, 5, 0),
('mt-07', 3, null, 6000, null, 0),
('mt-08', 2, 1000, 6000, 6, 0),
('mt-09', 2, 100, 6000, 7, 0),
('mt-10', 3, null, 6000, null, 0),
('mt-11', 2, 100, 10000, 6, 0),
('mt-12', 1, 100, 10000, 7, 0),
('mt-13', 3, null, 20000, null,  0),
('mt-14', 2, 2000, 20000, 5, 0);

insert into material_history
values
(1000, 'mt-01', '2024-02-02', null, 'EA', '나진상사', null, null, 300, 5000, null),
(1001, 'mt-03', '2024-02-02', null, 'EA', '나진상사', null, null, 450, 6000, null),
(1002, 'mt-05', '2024-02-03', null, 'EA', '나진상사', null, null, 400, 4300, null);

insert into required_material
values
('fb-01','mt-01','반죽','10'),
('fb-01','mt-02','반죽','5'),
('fb-01','mt-03','반죽','5'),
('fb-01','mt-04','반죽','2'),
('fb-01','mt-05','반죽','1'),
('fb-01','mt-06','반죽','2'),
('fb-01','mt-07','굽기','7'),
('fb-01','mt-11','굽기, 반죽','2'),
('fb-01','mt-12','반죽','1'),
('fb-01','mt-13','포장','1'),
('fb-01','mt-14','포장','1'),
('fb-02','mt-01','반죽','10'),
('fb-02','mt-02','반죽','5'),
('fb-02','mt-03','반죽','5'),
('fb-02','mt-04','반죽','2'),
('fb-02','mt-05','반죽','1'),
('fb-02','mt-06','반죽','2'),
('fb-02','mt-08','굽기','7'),
('fb-02','mt-11','굽기, 반죽','2'),
('fb-02','mt-12','반죽','1'),
('fb-02','mt-13','포장','1'),
('fb-02','mt-14','포장','1'),
('fb-03','mt-01','반죽','10'),
('fb-03','mt-02','반죽','5'),
('fb-03','mt-03','반죽','5'),
('fb-03','mt-04','반죽','2'),
('fb-03','mt-05','반죽','1'),
('fb-03','mt-06','반죽','2'),
('fb-03','mt-09','굽기','7'),
('fb-03','mt-11','굽기, 반죽','2'),
('fb-03','mt-12','반죽','1'),
('fb-03','mt-13','포장','1'),
('fb-03','mt-14','포장','1'),
('fb-04','mt-01','반죽','10'),
('fb-04','mt-02','반죽','5'),
('fb-04','mt-03','반죽','5'),
('fb-04','mt-04','반죽','2'),
('fb-04','mt-05','반죽','1'),
('fb-04','mt-06','반죽','2'),
('fb-04','mt-10','굽기','7'),
('fb-04','mt-11','굽기, 반죽','2'),
('fb-04','mt-12','반죽','1'),
('fb-04','mt-13','포장','1'),
('fb-04','mt-14','포장','1');

insert orders(order_id,vendor,manager,order_date,due_date,ending_state)
values('2021-11-25-1','상우 정밀','아무개','2021-11-25','2022-10-25','y');
insert orders(order_id,vendor,manager,order_date,due_date,ending_state)
values('2021-09-29-1','나진 상사','김삼식','2021-9-29','2022-7-29','y');

insert order_detail(order_id,product_id,product_amount,unit_price)
values('2021-11-25-1','fb-01','1000','1000');
insert order_detail(order_id,product_id,product_amount,unit_price)
values('2021-11-25-1','fb-02','700','1500');
insert order_detail(order_id,product_id,product_amount,unit_price)
values('2021-11-25-1','fb-04','633','1400');
insert order_detail(order_id,product_id,product_amount,unit_price)
values('2021-09-29-1','fb-04','1234','1200');

insert into production_plan
values
('202109291-fb-04','fb-04','2021-09-29-1','ea','1300','2022-11-26');

insert into monthly_production_plan
values
('21-11','202109291-fb-04',30),
('21-12','202109291-fb-04',140),
('22-01','202109291-fb-04',140),
('22-02','202109291-fb-04',140),
('22-03','202109291-fb-04',150),
('22-04','202109291-fb-04',140),
('22-05','202109291-fb-04',140),
('22-06','202109291-fb-04',210),
('22-07','202109291-fb-04',210);

insert into work_performance
values
(1,'202109291-fb-04','2022-11-30',30,30,0,5,'김삼식',1,'2025-01-01',''),
(2,'202109291-fb-04','2022-12-30',143,140,3,20,'김삼식',1,'2025-01-01',''),
(3,'202109291-fb-04','2023-1-30',142,140,2,21,'김삼식',1,'2025-01-01',''),
(4,'202109291-fb-04','2023-2-27',144,140,4,22,'김삼식',1,'2025-01-01',''),
(5,'202109291-fb-04','2023-3-30',154,150,4,22,'김삼식',1,'2025-01-01',''),
(6,'202109291-fb-04','2023-4-30',142,140,2,19,'김삼식',1,'2025-01-01',''),
(7,'202109291-fb-04','2023-5-30',144,140,4,23,'김삼식',1,'2025-01-01',''),
(8,'202109291-fb-04','2022-6-30',215,210,5,30,'김삼식',1,'2025-01-01',''),
(9,'202109291-fb-04','2022-7-30',217,210,7,30,'김삼식',1,'2025-01-01','');


## select
SELECT *
  FROM INFORMATION_SCHEMA.COLUMNS
 WHERE TABLE_SCHEMA = 'highfourm'
   AND TABLE_NAME   = 'user';

SELECT *
  FROM INFORMATION_SCHEMA.COLUMNS
 WHERE TABLE_SCHEMA = 'highfourm'
   AND TABLE_NAME   = 'production_plan';

select *
from material ma
left join material_stock ms on ma.material_id = ms.material_id
left join stock_management sm on ms.management_id = sm.management_id;
