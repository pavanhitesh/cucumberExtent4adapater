
@SendMail @Regression
Feature: To Send Confirmation mail to user in gmail

Scenario Outline: To Send mail to user "<mailId>"

Given user configure the gmail configuration to send mail
When user enter the message to send "<message>" to the mailId "<mailId>"
Then user send the message

  Examples:
  |mailId|message|
  |pavanfunny@gmail.com|Sample Message Parallel|