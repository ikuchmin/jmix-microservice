FROM node:latest
WORKDIR /app
COPY package.json ./
RUN npm install --force
COPY . .
CMD ["npm", "start"]
