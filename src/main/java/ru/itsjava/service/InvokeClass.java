package ru.itsjava.service;

import java.lang.reflect.Method;

public class InvokeClass {

    public void invokeAllMethods(){
        int counterOfFallen=0, counterOfGood=0;
        TestClass testClass=new TestClass();

        Method[] allMethods= TestClass.class.getDeclaredMethods();

        for(Method method : allMethods){
            try{
                method.invoke(testClass);
//                System.out.println(method.getName()+" успешно прошел");
                counterOfGood++;
            }
            catch(Throwable throwable){
                counterOfFallen++;
                System.err.println(method.getName()+" Успешно упал!!!");
            }
        }

        System.out.println("Количество пройденных тестов "+ counterOfGood);
        System.out.println("Количество заваленных тестов "+counterOfFallen);

    }
}
