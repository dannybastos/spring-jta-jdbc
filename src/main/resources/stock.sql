create table stock (
  id  integer identity primary key,
  product_id integer,
  stock_qty integer
);

insert into stock (product_id, stock_qty) values (1010, 10);
insert into stock (product_id, stock_qty) values (1020, 20);
