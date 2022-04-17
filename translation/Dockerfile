FROM node:14.17.1-alpine

WORKDIR /app
EXPOSE 3000

COPY package.json ./
COPY package-lock.json ./
RUN npm ci

COPY . ./
ENTRYPOINT ["npm", "start"]
