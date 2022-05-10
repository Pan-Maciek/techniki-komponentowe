
FROM node:14.17.1-alpine

RUN apk update && apk upgrade && \
    apk --no-cache --update add gcc musl-dev ca-certificates curl && \
    rm -rf /var/cache/apk/*

RUN npm install -g newman

WORKDIR /app

ENTRYPOINT ["sh", "./run-newman.sh"]
