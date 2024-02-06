drop database IF EXISTS highfourm;

create database IF NOT EXISTS highfourm character set = utf8mb4;

use highfourm;

show tables;

create table IF NOT EXISTS email_token (
	id varchar(50) unique COMMENT '이메일 토큰 ID',
	user_no bigint COMMENT '사용자 번호',
    expired int COMMENT '만료여부',
    expiration_date timestamp COMMENT '만료기간',
    primary key(id),
    foreign key (user_no) references users(user_no)
    ON UPDATE CASCADE
    ON DELETE CASCADE
);
drop table email_token;

create table IF NOT EXISTS company (
	company_id int auto_increment NOT NULL COMMENT '회사 코드',
    company_name varchar(30) NOT NULL COMMENT '회사명',
    primary key(company_id)
);

insert into company values(1, 'Samsung'); 
insert into company values(2, 'LG'); 

select * from company;

create table IF NOT EXISTS users (
	user_no bigint auto_increment NOT NULL COMMENT '사용자 번호',
    user_id varchar(50) unique COMMENT '사용자 ID',
    password varchar(255) COMMENT '비밀번호',
	user_name varchar(20) NOT NULL COMMENT '사용자 이름',
	emp_no bigint NOT NULL COMMENT '사번',
	position varchar(30) NOT NULL COMMENT '직급',
	birth date NOT NULL COMMENT '생년월일',
	email varchar(50) unique NOT NULL COMMENT '이메일',
	company_id int NOT NULL COMMENT '회사 코드',
	register_state char(1) NOT NULL default 'N' COMMENT '가입 여부',
    role char(10) NOT NULL default 'USER' COMMENT '권한',
	primary key(user_no),
	foreign key (company_id) references company(company_id)
    ON UPDATE CASCADE
);
  drop table users;
insert into users(user_name, emp_no, position, birth, email, company_id, role) values ('홍길동', 1000, '사원', '2024-01-01', 'hong333@gmail.com', 1, 'USER');
insert into users values (null, 'id', '', '김이박', 1001, '대리', '1990-12-31', 'kimleepark@naver.com', 2, 'N', 'USER');

select * from users where role not like 'ADMIN';

select * from users;

create table IF NOT EXISTS orders (
	order_id varchar(50) unique NOT NULL COMMENT '주문 코드',
	vendor varchar(50) NOT NULL COMMENT '거래처명',
	manager varchar(30) NOT NULL COMMENT '담당자',
	product varchar(50) NOT NULL COMMENT '품목',
	due_date date NOT NULL COMMENT '납기일',
	ending_state char(1) NOT NULL default 'N' COMMENT '종결 여부',
	order_date date NOT NULL COMMENT '주문일',
    primary key(order_id)
);
insert into orders values('주문 코드', '', '', '1990-02-20', '', '1991-01-01');
insert into orders values('주문 코드2', '', '', '1990-12-20', '', '1991-01-01');

SELECT *
  FROM INFORMATION_SCHEMA.COLUMNS
 WHERE TABLE_SCHEMA = 'highfourm'
   AND TABLE_NAME   = 'users';
   
select * from orders;

create table IF NOT EXISTS file (
	file_id	bigint auto_increment NOT NULL COMMENT '파일 아이디',
	order_id varchar(50) unique NOT NULL COMMENT '주문 코드',
	original_name varchar(300) NOT NULL COMMENT '파일 원본명',
	changed_name varchar(300) unique NOT NULL COMMENT '파일 변경명',
	file_type varchar(50) NOT NULL COMMENT '파일 타입',
	file_size bigint NOT NULL COMMENT '파일 크기',
	file_path varchar(225) NOT NULL COMMENT '파일 저장 위치',
    primary key(file_id),
    foreign key(order_id) references orders(order_id)
    ON UPDATE CASCADE
);

select * from file;

create table IF NOT EXISTS product (
	product_id varchar(50) unique NOT NULL COMMENT '제품 코드',
	product_name varchar(50) NOT NULL COMMENT '제품명',
	write_date date NOT NULL COMMENT '작성일',
	update_date date COMMENT '수정일',
    primary key(product_id)
);
insert into product values ('제품 코드', '제품명', '2000-01-01', '2000-12-31');
insert into product values ('제품 코드2', '제품명2', '2000-01-01', '2000-12-31');
insert into product values ('product_id', 'product_name', '2000-01-01', '2000-12-31');

select * from product;

create table IF NOT EXISTS order_detail (
	order_id varchar(50) NOT NULL COMMENT '주문 코드',
    product_id varchar(50) NOT NULL COMMENT '제품 코드',
	product_amount bigint NOT NULL COMMENT '주문 수량',
	unit_price bigint NOT NULL COMMENT '단가',
    primary key(order_id, product_id),
    foreign key(order_id) references orders(order_id)
    ON UPDATE CASCADE,
    foreign key(product_id) references product(product_id)
    ON UPDATE CASCADE
);

