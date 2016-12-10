/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package path;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Selman Ahatlı
 */

public class Path {
    
    public static void _5(boolean value, List<String> path){
        
        path.add("5");
        
        if(value)
        {
            path.add("(T)-");
            path.add("6-");
            _10(true,path);
            _10(false,path);
        }
        else
        {
            path.add("(F)-");
            _2(true,path);
            _2(false,path);
        }    
    }
    
    public static void _7(boolean value, List<String> path){
        
        path.add("7");
        
        if(value)
        {
            path.add("(T)-");
            path.add("8-");
            printList(path);
            _9(true,path);
            _9(false,path);
        }
        else
        {
            path.add("(F)-");
            path.add("13-");
            printList(path);
            _14(true,path);
            _14(false,path);
        }  
    }
    public static void _2(boolean value, List<String> path) {
        
        path.add("2");
        
        if(value)
        {
            path.add("(T)-");
            path.add("3-");
            _5(true,path);
            _5(false,path);
        }
        else
        {
            path.add("(F)-");
            path.add("4-");
            _7(true,path);
            _7(false,path);
        }
    }
    
    public static void main(String[] args) {
        
        List<String> path = new ArrayList<>();
       
        path.add("1-");
        _2(true,path);
        _2(false,path);
    }
    
    public static void printList(List<String> path)
    {
        for (int i = 0; i < path.size(); ++i) {
            
            System.out.print(path.get(i));
            
        }
        
        System.out.println("");
    }
}
