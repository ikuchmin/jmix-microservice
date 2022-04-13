// eslint-disable-next-line import/no-extraneous-dependencies
const { createProxyMiddleware } = require("http-proxy-middleware");

module.exports = function (app) {
    app.use(
        '/auth',
        createProxyMiddleware({
            target: process.env.REACT_APP_PROXY_IP,
            secure: false,
            changeOrigin: true,
        }),
    );
};
