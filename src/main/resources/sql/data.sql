--insert users

INSERT INTO users(user_id, first_name, last_name, email, role, client_name, start_date_at_client, avatar)
VALUES ('00000000-0000-0000-0000-000000000001', 'Keshia', 'Niemegeerts', 'keshia@test.com', 'MANAGER', null, null,
        'profile-images/00000000-0000-0000-0000-000000000001/Keshia-1.svg'),
       ('00000000-0000-0000-0000-000000000002', 'Kristof', 'De Bock', 'kristof@test.com', 'CONSULTANT', null, null,
        'profile-images/00000000-0000-0000-0000-000000000002/Kristof-1.svg'),
       ('00000000-0000-0000-0000-000000000003', 'Hannelie', 'Somers', 'hannelie@test.com', 'CONSULTANT', null, null,
        'profile-images/00000000-0000-0000-0000-000000000003/Hannelie-1.svg'),
       ('4a94af3d-350a-4425-9a31-ba071e695a14', 'Shaden', 'Ng', 'Shaden.Ng@optis.be', 'CONSULTANT', 'FujiFilm',
        date(now()), 'profile-images/4a94af3d-350a-4425-9a31-ba071e695a14/5491976.png'),
       ('5965a3ec-531c-4a2e-a443-4d7049a15549', 'Abir', 'Belhadj', 'Abir.Belhadj@optis.be', 'MANAGER', null, null,
        'profile-images/5965a3ec-531c-4a2e-a443-4d7049a15549/4352141.png');

INSERT INTO meetings(meeting_id, name, reference_code, start_date_time, end_date_time, occurred)
VALUES ('00000000-0000-0000-0000-000000000010', 'SYNC Q3', 'KJE-002', '2026-08-23T10:00:00', '2026-08-23T11:00:00',
        false),
       ('00000000-0000-0000-0000-000000000020', 'SYNC Q1', 'FJE-003', '2026-01-21T10:00:00', '2026-01-21T11:00:00',
        true);

INSERT INTO meeting_requests(meeting_request_id, previous_request_id, name, reference_code, start_date_time,
                             end_date_time, recurrence_per_year, requested_by, is_allowed, status)
VALUES ('01111111-1111-1111-1111-111111111111', null, 'YEARLY SYNC', 'BAY-123', '2026-01-18T14:00:00',
        '2026-01-18T15:00:00', 1, '00000000-0000-0000-0000-000000000001', true, 'COUNTER_REQUESTED'),
       ('11111111-1111-1111-1111-111111111111', '01111111-1111-1111-1111-111111111111', 'YEARLY SYNC', 'BAY-123',
        '2026-01-18T10:00:00', '2026-01-18T11:00:00', 1, '00000000-0000-0000-0000-000000000002', true, 'ACCEPTED'),
       ('22222222-2222-2222-2222-222222222222', null, 'MONTHLY SYNC', 'GKI-231', '2026-02-20T10:00:00',
        '2026-02-20T11:00:00', 12, '00000000-0000-0000-0000-000000000001', true, 'ACCEPTED');

INSERT INTO meeting_request_participants(meeting_request_id, user_id)
VALUES ('01111111-1111-1111-1111-111111111111', '00000000-0000-0000-0000-000000000002');

INSERT INTO attendees(role, meeting_id, user_id)
VALUES (null, '00000000-0000-0000-0000-000000000010', '00000000-0000-0000-0000-000000000002'),
       (null, '00000000-0000-0000-0000-000000000010', '00000000-0000-0000-0000-000000000001'),
       (null, '00000000-0000-0000-0000-000000000020', '00000000-0000-0000-0000-000000000003'),
       (null, '00000000-0000-0000-0000-000000000020', '00000000-0000-0000-0000-000000000001');

--insert client score
INSERT INTO consultant_client_scores(consultant_user_id, client_name, score, date_time)
VALUES ('4a94af3d-350a-4425-9a31-ba071e695a14', 'FujiFilm', 'SEVEN', '2026-02-10T10:00:00'),
       ('4a94af3d-350a-4425-9a31-ba071e695a14', 'FujiFilm', 'TEN', '2026-04-15T10:00:00');

