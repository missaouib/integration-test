CREATE TABLE scheduler
(
  id       serial              NOT NULL,
  name     varchar(255)        NOT NULL,
  PRIMARY KEY (id)
);

comment on table scheduler
  is 'Scheduler that runs to create tickets in wekan';

create index scheduler_name_index
  on scheduler (name);