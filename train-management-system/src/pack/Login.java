package pack;
import java.io.*;
import java.util.*;
public class Login{
    private String Username = "";
    private String Password = "";
    Console c=System.console();

    public void print_Tickets()
    {
        String str = login();

        if(!str.equals("Can't find the username!")){
            String[] user = str.split(" ");
            String[] s;
            try {
                String line;
                Scanner sc=new Scanner(new File("record.txt"));
                sc.nextLine();
                sc.nextLine();
                sc.nextLine();
                while(sc.hasNextLine()) {
                    line = sc.nextLine();
                    line = line.replaceAll("\\s+", " ");
                    s = line.split(" ");
                    if (s.length>5 && s[8].equals(user[0]) && s[9].equals(user[1])) {
                        System.out.println("\n\n\n---------------------------------------------------------------------------------");
                        System.out.print("=== \tTICKET DETAILS\t ===");
                        System.out.println("\n---------------------------------------------------------------------------------");
                        System.out.println("-\tTicket Number : "+s[7]);
                        System.out.println("-\tName : "+s[1]+"\n-\tAge : "+s[2]+"\n-\tContact Number : "+s[3]);
                        System.out.println("-\tDate : "+s[0]+"\n-\tTime : "+s[6]);
                        System.out.println("---------------------------------------------------------------------------------");
                        System.out.println("-\tSource : "+s[4]+"\n-\tDestination : "+s[5]);
                        System.out.println("---------------------------------------------------------------------------------");
                    }
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
        else{
            System.out.println("\nUsername doesn't exist!");
        }

    }

    public String signup(){
        Scanner sc=new Scanner(System.in);
        System.out.print("\nSet your username: ");
        Username=sc.next();
        System.out.print("\nSet your password: ");
        Password=sc.next();

        char[] pass2;String str;

        System.out.print("\nConfirm your password: ");
        str=sc.next();


        while(!str.equals(Password)){
            System.out.print("\nConfirm your password: ");
            pass2=c.readPassword();
            str=String.valueOf(pass2);
        }

        try {
            FileWriter fp=new FileWriter("UserDetails.txt",true);
            fp.write(Username+" "+Password+"\n");
            fp.close();
        } catch (IOException e) {
            System.out.println("Can't open the file from the directory!");
            e.printStackTrace();
        }
        System.out.println("-----------------------------------");
        System.out.println(" -You Have Successfully Signed up-");
        System.out.println("-----------------------------------");
        System.out.println();
        return Username+" "+str;
    }

    public String login(){
        Scanner sc=new Scanner(System.in);
        boolean flag=false;
        String line;
        System.out.print("\nUsername: ");
        Username=sc.next();

        File fp=new File("UserDetails.txt");
        try {
            sc=new Scanner(fp);
            while(sc.hasNextLine()){
                line=sc.nextLine();
                String[] s =line.split(" ");
                if(s[0].equals(Username)){
                    flag=true;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("UNABLE TO OPEN FILE!");
            e.printStackTrace();
        }
        Scanner naya = new Scanner(System.in);

        if(flag){
            System.out.print("\nPassword: ");
            String check=naya.next();

            while(!check.equals(Password)) {
                System.out.println("\nYou Have Entered incorrect password!\n");
                System.out.print("\nPlease enter your password again: ");
                Password=naya.next();

            }
            System.out.println("\n----------------------------------------");
            System.out.println("-You have successfully logged in!-");
            System.out.println("----------------------------------------");
            return Username+" "+Password;
        }
        else{
            System.out.println("\n--You have to sign up first!--");
            return "Can't find the username!";
        }
    }

    public String menu(){
        boolean flag=true;
        String output;
        output="EMPTY";
        Scanner scanner=new Scanner(System.in);
        while(flag){
            System.out.println("---------------------------------");
            System.out.println("-  1 : Sign up                  -");
            System.out.println("-  2 : Login                    -");
            System.out.println("-  3 : Go to the previous menu  -");
            System.out.print("---------------------------------\n\nEnter Your Choice: ");
            int a = scanner.nextInt();
            if (a == 1){
                output=signup();
                flag=false;
            }
            else if(a == 2)
            {
                output=login();
                if(!output.equals("Can't find the username!")){
                    flag=false;
                }

            }
            else{
                break;
            }
        }
        return output;
    }
}
