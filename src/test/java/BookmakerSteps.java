import com.codeborne.selenide.Selenide;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

public class BookmakerSteps {


    BookmakersPages bookmakersPages;
    List<BookmakerDto> bookmakerTable;
    BookmakerDto bookmakerDto;

    @Given("^Open legal bk page$")
    public void start() {
        Selenide.open("https://legalbet.ru/");
        bookmakersPages = BookmakersPages.openPage();

    }

    @When("^Read bk table$")
    public void getBkTable(){

        bookmakerTable = bookmakersPages.getTableFromPage();;

    }

    @Then("Find bookmaker with {int}")
    public void findBookmakerWith(int arg0) {

        bookmakerDto = BookmakersPages.findByBonus(bookmakerTable, arg0);
        BookmakersPages.printlnBookmake(bookmakerDto);
        Selenide.open(bookmakerDto.link);

    }

    @Then("^Check feedbacks$")
    public void findsBookmakerWith() {

       bookmakersPages.compareNumOfFeedbacks(bookmakerDto.feedbacks);

    }


}
