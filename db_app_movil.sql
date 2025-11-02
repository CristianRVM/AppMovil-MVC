USE db_app_movil;

/*
DROP TABLE EstadoDeAnimo;
DROP TABLE Emocion;
DROP TABLE Usuario;
*/

CREATE TABLE IF NOT EXISTS Usuario(
	idUsuario INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    correo VARCHAR(50) NOT NULL UNIQUE,
    contrasenia VARCHAR(255) NOT NULL,
    rol VARCHAR(50) NOT NULL,
    estado BOOLEAN DEFAULT TRUE
);

CREATE TABLE IF NOT EXISTS Emocion (
  idEmocion INT AUTO_INCREMENT PRIMARY KEY,
  code   VARCHAR(30) NOT NULL UNIQUE,
  nombre VARCHAR(50) NOT NULL,
  emoji  VARCHAR(16) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Registros por usuario
CREATE TABLE IF NOT EXISTS EstadoDeAnimo (
  idEstadoDeAnimo BIGINT AUTO_INCREMENT PRIMARY KEY,
  idUsuario INT NOT NULL,
  idEmocion INT NOT NULL,
  texto VARCHAR(200) NOT NULL,
  creadoEn DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT fk_estado_usuario  FOREIGN KEY (idUsuario)  REFERENCES Usuario(idUsuario)
    ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT fk_estado_emocion  FOREIGN KEY (idEmocion)  REFERENCES Emocion(idEmocion)
    ON DELETE RESTRICT ON UPDATE CASCADE,
  INDEX idx_estado_usuario_fecha (idUsuario, creadoEn),
  INDEX idx_estado_emocion (idEmocion)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS Consejo (
  idConsejo BIGINT AUTO_INCREMENT PRIMARY KEY,
  contenido VARCHAR(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO Consejo (contenido) VALUES
('Da un paseo corto al aire libre y observa tu entorno con curiosidad y gratitud.'),
('Organiza tus tareas por prioridad y permítete avanzar paso a paso sin presionarte.'),
('Comparte cómo te sientes con alguien de confianza y escucha también su perspectiva.'),
('Reserva un momento para estirarte suavemente y agradecer lo que tu cuerpo permite.'),
('Practica tres respiraciones profundas contando hasta cuatro y suelta el aire lentamente.'),
('Escribe en una nota tres cosas pequeñas que te hicieron sonreír durante el día.'),
('Prepara una bebida caliente, bébela despacio y concéntrate en su aroma y temperatura.'),
('Pon tu canción favorita, canta o mueve el cuerpo y deja que la energía fluya libremente.'),
('Haz una pausa digital de quince minutos y dedica ese tiempo a cuidar tu espacio personal.'),
('Abraza tu ritmo actual, reconoce tus logros recientes y celebra tu esfuerzo constante.'),
('Elige una afirmación amable, repítela frente al espejo y mírate con respeto genuino.'),
('Dedica cinco minutos a ordenar tu rincón favorito y hazlo acogedor para tu descanso.'),
('Observa tu respiración antes de dormir y agradece lo aprendido en la jornada de hoy.'),
('Sustituye un pensamiento autocrítico por uno compasivo que impulse tu bienestar.'),
('Planifica una actividad que te entusiasme para mañana y regálate esa motivación extra.');

-- 12 emociones (mapea tu UI 1:1)
INSERT INTO Emocion (code,nombre,emoji) VALUES
('muy_feliz','Muy feliz','😄'),
('feliz','Feliz','😊'),
('contento','Contento','🙂'),
('neutral','Neutral','😐'),
('desanimado','Desanimado','😔'),
('triste','Triste','😢'),
('enojado','Enojado','😡'),
('frustrado','Frustrado','😤'),
('somnoliento','Somnoliento','😴'),
('enfermo','Enfermo','🤒'),
('sorprendido','Sorprendido','😱'),
('fiesta','Fiesta','🥳');


SHOW TABLES;
SELECT * FROM Usuario;
SELECT * FROM Emocion;
SELECT * FROM EstadoDeAnimo;
SELECT * FROM Consejo;
