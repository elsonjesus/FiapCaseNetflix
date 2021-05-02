--
-- PostgreSQL database dump
--

-- Dumped from database version 13.2 (Debian 13.2-1.pgdg100+1)
-- Dumped by pg_dump version 13.2

-- Started on 2021-04-21 14:43:54 UTC

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
-- TOC entry 200 (class 1259 OID 16384)
-- Name: category; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.category (
    id numeric NOT NULL,
    name text NOT NULL
);


ALTER TABLE public.category OWNER TO postgres;

--
-- TOC entry 201 (class 1259 OID 16390)
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.hibernate_sequence OWNER TO postgres;

--
-- TOC entry 202 (class 1259 OID 16392)
-- Name: movie; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.movie (
    id integer NOT NULL,
    name text,
    description text,
    categoryid integer NOT NULL,
    liked boolean,
    view boolean DEFAULT false,
    future boolean DEFAULT false
);


ALTER TABLE public.movie OWNER TO postgres;

--
-- TOC entry 203 (class 1259 OID 16400)
-- Name: movie_categoryid_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.movie_categoryid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.movie_categoryid_seq OWNER TO postgres;

--
-- TOC entry 2970 (class 0 OID 0)
-- Dependencies: 203
-- Name: movie_categoryid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.movie_categoryid_seq OWNED BY public.movie.categoryid;


--
-- TOC entry 204 (class 1259 OID 16402)
-- Name: movie_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.movie_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.movie_id_seq OWNER TO postgres;

--
-- TOC entry 2971 (class 0 OID 0)
-- Dependencies: 204
-- Name: movie_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.movie_id_seq OWNED BY public.movie.id;


--
-- TOC entry 205 (class 1259 OID 16404)
-- Name: support; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.support (
    id numeric NOT NULL,
    name text,
    description text
);


ALTER TABLE public.support OWNER TO postgres;

--
-- TOC entry 2820 (class 2604 OID 16410)
-- Name: movie id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.movie ALTER COLUMN id SET DEFAULT nextval('public.movie_id_seq'::regclass);


--
-- TOC entry 2821 (class 2604 OID 16411)
-- Name: movie categoryid; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.movie ALTER COLUMN categoryid SET DEFAULT nextval('public.movie_categoryid_seq'::regclass);


--
-- TOC entry 2959 (class 0 OID 16384)
-- Dependencies: 200
-- Data for Name: category; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.category (id, name) FROM stdin;
1	Action
2	Adventure
3	Comedy
4	Drama
5	Horror
\.


