
INSERT INTO item VALUES(null, 'Descripcion 1', null, null);
INSERT INTO item VALUES(null, 'Descripcion 2', null, null);
INSERT INTO item VALUES(null, 'Descripcion 3', null, null);

INSERT INTO usuario (usuario, cantidad_intentos, contrasenia, fecha_espera, nombre)
VALUES('dai', 0, '1234567',null,'Daiana');
INSERT INTO mensaje (asunto, cuerpo, usuario_id)
VALUES ('Resultado Validación', 'La validación fue exitosa', 1);
INSERT INTO mensaje (asunto, cuerpo, usuario_id)
VALUES ('Resultado Validación', 'La validación no fue exitosa', 1);
