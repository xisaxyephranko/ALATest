import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


/** 
 * 
 * 
 * @author kisakye franco
 * xisaxyephranko@gmail.com
 * 
 * store demo data in the file 
 * add file to project
 * prompt user to enter number
 * load file content into a BufferedReader
 * load content into a list/map/listarray (asignToList) function
 * get possible prices and operator data
 * while storing temprary data in (opTemp);
 * 
 * show the best possible prices of each operator (	displayResults(bestOperatorDetails) function
) 
 * show my recomendered operator showLowerOPS
 */

public class ALATest {
	
	
		
		   //getting the data / store data
				static Map bestOperatorDetails = new HashMap();
				static Map opTemp = new HashMap();
				static Map opTempResults = new HashMap();


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
        System.out.println("\t\t PHONE OPERATOR \n");
		
		//input a Phone Number
		Scanner reader = new Scanner(System.in);  
		System.out.println("Enter a Phone Number: "); 

		String phoneNum = reader.next(); 
		//once finished
		reader.close();
		
		
		String COMMA_DELIMITER = ",";
		
		File f =  new File(ALATest.class.getResource("pricing.csv").getFile());
		
		//String[] bestPrice = new String[1];
		String bestOperator = "";
		int prefixCode = 0;
		Double bestPrice = 0.0;
		int prefixCharCount = 0;

		//load data src
		try (BufferedReader br = new BufferedReader(new FileReader(ALATest.class.getResource("pricing.csv").getFile()))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		        String[] values = line.split(COMMA_DELIMITER);
		        
		        //get/use rows/data from file
		        if(phoneNum.startsWith(values[1])) {
			     
					//longest sequence char count
					prefixCharCount =  values[1].length();
					
					bestOperator = values[0];
					prefixCode = Integer.parseInt(values[1]);
					bestPrice = Double.parseDouble(values[2]);
					asignToList(bestOperator, prefixCode, bestPrice, prefixCharCount);

		
					
					//4673212345
					
			
		        }
		        
		        
		        
		    }
		    
		    

		    //show Results
		    displayResults(bestOperatorDetails);
			

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		

	}



	//sort and again if option was better


	private static void asignToList(String bestOperator, int prefixCode, Double bestPrice, int prefixCharCount) {
	//46732534523523
		//bestOperator, prefixCode,  bestPrice,  prefixCharCount
		
		//Map opData = new HashMap();
		
		if(bestOperatorDetails.isEmpty()) {

			bestOperatorDetails.put(bestOperator, setTempOption( bestOperator, prefixCode, bestPrice, prefixCharCount));

			
		}else {
			
			if(opTemp.get("bestOperator").equals(bestOperator)) {

			
					if( Integer.parseInt(opTemp.get("prefixCharCount").toString()) == prefixCharCount) {
						
						 if(Double.parseDouble(opTemp.get("bestPrice").toString()) <= bestPrice) {
								
							// setTempOption( bestOperator, prefixCode, bestPrice, prefixCharCount)
		
								bestOperatorDetails.put(bestOperator, setTempOption( bestOperator, prefixCode, bestPrice, prefixCharCount));
							
								
							}else {
								
								bestOperatorDetails.put(bestOperator, setTempOption( bestOperator, prefixCode, bestPrice, prefixCharCount));
	
							}
						
		
					}else if( Integer.parseInt(opTemp.get("prefixCharCount").toString()) <= prefixCharCount) {
						
						bestOperatorDetails.put(bestOperator, setTempOption( bestOperator, prefixCode, bestPrice, prefixCharCount));

					}
					

					
			
			}else {
				//check operator
				//System.out.println("Chenging Operators .................." + bestOperator);

				
				if( Integer.parseInt(opTemp.get("prefixCharCount").toString()) == prefixCharCount) {
					
					 if(Double.parseDouble(opTemp.get("bestPrice").toString()) <= bestPrice) {
						
						 bestOperatorDetails.put(bestOperator, setTempOption( bestOperator, prefixCode, bestPrice, prefixCharCount));

						}else {
							
							bestOperatorDetails.put(bestOperator, setTempOption( bestOperator, prefixCode, bestPrice, prefixCharCount));

						}
	
				}else if(Double.parseDouble(opTemp.get("bestPrice").toString()) <= bestPrice) {
					
					bestOperatorDetails.put(bestOperator, setTempOption( bestOperator, prefixCode, bestPrice, prefixCharCount));

					
				}else if( Integer.parseInt(opTemp.get("prefixCharCount").toString()) >= prefixCharCount) {
					
					bestOperatorDetails.put(bestOperator, setTempOption( bestOperator, prefixCode, bestPrice, prefixCharCount));

				}
				
			}

		
		}
		
		opTemp = (Map) bestOperatorDetails.get(bestOperator);
	
		
	
		
	}




