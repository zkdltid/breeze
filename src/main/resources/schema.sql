-- Drop table

DROP TABLE IF EXISTS public.users;

DROP TABLE IF EXISTS public.users;
CREATE TABLE public.users (
	id BIGSERIAL PRIMARY KEY NOT NULL,
	username varchar(20) NULL UNIQUE,
	email varchar(50) NULL UNIQUE,
	"password" varchar(120) NULL
);

-- DROP TABLE public.roles;
DROP TABLE IF EXISTS public.roles;

CREATE TABLE public.roles (
	id BIGSERIAL PRIMARY KEY NOT NULL,
	"name" varchar(20) NULL UNIQUE
);

---- DROP TABLE public.user_roles;
DROP TABLE IF EXISTS public.user_roles;

CREATE TABLE public.user_roles (
	user_id int8 NOT NULL,
	role_id int4 NOT NULL,
	FOREIGN KEY (user_id) REFERENCES users(id),
	FOREIGN KEY (role_id) REFERENCES roles(id),
	CONSTRAINT user_roles_pkey PRIMARY KEY (user_id, role_id)
);

-- DROP TABLE public.product;
DROP TABLE IF EXISTS public.product;

CREATE TABLE public.product (
	id BIGSERIAL PRIMARY KEY NOT NULL,
	category varchar(120) NULL,
	"desc" varchar(120) NULL,
	image varchar(120) NULL,
	"name" varchar(20) NULL,
	price int4 NOT NULL,
	stock int4 NOT NULL
);

-- DROP TABLE public.orders;

DROP TABLE IF EXISTS public.orders;

CREATE TABLE public.orders (
	id BIGSERIAL PRIMARY KEY NOT NULL,
	product_id int8 NULL,
	user_id int8 NULL,
	FOREIGN KEY (user_id) REFERENCES users(id),
	FOREIGN KEY (product_id) REFERENCES product(id)
);

