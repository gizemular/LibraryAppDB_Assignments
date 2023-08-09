package com.library.steps;

import com.library.pages.DashBoardPage;
import com.library.pages.LoginPage;
import com.library.utility.ConfigurationReader;
import com.library.utility.DB_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class US02_stepDef {

    LoginPage loginPage = new LoginPage();
    DashBoardPage dashBoardPage = new DashBoardPage();
    String expectedB_BookNumber;

    @Given("the {string} on the home page")
    public void the_on_the_home_page(String userName) {

       loginPage.login(userName);

    }
    @When("the librarian gets borrowed books number")
    public void the_librarian_gets_borrowed_books_number() {

        String expectedB_BookNumber = dashBoardPage.borrowedBooksNumber.getText();

        this.expectedB_BookNumber = expectedB_BookNumber;

        System.out.println("expectedB_BookNumber = " + expectedB_BookNumber);

    }
    @Then("borrowed books number information must match with DB")
    public void borrowed_books_number_information_must_match_with_db() {

        DB_Util.runQuery("select count(*) from book_borrow where is_returned=0");

        String actualB_BookNumber = DB_Util.getFirstRowFirstColumn();

        System.out.println("actualB_BookNumber = " + actualB_BookNumber);

        Assert.assertEquals(expectedB_BookNumber,actualB_BookNumber);

    }

}
