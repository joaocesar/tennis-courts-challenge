insert into guest(id, name) values(null, 'Roger Federer');
insert into guest(id, name) values(null, 'Rafael Nadal');

insert into tennis_court(id, name) values(null, 'Roland Garros - Court Philippe-Chatrier');
insert into tennis_court(id, name) values(null, 'Roland Garros - Court Suzanne Lenglen');
insert into tennis_court(id, name) values(null, 'Roland Garros - Court Simonne Mathieu');

insert into schedule (id, start_date_time, end_date_time, tennis_court_id) values (null, '2021-09-09T08:00:00.0', '2021-09-09T09:00:00.0', 1);
insert into schedule (id, start_date_time, end_date_time, tennis_court_id) values (null, '2021-09-09T09:00:00.0', '2021-09-09T10:00:00.0', 1);
insert into schedule (id, start_date_time, end_date_time, tennis_court_id) values (null, '2021-09-09T10:00:00.0', '2021-09-09T11:00:00.0', 1);

insert into schedule (id, start_date_time, end_date_time, tennis_court_id) values (null, '2021-09-09T08:00:00.0', '2021-09-09T09:00:00.0', 2);
insert into schedule (id, start_date_time, end_date_time, tennis_court_id) values (null, '2021-09-09T09:00:00.0', '2021-09-09T10:00:00.0', 2);
insert into schedule (id, start_date_time, end_date_time, tennis_court_id) values (null, '2021-09-09T10:00:00.0', '2021-09-09T11:00:00.0', 2);

insert into schedule (id, start_date_time, end_date_time, tennis_court_id) values (null, '2021-09-09T08:00:00.0', '2021-09-09T09:00:00.0', 3);
insert into schedule (id, start_date_time, end_date_time, tennis_court_id) values (null, '2021-09-09T09:00:00.0', '2021-09-09T10:00:00.0', 3);
insert into schedule (id, start_date_time, end_date_time, tennis_court_id) values (null, '2021-09-09T10:00:00.0', '2021-09-09T11:00:00.0', 3);
