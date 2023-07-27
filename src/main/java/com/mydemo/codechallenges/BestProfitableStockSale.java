package com.mydemo.codechallenges;

//TODO
// For testing corner cases for this problem, visit https://www.interviewcake.com/question/python/stock-price
public class BestProfitableStockSale {
	// The stock prices of a listed company is given in an array, each index corresponding to the stock price at a particular minute of the 
	// 1 hour trading window.
	// The indices are the time (in minutes) past trade opening time, which was 9:30am local time.
	// The values are the price (in US dollars) of one share of Apple stock at that time.
	// So if the stock cost $500 at 10:30am, that means stock_prices_yesterday[60] = 500.
	
	
	// Write an efficient function that takes stock_prices_yesterday and returns the best profit I could have made from one purchase and 
	// one sale of one share of the stock.
	/* For example:

		  stock_prices_yesterday = [10, 7, 5, 8, 11, 9]

		get_max_profit(stock_prices_yesterday)
		# Returns 6 (buying for $5 and selling for $11)
	*/
	// IMP: You need to buy before you can sell. Also, you can't buy and sell in the same time step�at least 1 minute has to pass.
	
	public static void main(String[] args) {
		get_max_profit(new int[]{10, 7, 5, 8, 11, 9});
	}

	private static void get_max_profit(int [] stockPrices){
		//int result[] = new int[2];
		int lowestNum=0;
		int largestNum = 0;
		int greatestProfit = 0;
		int purchasePriceIndex = 0;
		int salePriceIndex = 0;
		int temp = 0;
		// 10, 7, 5, 8, 11, 9
		
		/* lowestNum = stockPrices[i];
		 * max_profit[] = {0}
		 				  {0}
		 				  {0} // lowestNum = 5
		 				  {3} // lowestNum = 5
		 				  {6} // lowestNum = 5
		 				  {4} // lowestNum = 5
						  */
		purchasePriceIndex = salePriceIndex = 0;
		for(int i=1;i<stockPrices.length;i++){
			if(stockPrices[i] < stockPrices[purchasePriceIndex]){
				purchasePriceIndex = i;
			}
			else if(stockPrices[i] > stockPrices[salePriceIndex] && salePriceIndex > purchasePriceIndex){
				
			}
		}
		
		
		int j;
		for(int i=0;i<stockPrices.length-2;i++){
			j=i+1;

			largestNum = lowestNum = stockPrices[i];
			for(j=i+1;j<stockPrices.length;j++){
				if(stockPrices[j] > largestNum){
					largestNum = stockPrices[j];
					salePriceIndex = j; // Largest num index
				}
			}
			temp = largestNum - lowestNum;
			if(temp>greatestProfit){
				purchasePriceIndex = i; // Lowest num index
				greatestProfit = temp;
			}
		}
		
		/*for(int i=0;i<stockPrices.length-1;i++){
			int largestNum = lowestNum = stockPrices[i];
			for(int j=i+1;j<stockPrices.length;j++){
				if(stockPrices[j] > largestNum){
					largestNum = stockPrices[j];
					salePriceIndex = j; // Largest num index
				}
			}
			temp = largestNum - lowestNum;
			if(temp>greatestProfit){
				purchasePriceIndex = i; // Lowest num index
				greatestProfit = temp;
			}
		}*/
		System.out.println(greatestProfit + ": Purchased in minute " + purchasePriceIndex + ", and sold in minute "
				+ salePriceIndex + " of trading window");
	}
	
	private static void get_max_profit_PREV(int [] stockPrices){
		//int result[] = new int[2];
		int lowestNum;
		int greatestProfit = 0;
		int purchasePriceIndex = 0;
		int salePriceIndex = 0;
		int temp = 0;
		// 10, 7, 5, 8, 11, 9
		for(int i=0;i<stockPrices.length-1;i++){
			int largestNum = lowestNum = stockPrices[i];
			for(int j=i+1;j<stockPrices.length;j++){
				if(stockPrices[j] > largestNum){
					largestNum = stockPrices[j];
					salePriceIndex = j; // Largest num index
				}
			}
			temp = largestNum - lowestNum;
			if(temp>greatestProfit){
				purchasePriceIndex = i; // Lowest num index
				greatestProfit = temp;
			}
		}
		System.out.println(greatestProfit + ": Purchased in minute " + purchasePriceIndex + ", and sold in minute "
				+ salePriceIndex + " of trading window");
	}
	
}