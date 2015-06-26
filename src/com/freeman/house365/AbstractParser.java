package com.freeman.house365;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public abstract class AbstractParser {
	private URL url;
	protected Document doc;
	protected String type;
	protected String community;
	protected String title;
	protected String price;
	protected String priceCondition;
	protected String priceDesc;
	protected String houseType;
	protected String area;
	protected String floorType;
	protected String floor;
	protected String ownerShip;
	protected String furnishment;
	protected String buildDate;
	protected String face;
	protected String descText;
	protected ArrayList<String> pictureUrls;

	public void connect(String strUrl) {
		pictureUrls = new ArrayList<String>();
		try {
			this.url = new URL(strUrl);
			doc = Jsoup.parse(url, 10 * 1000);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "网络连接错误，请检查网址是不是已正确填写");
			e.printStackTrace();
		}
	}

	public void relesse() {

	}

	public URL getUrl() {
		return url;
	}

	public String getCommunity() {
		return community;
	}

	public String getType() {
		return type;
	}

	public String getTitle() {
		return title;
	}

	public String getPrice() {
		return price;
	}

	public String getPriceCondition() {
		return priceCondition;
	}

	public String getPriceDesc() {
		return priceDesc;
	}

	public String getHouseType() {
		return houseType;
	}

	public String getArea() {
		return area;
	}

	public String getFloorType() {
		return floorType;
	}

	public String getFloor() {
		return floor;
	}

	public String getOwnerShip() {
		return ownerShip;
	}

	public String getFurnishment() {
		return furnishment;
	}

	public String getBuildDate() {
		return buildDate;
	}

	public String getFace() {
		return face;
	}

	public String getDescText() {
		return descText;
	}

	public ArrayList<String> getPictureUrl() {
		return pictureUrls;
	}

	abstract public void parseType();

	abstract public void parseCommunity();

	abstract public void parseTitle();

	abstract public void parsePrice();

	abstract public void parsePriceCondition();

	abstract public void parsePriceDesc();

	abstract public void parseHouseType();

	abstract public void parseArea();

	abstract void parseFloorType();

	abstract public void parseFloor();

	abstract public void parseOwnerShip();

	abstract public void parseFurnishment();

	abstract public void parseBuildDate();

	abstract public void parseFace();

	abstract public void parseDescText();

	abstract public void parsePictureUrl();

}
