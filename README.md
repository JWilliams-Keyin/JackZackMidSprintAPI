This project was made using Maven

Once project is installed locally, take the following steps to get it to run:

1. Go to the src/main/resources/application.properties and change the url and password information to match your local MySQL database
2. Run maven clean and maven package (can be done in terminal or using IntelliJ's Maven tab on the right of the screen)
3. In your terminal window, run: docker build -t YOURDOCKERUSERNAME/dsa_final_sprint_api:latest . (make sure to add the period at the end)
4. Once docker build is finished running, run: docker compose up

The project should now be running

Move on to JackZackFinalSprintFrontEnd to get install guide for the front end