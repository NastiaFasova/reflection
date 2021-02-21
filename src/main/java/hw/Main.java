package hw;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;

public class Main {
    public static void main(String[] args) {
        Class reflected = Notebook.class;
        // Отримуємо ім'я класу
        System.out.println("Reflected class name: " + reflected.getSimpleName());
        Package pack = reflected.getPackage();
        String packageName = pack.getName();
        System.out.println("Package = " + packageName);

        // Отримуємо модифікатори класу
        int classModifiers = reflected.getModifiers();
        System.out.println(Modifier.isPublic(classModifiers));
        // Отримуємо відкриті методи класу, параметри, повертаємий тип
        Method[] methods = reflected.getMethods();
        System.out.println("\nSubClass methods:");
        for(Method method: methods){
            System.out.print("Method " + method.getName());
            System.out.print(" Return Type: " + method.getReturnType());
            Class[] parameterType = method.getParameterTypes();
            System.out.print(" Parameters: ");
            for(Class parameter : parameterType){
                System.out.print(parameter.getName()+ " ");
            }
            System.out.println();
        }

        // Отримуємо суперклас
        Class reflectedSuperclass = reflected.getSuperclass();
        System.out.println("\nSubclass superclass: " + reflectedSuperclass.getName());

        // Отримуємо інтерфейси
        Class [] interfaces = reflectedSuperclass.getInterfaces();
        System.out.println("\nBaseClass interfaces:");
        for(Class interface_: interfaces){
            System.out.println(interface_.getName());
        }

        // Отримуємо конструктори і їх параметри
        Constructor[] constructors = reflectedSuperclass.getConstructors();
        for(Constructor constructor: constructors){
            Class[] parameterType = constructor.getParameterTypes();
            System.out.println("BaseClass Constructors Parameters: ");
            for(Class parameter : parameterType){
                System.out.print(parameter.getName() + " ");
            }
        }
        System.out.println();

        // Ініціалізуємо і викликаємо конструктор
        Constructor baseConstructor = null;
        try {
            baseConstructor = reflectedSuperclass.getConstructor(ComputerBrand.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        Computer baseClassObject = null;
        try {
            assert baseConstructor != null;
            baseClassObject = (Computer) baseConstructor.newInstance(ComputerBrand.LENOVO);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        // Можна користуватися створеним об'єктом, так само, якщо б ми просто створили його за допомогою команди new
        assert baseClassObject != null;
        baseClassObject.setCores(4);
        baseClassObject.setCPUFrequency(10);

        // Працюємо з приватними полями
        Field privateRAM = null;
        Field privateMemory = null;

        try {
            privateRAM = Computer.class.getDeclaredField("RAM");
            privateRAM.setAccessible(true);
            privateMemory = Computer.class.getDeclaredField("memory");
            privateMemory.setAccessible(true);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        // Працюємо з усіма методами і анотацією
        methods = reflectedSuperclass.getDeclaredMethods();
        for(Method method: methods){
            Annotation annotation = method.getAnnotation(CustomNotation.class);
            if (annotation != null && annotation.annotationType() == CustomNotation.class){
                Class<?>[] pType  = method.getParameterTypes();
                Object[] params = new Object[pType.length];
                for (int i = 0; i < pType.length; i++) {
                    if(pType[i].toString().equals("int")){
                        params[i] = 0;
                    }
                }
                try {
                    method.invoke(baseClassObject, params);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
