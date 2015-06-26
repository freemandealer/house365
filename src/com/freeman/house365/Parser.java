package com.freeman.house365;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Parser extends AbstractParser {

	@Override
	public void parseType() {
		this.type = doc.select("div[class=houseInfoMain]")
				.select("dd[class=info]").get(6).text();
	}

	@Override
	public void parseCommunity() {
		this.community = doc.select("div[class=houseInfoMain]")
				.select("div[class=leftIn]").select("a[class=link]").first()
				.text();
	}

	@Override
	public void parseTitle() {
		this.title = doc.select("div[class=titleT]").first().text();
	}

	@Override
	public void parsePrice() {
		this.price = doc.select("div[class=houseInfoMain]")
				.select("dd[class=info]").select("span[class=number]").first()
				.text();
	}

	@Override
	public void parsePriceCondition() {
		this.priceCondition = doc.select("div[class=houseInfoMain]")
				.select("dd[class=info]").get(3).text();
	}

	@Override
	public void parsePriceDesc() {
		this.priceDesc = doc.select("div[class=houseInfoMain]")
				.select("dd[class=info]").get(2).text().substring(0, 4);
	}

	@Override
	public void parseHouseType() {
		this.houseType = doc.select("div[class=houseInfoMain]")
				.select("dd[class=info]").get(4).text();
	}

	@Override
	public void parseArea() {
		this.area = doc.select("div[class=houseInfoMain]")
				.select("dd[class=info infoColor]").get(1).text();

	}

	@Override
	void parseFloorType() { // gap floor?

	}

	@Override
	public void parseFloor() {
		this.floor = doc.select("div[class=houseInfoMain]")
				.select("dd[class=info]").get(5).text();
	}

	@Override
	public void parseOwnerShip() {
		this.ownerShip = doc.select("div[class=houseInfoMain]")
				.select("dd[class=info]").get(9).text();
	}

	@Override
	public void parseFurnishment() {
		this.furnishment = doc.select("div[class=houseInfoMain]")
				.select("dd[class=info]").get(8).text();
	}

	@Override
	public void parseBuildDate() {
		this.buildDate = doc.select("div[class=houseInfoMain]")
				.select("dd[class=info]").get(10).text();
	}

	@Override
	public void parseFace() {
		this.face = doc.select("div[class=houseInfoMain]")
				.select("dd[class=info]").get(7).text();
	}

	@Override
	public void parseDescText() {
		this.descText = doc.select("div[class=houseDetailsBox]")
				.select("div[class=mod]").select("div[class=text]").text();
	}

	@Override
	public void parsePictureUrl() {
		Iterator<Element> it;
		it = doc.select("div[class=houseDetailsBox]").select("div[class=mod]")
				.select("div[id=fyzp]").select("img").iterator();
		while (it.hasNext()) {
			this.pictureUrls.add(it.next().attr("src"));
		}
	}
}
