 INSERT INTO role (name) VALUES ('ROLE_USER');
 INSERT INTO role (name) VALUES ('ROLE_ADMIN');

 INSERT INTO client (id_client, name, created_by, created_date) VALUES (1, 'Banco 1', 'system', STRINGTOUTF8(CURRENT_TIMESTAMP));
 INSERT INTO client (id_client, name, created_by, created_date) VALUES (2, 'Tienda 24', 'system', STRINGTOUTF8(CURRENT_TIMESTAMP));
 INSERT INTO client (id_client, name, created_by, created_date) VALUES (3, 'Hotel 5', 'system', STRINGTOUTF8(CURRENT_TIMESTAMP));
 
 INSERT INTO business_unit (id_business_unit, name, id_client, created_by, created_date) VALUES (1, 'Sucursal 1', 1, 'system', STRINGTOUTF8(CURRENT_TIMESTAMP));
 INSERT INTO business_unit (id_business_unit, name, id_client, created_by, created_date) VALUES (2, 'Sucursal 3', 1, 'system', STRINGTOUTF8(CURRENT_TIMESTAMP));
 INSERT INTO business_unit (id_business_unit, name, id_client, created_by, created_date) VALUES (3, 'Resort 3', 3, 'system', STRINGTOUTF8(CURRENT_TIMESTAMP));
 INSERT INTO business_unit (id_business_unit, name, id_client, created_by, created_date) VALUES (4, 'Resort 4', 3, 'system', STRINGTOUTF8(CURRENT_TIMESTAMP));
 
 INSERT INTO project (id_project, name, id_business_unit, created_by, created_date) VALUES (1, 'Portal General', 3, 'system', STRINGTOUTF8(CURRENT_TIMESTAMP));
 INSERT INTO project (id_project, name, id_business_unit, created_by, created_date) VALUES (2, 'Portal Privado', 3, 'system', STRINGTOUTF8(CURRENT_TIMESTAMP));
 INSERT INTO project (id_project, name, id_business_unit, created_by, created_date) VALUES (3, 'Microservicios', 1, 'system', STRINGTOUTF8(CURRENT_TIMESTAMP));
 INSERT INTO project (id_project, name, id_business_unit, created_by, created_date) VALUES (4, 'Carga Masiva', 4, 'system', STRINGTOUTF8(CURRENT_TIMESTAMP));
 
 INSERT INTO environment (id_environment, name, created_by, created_date) VALUES (1, 'Desarrollo', 'system', STRINGTOUTF8(CURRENT_TIMESTAMP));
 INSERT INTO environment (id_environment, name, created_by, created_date) VALUES (2, 'Calidad', 'system', STRINGTOUTF8(CURRENT_TIMESTAMP));
 INSERT INTO environment (id_environment, name, created_by, created_date) VALUES (3, 'Producción', 'system', STRINGTOUTF8(CURRENT_TIMESTAMP));
 
 INSERT INTO priority (id_priority, name, created_by, created_date) VALUES (1, 'Baja', 'system', STRINGTOUTF8(CURRENT_TIMESTAMP));
 INSERT INTO priority (id_priority, name, created_by, created_date) VALUES (2, 'Media', 'system', STRINGTOUTF8(CURRENT_TIMESTAMP));
 INSERT INTO priority (id_priority, name, created_by, created_date) VALUES (3, 'Alta', 'system', STRINGTOUTF8(CURRENT_TIMESTAMP));
 INSERT INTO priority (id_priority, name, created_by, created_date) VALUES (4, 'Critica', 'system', STRINGTOUTF8(CURRENT_TIMESTAMP));
 
 INSERT INTO schema_ticket (id_schema_ticket, name, created_by, created_date) VALUES (1, 'En sitio', 'system', STRINGTOUTF8(CURRENT_TIMESTAMP));
 INSERT INTO schema_ticket (id_schema_ticket, name, created_by, created_date) VALUES (2, 'Remoto', 'system', STRINGTOUTF8(CURRENT_TIMESTAMP));
 INSERT INTO schema_ticket (id_schema_ticket, name, created_by, created_date) VALUES (3, 'Mixto', 'system', STRINGTOUTF8(CURRENT_TIMESTAMP));
 
 INSERT INTO status (id_status, name, created_by, created_date) VALUES (1, 'Abierto', 'system', STRINGTOUTF8(CURRENT_TIMESTAMP));
 INSERT INTO status (id_status, name, created_by, created_date) VALUES (2, 'En Desarrollo', 'system', STRINGTOUTF8(CURRENT_TIMESTAMP));
 INSERT INTO status (id_status, name, created_by, created_date) VALUES (3, 'Cerrado', 'system', STRINGTOUTF8(CURRENT_TIMESTAMP));
 INSERT INTO status (id_status, name, created_by, created_date) VALUES (4, 'En pruebas', 'system', STRINGTOUTF8(CURRENT_TIMESTAMP));
 INSERT INTO status (id_status, name, created_by, created_date) VALUES (5, 'Cancelado', 'system', STRINGTOUTF8(CURRENT_TIMESTAMP));
 
 INSERT INTO technology (id_technology, name, created_by, created_date) VALUES (1, 'NodeJS', 'system', STRINGTOUTF8(CURRENT_TIMESTAMP));
 INSERT INTO technology (id_technology, name, created_by, created_date) VALUES (2, 'ABAP', 'system', STRINGTOUTF8(CURRENT_TIMESTAMP));
 INSERT INTO technology (id_technology, name, created_by, created_date) VALUES (3, 'Java', 'system', STRINGTOUTF8(CURRENT_TIMESTAMP));
 INSERT INTO technology (id_technology, name, created_by, created_date) VALUES (4, '.Net', 'system', STRINGTOUTF8(CURRENT_TIMESTAMP));
 
 INSERT INTO unit_time (id_unit_time, name, created_by, created_date) VALUES (1, 'Horas', 'system', STRINGTOUTF8(CURRENT_TIMESTAMP));
 INSERT INTO unit_time (id_unit_time, name, created_by, created_date) VALUES (2, 'Dias', 'system', STRINGTOUTF8(CURRENT_TIMESTAMP));

 insert into user (created_by, created_date, last_modified_by, last_modified_date, activated, activation_key, email, first_name, image_url, lang_key, last_name, login, password_hash, reset_date, reset_key) 
 			values ('anonymousUser',  STRINGTOUTF8(CURRENT_TIMESTAMP), null, null, 1, null, 'paco@gmail.com', 'Paco', null, 'ES', 'Carrillo', 'paco', '$2a$10$z39mCahnMd2SImwtMXJQVOPGAfkAqrxK4Set4ZdCCyuGXONrB7/4q', null, null);
 
 insert into user_role (user_id, role_name) values (1, 'ROLE_USER');