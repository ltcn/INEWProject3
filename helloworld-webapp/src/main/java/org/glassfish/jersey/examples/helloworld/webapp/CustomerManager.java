
package org.glassfish.jersey.examples.helloworld.webapp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author dawit.gebremichael1
 */


public class CustomerManager {
    List<Customer> customerList = new ArrayList();
    
   public List<Customer> getCustomers(){
        try {
            loadCustomers();
        } catch (IOException ex) {
            Logger.getLogger(CustomerManager.class.getName()).log(Level.SEVERE, null, ex);
        }
       return customerList;
   }
    
    private  void loadCustomers() throws FileNotFoundException, IOException{
        FileReader fr = new FileReader("/Users/dawitgebremichael/NetBeansProjects/helloworld-webapp/customers.txt");
        BufferedReader br = new BufferedReader(fr);
        String ln;
        
        
        while((ln = br.readLine()) != null){
            StringTokenizer st = new StringTokenizer(ln,",");
            
            Customer customer = new Customer();
            customer.setName(st.nextToken());
            customer.setAddress(st.nextToken());
            customerList.add(customer);
            
        }
        
       // JOptionPane.showMessageDialog(null, customerList.size());
    }
    
    public static void main(String[] args) throws IOException {
        Customer cs = new Customer();
        cs.loadCustomers();
    }
    
}
