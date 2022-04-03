FROM cypress/included:8.7.0
WORKDIR /e2e

COPY package.json .
COPY package-lock.json .
ENV CI=1
RUN npm ci
RUN npx cypress verify
