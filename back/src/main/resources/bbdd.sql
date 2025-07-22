-- Table: users
CREATE TABLE users (
    user_id SERIAL PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(150) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    user_password VARCHAR(255) NOT NULL
);

-- Table: schedules
CREATE TABLE schedules (
    id_schedule SERIAL PRIMARY KEY,
    description VARCHAR(100) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    daily_hours DECIMAL(4,2) NOT NULL,
    enabled BOOLEAN DEFAULT TRUE
);

-- Table: checkins
CREATE TABLE checkins (
    check_id SERIAL PRIMARY KEY,
    user_id INTEGER REFERENCES users(id),
    type VARCHAR(10) CHECK (type IN ('entry', 'exit')),
    timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    is_inside BOOLEAN NOT NULL
);

-- Insert users
INSERT INTO users (first_name, last_name, email,user_password ) VALUES
('Ana', 'García López', 'ana.garcia@example.com', 'hash1'),
('Luis', 'Martínez Ruiz', 'luis.martinez@example.com', 'hash2'),
('María', 'Sánchez Díaz', 'maria.sanchez@example.com', 'hash3'),
('Carlos', 'Fernández Gómez', 'carlos.fernandez@example.com', 'hash4'),
('Laura', 'Jiménez Pérez', 'laura.jimenez@example.com', 'hash5'),
('Javier', 'Moreno Torres', 'javier.moreno@example.com', 'hash6'),
('Elena', 'Romero Vargas', 'elena.romero@example.com', 'hash7'),
('David', 'Navarro Castro', 'david.navarro@example.com', 'hash8'),
('Sara', 'Domínguez León', 'sara.dominguez@example.com', 'hash9'),
('Pablo', 'Ortega Molina', 'pablo.ortega@example.com', 'hash10');

-- Insert schedules
INSERT INTO schedules (description, start_date, end_date, daily_hours, enabled) VALUES
('Summer intensive schedule (8:00 - 15:00)', '2025-07-01', '2025-08-31', 7.0, TRUE),
('Winter schedule (8:30 - 18:00)', '2025-01-01', '2025-06-30', 8.5, TRUE);