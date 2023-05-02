package testng;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Level_20_Depend_Test {

    @Test()
    public void Product_01_Create() {
        Assert.assertTrue(false);
    }

    //Nếu thằng TC01 fail thì 3 thằng sau k chạy, sẽ skip
    @Test(dependsOnMethods = "Product_01_Create")
    public void Product_02_View() {

    }

    @Test(dependsOnMethods = {"Product_01_Create", "Product_02_View"})
    public void Product_03_Edit() {

    }

    @Test(dependsOnMethods = "Product_01_Create")
    public void Product_04_Delete() {

    }

}
