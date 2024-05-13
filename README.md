# Leaderboard Service

## Description
The Leaderboard Service is a RESTful API service built using Spring Boot and MongoDB. It is designed to manage the leaderboard for a coding platform's contest. The service allows users to submit their scores and retrieve the leaderboard rankings. Additionally, users can earn virtual badges based on their scores.

## Installation
To install and run this service, follow these steps:
1. Clone the repository: `git clone https://github.com/patilDevang04/CoderHack.git`
2. Install the required dependencies: `./gradlew build`
3. Start the service: `./gradlew bootRun`

Make sure you have MongoDB installed and running on your system.

## API Endpoints
The following API endpoints are available:

- `GET /users`: Get all users.
- `GET /users/{userId}`: Get a specific user by ID.
- `POST /users`: Register a new user.
- `PUT /users/{userId}`: Update a user's score.
- `DELETE /users/{userId}`: Deregister a user.

## Usage
To use this service, follow these steps:

1. Submit a user's score by sending a POST request to `/users/{userId}/scores`. Include the user's ID and score in the request body.

2. Delete a user by sending a DELETE request to `/users/{userId}`. This will deregister the user from the leaderboard.

3. Update a user's score by sending a PUT request to `/users/{userId}`. Include the updated score in the request body.

4. Register a new user by sending a POST request to `/users`. Include the user's details in the request body.

5. Get a specific user by ID by sending a GET request to `/users/{userId}`.

6. Get all users by sending a GET request to `/users`.

## Contributing
Contributions are welcome! Here's how you can contribute to this project:

1. Fork the repository.
2. Create a new branch: `git checkout -b feature/your-feature`.
3. Make your changes and commit them: `git commit -m 'Add some feature'`.
4. Push to the branch: `git push origin feature/your-feature`.
5. Submit a pull request.
