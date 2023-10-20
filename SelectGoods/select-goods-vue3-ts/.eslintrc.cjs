module.exports = {
  env: {
    browser: true,
    es2021: true,
    node: true,
    jest: true
  },
  extends: [
    // eslint的全部规则默认是关闭的, 这个配置项开启推荐规则 具体要参考文档, 比如函数名不能重名
    'eslint:recommended',
    // vue3的语法规则
    'plugin:vue/vue3-essential',
    // ts的语法规则
    'plugin:@typescript-eslint/recommended',
    'plugin:prettier/recommended'
  ],
  // 要为特定类型的文件指定处理器, 比如我们要让eslint校验markdown
  overrides: [
    {
      env: {
        node: true
      },
      files: ['.eslintrc.{js,cjs}'],
      parserOptions: {
        sourceType: 'script'
      }
    }
  ],
  // 指定如何解析语法
  parser: 'vue-eslint-parser',
  parserOptions: {
    // 校验ecma最新版本
    ecmaVersion: 'latest',
    /*
        eslint检查语法也需要解析器 我们的解析器有3种
        1. Esprima 默认解析器
        2. Babel-ESlint babel解析器
        3. @typescript-eslint/parser ts解析器
      */
    parser: '@typescript-eslint/parser',
    // 默认设置为 script, 或者指明为 module, 表示代码在ecmascript模块中
    sourceType: 'module'
  },
  // 给eslint这个工具 安装的其他的插件, 写的时候可以省略前缀名 eslint-plugin-
  plugins: [
    // 检测vue语法的插件
    'vue',
    // 检测ts语法的插件
    '@typescript-eslint'
  ],
  // 具体规则
  rules: {
    // eslint (https://eslint.bootcss.com/docs/rules/)
    // eslint (https://zh-hans.eslint.org/docs/latest/use/configure/rules/)
    // typescript (https://typescript-eslint.io/rules/)
    // eslint-plugin-vue (https://eslint.vuejs.org/rules/)
    'vue/multi-word-component-names': 0
  }
}
