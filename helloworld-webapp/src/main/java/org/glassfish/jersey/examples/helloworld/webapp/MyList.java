
package org.glassfish.jersey.examples.helloworld.webapp;

/**
 *
 * @author ltcn
 * @param <T>
 */


public class MyList <T> {
    private T[] buffer;
    public int size;
    private int sizelimit;
    
    public MyList(T[] a, int b){    
// Since Java simply does not support generic array, I have no choice but put storage space outside...
// 
        buffer = a;
        size = 0;
        sizelimit=b;
    }
    
    public boolean add(T a){
        if(sizelimit <= size + 1){
            return false;
        }
        buffer[size] = a;
        size++;  
        return true;
    }
    
    public T get(int i){
        if (i < size) {
            return buffer[i];
        } else {
            return null;
        }
    }
    
}
