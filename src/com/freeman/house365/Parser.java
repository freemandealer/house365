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
				.select("dl[class=clearfix item itemLeft]").get(5)
				.select("dd[class=info]").text();
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
		if (doc.select("div[class=houseInfoMain]").select("dd[class=info]")
				.select("img[class=img]").first().attr("src").contains("jing")) {
			this.priceCondition = "房主净得价";
		} else if (doc.select("div[class=houseInfoMain]")
				.select("dd[class=info]").select("img[class=img]").first()
				.attr("src").contains("shuang")) {
			this.priceCondition = "双方各自付税";
		}
	}

	@Override
	public void parsePriceDesc() {
		if (doc.select("div[class=houseInfoMain]").select("dd[class=info]")
				.select("img[class=img]").get(1).attr("src").contains("02")) {
			this.priceDesc = "无营业税";
		} else {
			this.priceDesc = "有营业税";
		}
	}

	@Override
	public void parseHouseType() {
		this.houseType = doc.select("div[class=houseInfoMain]")
				.select("dl[class=clearfix item itemLeft]").get(3)
				.select("dd[class=info]").text();
	}

	@Override
	public void parseArea() {
		this.area = doc.select("div[class=houseInfoMain]")
				.select("dl[class=clearfix item itemLeft]").get(2)
				.select("dd[class=info infoColor]").text();

	}

	@Override
	void parseFloorType() {

	}

	@Override
	public void parseFloor() {
		this.floor = doc.select("div[class=houseInfoMain]")
				.select("dl[class=clearfix item itemLeft]").get(4)
				.select("dd[class=info]").text();
	}

	@Override
	public void parseOwnerShip() {
		this.ownerShip = doc.select("div[class=houseInfoMain]")
				.select("dl[class=clearfix item itemLeft]").get(8)
				.select("dd[class=info]").text();
	}

	@Override
	public void parseFurnishment() {
		this.furnishment = doc.select("div[class=houseInfoMain]")
				.select("dl[class=clearfix item itemLeft]").get(7)
				.select("dd[class=info]").text();
	}

	@Override
	public void parseBuildDate() {
		this.buildDate = doc.select("div[class=houseInfoMain]")
				.select("dl[class=clearfix item itemLeft]").get(9)
				.select("dd[class=info]").text();
	}

	@Override
	public void parseFace() {
		this.face = doc.select("div[class=houseInfoMain]")
				.select("dl[class=clearfix item itemLeft]").get(6)
				.select("dd[class=info]").text();
	}

	@Override
	public void parseDescText() {
		this.descText = doc.select("div[class=houseDetailsBox]")
				.select("div[class=mod]").select("div[class=text]").text();
	}

	@Override
	public void parsePictureUrl() {
		Iterator<Element> it;
		it = doc.select("div[class=houseDetailsBox]").select("div[class=mod]").select("div[id=fyzp]").select("img").iterator();
		while(it.hasNext()) {
			this.pictureUrls.add(it.next().attr("src"));
		}
	}
}
