
Feature: Simple Scenario

  Scenario: Open Bookmaker page
  Given Open legal bk page
  When Read bk table
  Then Find bookmaker with 10000
  Then Check feedbacks