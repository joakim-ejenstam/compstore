/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.*;
import java.sql.*;
import java.io.*;

/**
 *
 * @author Martin
 */
public class ManagerBean {
    private ComponentListBean compList;
    private ComputerListBean cpuList;
    private String url = null;
    
    public ManagerBean(ComponentListBean _compList, ComputerListBean _cpuList){
        compList = _compList;
        cpuList = _cpuList;
    }
}