select * from order_detail;

create table IF NOT EXISTS production_plan (
	production_plan_id varchar(50) unique NOT NULL COMMENT '생산 계획 코드',
    product_id varchar(50) unique NOT NULL COMMENT '제품 코드',
    order_id varchar(50) NOT NULL COMMENT '주문 코드',
	production_unit varchar(30) NOT NULL COMMENT '생산 단위',
	production_plan_amount bigint NOT NULL COMMENT '생산 계획 수량',
	production_start_date date NOT NULL COMMENT '착수일',
	due_date date NOT NULL COMMENT '납기일',
    primary key(production_plan_id),
	foreign key(order_id) references orders(order_id)
    ON UPDATE CASCADE,
	foreign key(product_id) references product(product_id)
    ON UPDATE CASCADE
);
-- drop table production_plan;
insert into production_plan values('생산 계획 코드', '제품 코드', '주문 코드', '', 50, '1234-01-01', '1345-01-01');
insert into production_plan values('생산 계획 코드2', '제품 코드2', '주문 코드2', '', 30, '1234-01-01', '1345-01-01');
insert into production_plan values('plan1', 'product_id', '주문 코드', '', 25, '1990-00-00', '2023-05-10');
insert into production_plan values('생산 계획 코드4', '제품 코드', '주문 코드', '', 10, '1234-01-01', '1345-01-01');
insert into production_plan values('생산 계획 코드5', '제품 코드', '주문 코드2', '', 20, '1234-01-01', '1345-01-01');
insert into production_plan values('생산 계획 코드6', '제품 코드', '주문 코드2', '', 30, '1234-01-01', '1345-01-01');

select * from production_plan;

SELECT *
  FROM INFORMATION_SCHEMA.COLUMNS
 WHERE TABLE_SCHEMA = 'highfourm'
   AND TABLE_NAME   = 'production_plan';

select * from required_material;

create table IF NOT EXISTS work_performance (
	work_performance_id bigint auto_increment NOT NULL COMMENT '작업 실적 코드',
	production_plan_id varchar(50) NOT NULL COMMENT '생산 계획 코드',
	working_date date NOT NULL COMMENT '작업 실적 일자',
	production_amount bigint NOT NULL COMMENT '생산 수량',
	acceptance_amount bigint NOT NULL COMMENT '양품 수량',
	defective_amount bigint NOT NULL COMMENT '불량 수량',
	working_time int NOT NULL COMMENT '작업 시간',
	manager varchar(30) NOT NULL COMMENT '담당자',
	lot_no int NOT NULL COMMENT '로트 번호',
	valid_date date COMMENT '유효 일자',
	note varchar(225) COMMENT '비고',
    primary key(work_performance_id),
    foreign key(production_plan_id) references production_plan(production_plan_id)
    ON UPDATE CASCADE
);

select * from work_performance;

create table IF NOT EXISTS monthly_product_plan (
	month int NOT NULL COMMENT '월',
	production_plan_id varchar(50) NOT NULL COMMENT '생산 계획 코드',
	production_amount bigint NOT NULL COMMENT '생산 수량',
    primary key(month, production_plan_id),
    foreign key(production_plan_id) references production_plan(production_plan_id)
    ON UPDATE CASCADE
);

select * from monthly_product_plan;

create table IF NOT EXISTS process (
	product_id varchar(50) NOT NULL COMMENT '제품 코드',
	process_id varchar(50) unique NOT NULL COMMENT '공정 코드',
	sequence int NOT NULL COMMENT '공정 순서',
	process_name varchar(30) NOT NULL COMMENT '공정명',
	time_unit varchar(30) NOT NULL COMMENT '시간 단위',
	standard_work_time int NOT NULL COMMENT '표준 작업 시간',
	output_unit varchar(20) NOT NULL COMMENT '산출물 단위',
    primary key(process_id),
    foreign key(product_id) references product(product_id)
    ON UPDATE CASCADE
);

select * from process;

create table IF NOT EXISTS material (
    material_id varchar(50) unique NOT NULL COMMENT '원자재 코드',
    material_name varchar(30) NOT NULL COMMENT '원자재명',
	unit varchar(20) NOT NULL COMMENT '단위',
	primary key(material_id)
);
insert into material values('원자재 코드', '원자재명', '단위');
insert into material values('원자재 코드2', '원자재명2', '단위');
insert into material values('원자재 코드3', '원자재명3', '단위');

select * from material;

