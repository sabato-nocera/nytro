use piattaforma_videogiochi_tsw;
insert into Account
	values 	('mariorossi91', 'e4187f73b067afa42e5d24245e770c65a6038927', 'mariorossi@gmail.com', 'mariorecupero@gmail.com', NULL, '2011-3-17', current_time(), '123.456.23.12', 1, NULL),
			('luigiverdi94', '467ad0cf7138731654845641704339e98dda6dc1', 'luigiverdi@gmail.com', 'luigirecupero@gmail.com', '3391234567', '2017-4-8', current_time(), '132.645.32.21', 1, NULL),
			('lucagialli01', '187fe79300c9e68c78d480ab1c5646beea964e2d', 'lucagialli@gmail.com', 'lucarecupero@gmail.com', NULL, '2016-7-4', current_time(), '456.123.34.78', 1, NULL),
            ('mariablu98', 'e175251395aee723faa2497c0cf63e4f6a28ca29', 'mariablu@gmail.com', 'mariarecupero@gmail.com', '3274569876', '2013-5-26', current_time(), '345.123.34.78', 1, NULL),
            ('rosanera89', 'c1be695ccac688780d3d3d589fbfe681b2817f1f', 'rosanera@gmail.com', 'rosarecupero@gmail.com', NULL, '2011-2-16', current_time(), '454.657.12.46', 1, NULL),
            ('ubisoft', '40506a4d3134729396b8a3fa8f5db9878629924c', 'manager@ubisoft.com', 'recovery@ubisoft.com', NULL, '2015-4-23', current_time(), '358.342.79.69', 2, NULL),
            ('nintendo', '0c644c07df798e30c5b4b3f377e97d08cbf1e54f', 'manager@nintendo.com', 'recovery@nintendo.com', NULL, '2013-5-3', current_time(), '863.467.23.79', 2, NULL),
            ('tobyfox', 'e6de9316f107d2895e016238d166436162a03079', 'tobyfox@gmail.com', 'tobyrecovery@gmail.com', NULL, '2014-8-2', current_time(), '456.388.16.47', 2, NULL),
            ('annaarancio97', 'c80781a657f12f990e936c85a458b6c9768c22d2', 'annaarancia@gmail.com', 'annarecupero@gmail.com', '3478659326', '2014-7-3', current_time(), '878.246.37.12', 1, NULL),
            ('squareenix', '777048093c9dd4cebf3f4ee960dc30093d30c760', 'manager@squareenix.com', 'recovery@squareenix.com', NULL, '2012-5-27', current_time(), '567.437.89.320', 2, NULL),
            ('freebirdgames', 'fda7cad7975c96f23753413c041ddafc527e9d8c', 'freebird@gmail.com', 'fbrecovery@gmail.com', NULL, '2011-6-3', current_time(), '943.356.78.1', 2, NULL),
            ('admin', '92d335368fdfc4153b791af1969fba0da4f95b04', 'siteadmin@gmail.com', 'adminrecupero@gmail.com', NULL, '2019-5-17', current_time(), '457.257.1.12', 0, NULL);
insert into Giocatore
	values	('mariorossi91', '1991-05-12', current_date(), 'M'),
			('luigiverdi94', '1994-08-02', current_date(), 'M'),
			('lucagialli01', '2006-10-23', current_date(), 'M'),
			('mariablu98', '1998-02-26', current_date(), 'F'),
			('rosanera89', NULL, current_date(), 'F'),
            ('annaarancio97', '1997-09-17', current_date(), 'F');			
insert into Casa_Editrice
	values	('123456789012', 'Yves Guillemot', 'www.ubisoft.com'),
			('210987654321',  'Shuntaro Furukawa', 'www.nintendo.com'),
            ('453657688853', 'Toby Fox', 'www.twitter.com/tobyfox'),
            ('237648450745', 'Yosuke Matsuda', 'www.square-enix.com'),
            ('675648205634', 'Kan Gao', 'www.freebirdgames.com');
insert into Amministratore
	values	('admin', 'Veronica', 'Volpicelli');
insert into Azienda
	values 	('ubisoft', '123456789012', 'Ubisoft'),
			('nintendo', '210987654321', 'Nintendo'),
            ('tobyfox', '453657688853', 'Toby Fox'),
            ('squareenix', '237648450745', 'Square Enix'),
            ('freebirdgames', '675648205634', 'Freebird Games');
insert into Carta_Di_Pagamento
	values	('5888736213861112', 'luigiverdi94'),
			('211108952362935', 'rosanera89'),
            ('3598759864360245', 'lucagialli01');
insert into Indie
	values	('453657688853', 1),
			('675648205634', 4);
insert into Holding
	values	('123456789012', '1986-03-12', 'Montreuil, Francia'),
			('210987654321', '1889-09-23', 'Kyoto, Giappone'),
            ('237648450745', '1975-09-22', 'Tokyo, Giappone');
