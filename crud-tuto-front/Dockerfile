# Stage 1: Build the Angular application
FROM node:14.20-alpine as node

WORKDIR /app
COPY . .

# Install dependencies
RUN npm install

# Build the application for production
RUN npm run build -- --prod

# Stage 2: Create the Nginx server
FROM nginx:alpine

# Copy the built Angular app to the Nginx web server directory
COPY --from=node /app/dist/crudtuto-Front /usr/share/nginx/html

# Expose port 80 (the default HTTP port)
EXPOSE 80
