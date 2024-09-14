CREATE INDEX idx_addresses_state ON addresses (state);
CREATE INDEX idx_addresses_city ON addresses (city);
CREATE INDEX idx_addresses_state_city ON addresses (state,city);


CREATE INDEX idx_rooms_quantity_available ON rooms (quantity_available);
CREATE INDEX idx_rooms_capacity ON rooms (capacity);

CREATE INDEX idx_hotels_address_id ON hotels (address_id);
