import pack.User;
import pack.Login;
import pack.FileHandling;
import java.io.*;
import java.util.*;

public class Train{
    public static void main(String[] args) {

        File myfile=new File("./src/Train.txt");
        User U=new User();
        Scanner sc=new Scanner(System.in);
        FileHandling op=new FileHandling();
        int p,q,r;String upd;
        op.starter();
        Login T=new Login();
        while (true) {
            System.out.println("\n");
            System.out.println("---------------------------------------------");
            System.out.println("-  1 : Search Trains                        -");
            System.out.println("-  2 : Cancel Booked Tickets                -");
            System.out.println("-  3 : Book your Ticket with Train Number   -");
            System.out.println("-  4 : Print Booked Tickets                 -");
            System.out.println("-  5 : Exit                                 -");
            System.out.print("---------------------------------------------\n\nEnter Your Choice: ");
            p = sc.nextInt();

            if (p == 1) {
                System.out.print("\nEnter the location from where you want to board the train: ");
                String src = sc.next();
                System.out.print("\nEnter the destination of your journey: ");
                String dest = sc.next();

                boolean flag = op.read(src, dest, myfile);
                if (flag) {
                    System.out.println("------------------------------------");
                    System.out.println("-  1 : Book Train from List        -");
                    System.out.println("-  2 : Go To Previous menu         -");
                    System.out.print("------------------------------------\n\nEnter Your Choice: ");
                    r = sc.nextInt();
                    if (r == 1) {
                        upd = T.menu();
                        if (!upd.equals("EMPTY")) {
                            System.out.print("\nEnter the Train Number: ");
                            int trainNumber = sc.nextInt();
                            String confirm = op.read(trainNumber, myfile);
                            if (!confirm.equalsIgnoreCase("Cannot find a train with the number you provided!")) {
                                System.out.print("\nEnter the total number of passengers for Ticket Booking : ");
                                q = sc.nextInt();
                                String temp;
                                if (q > 1) temp = q + " passengers are";
                                else temp = q + " passenger is";
                                String Date = U.DATE();
                                for (int i = 1; i <= q; i++) {
                                    String kj = U.EnterDetails();
                                    op.appendinlist(Date + " " + kj + " " + confirm + " " + upd);
                                }
                                System.out.println();
                                System.out.println("-----------------------------------------------------------");
                                System.out.println("Ticket for " + temp + " Booked Successfully..");
                                System.out.println("-----------------------------------------------------------");
                            }
                        } else {
                            System.out.println("\nPlease enter a valid Train Number!");
                        }
                    }

                } else {
                    System.out.println("\nSorry. There are no trains available from " + src + " to " + dest);
                }
            } else if (p == 2) {
                String conf = T.login();
                if (!conf.equals("Can't find the username!")) {
                    System.out.print("\nEnter the ticket number of the ticket you would like to cancel: ");
                    sc.nextLine();
                    String delete = sc.nextLine();
                    boolean flag = op.renewrecord(delete, conf);
                    if (flag) {
                        System.out.println("-----------------------------------------------------------------");
                        System.out.println("Ticket : " + delete + " is Cancelled Successfully!");
                        System.out.println("-----------------------------------------------------------------");
                    } else {
                        System.out.println("\nYou have entered invalid ticket number!");
                    }
                } else {
                    System.out.println("\nUsername doesn't exist!");
                }
            } else if (p == 3) {
                System.out.print("\nEnter the Train Number : ");
                sc.nextLine();
                String trainNumber = sc.nextLine();
                String output = op.read(trainNumber, myfile);

                if (!output.equals("Cannot find a train with the number you provided!")) {
                    upd = T.menu();
                    if (!upd.equals("EMPTY")) {
                        System.out.print("\nEnter Total number of Passengers for Ticket Booking : ");
                        q = sc.nextInt();
                        String temp;
                        if (q > 1) temp = q + " passengers are";
                        else temp = q + " passenger is";
                        String Date = U.DATE();
                        for (int i = 1; i <= q; i++) {
                            System.out.println("-----------------------------------------------------------");
                            String kj = U.EnterDetails();
                            op.appendinlist(Date + " " + kj + " " + output + " " + upd);
                        }
                        System.out.println("\n--------------------------------------------");
                        System.out.println("Ticket for " + temp + " Booked Successfully!");
                        System.out.println("--------------------------------------------");
                    }
                } else {
                    System.out.println("\nYou have entered invalid Train Number!");
                }
            } else if (p == 4) {
                T.print_Tickets();
            } else {
                break;
            }
        }
        System.out.println("Thank you for using our system! We hope to see you again! :D");
    }
}
