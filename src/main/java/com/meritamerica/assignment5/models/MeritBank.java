package com.meritamerica.assignment5.models;

import java.io.*;
import java.util.*;


import com.meritamerica.assignment5.exceptions.*;

public class MeritBank{
	
	private static List<AccountHolder> accounts = new ArrayList<AccountHolder>();
	private static List<CDOffering> cdOfferings = new ArrayList<CDOffering>();
	                                     
	private static  long accountNumber = 1;
	
	private static CDOffering bestCDOffering;
	private static CDOffering secondBestCDOffering;
	
	
	public static void addAccountHolder(AccountHolder accountHolder) 
	{
		accounts.add(accountHolder);	
	}
	
	public static List<AccountHolder> getAccountHolders() 
	{
		return accounts;
	}
	
	public static List<CDOffering> getCDOfferings() 
	{
		return  cdOfferings;	
	}
	
	public static CDOffering getBestCDOffering(double depositAmount) 
	{
		return bestCDOffering;
	}
	public static CDOffering getSecondBestCDOfferings(double depositAmount)
	{
		return secondBestCDOffering;
	}
	
	public static void clearCDOfferings() 
	{
		cdOfferings = null;	
	}
	
	public static void setCDOfferings(CDOffering offerings)
	{
		cdOfferings.add(offerings);
	}
	
	public  static long getNextAccountNumber() 
	{
		return accountNumber++ ;
	}
	
	public static void setAccountNumber(Long accountN) 
	{
		accountNumber = accountN;
	}
	
	public static double totalBalances() 
	{
		double tB = 0;
		System.out.println("Method Total Balance" + tB);
		return tB;
	}
	
	public static double futureValue(double presentValue, double interestRate, int term) 
	{
		return 0;
	}
	
	
	public static boolean readFromFile(String fileName)  // --------------------------> Read transactions and the Fraud Queue TODO. Revise Savings, Checkings Exceptions.
	{
		File file = new File(fileName);
		ArrayList<String> values = new ArrayList<String>();

		int cdofferingsCounter; 
		AccountHolder ac;
		int checkingCounter;
		int accountHolderCounter;
		int savingsCounter;
		int cdAccountCounter;
		int index = 0;
		long accountN;
	//	FraudQueue fraud = new FraudQueue();

		int savingsTransactions;
		int checkingTransactions;
		int cdAccountTransactions;
		int fraudQ;

		try (BufferedReader bR = new BufferedReader(new FileReader(file)) )
		{	
			accountN = Long.parseLong(bR.readLine());
			setAccountNumber(accountN);
			cdofferingsCounter = Integer.parseInt(bR.readLine());
			
			for(int i = index ; i < cdofferingsCounter + index; i ++) //runs the amount of cd offerings
			{ 
				CDOffering.readFromString(bR.readLine()); 
			}
			accountHolderCounter = Integer.parseInt(bR.readLine());
			for(int i = 0; i < accountHolderCounter ; i++) 
			{
				addAccountHolder(ac = AccountHolder.readFromString(bR.readLine()));
				checkingCounter = Integer.parseInt(bR.readLine());
				for (int j = index ; j < checkingCounter ; j++) 
				{	
					ac.addCheckingAccount(CheckingAccount.readFromString(bR.readLine()));
					checkingTransactions = Integer.parseInt(bR.readLine());
					for(int y = 0; y <  checkingTransactions ; y++) 
					{
					//	Transaction.readFromString(bR.readLine());
					}
				}
				savingsCounter = Integer.parseInt(bR.readLine());
				for(int k = index; k < savingsCounter + index; k++)
				{
					ac.addSavingsAccount(SavingsAccount.readFromString(bR.readLine()));
					savingsTransactions = Integer.parseInt(bR.readLine());
					for(int z = 0; z < savingsTransactions; z++) {
					//	Transaction.readFromString(bR.readLine());
					}
				}
				cdAccountCounter = Integer.parseInt(bR.readLine());
				for(int x = 0; x < cdAccountCounter ; x++)
				{
					ac.addCDAccount(CDAccount.readFromString(bR.readLine()));
					cdAccountTransactions = Integer.parseInt(bR.readLine());
					System.out.println("CDAccounts Transactions: " + cdAccountTransactions);
					for(int xx = 0; xx < cdAccountTransactions; xx++) {
						System.out.println("test" + bR.readLine());;
					}
				}
			}
			fraudQ =Integer.parseInt(bR.readLine());
			System.out.println("Fraud Queue: " + fraudQ);
			for(int fQ = 0; fQ < fraudQ; fQ++) {
				//fraud.addTransaction(bR.readLine()); -------> Read String Transaction 
			}
		}catch(NumberFormatException e)
		{
			return false;
		} 
		catch(NullPointerException n)
		{
			System.out.println(n.getMessage());
			return false;
		}
		catch(IOException e )
		{
			e.getStackTrace();
			System.out.println("File not found");
		} catch (ExceedsCombinedBalanceLimitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true; 
	}

	public static boolean writeToFile(String fileName)
	{
		return true;
	}
	
	/*public static AccountHolder[] sortAccountHolders() 
	{
		 Arrays.sort(accounts);
		 return accounts;
	}
	*/
	public static void setNextAccountNumber(long nextAccountNumber) 
	{
		++accountNumber ;
	}

	
	//-------------------------------------------------------Assignment4---------------------------------------
	
	public static double recursiveFutureValue(double amount, int years, double interestRate) {
		
		//No MathPow, but recursive
		return 0; // ---------------------------> //(amount * recursiveFutureValue((1 + interestRate), years - 1)); TODO for loop maybe.
	}
	
	/*
	public static boolean processTransaction(Transaction transaction) throws NegativeAmountException, ExceedsAvailableBalanceException, ExceedsFraudSuspicionLimitException {
		try {
			transaction.process();
			return true;
		} catch (NegativeAmountException e){
			
			throw e;
		} catch ( ExceedsAvailableBalanceException e) {
			throw e;
		} catch (ExceedsFraudSuspicionLimitException e) {
			throw e;
		}
		
		
	}
	
	
	public static FraudQueue getFraudQueue() {
		return null;
		
	}
	*/
	
	public static BankAccount getBankAccount(long accountId) {
		return null;
	}

}
