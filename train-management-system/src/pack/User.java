package pack;

import java.util.Scanner;
public class User{
    Scanner sc=new Scanner(System.in);
    String Name;
    int age;
    long contact;

    public String EnterDetails(){
        System.out.print("\n---------------------------------------------");
        System.out.print("\nPlease Provide Passenger's Details Below:  ");
        System.out.print("\n---------------------------------------------");
        System.out.print("\nName: ");
        Name=sc.nextLine();
        System.out.print("\nAge: ");
        age=sc.nextInt();
        System.out.print("\nContact Number: ");
        contact=sc.nextLong();
        sc.nextLine();
        System.out.print("\n---------------------------------------------");
        return Name+" "+ age +" "+ contact;
    }

    public String DATE() {
        String d= "";
        boolean flag1 = true;

        while (flag1) {
            int flag = 0, leap = 0, n = 0;
            System.out.print("\nEnter Date (DD/MM/YYYY) : ");
            d = sc.nextLine();
            String[] str = d.split("/");
            int a = Integer.parseInt(str[0]);
            int b = Integer.parseInt(str[1]);
            int c = Integer.parseInt(str[2]);
            if ((c % 400 == 0 || c % 4 == 0) && c % 100 != 0) {
                leap = 1;
            }
            if (c >= 2021 && c <= 2030) {
                if (b >= 1 && b <= 12) {
                    switch (b) {
                        case 1, 3, 5, 7, 8, 10, 12 -> {
                            n = 31;
                            break;
                        }
                        case 4, 6, 9, 11 -> {
                            n = 30;
                            break;
                        }
                        case 2 -> {
                            if (leap == 1) n = 29;
                            else n = 28;
                            break;
                        }
                    }
                    if (a >= 1 && a <= n) flag = 1;
                }
            }
            if (flag == 1) {
                flag1=false;
            } else {
                System.out.print("\n\nYou have entered Invalid Date..\n");
            }
        }
        return d;
    }
}
