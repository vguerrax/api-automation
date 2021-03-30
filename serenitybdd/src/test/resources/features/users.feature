#encoding: utf-8

Feature: Users

  Scenario: List users
    When I made a GET request to the endpoint '/users'
    Then I should see the status code 200
    And I should see the list of users with the following fields
      | data.id         |
      | data.email      |
      | data.first_name |
      | data.last_name  |
      | data.avatar     |
    And I should see the pagination metadata with the following fields
      | page        |
      | per_page    |
      | total       |
      | total_pages |
    And I should see the following fields with the respective values
      | field        | value                                                                    |
      | support.url  | https://reqres.in/#support-heading                                       |
      | support.text | To keep ReqRes free, contributions towards server costs are appreciated! |

  Scenario: Get Specific User
    When I made a GET request to the endpoint '/users/1'
    Then I should see the status code 200
    And I should see the following fields with the respective values
      | field           | value                                                                    |
      | data.id         | 1                                                                        |
      | data.email      | george.bluth@reqres.in                                                   |
      | data.first_name | George                                                                   |
      | data.last_name  | Bluth                                                                    |
      | data.avatar     | https://reqres.in/img/faces/1-image.jpg                                  |
      | support.url     | https://reqres.in/#support-heading                                       |
      | support.text    | To keep ReqRes free, contributions towards server costs are appreciated! |

    Scenario: Create a new user
      When I inform the data to create a new user
      And I made a POST request to the endpoint '/users'
      Then I should see the status code 201
      And I should see the field 'id' with the generated value
      And I should see the field 'createdAt' with the actual date and time

  Scenario: Update a user
    When I inform the data to update a user
    And I made a PUT request to the endpoint '/users/1'
    Then I should see the status code 200
    And I should see the field 'updatedAt' with the actual date and time

  Scenario: Delete a user
    When I made a DELTE request to the endpoint '/users/1'
    Then I should see the status code 204