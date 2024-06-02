const {defineConfig} = require('@vue/cli-service')

module.exports = defineConfig({
    transpileDependencies: true,
    chainWebpack: config => {
        config.module.rules.delete('eslint');
        config.module

            .rule('vue')
            .use('vue-loader')
            .tap(options => {
                options.transpileOptions = {
                    isTS: true,
                }
                return options
            })
    },
})
