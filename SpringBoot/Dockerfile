# Базовый образ, содержащий Java 19
FROM openjdk:19-oracle

# Директория приложения внутри контейнера
WORKDIR /app

# Копирование jar-файла приложения в контейнер
COPY target/SpringBoot.jar app.jar

# Команда для запуска приложения
CMD ["java", "-jar", "app.jar"]

