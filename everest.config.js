module.exports = {
  pages: {
    index: {
      entry: './src/index.js',
      template: './public/index.html'
    }
  },
  themes: ['white', 'blue'],
  alias: {
    '@': './src'
  },
  proxy: {
    context: ['/tenant/**', '/api/**', '/notify/**', '/frontend/**'],
    target: 'http://newprod.dev.cn/'
  }
}
