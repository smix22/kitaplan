create table plan
(
    id        uuid    DEFAULT uuid_generate_v4(),
    woche     varchar(255),
    datum     date,
    wald      boolean DEFAULT true,
    offen     boolean DEFAULT true,
    start     time    DEFAULT '08:00:00' NOT NULL,
    ende      time    DEFAULT '16:30:00' NOT NULL,
    abfahrt   time    DEFAULT '09:00:00' NOT NULL,
    kommentar varchar(255),
    primary key (id)
);
