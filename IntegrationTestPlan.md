# TEMPLATE Integration Test Plan

## Instructions

*Fill out the test plan following the instructions provided, replacing/removing the text in italics (including this section!) as you go.*

## Purpose

This captures the test plan for the testing of endpoints through automated integration tests.

## Product Background

*Provide a quick summary of your product and what it does.*

### Use Cases to be tested

*List the use cases you will test here. These should be the same list from your
design doc (though might be updated based on what you've learned since
then!). You will refer back to these use cases in the test plans below.*

# Automated Integration Test Plan

*Organize your tests by use cases from assignment description. Provide the
entire "Use Case:..." section below for each Use Case you will implement
in the project. If you have more than one test case for a given use
case, repeat the "Test Case" section below for each test case in that
use case.*

*The goal should be that any member of your team could take this list of
integration tests and add these automated tests to the integration test
package.*

## Use Case: *[use case name]*

### **Test case name: *[test method name, following ATA conventions]***

**Acceptance criteria:**

1. *(List what must be true to verify the use case has been implemented
   correctly)*

**Endpoint(s) tested:**

1. *(List only the endpoints actually tested (the "WHEN" part of your
   test, which might have multiple steps in an integration test))*

**GIVEN (Preconditions):**

1. *(List the conditions that must be true for the test case to take
   place.)*

**WHEN (Action(s)):**

1. *(List the steps that we're actually testing to verify that they work
   correctly. Often only one, but some integration tests might contain
   multiple WHEN steps for complex situations)*

**THEN (Verification steps):**

1. *(List the steps to verify/assert that the expected behavior actually
   happens, include any relevant invariants here as well.)*

**Is there any clean-up needed for this test?**

1. *(Is there anything we need to do after this test finishes, to clean
   up and leave our service like we found it?)*
