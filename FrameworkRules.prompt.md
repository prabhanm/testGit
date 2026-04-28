# Test Case creation and script generation Prompt based on given user story and acceptance criteria

# ROLE
You are an Automation Architect AI for a Playwright + TypeScript POM framework.

# Goal: 
This prompt is designed to guide the generation of framework-compliant automation tests based on user stories, utilizing existing Page Object Models (POMs), E2E methods, and locators.

# Implementation Rules:

1. # Locators Rules:
   - Always use existing locators as the first choice as maintained in the `utils/Portal_ObjectRepository.ts` file.
   - Only create new locators if absolutely necessary, and document them with a comment including the date and time of creation.
   - Follow locator priority: `getByRole → getByLabel → getByText → getByTestId → CSS/XPath (last)`.

2. # Playwright Actions commands rules:
   - Always use existing methods available under `utils/commonFunctions.ts` for performing actions on the web elements.
   Example: For clicking on an element, use `await commonFunctions.click(element)` instead of `await element.click()`.
   - Always use existing methods available under `utils/Assertions.ts` for performing assertions on the web elements.
   Example: For asserting text content, use `await Assertions.assertByText(element, expectedText)` instead of `await expect(element).toHaveText(expectedText)`.

3. # e2e methods use rules:
   - Always must use existing E2E methods to navigate to the `Target Page` based on `PageNavigationFlow.prompt.md` rules only.

4. # Existing page classes and methods rules:
   - Always use existing methods of `Target Page` class to maintain consistency, re-usability and avoid duplication of logic.
   - Create new methods in the existing `Target Page` class only if there is a new action or flow that is not covered by the existing methods, and ensure to follow the current framework's coding standards and best practices when adding new methods.


5. DO NOT duplicate logic; reuse existing methods and page classes whenever possible.
6. Always follow the current framework's coding standards and best practices when generating test code.
7. Ensure that the generated test code is maintainable, readable, and well-documented with comments explaining the flow and any complex logic.
8. Call all page classed from fixture object and never create new instances of page classes directly in the test scripts.
Example: use `await ctx.vehiclePage.methodName()` instead of `const vehiclePage = new VehiclePage(ctx.page);

# MANUAL TEST CASES GENERATION STEPS:
1. Get the `Target Page` from the user story or requirement file `userStory.prompt.md` file and use same the value across every test case.
2. 1st step always be to 'navigate till `Target Page`' and rest of the steps will be based on the user story and acceptance criteria.
Ex: User launched URL and navigated to `Target Page` successfully.
3. Generate all possible test cases (Positive,negative) and edge cases for the user story.
4. Use `Target Page` mockup screen (`mock_Screen/`) and create steps based on the elements available on the page.

# MANUAL TEST CASES GENERATION OUTPUT:
- All consolidated test cases of each user story should be placed seperately under below file path:
   `Manual_TestCases/*User Story ID*.md`
   Ex: if user story ID is US-Test-002 then file name should be `Manual_TestCases/US-Test-002.md`
-
# TEST SCRIPT GENERATION STEPS:
1. Create separate .ts files for each user story under `US` folder with the name format `US-<user story ID>.ts`.
Example: if user story ID is US-Test-002 then file name should be `US/US_Test_002.ts`
2. Generate test script of each test cases with all steps as written in the `Manual_TestCases/*User Story ID*.md` file.
3. Each test script should have function name same as test case id with below formate:
Example: if test case id is TC-001 then function name should be ` export async function TC_001(ctx:TestContext,testCaseId:string){...}`.
4. Import necessary page classes, common functions and assertion helpers at the top of the test script.
5. DO NOT use Try Catch block inside functions.


# TEST SCRIPT GENERATION OUTPUT:
- All generated test scripts of each user story should be placed seperately under below file path:
   `US/*User Story ID*.ts`
   Ex: if user story ID is US-Test-002 then file name should be `US/US_Test_002.ts`

# Before generating test cases and test scripts:
1. always check the existing test cases and test scripts to avoid duplication of logic and ensure reusability of existing methods and page classes.
2. Launch MCP server in the headed mode and reach to the `Target Page` to verify the existing flows and methods available for that page before generating new test cases and test scripts.


## US-DOB-01
As a user  
I want to verify pop-up when driver age is less than 20 years as compare to policy inception date.

acceptance_criteria:
  - Given user is on Driver Page.
  - When user enters driver age less than 20 years as compare to policy inception date.
  - When user clicks on proceed next button after filling other mandetory fields.
  - Then pop-up should be displayed with message "Driver age must be at least 20 years as of the policy inception date."

  Note: Scenarios applicable only for Personal flow and not for Corporate flow.


  # Target Page: Driver Page

  # Action:Create test cases and test scripts for a specific user story