--insert notes
INSERT INTO notes(note_id, title, owner, visibility, meeting_id, description, creation_time)
VALUES ('10000000-0000-0000-0000-000000000000', 'Request for raise', '4a94af3d-350a-4425-9a31-ba071e695a14', 'SHARED',
        NULL, '{
    "ops": [
      {
        "insert": "provide all the evidence for hard work and add manager for raise based on the given evidences"
      }
    ]
  }', '2026-01-18T14:00:00'),
       ('20000000-0000-0000-0000-000000000000', 'Sprint retrospective notes', '4a94af3d-350a-4425-9a31-ba071e695a14',
        'MEETING_PARTICIPANTS', '20000000-0000-0000-0000-000000000000', '{
         "ops": [
           {
             "insert": "Discuss what went well, what can be improved, and action points"
           }
         ]
       }', '2025-05-18T14:23:22'),
       ('30000000-0000-0000-0000-000000000000', 'Personal growth plan', '4a94af3d-350a-4425-9a31-ba071e695a14',
        'PRIVATE', NULL, '{
         "ops": [
           {
             "insert": "Define goals for the next 6 months and track progress weekly"
           }
         ]
       }', '2026-02-01T00:00:00');

--insert meetings
INSERT INTO meetings(meeting_id, name, reference_code, start_date_time, end_date_time, occurred)
VALUES ('00000000-0000-0000-0000-000000000030', 'SYNC Q3', 'VBA-002', '2026-08-23T10:00:00', '2026-01-23T11:00:00',
        true),
       ('00000000-0000-0000-0000-000000000040', 'Daily Standup', 'DES-659', '2026-09-09T10:00:00',
        '2026-09-09T11:00:00', false),
       ('00000000-0000-0000-0000-000000000050', 'SYNC Q2', 'ERF-659', '2027-06-17T13:45:12', '2027-06-18T13:45:12',
        false);

--insert attendee
INSERT INTO attendees(role, meeting_id, user_id)
VALUES (NULL, '00000000-0000-0000-0000-000000000030', '00000000-0000-0000-0000-000000000002'),
       (NULL, '00000000-0000-0000-0000-000000000030', '4a94af3d-350a-4425-9a31-ba071e695a14'),
       (NULL, '00000000-0000-0000-0000-000000000040', '00000000-0000-0000-0000-000000000003'),
       (NULL, '00000000-0000-0000-0000-000000000040', '4a94af3d-350a-4425-9a31-ba071e695a14'),
       (NULL, '00000000-0000-0000-0000-000000000050', '00000000-0000-0000-0000-000000000003'),
       (NULL, '00000000-0000-0000-0000-000000000050', '00000000-0000-0000-0000-000000000002');

-- seeding for abir user (manager) to use as manager

INSERT INTO meetings(meeting_id, name, reference_code, start_date_time, end_date_time, occurred)
VALUES ('70000000-0000-0000-0000-000000000001', 'Architecture Review', 'ARC-001', '2025-02-14T09:00:00',
        '2025-02-14T10:00:00', true),
       ('70000000-0000-0000-0000-000000000002', 'Q2 Planning', 'QPL-002', '2025-05-10T13:00:00', '2025-05-10T15:00:00',
        true),
       ('70000000-0000-0000-0000-000000000003', 'Client Escalation Sync', 'CES-003', '2025-08-21T11:00:00',
        '2025-08-21T12:00:00', false),
       ('70000000-0000-0000-0000-000000000004', 'Yearly Budget Meeting', 'YBM-004', '2026-01-11T15:00:00',
        '2026-01-11T16:30:00', false),
       ('70000000-0000-0000-0000-000000000005', 'Consultant Feedback Session', 'CFS-005', '2024-11-03T14:00:00',
        '2024-11-03T15:00:00', true);


INSERT INTO attendees(role, meeting_id, user_id)
VALUES (NULL, '70000000-0000-0000-0000-000000000001', '5965a3ec-531c-4a2e-a443-4d7049a15549'),
       (NULL, '70000000-0000-0000-0000-000000000001', '00000000-0000-0000-0000-000000000001'),
       (NULL, '70000000-0000-0000-0000-000000000002', '5965a3ec-531c-4a2e-a443-4d7049a15549'),
       (NULL, '70000000-0000-0000-0000-000000000002', '00000000-0000-0000-0000-000000000002'),
       (NULL, '70000000-0000-0000-0000-000000000003', '5965a3ec-531c-4a2e-a443-4d7049a15549'),
       (NULL, '70000000-0000-0000-0000-000000000003', '00000000-0000-0000-0000-000000000003'),
       (NULL, '70000000-0000-0000-0000-000000000004', '5965a3ec-531c-4a2e-a443-4d7049a15549'),
       (NULL, '70000000-0000-0000-0000-000000000004', '4a94af3d-350a-4425-9a31-ba071e695a14'),
       (NULL, '70000000-0000-0000-0000-000000000005', '5965a3ec-531c-4a2e-a443-4d7049a15549'),
       (NULL, '70000000-0000-0000-0000-000000000005', '00000000-0000-0000-0000-000000000003');


