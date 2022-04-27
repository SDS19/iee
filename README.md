## UI
![image](https://user-images.githubusercontent.com/46221024/165363373-6717a250-d437-4337-b4cd-9dc083ce419d.png)

## Project structure

- `backend` package contains all backend project file
  - `entity` package contains the entity class `WindData`.
  - `controller` package contains the `DataController` class to service the request from browser.
  - `service` package contains the `DataService` class to read .csv file, persist data, get MAX/AVG/MIN value...
  - `dao` package contains the dao interface and mybatis mapper file to execute SQL statement.
  - `resources/static` package contains the resource file of frontend.
  - `test` package contains the Unit Test class `DataServiceTest` for `DataService.class`, `DataDaoTests` for `DataDao.class`.

- `frontend` package contains all frontend .vue file

## Run the Spring Boot Project

1. Install JDK8 on your computer
2. Run command `java -jar iee-0.0.1-SNAPSHOT.jar` in the terminal (Note: absolute path of `.jar`)
3. Access `localhost:9090` in browser
4. Use date picker to select the range to show data between start date and end date (01.02.2022 ~ 28.02.2022)
