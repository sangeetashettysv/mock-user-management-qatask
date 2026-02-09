# Requirement: Update User Phone

## Requirement ID
**REQ-UL-UPDATE-001**

## Title
Update the phone number of an existing user

## Description
As an admin, I want to update the phone number of a user so that user contact information remains accurate in the system.

---

## Positive Test Cases

**Test Case ID:** TC-UPDATE-001  
**Scenario:** Successfully update phone number  
**Steps:**  
1. Open Users List page  
2. Click Update action for a specific user  
3. Edit the Phone field with valid 10-digit data  
4. Click Update Phone  
**Expected Result:** Modal closes, and updated phone number is visible in Users List  
**Tags:** @updateUser, @positive, @req-UL-UPDATE-002  

---

## Negative / Validation Test Cases

**Test Case ID:** TC-UPDATE-002  
**Scenario:** Close modal without saving  
**Steps:**  
1. Open Users List page  
2. Click Update action for a specific user  
3. Click Close  
**Expected Result:** Modal closes, phone number remains unchanged  
**Tags:** @updateUser, @negative, @req-UL-UPDATE-003  

**Test Case ID:** TC-UPDATE-003  
**Scenario:** Close modal using X icon  
**Steps:**  
1. Open Users List page  
2. Click Update action for a specific user  
3. Click X icon on modal  
**Expected Result:** Modal closes, phone number remains unchanged  
**Tags:** @updateUser, @negative, @req-UL-UPDATE-004  

**Test Case ID:** TC-UPDATE-004  
**Scenario:** Enter phone number with less than 10 digits  
**Steps:**  
1. Open Users List page  
2. Click Update action for a specific user  
3. Enter a phone number with less than 10 digits  
**Expected Result:**  
- Error message displayed below Phone field: "Phone must be a 10-digit number (eg. 9812345678)"  
- Update Phone button is disabled  
**Tags:** @updateUser, @negative, @req-UL-UPDATE-004  

**Test Case ID:** TC-UPDATE-005  
**Scenario:** Intentional failure for debugging  
**Steps:**  
1. Open Users List page  
2. Click Update action for a specific user  
3. Enter invalid phone data (non-numeric or wrong format)  
4. Click Update Phone  
**Expected Result:** Modal behavior is validated for debugging purposes  
**Tags:** @updateUser, @debug, @req-UL-UPDATE-003  

---

## Traceability

- Requirement `REQ-UL-UPDATE-001` → Test Cases: `TC-UPDATE-001`, `TC-UPDATE-002`, `TC-UPDATE-003`, `TC-UPDATE-004`, `TC-UPDATE-005`  
- Automated test scenarios reference these IDs for traceability and reporting.
