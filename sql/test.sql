SELECT max(subject) + 1 as ID FROM entity_store;

SELECT subject AS id FROM entity_store WHERE predicate='label' AND object='liked';
UPDATE entity_store SET object='liked' WHERE subject=3 AND predicate='label';

SELECT * FROM entity_store WHERE predicate='number' AND object=3;

SELECT * FROM entity_store WHERE object='rc_daniel';

SELECT * FROM entity_store WHERE NOT (predicate='type' OR predicate='class') AND subject IN 
(SELECT subject AS id FROM entity_store 
	WHERE ((predicate='firstname' AND object='daniel') 
	OR (predicate='surname' AND object='daniel')
	OR (predicate='gender' AND object='daniel')
	OR (predicate='birthdate' AND object='daniel')
	OR (predicate='birthmonth' AND object='daniel')
	OR (predicate='birthyear' AND object='daniel'))
	AND subject IN (SELECT subject FROM entity_store
	WHERE predicate='class' AND object='node'));

SELECT subject, object FROM entity_store 
	WHERE predicate='label' AND NOT object='friendOf' 
	AND subject IN (SELECT subject FROM entity_store 
	WHERE predicate='class' AND object='edge');
	
SELECT a.subject FROM entity_store a, entity_store b, entity_store c
	WHERE (a.predicate='birthdate' AND a.object=23) 
	AND (b.predicate='birthmonth' AND b.object=7) 
	AND (c.predicate='birthyear' AND c.object=1989) 
	AND a.subject=b.subject 
	AND a.subject=c.subject;