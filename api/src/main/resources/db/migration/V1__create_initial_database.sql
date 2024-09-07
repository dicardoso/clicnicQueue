-- Criação da tabela de tipos de atendimento
CREATE TABLE service_types (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    priority INT NOT NULL,
    is_active BOOLEAN NOT NULL
);

-- Criação da tabela de guichês
CREATE TABLE counters (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    location VARCHAR(255),
    is_active BOOLEAN NOT NULL
);

-- Criação da tabela de tipos de eventos
CREATE TABLE event_types (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    is_active BOOLEAN NOT NULL
);

-- Criação da tabela de status
CREATE TABLE statuses (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    is_active BOOLEAN NOT NULL
);

-- Criação da tabela de senhas
CREATE TABLE tickets (
    id BIGSERIAL PRIMARY KEY,
    number INT NOT NULL,
    service_type_id BIGINT NOT NULL REFERENCES service_types(id),
    issued_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    called_at TIMESTAMP,
    status_id BIGINT NOT NULL REFERENCES statuses(id),
    counter_id BIGINT REFERENCES counters(id)
);

-- Criação da tabela de usuários (atendentes)
CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    counter_id BIGINT REFERENCES counters(id),
    is_active BOOLEAN NOT NULL
);

-- Criação da tabela de eventos de senhas (tickets)
CREATE TABLE ticket_events (
    id BIGSERIAL PRIMARY KEY,
    ticket_id BIGINT NOT NULL REFERENCES tickets(id) ON DELETE CASCADE,
    event_type_id BIGINT NOT NULL REFERENCES event_types(id),
    event_timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    counter_id BIGINT REFERENCES counters(id), -- Guichê associado ao evento
    user_id BIGINT REFERENCES users(id) -- Atendente responsável pelo evento
);

-- SEEDS
-- Inserindo tipos de atendimento
INSERT INTO service_types (name, priority, is_active)
VALUES
    ('Atendimento Normal', 1, TRUE),
    ('Atendimento Prioritário', 2, TRUE),
    ('Atendimento Emergencial', 3, TRUE);

-- Inserindo guichês
INSERT INTO counters (name, location, is_active)
VALUES
    ('Guichê 1', 'Recepção 1', TRUE),
    ('Guichê 2', 'Recepção 2', TRUE),
    ('Guichê 3', 'Recepção 3', TRUE);

-- Inserindo tipos de eventos
INSERT INTO event_types (name, is_active)
VALUES
    ('CHAMADO', TRUE),
    ('ATENDIDO', TRUE),
    ('CANCELADO', TRUE);

-- Inserindo status
INSERT INTO statuses (name, is_active)
VALUES
    ('PENDING', TRUE),
    ('CALLED', TRUE),
    ('COMPLETED', TRUE);

-- Inserindo um atendente de exemplo
INSERT INTO users (name, username, password, counter_id, is_active)
VALUES
    ('Atendente 1', 'atendente1', 'senhaSegura1', 1, TRUE);
