FROM payara/server-full:latest

COPY init-datasource.sh init-datasource.sh

COPY target/app-1.0-SNAPSHOT.war /opt/payara6/temp/ROOT.war

CMD ["bash", "init-datasource.sh"]
