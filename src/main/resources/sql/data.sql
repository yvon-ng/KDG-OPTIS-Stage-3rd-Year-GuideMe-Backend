--insert users

INSERT INTO users(user_id, first_name, last_name, email, role, client_name, start_date_at_client, avatar, profile_notification_sent)
VALUES ('00000000-0000-0000-0000-000000000001', 'Keshia', 'Niemegeerts', 'Keshia@optis.be', 'MANAGER', null, null,
        'profile-images/00000000-0000-0000-0000-000000000001/Keshia-1.png', TRUE),
       ('00000000-0000-0000-0000-000000000002', 'Kristof', 'De Bock', 'Kristof@optis.be', 'CONSULTANT', 'Toyota', '2019-09-09T12:55:00',
        'profile-images/00000000-0000-0000-0000-000000000002/Kristof-1.png', TRUE),
       ('00000000-0000-0000-0000-000000000003', 'Hannelie', 'Somers', 'Hannelie@optis.be', 'CONSULTANT', 'TOMORROWLAND', '2025-05-31T08:00:00',
        'profile-images/00000000-0000-0000-0000-000000000003/Hannelie-1.png', TRUE),
       ('4a94af3d-350a-4425-9a31-ba071e695a14', 'Shaden', 'Ng', 'ngshade@cronos.be', 'CONSULTANT', 'FujiFilm',
        date(now()), 'profile-images/4a94af3d-350a-4425-9a31-ba071e695a14/5491976.png', TRUE),
       ('5965a3ec-531c-4a2e-a443-4d7049a15549', 'Abir', 'Belhadj', 'Abir.Belhadj@optis.be', 'MANAGER', null, null,
        'profile-images/5965a3ec-531c-4a2e-a443-4d7049a15549/4352141.png', TRUE),
       ('00000000-0000-0000-0000-000000000004', 'Dries', 'Meirte', 'dries.meirte@optis.be', 'CONSULTANT', 'Siemens', '2025-11-10T09:00:00',
        'profile-images/Dries-1.png', TRUE),
       ('00000000-0000-0000-0000-000000000005', 'Bram', 'Van Herck', 'Abir.Belhadj@optis.be', 'CONSULTANT', 'Proximus', '2025-10-05T14:00:00',
        'profile-images/Bram-1.png', TRUE),
       ('00000000-0000-0000-0000-000000000006', 'Charlotte', 'Van der Voort', 'Abir.Belhadj@optis.be', 'CONSULTANT', 'Colruyt', '2025-09-15T10:00:00',
        'profile-images/Charlotte-1.png', TRUE),
       ('00000000-0000-0000-0000-000000000007', 'Dimitri', 'Mestdagh', 'dimitri.mestdagh@optis.be', 'CONSULTANT', 'Belfius', '2025-12-01T13:00:00',
        'profile-images/Dimi-1.png', TRUE),
       ('00000000-0000-0000-0000-000000000008', 'Gunther', 'Laurijssens', 'Abir.Belhadj@optis.be', 'CONSULTANT', 'Engie', '2025-08-20T11:00:00',
        'profile-images/Gunther-1.png', TRUE),
       ('00000000-0000-0000-0000-000000000009', 'Mathias', 'Mariman', 'mathias.mariman@optis.be', 'MANAGER', null, null,
        'profile-images/Mathi-1.png', TRUE),
       ('00000000-0000-0000-0000-000000000010', 'Robbe', 'Van Winckel', 'Robbe.VanWinckel@optis.be', 'CONSULTANT', 'Telenet', '2025-10-22T15:00:00',
        'profile-images/Robbe-1.png', TRUE),
       ('00000000-0000-0000-0000-000000000011', 'Tom', 'Smeets', 'Tom.Smeets@optis.be', 'CONSULTANT', 'ING', '2025-11-30T09:30:00',
        'profile-images/Tom-1.png', TRUE),
       ('00000000-0000-0000-0000-000000000012', 'Ward', 'Driesen', 'Ward.Driesen@optis.be', 'CONSULTANT', 'KBC', '2025-09-09T10:00:00',
        'profile-images/Ward-1.png', TRUE),
       ('00000000-0000-0000-0000-000000000013', 'Yeung', 'Hung', 'Hung-Yeung.Chan@optis.be', 'CONSULTANT', 'Delhaize', '2025-12-12T14:00:00',
        'profile-images/Yeung-1.png', TRUE);

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
       ('4a94af3d-350a-4425-9a31-ba071e695a14', 'FujiFilm', 'TEN', '2026-04-15T10:00:00'),
       ('00000000-0000-0000-0000-000000000003', 'FlowFactor', 'FIVE', '2024-11-20T11:00:00'),
       ('00000000-0000-0000-0000-000000000003', 'FlowFactor', 'FOUR', '2025-02-27T16:20:00'),
       ('00000000-0000-0000-0000-000000000003', 'TOMORROWLAND', 'SEVEN', '2025-06-01T10:45:00'),
       ('00000000-0000-0000-0000-000000000002', 'Toyota', 'EIGHT', '2024-09-14T13:55:00');

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
VALUES ('CONSULTANT', '00000000-0000-0000-0000-000000000030', '00000000-0000-0000-0000-000000000002'),
       ('CONSULTANT', '00000000-0000-0000-0000-000000000030', '4a94af3d-350a-4425-9a31-ba071e695a14'),
       ('CONSULTANT', '00000000-0000-0000-0000-000000000040', '00000000-0000-0000-0000-000000000003'),
       ('CONSULTANT', '00000000-0000-0000-0000-000000000040', '4a94af3d-350a-4425-9a31-ba071e695a14'),
       ('CONSULTANT', '00000000-0000-0000-0000-000000000050', '00000000-0000-0000-0000-000000000003'),
       ('CONSULTANT', '00000000-0000-0000-0000-000000000050', '00000000-0000-0000-0000-000000000002');

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
VALUES ('MANAGER', '70000000-0000-0000-0000-000000000001', '5965a3ec-531c-4a2e-a443-4d7049a15549'),
       ('MANAGER', '70000000-0000-0000-0000-000000000001', '00000000-0000-0000-0000-000000000001'),
       ('CONSULTANT', '70000000-0000-0000-0000-000000000002', '5965a3ec-531c-4a2e-a443-4d7049a15549'),
       ('MANAGER', '70000000-0000-0000-0000-000000000002', '00000000-0000-0000-0000-000000000002'),
       ('MANAGER', '70000000-0000-0000-0000-000000000003', '5965a3ec-531c-4a2e-a443-4d7049a15549'),
       ('CONSULTANT', '70000000-0000-0000-0000-000000000003', '00000000-0000-0000-0000-000000000003'),
       ('MANAGER', '70000000-0000-0000-0000-000000000004', '5965a3ec-531c-4a2e-a443-4d7049a15549'),
       ('CONSULTANT', '70000000-0000-0000-0000-000000000004', '4a94af3d-350a-4425-9a31-ba071e695a14'),
       ('MANAGER', '70000000-0000-0000-0000-000000000005', '5965a3ec-531c-4a2e-a443-4d7049a15549'),
       ('CONSULTANT', '70000000-0000-0000-0000-000000000005', '00000000-0000-0000-0000-000000000003');


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

