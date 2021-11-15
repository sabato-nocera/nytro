drop database if exists piattaforma_videogiochi_tsw;
create database piattaforma_videogiochi_tsw;
use piattaforma_videogiochi_tsw;
create table Account (
	Username		varchar(20)	not null,
	Password		varchar(50)	not null,
	Email			varchar(35)	not null,
    Email_Recupero	varchar(35)	not null,
    Cellulare		char(10),
    Data			date		not null,
    Ora				time		not null,
    IP				varchar(20)	not null,
    Ruolo			smallint 	not null,
    Img_Profilo		mediumblob,
    primary key (Username)
    );
create table Casa_Editrice (
	ISIN		char(12)	not null,
    CEO			varchar(30)	not null,
    Sito_Web	varchar(30)	not null,
    primary key (ISIN)
    );
create table Giocatore (
	Username 		varchar(20)		not null,
    Data_Nascita	date,
    Data_Iscrizione	date			not null,
    Genere			enum('m', 'f'),
    primary key (Username),
    foreign key (Username) references Account (Username)
		on delete cascade	on update cascade
	);
create table Azienda (
	Username			varchar(20)		not null,
    ISIN				char(12)		not null,
    Nome_Casa_Editrice	varchar(30)		not null,
    primary key (Username),
    foreign key (Username) references Account (Username)
		on delete cascade	on update cascade,
	foreign key	(ISIN) references Casa_Editrice (ISIN)
		on delete cascade	on update cascade
	);
create table Amministratore (
	Username			varchar(20)		not null,
    Nome				char(30)		not null,
    Cognome				varchar(30)		not null,
    primary key (Username),
    foreign key (Username) references Account (Username)
		on delete cascade	on update cascade
	);
create table Carta_Di_Pagamento (
	Numero_Carta	varchar(16)	not null,
    Username		varchar(20)	not null,
    primary key (Numero_Carta, Username),
    foreign key (Username) references Giocatore (Username)
		on delete cascade	on update cascade
	);
create table Indie (
	ISIN				char(12)	not null,
    Numero_Sviluppatori	int			not null,
    primary key (ISIN),
    foreign key (ISIN)	references Casa_Editrice (ISIN)
		on delete cascade	on update cascade
	);
create table Holding (
	ISIN			char(12)	not null,
    Data_Fondazione	date		not null,
    Sede			varchar(30)	not null,
    primary key (ISIN),
    foreign key (ISIN) references Casa_Editrice (ISIN)
		on delete cascade	on update cascade
	);
create table Videogioco (
	Codice			int			not null	auto_increment,
    ISIN			char(12)	not null,
    Data_Rilascio	date		not null,
	Data_Rimozione	date,
    Titolo			varchar(35)	not null,
    Voto_Medio		float		not null	default 0.00,
    PEGI			smallint	not null,
    Img				mediumblob,
    Trailer			varchar(50),
    Prezzo			float	not null,
    Copie_Vendute	int		not null	default 0,
    primary key (Codice),
    foreign key (ISIN) references Casa_Editrice (ISIN)
		on delete cascade	on update cascade
	);
create table Genere (
	Nome	varchar(15)		not null,
    Videogioco	int			not null,
    primary key (Nome, Videogioco),
    foreign key (Videogioco) references Videogioco (Codice)
		on delete cascade	on update cascade
	);
create table Console (
                        Nome_Console	varchar(15)		not null,
                        Videogioco	    int			    not null,
                        primary key (Nome_Console, Videogioco),
                        foreign key (Videogioco) references Videogioco (Codice)
                            on delete cascade	on update cascade
);
create table Recensione (
	Num_Recensione	int				not null,
    Videogioco		int				not null,
    Username		varchar(20)		not null,
    Commento		varchar(200)	not null,
    Voto			float			not null,
    primary key (Num_Recensione, Videogioco),
    foreign key (Videogioco) references Videogioco (Codice)
		on delete cascade	on update cascade,
	foreign key (Username) references Giocatore (Username)
		on delete cascade	on update cascade
	);
create table E_NELLA_FRIENDLIST (
	Possessore	varchar(20)	not null,
    Amico	varchar(20)	not null,
    primary key (Possessore, Amico),
    foreign key (Possessore) references Giocatore (Username)
		on delete cascade	on update cascade,
	foreign key (Amico) references Giocatore (Username)
		on delete cascade	on update cascade
	);