insert into Videogioco (ISIN, Data_Rilascio, Data_Rimozione, Titolo, Voto_Medio, PEGI, Trailer, Prezzo, Copie_Vendute)
	values	('123456789012', '2017-10-27', null, 'Assassin\'s Creed: Origins', 4, 18, 'https://www.youtube.com/embed/s_SJZSAtLBA', 69.99, 1),
			('210987654321', '2018-12-07', null, 'Super Smash Bros. Ultimate', 4.5, 12, 'https://www.youtube.com/embed/TUzZKLirkrE', 69.99, 2),
            ('237648450745', '2013-08-24', null, 'Final Fantasy XIV', 4, 14, 'https://www.youtube.com/embed/w7eH0mqK3To', 19.99, 2),
            ('675648205634', '2011-11-1', null, 'To The Moon', 5, 12, 'https://www.youtube.com/embed/sqkJuSV-23U', 9.99, 3),
            ('675648205634', '2017-12-14', null, 'Finding Paradise', 4.25, 12, 'https://www.youtube.com/embed/ntSOXMwil7A', 9.99, 1);
insert into Genere
	values	('Avventura', 1),
            ('Stealth', 1),
			('Picchiaduro', 2),
            ('RPG', 4),
            ('RPG', 5),
			('MMORPG', 3);
insert into Console
	values	('PS4', 1),
			('Xbox One', 1),
			('Xbox Series X', 1),
            ('PS5', 1),
			('PC', 1),
            ('Nintendo Switch', 2),
			('PC', 3),
			('PS4', 3),
            ('PC', 4),
            ('PC', 5);
insert into Recensione
	values	(1, 4, 'rosanera89', 'Storia stupenda, gioco che consiglio a tutti.', 5),
			(1, 1, 'mariorossi91', '', 4),
			(1, 2, 'lucagialli01', 'Molto divertente in multiplayer.', 4.5),
			(1, 3, 'mariablu98', 'Potrebbe essere gestito meglio, ma non male.', 3.5),
			(1, 5, 'luigiverdi94', 'Molto godibile anche senza aver giocato al primo.', 4),
			(2, 5, 'rosanera89', 'Stessa casa di To the moon, una garanzia.', 4.5),
			(2, 4, 'annaarancio97', 'I videogiochi indie sono quelli con le storie migliori.', 5),
			(2, 3, 'lucagialli01', '', 4.5);
insert into e_nella_friendlist
	values	('mariorossi91', 'luigiverdi94'),
			('luigiverdi94', 'mariorossi91'),
			('mariorossi91', 'lucagialli01'),
			('lucagialli01', 'mariorossi91'),
			('rosanera89', 'luigiverdi94'),
			('luigiverdi94', 'rosanera89'),
			('annaarancio97', 'lucagialli01'),
			('lucagialli01', 'annaarancio97'),
			('mariablu98', 'mariorossi91'),
            ('mariorossi91', 'mariablu98');
insert into ha_nella_libreria
	values 	('rosanera89', 4, 21),
			('rosanera89', 5, 28),
			('mariorossi91', 1, 37),
			('lucagialli01', 3, 41),
			('luigiverdi94', 4, 12),
			('annaarancio97', 4, 23),
			('mariablu98', 3, 45),
			('mariorossi91', 2, 24);
insert into acquista
	values	('mariorossi91', 1, '463837465812345', 59.99, current_time(), '2017-10-30', '123.456.23.12', 'PS4', 'JBo6j82slEbKzc6Z'),
			('mariorossi91', 2, '463837465812345', 69.99, current_time(), '2018-12-10', '123.456.23.12', 'Nintendo Switch', 't1Bfry3To2YCXQTD'),
			('rosanera89', 4, '211108952362935', 9.99, current_time(), '2013-8-18', '345.123.34.78', 'PC', 'ZyFdGibtU2GMVjHN'),
            ('rosanera89', 5, '211108952362935', 9.99, current_time(), '2017-12-14', '345.123.34.78', 'PC', 'TvDAUWH1KkmtTSWc'),
            ('lucagialli01', 2, '3598759864360245', 69.99, current_time(), '2019-1-4', '456.123.34.78', 'Nintendo Switch', 'nKQdxcEGMKg4KVZR'),
            ('lucagialli01', 3, '3598759864360245', 69.99, current_time(), '2016-12-25', '456.123.34.78', 'PS4', 'o6Ul1SrcGJgFMkRe'),
            ('luigiverdi94', 4, '5888736213861112', 9.99, current_time(), '2018-3-21', '132.645.32.21', 'PC', 'DJvvIrpWSDGw2Yc5'),
            ('annaarancio97', 4, '474564780234657', 9.99, current_time(), '2015-6-2', '878.246.37.12', 'PC', '9DvjSGAmQJH4TX6J'),
            ('mariorossi91', 1, '463837465812345',59.99, current_time(), '2017-10-30', '123.456.23.12', 'PS5', 'JBo6j82slEbKzc6A'),
            ('mariablu98', 3, '93475829045748', 19.99, current_time(), '2015-11-27', '345.123.34.78', 'PS4', 'gmbIq8osEoHbDnTw');