FROM node:18 AS builder
WORKDIR /app
COPY package.json .
RUN npm install
RUN yarn install

COPY . .
RUN npm run build

FROM nginx:latest
COPY --from=builder /app/build /usr/share/nginx/html

RUN rm /etc/nginx/conf.d/default.conf
COPY nginx.conf /etc/nginx/conf.d

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]