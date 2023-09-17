# TEMPLATE Integration Test Plan

## Instructions

*Fill out the test plan following the instructions provided, replacing/removing the text in italics (including this section!) as you go.*

## Purpose

This test plan outlines the automated integration tests for endpoints in our application. 
These tests are designed to ensure that our application functions properly.

## Product Background

Our product is a comment moderation system for a chat application. It includes the following key components:

UserSystem: An external package maintained by another team, responsible for user management.
ContentModerationService: A service that scans for new users who have posted too many comments and deactivates their accounts.
Comment Application: The core application that manages comments, users, and moderation.

### Use Cases to be tested

A user can be created.
A user can be retrieved by their username.
Active users can post comments, and they must have a valid and active account.
Deactivated users are not allowed to post comments.
A comment can be retrieved by its ID.
A list of all comments can be retrieved.
A user can be manually deactivated.
If a user posts too many comments during their probation period and the moderation/checkForSpam endpoint is called, the user should be deactivated.

# Automated Integration Test Plan

*Organize your tests by use cases from assignment description. Provide the
entire "Use Case:..." section below for each Use Case you will implement
in the project. If you have more than one test case for a given use
case, repeat the "Test Case" section below for each test case in that
use case.*

*The goal should be that any member of your team could take this list of
integration tests and add these automated tests to the integration test
package.*

## Use Case: *[User Creation]*

### **Test case name: createUser_withValidInputs

**Acceptance criteria:**

1. A new user is successfully created.
2. The created user is valid and active.

**Endpoint(s) tested:**

1. '/user/'(POST)

**GIVEN (Preconditions):**

1. No existing user with the same username.

**WHEN (Action(s)):**

1.Create a new user using the '/user/' endpoint with valid input data

**THEN (Verification steps):**

1. Verify that the user is successfully created
2. Verify that the created user is valid and active.

**Is there any clean-up needed for this test?**

1. Delete the created user

Use Case: User Retrieval
Test case name: getUser_withValidUsername
Acceptance criteria:

The user with the given username is successfully retrieved.
The retrieved user is valid and active.
Endpoint(s) tested:

/user/{username} (GET)
GIVEN (Preconditions):

An existing user with the specified username.
WHEN (Action(s)):

Retrieve the user using the /user/{username} endpoint.
THEN (Verification steps):

Verify that the user with the given username is successfully retrieved.
Verify that the retrieved user is valid and active.
Is there any clean-up needed for this test?

No clean-up needed.
Use Case: Comment Creation
Test case name: addNewComment_withValidUser
Acceptance criteria:

A new comment is successfully created.
The owner of the comment is a valid and active user.
Endpoint(s) tested:

/comment/ (POST)
GIVEN (Preconditions):

An existing user with a valid and active account.
WHEN (Action(s)):

Create a new comment using the /comment/ endpoint with valid input data.
THEN (Verification steps):

Verify that the comment is successfully created.
Verify that the owner of the comment is a valid and active user.
Is there any clean-up needed for this test?

Delete the created comment.
Use Case: Comment Retrieval
Test case name: getComment_withValidID
Acceptance criteria:

The comment with the given ID is successfully retrieved.
Endpoint(s) tested:

/comment/{id} (GET)
GIVEN (Preconditions):

An existing comment with the specified ID.
WHEN (Action(s)):

Retrieve the comment using the /comment/{id} endpoint.
THEN (Verification steps):

Verify that the comment with the given ID is successfully retrieved.
Is there any clean-up needed for this test?

No clean-up needed.
