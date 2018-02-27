package com.test;

public class TestNumber {

	public static void main(String[] args) {
		
	TestNumber tm  = new TestNumber();
		System.out.println(tm.getNotEndWithZero("00"));

	}
	 public  String getNotEndWithZero(String num){
		 
		

		 if(num.endsWith("0")){
		
			 String aa = num.substring(0, num.length()-1);
			 System.out.println("aa=="+aa);
			 num =  getNotEndWithZero(aa) ;
		 }
	
		 System.out.println("num=="+num);
		 return num;
	 }
}
