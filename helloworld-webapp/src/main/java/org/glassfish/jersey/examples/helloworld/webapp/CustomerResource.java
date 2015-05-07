package org.glassfish.jersey.examples.helloworld.webapp;

import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.PathSegment;
import javax.ws.rs.HeaderParam;

/**
 *
 * @author dawit.gebremichael1
 */
@Path("customer")
public class CustomerResource {

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String getHello() {
//        return "hello world";
        CoustomerDBAgent dba = new CoustomerDBAgent();
        MyList<NewCustomer> list = dba.getData();
        dba.close();
        String listcontent = "";
        
        
        for (int i=0; i<list.size ; i++) {
            NewCustomer a = list.get(i);
            listcontent = listcontent + "<p>email:" + a.getemail() + 
                    "                   password:" + a.getpassword() + "</p>\n";

        }
        
        
        
        
        String content = "<!DOCTYPE html PUBLIC \"-//IETF//DTD HTML 2.0//EN\">\n" +
"<HTML>\n" +
"   <HEAD>\n" +
"      <TITLE>\n" +
"         Customers List\n" +
"      </TITLE>\n" +
"   </HEAD>\n" +
"<BODY>\n" +
"   <H1>This is customer list</H1>\n" +
     listcontent +
"\n" +
"</BODY>\n" +
"</HTML>";
        return content;
    }

    @GET
    @Path("/{model : .+}/year/{year}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPicture(@PathParam("make") String make,
            @PathParam("model") List<PathSegment> car,
            @PathParam("year") String year) {
        return "Got it "+car.toString();
    }

    /***
     * /customers?start=0&size=10
     * @param start
     * @param size
     * @return 
     */
    @Path("/customers")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getCustomers(@QueryParam("start") int start,
            @QueryParam("size") int size) {
        CustomerManager cm = new CustomerManager();
        //JOptionPane.showMessageDialog(null, cm.getCustomers());
        String customerList = cm.getCustomers().toString();
        return "start = "+start+" size = "+size+" "+customerList;
    }
    
    /***
     * The form comes from users
     * @param inputEmail
     * @param inputPassword 
     */
    @Path("/customers")
    @POST 
    public void createCustomers(@FormParam("inputEmail") String inputEmail,
            @FormParam("inputPassword") String inputPassword) {
        System.out.println(" inputEmail "+inputEmail+" password"+
                "input password "+inputPassword);
        CoustomerDBAgent dba = new CoustomerDBAgent();
        if(dba.ifdbinit() == false){
            dba.initdb();
        }
        String res = dba.insertData(inputEmail, inputPassword);
        dba.close();
        System.out.println("Insert result :" + res);
    }
    
    
    
    /***
     * The form comes from users
     * @param inputEmail
     * @param inputPassword 
     */
    @Path("/customers")
    @PUT 
    public void changePassword(@FormParam("inputEmail") String inputEmail,
            @FormParam("inputPassword") String inputPassword) {
        System.out.println(" inputEmail "+inputEmail+" password"+
                "input password "+inputPassword);
        CoustomerDBAgent dba = new CoustomerDBAgent();
        if(dba.ifdbinit() == false){
            return;   // table is not even exist!
        }
        String res = dba.changePassword(inputEmail, inputPassword);
        dba.close();
        System.out.println("Update result :" + res);
    }
    
    /***
     * The form comes from users
     * @param inputEmail
     */
    @Path("/customers")
    @DELETE 
    public void deleteCustomer(@HeaderParam("inputEmail") String inputEmail
            ) {
        System.out.println(" inputEmail "+inputEmail);
        CoustomerDBAgent dba = new CoustomerDBAgent();
        if(dba.ifdbinit() == false){
            return;   // table is not even exist!
        }
        String res = dba.deleteCustomer(inputEmail);
        dba.close();
        System.out.println("Delete result :" + res);
    }
    
    
    public static void main(String[] args) {
         CustomerManager cm = new CustomerManager();
        JOptionPane.showMessageDialog(null, cm.getCustomers());
    }

}
