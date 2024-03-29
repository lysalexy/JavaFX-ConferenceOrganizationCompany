# Декстоп-приложение для компании по организации конференций

Проект представляет собой приложение для работы с базой данных компании по организации конференций. База данных, а также
используемые функции хранятся в СУБД MSSQL Server и были спроектирована в рамках дисциплины "Базы данных"
Санкт-Петербургского Политехнического университета им. Петра Великого (ВШПИ, Программная инженерия, 3-ий курс). В рамках
курсового проекта запрещено использование ORM для клиентского приложения, был реализован шаблон проектирования
Model-View-Presenter.

## Схема базы данных
![база данных с типами](https://github.com/Lysenko-Aleksandra/JavaFX-ConferenceOrganizationCompany/assets/78423459/7b838058-ba53-4336-8b33-37579d8a5cc3)

## Механика работы приложения
Для получения доступа к приложению необходимо пройти авторизацию, на этапе которой определяется роль пользователя

В рамках приложения выделяются 3 роли пользователей:

* администратор
* личный помощник
* видеограф/фотограф

В рамках создания конференции возможно закрепить за мероприятием видеографа/фотографа, а также составить фуршетное меню.

### Функционал пользователей
Подробное описание функционала с демонстрацией исходного вида приложения представлено в виде [отчета](https://disk.yandex.ru/i/we9wTc3xjYRsIQ)

## Стек технологий

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Gradle](https://img.shields.io/badge/Gradle-02303A.svg?style=for-the-badge&logo=Gradle&logoColor=white)
![MicrosoftSQLServer](https://img.shields.io/badge/Microsoft%20SQL%20Server-CC2927?style=for-the-badge&logo=microsoft%20sql%20server&logoColor=white)

## Запуск и установка

Требуется запущенный MSSQL Server, содержащий заполненную базу данных и все используемые встроенные функции.

Необходимо изменить значение переменной url в java/ConferenceOrganizationCompanyApplication.java

| Название переменной внутри url | Описание                         |
|--------------------------------|----------------------------------|
| user                           | Пользователь MSSQL Server        |
| password                       | Пароль пользователя MSSQL Server |