-- ============================================================
-- ADDITIONAL SEEDING BY AI
-- ============================================================

-- -------------------------------------------------------
-- MEETINGS
-- -------------------------------------------------------

INSERT INTO meetings(meeting_id, name, reference_code, start_date_time, end_date_time, occurred)
VALUES
    -- Keshia (manager) additional meetings with consultants
    ('A1000000-0000-0000-0000-000000000001', 'Onboarding Check-in',      'ONC-101', '2026-03-10T09:00:00', '2026-03-10T10:00:00', true),
    ('A1000000-0000-0000-0000-000000000002', 'Mid-year Review',          'MYR-102', '2026-06-15T14:00:00', '2026-06-15T15:30:00', false),
    ('A1000000-0000-0000-0000-000000000003', 'Team Retrospective',       'TRP-103', '2026-04-20T10:00:00', '2026-04-20T11:00:00', true),

    -- Dries
    ('A2000000-0000-0000-0000-000000000001', 'Sprint Planning',          'SPL-201', '2026-05-05T09:00:00', '2026-05-05T10:00:00', true),
    ('A2000000-0000-0000-0000-000000000002', 'Client Kickoff',           'CKO-202', '2026-07-14T13:00:00', '2026-07-14T14:00:00', false),

    -- Bram
    ('A3000000-0000-0000-0000-000000000001', 'Tech Alignment',           'TAL-301', '2026-04-08T11:00:00', '2026-04-08T12:00:00', true),
    ('A3000000-0000-0000-0000-000000000002', 'Quarterly Sync',           'QSY-302', '2026-09-22T10:00:00', '2026-09-22T11:00:00', false),

    -- Charlotte
    ('A4000000-0000-0000-0000-000000000001', 'UX Review',                'UXR-401', '2026-03-25T14:00:00', '2026-03-25T15:00:00', true),
    ('A4000000-0000-0000-0000-000000000002', 'Design Sprint',            'DSP-402', '2026-08-11T09:30:00', '2026-08-11T11:00:00', false),

    -- Dimitri
    ('A5000000-0000-0000-0000-000000000001', 'Infrastructure Sync',      'INS-501', '2026-02-17T10:00:00', '2026-02-17T11:00:00', true),
    ('A5000000-0000-0000-0000-000000000002', 'Security Briefing',        'SCB-502', '2026-10-05T14:00:00', '2026-10-05T15:00:00', false),

    -- Gunther
    ('A6000000-0000-0000-0000-000000000001', 'Stakeholder Update',       'STU-601', '2026-01-29T15:00:00', '2026-01-29T16:00:00', true),
    ('A6000000-0000-0000-0000-000000000002', 'Release Planning',         'RPL-602', '2026-11-03T10:00:00', '2026-11-03T11:00:00', false),

    -- Mathias
    ('A7000000-0000-0000-0000-000000000001', 'Backend Review',           'BER-701', '2026-03-03T09:00:00', '2026-03-03T10:00:00', true),
    ('A7000000-0000-0000-0000-000000000002', 'API Integration Sync',     'AIS-702', '2026-07-28T13:00:00', '2026-07-28T14:00:00', false),

    -- Robbe
    ('A8000000-0000-0000-0000-000000000001', 'Code Review Session',      'CRS-801', '2026-04-14T11:00:00', '2026-04-14T12:00:00', true),
    ('A8000000-0000-0000-0000-000000000002', 'Feature Kickoff',          'FKO-802', '2026-09-01T09:00:00', '2026-09-01T10:00:00', false),

    -- Tom
    ('A9000000-0000-0000-0000-000000000001', 'Performance Sync',         'PFS-901', '2026-02-24T14:00:00', '2026-02-24T15:00:00', true),
    ('A9000000-0000-0000-0000-000000000002', 'End-of-quarter Review',    'EQR-902', '2026-09-30T10:00:00', '2026-09-30T11:30:00', false),

    -- Ward
    ('AA000000-0000-0000-0000-000000000001', 'Product Alignment',        'PAL-A01', '2026-03-18T10:00:00', '2026-03-18T11:00:00', true),
    ('AA000000-0000-0000-0000-000000000002', 'Roadmap Discussion',       'RDM-A02', '2026-10-20T14:00:00', '2026-10-20T15:00:00', false),

    -- Yeung
    ('AB000000-0000-0000-0000-000000000001', 'Client Integration Sync',  'CIS-B01', '2026-04-01T09:00:00', '2026-04-01T10:00:00', true),
    ('AB000000-0000-0000-0000-000000000002', 'Sprint Demo',              'SDM-B02', '2026-08-05T13:00:00', '2026-08-05T14:00:00', false);

-- -------------------------------------------------------
-- ATTENDEES
-- -------------------------------------------------------