create table HA_NELLA_LIBRERIA (
	Username		varchar(20)	not null,
    Videogioco		int			not null,
    Ore_Di_Gioco	int			not null	default 0,
    primary key	(Username, Videogioco),
    foreign key (Username) references Giocatore (Username)
		on delete cascade	on update cascade,
	foreign key (Videogioco) references Videogioco (Codice)
		on delete cascade	on update cascade
	);
create table ACQUISTA (
	Username			varchar(20)	not null,
    Videogioco			int			not null,
    Carta_Pagamento		varchar(16)	not null,
	Prezzo_Di_Acquisto	float		not null,
    Ora					time		not null,
    Data				date		not null,
    IP					varchar(20)	not null,
    Nome_Console	    varchar(15)	not null,
    Codice_Download     varchar(16)	not null,
    primary key (Username, Videogioco, Nome_Console),
    foreign key (Username) references Giocatore (Username)
		on delete cascade	on update cascade,
	foreign key (Videogioco) references Console (Videogioco)
		on delete cascade	on update cascade,
	foreign key (Nome_Console) references Console (Nome_Console)
		on delete cascade	on update cascade
	);

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

/*!50003 DROP FUNCTION IF EXISTS `calcola_k` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `calcola_k`(a_ISIN char(12)) RETURNS float
    READS SQL DATA
BEGIN
RETURN (SELECT IF(indie.ISIN IS NOT NULL,
                  3 + POW(casa_editrice.numero_giochi_pubblicati + indie.numero_sviluppatori, 1/3),
                  10 + POW(casa_editrice.numero_giochi_pubblicati, 1/3))
        FROM casa_editrice
                 LEFT JOIN indie ON indie.ISIN = casa_editrice.ISIN
                 LEFT JOIN holding ON holding.ISIN = casa_editrice.ISIN
        WHERE casa_editrice.ISIN = a_ISIN
        GROUP BY casa_editrice.ISIN);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `calcola_k2` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `calcola_k2`(a_ISIN char(12), a_data_inizio Date, a_data_fine Date) RETURNS float
    READS SQL DATA
BEGIN
SET @numero = (SELECT COUNT(*) FROM videogioco WHERE ISIN = a_ISIN AND videogioco.Data_Rilascio <= a_data_fine
	AND (videogioco.data_rimozione >= a_data_inizio OR videogioco.data_rimozione IS NULL));
RETURN (SELECT IF(indie.ISIN IS NOT NULL,
                  3 + POW(@numero + indie.numero_sviluppatori, 1/3),
                  10 + POW(@numero, 1/3))
        FROM casa_editrice
                 LEFT JOIN indie ON indie.ISIN = casa_editrice.ISIN
                 LEFT JOIN holding ON holding.ISIN = casa_editrice.ISIN
        WHERE casa_editrice.ISIN = a_ISIN
        GROUP BY casa_editrice.ISIN);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `acquista_videogioco` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `acquista_videogioco`(IN a_username char(20), IN a_codice int, IN a_carta varchar(16), IN a_prezzo float, IN a_IP varchar(20), IN a_nome_console varchar(15), IN a_codice_download varchar(16))
BEGIN
    DECLARE EXIT HANDLER FOR SQLEXCEPTION 
    BEGIN
        ROLLBACK;
        SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'An error occurred';
    END;
    SET @data_rim = (SELECT Data_Rimozione FROM videogioco WHERE codice = a_codice);
    CASE WHEN (@data_rim IS NOT NULL AND @data_rim <= current_date())
		THEN 
			SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'An error occurred: videogioco rimosso';
		ELSE
			SET @pegi = (SELECT PEGI FROM videogioco WHERE codice = a_codice);
			SET @nascita = (SELECT Data_Nascita FROM giocatore WHERE Username = a_username);
			CASE WHEN ((@pegi >= 18 AND TIMESTAMPDIFF(year, @nascita, current_date()) < 18) OR (@pegi >= 18 AND @nascita IS NULL))
				THEN
					SIGNAL SQLSTATE '45000'
					SET MESSAGE_TEXT = 'An error occurred: problema di eta';
                ELSE
					START TRANSACTION;
						INSERT INTO acquista VALUES (a_username, a_codice, a_carta, a_prezzo, current_time(), current_date(), a_IP, a_nome_console, a_codice_download);
						SET @numero = (SELECT Copie_vendute FROM videogioco WHERE Codice = a_codice);
						UPDATE videogioco SET Copie_vendute = @numero+1
						WHERE codice = a_codice;
                        SET @libStat = ((SELECT COUNT(*) FROM ha_nella_libreria WHERE Username = a_username AND Videogioco = a_codice));
                        CASE WHEN ((@libStat = 0 ))
							THEN
								INSERT INTO ha_nella_libreria VALUES (a_username, a_codice, default);
							ELSE
								SET @libStat = ((SELECT COUNT(*) FROM ha_nella_libreria WHERE Username = a_username AND Videogioco = a_codice));
						END CASE;
					COMMIT;
			END CASE;
	END CASE;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `aggiorna_recensione` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `aggiorna_recensione`(IN a_codice int,  IN a_username varchar(20), IN a_voto float)
BEGIN
    DECLARE EXIT HANDLER FOR SQLEXCEPTION 
    BEGIN
        ROLLBACK;
    END;
    START TRANSACTION;
		SET @numero = (SELECT COUNT(*) FROM recensione WHERE recensione.videogioco = a_codice);
        SET @voto = (SELECT Voto FROM recensione WHERE recensione.videogioco = a_codice AND recensione.username = a_username);
		UPDATE recensione SET Voto = a_voto WHERE recensione.videogioco = a_codice AND recensione.username = a_username;
        UPDATE videogioco SET voto_medio = (voto_medio*@numero - @voto + a_voto)/(@numero) WHERE videogioco.codice = a_codice;
	COMMIT;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `aggiungi_genere` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `aggiungi_genere`(IN a_genere varchar(15), IN a_codice int(11))
BEGIN
    DECLARE EXIT HANDLER FOR SQLEXCEPTION 
    BEGIN
        ROLLBACK;
    END;
    START TRANSACTION;
		INSERT INTO piattaforma_videogiochi.genere VALUES (a_genere, a_codice);
	COMMIT;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `assegna_ticket` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `assegna_ticket`(IN a_codice int, IN a_ticket int, IN a_username varchar(20))
BEGIN
    DECLARE EXIT HANDLER FOR SQLEXCEPTION 
    BEGIN
        ROLLBACK;
        SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'An error occurred';
    END;
    SET @stato = (SELECT Stato FROM ticket WHERE Numero_Progressivo = a_ticket AND Username = a_username);
    CASE WHEN (@stato != 'in attesa') 
		THEN 
			SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'An error occurred';
        ELSE
			SET @tipo = (SELECT Impiegato FROM ticket JOIN specializzazione ON ticket.tipologia = specializzazione.ambito 
						WHERE ticket.Numero_Progressivo = a_ticket AND ticket.Username = a_username AND specializzazione.impiegato = a_codice);
			CASE WHEN (@tipo IS NULL)
				THEN
					SIGNAL SQLSTATE '45000'
					SET MESSAGE_TEXT = 'An error occurred';
				ELSE	
					START TRANSACTION;
						INSERT INTO lavora VALUES (a_codice, a_ticket, a_username);
                        UPDATE ticket SET Stato = 'in lavorazione';
					COMMIT;
		END CASE;
	END CASE;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `calcolo_contributo` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `calcolo_contributo`(IN a_data_inizio date, IN a_data_fine date)
BEGIN
    DECLARE EXIT HANDLER FOR SQLEXCEPTION 
    BEGIN
        ROLLBACK;
    END;
    START TRANSACTION;
		SELECT casa_editrice.ISIN,
		ROUND(COALESCE(SUM(Prezzo_Di_Acquisto), 0), 2) AS incassi,
		ROUND(
			COALESCE(SUM(Prezzo_Di_Acquisto), 0) * (calcola_k2(casa_editrice.ISIN, a_data_inizio, a_data_fine)/100) +
			POW(calcola_k2(casa_editrice.ISIN, a_data_inizio, a_data_fine), 2)
		, 2) AS contributi_annuali
		FROM videogioco
		JOIN casa_editrice ON videogioco.ISIN = casa_editrice.ISIN
		LEFT JOIN holding ON holding.ISIN = casa_editrice.ISIN
		LEFT JOIN indie ON indie.ISIN = casa_editrice.ISIN
		LEFT JOIN acquista ON (acquista.Videogioco = videogioco.codice
			AND acquista.Data >= a_data_inizio
			AND acquista.Data <= a_data_fine)
		WHERE videogioco.Data_Rilascio <= a_data_fine
		AND (videogioco.Data_Rimozione IS NULL || videogioco.Data_Rimozione >= a_data_inizio)
		GROUP BY casa_editrice.ISIN;
	COMMIT;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `calcolo_contributo_admin` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `calcolo_contributo_admin`(IN a_data_inizio date, IN a_data_fine date)
BEGIN
    DECLARE EXIT HANDLER FOR SQLEXCEPTION 
    BEGIN
        ROLLBACK;
    END;
    START TRANSACTION;
		SELECT azienda.Nome_Casa_Editrice AS ISIN,
		ROUND(COALESCE(SUM(Prezzo_Di_Acquisto), 0), 2) AS incassi,
		ROUND(
			COALESCE(SUM(Prezzo_Di_Acquisto), 0) * (calcola_k2(casa_editrice.ISIN, a_data_inizio, a_data_fine)/100) +
			POW(calcola_k2(casa_editrice.ISIN, a_data_inizio, a_data_fine), 2)
		, 2) AS contributi_annuali
		FROM videogioco
		JOIN casa_editrice ON videogioco.ISIN = casa_editrice.ISIN
		LEFT JOIN azienda ON azienda.ISIN = casa_editrice.ISIN
		LEFT JOIN holding ON holding.ISIN = casa_editrice.ISIN
		LEFT JOIN indie ON indie.ISIN = casa_editrice.ISIN
		LEFT JOIN acquista ON (acquista.Videogioco = videogioco.codice
			AND acquista.Data >= a_data_inizio
			AND acquista.Data <= a_data_fine)
		WHERE videogioco.Data_Rilascio <= a_data_fine
		AND (videogioco.Data_Rimozione IS NULL || videogioco.Data_Rimozione >= a_data_inizio)
		GROUP BY casa_editrice.ISIN;
	COMMIT;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `cancella_amicizia` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `cancella_amicizia`(IN a_possessore varchar(20), IN a_amico varchar(20))
BEGIN
    DECLARE EXIT HANDLER FOR SQLEXCEPTION 
    BEGIN
        ROLLBACK;
    END;
    START TRANSACTION;
		DELETE FROM e_nella_friendlist WHERE Possessore = a_possessore AND Amico = a_amico;
        DELETE FROM e_nella_friendlist WHERE Possessore = a_amico AND Amico = a_possessore;
	COMMIT;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `inserisci_amicizia` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `inserisci_amicizia`(IN a_possessore varchar(20), IN a_amico varchar(20))
BEGIN
    DECLARE EXIT HANDLER FOR SQLEXCEPTION 
    BEGIN
        ROLLBACK;
    END;
    START TRANSACTION;
		INSERT INTO e_nella_friendlist VALUES (a_possessore, a_amico);
        INSERT INTO e_nella_friendlist VALUES (a_amico, a_possessore);
	COMMIT;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `inserisci_azienda` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `inserisci_azienda`(IN a_username varchar(20), IN a_password varchar(50), IN a_email varchar(35), 
	IN a_email_recupero varchar(35), IN a_cellulare char(10), IN a_ip varchar(20), IN a_image mediumblob, IN a_ISIN char(12), IN a_nome_casa varchar(30), IN a_CEO varchar(30), IN a_sito varchar(30))
BEGIN
    DECLARE EXIT HANDLER FOR SQLEXCEPTION 
    BEGIN
        ROLLBACK;
    END;
    START TRANSACTION;
		INSERT INTO casa_editrice VALUES (a_ISIN, a_CEO, a_sito);
		INSERT INTO `account` VALUES (a_username, a_password, a_email, a_email_recupero, a_cellulare, current_date(), current_time(), a_ip, 2, a_image);
        INSERT INTO azienda VALUES(a_username, a_ISIN, a_nome_casa);
	COMMIT;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `inserisci_a_pagamento` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `inserisci_a_pagamento`(IN a_ISIN char(12), IN a_data_rilascio date, IN a_titolo varchar(50), IN a_PEGI int, IN a_image mediumblob, IN a_genere varchar(15), IN a_prezzo float, IN console_name varchar(15), IN a_trailer varchar(50))
BEGIN
    DECLARE EXIT HANDLER FOR SQLEXCEPTION 
    BEGIN
        ROLLBACK;
    END;
    START TRANSACTION;
		INSERT INTO videogioco (ISIN, Data_Rilascio, Titolo, PEGI, Img, Prezzo, Copie_Vendute, Trailer)
		VALUES (a_ISIN, a_data_rilascio, a_titolo, a_PEGI, a_image, a_prezzo, default, a_trailer);
        SET @numero = (SELECT Codice FROM Videogioco WHERE ISIN = a_ISIN AND data_rilascio = a_data_rilascio AND titolo = a_titolo AND PEGI = a_PEGI);
        INSERT INTO genere VALUES (a_genere, @numero);
        INSERT INTO console VALUES (console_name, @numero);
	COMMIT;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `inserisci_giocatore` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `inserisci_giocatore`(IN a_username varchar(20), IN a_password varchar(50), IN a_email varchar(35), 
	IN a_email_recupero varchar(35), IN a_cellulare char(10), IN a_ip varchar(20), IN a_image mediumblob, IN a_data_nascita date, IN a_genere enum('m', 'f'))
BEGIN
    DECLARE EXIT HANDLER FOR SQLEXCEPTION 
    BEGIN
        ROLLBACK;
    END;
    START TRANSACTION;
		INSERT INTO `account` VALUES (a_username, a_password, a_email, a_email_recupero, a_cellulare, current_date(), current_time(), a_ip, 1, a_image);
        INSERT INTO giocatore VALUES (a_username, a_data_nascita, current_date(), a_genere);
	COMMIT;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `inserisci_holding` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `inserisci_holding`(IN a_username varchar(20), IN a_password varchar(50), IN a_email varchar(35), IN a_email_recupero varchar(35), 
	IN a_cellulare char(10), IN a_ip varchar(20), IN a_ISIN char(12), IN a_nome_casa varchar(30), IN a_CEO varchar(30), IN a_sito varchar(30), IN a_fondazione date, a_sede varchar(30))
BEGIN
    DECLARE EXIT HANDLER FOR SQLEXCEPTION 
    BEGIN
        ROLLBACK;
    END;
    START TRANSACTION;
		CALL inserisci_azienda(a_username, a_password, a_email, a_email_recupero, a_cellulare, a_ip, a_ISIN, a_nome_casa, a_CEO, a_sito);
        INSERT INTO holding VALUES (a_ISIN, a_fondazione, a_sede);
	COMMIT;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `inserisci_indie` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `inserisci_indie`(IN a_username varchar(20), IN a_password varchar(50), IN a_email varchar(35), IN a_email_recupero varchar(35), 
	IN a_cellulare char(10), IN a_ip varchar(20), IN a_ISIN char(12), IN a_nome_casa varchar(30), IN a_CEO varchar(30), IN a_sito varchar(30), IN a_numero_sviluppatori int)
BEGIN
    DECLARE EXIT HANDLER FOR SQLEXCEPTION 
    BEGIN
        ROLLBACK;
    END;
    START TRANSACTION;
		CALL inserisci_azienda(a_username, a_password, a_email, a_email_recupero, a_cellulare, a_ip, a_ISIN, a_nome_casa, a_CEO, a_sito);
        INSERT INTO indie VALUES (a_ISIN, a_numero_sviluppatori);
	COMMIT;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `inserisci_libreria` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `inserisci_libreria`(IN a_username char(20), IN a_codice int)
BEGIN
    DECLARE EXIT HANDLER FOR SQLEXCEPTION 
    BEGIN
        ROLLBACK;
        SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'An error occurred';
    END;
    SET @data_rim = (SELECT Data_Rimozione FROM videogioco WHERE codice = a_codice);
    CASE WHEN (@data_rim IS NOT NULL AND @data_rim <= current_date())
		THEN 
			SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'An error occurred';
		ELSE
			SET @pegi = (SELECT PEGI FROM videogioco WHERE codice = a_codice);
			SET @nascita = (SELECT Data_Nascita FROM giocatore WHERE Username = a_username);
			CASE WHEN ((@pegi >= 18 AND date_sub(@nascita, INTERVAL year(current_date()) YEAR) < 18) OR (@pegi >= 18 AND @nascita IS NULL))
				THEN
					SIGNAL SQLSTATE '45000'
					SET MESSAGE_TEXT = 'An error occurred';
                ELSE
					START TRANSACTION;
						INSERT INTO ha_nella_libreria VALUES (a_username, a_codice, default);
					COMMIT;
			END CASE;
	END CASE;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `inserisci_recensione` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `inserisci_recensione`(IN a_codice int,  IN a_username varchar(20), IN a_commento varchar(200), IN a_voto float)
BEGIN
    DECLARE EXIT HANDLER FOR SQLEXCEPTION 
    BEGIN
        ROLLBACK;
    END;
    START TRANSACTION;
		SET @numero = (SELECT COUNT(*) FROM recensione WHERE recensione.videogioco = a_codice) +1;
		INSERT INTO recensione VALUES (@numero, a_codice, a_username, a_commento , a_voto);
        UPDATE videogioco SET voto_medio = (voto_medio*(@numero-1)+a_voto)/@numero WHERE videogioco.codice = a_codice;
	COMMIT;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `rimuovi_recensione` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `rimuovi_recensione`(IN a_codice int,  IN a_username varchar(20))
BEGIN
    DECLARE EXIT HANDLER FOR SQLEXCEPTION 
    BEGIN
        ROLLBACK;
    END;
    START TRANSACTION;
		SET @numero = (SELECT COUNT(*) FROM recensione WHERE recensione.videogioco = a_codice);
        SET @voto = (SELECT Voto FROM recensione WHERE recensione.videogioco = a_codice AND recensione.username = a_username);
		DELETE FROM recensione WHERE recensione.videogioco = a_codice AND recensione.username = a_username;
        IF (@numero > 1)
		THEN UPDATE videogioco SET voto_medio = (voto_medio*@numero - @voto)/(@numero-1) WHERE videogioco.codice = a_codice;
        ELSE UPDATE videogioco SET voto_medio = 0 WHERE videogioco.codice = a_codice;
        END IF;
	COMMIT;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `videogioco_eta` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `videogioco_eta`(IN eta_min int, IN eta_max int)
BEGIN
    DECLARE EXIT HANDLER FOR SQLEXCEPTION 
    BEGIN
        ROLLBACK;
    END;
    START TRANSACTION;
		SELECT codice, SUM(ore_di_gioco) AS ore_totali
		FROM videogioco
		JOIN ha_nella_libreria ON videogioco.codice = ha_nella_libreria.videogioco
		JOIN giocatore ON ha_nella_libreria.username = giocatore.username
		WHERE TIMESTAMPDIFF(year, giocatore.data_nascita, current_date()) >= eta_min
		AND TIMESTAMPDIFF(year, giocatore.data_nascita, current_date()) <= eta_max
        GROUP BY codice ORDER BY SUM(ore_di_gioco) DESC LIMIT 1;
	COMMIT;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `videogioco_fem` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `videogioco_fem`()
BEGIN
    DECLARE EXIT HANDLER FOR SQLEXCEPTION 
    BEGIN
        ROLLBACK;
    END;
    START TRANSACTION;
		SELECT Codice, COUNT(*) as Numero_Giocatrici, SUM(Ore_Di_Gioco) as Ore_Totali
		FROM videogioco
		JOIN ha_nella_libreria ON videogioco.codice = ha_nella_libreria.videogioco
		JOIN giocatore ON ha_nella_libreria.username = giocatore.username
		WHERE giocatore.genere = 'f'
        GROUP BY codice ORDER BY SUM(ore_di_gioco) DESC LIMIT 1;
	COMMIT;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `videogioco_mas` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `videogioco_mas`()
BEGIN
    DECLARE EXIT HANDLER FOR SQLEXCEPTION 
    BEGIN
        ROLLBACK;
    END;
    START TRANSACTION;
		SELECT Codice, COUNT(*) as Numero_Giocatori, SUM(Ore_Di_Gioco) as Ore_Totali
		FROM videogioco
		JOIN ha_nella_libreria ON videogioco.codice = ha_nella_libreria.videogioco
		JOIN giocatore ON ha_nella_libreria.username = giocatore.username
		WHERE giocatore.genere = 'm'
        GROUP BY codice ORDER BY SUM(ore_di_gioco) DESC LIMIT 1;
	COMMIT;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;