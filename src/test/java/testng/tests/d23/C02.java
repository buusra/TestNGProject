package testng.tests.d23;

import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;
import testng.pages.BlueRentalPage;
import testng.utilities.ConfigReader;
import testng.utilities.Driver;
import testng.utilities.ExcelReader;

public class C02 {


        BlueRentalPage blueRentalPage ;

        @Test
        public void test01() {

            //BlueRentalCar sayfasına gidelim
            Driver.getDriver().get(ConfigReader.getProperty("blueRentalUrl"));

            //Login buttonuna tıklayalım
            blueRentalPage=new BlueRentalPage();
            blueRentalPage.loginButton.click();

            //Excel dosyasındaki herhangi bir email ve password ile login olalım
            String dosyaYolu="src/test/java/testng/resources/mysmoketestdata.xlsx";
            String sayfaIsmi="customer_info";
            ExcelReader excelReader = new ExcelReader(dosyaYolu,sayfaIsmi);

            String emailExcel = excelReader.getCellData(2,0);
            String passwordExcel = excelReader.getCellData(2,1);

            blueRentalPage.email.sendKeys(emailExcel);
            blueRentalPage.password.sendKeys(passwordExcel, Keys.ENTER);

            blueRentalPage.loginVerify.click();
            blueRentalPage.profile.click();

            //Girilen email ile giriş yapıldığını doğrulayın
            String profileEmail= blueRentalPage.profileEmail.getText();
            Assert.assertEquals(profileEmail,emailExcel,"Emaillerde uyumsuzluk var");

            //sayfayı kapatalım
            Driver.closeDriver();



    }

}