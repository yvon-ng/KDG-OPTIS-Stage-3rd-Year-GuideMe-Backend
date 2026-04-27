--insert user

INSERT INTO users(user_id, first_name, last_name,email, role, client_name, start_date_at_client, avatar)
VALUES ('00000000-0000-0000-0000-000000000001', 'Keshia', 'Niemegeerts', 'keshia@test.com', 'MANAGER', null, null, ''),
       ('00000000-0000-0000-0000-000000000002', 'Kristof', 'De Bock', 'kristof@test.com', 'CONSULTANT', null, null, ''),
       ('00000000-0000-0000-0000-000000000003', 'Hannelie', 'Somers', 'hannelie@test.com', 'CONSULTANT', null, null, ''),
       ('4a94af3d-350a-4425-9a31-ba071e695a14', 'Shaden', 'Ng', 'ngshade@cronos.be', 'CONSULTANT', 'optis', date(now()), 'https://cdn-icons-png.flaticon.com/512/5491/5491976.png');


--insert user
INSERT INTO consultant_client_ratings(consultant_user_id, client_name, rating, date)
VALUES ('4a94af3d-350a-4425-9a31-ba071e695a14','FujiFilm', 'SEVEN', date(now()));