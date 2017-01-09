/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amaws.model.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 *
 * @author BlackSpider
 */
@Component
public class AppContext implements ApplicationContextAware {
    /*
    Esta Clase sirve para hacer puente entre los beans manejados por spring y 
    las clases no manejadas por el mismo
    */
    private static ApplicationContext context;

    public static ApplicationContext getContext() {
        return context;
    }

    @Override
    public void setApplicationContext(ApplicationContext context)
            throws BeansException {
        this.context=context;
    }
}
