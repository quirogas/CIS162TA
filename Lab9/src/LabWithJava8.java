import javax.swing.*;
import java.util.ArrayList;

/*********************************************************************************************************************
 * This is an example of lab 9 with some java 8 functionality.
 * Reference links:
 *  1. https://www.mkyong.com/java8/java-8-streams-filter-examples/
 *  2. https://www.mkyong.com/java8/java-8-streams-map-examples/
 *  3.Java API (stream, map, filter, lambda)
 * @author Santiago Quiroga
 ********************************************************************************************************************/
public class LabWithJava8 {
    /** Field Variable */
    ArrayList<String> list;

    /**
     * Class' constructor. initializes the field variable.
     */
    public LabWithJava8(){
        list = new ArrayList<String>();
    }

    /**
     *  This method adds a word to the list as long a the String isn't empty.
     */
    public void addWord(String str){
       if (!str.isEmpty())
           list.add(str);
    }

    /**
     * This method displays an array with all the onjects inside the ArrayList.
     */
    public void display(){
        System.out.println(list);
    }

    /**
     *  This method attempts to add the num word to the list. If the word is an empty String, then it isn't
     *  added to the list.
     * @param num
     */
    public void addWords(int num){
        while (num > 0){
           addWord(JOptionPane.showInputDialog("Enter a String pls"));
           num --;
        }
    }

    /**
     *This method return the id of the given str. If the str does not exist in the list, then it return -1 .
     * @param str
     * @return
     */
    public int search(String str){
//        //WITH A FOR LOOP
//        for (int i = 0; i < list.size(); i++) {
//            if (str.equalsIgnoreCase(list.get(i)))
//                return i;
//        }
//
//        //WITH A FOREACH LOOP
//        for (String strFromArray: list) {
//            if (str.equalsIgnoreCase(strFromArray))
//                return list.indexOf(strFromArray);
//         }

        //WITH JAVA 8 API
        return list.stream().filter(strInList -> strInList.equalsIgnoreCase(str)).findFirst().map(strInList -> list.indexOf(strInList)).orElse(-1);
    }

    /**
     * This method displays all the elements in the list one by one, each in a new line.
     */
    public void displayElementPerLine() {
//        //WITH FOR LOOP
//        for (int i = 0; i < list.size(); i++) {
//            System.out.println(list.get(i));
//        }
//        //WITH FOR EACH LOOP
//        for (String strFromArray: list) {
//            System.out.println(strFromArray);
//        }

        //WITH JAVA 8 API
        list.forEach(System.out::println);
    }

    /**
     * This method removes every other element in the list.
     */
    public void removeEveryOther(){
        for (int i = 0; i < list.size() ; i++) list.remove(i);
    }

    /**
     *  This is the main method of the Class.
     * @param args
     */
    public static void main(String[] args) {
        LabWithJava8 object = new LabWithJava8();
        object.addWords(3);
        System.out.println(object.search("five"));
        object.display();
        object.displayElementPerLine();
        object.removeEveryOther();
    }
}


