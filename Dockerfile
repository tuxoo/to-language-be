FROM node:16-alpine

WORKDIR /app
COPY package*.json ./
COPY tsconfig*.json ./

COPY src /app/src
COPY config /app/config

ENV NODE_PATH=./dist

RUN npm install
RUN npm run build

EXPOSE 9000
CMD [ "node", "./dist/src/app.js" ]