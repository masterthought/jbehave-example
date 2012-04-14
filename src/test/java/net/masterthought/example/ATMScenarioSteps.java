package net.masterthought.example;

import net.masterthought.example.ATM;
import net.masterthought.example.Account;
import net.masterthought.example.CreditCard;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

public class ATMScenarioSteps {

    private ATM atm;
    private Account account;
    private CreditCard creditCard;

    private int money;

    @Given("the Account balance is <account_balance>")
    public void createAccount(@Named("account_balance") int balance) {
        account = new Account(balance);
    }

    @When("the card is $valid")
    public void createCreditCard() {
        creditCard = new CreditCard(account);
    }

    @When("the machine contains <atm_available>")
    public void createATM(@Named("atm_available") int money) {
        atm = new ATM(money);
    }

    @When("the Account Holder requests <request>")
    public void requestMoney(@Named("request") int amount) {
        money = atm.requestMoney(creditCard, amount);
    }

    @Then("the ATM should dispense <result>")
    public void checkMoney(@Named("result") int amount) {
        assertThat(money, is(amount));
    }

    @Then("the account balance should be <newBalance>")
    public void checkBalance(@Named("newBalance") int balance) {
        assertThat(balance, is(creditCard.getAccount().getBalance()));
    }

    @Then("the card should be returned")
    public void cardShouldBeReturned() {
        assertFalse(creditCard.isInUse());
    }


}
