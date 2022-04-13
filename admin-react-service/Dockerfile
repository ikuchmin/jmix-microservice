FROM node:latest AS builder
WORKDIR /app
COPY package.json ./
RUN npm install --force
COPY . .
CMD ["npm", "build"]

FROM nginx:alpine
WORKDIR /usr/share/nginx/html
RUN rm -rf ./*
COPY --from=builder /app/build .
ENTRYPOINT ["nginx", "-g", "daemon off;"]
