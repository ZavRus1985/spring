1.	Дан класс Car (model, price, owner, year, insuranceExpirationDate). Данный класс является Entity.
2.	Определить поля в Entity, которые не могут быть null (логически).
3.	Поле model сделать уникальным.
4.	Сделать первичный ключ авто-генерируемым.
5.	Дан класс Configuration, который содержит два бина: SessionFactory, CarRepository.
6.	В конфигурацию Hibernate добавить строчку, форматирующую SQL-запросы (как в презентации).
7.	SessionFactory должен внедряться в CarRepository.
8.	CarRepository должен содержать методы:
   *	Select all, select by id, select where year > «param».
   *	Insert
   *	Update by id
   *	Delete by id
9.	Маппинг полей при выполнении метода update (updatableCar.setModel(car.getModel)) необходимо вынести в отдельный класс.
10.	Изменить конфигурацию Hibernate на JavaCode.
11.	Изменить тип первичного ключа на String. Сделать автоматическую генерацию с помощью UUID. 
12.	После изменения в 11 пункте пересоздать таблицу средствами Hibernate, не удаляя таблицу вручную.
13.	** Вынести каркас работы с транзакциями в отдельный метод, чтобы его не дублировать в каждом методе репозитория.
