create table users
(
    id bigserial not null
        constraint users_pk
            primary key,
    name varchar not null,
    surname varchar not null,
    address varchar not null,
    telephone_number varchar not null,
    created timestamp,
    changed timestamp,
    login varchar,
    password varchar,
    money double precision
);

alter table users owner to postgres;

create unique index users_id_uindex
	on users (id);

create index users_address_index
	on users (address);

create index users_telephone_number_index
	on users (telephone_number);

create index users_user_surname_index
	on users (surname);

create index users_user_surname_user_name_index
	on users (surname, name);

create unique index users_login_uindex
	on users (login);

create index users__money_index
	on users (money);

create table books
(
    id bigserial not null,
    title varchar not null,
    author varchar not null,
    publishing_house varchar not null,
    number_of_pages integer not null,
    genre varchar not null,
    price double precision
);

alter table books owner to postgres;

create unique index books_id_uindex
	on books (id);

create index books_title_index
	on books (title);

create index books_author_index
	on books (author);

create table purchase
(
    id bigserial not null
        constraint purchase_pk
            primary key,
    user_id bigint not null
        constraint purchase_users_id_fk
            references users,
    book_id integer not null
        constraint purchase_books_id_fk
            references books (id),
    date_of_buying date not null,
    created timestamp,
    changed timestamp,
    is_deleted boolean default false
);

alter table purchase owner to postgres;

create unique index purchase_id_uindex
	on purchase (id);

create index purchase_date_of_buying_index
	on purchase (date_of_buying);

create index purchase_id_index
	on purchase (id);

create table roles
(
    id serial not null
        constraint roles_pk
            primary key,
    role_name varchar(20) not null
);

alter table roles owner to postgres;

create unique index roles_role_name_uindex
	on roles (role_name);

create unique index roles_role_name_index
	on roles (role_name);

create table user_roles
(
    id bigserial not null
        constraint user_roles_pk
            primary key,
    role_id bigint default 1 not null
        constraint user_roles_roles_id_fk
            references roles,
    user_id bigint not null
        constraint user_roles_users_id_fk
            references users
);

alter table user_roles owner to postgres;

create index user_roles_user_id_index
	on user_roles (user_id);

create index user_roles_role_id_index
	on user_roles (role_id);

create unique index user_roles_role_id_user_id_uindex
	on user_roles (role_id, user_id);