INSERT INTO attendees(role, meeting_id, user_id)
VALUES
    -- Keshia meetings
    ('MANAGER', 'A1000000-0000-0000-0000-000000000001', '00000000-0000-0000-0000-000000000001'),
    ('CONSULTANT', 'A1000000-0000-0000-0000-000000000001', '00000000-0000-0000-0000-000000000004'),
    ('MANAGER', 'A1000000-0000-0000-0000-000000000002', '00000000-0000-0000-0000-000000000001'),
    ('CONSULTANT', 'A1000000-0000-0000-0000-000000000002', '00000000-0000-0000-0000-000000000005'),
    ('MANAGER', 'A1000000-0000-0000-0000-000000000003', '00000000-0000-0000-0000-000000000001'),
    ('CONSULTANT', 'A1000000-0000-0000-0000-000000000003', '00000000-0000-0000-0000-000000000006'),

    -- Dries
    ('CONSULTANT', 'A2000000-0000-0000-0000-000000000001', '00000000-0000-0000-0000-000000000004'),
    ('MANAGER', 'A2000000-0000-0000-0000-000000000001', '5965a3ec-531c-4a2e-a443-4d7049a15549'),
    ('CONSULTANT', 'A2000000-0000-0000-0000-000000000002', '00000000-0000-0000-0000-000000000004'),
    ('MANAGER', 'A2000000-0000-0000-0000-000000000002', '00000000-0000-0000-0000-000000000001'),

    -- Bram
    ('CONSULTANT', 'A3000000-0000-0000-0000-000000000001', '00000000-0000-0000-0000-000000000005'),
    ('MANAGER', 'A3000000-0000-0000-0000-000000000001', '5965a3ec-531c-4a2e-a443-4d7049a15549'),
    ('CONSULTANT', 'A3000000-0000-0000-0000-000000000002', '00000000-0000-0000-0000-000000000005'),
    ('MANAGER', 'A3000000-0000-0000-0000-000000000002', '00000000-0000-0000-0000-000000000001'),

    -- Charlotte
    ('CONSULTANT', 'A4000000-0000-0000-0000-000000000001', '00000000-0000-0000-0000-000000000006'),
    ('MANAGER', 'A4000000-0000-0000-0000-000000000001', '00000000-0000-0000-0000-000000000001'),
    ('CONSULTANT', 'A4000000-0000-0000-0000-000000000002', '00000000-0000-0000-0000-000000000006'),
    ('MANAGER', 'A4000000-0000-0000-0000-000000000002', '5965a3ec-531c-4a2e-a443-4d7049a15549'),

    -- Dimitri
    ('CONSULTANT', 'A5000000-0000-0000-0000-000000000001', '00000000-0000-0000-0000-000000000007'),
    ('MANAGER', 'A5000000-0000-0000-0000-000000000001', '5965a3ec-531c-4a2e-a443-4d7049a15549'),
    ('CONSULTANT', 'A5000000-0000-0000-0000-000000000002', '00000000-0000-0000-0000-000000000007'),
    ('MANAGER', 'A5000000-0000-0000-0000-000000000002', '00000000-0000-0000-0000-000000000001'),

    -- Gunther
    ('CONSULTANT', 'A6000000-0000-0000-0000-000000000001', '00000000-0000-0000-0000-000000000008'),
    ('MANAGER', 'A6000000-0000-0000-0000-000000000001', '00000000-0000-0000-0000-000000000001'),
    ('CONSULTANT', 'A6000000-0000-0000-0000-000000000002', '00000000-0000-0000-0000-000000000008'),
    ('MANAGER', 'A6000000-0000-0000-0000-000000000002', '5965a3ec-531c-4a2e-a443-4d7049a15549'),

    -- Mathias
    ('MANAGER', 'A7000000-0000-0000-0000-000000000001', '00000000-0000-0000-0000-000000000009'),
    ('CONSULTANT', 'A7000000-0000-0000-0000-000000000001', '00000000-0000-0000-0000-000000000008'),
    ('MANAGER', 'A7000000-0000-0000-0000-000000000002', '00000000-0000-0000-0000-000000000009'),
    ('CONSULTANT', 'A7000000-0000-0000-0000-000000000002', '00000000-0000-0000-0000-000000000010'),

    -- Robbe
    ('CONSULTANT', 'A8000000-0000-0000-0000-000000000001', '00000000-0000-0000-0000-000000000010'),
    ('MANAGER', 'A8000000-0000-0000-0000-000000000001', '00000000-0000-0000-0000-000000000001'),
    ('CONSULTANT', 'A8000000-0000-0000-0000-000000000002', '00000000-0000-0000-0000-000000000010'),
    ('MANAGER', 'A8000000-0000-0000-0000-000000000002', '5965a3ec-531c-4a2e-a443-4d7049a15549'),

    -- Tom
    ('CONSULTANT', 'A9000000-0000-0000-0000-000000000001', '00000000-0000-0000-0000-000000000011'),
    ('MANAGER', 'A9000000-0000-0000-0000-000000000001', '5965a3ec-531c-4a2e-a443-4d7049a15549'),
    ('CONSULTANT', 'A9000000-0000-0000-0000-000000000002', '00000000-0000-0000-0000-000000000011'),
    ('MANAGER', 'A9000000-0000-0000-0000-000000000002', '00000000-0000-0000-0000-000000000001'),

    -- Ward
    ('CONSULTANT', 'AA000000-0000-0000-0000-000000000001', '00000000-0000-0000-0000-000000000012'),
    ('MANAGER', 'AA000000-0000-0000-0000-000000000001', '00000000-0000-0000-0000-000000000001'),
    ('CONSULTANT', 'AA000000-0000-0000-0000-000000000002', '00000000-0000-0000-0000-000000000012'),
    ('MANAGER', 'AA000000-0000-0000-0000-000000000002', '5965a3ec-531c-4a2e-a443-4d7049a15549'),

    -- Yeung
    ('CONSULTANT', 'AB000000-0000-0000-0000-000000000001', '00000000-0000-0000-0000-000000000013'),
    ('MANAGER', 'AB000000-0000-0000-0000-000000000001', '5965a3ec-531c-4a2e-a443-4d7049a15549'),
    ('CONSULTANT', 'AB000000-0000-0000-0000-000000000002', '00000000-0000-0000-0000-000000000013'),
    ('MANAGER', 'AB000000-0000-0000-0000-000000000002', '00000000-0000-0000-0000-000000000001');

