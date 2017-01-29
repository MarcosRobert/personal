/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.packtpub.as7development.chapter3.client;

import com.packtpub.as7development.chapter3.NotEnoughMoneyException;
import com.packtpub.as7development.chapter3.SeatBookedException;
import com.packtpub.as7development.chapter3.TheatreBooker;
import com.packtpub.as7development.chapter3.TheatreBookerBean;
import com.packtpub.as7development.chapter3.TheatreInfo;
import com.packtpub.as7development.chapter3.TheatreInfoBean;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;


/**
 *
 * @author CHICHO
 */
public class RemoteEJBClient {

    private final static java.util.logging.Logger logger = Logger.
            getLogger(RemoteEJBClient.class.getName());
    private final static Hashtable jndiProperties = new Hashtable();

    public static void main(String[] args) throws Exception {
        ClassLoader cl = ClassLoader.getSystemClassLoader();

        URL[] urls = ((URLClassLoader)cl).getURLs();

        for(URL url: urls){
        	System.out.println(url.getFile());
        }
        Logger.getLogger("org.jboss").setLevel(Level.SEVERE);
        Logger.getLogger("org.xnio").setLevel(Level.SEVERE);
        testRemoteEJB();
    }

    private static void testRemoteEJB() throws NamingException {
        jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
final TheatreInfo info = lookupTheatreInfoEJB();
        final TheatreBooker book = lookupTheatreBookerEJB();
        String command = "";
        /* not included for brevity. Prints out a Welcome message with
the available commands */
        dumpWelcomeMessage();
		  boolean runapp = true;
        while (runapp) {
			  
			  runapp = false;
            command = "list";
            if (command.equals("book")) {
                int seatId = 0;
                try {
                    seatId = 1;
                } catch (NumberFormatException e1) {
                    logger.info("Wrong seatid format!");
                    continue;
                }
                try {
                    String retVal = book.bookSeat(seatId - 1);
                } catch (SeatBookedException e) {
                    logger.info(e.getMessage());
                    continue;
                } catch (NotEnoughMoneyException ex) {
                    logger.info(ex.getMessage());
                }
            } else if (command.equals("list")) {
                logger.info(info.printSeatList().toString());
                continue;
            } else if (command.equals("quit")) {
                logger.info("Bye");
                break;
            } else {
                logger.info("Unknown command " + command);
            }
				break;
        }
    }

    private static void dumpWelcomeMessage(){
        System.err.println("Hello");
    }
    private static TheatreInfo lookupTheatreInfoEJB() throws
            NamingException {
        final Context context = new InitialContext(jndiProperties);
//        return (TheatreInfo) context.lookup("ejb:/ticket-agency-ejb/TheatreInfoBean!com.packtpub.as7development.chapter3.ejb.TheatreInfo");
                                             //ejb:/ticket-agency-ejb-1.0-SNAPSHOT//TheatreInfoBean!com.packtpub.as7development.chapter3.TheatreInfo        
final String appName = "";
        // This is the module name of the deployed EJBs on the server. This is typically the jar name of the
        // EJB deployment, without the .jar suffix, but can be overridden via the ejb-jar.xml
        // In this example, we have deployed the EJBs in a jboss-as-ejb-remote-app.jar, so the module name is
        // jboss-as-ejb-remote-app
        final String moduleName = "ticket-agency-ejb-1.0-SNAPSHOT";
        // AS7 allows each deployment to have an (optional) distinct name. We haven't specified a distinct name for
        // our EJB deployment, so this is an empty string
        final String distinctName = "";
        // The EJB name which by default is the simple class name of the bean implementation class
        final String beanName = TheatreInfoBean.class.getSimpleName();
        // the remote view fully qualified class name
        final String viewClassName = TheatreInfo.class.getName();
        // let's do the lookup
        return (TheatreInfo) context.lookup("ejb:" + appName + "/" + moduleName + "/" + distinctName + "/" + beanName + "!" + viewClassName);
        
        
    }
    private static TheatreBooker lookupTheatreBookerEJB() throws
            NamingException {
        final Context context = new InitialContext(jndiProperties);
//        return (TheatreBooker) context.lookup("ejb:/ticket-agency-ejb-1.0-SNAPSHOT/TheatreBookerBean!com.packtpub.as7development.chapter3.ejb.TheatreBook er?stateful");
 final String appName = "";
        // This is the module name of the deployed EJBs on the server. This is typically the jar name of the
        // EJB deployment, without the .jar suffix, but can be overridden via the ejb-jar.xml
        // In this example, we have deployed the EJBs in a jboss-as-ejb-remote-app.jar, so the module name is
        // jboss-as-ejb-remote-app
        final String moduleName = "ticket-agency-ejb-1.0-SNAPSHOT";
        // AS7 allows each deployment to have an (optional) distinct name. We haven't specified a distinct name for
        // our EJB deployment, so this is an empty string
        final String distinctName = "";
        // The EJB name which by default is the simple class name of the bean implementation class
        final String beanName = TheatreBookerBean.class.getSimpleName();
        // the remote view fully qualified class name
        final String viewClassName = TheatreBooker.class.getName();
        // let's do the lookup
        return (TheatreBooker) context.lookup("ejb:" + appName + "/" + moduleName + "/" + distinctName + "/" + beanName + "!" + viewClassName + "?stateful");
        
    }
}
