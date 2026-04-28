# Page Navigation Mapping Prompt

## Overview
This prompt defines rules and mappings to automatically generate E2E test flows based on target navigation pages. When a test case specifies a target page, the system determines which e2e methods must be called to reach that page.

## Page Sequence & Method Mapping

### Base Navigation (Required for all pages)
```typescript
await ctx.AppUrl.QnB_URL_Navigation(testCaseId, memberType, insuranceType);
```

### Page Hierarchy with Methods

| Target Page                              | Sequence of Methods Required |
|------------------------------------------|------------------------------|
| **Home/Contract Type**                   | URL Navigation | 
| **Switch Page/Interruption/Suspension**  | 1. URL + Home |
| **Vehicle Page**                         | 1. URL + Home 2. [Switch/Interruption if applicable] | 
| **Driver Page**                          | 1. URL + Home 2. [Switch/Interruption if applicable] 3. Vehicle | 
| **Tariff Page**                          | 1. URL + Home 2. [Switch/Interruption if applicable] 3. Vehicle 4. Driver | 
| **Vehicle Info Page**                    | 1. URL + Home 2. [Switch/Interruption if applicable] 3. Vehicle 4. Driver |
| **Contractor/Policy Holder**             | 1-4. Previous steps 5. Vehicle Info | 
| **Terms & Conditions**                   | 1-5. Previous steps 6. Contractor | 
| **Payment**                              | 1-7. Previous steps 8. Summary | 
| **Thankyou Page**                        | 1-8. Previous steps 9. Payment |

## Conditional Logic Rules

### Rule 1: Policy Purchase Type Branching
```typescript
if (data['Final summary'][testCaseId]['Policy Purchase type'] === "現在他社で加入している") {
  // Call switchPage methods
  await ctx.switchPage.e2eFlowOfCurrentInsurnacePage(testCaseId, insuranceType);
} else if (data['Final summary'][testCaseId]['Policy Purchase type'] === "中断証明書を使用して加入する") {
  // Call interruptPage methods
  await ctx.interruptPage.e2eFlowOfSuspensionCertificatePage(testCaseId, insuranceType);
}
// Else: Skip both and proceed to vehiclePage
```

## Test Case Definition Input Format
Test data will be maintained in excel and will pass in test cases in Json formate as below:

Example:

```json
{
  "testCaseId": "E2E_Test_001",
  "targetPage": "driver page",
  "data": {
    "memberType": "Individual",
    "insuranceType": "Auto",
    "policyPurchaseType": "現在他社で加入している",
    "inceptionDate": "2026-03-01"
  }
}
```

### Target Page Values (Case-insensitive)
- `contract type` / `home` / `home page`
- `switch page` / `current insurance`
- `interruption` / `suspension certificate` / `suspension`
- `vehicle` / `vehicle page`
- `driver` / `driver page`
- `vehicle info` / `vehicle information`
- `contractor` / `policy holder` / `policy holder information`
- `terms` / `terms and conditions` / `condition`
- `summary` / `contract details` / `summary page`
- `payment` / `payment page`
- `thankyou` / `thank you page`/ `Completion page`

## Code Generation Rules

### Rule 1: Dependency Injection
All methods require the TestContext object:
```typescript
export async function generateFlowToPage(
  ctx: TestContext,
  testCaseId: string,
): Promise<void>
```

### Rule 2: Always Include URL Navigation
First step in every flow:
```typescript
await ctx.AppUrl.QnB_URL_Navigation(
  testCaseId,
  data['Final summary'][testCaseId]['Member Type'],
  data['Final summary'][testCaseId]['Insurance type']
);
```

### Rule 3: Conditional Pages Check
After Home Page, evaluate policy purchase type:
```typescript
const policyPurchaseType = data['Final summary'][testCaseId]['Policy Purchase type'];

if (policyPurchaseType === "現在他社で加入している") {
  await ctx.switchPage.e2eFlowOfCurrentInsurnacePage(
    testCaseId,
    data['Final summary'][testCaseId]['Insurance type']
  );
} else if (policyPurchaseType === "中断証明書を使用して加入する") {
  await ctx.interruptPage.e2eFlowOfSuspensionCertificatePage(
    testCaseId,
    data['Final summary'][testCaseId]['Insurance type']
  );
}
```

### Rule 4: Sequential Method Calls
Call all methods in order up to target page (never skip intermediate pages):
```typescript
// For target page "vehicle page":
1. URL Navigation
2. Home Page flow
3. [Conditional: Switch OR Interruption]
// STOP - do not call Vehicle,Driver, VehicleInfo, etc.
```

