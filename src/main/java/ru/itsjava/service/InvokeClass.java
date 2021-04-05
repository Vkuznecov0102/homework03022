package ru.itsjava.service;

import lombok.SneakyThrows;
import ru.itsjava.annotations.*;

import java.lang.reflect.Method;
import java.util.ArrayList;
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
    public void invokeBefore() {
        List<Method> copyBefore = new ArrayList<>();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Before.class)) {
                copyBefore.add(method);
            }
        }

        for (Method method : copyBefore) {
            method.invoke(testClass);
        }
    }

    @SneakyThrows
    public void invokeTestsWithBeforeEachAndAfterEach() {
        List<Method> copyBeforeEach = new ArrayList<>();
        List<Method> copyTest = new ArrayList<>();
        List<Method> copyAfterEach = new ArrayList<>();

        for (Method value : methods) {
            if (value.isAnnotationPresent(BeforeEach.class)) {
                copyBeforeEach.add(value);
            }
        }
        for (Method value : methods) {
            if (value.isAnnotationPresent(Test.class)) {
                copyTest.add(value);
            }
        }
        for (Method value : methods) {
            if (value.isAnnotationPresent(AfterEach.class)) {
                copyAfterEach.add(value);
            }
        }

        for (Method value : copyTest) {
            for (Method method : copyBeforeEach) {
                method.invoke(testClass);
            }

            try {
                value.invoke(testClass);
                goodCounter++;
            } catch (Throwable throwable) {
                fallenCounter++;
                System.err.println(value.getName() + " Успешно упал!!!");
            }

            for (Method method : copyAfterEach) {
                method.invoke(testClass);
            }
        }
    }

    @SneakyThrows
    public void invokeWithAfter() {
        List<Method> copyAfter = new ArrayList<>();
        for (Method method : methods) {
            if (method.isAnnotationPresent(After.class)) {
                copyAfter.add(method);
            }
        }

        for (Method method : copyAfter) {
            method.invoke(testClass);
        }
    }
}