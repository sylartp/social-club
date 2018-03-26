const path = require('path')
const webpack = require('webpack')
const vendor = require('./vendor.js')
const CleanWebpackPlugin = require('clean-webpack-plugin')
const HtmlWebpackPlugin = require('html-webpack-plugin')
const ExtractTextPlugin = require('extract-text-webpack-plugin')

const PATHS = {
    src: path.join(__dirname, './src'),
    dist: path.join(__dirname, './dist')
};

module.exports = {
    context: __dirname,
    entry:{
        app: [PATHS.src],
        vendor
    },
    output:{
        filename: "[name].[hash].js",
        path: PATHS.dist
    },
    module:{
        rules: [
            {
                test: /\.(less)$/,
                include: PATHS.src,
                use: ExtractTextPlugin.extract(
                    {
                    fallback: 'style-loader',
                    use: [
                        {loader: 'css-loader'},{
                            loader: 'less-loader',
                            options: {
                                sourceMap: true
                            }
                        }
                    ]
                }
            )
            },
            {
                test: /\.(js|jsx)$/,
                loader: "babel-loader",
                include: PATHS.src
            },
            {
                test: /\.(png|svg|jpg|gif)$/,
                use: ['file-loader']
            },
            {
                test: /\.(woff|woff2|eot|ttf|otf)$/,
                use: ['file-loader']
            }
        ]
    },
    optimization: {
        splitChunks: {
            cacheGroups: {
                vendor: {
                  test: /[\\/]node_modules[\\/]/,
                  chunks: 'initial',
                  name: 'vendor',
                  filename: 'bundle.[chunkhash].js',
                  priority: 10,
                  enforce: true
                }
            }
        },      
    },
    plugins:[
        new CleanWebpackPlugin(PATHS.dist, {verbose: true}),
        new HtmlWebpackPlugin(
            {
                template: './src/index.html'
            }),
        new ExtractTextPlugin('css/theme.[contenthash].css'),
    ]
}