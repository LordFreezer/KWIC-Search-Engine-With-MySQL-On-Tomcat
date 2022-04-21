package PAK;
public class Test {

    public static void main(String[] args) {
        LineStorage l = new LineStorage();
        addLine(l, "Hello World", "https//www.hello.world");
        addLine(l, "This is a test", "https//www.test.org");
        addLine(l, "Shared Data Design", "https//www.sharedata.design");
        addLine(l, "Another line", "https//www.one.more");
        addLine(l, "Big Beefy boi with lots and lots of words", "https//www.gobigbeef.org");
        addLine(l, "Another Big Beefy boi with similar words", "https//www.gobigbeef.com");
        CircularShifter cs = new CircularShifter(l);
        Alphabetizer az = new Alphabetizer(cs);

/*        //Display LineStorage
        System.out.println("--LINESTORAGE DISPLAY--");
        Display(l);
        System.out.println("--END LINESTORAGE DISPLAY--");
        
        //Test Circular Shifter
        System.out.println("--CIRCULAR SHIFTER TEST--");
        Display(cs);
        System.out.println("--END CIRCULAR SHIFTER TEST--");*/
        
        //Test Alphabetizer
        System.out.println("--ALPHABETIZER TEST--");
        Display(az);
        System.out.println("--END ALPHABETIZER TEST--");
        
/*        //Test Keyword retrieval
        System.out.println("--KEYWORD TEST--");
        DisplayKeyword(az);
        System.out.println("--END KEYWORD TEST--");*/
        
        //WhereIs Full Run
        /*char[] charTree = {'a', 'A', 'b', 'B', 'c', 'C', 'd', 'D', 'e', 'E', 'f', 'F', 'g', 'G', 'h', 'H', 
            'i', 'I', 'j', 'J', 'k', 'K', 'l', 'L', 'm', 'M', 'n', 'N', 'o', 'O', 'p', 'P', 'q', 'Q',
            'r', 'R', 's', 'S', 't', 'T', 'u', 'U', 'v', 'V', 'w', 'W', 'x', 'X', 'y', 'Y', 'z', 'Z'};
        for(char c : charTree)
            System.out.println(c + " is at " + az.whereIs(c));*/
        
        //WhereIs Selection
        System.out.println("--MICROMINER TEST--");
        MicroMiner m = new MicroMiner(az);
        String query = "Another";               //Use whatever String you want
        System.out.println("Printing every line that contains '" + query + "'");
        m.Search(query);
        System.out.println("--END MICROMINER TEST--");
    }
    
    public static void Display(ILineSet ls) {
        int f = 0, g = 0, h = 0;
        while (true) {
            if (ls.getChar(f, g, h) == 0) {
                h = 0;
                g++;
                System.out.print(' ');
            }
            if (ls.getChar(f, g, h) == 0) {
                System.out.print(" =>  " + ls.getUrl(f));
                g = 0;
                f++;
                System.out.print('\n');
            }
            if (ls.getChar(f, g, h) == 0) {
                break;
            }
            System.out.print(ls.getChar(f, g, h));
            h++;
        }
    }
    
    public static void DisplayKeyword(Alphabetizer az){
        int f = 0, g = 0, h = 0;
        while (true){
        System.out.print("      Keyword: ");
        for (int i = 0; az.getChar(f, g, i) != 0; i++)
            System.out.print(az.getChar(f, g, i));
        System.out.print("\nOriginal Line: ");
        while (true) {
            if (az.getParentChar(f, g, h) == 0) {
                h = 0;
                g++;
                System.out.print(' ');
            }
            if (az.getParentChar(f, g, h) == 0) {
                System.out.println(" =>  " + az.getUrl(f) + '\n');
                g = 0;
                f++;
                break;
            }
            System.out.print(az.getParentChar(f, g, h));
            h++;
        }
        if (az.getParentChar(f, g, h) == 0) {
            break;
        }
        }
    }
    
    public static void addLine(LineStorage ls, String st, String url)
    {
        ls.addLine(url);
        ls.addWord();
        for(int g=0; g < st.length(); g++)
        {
            if(st.charAt(g) == ' ')
                ls.addWord();
            else ls.addChar(st.charAt(g));
        }
    }
}
