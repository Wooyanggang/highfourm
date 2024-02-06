FROM node:20 AS builder
WORKDIR /app/frontend
COPY package.json .
RUN npm install
RUN yarn install

COPY . .
RUN npm run build

FROM nginx
RUN rm /etc/nginx/conf.d/default.conf
RUN rm -rf /etc/nginx/conf.d/*
COPY ./nginx.conf /etx/nginx/conf.d/

COPY --from=builder fonrend/build /usr/share/nginx/html
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]