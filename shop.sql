--
-- PostgreSQL database dump
--

-- Dumped from database version 14.5
-- Dumped by pg_dump version 14.5

-- Started on 2022-12-11 17:21:50

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 212 (class 1259 OID 24982)
-- Name: category; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.category (
    id integer NOT NULL,
    name character varying(255)
);


ALTER TABLE public.category OWNER TO postgres;

--
-- TOC entry 211 (class 1259 OID 24981)
-- Name: category_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.category ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.category_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 214 (class 1259 OID 24988)
-- Name: image; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.image (
    id integer NOT NULL,
    file_name character varying(255),
    product_id integer NOT NULL
);


ALTER TABLE public.image OWNER TO postgres;

--
-- TOC entry 213 (class 1259 OID 24987)
-- Name: image_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.image ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.image_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 220 (class 1259 OID 25044)
-- Name: orders; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.orders (
    id integer NOT NULL,
    count integer NOT NULL,
    date_time timestamp without time zone,
    number character varying(255),
    price real NOT NULL,
    status integer,
    person_id integer NOT NULL,
    product_id integer NOT NULL
);


ALTER TABLE public.orders OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 25043)
-- Name: orders_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.orders ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.orders_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 210 (class 1259 OID 24946)
-- Name: person; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.person (
    id integer NOT NULL,
    login character varying(20),
    password character varying(255),
    role character varying(255)
);


ALTER TABLE public.person OWNER TO postgres;

--
-- TOC entry 209 (class 1259 OID 24945)
-- Name: person_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.person ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.person_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 216 (class 1259 OID 24994)
-- Name: product; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.product (
    id integer NOT NULL,
    date_time_of_create timestamp without time zone,
    description text NOT NULL,
    price real NOT NULL,
    seller character varying(255) NOT NULL,
    title text NOT NULL,
    warehouse character varying(255) NOT NULL,
    category_id integer NOT NULL,
    CONSTRAINT product_price_check CHECK ((price >= (1)::double precision))
);


ALTER TABLE public.product OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 25028)
-- Name: product_cart; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.product_cart (
    id integer NOT NULL,
    person_id integer,
    product_id integer
);


ALTER TABLE public.product_cart OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 25027)
-- Name: product_cart_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.product_cart ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.product_cart_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 215 (class 1259 OID 24993)
-- Name: product_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.product ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.product_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 3352 (class 0 OID 24982)
-- Dependencies: 212
-- Data for Name: category; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.category (id, name) VALUES (1, 'Свечи');
INSERT INTO public.category (id, name) VALUES (2, 'Материалы');
INSERT INTO public.category (id, name) VALUES (3, 'Аксессуары');


