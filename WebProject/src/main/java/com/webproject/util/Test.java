package com.webproject.util;

public class Test {
	public static void main(String[] args) {
		len();
	}
	
	private static void len() {
		// TODO Auto-generated method stub
		int length = 9436160;
		String mFillzero2 = "";
        if (length <= 8388608) {
        	mFillzero2 = length+"";
        } else {
        	mFillzero2 = String.valueOf((int)(Math.rint((float)(length-8388608)/1024)) + 8388608);
        }
        System.out.println(mFillzero2);
	}
}