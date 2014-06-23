package com.ai.mapp.sys.entity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name="HW_MOBILE")
public class Mobile implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="hw_mobile_gen")
    @SequenceGenerator(name="hw_mobile_gen",sequenceName="HW_MOBILE$SEQ")
	@Column(name="MID")
	private Long mId;
	
    @Column(name="MOBILEID")
	private Long mobileId;
	
	@Column(name="BSSMOBILEID")
	private String bssMobileId;
	
	@JoinColumn(name="BRAND")
	private String brand;
	
	@Column(name="MOBILEMODEL")
	private String mobileModel;
	
	@Column(name="PRICE")
	private Long price;
	
	@Column(name="COLOR")
	private String color;
	
	@Column(name="EXTERIOR")
	private String exterior;
	
	@Column(name="IFINTELLIGENT")
	private String ifIntelligent;
	
	@Column(name="OS")
	private String os;
	
	@Column(name="NETWORK")
	private String network;
	
	@Column(name="TRANSMISSION")
	private String transmission;
	
	@Column(name="BROWSER")
	private String browser;
	
	@Column(name="MEMORY")
	private String memory;
	
	@Column(name="EXTRAMEMORY")
	private String extraMemory;
	
	@Column(name="SCREEN")
	private String screen;
	
	@Column(name="VIDEOFORMAT")
	private String videoFormat;
	
	@Column(name="IFRECOMMEND")
	private String ifRecommend;
	
	@Column(name="IFBRANDRECOMMEND")
	private String ifBrandRecommend;
	
	@Column(name="VALID")
	private String valid;
	
	@Column(name="FEATURE")
	private String feature;
	
	@Column(name="FETCHSTATUS")
	private String fetchStatus;
	
	@ManyToOne(fetch=FetchType.LAZY,targetEntity=FileRelation.class)
	@JoinColumn(name="LIST_PIC",referencedColumnName="ID")
	private FileRelation listPic;
	
	@ManyToOne(fetch=FetchType.LAZY,targetEntity=FileRelation.class)
	@JoinColumn(name="DETAIL_PIC",referencedColumnName="ID")
	private FileRelation detailPic;
	
	@Column(name="TITLE")
	private String title;
	
	@Column(name="DETAILINFOURL")
	private String detailInfoUrl;
	
	@Column(name="FILEPATH")
	private String filePath;
	
	@Column(name="PRESENT")
	private String present;
	
	@Column(name="ISWIDESCREEN")
	private String isWideScreen;
	
	@Column(name="SCREENTYPE")
	private String screenType;

	@Column(name="RESOLUTION")
	private String resolution;
	
	@Column(name="TOUCHSCREEN")
	private String touchScreen;
	
	@Column(name="MUSICTYPE")
	private String musicType;
	
	@Column(name="JAVA")
	private String java;
	
	@Column(name="FM")
	private String fm;
	
	@Column(name="MMS")
	private String mms;
	
	@Column(name="OFFICE")
	private String office;
	
	@Column(name="PIXELS")
	private String pixels;
	
	@Column(name="GPS")
	private String gps;
	
	@Column(name="BLUE")
	private String blue;
	
	@Column(name="BATTERYTYPE")
	private String batteryType;
	
	@Column(name="BATTERYSPACE")
	private String batterySpace;
	
	@Column(name="PHONESIZE")
	private String phoneSize;
	
	@Column(name="WEIGHT")
	private String weight;
	
	@Column(name="INSTRUCTIONS")
	private String instructions;
	
	@Column(name="TERMINAL_BRAND_S")
	private String terminal_brand_s;
	
	@Column(name="TERMINAL_OS_S")
	private String terminal_os_s;
	
	@Column(name="TERMINAL_SELLFEATURE_S")
	private String terminal_sellFeature_s;
	
	public Mobile()
	{
		
	}
	public Mobile(Long mobileId) {
		super();
		this.mobileId = mobileId;
	}

	public Long getMobileId() {
		return mobileId;
	}

	public void setMobileId(Long mobileId) {
		this.mobileId = mobileId;
	}

	public String getBssMobileId() {
		return bssMobileId;
	}

	public void setBssMobileId(String bssMobileId) {
		this.bssMobileId = bssMobileId;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getMobileModel() {
		return mobileModel;
	}

	public void setMobileModel(String mobileModel) {
		this.mobileModel = mobileModel;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getExterior() {
		return exterior;
	}

	public void setExterior(String exterior) {
		this.exterior = exterior;
	}

	public String getIfIntelligent() {
		return ifIntelligent;
	}

	public void setIfIntelligent(String ifIntelligent) {
		this.ifIntelligent = ifIntelligent;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getNetwork() {
		return network;
	}

	public void setNetwork(String network) {
		this.network = network;
	}

	public String getTransmission() {
		return transmission;
	}

	public void setTransmission(String transmission) {
		this.transmission = transmission;
	}

	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	public String getMemory() {
		return memory;
	}

	public void setMemory(String memory) {
		this.memory = memory;
	}

	public String getExtraMemory() {
		return extraMemory;
	}

	public void setExtraMemory(String extraMemory) {
		this.extraMemory = extraMemory;
	}

	public String getScreen() {
		return screen;
	}

	public void setScreen(String screen) {
		this.screen = screen;
	}

	public String getVideoFormat() {
		return videoFormat;
	}

	public void setVideoFormat(String videoFormat) {
		this.videoFormat = videoFormat;
	}

	public String getIfRecommend() {
		return ifRecommend;
	}

	public void setIfRecommend(String ifRecommend) {
		this.ifRecommend = ifRecommend;
	}

	public String getIfBrandRecommend() {
		return ifBrandRecommend;
	}

	public void setIfBrandRecommend(String ifBrandRecommend) {
		this.ifBrandRecommend = ifBrandRecommend;
	}

	public String getValid() {
		return valid;
	}

	public void setValid(String valid) {
		this.valid = valid;
	}
	
	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

	public FileRelation getListPic() {
		return listPic;
	}
	public void setListPic(FileRelation listPic) {
		this.listPic = listPic;
	}
	public FileRelation getDetailPic() {
		return detailPic;
	}
	public void setDetailPic(FileRelation detailPic) {
		this.detailPic = detailPic;
	}

	@Transient
	private String priceRange;

	public String getPriceRange() {
		return priceRange;
	}

	public void setPriceRange(String priceRange) {
		this.priceRange = priceRange;
	}
	
	@Column(name="promotion")
	private String promotion;

	public String getPromotion() {
		return promotion;
	}

	public void setPromotion(String promotion) {
		this.promotion = promotion;
	}
	public String getFetchStatus() {
		return fetchStatus;
	}
	public void setFetchStatus(String fetchStatus) {
		this.fetchStatus = fetchStatus;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDetailInfoUrl() {
		return detailInfoUrl;
	}
	public void setDetailInfoUrl(String detailInfoUrl) {
		this.detailInfoUrl = detailInfoUrl;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getPresent() {
		return present;
	}
	public void setPresent(String present) {
		this.present = present;
	}
	public String getIsWideScreen() {
		return isWideScreen;
	}
	public void setIsWideScreen(String isWideScreen) {
		this.isWideScreen = isWideScreen;
	}
	public String getScreenType() {
		return screenType;
	}
	public void setScreenType(String screenType) {
		this.screenType = screenType;
	}
	public String getResolution() {
		return resolution;
	}
	public void setResolution(String resolution) {
		this.resolution = resolution;
	}
	public String getTouchScreen() {
		return touchScreen;
	}
	public void setTouchScreen(String touchScreen) {
		this.touchScreen = touchScreen;
	}
	public String getMusicType() {
		return musicType;
	}
	public void setMusicType(String musicType) {
		this.musicType = musicType;
	}
	public String getJava() {
		return java;
	}
	public void setJava(String java) {
		this.java = java;
	}
	public String getFm() {
		return fm;
	}
	public void setFm(String fm) {
		this.fm = fm;
	}
	public String getMms() {
		return mms;
	}
	public void setMms(String mms) {
		this.mms = mms;
	}
	public String getOffice() {
		return office;
	}
	public void setOffice(String office) {
		this.office = office;
	}
	public String getPixels() {
		return pixels;
	}
	public void setPixels(String pixels) {
		this.pixels = pixels;
	}
	public String getGps() {
		return gps;
	}
	public void setGps(String gps) {
		this.gps = gps;
	}
	public String getBlue() {
		return blue;
	}
	public void setBlue(String blue) {
		this.blue = blue;
	}
	public String getBatteryType() {
		return batteryType;
	}
	public void setBatteryType(String batteryType) {
		this.batteryType = batteryType;
	}
	public String getBatterySpace() {
		return batterySpace;
	}
	public void setBatterySpace(String batterySpace) {
		this.batterySpace = batterySpace;
	}
	public String getPhoneSize() {
		return phoneSize;
	}
	public void setPhoneSize(String phoneSize) {
		this.phoneSize = phoneSize;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getInstructions() {
		return instructions;
	}
	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}
	
	public Long getmId() {
		return mId;
	}
	public void setmId(Long mId) {
		this.mId = mId;
	}
	
	public String getTerminal_brand_s() {
		return terminal_brand_s;
	}
	public void setTerminal_brand_s(String terminal_brand_s) {
		this.terminal_brand_s = terminal_brand_s;
	}
	public String getTerminal_os_s() {
		return terminal_os_s;
	}
	public void setTerminal_os_s(String terminal_os_s) {
		this.terminal_os_s = terminal_os_s;
	}
	public String getTerminal_sellFeature_s() {
		return terminal_sellFeature_s;
	}
	public void setTerminal_sellFeature_s(String terminal_sellFeature_s) {
		this.terminal_sellFeature_s = terminal_sellFeature_s;
	}
	
	@Override
	public String toString() {
		return "MappMobile [mobileId=" + mobileId + ", bssMobileId="
				+ bssMobileId + ", brand=" + brand + ", mobileModel="
				+ mobileModel + ", price=" + price + ", color=" + color
				+ ", exterior=" + exterior + ", ifIntelligent=" + ifIntelligent
				+ ", os=" + os + ", network=" + network + ", transmission="
				+ transmission + ", browser=" + browser + ", memory=" + memory
				+ ", extraMemory=" + extraMemory + ", screen=" + screen
				+ ", videoFormat=" + videoFormat + ", ifRecommend="
				+ ifRecommend + ", ifBrandRecommend=" + ifBrandRecommend
				+ ", valid=" + valid + ", feature=" + feature
				+ ", fetchStatus=" + fetchStatus + ", listPic=" + listPic
				+ ", detailPic=" + detailPic + ", title=" + title
				+ ", detailInfoUrl=" + detailInfoUrl + ", filePath=" + filePath
				+ ", present=" + present + ", isWideScreen=" + isWideScreen + ", screenType="
				+ screenType + ", resolution=" + resolution + ", touchScreen="
				+ touchScreen + ", musicType=" + musicType + ", java=" + java
				+ ", fm=" + fm + ", mms=" + mms + ", office=" + office
				+ ", pixels=" + pixels + ", gps=" + gps + ", blue=" + blue
				+ ", batteryType=" + batteryType + ", batterySpace="
				+ batterySpace + ", phoneSize=" + phoneSize + ", weight="
				+ weight + ", instructions=" + instructions + ", priceRange="
				+ priceRange + ", promotion=" + promotion + "]";
	}
	
	/** 不对接数据库字段 **/
	/** 不对应数据库 **/ 
	@Transient
	public static final String SPECIALSEARCH_POSTPAY_HAS_PRODUCT = "POSTPAY_HAS_PRODUCT";
	
	@Transient
	public String specialSearch;

	public String getSpecialSearch() {
		return specialSearch;
	}

	public void setSpecialSearch(String specialSearch) {
		this.specialSearch = specialSearch;
	}
	
	@Transient
	private Product product;

	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
	@Transient
	private Collection<Long> ids;

	public Collection<Long> getIds() {
		return ids;
	}
	public void setIds(Collection<Long> ids) {
		this.ids = ids;
	}
	
	@Transient
	private Collection<FileRelation> relations;

	public Collection<FileRelation> getRelations() {
		return relations;
	}
	public void setRelations(Collection<FileRelation> relations) {
		this.relations = relations;
	}
	
	@Transient
	private String patternModel;

	@Transient
	private String optType;
	
	@Column(name="STATUS")
	private String status;
	
	@Column(name="PICPATHJSON")
	private String picPathJson;
	
	public String getPatternModel() {
		return patternModel;
	}
	public void setPatternModel(String patternModel) {
		this.patternModel = patternModel;
	}
	public String getOptType() {
		return optType;
	}
	public void setOptType(String optType) {
		this.optType = optType;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPicPathJson() {
		return picPathJson;
	}
	public void setPicPathJson(String picPathJson) {
		this.picPathJson = picPathJson;
	}
	
}