--
-- TOC entry 2961 (class 0 OID 16392)
-- Dependencies: 202
-- Data for Name: movie; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.movie (id, name, description, categoryid, liked, view, future) FROM stdin;
1	Entre Irmaos	Sam Cahill é um fuzileiro naval casado com Grace e com ela tem duas filhas: Isabelle e Maggie. Tommy é seu irmão caçula, um andarilho que deixou recentemente a prisão.	4	f	f	f
3	John Wick	John Wick é uma franquia americana de thriller de ação neo-noir criada pelo roteirista Derek Kolstad e de propriedade da Summit Entertainment.	1	f	f	f
9	Invocacao do Mal	Os investigadores paranormais Ed e Lorraine Warren trabalham para ajudar a família aterrorizada por uma entidade demoníaca em sua fazenda.	5	f	f	f
10	Annabelle	John Form acha que encontrou o presente ideal para sua esposa grávida, uma boneca vintage. Porem, a alegria do casal não dura muito. Uma noite terrível, membros de uma seita satânica invadem a casa do casal em um ataque violento.	5	f	f	f
11	A Entidade	Um autor de romances criminais encontra uma caixa com filmagens antigas de crimes horripilantes, que parecem ter sido cometidos por um assassino em série. Ao investigar, ele e sua família se tornam alvos de uma entidade sobrenatural maligna.	5	f	f	f
12	Corra	Um jovem fotógrafo descobre um segredo sombrio quando conhece os pais aparentemente amigáveis da sua namorada.	5	f	f	f
13	Sobrenatural	Josh e Renai se mudam com a família para uma casa maior. Lá, o filho Dalton sofre um estranho acidente e entra em coma. Enquanto eles tentam salvar o menino, entidades malignas atormentam a família.	5	f	f	f
14	O Grito	Depois que uma jovem mãe mata a família em sua própria casa, uma mãe solteira e um detetive tentam investigar e resolver o caso. Mais tarde, eles descobrem que a casa é amaldiçoada.	5	f	f	f
2	John Wick II	John Wick é forçado a deixar a aposentadoria mais uma vez por causa de uma promessa antiga e viaja para Roma, com o objetivo de ajudar um velho amigo a derrubar uma organização secreta, perigosa e mortal de assassinos procurados em todo o mundo.	1	f	f	f
4	O Passageiro	Em um trem, uma mulher misteriosa oferece cem mil dólares a um homem de negócios para ele ajudá-la a encontrar um passageiro escondido antes da última parada.	1	f	f	f
5	O Hobbit	O Hobbit é uma série de três filmes de fantasia épica e de aventura dirigido, coescrito e produzido por Peter Jackson e baseado em um livro The Hobbit de J. R. R. Tolkien.	2	f	f	f
6	Regaste	Em Bangladesh, o mercenário do mercado negro Tyler Rake luta para sobreviver durante a missão para resgatar o filho sequestrado de um chefe do crime internacional.	1	f	f	f
7	A Volta do Todo Poderoso	Evan Baxter consegue uma cadeira em um Congresso e se muda para Washington com a família. Mas, quando tudo estava indo bem, ele recebe uma ordem de Deus para construir uma arca como a de Noé.	2	f	f	f
8	John Wick III	O lendário John Wick luta para sair de Nova York quando um contrato de 14 milhões de dólares faz dele o alvo dos maiores assassinos do mundo.	1	f	f	f
15	Jumanji	O tempo passou para Rambo, que agora vive recluso em um rancho. Sua vida marcada por lutas violentas ficou para trás, mas deixou marcas inesquecíveis. 	2	f	f	f
16	Bem Vindo a Selva	Beck é um caçador de recompensas que não gosta de usar armas e aceita qualquer trabalho sem fazer perguntas.	2	f	f	f
17	Viagem ao Centro da Terra	Durante uma expedição na Islândia, o professor Trevor Anderson, o seu sobrinho Sean, e sua guia Hannah ficam presos em uma caverna. 	2	f	f	f
18	Amercan Pie	American Pie é uma franquia de filmes de comédia pastelão, concebidas por Adam Herz.	3	f	f	f
19	Gente Grande	A morte do treinador de basquete da infância de velhos amigos os reúne no mesmo lugar que celebraram um campeonato anos atrás.	3	f	f	f
20	Gigolo Por Acidente	Deuce Bigalow não tem nada de atraente e está sem sorte em seu trabalho de limpador de aquários. Um dia, ele arruína a casa de um gigolô e precisa de dinheiro rápido para pagar os reparos.	3	f	f	f
\.


--
-- TOC entry 2964 (class 0 OID 16404)
-- Dependencies: 205
-- Data for Name: support; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.support (id, name, description) FROM stdin;
3	Problem with the legend of the film.	Make a mistake or load a legend.
\.


--
-- TOC entry 2972 (class 0 OID 0)
-- Dependencies: 201
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.hibernate_sequence', 3, true);


--
-- TOC entry 2973 (class 0 OID 0)
-- Dependencies: 203
-- Name: movie_categoryid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.movie_categoryid_seq', 2, true);


--
-- TOC entry 2974 (class 0 OID 0)
-- Dependencies: 204
-- Name: movie_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.movie_id_seq', 1, false);


--
-- TOC entry 2823 (class 2606 OID 16413)
-- Name: category category_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.category
    ADD CONSTRAINT category_pkey PRIMARY KEY (id);


--
-- TOC entry 2825 (class 2606 OID 16415)
-- Name: movie movie_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.movie
    ADD CONSTRAINT movie_pkey PRIMARY KEY (id);


--
-- TOC entry 2827 (class 2606 OID 16417)
-- Name: support support_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.support
    ADD CONSTRAINT support_pkey PRIMARY KEY (id);


--
-- TOC entry 2828 (class 2606 OID 16418)
-- Name: movie movie_categoryid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.movie
    ADD CONSTRAINT movie_categoryid_fkey FOREIGN KEY (categoryid) REFERENCES public.category(id);


-- Completed on 2021-04-21 14:43:54 UTC

--
-- PostgreSQL database dump complete
--

