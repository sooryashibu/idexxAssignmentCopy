Feature: LoginAndSearch Functionalities 
  #Step 1 - Open Page https://test.animana.com/web2/login
#Step2 - Login with userId - testnl40, password - Test#12345.
#Select the location Hilversum
#Assert if 'new contact' icon is present on the page
#select patient from the search drop down and search for the patient - 'Diensthond'
#Assert the search result
 
  Scenario: TC1-Verify LoginAndSearch Functionalities
    Given Customer is Navigated to Login Page
    When Login Page is displayed
    And Customer enters the credentials "TC1" and submit
    And Verify customer is navigated to Choose Location Page
    And Choose the Location for "TC1"
    And Verify customer is navigated to Home Page
    And Verify New Contact icon on Home Page
    Then verify search functionality for "TC1"
    
    
    
    
    
    
   
    

