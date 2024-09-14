CREATE SEQUENCE  IF NOT EXISTS notification_primary_sequence START WITH 10000 INCREMENT BY 1;

CREATE TABLE notifications (
  id BIGINT NOT NULL,
   is_processed BOOLEAN NOT NULL,
   processed_date TIMESTAMP WITHOUT TIME ZONE NOT NULL,
   reservation_event_id UUID NOT NULL,
   type VARCHAR(255) NOT NULL,
   date_created TIMESTAMP WITHOUT TIME ZONE NOT NULL,
   last_updated TIMESTAMP WITHOUT TIME ZONE NOT NULL,
   CONSTRAINT pk_notifications PRIMARY KEY (id)
);