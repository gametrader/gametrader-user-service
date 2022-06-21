CREATE TABLE app_user
(
    id        BIGINT       NOT NULL,
    username  VARCHAR(255) NOT NULL,
    password  VARCHAR(255) NOT NULL,
    email     VARCHAR(255) NOT NULL,
    birthdate date         NOT NULL,
    gender    VARCHAR(255) NOT NULL,
    CONSTRAINT pk_app_user PRIMARY KEY (id)
);

CREATE TABLE user_role
(
    id   BIGINT       NOT NULL,
    role VARCHAR(255) NOT NULL,
    CONSTRAINT pk_user_role PRIMARY KEY (id)
);

CREATE TABLE user_roles
(
    role_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    CONSTRAINT pk_user_roles PRIMARY KEY (role_id, user_id)
);

ALTER TABLE app_user
    ADD CONSTRAINT uc_app_user_email UNIQUE (email);

ALTER TABLE app_user
    ADD CONSTRAINT uc_app_user_username UNIQUE (username);

ALTER TABLE user_roles
    ADD CONSTRAINT fk_userol_on_app_user FOREIGN KEY (user_id) REFERENCES app_user (id);

ALTER TABLE user_roles
    ADD CONSTRAINT fk_userol_on_user_role FOREIGN KEY (role_id) REFERENCES user_role (id);


INSERT INTO user_role (id , role)
VALUES (1,'USER');

INSERT INTO user_role (id , role)
VALUES (2,'ADMIN');