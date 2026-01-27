import java.util.Scanner;
public class Miffy {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String logo = " __  __ ___ _____ _____ __   __\n"
                + "|  \\/  |_ _|  ___|  ___| \\ \\ / /\n"
                + "| |\\/| || || |_  | |_   \\ \\ V / \n"
                + "| |  | || ||  _| |  _|   | | |  \n"
                + "|_|  |_|___|_|   |_|     |_|_| \n";
        System.out.println("Hello from\n" + logo);
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

        Scanner scanner = new Scanner(System.in);
        handleUserInput(scanner);
    }

    public static void handleUserInput(Scanner scanner) {
        String[] list = new String[100];
        int numItems = 0;

        while (true) {
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equalsIgnoreCase("list")) {
                printList(list, numItems);
            } else if (input.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else {
                printInput(input);
                list[numItems] = input;
                numItems++;
            }
        }
        scanner.close();
    }

    public static void printInput(String input) {
        System.out.print("added: ");
        System.out.println(input);
        System.out.println("____________________________________________________________");
    }

    public static void printList(String[] list, int numItems) {
        for (int i = 0; i < numItems; i++) {
            int number = i + 1;
            String user = list[i];

            System.out.println("     " + number + ". " + user);
        }
        System.out.println("____________________________________________________________");
    }
}
