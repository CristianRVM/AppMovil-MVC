-- Crear Base de Datos
CREATE DATABASE IF NOT EXISTS db_app_movil;
-- Seleccionar la Base de Datos
USE db_app_movil;

-- Crear Tabla con los campos [id, nombre, precio, cantidad]
CREATE TABLE IF NOT EXISTS usuarios (
    idUsuario INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    contrasenia VARCHAR(255) NOT NULL,
    rol VARCHAR(50) NOT NULL,
    estado BOOLEAN DEFAULT TRUE
);

-- Mostrar Datos de Tabla Productos
SELECT * FROM usuarios;