-- -------------------------------------------------------
-- MEETING REQUESTS
-- -------------------------------------------------------

INSERT INTO meeting_requests(meeting_request_id, previous_request_id, name, reference_code, start_date_time,
                             end_date_time, recurrence_per_year, requested_by, is_allowed, status)
VALUES
    ('B1000000-0000-0000-0000-000000000001', NULL, 'Dries Check-in',          'DCI-001', '2026-07-10T10:00:00', '2026-07-10T11:00:00', 4,  '00000000-0000-0000-0000-000000000001', true, 'PENDING'),
    ('B2000000-0000-0000-0000-000000000001', NULL, 'Bram Sync',                'BRS-002', '2026-07-14T09:00:00', '2026-07-14T10:00:00', 4,  '00000000-0000-0000-0000-000000000001', true, 'ACCEPTED'),
    ('B3000000-0000-0000-0000-000000000001', NULL, 'Charlotte Review',         'CHR-003', '2026-08-01T14:00:00', '2026-08-01T15:00:00', 2,  '5965a3ec-531c-4a2e-a443-4d7049a15549', true, 'COUNTER_REQUESTED'),
    ('B3000000-0000-0000-0000-000000000002', 'B3000000-0000-0000-0000-000000000001', 'Charlotte Review', 'CHR-003', '2026-08-05T10:00:00', '2026-08-05T11:00:00', 2, '00000000-0000-0000-0000-000000000006', true, 'ACCEPTED'),
    ('B4000000-0000-0000-0000-000000000001', NULL, 'Dimitri Infra Sync',       'DIM-004', '2026-09-03T11:00:00', '2026-09-03T12:00:00', 1,  '5965a3ec-531c-4a2e-a443-4d7049a15549', true, 'DECLINED'),
    ('B5000000-0000-0000-0000-000000000001', NULL, 'Gunther Planning',         'GNT-005', '2026-06-25T13:00:00', '2026-06-25T14:00:00', 6,  '00000000-0000-0000-0000-000000000001', true, 'ACCEPTED'),
    ('B6000000-0000-0000-0000-000000000001', NULL, 'Mathias Backend Review',   'MBR-006', '2026-07-22T10:00:00', '2026-07-22T11:00:00', 4,  '5965a3ec-531c-4a2e-a443-4d7049a15549', true, 'PENDING'),
    ('B7000000-0000-0000-0000-000000000001', NULL, 'Robbe Code Review',        'RCR-007', '2026-08-19T09:00:00', '2026-08-19T10:00:00', 12, '00000000-0000-0000-0000-000000000001', true, 'ACCEPTED'),
    ('B8000000-0000-0000-0000-000000000001', NULL, 'Tom Performance Check',    'TPC-008', '2026-09-15T14:00:00', '2026-09-15T15:00:00', 2,  '5965a3ec-531c-4a2e-a443-4d7049a15549', true, 'COUNTER_REQUESTED'),
    ('B9000000-0000-0000-0000-000000000001', NULL, 'Ward Product Session',     'WPS-009', '2026-07-07T11:00:00', '2026-07-07T12:00:00', 4,  '00000000-0000-0000-0000-000000000001', true, 'ACCEPTED'),
    ('BA000000-0000-0000-0000-000000000001', NULL, 'Yeung Integration Sync',   'YIS-010', '2026-06-30T10:00:00', '2026-06-30T11:00:00', 4,  '5965a3ec-531c-4a2e-a443-4d7049a15549', true, 'PENDING');

INSERT INTO meeting_request_participants(meeting_request_id, user_id)
VALUES
    ('B1000000-0000-0000-0000-000000000001', '00000000-0000-0000-0000-000000000004'),
    ('B2000000-0000-0000-0000-000000000001', '00000000-0000-0000-0000-000000000005'),
    ('B3000000-0000-0000-0000-000000000001', '00000000-0000-0000-0000-000000000006'),
    ('B3000000-0000-0000-0000-000000000002', '00000000-0000-0000-0000-000000000006'),
    ('B4000000-0000-0000-0000-000000000001', '00000000-0000-0000-0000-000000000007'),
    ('B5000000-0000-0000-0000-000000000001', '00000000-0000-0000-0000-000000000008'),
    ('B6000000-0000-0000-0000-000000000001', '00000000-0000-0000-0000-000000000009'),
    ('B7000000-0000-0000-0000-000000000001', '00000000-0000-0000-0000-000000000010'),
    ('B8000000-0000-0000-0000-000000000001', '00000000-0000-0000-0000-000000000011'),
    ('B9000000-0000-0000-0000-000000000001', '00000000-0000-0000-0000-000000000012'),
    ('BA000000-0000-0000-0000-000000000001', '00000000-0000-0000-0000-000000000013');

-- -------------------------------------------------------
-- CONSULTANT CLIENT SCORES
-- -------------------------------------------------------

