insert into benutzer(id, name, rolle)
values ('56a20397-c953-4e3b-b033-1a9a1e574455', 'Nasti', 'admin'),
       ('56a20397-c953-4e3b-b033-2a9a1e574455', 'Beate', 'admin'),
       ('56a20397-c953-4e3b-b033-3a9a1e574455', 'Stefan', 'admin');

insert into plan(id, tag, datum)
values ('15c4f07c-f4db-4d6f-bf4c-a52f49a1bd63', 'Montag',
        '05-08-2024'),
       ('25c4f07c-f4db-4d6f-bf4c-a52f49a1bd63', 'Dienstag',
        '06-08-2024'),
       ('35c4f07c-f4db-4d6f-bf4c-a52f49a1bd63', 'Mittwoch',
        '07-08-2024'),
       ('45c4f07c-f4db-4d6f-bf4c-a52f49a1bd63', 'Donnerstag',
        '08-08-2024'),
       ('55c4f07c-f4db-4d6f-bf4c-a52f49a1bd63', 'Freitag',
        '09-08-2024');


insert into benutzer_plan(benutzer_id, plan_id)
values ('56a20397-c953-4e3b-b033-1a9a1e574455', '15c4f07c-f4db-4d6f-bf4c-a52f49a1bd63'),
       ('56a20397-c953-4e3b-b033-2a9a1e574455', '15c4f07c-f4db-4d6f-bf4c-a52f49a1bd63'),
       ('56a20397-c953-4e3b-b033-3a9a1e574455', '15c4f07c-f4db-4d6f-bf4c-a52f49a1bd63'),
       ('56a20397-c953-4e3b-b033-1a9a1e574455', '25c4f07c-f4db-4d6f-bf4c-a52f49a1bd63'),
       ('56a20397-c953-4e3b-b033-1a9a1e574455', '35c4f07c-f4db-4d6f-bf4c-a52f49a1bd63'),
       ('56a20397-c953-4e3b-b033-1a9a1e574455', '45c4f07c-f4db-4d6f-bf4c-a52f49a1bd63'),
       ('56a20397-c953-4e3b-b033-1a9a1e574455', '55c4f07c-f4db-4d6f-bf4c-a52f49a1bd63');