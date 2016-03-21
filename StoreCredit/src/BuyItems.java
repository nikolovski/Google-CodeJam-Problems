
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;


public class BuyItems {
	private int credit, numberOfCases,numberOfItems;
	private HashMap<Integer,LinkedList<Integer>> itemPrices;
	
	private class Item{
		 int index, value;
		 Item(int index, int value) {
			this.index=index;
			this.value=value;
		}
	}
	public BuyItems(String args) throws FileNotFoundException {
		Scanner input = new Scanner(new File(args));
		PrintStream output = new PrintStream(new File("outputFile.out"));
		numberOfCases=input.nextInt();
		for (int i = 0; i < numberOfCases; i++) {
			credit = input.nextInt();
			if(!withinLimits(credit, 5, 1000)) break;
			numberOfItems = input.nextInt();
			LinkedList<Integer> samePriceItemsIndicies;
			itemPrices = new HashMap<Integer,LinkedList<Integer>>(numberOfItems);
			for (int j = 0; j<numberOfItems ; j++) {
				int currentRead = input.nextInt();
				if(withinLimits(currentRead, 1, 1000)){
					samePriceItemsIndicies = new LinkedList<Integer>();
					if (!itemPrices.containsKey(currentRead)) {
						samePriceItemsIndicies.add(j+1);
					}
					else{
						samePriceItemsIndicies=itemPrices.get(currentRead);
						samePriceItemsIndicies.add(j+1);
					}
					itemPrices.put(currentRead, samePriceItemsIndicies);
				}
					
				
			}
			output.print("Case #"+(i+1)+": ");
			calculate(itemPrices, credit, output);
		}
		output.close();
		input.close();
	}
	
	public static void calculate(HashMap<Integer,LinkedList<Integer>>itemPrices, int credit, PrintStream outFile){
		int difference;
		for (Iterator<Integer> iterator = itemPrices.keySet().iterator(); iterator.hasNext();) {
			int currentInt = (int) iterator.next();
			difference = credit-currentInt;
			if(itemPrices.containsKey(difference)) { 
				if(currentInt==difference){
					if(itemPrices.get(currentInt).get(0)>itemPrices.get(currentInt).get(1)){
						outFile.print(itemPrices.get(currentInt).get(1)+" "+itemPrices.get(difference).get(0));
					}
					else {
						outFile.print(itemPrices.get(currentInt).get(0)+" "+itemPrices.get(difference).get(1));
					}
					outFile.println();
					break;
				}
				else {
					if (itemPrices.get(currentInt).getFirst()>itemPrices.get(difference).getFirst()) {
						outFile.print(itemPrices.get(difference).getFirst()+" "+itemPrices.get(currentInt).getFirst());
					}
					else { 
						outFile.print(itemPrices.get(currentInt).getFirst()+" "+itemPrices.get(difference).getFirst());
					}
					outFile.println();
					break;
				}	
			}
		}
	}
	
	public static boolean withinLimits(int input, int min, int max) { return (input>=min && input<=max );}
	
	public static void main(String[] args) {
		try {
			BuyItems buy = new BuyItems(args[0]);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
