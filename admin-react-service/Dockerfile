FROM nginx
COPY build /usr/share/nginx/html
COPY config/nginx/admin-react.conf.template /etc/nginx/templates/
RUN rm /etc/nginx/conf.d/default.conf
