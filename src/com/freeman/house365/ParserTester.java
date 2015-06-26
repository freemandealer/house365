package com.freeman.house365;

import java.util.Iterator;

public class ParserTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Parser testp = new Parser();
		//testp.connect("http://nj.sell.house365.com/s_46202292.html");
		testp.connect("http://nj.sell.house365.com/s_46040195.html");
		
		testp.parseType();
		System.out.println(testp.getType());
		testp.parseCommunity();
		System.out.println(testp.getCommunity());
		testp.parseTitle();
		System.out.println(testp.getTitle());
		testp.parsePrice();
		System.out.println(testp.getPrice());
		testp.parsePriceCondition();
		System.out.println(testp.getPriceCondition());
		testp.parsePriceDesc();
		System.out.println(testp.getPriceDesc());
		testp.parseHouseType();
		System.out.println(testp.getHouseType());
		testp.parseArea();
		System.out.println(testp.getArea());
		testp.parseFloor();
		System.out.println(testp.getFloor());
		testp.parseOwnerShip();
		System.out.println(testp.getOwnerShip());
		testp.parseFurnishment();
		System.out.println(testp.getFurnishment());
		testp.parseBuildDate();
		System.out.println(testp.getBuildDate());
		testp.parseDescText();
		System.out.println(testp.getDescText());
		testp.parsePictureUrl();
		Iterator<String> it;
		it = testp.getPictureUrl().iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
	}
}
