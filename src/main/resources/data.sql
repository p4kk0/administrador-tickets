 use incidencias;
 
 INSERT INTO role (name) VALUES ('ROLE_LIDER');
 INSERT INTO role (name) VALUES ('ROLE_GERENTE');
 INSERT INTO role (name) VALUES ('ROLE_ADMIN');
 INSERT INTO role (name) VALUES ('ROLE_MESA_TRABAJO');
 INSERT INTO role (name) VALUES ('ROLE_KEY_USER');

 INSERT INTO client (id_client, name, created_by, created_date) VALUES (1, 'Banco 1', 'system',  now());
 INSERT INTO client (id_client, name, created_by, created_date) VALUES (2, 'Tienda 24', 'system',  now());
 INSERT INTO client (id_client, name, created_by, created_date) VALUES (3, 'Hotel 5', 'system',  now());
 
 INSERT INTO business_unit (id_business_unit, name, id_client, created_by, created_date) VALUES (1, 'Sucursal 1', 1, 'system',  now());
 INSERT INTO business_unit (id_business_unit, name, id_client, created_by, created_date) VALUES (2, 'Sucursal 3', 1, 'system',  now());
 INSERT INTO business_unit (id_business_unit, name, id_client, created_by, created_date) VALUES (3, 'Resort 3', 3, 'system',  now());
 INSERT INTO business_unit (id_business_unit, name, id_client, created_by, created_date) VALUES (4, 'Resort 4', 3, 'system',  now());
 
 INSERT INTO project (id_project, name, id_business_unit, created_by, created_date) VALUES (1, 'Portal General', 3, 'system',  now());
 INSERT INTO project (id_project, name, id_business_unit, created_by, created_date) VALUES (2, 'Portal Privado', 3, 'system',  now());
 INSERT INTO project (id_project, name, id_business_unit, created_by, created_date) VALUES (3, 'Microservicios', 1, 'system',  now());
 INSERT INTO project (id_project, name, id_business_unit, created_by, created_date) VALUES (4, 'Carga Masiva', 4, 'system',  now());
 
 INSERT INTO environment (id_environment, name, created_by, created_date) VALUES (1, 'Desarrollo', 'system',  now());
 INSERT INTO environment (id_environment, name, created_by, created_date) VALUES (2, 'Calidad', 'system',  now());
 INSERT INTO environment (id_environment, name, created_by, created_date) VALUES (3, 'Producción', 'system',  now());
 
 INSERT INTO priority (id_priority, name, created_by, created_date) VALUES (1, 'Baja', 'system',  now());
 INSERT INTO priority (id_priority, name, created_by, created_date) VALUES (2, 'Media', 'system',  now());
 INSERT INTO priority (id_priority, name, created_by, created_date) VALUES (3, 'Alta', 'system',  now());
 INSERT INTO priority (id_priority, name, created_by, created_date) VALUES (4, 'Critica', 'system',  now());
 
 INSERT INTO schema_ticket (id_schema_ticket, name, created_by, created_date) VALUES (1, 'En sitio', 'system',  now());
 INSERT INTO schema_ticket (id_schema_ticket, name, created_by, created_date) VALUES (2, 'Remoto', 'system',  now());
 INSERT INTO schema_ticket (id_schema_ticket, name, created_by, created_date) VALUES (3, 'Mixto', 'system',  now());
 
 INSERT INTO status (id_status, name, created_by, created_date) VALUES (1, 'Abierto', 'system',  now());
 INSERT INTO status (id_status, name, created_by, created_date) VALUES (2, 'Desarrollo', 'system',  now());
 INSERT INTO status (id_status, name, created_by, created_date) VALUES (3, 'Termindado', 'system',  now());
 INSERT INTO status (id_status, name, created_by, created_date) VALUES (4, 'Pruebas', 'system',  now());
 INSERT INTO status (id_status, name, created_by, created_date) VALUES (5, 'Cerrado', 'system',  now());
 
 INSERT INTO technology (id_technology, name, created_by, created_date) VALUES (1, 'NodeJS', 'system',  now());
 INSERT INTO technology (id_technology, name, created_by, created_date) VALUES (2, 'ABAP', 'system',  now());
 INSERT INTO technology (id_technology, name, created_by, created_date) VALUES (3, 'Java', 'system',  now());
 INSERT INTO technology (id_technology, name, created_by, created_date) VALUES (4, '.Net', 'system',  now());
 
 INSERT INTO unit_time (id_unit_time, name, created_by, created_date) VALUES (1, 'Horas', 'system',  now());
 INSERT INTO unit_time (id_unit_time, name, created_by, created_date) VALUES (2, 'Dias', 'system',  now());
 
 insert into user (created_by, created_date, last_modified_by, last_modified_date, activated, activation_key, email, first_name, image_url, lang_key, last_name, login, password_hash, reset_date, reset_key, id_business_unit_fk, id_client_fk) 
 			values ('anonymousUser', now(), null, null, 1, null, 'admin@gmail.com', 'Paco', null, 'ES', 'Carrillo', 'admin', '$2a$10$ZZ6buLqFHh65NAs4VqKDH.yzftKQcpwWIvHenVyY13Uctdrc8Em0G', null, null, null, null);
 			
 insert into user (created_by, created_date, last_modified_by, last_modified_date, activated, activation_key, email, first_name, image_url, lang_key, last_name, login, password_hash, reset_date, reset_key, id_business_unit_fk, id_client_fk) 
 			values ('anonymousUser', now(), null, null, 1, null, 'gerente@gmail.com', 'Paco2', null, 'ES', 'Carrillo2', 'gerente', '$2a$10$ZZ6buLqFHh65NAs4VqKDH.yzftKQcpwWIvHenVyY13Uctdrc8Em0G', null, null, null, 1);
 			
 insert into user (created_by, created_date, last_modified_by, last_modified_date, activated, activation_key, email, first_name, image_url, lang_key, last_name, login, password_hash, reset_date, reset_key, id_business_unit_fk, id_client_fk) 
 			values ('anonymousUser', now(), null, null, 1, null, 'lider@gmail.com', 'Paco3', null, 'ES', 'Carrillo3', 'lider', '$2a$10$ZZ6buLqFHh65NAs4VqKDH.yzftKQcpwWIvHenVyY13Uctdrc8Em0G', null, null, 1, 1);
            
