@echo off
echo Starting all microservices...
echo.

set docker_services=api-gateway inventory-service product-service order-service

for %%s in (%docker_services%) do (
    echo Starting %%s with Docker...
    cd %%s
    
    if exist "docker-compose.yml" (
        docker-compose up -d
    ) else if exist "compose.yaml" (
        docker compose up -d
    ) else (
        echo No docker-compose file found in %%s
    )
    
    cd ..
    echo %%s started successfully
    echo.
)

echo Starting frontend with npm...
cd microservices-shop-frontend
start cmd /k "npm start"
cd ..
echo Frontend started successfully
echo.

echo All services have been started!
echo You can check the status of docker containers using: docker ps
echo Frontend is running with npm start in a separate window
pause 