INSERT INTO consultant_client_scores(consultant_user_id, client_name, score, date_time)
VALUES
    -- Dries
    ('00000000-0000-0000-0000-000000000004', 'Siemens',    'SIX',   '2025-11-10T09:00:00'),
    ('00000000-0000-0000-0000-000000000004', 'Siemens',    'EIGHT', '2026-03-20T10:00:00'),

    -- Bram
    ('00000000-0000-0000-0000-000000000005', 'Proximus',   'SEVEN', '2025-10-05T14:00:00'),
    ('00000000-0000-0000-0000-000000000005', 'Proximus',   'NINE',  '2026-04-02T11:00:00'),

    -- Charlotte
    ('00000000-0000-0000-0000-000000000006', 'Colruyt',    'FIVE',  '2025-09-15T10:00:00'),
    ('00000000-0000-0000-0000-000000000006', 'Colruyt',    'SEVEN', '2026-02-28T09:30:00'),

    -- Dimitri
    ('00000000-0000-0000-0000-000000000007', 'Belfius',    'EIGHT', '2025-12-01T13:00:00'),
    ('00000000-0000-0000-0000-000000000007', 'Belfius',    'TEN',   '2026-05-10T10:00:00'),

    -- Gunther
    ('00000000-0000-0000-0000-000000000008', 'Engie',      'SIX',   '2025-08-20T11:00:00'),
    ('00000000-0000-0000-0000-000000000008', 'Engie',      'EIGHT', '2026-01-15T14:00:00'),

    -- Robbe
    ('00000000-0000-0000-0000-000000000010', 'Telenet',    'FOUR',  '2025-10-22T15:00:00'),
    ('00000000-0000-0000-0000-000000000010', 'Telenet',    'SIX',   '2026-04-18T10:00:00'),

    -- Tom
    ('00000000-0000-0000-0000-000000000011', 'ING',        'EIGHT', '2025-11-30T09:30:00'),
    ('00000000-0000-0000-0000-000000000011', 'ING',        'TEN',   '2026-05-01T13:00:00'),

    -- Ward
    ('00000000-0000-0000-0000-000000000012', 'KBC',        'SIX',   '2025-09-09T10:00:00'),
    ('00000000-0000-0000-0000-000000000012', 'KBC',        'EIGHT', '2026-02-14T11:00:00'),

    -- Yeung
    ('00000000-0000-0000-0000-000000000013', 'Delhaize',   'SEVEN', '2025-12-12T14:00:00'),
    ('00000000-0000-0000-0000-000000000013', 'Delhaize',   'NINE',  '2026-04-25T10:00:00');

-- -------------------------------------------------------
-- NOTES
-- -------------------------------------------------------

INSERT INTO notes(note_id, title, owner, visibility, meeting_id, description, creation_time)
VALUES
    -- Keshia (manager)
    ('C1000000-0000-0000-0000-000000000001', 'Team health overview',
     '00000000-0000-0000-0000-000000000001', 'PRIVATE', NULL,
     '{"ops":[{"insert":"Track overall morale and engagement across consultant portfolio"}]}',
     '2026-03-01T08:00:00'),
    ('C1000000-0000-0000-0000-000000000002', 'Onboarding checklist',
     '00000000-0000-0000-0000-000000000001', 'SHARED', NULL,
     '{"ops":[{"insert":"Items to cover during first week: tooling setup, team intro, client context"}]}',
     '2026-04-05T09:00:00'),

    -- Dries
    ('C2000000-0000-0000-0000-000000000001', 'Sprint notes week 18',
     '00000000-0000-0000-0000-000000000004', 'PRIVATE', NULL,
     '{"ops":[{"insert":"Focus on backend refactor and unit test coverage improvement"}]}',
     '2026-05-03T10:00:00'),
    ('C2000000-0000-0000-0000-000000000002', 'Client feedback log',
     '00000000-0000-0000-0000-000000000004', 'SHARED', NULL,
     '{"ops":[{"insert":"Siemens stakeholders happy with delivery pace, want more demos"}]}',
     '2026-05-10T11:00:00'),

    -- Bram
    ('C3000000-0000-0000-0000-000000000001', 'Architecture decisions',
     '00000000-0000-0000-0000-000000000005', 'PRIVATE', NULL,
     '{"ops":[{"insert":"Decided to move to event-driven pattern for async processing"}]}',
     '2026-04-12T14:00:00'),
    ('C3000000-0000-0000-0000-000000000002', 'Meeting recap - Tech Alignment',
     '00000000-0000-0000-0000-000000000005', 'MEETING_PARTICIPANTS', 'A3000000-0000-0000-0000-000000000001',
     '{"ops":[{"insert":"Agreed on API versioning strategy and deployment pipeline changes"}]}',
     '2026-04-08T13:00:00'),

    -- Charlotte
    ('C4000000-0000-0000-0000-000000000001', 'UX research findings',
     '00000000-0000-0000-0000-000000000006', 'SHARED', NULL,
     '{"ops":[{"insert":"Users prefer guided flows over open-ended forms. Reduce cognitive load."}]}',
     '2026-03-26T10:00:00'),
    ('C4000000-0000-0000-0000-000000000002', 'Personal dev goals',
     '00000000-0000-0000-0000-000000000006', 'PRIVATE', NULL,
     '{"ops":[{"insert":"Improve TypeScript skills and learn more about accessibility standards"}]}',
     '2026-05-01T09:00:00'),

    -- Dimitri
    ('C5000000-0000-0000-0000-000000000001', 'Infra incident notes',
     '00000000-0000-0000-0000-000000000007', 'PRIVATE', NULL,
     '{"ops":[{"insert":"Document root cause of DB timeout and mitigation steps applied"}]}',
     '2026-02-18T15:00:00'),
    ('C5000000-0000-0000-0000-000000000002', 'Security review recap',
     '00000000-0000-0000-0000-000000000007', 'SHARED', NULL,
     '{"ops":[{"insert":"Identified 3 open vulnerabilities. Patches scheduled for next sprint."}]}',
     '2026-05-11T10:30:00'),

    -- Gunther
    ('C6000000-0000-0000-0000-000000000001', 'Stakeholder action items',
     '00000000-0000-0000-0000-000000000008', 'SHARED', NULL,
     '{"ops":[{"insert":"Follow up on delivery timeline with Engie PMO by end of week"}]}',
     '2026-01-30T09:00:00'),
    ('C6000000-0000-0000-0000-000000000002', 'Lessons learned',
     '00000000-0000-0000-0000-000000000008', 'PRIVATE', NULL,
     '{"ops":[{"insert":"Better estimation needed. Buffer 20% for integration unknowns."}]}',
     '2026-04-22T16:00:00'),

    -- Mathias
    ('C7000000-0000-0000-0000-000000000001', 'API design notes',
     '00000000-0000-0000-0000-000000000009', 'PRIVATE', NULL,
     '{"ops":[{"insert":"REST vs GraphQL comparison for UCB data aggregation layer"}]}',
     '2026-03-04T11:00:00'),
    ('C7000000-0000-0000-0000-000000000002', 'Retrospective highlights',
     '00000000-0000-0000-0000-000000000009', 'SHARED', NULL,
     '{"ops":[{"insert":"Team velocity improved. CI pipeline now under 8 minutes."}]}',
     '2026-05-08T14:00:00'),

    -- Robbe
    ('C8000000-0000-0000-0000-000000000001', 'Code review comments',
     '00000000-0000-0000-0000-000000000010', 'MEETING_PARTICIPANTS', 'A8000000-0000-0000-0000-000000000001',
     '{"ops":[{"insert":"Focus on reducing cyclomatic complexity in service layer"}]}',
     '2026-04-14T13:00:00'),
    ('C8000000-0000-0000-0000-000000000002', 'Learning log',
     '00000000-0000-0000-0000-000000000010', 'PRIVATE', NULL,
     '{"ops":[{"insert":"Completed Kubernetes basics course. Next: advanced networking."}]}',
     '2026-05-06T10:00:00'),

    -- Tom
    ('C9000000-0000-0000-0000-000000000001', 'Performance goals recap',
     '00000000-0000-0000-0000-000000000011', 'SHARED', NULL,
     '{"ops":[{"insert":"ING team praised consistent delivery and proactive communication"}]}',
     '2026-02-25T09:00:00'),
    ('C9000000-0000-0000-0000-000000000002', 'End of quarter notes',
     '00000000-0000-0000-0000-000000000011', 'PRIVATE', NULL,
     '{"ops":[{"insert":"Review OKRs and set new targets with manager in upcoming sync"}]}',
     '2026-03-31T16:30:00'),

    -- Ward
    ('CA000000-0000-0000-0000-000000000001', 'Product roadmap input',
     '00000000-0000-0000-0000-000000000012', 'SHARED', NULL,
     '{"ops":[{"insert":"KBC wants reporting module prioritised in Q3. Align with tech lead."}]}',
     '2026-03-19T10:00:00'),
    ('CA000000-0000-0000-0000-000000000002', 'Self-reflection notes',
     '00000000-0000-0000-0000-000000000012', 'PRIVATE', NULL,
     '{"ops":[{"insert":"Need to improve stakeholder communication and over-delivery prevention"}]}',
     '2026-05-04T08:30:00'),

    -- Yeung
    ('CB000000-0000-0000-0000-000000000001', 'Integration testing log',
     '00000000-0000-0000-0000-000000000013', 'SHARED', NULL,
     '{"ops":[{"insert":"Covered 80% of Delhaize API endpoints with contract tests"}]}',
     '2026-04-02T11:00:00'),
    ('CB000000-0000-0000-0000-000000000002', 'Sprint demo prep',
     '00000000-0000-0000-0000-000000000013', 'PRIVATE', NULL,
     '{"ops":[{"insert":"Prepare live demo of checkout flow with fallback mock for unstable env"}]}',
     '2026-08-04T15:00:00');

