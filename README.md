# Tambola Claim Validator

## Overview

This project provides a set of validators for the Tambola (Housie) game, focusing on validating various types of claims, including Full House claims. The validators check if the claims meet the required conditions based on the announced numbers and the ticket's numbers.

## Project Structure

- **`FullHouseValidator`**: Validates if a ticket's claim is a full house by checking if all numbers on the ticket are announced and if the last announced number is present on the ticket. Utilizes individual line validators for top, middle, and bottom lines.


- **`ClaimValidator`**: An interface that defines the contract for validating claims.


- **`ClaimValidatorFactory`**: A factory class to obtain instances of line validators (TopLine, MiddleLine, BottomLine) based on the claim type.


- **`Ticket`**: Represents a Tambola ticket containing a 3x9 matrix of numbers.


- **`ClaimStatus`**: Represents the status of a claim (Accepted or Rejected).


- **`TambolaModerator`**: Manages the game state and performs claim validations.
