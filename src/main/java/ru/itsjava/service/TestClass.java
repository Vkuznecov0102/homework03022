package ru.itsjava.service;

import ru.itsjava.annotations.*;

public class TestClass {

    @Before
    public void beforeTest() {
        System.out.println("Начинаем наши тесты. Добро пожаловать!");
    }

    @BeforeEach
    public void beforeEachTest() {
//        System.out.println("-------");
        System.out.println("Сейчас нам предстоит тест");
//        System.out.println("-------");
    }

    @Test
    public void test1() {
        throw new AssertionError();
    }

    @Test
    public void test2() {
        System.out.println("На связи тест 2");
    }

    @Test
    public void test3() {
        System.out.println("На связи тест 3");
    }

    @Test
    public void test4() {
        System.out.println("На связи тест 4");
    }

    @Test
    public void test5() {
        System.out.println("На связи тест 5");
    }

    @AfterEach
    public void afterEachTest() {
        System.out.println("Этот тест завершен");
    }

    @After
    public void afterAllTests() {
        System.out.println("Тесты завершены полностью");
    }

}