-- -------------------------------------------------------
-- GOALS
-- -------------------------------------------------------

INSERT INTO goals(goal_id, user_id, title, description, creation_date, due_date, achievement_date)
VALUES
    -- Keshia
    ('D1000000-0000-0000-0000-000000000001', '00000000-0000-0000-0000-000000000001',
     'Complete manager onboarding program', 'Finish all internal management training modules',
     '2026-03-01', '2026-04-01', '2026-03-28'),
    ('D1000000-0000-0000-0000-000000000002', '00000000-0000-0000-0000-000000000001',
     'Build Q3 consultant review framework', 'Create structured review template for all consultants',
     '2026-05-01', '2026-06-30', NULL),

    -- Dries
    ('D2000000-0000-0000-0000-000000000001', '00000000-0000-0000-0000-000000000004',
     'Achieve 80% test coverage', 'Write unit and integration tests for Siemens project modules',
     '2026-04-01', '2026-05-31', NULL),
    ('D2000000-0000-0000-0000-000000000002', '00000000-0000-0000-0000-000000000004',
     'Complete Docker training', 'Finish containerisation and orchestration course',
     '2026-03-10', '2026-04-10', '2026-04-07'),

    -- Bram
    ('D3000000-0000-0000-0000-000000000001', '00000000-0000-0000-0000-000000000005',
     'Lead architecture decision record', 'Document and present ADR for event-driven migration',
     '2026-04-01', '2026-05-01', '2026-04-29'),
    ('D3000000-0000-0000-0000-000000000002', '00000000-0000-0000-0000-000000000005',
     'Mentor junior developer', 'Pair-program and review PRs for new team member',
     '2026-05-01', '2026-07-01', NULL),

    -- Charlotte
    ('D4000000-0000-0000-0000-000000000001', '00000000-0000-0000-0000-000000000006',
     'Complete WCAG accessibility audit', 'Audit Colruyt app for accessibility compliance',
     '2026-03-15', '2026-05-15', '2026-05-10'),
    ('D4000000-0000-0000-0000-000000000002', '00000000-0000-0000-0000-000000000006',
     'Build component library', 'Create reusable Storybook component set for design system',
     '2026-05-01', '2026-07-31', NULL),

    -- Dimitri
    ('D5000000-0000-0000-0000-000000000001', '00000000-0000-0000-0000-000000000007',
     'Obtain AWS Solutions Architect cert', 'Study and pass AWS SAA-C03 exam',
     '2026-01-10', '2026-04-10', '2026-04-05'),
    ('D5000000-0000-0000-0000-000000000002', '00000000-0000-0000-0000-000000000007',
     'Implement zero-downtime deployments', 'Set up blue/green deployment pipeline at Belfius',
     '2026-04-15', '2026-06-15', NULL),

    -- Gunther
    ('D6000000-0000-0000-0000-000000000001', '00000000-0000-0000-0000-000000000008',
     'Deliver Engie Phase 2 on time', 'Coordinate delivery of reporting module by deadline',
     '2026-01-20', '2026-03-31', '2026-03-28'),
    ('D6000000-0000-0000-0000-000000000002', '00000000-0000-0000-0000-000000000008',
     'Improve estimation accuracy', 'Reduce sprint commitment variance to under 10%',
     '2026-04-01', '2026-06-30', NULL),

    -- Mathias
    ('D7000000-0000-0000-0000-000000000001', '00000000-0000-0000-0000-000000000009',
     'Reduce API response time by 30%', 'Profile and optimise UCB data aggregation endpoints',
     '2026-02-01', '2026-04-01', '2026-03-30'),
    ('D7000000-0000-0000-0000-000000000002', '00000000-0000-0000-0000-000000000009',
     'Write tech blog post', 'Publish internal post on lessons learned from GraphQL migration',
     '2026-05-01', '2026-06-15', NULL),

    -- Robbe
    ('D8000000-0000-0000-0000-000000000001', '00000000-0000-0000-0000-000000000010',
     'Refactor legacy service layer', 'Break apart monolithic service into bounded contexts',
     '2026-03-01', '2026-05-01', '2026-04-25'),
    ('D8000000-0000-0000-0000-000000000002', '00000000-0000-0000-0000-000000000010',
     'Complete Kubernetes CKA prep', 'Study for CKA certification and take practice exams',
     '2026-05-01', '2026-08-01', NULL),

    -- Tom
    ('D9000000-0000-0000-0000-000000000001', '00000000-0000-0000-0000-000000000011',
     'Deliver ING mobile feature set', 'Complete and demo all 5 planned mobile features for Q2',
     '2026-03-01', '2026-06-30', NULL),
    ('D9000000-0000-0000-0000-000000000002', '00000000-0000-0000-0000-000000000011',
     'Improve client communication cadence', 'Weekly written updates to ING stakeholders',
     '2026-04-01', '2026-04-30', '2026-04-28'),

    -- Ward
    ('DA000000-0000-0000-0000-000000000001', '00000000-0000-0000-0000-000000000012',
     'Deliver KBC reporting module', 'Build and release reporting dashboard for KBC Q3',
     '2026-04-01', '2026-09-30', NULL),
    ('DA000000-0000-0000-0000-000000000002', '00000000-0000-0000-0000-000000000012',
     'Facilitate product discovery workshop', 'Run a 2-day discovery session with KBC product team',
     '2026-03-10', '2026-04-10', '2026-04-09'),

    -- Yeung
    ('DB000000-0000-0000-0000-000000000001', '00000000-0000-0000-0000-000000000013',
     'Reach 80% contract test coverage', 'Write consumer-driven contract tests for Delhaize APIs',
     '2026-03-15', '2026-05-15', '2026-05-12'),
    ('DB000000-0000-0000-0000-000000000002', '00000000-0000-0000-0000-000000000013',
     'Prepare Q3 sprint demo', 'Build polished demo of checkout flow with edge case handling',
     '2026-07-01', '2026-08-05', NULL);

