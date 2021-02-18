import com.thoughtworks.gauge.Step;
import org.junit.Assert;

public class Anasayfa extends BaseTest {


    @Step("Anasayfaya gidilir")
    public void implementation1() throws InterruptedException {
        driver.get(url);
        System.out.println("Anasayfa açıldı");


    }

    @Step("<reklamKapatma> tikla")
    public void implementation2(String reklamKapatma) {
        //findElement(reklamKapatma);
        clickElement(reklamKapatma);
        System.out.println("Reklam Kapandı");
    }

    @Step("<Samsung> ara <aramaText>")
    public void implementation3(String esya, String key) {
        sendkeysElement(key, esya);
    }


    @Step("<email> <email> ile <sifre> <sifre> giris")
    public void implementation5(String emailU, String email, String sifreU, String sifre) {
        sendkeysElement(email, emailU);
        sendkeysElement(sifre, sifreU);
    }

    @Step("<hesabimIsim> ile <username> isim kontrol")
    public void implementation4(String isim, String name) {

        assertControl(isim, convertTurkishChar(name));
        System.out.println(convertTurkishChar("isim dogru"));
    }

    @Step("<hesabimMenü> hesabim üzerine gel bekle")
    public void implementation6(String hesabimHover) {
        System.out.println("Hovera geldi");
        hoverElement(hesabimHover);
        System.out.println("Hovera geldi");
    }

    @Step("<uruneTikla> ilk urune tikla <1>")
    public void implementation7(String urun, int index) {
        clickListElement(urun, index);
        System.out.println("urune tiklandi");
    }


    @Step("Urun sepettemi kontrol et")
    public void implementation9() {

    }

    @Step("<sepet> ekle tikla")
    public void implementation8(String sepet) {
        action.moveToElement(findElement(sepet)).click().build().perform();
        System.out.println("Sepete Eklendi");
        //clickElement(sepet);
    }
    @Step("<key> elementi var mi")
    public void checkElement(String key) {
        try {
            findElement(key);
        } catch (Exception e) {
            Assert.fail(convertTurkishChar("Element bulunamadı."));
        }
    }
}