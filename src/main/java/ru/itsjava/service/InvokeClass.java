package ru.itsjava.service;

import lombok.SneakyThrows;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class InvokeClass {

    @SneakyThrows
    public void invokeAllMethods() {
        int fallenCounter = 0;
        int goodCounter = 0;

        TestClass testClass = new TestClass();

        Method beforeMethod = TestClass.class.getDeclaredMethod("beforeTest");
        Method beforeEachMethod = TestClass.class.getDeclaredMethod("beforeEachTest");
        Method methodWithTestOne = TestClass.class.getDeclaredMethod("test1");
        Method methodWithTestTwo = TestClass.class.getDeclaredMethod("test2");
        Method methodWithTestThree = TestClass.class.getDeclaredMethod("test3");
        Method methodWithTestFour = TestClass.class.getDeclaredMethod("test4");
        Method methodWithTestFive = TestClass.class.getDeclaredMethod("test5");
        Method afterMethod = TestClass.class.getDeclaredMethod("afterAllTests");
        Method afterEachMethod = TestClass.class.getDeclaredMethod("afterEachTest");

        List<Method> methodList = new ArrayList<>();
        methodList.add(beforeMethod);
        methodList.add(beforeEachMethod);
        methodList.add(methodWithTestOne);
        methodList.add(afterEachMethod);
        methodList.add(beforeEachMethod);
        methodList.add(methodWithTestTwo);
        methodList.add(afterEachMethod);
        methodList.add(beforeEachMethod);
        methodList.add(methodWithTestThree);
        methodList.add(afterEachMethod);
        methodList.add(beforeEachMethod);
        methodList.add(methodWithTestFour);
        methodList.add(afterEachMethod);
        methodList.add(beforeEachMethod);
        methodList.add(methodWithTestFive);
        methodList.add(afterEachMethod);
        methodList.add(afterMethod);

        for (Method method : methodList) {
            try {
                method.invoke(testClass);
                goodCounter++;
            } catch (Throwable throwable) {
                fallenCounter++;
                System.err.println(method.getName() + " Успешно упал!!!");
            }
        }

        System.out.println("Количество пройденных тестов " + goodCounter);
        System.out.println("Количество заваленных тестов " + fallenCounter);

    }
}
