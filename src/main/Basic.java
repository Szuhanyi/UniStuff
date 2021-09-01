package main;

public class Basic {

    public Basic() {
        System.out.println("1");
    }

    public Basic(String s) {
        System.out.println("2" + s);
    }

    public Basic( String s, String s2) {
        this();
        System.out.println("3");
    }

}
