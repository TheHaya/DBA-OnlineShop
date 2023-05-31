/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package util;

import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author Haya
 */
@Named(value = "sqlBean")
@SessionScoped
public class sqlBean implements Serializable {

    /**
     * Creates a new instance of sqlBean
     */
    public sqlBean() {
    }
    
}
