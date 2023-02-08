package bean;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.annotation.Annotation;

public class Test {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        Teacher t = new Teacher();
        Student[] ss = {new Student()};
        t.setStudents(ss);
        checkObjifNull(t);

    }

    public static void checkObjifNull(Object obj) throws InvocationTargetException, IllegalAccessException {
        Method[] methods = obj.getClass().getDeclaredMethods();

        for (int i = 0; i < methods.length; i++) {
            Method m = methods[i];
            Annotation[] annotations = m.getAnnotations();

            System.out.println(methods[i].getName());

            for (int j = 0; j < annotations.length; j++) {
                Annotation annotation = annotations[j];
                if (annotation.annotationType() == NotNull.class){
                    Object result = m.invoke(obj);
                    if (result == null){
                        throw new IllegalArgumentException("Bosh olmasin...");
                    }
                    if (result instanceof Object[]){
                        Object[] arr = (Object[]) result;
                        if (arr.length == 0){
                            throw new IllegalArgumentException("Bosh olmasin arr...");
                        }
                    }
                }
                System.out.println("Annotation = "+annotation.annotationType());
            }
            System.out.println("---------------------");
        }
    }


}
