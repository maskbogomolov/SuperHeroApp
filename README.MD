### Test KODE
### Декомпозиция
Приложение содержит 2 основных экрана(***Главный*** &***Детали***) и переход между ними, также есть логика фильтрации.


> Далее для каждой задачи я буду определять примерное время её  выполнение и сложность по формуле:= Задача(время, сложность от 0 до 10)
#### Экран "Главный" примерный UI
<img src="https://user-images.githubusercontent.com/76943234/151440916-4ec58bd4-2d11-4cd5-b1c0-b6bf56906600.png" width="336" align="right" hspace="20">

+ Верстка
  + список пользователей (30мин, 4сл)
  + выбор фильтра (1 час, 7сл)
+ Логика
  + загрузка пользователей с фотографией
+ Переходы
  + при нажатие на пользователя -> экран деталей (30мин, 3сл)
  + при нажатии на иконку фильтра -> fragment filter (40мин, 6сл)
+ Особенности
  + Возможность фильтрации
+ Обработка ошибок
  + в тз явно не указано
 # ///////////////////////////////////////////////////////////////////////////////////////////////
 

 #### Экран "Детали" примерный UI
  <img src="https://user-images.githubusercontent.com/76943234/151785814-15aff7f5-4040-47ae-a865-726ccbaa3cd5.png" width="336" align="right" hspace="20">

 + Верстка
    + Информация о герое
 + Логика
    + загрузка биографии с фотографией
 + Переходы
    + при нажатие на иконку "назад" -> главный экран
 + Обработка ошибок
    + в тз явно не указано

# ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

## Предположительное время выполнения в мире где отстутсвуют баги ~ 30 часов

# Итог

## Tech-stack
 <img src="https://user-images.githubusercontent.com/76943234/151781833-81577f7d-d2c5-47a7-950d-f5899490c55c.gif" width="250" align="right" hspace="20">
 
+ Kotlin
+ Coroutine
  + For background work
+ Flow
  + For emit multiple values sequentially
+ Dagger2
  + For Dependency injection
+ Retrofit
  + For networking
+ Jetpack
  + LiveData
  + Lifecycle
+ Coil
  + For load image

## Architecture

+ MVVM
  + Model-View-ViewModel is a structural design pattern that separates objects into three distinct groups
+ Data layer
  +  The data layer contains application data and business logic
+ Domain layer
  + The domain layer is an optional layer that sits between the UI layer and the data layer.Here I just put clean data
+ Presentation layer
  + The role of the UI is to display the application data on the screen and also to serve as the primary point of user interaction.
  
## Design


| Mode  | Characters list                                                                           | Character detail                                                          |
|-------|-------------------------------------------------------------------------------------------|---------------------------------------------------------------------------|
| Light | <img src="https://user-images.githubusercontent.com/76943234/151782376-254c9424-75c4-4939-9ce2-5521beeecc03.jpg" width="250">                  | <img src="https://user-images.githubusercontent.com/76943234/151782579-b279acaf-ee59-4827-a4cd-fae5eba62796.jpg" width="250"> |
| Dark  | <img src="https://user-images.githubusercontent.com/76943234/151782734-f091f58e-91e9-452e-92df-f55ddd4edf59.jpg" width="250">                   | <img src="https://user-images.githubusercontent.com/76943234/151789052-e1f1116f-3fd5-470d-9905-adf7e12e13ea.jpg" width="250">  |
