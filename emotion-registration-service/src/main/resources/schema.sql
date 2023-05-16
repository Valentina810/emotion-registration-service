CREATE TABLE IF NOT EXISTS users
(
    id_user         bigint                 NOT NULL GENERATED ALWAYS AS IDENTITY,
    name            character varying(250) NOT NULL,
    email           character varying(250) NOT NULL,
    hashed_password varchar(255)           NOT NULL,
    salt            varchar(1024)          NOT NULL,
    CONSTRAINT users_pkey PRIMARY KEY (id_user),
    CONSTRAINT uq_users_email UNIQUE (email)
);

CREATE TABLE IF NOT EXISTS events
(
    id_event    bigint                      NOT NULL GENERATED ALWAYS AS IDENTITY,
    user_id     bigint                      NOT NULL NOT NULL,
    emotion_id  bigint                      NOT NULL NOT NULL,
    cause_id    bigint                      NOT NULL NOT NULL,
    description character varying(5000),
    created_on  timestamp without time zone,
    start_date  timestamp without time zone NOT NULL,
    end_date    timestamp without time zone NOT NULL,
    CONSTRAINT events_pkey PRIMARY KEY (id_event),
    CONSTRAINT user_id_id_user FOREIGN KEY (user_id)
        REFERENCES users (id_user),
    CONSTRAINT emotion_id_id_emotion FOREIGN KEY (emotion_id)
        REFERENCES emotions (id_emotion),
    CONSTRAINT cause_id_id_cause FOREIGN KEY (cause_id)
        REFERENCES causes (id_cause)
);

CREATE TABLE IF NOT EXISTS emotions
(
    id_emotion bigint                 NOT NULL GENERATED ALWAYS AS IDENTITY,
    name       character varying(250) NOT NULL,
    CONSTRAINT emotions_pkey PRIMARY KEY (id_emotion),
    CONSTRAINT uq_emotion_name UNIQUE (name)
);

CREATE TABLE IF NOT EXISTS causes
(
    id_cause bigint                 NOT NULL GENERATED ALWAYS AS IDENTITY,
    name     character varying(250) NOT NULL,
    CONSTRAINT causes_pkey PRIMARY KEY (id_cause),
    CONSTRAINT uq_causes_name UNIQUE (name)
);

CREATE TABLE IF NOT EXISTS tablets
(
    id_tablet bigint                 NOT NULL GENERATED ALWAYS AS IDENTITY,
    name      character varying(250) NOT NULL,
    CONSTRAINT tablets_pkey PRIMARY KEY (id_tablet),
    CONSTRAINT uq_tablets_name UNIQUE (name)
);