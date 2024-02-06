drop database IF EXISTS highfourm;

create database IF NOT EXISTS highfourm character set = utf8mb4;

use highfourm;

show tables;

create table IF NOT EXISTS company (
	company_id int auto_increment NOT NULL COMMENT '회사 코드',
    company_name varchar(30) NOT NULL COMMENT '회사명',
    primary key(company_id)
);

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

create table IF NOT EXISTS orders (
	order_id varchar(50) unique NOT NULL COMMENT '주문 코드',
	vendor varchar(50) NOT NULL COMMENT '거래처명',
	manager varchar(30) NOT NULL COMMENT '담당자',
	due_date date NOT NULL COMMENT '납기일',
	ending_state char(1) NOT NULL default 'N' COMMENT '종결 여부',
	order_date date NOT NULL COMMENT '주문일',
    primary key(order_id)
);


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


create table IF NOT EXISTS product (
	product_id varchar(50) NOT NULL COMMENT '제품 코드', 
	product_name varchar(50) NOT NULL COMMENT '제품명',
	write_date date NOT NULL COMMENT '작성일',
	update_date date COMMENT '수정일',
    primary key(product_id)
);


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


create table IF NOT EXISTS production_plan (
	production_plan_id varchar(50) unique NOT NULL COMMENT '생산 계획 코드',
    product_id varchar(50) NOT NULL COMMENT '제품 코드',
    order_id varchar(50) NOT NULL COMMENT '주문 코드',  
	production_unit varchar(30) NOT NULL COMMENT '생산 단위',
	production_plan_amount bigint COMMENT '생산 계획 수량',
	production_start_date date COMMENT '착수일',
    primary key(production_plan_id),
	foreign key(order_id) references orders(order_id)
    ON UPDATE CASCADE,
	foreign key(product_id) references product(product_id)
    ON UPDATE CASCADE
);


create table IF NOT EXISTS work_performance (
	work_performance_id bigint auto_increment NOT NULL COMMENT '작업 실적 코드',
	production_plan_id varchar(50) NOT NULL COMMENT '생산 계획 코드',
	work_date date NOT NULL COMMENT '작업 실적 일자',
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


create table IF NOT EXISTS monthly_production_plan (
	month char(10) NOT NULL COMMENT '월',
	production_plan_id varchar(50) NOT NULL COMMENT '생산 계획 코드',
	production_amount bigint NOT NULL COMMENT '생산 수량',
    primary key(month, production_plan_id),
    foreign key(production_plan_id) references production_plan(production_plan_id)
    ON UPDATE CASCADE
);


create table IF NOT EXISTS process (
	process_id varchar(50) NOT NULL COMMENT '공정 코드', 
	product_id varchar(50) NOT NULL COMMENT '제품 코드', 
	sequence int NOT NULL COMMENT '공정 순서',
	process_name varchar(30) NOT NULL COMMENT '공정명',
	time_unit varchar(30) NOT NULL COMMENT '시간 단위',
	standard_work_time int NOT NULL COMMENT '표준 작업 시간',
	output_unit varchar(20) NOT NULL COMMENT '산출물 단위',
    primary key(process_id),
    foreign key(product_id) references product(product_id)
    ON UPDATE CASCADE
);


create table IF NOT EXISTS stock_management (
	management_id bigint auto_increment NOT NULL COMMENT '재고 관리 코드',
	management_name varchar(10) NOT NULL COMMENT '재고 관리명',
    primary key(management_id)
);


create table IF NOT EXISTS material (
    material_id varchar(50) unique NOT NULL COMMENT '원자재 코드',
    material_name varchar(30) NOT NULL COMMENT '원자재명',
	unit varchar(20) NOT NULL COMMENT '단위',
	primary key(material_id)
);


create table IF NOT EXISTS required_material (
	product_id varchar(50) NOT NULL COMMENT '제품 코드', 
    material_id varchar(50) NOT NULL COMMENT '원자재 코드', 
	input_process varchar(30) NOT NULL COMMENT '투입 공정',
	input_amount bigint NOT NULL COMMENT '투입량',
    primary key(product_id, material_id),
    foreign key(product_id) references product(product_id)
    ON UPDATE CASCADE,
    foreign key(material_id) references material(material_id)
    ON UPDATE CASCADE
);



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

#drop table material_history;


create table IF NOT EXISTS material_history (
	material_history_id bigint auto_increment NOT NULL COMMENT '수급 내역 코드',
	material_id varchar(50) NOT NULL COMMENT '자재 코드',
	order_date date NOT NULL COMMENT '발주일',
	recieving_date date COMMENT '입고일',
	standard varchar(20) NOT NULL COMMENT '규격/사양',
	supplier varchar(50) COMMENT '공급처',
	material_inventory bigint  COMMENT '재고량',
	inbound_amount bigint COMMENT '입고량',
	order_amount bigint NOT NULL COMMENT '발주량',
	unit_price bigint NOT NULL COMMENT '입고 단가',
	note varchar(225) COMMENT '비고',
    primary key(material_history_id),
    foreign key(material_id) references material(material_id)
    ON UPDATE CASCADE
);
alter table material_history auto_increment=1000;



create table IF NOT EXISTS notification (
	notification VARCHAR(255) COMMENT '알림'
);


