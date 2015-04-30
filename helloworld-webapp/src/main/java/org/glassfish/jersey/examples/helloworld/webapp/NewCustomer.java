
package org.glassfish.jersey.examples.helloworld.webapp;

/**
 *
 * @author ltcn
 */


public class NewCustomer {
    private String email;
    private String password;
    
    NewCustomer(String a, String b){
        this.email = a;
        this.password = b;
    }
    
    public void setemail(String a){
        this.email = a;
    }
    
    public String getemail(){
        return this.email;
    }
    
    public void setpassword(String b){
        this.password = b;
    }
    
    public String getpassword(){
        return this.password;
    }
}
