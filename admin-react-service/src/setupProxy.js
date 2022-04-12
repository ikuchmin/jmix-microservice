// eslint-disable-next-line import/no-extraneous-dependencies
const { createProxyMiddleware } = require("http-proxy-middleware");

module.exports = function (app) {
    app.use(
        '/auth',
        createProxyMiddleware({
            target: 'http://localhost:8060',
            secure: false,
            changeOrigin: true,
        }),
    );
};