insert into user (created_by, created_date, last_modified_by, last_modified_date, activated, activation_key, email, first_name, image_url, lang_key, last_name, login, password_hash, reset_date, reset_key, id_business_unit_fk, id_client_fk) 
 			values ('anonymousUser', now(), null, null, 1, null, 'gerente2@gmail.com', 'Paco4', null, 'ES', 'Carrillo4', 'gerente2', '$2a$10$ZZ6buLqFHh65NAs4VqKDH.yzftKQcpwWIvHenVyY13Uctdrc8Em0G', null, null, null, 3);
 			
insert into user (created_by, created_date, last_modified_by, last_modified_date, activated, activation_key, email, first_name, image_url, lang_key, last_name, login, password_hash, reset_date, reset_key, id_business_unit_fk, id_client_fk) 
 			values ('anonymousUser', now(), null, null, 1, null, 'lider2@gmail.com', 'Paco5', null, 'ES', 'Carrillo5', 'lider2', '$2a$10$ZZ6buLqFHh65NAs4VqKDH.yzftKQcpwWIvHenVyY13Uctdrc8Em0G', null, null, 4, 3);
 
 insert into user_role (user_id, role_name) values (1, 'ROLE_ADMIN');
 insert into user_role (user_id, role_name) values (2, 'ROLE_GERENTE');
 insert into user_role (user_id, role_name) values (3, 'ROLE_LIDER');
 insert into user_role (user_id, role_name) values (4, 'ROLE_GERENTE');
 insert into user_role (user_id, role_name) values (5, 'ROLE_LIDER');