--
-- TOC entry 3354 (class 0 OID 24988)
-- Dependencies: 214
-- Data for Name: image; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.image (id, file_name, product_id) VALUES (1, 'e974ea4d-43ce-4512-8c1e-68d0e8f60ff0.eae5b0ab474a622bb0e5281863842d63.jpg', 1);
INSERT INTO public.image (id, file_name, product_id) VALUES (2, 'c2db5ea4-6093-4109-8149-932be11dbfd7.700-nw (1).jpg', 1);
INSERT INTO public.image (id, file_name, product_id) VALUES (3, 'c07d5478-8af9-43fb-8317-62bfeee1a53f.700-nw (2).jpg', 1);
INSERT INTO public.image (id, file_name, product_id) VALUES (4, '65c65e51-6f5d-4f93-99a1-40167a19bd4c.700-nw (3).jpg', 1);
INSERT INTO public.image (id, file_name, product_id) VALUES (5, '9296eecb-364c-48e0-a170-191509420828.700-nw.jpg', 1);
INSERT INTO public.image (id, file_name, product_id) VALUES (6, '730b6efd-4184-42c2-939f-9e068c93422f.Снимок экрана 2022-12-08 162142.jpg', 2);
INSERT INTO public.image (id, file_name, product_id) VALUES (7, 'de400d02-f78e-444d-9cc4-e037e844238a.Снимок экрана 2022-12-08 162026.jpg', 2);
INSERT INTO public.image (id, file_name, product_id) VALUES (8, '17fd4c78-19e1-4e91-8e7a-ade5ba8e6523.Снимок экрана 2022-12-08 162046.jpg', 2);
INSERT INTO public.image (id, file_name, product_id) VALUES (9, '71ec229b-7efd-46c5-aa87-8af3e264f130.Снимок экрана 2022-12-08 162102.jpg', 2);
INSERT INTO public.image (id, file_name, product_id) VALUES (10, 'd76361bd-c9eb-442a-91e8-84c9bccf290e.Снимок экрана 2022-12-08 162117.jpg', 2);
INSERT INTO public.image (id, file_name, product_id) VALUES (16, 'c4512282-b825-4a9c-aeb6-a3deefa0da48.6eb5850501704f5af0d654de8cbi--suveniry-i-podarki-svecha-soevyj-vosk.jpg', 4);
INSERT INTO public.image (id, file_name, product_id) VALUES (17, '4666eefc-823b-43ca-94d8-7d577e6f412f.c0c8f511302a5d8802e93c557c5y--suveniry-i-podarki-svecha-soevyj-vosk.jpg', 4);
INSERT INTO public.image (id, file_name, product_id) VALUES (18, 'c1d0d692-de28-45cf-8c65-3eab7906da2a.4d0d6ba5ed91849ba46f9e186f5o--suveniry-i-podarki-svecha-soevyj-vosk.jpg', 4);
INSERT INTO public.image (id, file_name, product_id) VALUES (19, '10dd7f97-7314-4fc0-aa71-92a41f75322a.70365c0c4f45b36a1643eefb7deb--suveniry-i-podarki-svecha-soevyj-vosk.jpg', 4);
INSERT INTO public.image (id, file_name, product_id) VALUES (20, '73f46d9b-df2f-4032-8c85-0029aa1d5a6c.89df6552dac5442934b6bfb63d3b.jpeg', 5);
INSERT INTO public.image (id, file_name, product_id) VALUES (21, 'cdf20502-15ad-4ed1-86c6-525b39b89da7.2273.970.jpg', 6);
INSERT INTO public.image (id, file_name, product_id) VALUES (22, '053d5ad6-9ed6-4b8b-bfbf-d2c5598af85f.177781820.jfif', 7);
INSERT INTO public.image (id, file_name, product_id) VALUES (23, 'c8c63e81-82d2-4a0f-b2a6-0db6b5dffb90.53749.750.jpg', 8);
INSERT INTO public.image (id, file_name, product_id) VALUES (24, '1745d5a2-1b5e-417b-902e-e874e43fdc0c.700-nw.jpg', 10);
INSERT INTO public.image (id, file_name, product_id) VALUES (25, '90a1f783-ceb6-436b-b4f8-423917055d57.241459528-zagigalka-dlya-svechejnesortirovanoe-800x800.jpg', 11);


--
-- TOC entry 3360 (class 0 OID 25044)
-- Dependencies: 220
-- Data for Name: orders; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.orders (id, count, date_time, number, price, status, person_id, product_id) VALUES (9, 1, '2022-12-10 02:40:23.889237', '026a031e-571c-4596-9cd3-8bcd61452641', 320, 1, 2, 6);
INSERT INTO public.orders (id, count, date_time, number, price, status, person_id, product_id) VALUES (10, 1, '2022-12-10 23:25:37.693079', 'bc6e3650-441c-463d-9057-1d2aee45c475', 400, 1, 2, 10);
INSERT INTO public.orders (id, count, date_time, number, price, status, person_id, product_id) VALUES (5, 1, '2022-12-09 21:13:39.228739', '799279c8-b0f1-4ac1-af62-bedeff1409a7', 60, 0, 2, 1);


--
-- TOC entry 3350 (class 0 OID 24946)
-- Dependencies: 210
-- Data for Name: person; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.person (id, login, password, role) VALUES (2, 'user_test0', '$2a$10$XhHyDP0f6J6VDwvbaoDF5.bjITq4PfGRev3GmTNShrzhfmksGn8nO', 'ROLE_USER');
INSERT INTO public.person (id, login, password, role) VALUES (1, 'admin', '$2a$10$Tm2wOz7RVG6ZXS7w8hF.wugg3OettsdVU7Uinl5yyhYCIm/x3DdY.', 'ROLE_ADMIN');


