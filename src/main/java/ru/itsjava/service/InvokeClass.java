package ru.itsjava.service;

import lombok.SneakyThrows;
import ru.itsjava.annotations.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class InvokeClass {

    @SneakyThrows
    public void invokeAllMethods() {
        int fallenCounter = 0;
        int goodCounter = 0;

        TestClass testClass = new TestClass();

        Method[] methods = TestClass.class.getDeclaredMethods();

        for (Method method : methods) {
            if (method.isAnnotationPresent(Before.class)) {
                method.invoke(testClass);
            }
        }

        for (Method method : methods) {
            for (Method value : methods) {
                if (value.isAnnotationPresent(BeforeEach.class)) {
                    try {
                        value.invoke(testClass);
                    } catch (InvocationTargetException ite) {
                        System.err.println(value.getName() + "Успешно упал");
                    }
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
                    try {
                        value.invoke(testClass);
                    } catch (InvocationTargetException ite) {
                        System.err.println(value.getName() + "Успешно упал");
                    }
                }
            }
        }


        for (Method method : methods) {
            if (method.isAnnotationPresent(After.class)) {
                method.invoke(testClass);
            }
        }
        System.out.println("Количество пройденных тестов " + goodCounter);
        System.out.println("Количество заваленных тестов " + fallenCounter);

    }
}
