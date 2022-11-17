import com.codeborne.selenide.SelenideElement;

public class Utils {

    public static String getLink(SelenideElement element){

        return element.$x(".//*[contains(@href,'bukmekerskye-kontory')]").getAttribute("href");

    }


    public static String getName(SelenideElement element){

        return element.$x(".//*[contains(@href,'bukmekerskye-kontory')]//img").getAttribute("alt").
                replaceAll("Логотип букмекерской конторы","").
                replaceAll(" - legalbet.ru","");
    }


    public static int getBonus(SelenideElement element){

        int bonus = 0;

        if (element.$x(".//*[contains(@class,'bonus')]//*[contains(@href,'label=sets-bonus')]").exists())
            bonus = Integer.parseInt(element.$x(".//*[contains(@class,'bonus')]//*[contains(@href,'label=sets-bonus')]").getText().replaceAll("\\D+",""));

        return bonus;
    }

    public static boolean getLicence(SelenideElement element) {

        return element.$x(".//*[contains(@class,'bonus')]//*[contains(@class,'caption license')]").getText().equals("Легальный букмекер");

    }

    public static int getFeedbacks(SelenideElement element){

        return Integer.parseInt(element.$x(".//*[contains(@href,'feedback')]").getText().replaceAll("\\D+",""));

    }


}
