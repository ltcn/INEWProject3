
package org.glassfish.jersey.examples.helloworld.webapp;

/**
 *
 * @author dawit.gebremichael1
 */


public class Customer {
    private String name;
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    void loadCustomers() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return "name = "+name+" address "+address;
    }
    
    
}
