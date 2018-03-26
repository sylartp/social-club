const TARGET = process.env.npm_lifecycle_event;

if (TARGET === 'dev') {
    console.log('run dev!')
    module.exports = require('./webpack.dev.conf.js')
}
if (TARGET === 'build') {
    console.log('run prod!')
    module.exports = require('./webpack.prod.conf.js')
}

