CREATE DATABASE IF NOT EXISTS los_condores
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

USE los_condores;

DROP TABLE IF EXISTS reservas;
DROP TABLE IF EXISTS canchas;

CREATE TABLE canchas (
  id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  nombre VARCHAR(100) NOT NULL,
  tipo VARCHAR(50) NOT NULL,
  estado VARCHAR(30) NOT NULL,
  precio_por_hora DOUBLE NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE reservas (
  id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  cancha_id BIGINT UNSIGNED NOT NULL,
  cliente VARCHAR(120) NOT NULL,
  fecha VARCHAR(10) NOT NULL,
  hora_inicio VARCHAR(5) NOT NULL,
  hora_fin VARCHAR(5) NOT NULL,
  PRIMARY KEY (id),
  INDEX idx_reservas_cancha (cancha_id),
  CONSTRAINT fk_reservas_cancha
    FOREIGN KEY (cancha_id) REFERENCES canchas(id)
    ON UPDATE CASCADE
    ON DELETE RESTRICT
) ENGINE=InnoDB;
