/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package path;

import java.util.ArrayList;
import java.util.List;

import static javax.swing.text.html.HTML.Tag.HEAD;

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

    public static void _42(boolean value, List<String> path){

        path.add("42");

        if(value)
        {
            path.add("(T)-");
            path.add("43-");
            _44(true,path);
            _44(false,path);
        }
        else
        {
            path.add("(F)-");
            _47(true,path);
            _47(false,path);
        }
    }

    public static void _44(boolean value, List<String> path){

        path.add("44");

        if(value)
        {
            path.add("(T)-");
            path.add("45-");
            path.add("46-");
            _47(true,path);
            _47(false,path);
        }
        else
        {
            path.add("(F)-");
            path.add("46-");
            _47(true,path);
            _47(false,path);
        }
    }
    public static void _47(boolean value, List<String> path){

        path.add("47");

        if(value)
        {
            path.add("(T)-");
            path.add("48-");
            _49(true,path);
            _49(false,path);
        }
        else
        {
            path.add("(F)-");
            _49(true,path);
            _49(false,path);
        }
    }
    public static void _49(boolean value, List<String> path){

        path.add("49");

        if(value)
        {
            path.add("(T)-");
            path.add("50-");
            path.add("51-");
            _52(true,path);
            _52(false,path);
        }
        else
        {
            path.add("(F)-");
            path.add("51-");
            _52(true,path);
            _52(false,path);
        }
    }
    public static void _52(boolean value, List<String> path){

        path.add("52");

        if(value)
        {
            path.add("(T)-");
            path.add("53-");
            path.add("54-");
            _55(true,path);
        }
        else
        {
            path.add("(F)-");
            _55(true,path);
        }
    }
    public static void _55(boolean value, List<String> path){

        path.add("55");

        if(value)
        {
            path.add("(T)-");
            path.add("56-");
            _57(true,path);
            _57(false,path);
        }

    }
    public static void _57(boolean value, List<String> path){

        path.add("57");

        if(value)
        {
            path.add("(T)-");
            path.add("58-");
            _59(true,path);
            _59(false,path);
        }
        else
        {
            path.add("(F)-");
            _59(true,path);
            _59(false,path);
        }
    }
    public static void _59(boolean value, List<String> path){

        path.add("59");

        if(value)
        {
            path.add("(T)-");
            path.add("60-");
            _62(true,path);
            _62(false,path);
        }
        else
        {
            path.add("(F)-");
            path.add("61-");
            _66(true,path);
            _66(false,path);
        }
    }
    public static void _62(boolean value, List<String> path){

        path.add("62");

        if(value)
        {
            path.add("(T)-");
            path.add("63-");
            path.add("68-");
            _69(true,path);
            _69(false,path);
        }
        else
        {
            path.add("(F)-");
            _64(true,path);
            _64(false,path);
        }
    }
    public static void _64(boolean value, List<String> path){

        path.add("64");

        if(value)
        {
            path.add("(T)-");
            path.add("65-");
            path.add("68-");
            _69(true,path);
            _69(false,path);
        }
        else
        {
            path.add("(F)-");
            path.add("68-");
            _69(true,path);
            _69(false,path);
        }
    }
    public static void _66(boolean value, List<String> path){

        path.add("66");

        if(value)
        {
            path.add("(T)-");
            path.add("67-");
            _74(true,path);
            _74(false,path);
        }
        else
        {
            path.add("(F)-");
            _74(true,path);
            _74(false,path);
        }
    }
    public static void _69(boolean value, List<String> path){

        path.add("69");

        if(value)
        {
            path.add("(T)-");
            path.add("70-");
            _111(true,path);
            _111(false,path);
        }
        else
        {
            path.add("(F)-");
            path.add("71-");
            path.add("77-");
            path.add("78-");
            _98(true,path);
            _98(false,path);
        }
    }
    public static void _74(boolean value, List<String> path){

        path.add("74");

        if(value)
        {
            path.add("(T)-");
            path.add("75-");
            _76(true,path);
            _76(false,path);
        }
        else
        {
            path.add("(F)-");
            _98(true,path);
            _98(false,path);
        }
    }
    public static void _72(boolean value, List<String> path){

        path.add("72");

        if(value)
        {
            path.add("(T)-");
            path.add("73-");
            path.add("77-");
            path.add("78-");
            _98(true,path);
            _98(false,path);
        }
        else
        {
            path.add("(F)-");
            _111(true,path);
            _111(false,path);
        }
    }
    public static void _76(boolean value, List<String> path){

        path.add("76");

        if(value)
        {
            path.add("(T)-");
            path.add("77-");
            _79(true,path);
            _79(false,path);
        }
        else
        {
            path.add("(F)-");
            path.add("78-");
            _80(true,path);
            _80(false,path);
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

    public static void _79(boolean value, List<String> path) {

        path.add("79");

        if(value)
        {
            path.add("(T)-");
            path.add("85-");
            _86(true,path);
            _86(false,path);
        }
        else
        {
            path.add("(F)-");
            path.add("88-");
            _89(true,path);
            _89(false,path);
        }
    }

    public static void _89(boolean value, List<String> path) {

        path.add("89");

        if(value)
        {
            path.add("(T)-");
            path.add("90-");
            _91(true,path);
<<<<<<< HEAD
            _9(false,path);
=======
            _91(false,path);
>>>>>>> origin/master
        }
        else
        {
            path.add("(F)-");
            path.add("93-");
            _94(true,path);
            _94(false,path);
        }
    }
    public static void _86(boolean value, List<String> path) {

        path.add("86");

        if(value)
        {
            path.add("(T)-");
            path.add("87-");
            path.add("88-");
            _89(true,path);
            _89(false,path);
        }
        else
        {
            path.add("(F)-");
            _79(true,path);
            _79(false,path);
        }
    }
    public static void _80(boolean value, List<String> path) {

        path.add("80");

        if(value)
        {
            path.add("(T)-");
            path.add("81-");
            _82(true,path);
            _82(false,path);
        }
        else
        {
            path.add("(F)-");
            path.add("84-");
            path.add("88-");
            _89(true,path);
            _89(false,path);
        }
    }
    public static void _82(boolean value, List<String> path) {

        path.add("82");

        if(value)
        {
            path.add("(T)-");
            path.add("83-");
            path.add("84-");
            path.add("88-");
            _89(true,path);
            _89(false,path);
        }
        else
        {
            _80(true,path);
            _80(false,path);
        }
    }
    public static void _91(boolean value, List<String> path) {

        path.add("91");

        if(value)
        {
            path.add("(T)-");
            path.add("92-");
            path.add("93-");
            _94(true,path);
            _94(false,path);
        }
        else
        {
            path.add("(F)-");
<<<<<<< HEAD
            _103(true,path);
            _103(false,path);
=======
            _89(true,path);
            _89(false,path);
>>>>>>> origin/master
        }
    }
    public static void _94(boolean value, List<String> path) {

        path.add("94");

        if(value)
        {
            path.add("(T)-");
            path.add("95-");
<<<<<<< HEAD
            _100(true,path);
            _100(false,path);
=======
            _96(true,path);
            _96(false,path);
>>>>>>> origin/master
        }
        else
        {
            path.add("(F)-");
<<<<<<< HEAD
            _101(true,path);
            _101(false,path);
=======
            _98(true,path);
            _98(false,path);
>>>>>>> origin/master
        }
    }

    public static void _96(boolean value, List<String> path) {

        path.add("96");

        if(value)
        {
            path.add("(T)-");
            path.add("97-");
            _98(true,path);
            _98(false,path);
        }
        else
        {
            path.add("(F)-");
            _94(true,path);
            _94(false,path);
        }
    }
    public static void _98(boolean value, List<String> path) {

        path.add("98");

        if(value)
        {
            path.add("(T)-");
            path.add("99-");
            _100(true,path);
            _100(false,path);
        }
        else
        {
            path.add("(F)-");
            _100(true,path);
            _100(false,path);
        }
    }
    public static void _100(boolean value, List<String> path) {

        path.add("100");

        if(value)
        {
            path.add("(T)-");
            _101(true,path);
            _101(false,path);
        }
        else
        {
            path.add("(F)-");
            _111(true,path);
            _111(false,path);
        }
    }
    public static void _101(boolean value, List<String> path) {

        path.add("101");

        if(value)
        {
            path.add("(T)-");
            path.add("102-");
            path.add("105-");
            _107(true,path);
            _107(false,path);
        }
        else
        {
            path.add("(F)-");
            _103(true,path);
            _103(false,path);
        }
    }

    public static void _103(boolean value, List<String> path) {

        path.add("103");

        if(value)
        {
            path.add("(T)-");
            path.add("104-");
            path.add("105-");
            _107(true,path);
            _107(false,path);
        }
        else
        {
            path.add("(F)-");
            path.add("105-");
            _107(true,path);
            _107(false,path);
        }
    }
    public static void _107(boolean value, List<String> path) {

        path.add("107");

        if(value)
        {
            path.add("(T)-");
            path.add("106-");
<<<<<<< HEAD
            _111(true,path);
            _111(false,path);
=======
            _108(true,path);
            _108(false,path);
>>>>>>> origin/master
        }
        else
        {
            path.add("(F)-");
            path.add("110-");
            _111(true,path);
            _111(false,path);
        }
    }
    public static void _108(boolean value, List<String> path) {

        path.add("108");

        if(value)
        {
            path.add("(T)-");
            path.add("109-");
            path.add("110-");
            _111(true,path);
            _111(false,path);
        }
        else
        {
            path.add("(F)-");
<<<<<<< HEAD
            _111(true,path);
            _111(false,path);
=======
            _107(true,path);
            _107(false,path);
>>>>>>> origin/master
        }
    }
    public static void _111(boolean value, List<String> path) {

        path.add("111");

        if(value)
        {
            path.add("(T)-");
            path.add("112-");
            path.add("113");
        }
        else
        {
            path.add("(F)-");
            path.add("113");
        }
    }
}
