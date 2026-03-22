package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.println("1".matches("\\d+"));
        System.out.println("".matches("\\d+"));
        System.out.println("007sadad".matches("\\d+"));
        System.out.println("qweq2qwds".matches("\\d+"));

        System.out.println("fucku".replace('u', 'r'));
    }


}