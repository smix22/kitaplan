create table benutzer
(
    id    uuid,
    name  varchar(255),
    rolle varchar(255),
    primary key (id)
);

create table plan
(
    id    uuid,
    tag   varchar(255),
    datum date,
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
