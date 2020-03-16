# Stock-finder

## How to run app
Before you start the next steps, please go `server/src/main/resources`
and create an `application.properties` file.

Copy and paste the contents in your `application.dist.properties`
file into the `application.properties` file

Make sure you update the iex values with tokens: 

```
iex.token.publishable=pk_enter_your_key
iex.token.secret=sk_enter_your_secret_key
```

1. Go into your terminal
2. Run `cd server && ./gradlew clean build bootRun`
3. Open another window
4. Run `cd client && yarn install && yarn run serve`
5. Go to your browser and go to url `http://localhost:8080`