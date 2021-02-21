package hw;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class NotebookInvocationHandler implements InvocationHandler {
    private Notebook notebook;

    public NotebookInvocationHandler(Notebook notebook) {
        this.notebook = notebook;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if( method.getName().startsWith("get") && method.getReturnType().equals(Exception.class)){
            return method.invoke(notebook, args);
        }
        return method.invoke(notebook, args);
    }
}
