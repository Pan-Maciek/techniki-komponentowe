# pull official base image
FROM node:16.14.2-alpine

# set working directory
WORKDIR /app
EXPOSE 3000

# add `/app/node_modules/.bin` to $PATH
ENV PATH /app/node_modules/.bin:$PATH

# install app dependencies
COPY package.json ./
COPY package-lock.json ./
RUN npm install -g npm@8.10.0
RUN npm install

# add app
COPY . ./

# start app
CMD ["npm", "start"]
