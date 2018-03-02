const path = require('path')
const ExtractTextPlugin = require("extract-text-webpack-plugin")
const CleanCSSPlugin = require("less-plugin-clean-css")


module.exports = {
    entry:'./src/app.js',
    output:{
        filename: "bundle.js",
        path: path.resolve(__dirname,'./dist')
    },
    module:{
        rules: [
            {
                test: /\.(less)$/,
                use: new ExtractTextPlugin.extract({
                    fallback: 'style-loader',
                    use: [
                        'css-loader',{
                            loader: 'less-loader',
                            options: {
                                plugins: [new CleanCSSPlugin({advanced: true})]
                            }
                        }
                    ]
                })
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
    plugins:{}
}