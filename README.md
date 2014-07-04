General information

[todo] В течении первого семестра необходимо сделать

        1. Техническое задание 

	2. Vision в RUP нотации. 

	3. SRS - Software Requirements Specification, VCS. 

Описать две критически важных функции. Для них составить основной сценарий - последовательность действий необходимая для достижения основной цели. Альтернативные сценарии - варианты основного сценария, возникающие при некоторых отклонениях (исключениях) при следовании основному сценарию.

	4. Risk list + Glossary

Глоссарий - абривеатуры, существующие термины, документ в RUP нотации.
Описание рисков - десять основных возможных рисков, относящихся к четырем группам по классификации. Оценить каждый риск  - затраты  на предотвращение последствий (время,деньги). Стратегия избегания рисков (Action Plan).  

	5. SDP   Softvare Development Plan.

Подробный план разработки, версии билдов, текущие задачи каждого члена команды.
--------------------------------------------------------
|Неделя|Сотрудник         |Задача    |Версия ПО (Build)|
--------------------------------------------------------
Таблица финансовых затрат.
--------------------------------------------------------------
|Сотрудник         |деньги за задачу  |общие расходы на этапе|
-------------------------------------------------------- -----

	6. Busness case

Описать издержки - неразумное использование средств при текущеп положении дел на предприятии (в виде таблицы).
Расчитать общую стоимость проекта  - SDP + прибыль...
Срок окупаемости проекта

	7. Исполняемая архитектура

Прототип системы 2-3 основных функции.


// Db tutorial

1. install postgres
sudo apt-get install postgresql

2. login in postgres
sudo -u postgres psql postgres

3. create user
CREATE USER test WITH PASSWORD 'test';
4. create db
CREATE DATABASE f;
5. grant access
GRANT ALL PRIVILEGES ON DATABASE f to test;

//https

#generate ssl certificate
mvn keytool:generateKeyPair

#clean ssl certificate
mvn keytool:clean

#add oracle jdbc driver 

mvn install:install-file -Dfile=ojdbc7.jar -DgroupId=com.oracle -DartifactId=ojdbc7 -Dversion=12.2.0 -Dpackaging=jar

#forward ssh ports
ssh -p 22222 -L 1521:localhost:1521 s153307@helios.cs.ifmo.ru

#Init DB
/admin/services/startinit/

#git repo
https://github.com/Serjk/451F/
