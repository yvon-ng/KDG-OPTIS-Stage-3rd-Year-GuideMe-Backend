--insert users

INSERT INTO users(user_id, first_name, last_name,email, role, client_name, start_date_at_client, avatar)
VALUES ('00000000-0000-0000-0000-000000000001', 'Keshia', 'Niemegeerts', 'keshia@test.com', 'MANAGER', NULL, NULL, ''),
       ('00000000-0000-0000-0000-000000000002', 'Kristof', 'De Bock', 'kristof@test.com', 'CONSULTANT', 'Toyota', '2026-01-12T11:12:00', ''),
       ('00000000-0000-0000-0000-000000000003', 'Hannelie', 'Somers', 'hannelie@test.com', 'CONSULTANT', NULL, '2026-08-23T03:30:00', ''),
       ('4a94af3d-350a-4425-9a31-ba071e695a14', 'Shaden', 'Ng', 'ngshade@cronos.be', 'CONSULTANT', 'Optis', date(now()), 'https://cdn-icons-png.flaticon.com/512/5491/5491976.png');


--insert users
INSERT INTO consultant_client_ratings(consultant_user_id, client_name, rating, date)
VALUES ('4a94af3d-350a-4425-9a31-ba071e695a14','FujiFilm', 'SEVEN', date(now()));


--insert notes
INSERT INTO notes(note_id, title, owner, visibility, meeting, description, creation_time)
VALUES ('10000000-0000-0000-0000-000000000000', 'Request for raise', '4a94af3d-350a-4425-9a31-ba071e695a14','SHARED', NULL, '{"text":"provide all the evidence for hard work and add manager for raise based on the given evidences"}', '2026-01-18T14:00:00'),
       ('20000000-0000-0000-0000-000000000000','Sprint retrospective notes','4a94af3d-350a-4425-9a31-ba071e695a14','MEETING_PARTICIPANTS','20000000-0000-0000-0000-000000000000','{"text":"Discuss what went well, what can be improved, and action points"}', '2025-05-18T14:23:22'),
       ('30000000-0000-0000-0000-000000000000','Personal growth plan','4a94af3d-350a-4425-9a31-ba071e695a14','PRIVATE',NULL,'{"text":"Define goals for the next 6 months and track progress weekly"}', '2026-02-01T00:00:00');

--insert meetings
INSERT INTO meetings(meeting_id, name, reference_code, start_date_time, end_date_time, occurred)
VALUES ('00000000-0000-0000-0000-000000000030','SYNC Q3', 'VBA-002', '2026-08-23T10:00:00', '2026-01-23T11:00:00', true),
       ('00000000-0000-0000-0000-000000000040','Daily Standup', 'DES-659', '2026-09-09T10:00:00', '2026-09-09T11:00:00', false),
       ('00000000-0000-0000-0000-000000000050','SYNC Q2', 'ERF-659', '2027-06-17T13:45:12', '2027-06-18T13:45:12', false);


--insert attendee
INSERT INTO attendee(role, attendee_id, meeting_id, user_id)
VALUES
    (NULL, 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', '00000000-0000-0000-0000-000000000030', '00000000-0000-0000-0000-000000000002'),
    (NULL, 'bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb', '00000000-0000-0000-0000-000000000030', '4a94af3d-350a-4425-9a31-ba071e695a14'),
    (NULL, 'cccccccc-cccc-cccc-cccc-cccccccccccc', '00000000-0000-0000-0000-000000000040', '00000000-0000-0000-0000-000000000003'),
    (NULL, 'dddddddd-dddd-dddd-dddd-dddddddddddd', '00000000-0000-0000-0000-000000000040', '4a94af3d-350a-4425-9a31-ba071e695a14'),
    (NULL, 'eeeeeeee-eeee-eeee-eeee-eeeeeeeeeeee', '00000000-0000-0000-0000-000000000050', '00000000-0000-0000-0000-000000000003'),
    (NULL, 'ffffffff-ffff-ffff-ffff-ffffffffffff', '00000000-0000-0000-0000-000000000050', '00000000-0000-0000-0000-000000000002');
