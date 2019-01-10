package edu.touro.mco152;

public class Singleton {

    private static Singleton instance;

    private Singleton(){

    }

    public  static Singleton getInstance(){
        //If instance if not set
        if (instance == null){
            synchronized (Singleton.class){
                if (instance == null){
                    //Create instance
                    instance = new Singleton();
                }
            }
        }

        //Return instance
        return instance;
    }

    public static String getString(){
        return "Hello World";
    }
}
