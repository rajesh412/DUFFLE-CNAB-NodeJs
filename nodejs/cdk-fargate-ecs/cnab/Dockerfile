FROM node:8.14.0-alpine
RUN apk update
RUN apk add -u bash && npm install -g aws-cdk && npm install -g typescript
# run chmod +x /cnab/app/run
# WORKDIR /cnab/app/fargate
COPY Dockerfile /cnab/Dockerfile
COPY app /cnab/app

WORKDIR /cnab/app/myapp-fargate
CMD [ "/cnab/app/run" ]