-- ============================================================
-- ADDITIONAL MEETINGS FOR ABIR (1 manager + 1 consultant)
-- ============================================================

INSERT INTO meetings(meeting_id, name, reference_code, start_date_time, end_date_time, occurred)
VALUES
    ('F1000000-0000-0000-0000-000000000001', 'Strategy Sync',      'SSD-101', '2026-03-12T10:00:00', '2026-03-12T11:00:00', false),
    ('F1000000-0000-0000-0000-000000000002', 'Portfolio Review',    'PRB-102', '2026-04-18T13:00:00', '2026-04-18T14:00:00', true),
    ('F1000000-0000-0000-0000-000000000003', 'Growth Check-in','GCC-103', '2026-06-05T09:00:00', '2026-06-05T10:00:00', false),
    ('F1000000-0000-0000-0000-000000000004', 'Delivery Follow-up','DFD-104', '2026-07-22T15:00:00', '2026-07-22T16:00:00', false),
    ('F1000000-0000-0000-0000-000000000005', 'Engagement Sync',  'ESG-105', '2026-09-10T11:00:00', '2026-09-10T12:00:00', false),
    ('F1000000-0000-0000-0000-000000000006', 'Career Discussion',  'CDR-106', '2026-10-08T14:00:00', '2026-10-08T15:00:00', false),
    ('F1000000-0000-0000-0000-000000000007', 'Performance Review',   'PRT-107', '2026-11-05T09:00:00', '2026-11-05T10:00:00', false),
    ('F1000000-0000-0000-0000-000000000008', 'Client Feedback',    'CFY-108', '2026-12-03T13:00:00', '2026-12-03T14:00:00', false);

