create database highfourm character set = utf8mb4;

drop database highfourm;

use highfourm;

show tables;

create table IF NOT EXISTS company (
	company_id int auto_increment NOT NULL COMMENT '회사 코드',
    company_name varchar(30) NOT NULL COMMENT '회사명',
    primary key(company_id)
);

select * from company;

create table IF NOT EXISTS user (
	user_no bigint auto_increment NOT NULL COMMENT '사용자 번호',
    user_id varchar(50) NOT NULL COMMENT '사용자 ID',
    password varchar(255) NOT NULL COMMENT '비밀번호',
	user_name varchar(20) NOT NULL COMMENT '사용자 이름',
	emp_no bigint NOT NULL COMMENT '사번',
	position varchar(30) NOT NULL COMMENT '직급',
	birth date NOT NULL COMMENT '생년월일',
	email varchar(50) NOT NULL COMMENT '이메일',
	company_id int NOT NULL COMMENT '회사 코드',
	register_state char(1) NOT NULL default 'N' COMMENT '가입 여부',
    role char(10) NOT NULL COMMENT '권한',
	primary key(user_no),
	foreign key (company_id) references company(company_id)
    ON UPDATE CASCADE
);

select * from user;

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
SELECT *
  FROM INFORMATION_SCHEMA.COLUMNS
 WHERE TABLE_SCHEMA = 'highfourm'
   AND TABLE_NAME   = 'user';
   
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

select * from product;

create table IF NOT EXISTS order_detail (
	order_id varchar(50) unique NOT NULL COMMENT '주문 코드',
    product_id varchar(50) unique NOT NULL COMMENT '제품 코드',
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
    order_id varchar(50) unique NOT NULL COMMENT '주문 코드',
	production_unit varchar(30) NOT NULL COMMENT '생산 단위',
	production_amount bigint NOT NULL COMMENT '생산 계획 수량',
	production_start_date date NOT NULL COMMENT '착수일',
	due_date date NOT NULL COMMENT '납기일',
    primary key(production_plan_id),
	foreign key(order_id) references orders(order_id)
    ON UPDATE CASCADE,
	foreign key(product_id) references product(product_id)
    ON UPDATE CASCADE
);
SELECT *
  FROM INFORMATION_SCHEMA.COLUMNS
 WHERE TABLE_SCHEMA = 'highfourm'
   AND TABLE_NAME   = 'production_plan';

select * from production_plan;

create table IF NOT EXISTS work_performence (
	work_performence_no bigint auto_increment NOT NULL COMMENT '작업 실적 코드',
	production_plan_id varchar(50) NOT NULL COMMENT '생산 계획 코드',
	date date NOT NULL COMMENT '작업 실적 일자',
	production_amount bigint NOT NULL COMMENT '생산 수량',
	acceptancen_amount bigint NOT NULL COMMENT '양품 수량',
	defectiven_amount bigint NOT NULL COMMENT '불량 수량',
	working_time int NOT NULL COMMENT '작업 시간',
	manager varchar(30) NOT NULL COMMENT '담당자',
	lot_no int NOT NULL COMMENT '로트 번호',
	valid_date date COMMENT '유효 일자',
	note varchar(225) COMMENT '비고',
    primary key(work_performence_no),
    foreign key(production_plan_id) references production_plan(production_plan_id)
    ON UPDATE CASCADE
);

select * from work_performence;

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
	product_id varchar(50) unique NOT NULL COMMENT '제품 코드',
	process_id varchar(50) unique NOT NULL COMMENT '공정 코드',
	sequance int NOT NULL COMMENT '공정 순서',
	progress_name varchar(30) NOT NULL COMMENT '공정명',
	time_unit varchar(30) NOT NULL COMMENT '시간 단위',
	standard_time int NOT NULL COMMENT '표준 작업 시간',
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

select * from material;

create table IF NOT EXISTS required_material (
	product_id varchar(50) unique NOT NULL COMMENT '제품 코드',
    material_id varchar(50)	unique NOT NULL COMMENT '자재 코드',
	process varchar(30)	NOT NULL COMMENT '투입 공정',
	input_unit varchar(30) NOT NULL COMMENT '투입 단위',
    primary key(product_id),
    foreign key(product_id) references product(product_id)
    ON UPDATE CASCADE,
    foreign key(material_id) references material(material_id)
    ON UPDATE CASCADE
);

select * from required_material;

create table IF NOT EXISTS method (
	method_id bigint auto_increment NOT NULL COMMENT '재고 관리 코드',
	method_name varchar(10) NOT NULL COMMENT '재고 관리명',
    primary key(method_id)
);

select * from method;

create table IF NOT EXISTS material_stock (
	material_id varchar(50) unique NOT NULL COMMENT '원자재 코드',
	method_id bigint NOT NULL COMMENT '재고 관리 코드',
	safety_stock bigint COMMENT '안전 재고',
	max_stock bigint COMMENT '최대 재고',
	lead_time int COMMENT '리드 타임',
	total_stock bigint NOT NULL COMMENT '총 재고량',
    primary key(material_id),
    foreign key(material_id) references material(material_id)
    ON UPDATE CASCADE,
    foreign key(method_id) references method(method_id)
    ON UPDATE CASCADE
);

select * from material_stock;

create table IF NOT EXISTS material_log (
	material_log_id	bigint auto_increment NOT NULL COMMENT '수급 내역 코드',
	material_id varchar(50) unique NOT NULL COMMENT '자재 코드',
	order_date date NOT NULL COMMENT '발주일',
	recieving_date date COMMENT '입고일',
	standard varchar(20) NOT NULL COMMENT '규격/사양',
	supplier varchar(50) NOT NULL COMMENT '공급처',
	inventory_amount bigint NOT NULL COMMENT '재고량',
	inbound_amount bigint COMMENT '입고량',
	order_amount bigint NOT NULL COMMENT '발주량',
	unit_price bigint NOT NULL COMMENT '입고단가',
	note varchar(225) COMMENT '비고',
    primary key(material_log_id),
    foreign key(material_id) references material(material_id)
    ON UPDATE CASCADE
);

select * from material_log;







create table IF NOT EXISTS notification (
	notification VARCHAR(255) COMMENT '알림'
);
