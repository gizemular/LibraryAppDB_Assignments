package com.library.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class BorrowedBooksPage extends BasePage{


    @FindBy(xpath = "//tbody//td[2]")
    public List<WebElement> allBorrowedBooksName;

    @FindBy(xpath = "//a[@onclick='Books.borrow_book(5798)']")
    public WebElement clickBorrowed;

    @FindBy(xpath = "/html/body/main/section[2]/div[1]/div[2]/div[2]/div/div[2]/table/tbody/tr[24]/td[2]")
    public WebElement BookText;

    @FindBy(css = "h3")
    public WebElement pageHeader;

    @FindBy(css = "a[onclick='Books.borrow_book(10031)']")
    public WebElement linkBorrowBook;

    @FindBy(xpath = "/html/body/header/nav/div/ul[1]/li[2]/a/span[1]")
    public WebElement BorrowingBooks;

    @FindBy(css = "html > body > main > section:nth-of-type(2) > div:nth-of-type(1) > div:nth-of-type(2) > div:nth-of-type(2) > div > div:nth-of-type(2) > table > tbody > tr:nth-of-type(25) > td:nth-of-type(2)")
    public WebElement SelfConfidenceText;











}
