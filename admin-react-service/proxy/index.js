const express = require('express');
const request = require('request');
const cors = require('cors');
const proxy = express();

const proxyUrl = "http://localhost:8060";
const proxyPartial = 'proxy';
const credentials = false;
const origin = "*";

proxy.use(cors({credentials, origin}));
proxy.options('*', cors({credentials, origin}));

proxy.use('/' + proxyPartial, function(req, res) {
    req.pipe(
        request(proxyUrl + req.url)
            .on('response', response => {
                response.headers['access-control-allow-origin'] = "*";
            })
    ).pipe(res);
});

proxy.listen(8010);
