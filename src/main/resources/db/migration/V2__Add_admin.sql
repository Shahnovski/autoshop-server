insert into users (id, username, password, active)
    values (1, 'admin', '$2a$10$EblZqNptyYvcLm/VwDCVAuBjzZOI7khzdyGPBr08PpIi0na624b8.', true);

insert into user_roles (user_id, roles)
    values (1, 'USER'), (1, 'ADMIN');