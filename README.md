# TestBookProvectus
/login UserName: admin Password:admin

Customer requirements:
 + You need to create web-interface for admin for add/edit title, description of the book, price, author and genre. +
 (дополнительно реализовано удаление книги ) +
Internet user should be able to:
+ 1) See list of the books by different genres. Any book can belong to more that one genre. +
+ 2) See list of the books by different authors. Any book can belong to more that one author. +
+ 3) See book details(Whole information on book: title, description, list of genres, list of authors, price) +
+ 4) On the same page user should be able to submit book order form: First Name, Last Name, Address, quantity of books user would like to order. This form should be stored in Database. +
Additional details:
+! a) Access to admin web-interface should be implemented via .htaccess, You don't need to store Admin user and pass at Database +
(С этим файлом я не работал, и его неудобно было внедрять в проект, поэтому использовал SpringSecurity как более оптимальный вариант) +
Не зарегестрированному пользователю ссылки на просмотр всех ордеров, добавление, редактирование и удаление книги недоступно и не видно.
чтобы воспользоваться этой функцианальностью, необходимо залогиниться с указаными данными(строка 2) .
Кнопка логин расположена сверху-справа
+ b) Genres are single-level. No sub-genres. +
test commit
jopa test