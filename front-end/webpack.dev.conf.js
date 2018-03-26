const path = require('path')
const merge = require('webpack-merge')
const webpack = require('webpack')
const common = require('./webpack.common.js')

module.exports = merge(common, {
    mode: 'development',
    devtool: 'eval-source-map',
    plugins:[
        new webpack.HotModuleReplacementPlugin()
    ],
    devServer: {
        contentBase: path.resolve(__dirname, 'dist'),
        inline: true,
        compress: true,
        port: 9092,
        publicPath: '/',
        host: '0.0.0.0',
        historyApiFallback: {
            rewrites: [
                { from: /^\/$/, to: 'index.html' },
            ]
        }
    }
})