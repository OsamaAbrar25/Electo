
import java.util.Scanner;

class test2
{
    public static void main(String[] args)
    {
        int f = 0;
        Scanner sc = new Scanner((System.in));
        String a = sc.nextLine();
        a = a.trim();
        a = a + " ";
        String word = "";
        for(int i = 0; i < a.length(); i++)
        {
            if(a.charAt(i) != ' ')
            {
                word = word + a.charAt(i);
            }

            else
            {
                int t = 0;
                int ch;
                if(word.charAt(0) == 'a' || word.charAt(0) == 'e' || word.charAt(0) == 'i' || word.charAt(0) == 'o' ||
                        word.charAt(0) == 'u' || word.charAt(0) == 'A' || word.charAt(0) == 'E' ||
                        word.charAt(0) == 'I' || word.charAt(0) == 'O' || word.charAt(0) == 'U')
                {
                    ch = 1;
                }
                else
                    ch = -1;

                for(int s = 0; s < word.length(); s++)
                {
                    if(word.charAt(s) == 'a' || word.charAt(s) == 'e' || word.charAt(s) == 'i' || word.charAt(s) == 'o' ||
                            word.charAt(s) == 'u' || word.charAt(s) == 'A' || word.charAt(s) == 'E' ||
                            word.charAt(s) == 'I' || word.charAt(s) == 'O' || word.charAt(s) == 'U')
                    {
                        t = 1;
                    }
                    else
                    {
                        t = -1;
                    }

                    if(t != ch)
                    {
                        f = 1;
                    }
                    ch = ch * -1;
                }

                if(f == 0)
                    System.out.print(word+" ");
                f = 0;
                word = "";
            }

        }

        if(f == 1)
        {
            System.out.print("No such words");
        }

    }
}