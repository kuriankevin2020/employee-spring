#!/bin/sh

docker container run -it -p 8080:8080 -e DB_HOST=employee-db employee-app:1.0.0
