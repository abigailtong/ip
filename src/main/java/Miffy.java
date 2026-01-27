import java.util.Scanner;
public class Miffy {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String logo = " __  __ ___ _____ _____ __   __\n"
                + "|  \\/  |_ _|  ___|  ___| \\ \\ / /\n"
                + "| |\\/| || || |_  | |_   \\ \\ V / \n"
                + "| |  | || ||  _| |  _|   | | |  \n"
                + "|_|  |_|___|_|   |_|     |_| |_| \n";
        System.out.println("Hello from\n" + logo);
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

        while(true) {
            String input = in.nextLine();

            if(input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            }
            System.out.println(input);
            System.out.println("____________________________________________________________");
        }
    }
}
