create table benutzer
(
    id   uuid,
    name varchar(255),
    primary key (id)
);

create table plan
(
    id        uuid,
    woche     varchar(255),
    wochentag varchar(255),
    datum     date,
    wald      boolean,
    start     time,
    ende      time,
    abfahrt   time,
    primary key (id)
);

create table benutzer_plan
(
    benutzer_id uuid,
    plan_id     uuid,
    primary key (benutzer_id, plan_id),
    foreign key (benutzer_id) references benutzer (id),
    foreign key (plan_id) references plan (id)
);