create table IF NOT EXISTS required_material (
	product_id varchar(50) NOT NULL COMMENT '제품 코드',
    material_id varchar(50) NOT NULL COMMENT '원자재 코드',
	input_process varchar(30)NOT NULL COMMENT '투입 공정',
	input_amount bigint NOT NULL COMMENT '투입량',
    primary key(product_id, material_id),
    foreign key(product_id) references product(product_id)
    ON UPDATE CASCADE,
    foreign key(material_id) references material(material_id)
    ON UPDATE CASCADE
);
-- drop table required_material; 
insert into required_material values ('제품 코드', '원자재 코드', '투입공정', 100);
insert into required_material values ('제품 코드', '원자재 코드3', '투입공정', 20);
insert into required_material values ('제품 코드2', '원자재 코드2', '투입공정', 100);

select * from required_material;

select * from required_material
where product_id like '제품 코드';

create table IF NOT EXISTS stock_management (
	management_id bigint auto_increment NOT NULL COMMENT '재고 관리 코드',
	management_name varchar(10) NOT NULL COMMENT '재고 관리명',
    primary key(management_id)
);

select * from stock_management;

create table IF NOT EXISTS material_stock (
	material_id varchar(50) unique NOT NULL COMMENT '원자재 코드',
	management_id bigint NOT NULL COMMENT '재고 관리 코드',
	safety_stock bigint COMMENT '안전 재고',
	max_stock bigint COMMENT '최대 재고',
	lead_time int COMMENT '리드 타임',
	total_stock bigint NOT NULL COMMENT '총 재고량',
    primary key(material_id),
    foreign key(material_id) references material(material_id)
    ON UPDATE CASCADE,
    foreign key(management_id) references stock_management(management_id)
    ON UPDATE CASCADE
);

select * from material_stock;

create table IF NOT EXISTS material_history (
	material_history_id bigint auto_increment NOT NULL COMMENT '수급 내역 코드',
	material_id varchar(50) unique NOT NULL COMMENT '자재 코드',
	order_date date NOT NULL COMMENT '발주일',
	recieving_date date COMMENT '입고일',
	standard varchar(20) NOT NULL COMMENT '규격/사양',
	supplier varchar(50) NOT NULL COMMENT '공급처',
	material_inventory bigint NOT NULL COMMENT '재고량',
	inbound_amount bigint COMMENT '입고량',
	order_amount bigint NOT NULL COMMENT '발주량',
	unit_price bigint COMMENT '입고 단가',
	note varchar(225) COMMENT '비고',
    primary key(material_history_id),
    foreign key(material_id) references material(material_id)
    ON UPDATE CASCADE
);

select * from material_history;

-- 자재 총 산출 production_plan 
select plan.due_date, production_plan_id, plan.product_id, p.product_name, production_plan_amount
from production_plan plan 
left join product p on plan.product_id = p.product_id
left join orders o on plan.order_id = o.order_id;

-- 자재 총 산출 material 
select plan.production_plan_id, plan.product_id, p.product_name, m.material_name, r.material_id, r.input_amount, sum(plan.production_plan_amount*r.input_amount) as total_material_amount,
total_stock, safety_stock, inbound_amount
from production_plan plan 
left join product p on plan.product_id = p.product_id
left join required_material r on plan.product_id = r.product_id
left join material m on r.material_id = m.material_id
left join material_stock s on m.material_id = s.material_id
left join material_history h on m.material_id = h.material_id
where production_plan_id like '생산 계획 코드'
group by production_plan_id;

-- plan.production_plan_id, plan.product_id, p.product_name, m.material_name, r.material_id, r.input_amount, sum(plan.production_plan_amount*r.input_amount) as total_material_amount,
-- total_stock, safety_stock, inbound_amount

select plan.production_plan_id, plan.product_id, m.material_name, r.material_id, r.input_amount, total_stock, safety_stock, inbound_amount
from production_plan plan
inner join required_material r on plan.product_id = r.product_id
left join material m on r.material_id = m.material_id
left join material_stock s on m.material_id = s.material_id
left join material_history h on m.material_id = h.material_id
where production_plan_id like '생산 계획 코드';

-- 자재 총 산출 검색<생산계획 코드 production_plan_id>
select due_date, production_plan_id, plan.product_id, p.product_name, production_plan_amount
from production_plan plan 
left join product p on plan.product_id = p.product_id
where production_plan_id like '%생산 계획%';

-- 자재 총 산출 검색<품번 product_id>
select due_date, production_plan_id, plan.product_id, p.product_name, production_plan_amount
from production_plan plan 
left join product p on plan.product_id = p.product_id
where plan.product_id like '%제품%';

-- 자재 총 산출 검색<품명 product_name>
select due_date, production_plan_id, plan.product_id, p.product_name, production_plan_amount
from production_plan plan 
left join product p on plan.product_id = p.product_id
where product_name like '%명%';

-- 자재 총 산출 검색<납기일 due_date>
select due_date, production_plan_id, plan.product_id, p.product_name, production_plan_amount
from production_plan plan 
left join product p on plan.product_id = p.product_id
where due_date like '%01%';

create table IF NOT EXISTS notification (
	notification VARCHAR(255) COMMENT '알림'
);