INSERT INTO attendees(role, meeting_id, user_id)
VALUES
    ('MANAGER',    'F1000000-0000-0000-0000-000000000001', '5965a3ec-531c-4a2e-a443-4d7049a15549'),
    ('CONSULTANT', 'F1000000-0000-0000-0000-000000000001', '00000000-0000-0000-0000-000000000004'),

    ('MANAGER',    'F1000000-0000-0000-0000-000000000002', '5965a3ec-531c-4a2e-a443-4d7049a15549'),
    ('CONSULTANT', 'F1000000-0000-0000-0000-000000000002', '00000000-0000-0000-0000-000000000005'),

    ('MANAGER',    'F1000000-0000-0000-0000-000000000003', '5965a3ec-531c-4a2e-a443-4d7049a15549'),
    ('CONSULTANT', 'F1000000-0000-0000-0000-000000000003', '00000000-0000-0000-0000-000000000006'),

    ('MANAGER',    'F1000000-0000-0000-0000-000000000004', '5965a3ec-531c-4a2e-a443-4d7049a15549'),
    ('CONSULTANT', 'F1000000-0000-0000-0000-000000000004', '00000000-0000-0000-0000-000000000007'),

    ('MANAGER',    'F1000000-0000-0000-0000-000000000005', '5965a3ec-531c-4a2e-a443-4d7049a15549'),
    ('CONSULTANT', 'F1000000-0000-0000-0000-000000000005', '00000000-0000-0000-0000-000000000008'),

    ('MANAGER',    'F1000000-0000-0000-0000-000000000006', '5965a3ec-531c-4a2e-a443-4d7049a15549'),
    ('CONSULTANT', 'F1000000-0000-0000-0000-000000000006', '00000000-0000-0000-0000-000000000010'),

    ('MANAGER',    'F1000000-0000-0000-0000-000000000007', '5965a3ec-531c-4a2e-a443-4d7049a15549'),
    ('CONSULTANT', 'F1000000-0000-0000-0000-000000000007', '00000000-0000-0000-0000-000000000011'),

    ('MANAGER',    'F1000000-0000-0000-0000-000000000008', '5965a3ec-531c-4a2e-a443-4d7049a15549'),
    ('CONSULTANT', 'F1000000-0000-0000-0000-000000000008', '00000000-0000-0000-0000-000000000013');

INSERT INTO meetings(meeting_id, name, reference_code, start_date_time, end_date_time, occurred)
VALUES
    ('F1000000-0000-0000-0000-000000000009', 'Quarterly Alignment',     'QAJ-109', '2027-01-14T10:00:00', '2027-01-14T11:00:00', false),
    ('F1000000-0000-0000-0000-000000000010', 'Skills Review',           'SRL-110', '2027-01-28T14:00:00', '2027-01-28T15:00:00', false),
    ('F1000000-0000-0000-0000-000000000011', 'Client Readiness',         'CRN-111', '2027-02-11T09:00:00', '2027-02-11T10:00:00', false),
    ('F1000000-0000-0000-0000-000000000012', 'Project Feedback',         'PFE-112', '2027-02-25T13:00:00', '2027-02-25T14:00:00', false),
    ('F1000000-0000-0000-0000-000000000013', 'Career Coaching',          'CCW-113', '2027-03-11T15:00:00', '2027-03-11T16:00:00', false),
    ('F1000000-0000-0000-0000-000000000014', 'Delivery Assessment',     'DAS-114', '2027-03-25T10:00:00', '2027-03-25T11:00:00', false),
    ('F1000000-0000-0000-0000-000000000015', 'Performance Check',      'PCV-115', '2027-04-08T11:00:00', '2027-04-08T12:00:00', false),
    ('F1000000-0000-0000-0000-000000000016', 'Growth Planning',          'GPA-116', '2027-04-22T14:00:00', '2027-04-22T15:00:00', false),
    ('F1000000-0000-0000-0000-000000000017', 'Mentorship Session',     'MSJ-117', '2027-05-06T09:00:00', '2027-05-06T10:00:00', false),
    ('F1000000-0000-0000-0000-000000000018', 'Development Review',     'DRP-118', '2027-05-20T13:00:00', '2027-05-20T14:00:00', false);

INSERT INTO attendees(role, meeting_id, user_id)
VALUES
    ('MANAGER',    'F1000000-0000-0000-0000-000000000009', '5965a3ec-531c-4a2e-a443-4d7049a15549'),
    ('CONSULTANT', 'F1000000-0000-0000-0000-000000000009', '00000000-0000-0000-0000-000000000013'),

    ('MANAGER',    'F1000000-0000-0000-0000-000000000010', '5965a3ec-531c-4a2e-a443-4d7049a15549'),
    ('CONSULTANT', 'F1000000-0000-0000-0000-000000000010', '00000000-0000-0000-0000-000000000012'),

    ('MANAGER',    'F1000000-0000-0000-0000-000000000011', '5965a3ec-531c-4a2e-a443-4d7049a15549'),
    ('CONSULTANT', 'F1000000-0000-0000-0000-000000000011', '00000000-0000-0000-0000-000000000011'),

    ('MANAGER',    'F1000000-0000-0000-0000-000000000012', '5965a3ec-531c-4a2e-a443-4d7049a15549'),
    ('CONSULTANT', 'F1000000-0000-0000-0000-000000000012', '00000000-0000-0000-0000-000000000010'),

    ('MANAGER',    'F1000000-0000-0000-0000-000000000013', '5965a3ec-531c-4a2e-a443-4d7049a15549'),
    ('CONSULTANT', 'F1000000-0000-0000-0000-000000000013', '00000000-0000-0000-0000-000000000008'),

    ('MANAGER',    'F1000000-0000-0000-0000-000000000014', '5965a3ec-531c-4a2e-a443-4d7049a15549'),
    ('CONSULTANT', 'F1000000-0000-0000-0000-000000000014', '00000000-0000-0000-0000-000000000007'),

    ('MANAGER',    'F1000000-0000-0000-0000-000000000015', '5965a3ec-531c-4a2e-a443-4d7049a15549'),
    ('CONSULTANT', 'F1000000-0000-0000-0000-000000000015', '00000000-0000-0000-0000-000000000006'),

    ('MANAGER',    'F1000000-0000-0000-0000-000000000016', '5965a3ec-531c-4a2e-a443-4d7049a15549'),
    ('CONSULTANT', 'F1000000-0000-0000-0000-000000000016', '00000000-0000-0000-0000-000000000005'),

    ('MANAGER',    'F1000000-0000-0000-0000-000000000017', '5965a3ec-531c-4a2e-a443-4d7049a15549'),
    ('CONSULTANT', 'F1000000-0000-0000-0000-000000000017', '00000000-0000-0000-0000-000000000004'),

    ('MANAGER',    'F1000000-0000-0000-0000-000000000018', '5965a3ec-531c-4a2e-a443-4d7049a15549'),
    ('CONSULTANT', 'F1000000-0000-0000-0000-000000000018', '00000000-0000-0000-0000-000000000003');