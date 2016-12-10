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
    
    public static void _38(boolean value, List<String> path){
        
        path.add("38");
        
        if(value)
        {
            path.add("(T)-");
            path.add("39-");
            path.add("40-");
            path.add("41-");
            _42(true,path);
            _42(false,path);
        }
        else
        {
            path.add("(F)-");
            path.add("40-");
            path.add("41-");
            _42(true,path);
            _42(false,path);
        }    
    }
    
    public static void _29(boolean value, List<String> path){
        
        path.add("29");
        
        if(value)
        {
            path.add("(T)-");
            path.add("30-");
            _38(true,path);
            _38(false,path);
        }
        else
        {
            path.add("(F)-");
            _38(true,path);
            _38(false,path);
        }    
    }
    
    public static void _35(boolean value, List<String> path){
        
        path.add("35");
        
        if(value)
        {
            path.add("(T)-");
            path.add("36-");
            path.add("37-");
            _52(true,path);
            _52(false,path);
        }
        else
        {
            path.add("(F)-");
            _33(true,path);
            _33(false,path);
        }    
    }
    
    public static void _27(boolean value, List<String> path){
        
        path.add("27");
        
        if(value)
        {
            path.add("(T)-");
            path.add("28-");
            _29(true,path);
            _29(false,path);
        }
        else
        {
            path.add("(F)-");
            _29(true,path);
            _29(false,path);
        }    
    }
    
    public static void _26(boolean value, List<String> path){
        
        path.add("26");
        
        if(value)
        {
            path.add("(T)-");
            _27(true,path);
            _27(false,path);
        }
        else
        {
            path.add("(F)-");
            path.add("41-");
            _42(true,path);
            _42(false,path);
        }    
    }
    
    public static void _33(boolean value, List<String> path){
        
        path.add("33");
        
        if(value)
        {
            path.add("(T)-");
            path.add("34-");
            _35(true,path);
            _35(false,path);
        }
        else
        {
            path.add("(F)-");
            path.add("37-");
            _52(true,path);
            _52(false,path);
        }    
    }
    
    public static void _22(boolean value, List<String> path){
        
        path.add("22");
        
        if(value)
        {
            path.add("(T)-");
            path.add("23-");
            path.add("31-");
            path.add("32-");
            _33(true,path);
            _33(false,path);
        }
        else
        {
            path.add("(F)-");
            _20(true,path);
            _20(false,path);
        }    
    }
    
    public static void _20(boolean value, List<String> path){
        
        path.add("20");
        
        if(value)
        {
            path.add("(T)-");
            path.add("21-");
            _22(true,path);
            _22(false,path);
        }
        else
        {
            path.add("(F)-");
            path.add("31-");
            path.add("32-");
            _33(true,path);
            _33(false,path);
        }    
    }
    
    public static void _16(boolean value, List<String> path){
        
        path.add("16");
        
        if(value)
        {
            path.add("(T)-");
            path.add("17-");
            path.add("18-");
            path.add("19-");
            _20(true,path);
            _20(false,path);
        }
        else
        {
            path.add("(F)-");
            _14(true,path);
            _14(false,path);
        }    
    }
    
    public static void _24(boolean value, List<String> path){
        
        path.add("24");
        
        if(value)
        {
            path.add("(T)-");
            path.add("25-");
            _26(true,path);
            _26(false,path);
        }
        else
        {
            path.add("(F)-");
            _26(true,path);
            _26(false,path);
        }    
    }
    
    public static void _10(boolean value, List<String> path){
        
        path.add("10");
        
        if(value)
        {
            path.add("(T)-");
            path.add("11-");
            _24(true,path);
            _24(false,path);
        }
        else
        {
            path.add("(F)-");
            _5(true,path);
            _5(false,path);
        }    
    }
    
    public static void _9(boolean value, List<String> path){
        
        path.add("9");
        
        if(value)
        {
            path.add("(T)-");
            path.add("12-");
            path.add("13-");
            _14(true,path);
            _14(false,path);
        }
        else
        {
            path.add("(F)-");
            _7(true,path);
            _7(false,path);
        }    
    }
    
    public static void _14(boolean value, List<String> path){
        
        path.add("14");
        
        if(value)
        {
            path.add("(T)-");
            path.add("15-");
            _16(true,path);
            _16(false,path);
        }
        else
        {
            path.add("(F)-");
            path.add("18-");
            path.add("19-");
            _20(true,path);
            _20(false,path);
        }    
    }
    
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
            _9(true,path);
            _9(false,path);
        }
        else
        {
            path.add("(F)-");
            path.add("13-");
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
