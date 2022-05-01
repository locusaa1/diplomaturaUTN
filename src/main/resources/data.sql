use spring;

create table provinces
(
    id   int         not null auto_increment,
    name varchar(50) not null,
    constraint pk_provinces primary key (id)
);

create table cities
(
    id          int         not null auto_increment,
    id_province int         not null,
    name        varchar(50) not null,
    area_code   varchar(30) not null,
    postal_code varchar(30) not null,
    constraint pk_cities primary key (id),
    constraint fk_province_in_city foreign key (id_province) references provinces (id)
);

create table phones
(
    id     int         not null auto_increment,
    number varchar(40) not null unique,
    constraint pk_phones primary key (id)
);

create table persons
(
    id        int         not null auto_increment,
    id_city   int         not null,
    id_phone  int         not null,
    name      varchar(50) not null,
    last_name varchar(50) not null,
    dni       varchar(30) not null unique,
    constraint pk_persons primary key (id),
    constraint fk_city_in_person foreign key (id_city) references cities (id),
    constraint fk_phone_in_person foreign key (id_phone) references phones (id)
);

create table employees
(
    id        int         not null auto_increment,
    id_person int         not null,
    username  varchar(30) not null unique,
    password  varchar(30) not null,
    constraint pk_employees primary key (id),
    constraint fk_person_in_employee foreign key (id_person) references persons (id)
);

create table fees
(
    id                  int    not null auto_increment,
    id_origin_city      int    not null,
    id_destination_city int    not null,
    cost                double not null,
    start_time          time   not null,
    end_time            time   not null,
    constraint pk_fees primary key (id, id_origin_city, id_destination_city),
    constraint fk_origin_city_in_fee foreign key (id_origin_city) references cities (id),
    constraint fk_destination_city_in_fee foreign key (id_destination_city) references cities (id)
);

create table bills
(
    id              int    not null auto_increment,
    id_person       int    not null,
    calls_sum       int    not null,
    total           double not null,
    generated_date  date   not null,
    expiration_date date   not null,
    constraint pk_bills primary key (id),
    constraint fk_person_in_bill foreign key (id_person) references persons (id)
);

create table calls
(
    id                   int    not null auto_increment,
    id_origin_phone      int    not null,
    id_destination_phone int    not null,
    id_origin_city       int    not null,
    id_destination_city  int    not null,
    id_fee               int    not null,
    id_bill              int    not null,
    duration             int    not null,
    total                double not null,
    constraint pk_calls primary key (id),
    constraint fk_origin_phone_in_call foreign key (id_origin_phone) references phones (id),
    constraint fk_destination_phone_in_call foreign key (id_destination_phone) references phones (id),
    constraint fk_origin_city_in_call foreign key (id_origin_city) references cities (id),
    constraint fk_destination_city_in_call foreign key (id_destination_city) references cities (id),
    constraint fk_fee_in_call foreign key (id_fee) references fees (id),
    constraint fk_bill_in_call foreign key (id_bill) references bills (id)
);

