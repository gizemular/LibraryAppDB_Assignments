package com.library.steps;

import com.library.pages.BasePage;
import com.library.pages.BookPage;
import com.library.utility.BrowserUtil;
import com.library.utility.DB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class US03_stepDef extends BasePage {


    BookPage bookPage = new BookPage();
    @When("the user navigates to {string} page")
    public void the_user_navigates_to_page(String bookPage) {

        navigateModule(bookPage);
    }
    @When("the user clicks book categories")
    public void the_user_clicks_book_categories() {

        bookPage.mainCategoryElement.click();

    }
    @Then("verify book categories must match book_categories table from db")
    public void verify_book_categories_must_match_book_categories_table_from_db() {


        String expectedBookCategories = bookPage.mainCategoryElement.getText();



        System.out.println("expectedBookCategories = " + expectedBookCategories);

        DB_Util.runQuery("select name from book_categories;");

        BrowserUtil.waitFor(3);

        List<String> actualBookCategories = DB_Util.getColumnDataAsList(1);
        System.out.println("actualBookCategories = " + actualBookCategories);


        Assert.assertEquals(expectedBookCategories,actualBookCategories);
    }
}