INSERT INTO meeting_requests(meeting_request_id, previous_request_id, name, reference_code, start_date_time,
                             end_date_time, recurrence_per_year, requested_by, is_allowed, status)
VALUES ('80000000-0000-0000-0000-000000000001', null, 'Cloud Migration Discussion', 'CMD-001', '2025-03-12T10:00:00',
        '2025-03-12T11:00:00', 1, '5965a3ec-531c-4a2e-a443-4d7049a15549', true, 'PENDING'),
       ('80000000-0000-0000-0000-000000000002', null, 'Security Review', 'SEC-002', '2025-06-18T14:00:00',
        '2025-06-18T15:00:00', 1, '5965a3ec-531c-4a2e-a443-4d7049a15549', true, 'ACCEPTED'),
       ('80000000-0000-0000-0000-000000000003', null, 'Performance Evaluation', 'PER-003', '2025-09-09T09:00:00',
        '2025-09-09T10:00:00', 1, '5965a3ec-531c-4a2e-a443-4d7049a15549', true, 'DECLINED'),
       ('80000000-0000-0000-0000-000000000004', null, 'Infrastructure Planning', 'INF-004', '2026-02-02T13:00:00',
        '2026-02-02T14:00:00', 2, '5965a3ec-531c-4a2e-a443-4d7049a15549', true, 'COUNTER_REQUESTED');


INSERT INTO meeting_request_participants(meeting_request_id, user_id)
VALUES ('80000000-0000-0000-0000-000000000001', '00000000-0000-0000-0000-000000000001'),
       ('80000000-0000-0000-0000-000000000002', '00000000-0000-0000-0000-000000000002'),
       ('80000000-0000-0000-0000-000000000003', '00000000-0000-0000-0000-000000000003'),
       ('80000000-0000-0000-0000-000000000004', '4a94af3d-350a-4425-9a31-ba071e695a14');

--insert goals
INSERT INTO goals(goal_id, user_id, title, description, creation_date, due_date, achievement_date)
VALUES ('c1a11111-1111-1111-1111-111111111111', '00000000-0000-0000-0000-000000000002', 'Finish onboarding module',
        'Complete the full onboarding training', '2026-05-01', '2026-05-10', '2026-05-08'),
       ('c1a22222-2222-2222-2222-222222222222', '4a94af3d-350a-4425-9a31-ba071e695a14', 'Improve Angular skills',
        'Practice signals and standalone components', '2026-05-02', '2026-06-01', NULL),
       ('c1a33333-3333-3333-3333-333333333333', '4a94af3d-350a-4425-9a31-ba071e695a14', 'Deploy first project',
        'Deploy GuideMe to production environment', '2026-04-15', '2026-05-15', '2026-05-14'),
       ('c1a44444-4444-4444-4444-444444444444', '00000000-0000-0000-0000-000000000003', 'Write clean architecture code',
        'Refactor services into clean layered structure', '2026-05-05', '2026-06-05', NULL),
       ('c1a55555-5555-5555-5555-555555555555', '5965a3ec-531c-4a2e-a443-4d7049a15549', 'Finish meeting module',
        'Complete scheduling and email notifications', '2026-05-10', '2026-05-20', '2026-05-16'),
       ('c1a66666-6666-6666-6666-666666666666', '5965a3ec-531c-4a2e-a443-4d7049a15549', 'Prepare slides',
        'Create presentation slides for end demo', '2026-05-12', '2026-06-01', NULL),
       ('c1a77777-7777-7777-7777-777777777777', '00000000-0000-0000-0000-000000000002', 'Read system design book',
        'Study distributed systems fundamentals', '2026-05-03', '2026-06-15', NULL),
       ('c1a77777-8888-8888-8888-888888888888', '4a94af3d-350a-4425-9a31-ba071e695a14', 'Books to read',
        'Atomic Habits, Deep Work, The Alchemist', '2026-05-12', '2026-05-20', NULL);