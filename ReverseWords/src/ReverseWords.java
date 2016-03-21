import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;
import java.io.File;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * Created by nikolovski23 on 3/19/16.
 */
public class ReverseWords {
    private int numberOfCases;
    private Stack<String> words;

    public ReverseWords(String args) throws FileNotFoundException{
        Scanner input = new Scanner(new File(args));
        PrintStream output = new PrintStream(new File("outputFile.out"));
        StringTokenizer tokenizer;
        numberOfCases = input.nextInt();
        for (int i=0; i<(numberOfCases+1); i++){
            String line = input.nextLine();
            if(line.isEmpty()) continue;
            words = new Stack<>();
            tokenizer = new StringTokenizer(line," ");
            while (tokenizer.hasMoreTokens()) {
                words.add(tokenizer.nextToken());
            }
            output.print("Case #"+i+": ");
            while(!words.empty()) output.print(words.pop()+" ");
            output.println();
        }

        input.close();
        output.close();
    }

    public static void main(String[] args){
        try {
            ReverseWords reverseWords = new ReverseWords(args[0]);
        }
        catch (FileNotFoundException fnf){
            System.out.println("File not found!");
        }
    }
}




