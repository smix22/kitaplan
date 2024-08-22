create table benutzer
(
    id   uuid,
    name varchar(255),
    primary key (id)
);

create table plan
(
    id        uuid    DEFAULT uuid_generate_v4(),
    woche     varchar(255),
    datum     date,
    wald      boolean DEFAULT true,
    offen     boolean DEFAULT true,
    start     time    DEFAULT '08:00:00' NOT NULL,
    ende      time    DEFAULT '16:30:00' NOT NULL,
    abfahrt   time    DEFAULT '08:30:00' NOT NULL,
    kommentar varchar(255),
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
