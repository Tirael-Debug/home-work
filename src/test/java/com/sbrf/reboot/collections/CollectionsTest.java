package com.sbrf.reboot.collections;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CollectionsTest {


    /*
     * Задача.
     * Имеется список лучших студентов вуза.
     *
     * 1. Иванов
     * 2. Петров
     * 3. Сидоров
     *
     * В новом семестре по результатам подсчетов оценок в рейтинг на 1 место добавился новый студент - Козлов.
     * Теперь в рейтинге участвуют 4 студента.
     * (Предполагаем что в рейтинг можно попасть только получив достаточное количество балов, что бы занять 1 место).
     *
     * Вопрос.
     * Какую коллекцию из Collections framework вы предпочтете для текущего хранения и использования списка студентов?
     *
     * Проинициализируйте students, добавьте в нее 4 фамилии студентов что бы тест завершился успешно.
     */
    @Test
    public void addStudentToRating() {
        //LinkedList лучше использовать для вставок элементов в произвольное место
        List<String> students = new LinkedList<>();

        students.add("Иванов");
        students.add("Петров");
        students.add("Сидоров");

        /*К сожалению addFirst работает, только при объявлении списка без интерфейса:
        LinkedList<String> students = new LinkedList<>();
        ...
        students.addFirst("Козлов");*/

        students.add(0, "Козлов");

        assertEquals(4, students.size());
        assertEquals("Козлов", students.get(0));
    }

    /*
     * Задача.
     * Вы коллекционируете уникальные монеты.
     * У вас имеется специальный бокс с секциями, куда вы складываете монеты в хаотичном порядке.
     *
     * Вопрос.
     * Какую коллекцию из Collections framework вы предпочтете использовать для хранения монет в боксе.
     *
     * Проинициализируйте moneyBox, добавьте в нее 10 монет что бы тест завершился успешно.
     */
    @Test
    public void addMoneyToBox() {
        // HashSet поддерживает хранение уникальных элементов без сохранения порядка вставок
        Set<Integer> moneyBox = new HashSet<>(Arrays.asList(11, 15, 1, 22, 17, 6, 13, 9, 99, 3));

        assertEquals(10, moneyBox.size());
    }

    /*
     * Задача.
     * Имеется книжная полка.
     * Периодически вы берете книгу для чтения, затем кладете ее на свое место.
     *
     * Вопрос.
     * Какую коллекцию из Collections framework вы предпочтете использовать для хранения книг.
     *
     * Проинициализируйте bookshelf, добавьте в нее 3 книги что бы тест завершился успешно.
     */
    @Test
    public void addBookToShelf() {
        class Book {
        }

        Book book1 = new Book();
        Book book2 = new Book();
        Book book3 = new Book();
        //Здесь также подойдет ArrayList, так как он может принимать null
        // и можно реализовать взятие книги заменой ее в списке на null, а возврат - помещением обратно
        List<Book> bookshelf = new ArrayList<>();

        bookshelf.add(book1);
        bookshelf.add(book2);
        bookshelf.add(book3);

        assertEquals(3, bookshelf.size());

        Book reading = bookshelf.get(1);
        bookshelf.set(1, null);

        assertEquals(reading, book2);
        assertNull(bookshelf.get(1));

        bookshelf.set(1, book2);

        assertEquals(bookshelf.get(1), book2);
    }


}
