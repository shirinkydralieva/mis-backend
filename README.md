Установка и запуск программы:(тестовый вариант)
1. Клонируйте репозиторий:
   
   git clone https://github.com/shirinkydralieva/mis-backend
   
3. Передите в директорию проекта:
   
   cd your-repo-name
   
5. Установите зависимости:
   
   mvn install
   
7. Создайте базу данных на порте 5432 с именем mis_test:
   
   psql -p 5432 postgres

   create database mis_test;

9. Запустите docker container для rabbitMQ:
    
   docker run -it -p 15672:15672 -p 5672:5672 rabbitmq:3.10.5-management
   
11. Выберите активный профиль в Spring Boot:
    
    export SPRING_PROFILES_ACTIVE=test

13. Запустите программу:
    
    mvn spring-boot:run
