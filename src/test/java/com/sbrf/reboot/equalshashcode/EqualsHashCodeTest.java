package com.sbrf.reboot.equalshashcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Objects;

public class EqualsHashCodeTest {

     class Car {
        String model;
        String color;
        Calendar releaseDate;
        int maxSpeed;

        @Override
        public boolean equals(Object o) {

            //Рефлексивность: объект должен равняться самому себе
            if (o == this)
                return true;
            //для любого заданного значения x вызов x.equals(null) должен возвращать false
            if (Objects.isNull(o)) {
                return false;
            }
            // сначала проверим, является ли о экземпляром Car, если нет то дальше идти незачем
            if (o instanceof Car) {
                // если о - экземпляр Car, проверим на равенство поля (через Objects.equals чтобы не получить NPE)
                return Objects.equals(this.model, ((Car) o).model)
                        && Objects.equals(this.color, ((Car) o).color)
                        && Objects.equals(this.releaseDate, ((Car) o).releaseDate)
                        && this.maxSpeed == ((Car) o).maxSpeed;
            }

            return false;
        }

        private int hashCodeFromString(String str) {
            // Изменения в этом плане коснулись только базового значения
            // (вместо константы я использовала размер строки)
            int hash = str.length();
            // И итерируясь по символам строки
            for (int i = 0; i < str.length(); i++) {
                // Добавляем код символа к хешу и умножаем на простое число 31
                // Подробное объеснение почему именно 31 трудно воспроизвести, но основной момент
                // в том, что оно простое и с достаточной вероятностью обеспечит ровномерность распределения
                // https://computinglife.wordpress.com/2008/11/20/why-do-hash-functions-use-prime-numbers/
                hash = hash * 31 + str.charAt(i);
            }

            return hash;
        }

         @Override
         public int hashCode() {
             /* сформируем int - timestamp из releaseDate
             * в идеале можно использовать встроенный hashCode(),
             * но поскольку в этом примере мы задаем только 3 поля
             * можно просто достать их и просуммировать (за пределы int сумма года
             * месяца и дня не выйдет)*/
             int stamp = this.releaseDate.get(Calendar.YEAR)
                     + this.releaseDate.get(Calendar.MONTH)
                     + this.releaseDate.get(Calendar.DAY_OF_MONTH);
             // Для строк можно вычислить hashCode по кодам символов (в отдельной функции)
             int modelHash = hashCodeFromString(this.model);
             /* Эта функция взята на основе следующего решения
             * https://stackoverflow.com/questions/2624192/good-hash-function-for-strings */
             int colorHash = hashCodeFromString(this.color);
             // Добавим единственный int - maxSpeed в итоговое значени
             return this.maxSpeed + modelHash + colorHash + stamp;
         }
     }

    @Test
    public  void assertTrueEquals() {
        Car car1 = new Car();
        car1.model = "Mercedes";
        car1.color = "black";
        car1.releaseDate = new GregorianCalendar(2020, 0, 25);
        car1.maxSpeed = 10;

        Car car2 = new Car();
        car2.model = "Mercedes";
        car2.color = "black";
        car2.releaseDate = new GregorianCalendar(2020, 0, 25);
        car2.maxSpeed = 10;


        Assertions.assertTrue(car1.equals(car2));
    }

    @Test
    public void assertFalseEquals() {
        Car car1 = new Car();
        car1.model = "Mercedes";
        car1.color = "black";
        car1.releaseDate = new GregorianCalendar(2020, 0, 25);
        car1.maxSpeed = 10;

        Car car2 = new Car();
        car2.model = "Audi";
        car2.color = "white";
        car2.releaseDate = new GregorianCalendar(2017, 0, 25);
        car2.maxSpeed = 10;

        Assertions.assertFalse(car1.equals(car2));
    }

    @Test
    public void successEqualsHashCode(){
        Car car1 = new Car();
        car1.model = "Mercedes";
        car1.color = "black";
        car1.releaseDate = new GregorianCalendar(2020, 0, 25);
        car1.maxSpeed = 10;

        Car car2 = new Car();
        car2.model = "Mercedes";
        car2.color = "black";
        car2.releaseDate = new GregorianCalendar(2020, 0, 25);
        car2.maxSpeed = 10;

        Assertions.assertEquals(car1.hashCode(),car2.hashCode());

    }

    @Test
    public void failEqualsHashCode(){
        Car car1 = new Car();
        car1.model = "Mercedes";
        car1.color = "black";
        car1.releaseDate = new GregorianCalendar(2020, 0, 25);
        car1.maxSpeed = 10;

        Car car2 = new Car();
        car2.model = "Audi";
        car2.color = "white";
        car2.releaseDate = new GregorianCalendar(2017, 0, 25);
        car2.maxSpeed = 10;

        Assertions.assertNotEquals(car1.hashCode(),car2.hashCode());

    }


}
