package com.library.steps;

import com.library.pages.BookPage;
import com.library.utility.BrowserUtil;
import com.library.utility.DB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.Map;

public class US04_stepDef {

    BookPage bookPage = new BookPage();
    String bookName;

    @When("the user searches for {string} book")
    public void the_user_searches_for_book(String bookName) {

        BrowserUtil.waitForClickablility(bookPage.search,5).sendKeys(bookName);
        this.bookName=bookName;

    }
    @When("the user clicks edit book button")
    public void the_user_clicks_edit_book_button() {

        BrowserUtil.waitForClickablility(bookPage.editBook(bookName),5).click();
    }
    @Then("book information must match the Database")
    public void book_information_must_match_the_database() {

        String actualBookName = bookPage.bookName.getAttribute("value");
        System.out.println("actualBookName = " + actualBookName);

        String actualAuthor = bookPage.author.getAttribute("value");
        System.out.println("actualAuthor = " + actualAuthor);

        String actualIsbn = bookPage.isbn.getAttribute("value");
        System.out.println("actualIsbn = " + actualIsbn);

        String actualYear = bookPage.year.getAttribute("value");
        System.out.println("actualYear = " + actualYear);

        String actualDescription = bookPage.description.getAttribute("value");
        System.out.println("actualDescription = " + actualDescription);


        BrowserUtil.waitFor(5);


        DB_Util.runQuery("select name,isbn,year,author,description from books\n" +
                "where name like '" + bookName + "'");


        Map<String, String> bookInfo = DB_Util.getRowMap(1);

        String expectedBookName = bookInfo.get("name");
        System.out.println("expectedBookName = " + expectedBookName);

        String expectedIsbn = bookInfo.get("isbn");
        System.out.println("expectedIsbn = " + expectedIsbn);

        String expectedAuthor = bookInfo.get("author");
        System.out.println("expectedAuthor = " + expectedAuthor);

        String expectedYear = bookInfo.get("year");
        System.out.println("expectedYear = " + expectedYear);

        String expectedDescription = bookInfo.get("description");
        System.out.println("expectedDescription = " + expectedDescription);


        Assert.assertEquals(actualBookName,expectedBookName);
        Assert.assertEquals(actualAuthor,expectedAuthor);
        Assert.assertEquals(actualIsbn,expectedIsbn);
        Assert.assertEquals(actualYear,expectedYear);
        Assert.assertEquals(actualDescription,expectedDescription);


    }
}
