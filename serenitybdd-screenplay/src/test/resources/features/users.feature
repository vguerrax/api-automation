#encoding: utf-8

Feature: Users

  Background:
    Given "Victor" is a valid user

  Scenario: List users
    When he makes a GET request to the endpoint '/users'
    Then he should see the status code 200
    And he should see the list of users with the following fields
      | data.id         |
      | data.email      |
      | data.first_name |
      | data.last_name  |
      | data.avatar     |
    And he should see the pagination metadata with the following fields
      | page        |
      | per_page    |
      | total       |
      | total_pages |
    And he should see the following fields with the respective values
      | support.url  | https://reqres.in/#support-heading                                       |
      | support.text | To keep ReqRes free, contributions towards server costs are appreciated! |

  Scenario: Get specific user
    When he makes a GET request to the endpoint '/users/1'
    Then he should see the status code 200
    And he should see the following fields with the respective values
      | data.id         | 1                                                                        |
      | data.email      | george.bluth@reqres.in                                                   |
      | data.first_name | George                                                                   |
      | data.last_name  | Bluth                                                                    |
      | data.avatar     | https://reqres.in/img/faces/1-image.jpg                                  |
      | support.url     | https://reqres.in/#support-heading                                       |
      | support.text    | To keep ReqRes free, contributions towards server costs are appreciated! |

  Scenario: Create a new user
    When he informs the data to create a new user
    And he makes a POST request to the endpoint '/users'
    Then he should see the status code 201
    And he should see the field 'id' with the generated value
    And he should see the field 'createdAt' with the actual date and time

  Scenario: Update a user
    When he informs the data to update a user
    And he makes a PUT request to the endpoint '/users/1'
    Then he should see the status code 200
    And he should see the field 'updatedAt' with the actual date and time

  Scenario: Delete a user
    When he makes a DELETE request to the endpoint '/users/1'
    Then he should see the status code 204