package com.library.steps;

import com.library.pages.BasePage;
import com.library.pages.BookPage;
import com.library.pages.BorrowedBooksPage;
import com.library.utility.BrowserUtil;
import com.library.utility.DB_Util;
import com.library.utility.Driver;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;

public class US07_stepDef extends BasePage {

    BookPage bookPage = new BookPage();
    BorrowedBooksPage borrowedBooksPage = new BorrowedBooksPage();
    String expectedBookName;

    @When("the user clicks Borrow Book")
    public void the_user_clicks_borrow_book() {


        BrowserUtil.waitForClickablility(borrowedBooksPage.clickBorrowed,5).click();
        String toastMessageText = bookPage.toastMessage.getText();
        System.out.println("toastMessageText = " + toastMessageText);

        BrowserUtil.waitFor(5);







    }
    @Then("verify that book is shown in {string} page")
    public void verify_that_book_is_shown_in_page(String pageName) {



        navigateModule(pageName);




        String expectedBookName = borrowedBooksPage.SelfConfidenceText.getText();
        this.expectedBookName = expectedBookName;

        System.out.println("expectedBookName = " + expectedBookName);



    }
    @Then("verify logged student has same book in database")
    public void verify_logged_student_has_same_book_in_database() {

        DB_Util.runQuery("select full_name,b.name,bb.borrowed_date from users u \n" +
                "inner join book_borrow bb on u.id = bb.user_id\n" +
                "inner join books b on bb.book_id = b.id\n" +
                "where full_name='Test Student 1’ and name='"+expectedBookName+"'\n" +
                "order by 3 desc;");

        String actualBookName = DB_Util.getFirstRowFirstColumn();

        Assert.assertEquals(expectedBookName,actualBookName);


    }

}


