import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.assertEquals;

public class BookmakersPages {

    private static String allLegalBookmaker = "Все легальные букмекеры";

    final private ElementsCollection bookmakersTableRows =
            $$x("//*[contains(@class,'bookmakers-rating-table bookmaker-rating-bonuses-table')]//*[contains(@data-href,'bukmekerskye-kontory')]");

    public static BookmakersPages openPage(){
        actions().moveToElement($x("//*[contains(@class,'panel__item  panel__item_bookmakers')]")).perform();
        $x(String.format("//*[contains(@class,'panel__item')]/a[contains(text(),'%s')]",allLegalBookmaker)).click();
        return page(BookmakersPages.class);
    }


    public List<BookmakerDto> getTableFromPage() {

        List<BookmakerDto> bookmakersTable = new ArrayList();

        for (SelenideElement element : bookmakersTableRows) {

            BookmakerDto bookmakerDto = new BookmakerDto();

            bookmakerDto.link = Utils.getLink(element);
            bookmakerDto.name = Utils.getName(element);
            bookmakerDto.bonus = Utils.getBonus(element);
            bookmakerDto.licence = Utils.getLicence(element);
            bookmakerDto.feedbacks = Utils.getFeedbacks(element);

            bookmakersTable.add(bookmakerDto);
        }

        return bookmakersTable;
    }

    public void printlnBookmakerTable(List<BookmakerDto> bookmakersTable) {

        for ( BookmakerDto y : bookmakersTable) {
            System.out.println(" " + y.name + " - " + y.bonus + " - " + y.licence + " - " + y.feedbacks + " - " + y.link );
        }
    }

    public static void printlnBookmake(BookmakerDto bookmakers) {

        System.out.println(" " + bookmakers.name + " - " + bookmakers.bonus + " - " + bookmakers.feedbacks);

    }

    public void compareNumOfFeedbacks(int numFromTable) {

        assertEquals("Количество отзывов на странице отличается от отзывов в таблице букмекеров",
                Integer.parseInt($x("//*[contains(@id,'feedbacks')]//*[contains(@class,'count')]").getText()),
                numFromTable);

        System.out.println("Количество отзывов на странице букмекера: " + Integer.parseInt($x("//*[contains(@id,'feedbacks')]//*[contains(@class,'count')]").getText())
                + " равно количеству отзывов на букмекера в таблице: " + numFromTable);

    }


    public static BookmakerDto findByBonus(List<BookmakerDto> bookmakersTable, int bonus) {

        for (int i=bookmakersTable.size()-1 ; i > 0; i--)
            if (bookmakersTable.get(i).bonus == bonus)
                return bookmakersTable.get(i);

        return null;

    }


}
