FROM node:8.14.0-alpine
RUN apk update
RUN apk add -u bash
COPY server.js /app/
WORKDIR /app
run chmod +x /app/*
RUN npm install
RUN ls app
CMD [ "node" , "server.js" ]