### Rule 5: Parameter Mapping
| Method | testCaseId | insuranceType | Additional |
|---|---|---|---|
| `e2eFlowOfContractTypePage` | ✓ | N/A | context, policyPurchaseType, inceptionDate |
| `e2eFlowOfCurrentInsurnacePage` | ✓ | ✓ | N/A |
| `e2eFlowOfSuspensionCertificatePage` | ✓ | ✓ | N/A |
| `e2eFlowOfVehiclePage` | ✓ | ✓ | N/A |
| `e2eFlowOfAboutMainDriverAndPolicyPlan` | ✓ | ✓ | N/A |
| `e2eFlowOfVehicleInformation` | ✓ | ✓ | N/A |
| `e2eFlowOfPolicyHolderInformation` | ✓ | N/A | N/A |
| `termAndConditionsConfirmationPage` | ✓ | N/A | N/A |
| `contractDetailsConfirmationPage` | ✓ | N/A | N/A |
| `e2eFlowOfPaymentPage` | ✓ | N/A | N/A |


## Example Implementations

### Example 1: Navigate to Driver Page
**Input:**
```json
{
  "testCaseId": "E2E_001",
  "targetPage": "driver page",
  "policyPurchaseType": "現在他社で加入している"
}
```

**Generated Flow:**
```typescript
await ctx.AppUrl.QnB_URL_Navigation(testCaseId, memberType, insuranceType);
await ctx.homePage.e2eFlowOfContractTypePage(ctx.context, testCaseId, policyPurchaseType, inceptionDate);
await ctx.switchPage.e2eFlowOfCurrentInsurnacePage(testCaseId, insuranceType);
await ctx.vehiclePage.e2eFlowOfVehiclePage(testCaseId, insuranceType);
await ctx.driverPage.e2eFlowOfAboutMainDriverAndPolicyPlan(testCaseId, insuranceType);
```

### Example 2: Navigate to Summary Page (No Policy Switch)
**Input:**
```json
{
  "testCaseId": "E2E_002",
  "targetPage": "summary page",
  "policyPurchaseType": "新規に加入する"
}
```

**Generated Flow:**
```typescript
await ctx.AppUrl.QnB_URL_Navigation(testCaseId, memberType, insuranceType);
await ctx.homePage.e2eFlowOfContractTypePage(ctx.context, testCaseId, policyPurchaseType, inceptionDate);
// Skip switchPage and interruptPage (policyPurchaseType doesn't match conditions)
await ctx.vehiclePage.e2eFlowOfVehiclePage(testCaseId, insuranceType);
await ctx.driverPage.e2eFlowOfAboutMainDriverAndPolicyPlan(testCaseId, insuranceType);
await ctx.vehicleInfoPage.e2eFlowOfVehicleInformation(testCaseId, insuranceType);
await ctx.contractorPage.e2eFlowOfPolicyHolderInformation(testCaseId);
await ctx.conditionPage.termAndConditionsConfirmationPage(testCaseId);
await ctx.summaryPage.contractDetailsConfirmationPage(testCaseId);
```

### Example 3: Navigate to Vehicle Info Page for "中断証明書を使用して加入する" purchase type

**Generated Flow:**
```typescript
await ctx.AppUrl.QnB_URL_Navigation(testCaseId, memberType, insuranceType);
await ctx.homePage.e2eFlowOfContractTypePage(ctx.context, testCaseId, policyPurchaseType, inceptionDate);
await ctx.interruptPage.e2eFlowOfSuspensionCertificatePage(testCaseId, insuranceType);
await ctx.vehiclePage.e2eFlowOfVehiclePage(testCaseId, insuranceType);
await ctx.driverPage.e2eFlowOfAboutMainDriverAndPolicyPlan(testCaseId, insuranceType);
await ctx.vehicleInfoPage.e2eFlowOfVehicleInformation(testCaseId, insuranceType);
```

## Data Access Pattern
All test data follows this pattern:
pattern: `data[**sheet name**][testCaseId][**Column name**]`
```typescript
data['Final summary'][testCaseId]['Field Name']
data['Final summary'][testCaseId]['Insurance type']
data['Final summary'][testCaseId]['Policy Purchase type']
data['Final summary'][testCaseId]['Member Type']
data['Final summary'][testCaseId]['Inception Date']
```

## Notes
- Always maintain the exact order of method calls as defined in the hierarchy
- Conditional pages (Switch/Interruption) should only be included based on Policy Purchase Type
- Page names are case-insensitive in input but methods must match exactly
- All methods are async and must be awaited
- TestContext (ctx) must be passed to all navigation functions
