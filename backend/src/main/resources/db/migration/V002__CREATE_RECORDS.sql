insert into benutzer(id, name)
values ('56a20397-c953-4e3b-b033-1a9a1e574455', 'Nasti'),
       ('56a20397-c953-4e3b-b033-2a9a1e574455', 'Beate'),
       ('56a20397-c953-4e3b-b033-3a9a1e574455', 'Stefan');

insert into plan(id, woche, wochentag, datum, wald, start, ende, abfahrt)
values ('15c4f07c-f4db-4d6f-bf4c-a52f49a1bd63', '2024-33', 'Montag',
        '2024-08-12', TRUE, '08:00:00', '18:00:00', '08:30:00'),
       ('25c4f07c-f4db-4d6f-bf4c-a52f49a1bd63', '2024-33', 'Dienstag',
        '2024-08-13', TRUE, '08:00:00', '18:00:00', '08:30:00'),
       ('35c4f07c-f4db-4d6f-bf4c-a52f49a1bd63', '2024-33', 'Mittwoch',
        '2024-08-14', TRUE, '08:00:00', '18:00:00', '08:30:00'),
       ('45c4f07c-f4db-4d6f-bf4c-a52f49a1bd63', '2024-33', 'Donnerstag',
        '2024-08-15', TRUE, '08:00:00', '18:00:00', '08:30:00'),
       ('55c4f07c-f4db-4d6f-bf4c-a52f49a1bd63', '2024-33', 'Freitag',
        '2024-08-16', TRUE, '08:00:00', '18:00:00', '08:30:00');

insert into benutzer_plan(benutzer_id, plan_id)
values ('56a20397-c953-4e3b-b033-1a9a1e574455', '15c4f07c-f4db-4d6f-bf4c-a52f49a1bd63'),
       ('56a20397-c953-4e3b-b033-2a9a1e574455', '15c4f07c-f4db-4d6f-bf4c-a52f49a1bd63'),
       ('56a20397-c953-4e3b-b033-3a9a1e574455', '15c4f07c-f4db-4d6f-bf4c-a52f49a1bd63'),
       ('56a20397-c953-4e3b-b033-1a9a1e574455', '25c4f07c-f4db-4d6f-bf4c-a52f49a1bd63'),
       ('56a20397-c953-4e3b-b033-1a9a1e574455', '35c4f07c-f4db-4d6f-bf4c-a52f49a1bd63'),
       ('56a20397-c953-4e3b-b033-1a9a1e574455', '45c4f07c-f4db-4d6f-bf4c-a52f49a1bd63'),
       ('56a20397-c953-4e3b-b033-1a9a1e574455', '55c4f07c-f4db-4d6f-bf4c-a52f49a1bd63');