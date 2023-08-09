package com.library.steps;

import com.library.pages.BookPage;
import com.library.utility.BrowserUtil;
import com.library.utility.DB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class US06_stepDef {

    BookPage bookPage = new BookPage();


    @When("the librarian click to add book")
    public void the_librarian_click_to_add_book() {

        BrowserUtil.waitForClickablility(bookPage.addBook,5).click();

    }
    @When("the librarian enter book name {string}")
    public void the_librarian_enter_book_name(String expectedBookName) {

        BrowserUtil.waitForClickablility(bookPage.bookName,5).sendKeys(expectedBookName);


    }
    @When("the librarian enter ISBN {string}")
    public void the_librarian_enter_isbn(String expectedIsbnNum) {

        BrowserUtil.waitForClickablility(bookPage.isbn,5).sendKeys(expectedIsbnNum);

    }
    @When("the librarian enter year {string}")
    public void the_librarian_enter_year(String expectedYear) {
        BrowserUtil.waitForClickablility(bookPage.year,5).sendKeys(expectedYear);
    }
    @When("the librarian enter author {string}")
    public void the_librarian_enter_author(String expectedAuthor) {
        BrowserUtil.waitForClickablility(bookPage.author,5).sendKeys(expectedAuthor);
    }
    @When("the librarian choose the book category {string}")
    public void the_librarian_choose_the_book_category(String expectedBookCategory) {

       BrowserUtil.selectOptionDropdown(bookPage.categoryDropdown,expectedBookCategory);


    }
    @When("the librarian click to save changes")
    public void the_librarian_click_to_save_changes() {

        BrowserUtil.waitForClickablility(bookPage.saveChanges,5).click();

    }
    @Then("verify {string} message is displayed")
    public void verify_message_is_displayed(String expectedMessage) {
        BrowserUtil.waitFor(1);
        String actualMessage = bookPage.toastMessage.getText();

        Assert.assertEquals(expectedMessage,actualMessage);
    }
    @Then("verify {string} information must match with DB")
    public void verify_information_must_match_with_db(String expectedBookName) {

        DB_Util.runQuery("select id,name,author from books\n" +
                "where name = 'Clean Code' and author='Robert C.Martin'\n" +
                "order by id desc;");

        List<String> actualBookName = DB_Util.getColumnDataAsList(1);

        System.out.println("expectedBookName = " + expectedBookName);
        System.out.println("actualBookName = " + actualBookName);

    }



}
