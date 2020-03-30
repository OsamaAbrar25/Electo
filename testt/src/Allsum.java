import java.util.Scanner;

public class Allsum {
    private static int a;
    private static void sum(int n) {
        int s;
        String p = "";
        String c = "";
        int d;
        for(int i = n; i >= 1; i--) {
            d = n;
            s = i;
            p=p+n;
            while (d != a) {
                if (d + s <= a) {
                    p=p+s;
                    d = d + s;
                } else if (d + s > a) {
                    --s;
                }
            }

            if(!p.equals(c)) {
                System.out.print(p);
                System.out.println();
            }
            c=p;
            p="";
        }
    }

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number");
        a = sc.nextInt();
        int d = a-1;
        while(d >= 1)
        {
            sum(d);
            d--;
        }
    }
}
