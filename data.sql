CREATE SCHEMA IF NOT EXISTS detective;

create table if not exists detective.plot (
    id serial primary key,
    name varchar(256),
    text varchar(4096),
    img varchar(256)
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
    suspect BOOLEAN DEFAULT FALSE,
    plot_id INTEGER REFERENCES detective.plot (id)
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
    bottom_pos INTEGER,
    formula_id INTEGER REFERENCES detective.formula (id)
);

insert into detective.plot (name, text, img)
values ('Яблоко', 'Яблоко не красное. Яблоко ароматное. Если яблоко красное и яблоко ароматное, то яблоко вкусное', 'img/1');

insert into detective.formula (description, plot_id, operators)
values
('Яблоко не красное', 1, ARRAY ['!','*']),
('Яблоко ароматное', 1, ARRAY ['*']),
('Если яблоко красное и ароматное, то яблоко вкусное', 1, ARRAY ['=>','&','*','*','*']);

insert into detective.literal (name, description, plot_id)
values
('A', 'яблоко не красное', 1),
('B', 'яблоко ароматное', 1),
('C', 'яблоко вкусное', 1);

insert into detective.evidence (left_pos, right_pos, top_pos, bottom_pos, formula_id)
values (10, 10, 10, 10, 1);

insert into detective.formula_literal (formula_id, literal_id)
values
(1, 1),
(2, 2),
(3, 1),
(3, 2),
(3, 3);