//return map of new best operator

	private static Map setTempOption(String bestOperator, int prefixCode, double bestPrice, int prefixCharCount) {
		// TODO Auto-generated method stub
		
		Map opData = new HashMap();

		
		opData.put("bestOperator", bestOperator);
		opData.put("prefixCode", prefixCode);
		opData.put("bestPrice", bestPrice);
		opData.put("prefixCharCount", prefixCharCount);
		
		return opData;
	}
	
	

	//display output in console
		private static void displayResults(Map showMyBestOperator) {
			// TODO Auto-generated method stub
			
			System.out.println("------x----=----x--------"); 

			System.out.println("Found "+ showMyBestOperator.size()+ " Operator(s).\n");
			
			int count = 0;
			int charCount = 0;
			String lowestOPS = "";
			double amtOPS = 0.0;
			
			if(showMyBestOperator.size() > 0) {
			 for(Object x : showMyBestOperator.keySet().toArray()) {
					//System.out.println(x);
					
					opTempResults = (Map) showMyBestOperator.get(x);

					//show Selected Operators
					System.out.println("------x==== "+ opTempResults.get("bestOperator") +" ====x--------"); 

					
					System.out.println("Operater: "+opTempResults.get("bestOperator"));
					System.out.println("Prefixt: "+opTempResults.get("prefixCode"));
					System.out.println("Cost/min: "+opTempResults.get("bestPrice"));
					System.out.println("Character Count: "+ opTempResults.get("prefixCharCount")); 
					

					
					
					int prefixCharCount = Integer.parseInt(opTempResults.get("prefixCharCount").toString());

					double prefixAMT = Double.parseDouble(opTempResults.get("bestPrice").toString());

					
					//pick best operator
					if(count == 0) {
							
							lowestOPS = (String) x;
						
					}else {
						
						//make most char count the best
						/*
						 
						if(prefixCharCount < charCount) {

							lowestOPS = (String) x;
						}else if(prefixCharCount == charCount) {

							if(prefixAMT <= amtOPS) {
								lowestOPS = (String) x;

							}
							
						}*/
						

						//make lowest price the best
						if(prefixAMT <= amtOPS) {
							lowestOPS = (String) x;

						}
						
					}

					
					amtOPS = (double) opTempResults.get("bestPrice");
					charCount = (int) opTempResults.get("prefixCharCount");

					count++;

			 }
			 

			 //show best operator
			 showLowerOPS(lowestOPS , bestOperatorDetails);
			 
			 
			 
			}else {
				System.out.println("Prefix NOT found..");

			}
			
		}



		private static void showLowerOPS(String lowestOPS, Map opDatax2) {
			
			opTempResults = (Map) opDatax2.get(lowestOPS);

			System.out.println("\nBest Operator: "+opTempResults.get("bestOperator")); 
			System.out.println("Prefix: "+opTempResults.get("prefixCode")+ ", "+"Cost/min: "+opTempResults.get("bestPrice")+ ", "+"Character Count: "+ opTempResults.get("prefixCharCount")); 

		}


}


