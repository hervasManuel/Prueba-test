FROM arm64v8/mysql:oracle

COPY iobuilders_0.1.sql /docker-entrypoint-initdb.d/
ENV MYSQL_ROOT_PASSWORD=SuperSecret
ENV MYSQL_DATABASE=appagar
ENV MYSQL_USER=admin
ENV MYSQL_PASSWORD=admin
EXPOSE 3306