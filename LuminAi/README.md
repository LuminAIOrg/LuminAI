# luminai

## Runnin the application

### Backend
Run standard quarkus configuration
You can find this configuration when opening the subproject LuminAI in IntelliJ

### Frontend
Run the watch configuration
```Bash
npm run watch
```
In the `src/main/frontend` directory

if you are not working on the frontend you don't need to run the this

---

**THE FRONTEND DOES NOT WORK WHEN THE BACKEND ISNT RUNNING**

This command should also work but it doesn't for some reason
```Bash
./mvnw exec:exec@npm-watch quarkus:dev
```