#!/bin/bash
set -e

asadmin start-domain &

echo "Aguardando Payara iniciar..."
until asadmin list-domains | grep -q running; do
  sleep 2
done

echo "Criando pool JDBC..."
asadmin create-jdbc-connection-pool \
  --datasourceclassname org.postgresql.ds.PGSimpleDataSource \
  --restype javax.sql.DataSource \
  --property user=$POSTGRES_USER:password=$POSTGRES_PASSWORD:databaseName=$POSTGRES_DB:serverName=postgres_db:portNumber=5432 \
  PostgresPool

echo "Criando recurso JDBC..."
asadmin create-jdbc-resource \
  --connectionpoolid PostgresPool \
  jdbc/PostgresDS__pm

echo "Fazendo deploy da aplicação..."
asadmin deploy /opt/payara6/temp/ROOT.war

echo "Payara iniciado com datasource e aplicação implantados."

tail -f /opt/payara6/glassfish/domains/domain1/logs/server.log
