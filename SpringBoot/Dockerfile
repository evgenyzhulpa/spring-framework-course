# Базовый образ, содержащий Java 19
FROM openjdk:19-oracle

# Директория приложения внутри контейнера
WORKDIR /app

# Копирование jar-файла приложения в контейнер
COPY target/SpringBoot-0.0.1-SNAPSHOT.jar app.jar

# Команда для запуска приложения
CMD ["java", "-jar", "app.jar"]

