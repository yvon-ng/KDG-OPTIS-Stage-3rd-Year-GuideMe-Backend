--insert users

INSERT INTO users(user_id, first_name, last_name,email, role, client_name, start_date_at_client, avatar)
VALUES ('00000000-0000-0000-0000-000000000001', 'Keshia', 'Niemegeerts', 'keshia@test.com', 'MANAGER', NULL, NULL, ''),
       ('00000000-0000-0000-0000-000000000002', 'Kristof', 'De Bock', 'kristof@test.com', 'CONSULTANT', NULL, NULL, ''),
       ('00000000-0000-0000-0000-000000000003', 'Hannelie', 'Somers', 'hannelie@test.com', 'CONSULTANT', NULL, NULL, ''),
       ('4a94af3d-350a-4425-9a31-ba071e695a14', 'Shaden', 'Ng', 'ngshade@cronos.be', 'CONSULTANT', 'optis', date(now()), 'https://cdn-icons-png.flaticon.com/512/5491/5491976.png');


--insert users
INSERT INTO consultant_client_ratings(consultant_user_id, client_name, rating, date)
VALUES ('4a94af3d-350a-4425-9a31-ba071e695a14','FujiFilm', 'SEVEN', date(now()));


--insert notes
INSERT INTO notes(note_id, title, owner, visibility, meeting, description, creation_time)
VALUES ('10000000-0000-0000-0000-000000000000', 'Request for raise', '4a94af3d-350a-4425-9a31-ba071e695a14','SHARED', NULL,'provide all the evidence for hard work and add manager for raise based on the given evidences', '2026-01-18T14:00:00'),
       ('20000000-0000-0000-0000-000000000000','Sprint retrospective notes','4a94af3d-350a-4425-9a31-ba071e695a14','MEETING_PARTICIPANTS','20000000-0000-0000-0000-000000000000','Discuss what went well, what can be improved, and action points', '2025-05-18T14:23:22'),
       ('30000000-0000-0000-0000-000000000000','Personal growth plan','4a94af3d-350a-4425-9a31-ba071e695a14','PRIVATE',NULL,'Define goals for the next 6 months and track progress weekly', '2026-02-01T00:00:00');
