create table customer (
                          id int primary key generated by default as identity,
                          email varchar not null unique,
                          registration_date timestamp not null ,
                          is_active boolean not null
);