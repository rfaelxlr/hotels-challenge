CREATE SEQUENCE  IF NOT EXISTS user_sequence START WITH 10000 INCREMENT BY 1;

CREATE TABLE users (
  id BIGINT NOT NULL,
   name VARCHAR(255) NOT NULL,
   email VARCHAR(255) NOT NULL,
   ddd VARCHAR(3) NOT NULL,
   phone_number VARCHAR(9) NOT NULL,
   external_id UUID NOT NULL,
   date_created TIMESTAMP WITHOUT TIME ZONE NOT NULL,
   last_updated TIMESTAMP WITHOUT TIME ZONE NOT NULL,
   CONSTRAINT pk_users PRIMARY KEY (id)
);

ALTER TABLE users ADD CONSTRAINT uc_users_email UNIQUE (email);

ALTER TABLE users ADD CONSTRAINT uc_users_external UNIQUE (external_id);

CREATE SEQUENCE  IF NOT EXISTS room_sequence START WITH 10000 INCREMENT BY 1;

CREATE TABLE rooms (
  id BIGINT NOT NULL,
   quantity_available INTEGER NOT NULL,
   capacity INTEGER NOT NULL,
   price DECIMAL(10, 2) NOT NULL,
   external_id UUID NOT NULL,
   hotel_id BIGINT NOT NULL,
   date_created TIMESTAMP WITHOUT TIME ZONE NOT NULL,
   last_updated TIMESTAMP WITHOUT TIME ZONE NOT NULL,
   CONSTRAINT pk_rooms PRIMARY KEY (id)
);

ALTER TABLE rooms ADD CONSTRAINT uc_rooms_external UNIQUE (external_id);

CREATE SEQUENCE  IF NOT EXISTS reservation_sequence START WITH 10000 INCREMENT BY 1;

CREATE TABLE reservations (
  id BIGINT NOT NULL,
   status VARCHAR(255) NOT NULL,
   check_in_date date NOT NULL,
   check_out_date date NOT NULL,
   guests_number INTEGER NOT NULL,
   total_price DECIMAL(10, 2) NOT NULL,
   user_id BIGINT,
   room_id BIGINT,
   date_created TIMESTAMP WITHOUT TIME ZONE NOT NULL,
   last_updated TIMESTAMP WITHOUT TIME ZONE NOT NULL,
   CONSTRAINT pk_reservations PRIMARY KEY (id)
);

ALTER TABLE reservations ADD CONSTRAINT FK_RESERVATIONS_ON_ROOM FOREIGN KEY (room_id) REFERENCES rooms (id);

ALTER TABLE reservations ADD CONSTRAINT FK_RESERVATIONS_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);

CREATE SEQUENCE  IF NOT EXISTS payment_sequence START WITH 10000 INCREMENT BY 1;

CREATE TABLE payments (
  id BIGINT NOT NULL,
   type VARCHAR(255) NOT NULL,
   value DECIMAL(10, 2),
   installment_number INTEGER NOT NULL,
   reservation_id BIGINT,
   date_created TIMESTAMP WITHOUT TIME ZONE NOT NULL,
   last_updated TIMESTAMP WITHOUT TIME ZONE NOT NULL,
   CONSTRAINT pk_payments PRIMARY KEY (id)
);

ALTER TABLE payments ADD CONSTRAINT FK_PAYMENTS_ON_RESERVATION FOREIGN KEY (reservation_id) REFERENCES reservations (id);

CREATE SEQUENCE  IF NOT EXISTS reservation_event_sequence START WITH 10000 INCREMENT BY 1;

CREATE TABLE reservation_events (
  id BIGINT NOT NULL,
   status VARCHAR(255) NOT NULL,
   user_id BIGINT,
   reservation_id BIGINT,
   external_id UUID NOT NULL,
   date_created TIMESTAMP WITHOUT TIME ZONE NOT NULL,
   last_updated TIMESTAMP WITHOUT TIME ZONE NOT NULL,
   CONSTRAINT pk_reservationevents PRIMARY KEY (id)
);

ALTER TABLE reservation_events ADD CONSTRAINT uc_reservationevents_external UNIQUE (external_id);

ALTER TABLE reservation_events ADD CONSTRAINT FK_RESERVATIONEVENTS_ON_RESERVATION FOREIGN KEY (reservation_id) REFERENCES reservations (id);

ALTER TABLE reservation_events ADD CONSTRAINT FK_RESERVATIONEVENTS_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);