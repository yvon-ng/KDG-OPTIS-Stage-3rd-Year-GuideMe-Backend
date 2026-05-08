--insert users

INSERT INTO users(user_id, first_name, last_name,email, role, client_name, start_date_at_client, avatar)
VALUES ('00000000-0000-0000-0000-000000000001', 'Keshia', 'Niemegeerts', 'keshia@test.com', 'MANAGER', null, null, ''),
       ('00000000-0000-0000-0000-000000000002', 'Kristof', 'De Bock', 'kristof@test.com', 'CONSULTANT', null, null, ''),
       ('00000000-0000-0000-0000-000000000003', 'Hannelie', 'Somers', 'hannelie@test.com', 'CONSULTANT', null, null, ''),
       ('4a94af3d-350a-4425-9a31-ba071e695a14', 'Shaden', 'Ng', 'ngshade@cronos.be', 'CONSULTANT', 'optis', date(now()), 'https://cdn-icons-png.flaticon.com/512/5491/5491976.png'),
       ('5965a3ec-531c-4a2e-a443-4d7049a15549', 'Abir', 'Belhadj', 'belhaab@cronos.be', 'MANAGER', null, null, '' );

INSERT INTO meetings(meeting_id, name, reference_code, start_date_time, end_date_time, occurred)
VALUES
    ('00000000-0000-0000-0000-000000000010','SYNC Q3', 'KJE-002', '2026-08-23T10:00:00', '2026-08-23T11:00:00', false),
    ('00000000-0000-0000-0000-000000000020','SYNC Q1', 'FJE-003', '2026-01-21T10:00:00', '2026-01-21T11:00:00', true);

INSERT INTO meeting_requests(meeting_request_id, previous_request_id, name, reference_code, start_date_time, end_date_time, recurrence_per_year, requested_by, is_allowed, status)
VALUES
    ('01111111-1111-1111-1111-111111111111', null, 'YEARLY SYNC', 'BAY-123', '2026-01-18T14:00:00', '2026-01-18T15:00:00', 1, '00000000-0000-0000-0000-000000000001', true, 'COUNTER_REQUESTED'),
    ('11111111-1111-1111-1111-111111111111', '01111111-1111-1111-1111-111111111111', 'YEARLY SYNC', 'BAY-123', '2026-01-18T10:00:00', '2026-01-18T11:00:00', 1, '00000000-0000-0000-0000-000000000002', true, 'ACCEPTED'),
    ('22222222-2222-2222-2222-222222222222', null, 'MONTHLY SYNC', 'GKI-231', '2026-02-20T10:00:00', '2026-02-20T11:00:00', 12, '00000000-0000-0000-0000-000000000001', true, 'ACCEPTED');

INSERT INTO meeting_request_participants(meeting_request_id, user_id)
VALUES
    ('01111111-1111-1111-1111-111111111111', '00000000-0000-0000-0000-000000000002' );

INSERT INTO attendees(role, meeting_id, user_id)
VALUES
    (null,  '00000000-0000-0000-0000-000000000010', '00000000-0000-0000-0000-000000000002'),
    (null,  '00000000-0000-0000-0000-000000000010', '00000000-0000-0000-0000-000000000001'),
    (null,  '00000000-0000-0000-0000-000000000020', '00000000-0000-0000-0000-000000000003'),
    (null,  '00000000-0000-0000-0000-000000000020', '00000000-0000-0000-0000-000000000001');

--insert client rating
INSERT INTO consultant_client_ratings(consultant_user_id, client_name, rating, date)
VALUES ('4a94af3d-350a-4425-9a31-ba071e695a14','FujiFilm', 'SEVEN', date(now()));


--insert notes
INSERT INTO notes(note_id, title, owner, visibility, meeting_id, description, creation_time)
VALUES ('10000000-0000-0000-0000-000000000000', 'Request for raise', '4a94af3d-350a-4425-9a31-ba071e695a14','SHARED', NULL, '{"ops": [{"insert": "provide all the evidence for hard work and add manager for raise based on the given evidences"}]}', '2026-01-18T14:00:00'),
       ('20000000-0000-0000-0000-000000000000','Sprint retrospective notes','4a94af3d-350a-4425-9a31-ba071e695a14','MEETING_PARTICIPANTS','20000000-0000-0000-0000-000000000000','{"ops": [{"insert": "Discuss what went well, what can be improved, and action points"}]}', '2025-05-18T14:23:22'),
       ('30000000-0000-0000-0000-000000000000','Personal growth plan','4a94af3d-350a-4425-9a31-ba071e695a14','PRIVATE',NULL,'{"ops": [{"insert": "Define goals for the next 6 months and track progress weekly"}]}', '2026-02-01T00:00:00');

--insert meetings
INSERT INTO meetings(meeting_id, name, reference_code, start_date_time, end_date_time, occurred)
VALUES ('00000000-0000-0000-0000-000000000030','SYNC Q3', 'VBA-002', '2026-08-23T10:00:00', '2026-01-23T11:00:00', true),
       ('00000000-0000-0000-0000-000000000040','Daily Standup', 'DES-659', '2026-09-09T10:00:00', '2026-09-09T11:00:00', false),
       ('00000000-0000-0000-0000-000000000050','SYNC Q2', 'ERF-659', '2027-06-17T13:45:12', '2027-06-18T13:45:12', false);


--insert attendee
INSERT INTO attendees(role, meeting_id, user_id)
VALUES
    (NULL, '00000000-0000-0000-0000-000000000030', '00000000-0000-0000-0000-000000000002'),
    (NULL,  '00000000-0000-0000-0000-000000000030', '4a94af3d-350a-4425-9a31-ba071e695a14'),
    (NULL,  '00000000-0000-0000-0000-000000000040', '00000000-0000-0000-0000-000000000003'),
    (NULL,  '00000000-0000-0000-0000-000000000040', '4a94af3d-350a-4425-9a31-ba071e695a14'),
    (NULL,  '00000000-0000-0000-0000-000000000050', '00000000-0000-0000-0000-000000000003'),
    (NULL,  '00000000-0000-0000-0000-000000000050', '00000000-0000-0000-0000-000000000002');
