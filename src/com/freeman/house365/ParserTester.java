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
		testp.connect("http://nj.sell.house365.com/s_62391996.html");
		
		System.out.print("Type:");
		testp.parseType();
		System.out.println(testp.getType());
		System.out.print("Community:");
		testp.parseCommunity();
		System.out.println(testp.getCommunity());
		System.out.print("Title:");
		testp.parseTitle();
		System.out.println(testp.getTitle());
		System.out.print("Price:");
		testp.parsePrice();
		System.out.println(testp.getPrice());
		System.out.print("PriceCondition:");
		testp.parsePriceCondition();
		System.out.println(testp.getPriceCondition());
		System.out.print("PriceDesc:");
		testp.parsePriceDesc();
		System.out.println(testp.getPriceDesc());
		System.out.print("HouseType:");
		testp.parseHouseType();
		System.out.println(testp.getHouseType());
		System.out.print("Area:");
		testp.parseArea();
		System.out.println(testp.getArea());
		System.out.print("Floor:");
		testp.parseFloor();
		System.out.println(testp.getFloor());
		System.out.print("OwnerShip:");
		testp.parseOwnerShip();
		System.out.println(testp.getOwnerShip());
		System.out.print("Furnishment:");
		testp.parseFurnishment();
		System.out.println(testp.getFurnishment());
		System.out.print("BuiltDate:");
		testp.parseBuildDate();
		System.out.println(testp.getBuildDate());
		System.out.print("Desc:");
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
