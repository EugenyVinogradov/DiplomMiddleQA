# Дипломный проект по профессии "Инженер по тестированию"
---

## Описание 

Дипломный проект включает в себя изучение функционала мобильного приложения [Мобильный хоспис](https://github.com/fmh-charity/fmh-android), составление плана его тестирования,
создание чек-листа и тест-кейсов, проведение ручного тестирования приложения, оформление обнаруженных дефектов, создание автотестов и их запуск с помощью **Android Studio**, создание
отчета о проведенном автоматизированном тестировании с помощью **Allure**.

---

## Подготовка тестового окружения

1. Склонировать [репозиторий](https://github.com/EugenyVinogradov/DiplomMiddleQA.git) на локальную рабочую станцию
2. Открыть папку **fmh-android** в **Android Studio**
3. Дождаться скачивания и установки всех зависимостей проекта
4. Подключить к рабочей станции реальное устройство или выбрать (создать) эмулятор в **Android Studio** в качестве тестового устройства. 
На устройстве должны быть **Android API 29**, анимация окон и переходов должны быть отключены

---

## Запуск автотестов

На папке проекта **fmh-android\app** выбрать пункт контекстного меню **Run 'All Tests'**, или нажать **Ctrl+Shift+F10**, или выполнить в терминале команду **./gradlew cAT**

---

## Создание отчета Allure

1. После прогона всех тестов для формирования отчета **Allure** необходимо скопировать результаты тестирования с тестового устройства на рабочую станцию. Для этого необходимо:
  *  В **Android Studio** запустить **Device File Explorer**, в контекстном меню папки **\data\data\ru.iteco.fmhandroid\files\target** выбрать пункт **Save As...** 
     и указать путь к папке проекта для сохранения
  *  В терминале последовательно выполнить команды:
     ```
      adb exec-out run-as ru.iteco.fmhandroid sh -c 'cd /data/data/ru.iteco.fmhandroid/files/target && tar cf - allure-results' > allure-results.tar
     ```

     ```
     adb shell
     ```

     ```
     run-as ru.iteco.fmhandroid sh -c 'cd /data/data/ru.iteco.fmhandroid/files/target && tar cf - allure-results' | tar xvf - -C /data/local/tmp
     ```

     ```
     exit
     ```

     ```
     adb pull /data/local/tmp/allure-results
     ```

2. Для визуализации отчета выполнить в терминале команду **allure serve**

## Отчетная документация

* [Обнаруженные при ручном тестировании дефекты](https://github.com/EugenyVinogradov/DiplomMiddleQA/issues)
* [Результыты автоматизированного тестирования](https://github.com/EugenyVinogradov/DiplomMiddleQA/blob/main/allure-results/allure-results.zip)
