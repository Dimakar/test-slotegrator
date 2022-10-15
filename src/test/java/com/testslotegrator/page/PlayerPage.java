package com.testslotegrator.page;

import com.codeborne.selenide.*;
import org.openqa.selenium.By;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$;
import static org.assertj.core.api.Assertions.assertThat;

public class PlayerPage {

    private final SelenideElement table = $(By.id("payment-system-transaction-grid")).$x(".//table");

    public void tableIsLoaded() {
        waitLoadingTable();
        table.$$x(".//tbody/tr").shouldHave(CollectionCondition.sizeGreaterThan(0));
    }

    private void waitLoadingTable() {
        table.parent().shouldNotHave(Condition.attribute("class", "grid-view grid-view-loading"));
    }

    public void selectSorting(String column) {
        table.$x(".//th/a[normalize-space(.)='" + column + "']").click();
        waitLoadingTable();
    }

    private int getColumnNumberById(String columnName) {
        List<String> columns = table.$$x("./thead/tr[1]/th")
                .asDynamicIterable()
                .stream()
                .map(SelenideElement::getText)
                .collect(Collectors.toList());
        return columns.indexOf(columnName);
    }

    public void checkRegistrationSort(String sortType) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        checkSorting("Registration date", Comparator.comparing(el ->
                LocalDateTime.parse(el.getText(), timeFormatter)), SortType.valueOf(sortType));
    }

    private void checkSorting(String column, Comparator<SelenideElement> comparator, SortType sortType) {
        comparator = sortType == SortType.DESC ? comparator.reversed() : comparator;
        int columnNumber = getColumnNumberById(column);
        ElementsCollection selenideElements = table.$$x(".//tbody/tr/td[" + (columnNumber + 1) + "]");
        assertThat(selenideElements)
                .isSortedAccordingTo(comparator);
    }

    public enum SortType {
        DESC,
        ASC
    }

}
