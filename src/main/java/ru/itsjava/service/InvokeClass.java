package ru.itsjava.service;

import lombok.SneakyThrows;
import ru.itsjava.annotations.*;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public class InvokeClass {

    TestClass testClass = new TestClass();

    int fallenCounter = 0;
    int goodCounter = 0;

    List<Method> methods = Arrays.asList(TestClass.class.getDeclaredMethods());

    @SneakyThrows
    public void invokeAllMethods() {
        invokeBefore();
        invokeTestsWithBeforeEachAndAfterEach();
        invokeWithAfter();

        System.out.println("Количество пройденных тестов " + goodCounter);
        System.out.println("Количество заваленных тестов " + fallenCounter);

    }

    @SneakyThrows
    public void invokeBefore(){
        for (Method method : methods) {
            if (method.isAnnotationPresent(Before.class)) {
                method.invoke(testClass);
            }
        }
    }

    @SneakyThrows
    public void invokeTestsWithBeforeEachAndAfterEach(){
        for (Method method : methods) {
            for (Method value : methods) {
                if (value.isAnnotationPresent(BeforeEach.class)) {
                    value.invoke(testClass);
                }
            }

            if (method.isAnnotationPresent(Test.class)) {
                try {
                    method.invoke(testClass);
                    goodCounter++;
                } catch (Throwable throwable) {
                    fallenCounter++;
                    System.err.println(method.getName() + " Успешно упал!!!");
                }
            }

            for (Method value : methods) {
                if (value.isAnnotationPresent(AfterEach.class)) {
                    value.invoke(testClass);
                }
            }
        }
    }

    @SneakyThrows
    public void invokeWithAfter(){
        for (Method method : methods) {
            if (method.isAnnotationPresent(After.class)) {
                method.invoke(testClass);
            }
        }
    }
}
