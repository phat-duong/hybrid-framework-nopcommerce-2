package javaBasic;

import org.testng.annotations.Test;

public class Topic_14_String_Format {
    public static String ADDRESS_LINK = "xpath=//div[@class='listbox']//a[text()='Addresses']";
    public static String MY_PRODUCT_REVIEW_LINK = "xpath=//div[@class='listbox']//a[text()='My product reviews']";
    public static String REWARD_POINT_LINK = "xpath=//div[@class='listbox']//a[text()='Reward points']";
    public static String CUSTOMER_INFO_LINK = "xpath=//div[@class='listbox']//a[text()='Customer info']";

    //1 biến cho cả 14 page hoặc n page (format giống nhau - chỉ khác nhau bởi tên page)
    public static String DYNAMIC_LINK = "xpath=//div[@class='listbox']//a[text()='%s']";
    public static String DYNAMIC_GENERAL_LINK = "xpath=//div[@class='%s']//a[text()='%s']";

    public static void main(String[] args) {
        clickOnSideBarLink(DYNAMIC_LINK, "Addresses");
        clickOnSideBarLink(DYNAMIC_LINK, "My product reviews");
        clickOnSideBarLink(DYNAMIC_GENERAL_LINK, "listbox", "Addresses");
    }

    public static void clickOnSideBarLink(String locator) {
        System.out.println("Click on " + locator );
    }

    //1 tham so dong
//	public static void clickOnSideBarLink(String dynamicLocator, String pageName) {
//		String locator = String.format(dynamicLocator, pageName);
//		System.out.println("Click on " + locator );
//	}

    // 2 tham so dong
//	public static void clickOnSideBarLink(String dynamicLocator, String areaName, String pageName) {
//		String locator = String.format(dynamicLocator, areaName, pageName);
//		System.out.println("Click on " + locator );
//	}

    // 1 - n tham so dong
    // ... gọi là Rest parameter
    public static void clickOnSideBarLink(String dynamicLocator, String... params) {
        String locator = String.format(dynamicLocator, (Object[]) params);
        System.out.println("Click on " + locator );
    }
}
