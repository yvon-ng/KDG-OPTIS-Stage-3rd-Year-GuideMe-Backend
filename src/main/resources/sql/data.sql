INSERT INTO users(user_id, first_name, last_name,email, role, client_name, start_date_at_client, avatar)
VALUES ('00000000-0000-0000-0000-000000000001', 'Keshia', 'Niemegeerts', 'keshia@test.com', 'MANAGER', null, null, ''),
       ('00000000-0000-0000-0000-000000000002', 'Kristof', 'De Bock', 'kristof@test.com', 'CONSULTANT', null, null, ''),
       ('00000000-0000-0000-0000-000000000003', 'Hannelie', 'Somers', 'hannelie@test.com', 'CONSULTANT', null, null, ''),
       ('4a94af3d-350a-4425-9a31-ba071e695a14', 'Shaden', 'Ng', 'ngshade@cronos.be', 'CONSULTANT', 'optis', date(now()), 'https://cdn-icons-png.flaticon.com/512/5491/5491976.png'),
        ('5965a3ec-531c-4a2e-a443-4d7049a15549', 'Abir', 'Belhadj', 'belhaab@cronos.be', 'MANAGER', null, null, '' );

INSERT INTO meetings(meeting_id, name, reference_code, start_date_time, end_date_time, occured)
VALUES
    ('00000000-0000-0000-0000-000000000010','SYNC Q3', 'KJE-002', '2026-08-23T10:00:00', '2026-08-23T11:00:00', false),
    ('00000000-0000-0000-0000-000000000020','SYNC Q1', 'FJE-003', '2026-01-21T10:00:00', '2026-01-21T11:00:00', true);

INSERT INTO meeting_requests(meeting_request_id, previous_request_id, name, reference_code, start_date_time, end_date_time, recurrence_interval_in_months, requested_by, is_allowed, status)
VALUES
    ('01111111-1111-1111-1111-111111111111', null, 'YEARLY SYNC', 'BAY-123', '2026-01-18T14:00:00', '2026-01-18T15:00:00', 1, '00000000-0000-0000-0000-000000000001', true, 'COUNTER_REQUESTED'),
    ('11111111-1111-1111-1111-111111111111', '01111111-1111-1111-1111-111111111111', 'YEARLY SYNC', 'BAY-123', '2026-01-18T10:00:00', '2026-01-18T11:00:00', 1, '00000000-0000-0000-0000-000000000002', true, 'ACCEPTED'),
    ('22222222-2222-2222-2222-222222222222', null, 'MONTHLY SYNC', 'GKI-231', '2026-02-20T10:00:00', '2026-02-20T11:00:00', 12, '00000000-0000-0000-0000-000000000001', true, 'ACCEPTED');

INSERT INTO meeting_request_participants(meeting_request_id, user_id)
VALUES
    ('01111111-1111-1111-1111-111111111111', '00000000-0000-0000-0000-000000000001' ),
    ('01111111-1111-1111-1111-111111111111', '00000000-0000-0000-0000-000000000002' );

INSERT INTO attendee(role, attendee_id, meeting_id, user_id)
VALUES
    (null, '55555555-5555-5555-5555-555555555555', '00000000-0000-0000-0000-000000000010', '00000000-0000-0000-0000-000000000002'),
    (null, '66666666-6666-6666-6666-666666666666', '00000000-0000-0000-0000-000000000010', '00000000-0000-0000-0000-000000000001'),
    (null, '77777777-7777-7777-7777-777777777777', '00000000-0000-0000-0000-000000000020', '00000000-0000-0000-0000-000000000003'),
    (null, '88888888-8888-8888-8888-888888888888', '00000000-0000-0000-0000-000000000020', '00000000-0000-0000-0000-000000000001')

--insert user
--insert user
INSERT INTO consultant_client_ratings(consultant_user_id, client_name, rating, date)
VALUES ('4a94af3d-350a-4425-9a31-ba071e695a14','FujiFilm', 'SEVEN', date(now()));