--
-- TOC entry 3356 (class 0 OID 24994)
-- Dependencies: 216
-- Data for Name: product; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.product (id, date_time_of_create, description, price, seller, title, warehouse, category_id) VALUES (1, '2022-12-08 19:50:43.656833', 'Свеча полностью прокрашенная глянцевая формовая в виде конуса.
Подходит под любой стандартный подсвечник, диаметр по юбочке 22мм.
Время горения 5-7 часов.
Идеально подходит для кафе, ресторанов.
поставляется по 10шт и 100шт в коробках.', 60, 'Свечмаг', 'Свеча парафиновая античная', 'Внуково', 1);
INSERT INTO public.product (id, date_time_of_create, description, price, seller, title, warehouse, category_id) VALUES (2, '2022-12-08 19:55:02.797967', 'Свечи восковые часовые цветные, изготовлены из натурального пасечного воска, немецкого фитиля из 100% хлопка и красителя. Одним из показателей высокого качества воска, является то, что на восковых свечах при хранении образуется белый налет, который легко убирается рукой или тряпочкой. Именно это свойство отличает свечи из настоящего пасечного воска от свечей из парафина или синтетического воска. Восковые часовые свечи обладают естественным тонким медовым ароматом. Цвет свечи является показателем натуральности воска и может варьировать от чистого желтого до желтого с легким коричневым или оливковым оттенком. Обычно ярко-желтые свечи не являются натуральными восковыми, а содержат в себе нефте-добавки и краситель. Восковую часовую свечу можно прикрепить к любой поверхности, предварительно подогрев кончик свечи руками, однако лучше использовать подсвечник для сохранения поверхности от брызг и капель воска.', 10, 'Свечная фабрика', 'Свеча восковая часовая', 'Основной', 1);
INSERT INTO public.product (id, date_time_of_create, description, price, seller, title, warehouse, category_id) VALUES (4, '2022-12-08 20:18:03.519555', 'Свечи сделаны из 100% соевого растительного воска с деревянным фитилем. В качестве ароматизатора использованы специальные свечные немецкие ароматизаторы предназначенные для свечей из соевого воска.', 250, 'Ведьмин дом', 'Свечи соевые контейнерные', 'Основной', 1);
INSERT INTO public.product (id, date_time_of_create, description, price, seller, title, warehouse, category_id) VALUES (5, '2022-12-08 20:23:19.523378', 'Натуральный колотый пчелиный воск.
Подходит для изготовления свечей, отливок, вощения и др.
Воск может содержать прополис, пергу, мерву, пчел.', 1500, 'СвечМаг', 'Натуральный воск', 'Основной', 2);
INSERT INTO public.product (id, date_time_of_create, description, price, seller, title, warehouse, category_id) VALUES (6, '2022-12-08 20:27:45.53931', 'Парафин пластовой 1кг.
Прекрасно подходит для изготовления резных свечей, отливки свечей в формы итд.
Производство: Россия, Ярославль.', 320, 'Завод НПТ', 'Парафин', 'Ярославль', 2);
INSERT INTO public.product (id, date_time_of_create, description, price, seller, title, warehouse, category_id) VALUES (7, '2022-12-08 20:30:16.381936', 'Фитиль подходит для тонких восковых и парафиновых свечей от 5мм до 10мм диаметром.
Данные рекомендации являются примерными, под каждый вид материала и тип свечи фитиль подбирается опытным путем.
Примерный вес бобины 1кг (+- 100г)
Состав: 100% хлопок.', 1500, 'СвечМаг', 'Фитиль хлопковый', 'Домодедово', 2);
INSERT INTO public.product (id, date_time_of_create, description, price, seller, title, warehouse, category_id) VALUES (8, '2022-12-08 20:32:36.506758', 'Если Вы задуваете  свечи классическим способом (дунув на пламя), то это может привести к тлению фитиля, что может сказаться на его свойствах при следующем использовании. Так же при задувании образуется неприятный запах и небольшой дым.
Правильный способ тушения свечи - перекрыть фитилю доступ к кислороду. Для этого предлагаем использовать вам специальный гаситель.
А еще он непременно придаст вашим действиям больше шарма и изящности!
Стильный колпачок для безопасного и простого тушения свечи, который предотвращает появление дыма и запаха. Практичный аксессуар, который порадует любителей свечей. А так же прекрасно подойдет под любую обстановку дома, в кафе или ресторане.', 500, 'Ведьмин дом', 'Гаситель свечи', 'Основной', 3);
INSERT INTO public.product (id, date_time_of_create, description, price, seller, title, warehouse, category_id) VALUES (10, '2022-12-08 20:40:51.945848', 'Подсвечник выполненный в стиле неоклассика, станет украшением интерьера спальни, гостиной или другой комнаты. Предназначен для античной свечи, которую можно зажигать в тёмное время суток, создавая тёплую и уютную атмосферу. Подсвечник прозрачный, изготовлен из стекла. Может использоваться в качестве презента. Высота изделия — 25 см.', 400, 'Ведьмин дом', 'Подсвечник для античной свечи', 'Основной', 3);
INSERT INTO public.product (id, date_time_of_create, description, price, seller, title, warehouse, category_id) VALUES (11, '2022-12-08 20:44:06.791598', 'Какой бы ни была форма банки, поджечь фитиль больше не проблема. Даже если свеча почти закончилась, и он очень глубоко.
Теперь вам не придется искать длинные спички, чтобы зажечь свечи в банках и стаканах. Да и запах горелой серы мало кому по вкусу.', 275, 'СвечМаг', 'Зажигалка', 'Внуково', 3);


--
-- TOC entry 3358 (class 0 OID 25028)
-- Dependencies: 218
-- Data for Name: product_cart; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3366 (class 0 OID 0)
-- Dependencies: 211
-- Name: category_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.category_id_seq', 3, true);


--
-- TOC entry 3367 (class 0 OID 0)
-- Dependencies: 213
-- Name: image_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.image_id_seq', 25, true);


--
-- TOC entry 3368 (class 0 OID 0)
-- Dependencies: 219
-- Name: orders_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.orders_id_seq', 10, true);


--
-- TOC entry 3369 (class 0 OID 0)
-- Dependencies: 209
-- Name: person_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.person_id_seq', 2, true);


--
-- TOC entry 3370 (class 0 OID 0)
-- Dependencies: 217
-- Name: product_cart_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.product_cart_id_seq', 24, true);


--
-- TOC entry 3371 (class 0 OID 0)
-- Dependencies: 215
-- Name: product_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.product_id_seq', 11, true);


--
-- TOC entry 3193 (class 2606 OID 24986)
-- Name: category category_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.category
    ADD CONSTRAINT category_pkey PRIMARY KEY (id);


--
-- TOC entry 3195 (class 2606 OID 24992)
-- Name: image image_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.image
    ADD CONSTRAINT image_pkey PRIMARY KEY (id);


--
-- TOC entry 3203 (class 2606 OID 25048)
-- Name: orders orders_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT orders_pkey PRIMARY KEY (id);


--
-- TOC entry 3191 (class 2606 OID 24952)
-- Name: person person_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.person
    ADD CONSTRAINT person_pkey PRIMARY KEY (id);


--
-- TOC entry 3201 (class 2606 OID 25032)
-- Name: product_cart product_cart_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product_cart
    ADD CONSTRAINT product_cart_pkey PRIMARY KEY (id);


--
-- TOC entry 3197 (class 2606 OID 25001)
-- Name: product product_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT product_pkey PRIMARY KEY (id);


--
-- TOC entry 3199 (class 2606 OID 25003)
-- Name: product uk_qka6vxqdy1dprtqnx9trdd47c; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT uk_qka6vxqdy1dprtqnx9trdd47c UNIQUE (title);


--
-- TOC entry 3208 (class 2606 OID 25049)
-- Name: orders fk1b0m4muwx1t377w9if3w6wwqn; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT fk1b0m4muwx1t377w9if3w6wwqn FOREIGN KEY (person_id) REFERENCES public.person(id);


--
-- TOC entry 3205 (class 2606 OID 25009)
-- Name: product fk1mtsbur82frn64de7balymq9s; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT fk1mtsbur82frn64de7balymq9s FOREIGN KEY (category_id) REFERENCES public.category(id);


--
-- TOC entry 3209 (class 2606 OID 25054)
-- Name: orders fk787ibr3guwp6xobrpbofnv7le; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT fk787ibr3guwp6xobrpbofnv7le FOREIGN KEY (product_id) REFERENCES public.product(id);


--
-- TOC entry 3204 (class 2606 OID 25004)
-- Name: image fkgpextbyee3uk9u6o2381m7ft1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.image
    ADD CONSTRAINT fkgpextbyee3uk9u6o2381m7ft1 FOREIGN KEY (product_id) REFERENCES public.product(id);


--
-- TOC entry 3207 (class 2606 OID 25038)
-- Name: product_cart fkhpnrxdy3jhujameyod08ilvvw; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product_cart
    ADD CONSTRAINT fkhpnrxdy3jhujameyod08ilvvw FOREIGN KEY (product_id) REFERENCES public.product(id);


--
-- TOC entry 3206 (class 2606 OID 25033)
-- Name: product_cart fksgnkc1ko2i1o9yr2p63ysq3rn; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product_cart
    ADD CONSTRAINT fksgnkc1ko2i1o9yr2p63ysq3rn FOREIGN KEY (person_id) REFERENCES public.person(id);


-- Completed on 2022-12-11 17:21:51

--
-- PostgreSQL database dump complete
--
