package com.amc.bfsi;

import com.amc.bfsi.config.AppConfig;
import com.amc.bfsi.model.Customer;
import com.amc.bfsi.service.CustomerService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/*The ApplicationContext is the core heart of the 
 * Spring Framework.It acts as Spring'sInversion
 *  of Control(IoC)container,meaning it takes
 *   over the responsibility of instantiating,
 *   configuring,assembling,and managing objects
 *   (known as Spring beans)throughout their lifecycle.

*/public class MainApp {

    public static void main(String[] args) {

        // The Spring container starts here
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        // We ask the container for the bean instead of calling new
        CustomerService service = context.getBean(CustomerService.class);

        service.register(new Customer(101, "Anita Rao", "Bangalore"));
        service.register(new Customer(102, "Vikram Shah", "Mumbai"));

        System.out.println("All customers: " + service.getAll());
        System.out.println("Customer 101: " + service.getById(101));

        System.out.println("\nBeans created by the container:");
        for (String name : context.getBeanDefinitionNames()) {
            if (name.startsWith("inMemory") || name.startsWith("customer")) {
                System.out.println("  " + name);
            }
        }
    }
}
