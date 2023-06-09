drop schema if exists detective cascade;
CREATE SCHEMA IF NOT EXISTS detective;

create table if not exists detective.plot (
    id serial primary key,
    name varchar(256),
    text varchar(256)[]
);

create table if not exists detective.formula (
    id serial primary key,
    description varchar(256),
    operators varchar(256)[],
    plot_id INTEGER REFERENCES detective.plot (id)
);

create table if not exists detective.literal (
    id serial primary key,
    name varchar(256),
    description varchar(256),
    suspect BOOLEAN DEFAULT FALSE
);

create table if not exists detective.formula_literal (
    id BIGSERIAL PRIMARY KEY,
    formula_id INTEGER NOT NULL REFERENCES detective.formula,
    literal_id   INTEGER NOT NULL REFERENCES detective.literal
);

create table if not exists detective.evidence (
    id serial primary key,
    left_pos INTEGER,
    right_pos INTEGER,
    top_pos INTEGER,
    bottom_pos INTEGER
);

insert into detective.plot (name, text)
values ('Яблоко', ARRAY ['Имеются следующие данные о яблоке.','Яблоко красное','. ','Яблоко ароматное','. ','Если яблоко красное и яблоко ароматное, то яблоко вкусное','. Исходя из представленных данных будет ли верно утверждение, что яблоко вкусное?']);

insert into detective.formula (description, plot_id, operators)
values
('Яблоко красное', 1, ARRAY ['*']),
('Яблоко ароматное', 1, ARRAY ['*']),
('Если яблоко красное и яблоко ароматное, то яблоко вкусное', 1, ARRAY ['=>','&','*','*','*']);

insert into detective.literal (name, description)
values
('A', 'яблоко красное'),
('B', 'яблоко ароматное'),
('C', 'яблоко вкусное');

insert into detective.evidence (left_pos, right_pos, top_pos, bottom_pos)
values (10, 10, 10, 10);

insert into detective.formula_literal (formula_id, literal_id)
values
(1, 1),
(2, 2),
(3, 1),
(3, 2),
(3, 3);
