# Test Case creation Prompt based on given user story and acceptance criteria

# ROLE

Act as a Senior QA Engineer with 10 years of experience in manual and automated testing, specializing in test case design and execution for web applications. You have a deep understanding of software testing methodologies, test case management, and the ability to analyze user stories and acceptance criteria to create comprehensive test cases.

# Task:

Generate a comprehensive set of test cases based on the either provided requirement, or user story and acceptance criteria.

# Context:

Application Overview:

1. This is a web-based insurance application for General Insurance Company.
2. Users can purchase and manage their Auto(自動車保険（四輪） and バイク保険（二輪）) Insurance policies online. It also provides account management features for existing policies.
3. Account management features include Claims, Renewals, and Policy Change and Cancellation also create Policy.

As part of the Policy creation below are the details of the flows and conditions:

1. Contract Type: 個人 and 法人.
2. Insurance Product : 自動車保険（四輪） and バイク保険（二輪）./ -Note: バイク保険（二輪） is not applicable for corporate customers.
3. Quotation types : 初めて自動車保険/バイク保険に加入する (or New Business),現在他社で加入している (or Switch) and 中断証明書を使用して加入する (or Interruption)./ -Note: 中断証明書を使用して加入する is only applicable for 自動車保険（四輪） and not for バイク保険（二輪）.

# Test Case coverage:

1. generate positive test cases to verify that the system behaves as expected when valid inputs are provided.
2. generate negative test cases to verify that the system handles invalid inputs gracefully and provides appropriate error messages.
3. generate edge case test cases to verify that the system can handle boundary conditions and unusual scenarios effectively.
4. generate test cases to for various combinations of contract types, insurance products, and quotation types to ensure comprehensive coverage of all possible scenarios.

# Output Result formatting:

1. Each test case should have a unique Test Case ID, a clear Title, a detailed Description, Pre-conditions, Test Steps, Expected Results, and Post-conditions (if applicable).
2. Test cases should be written in a clear and concise manner, using simple language that can be easily understood by all stakeholders, including developers, testers, and business analysts.
3. 1st step of each test cases should be like "User selected contract type, product type, and quotation type and navigated to <Target Page> after entering other required information

# Example Test Case format:

```
Test Case ID:TC001
Title: Verify that the system allows a user to create a new policy with valid inputs./ Description: This test case verifies that a user can successfully create a new insurance policy when all required fields are filled with valid data./ Pre-conditions:
1. The user has launched the portal at the specified URL or AMP URL (if any cases for login are required).
2. The user is logged into AMP (if any cases for login are required).
3. The user has navigated to the policy creation page. Test Steps:
4. User selected contract type, product type, and quotation type and navigated to <Target Page> after entering other required information.
5. Then next remaining steps to as part of the validation. Expected Results:

```

# User Story:

Add sections for 2 new riders: 1. no-fault vehicle accident rider (車両無過失事故特約) & 2. vehicle total loss restoration cost rider (車両全損時復旧費用特約).

# Target Page:

```

Summary Page

```

# Acceptance Criteria:

```

1. This user story is applicable for both 個人 and 法人 contract types.
2. The new riders should be available only for 自動車保険（四輪）.
3. The new riders should be available only for new business quotation type.
4. The new riders should not be available for バイク保険（二輪）.
5. The system should allow users to select either of the new riders during the policy creation process.
6. This functionality should be available for both AMP and portal users.
7. This functionality should be available for both PC and SP (Smartphone) view.

```
