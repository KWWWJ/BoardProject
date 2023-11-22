CMD -> sqlpuls -> system/1234

세션 변경 : alter session set "_ORACLE_SCRIPT"=true;  

계정생성 : create user board identified by qwer;

권한 부여 : grant coonct, resource, dba to board;

테이블 삭제 및 생성 :

drop table like_and_hates;
drop table comments;
drop table categories;
drop table boards;
drop table users;

create table users(
    id number generated as identity primary key ,
    user_id varchar2(50) not null unique,
    password varchar2(64) not null,
    name varchar2(15) not null,
    phone varchar2(14) not null,
    address varchar2(100),
    email varchar2(100) not null unique,
    git_address varchar2(100),
    gender number(1)not null,
    birth date,
    created_at timestamp
);

create table boards(
    id number generated as identity primary key,
    user_id number not null,
    title varchar2(100) not null,
    content long not null,
    veiws number default 0,
    created_at timestamp,
    constraint fk_user_id foreign key("user_id") references users("id")
);

create table like_and_hates(
    id number generated as identity primary key,
    user_id number,
    board_id number,
    like_and_hates number(1),
    created_at timestamp,
    constraint fk_user_lah_id foreign key("user_id") references users("id"),
    constraint fk_board_lah_id foreign key("board_id") references boards("id")
);

--create table comments(
--    
--);
--
--create table categories(
--    
--);
