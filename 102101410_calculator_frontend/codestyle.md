# 来自百度的html规范

## 1 前言

HTML 作为描述网页结构的超文本标记语言，在百度一直有着广泛的应用。本文档的目标是使 HTML 代码风格保持一致，容易被理解和被维护。

## 2 代码风格

### 2.1 缩进与换行

[强制] 使用 `4` 个空格做为一个缩进层级，不允许使用 `2` 个空格 或 `tab` 字符。

解释：对于非 HTML 标签之间的缩进，比如 script 或 style 标签内容缩进，与 script 或 style 标签的缩进同级。



```html
<style>
/* 样式内容的第一级缩进与所属的 style 标签对齐 */
ul {
    padding: 0;
}
</style>
<ul>
    <li>first</li>
    <li>second</li>
</ul>
<script>
// 脚本代码的第一级缩进与所属的 script 标签对齐
require(['app'], function (app) {
    app.init();
});
</script>
```

[建议] 每行不得超过 `120` 个字符。

解释：过长的代码不容易阅读与维护。但是考虑到 HTML 的特殊性，不做硬性要求。

### 2.2 命名

[强制] `class` 必须单词全字母小写，单词间以 `-` 分隔。

[强制] `class` 必须代表相应模块或部件的内容或功能，不得以样式信息进行命名。



```html
<!-- good -->
<div class="sidebar"></div>

<!-- bad -->
<div class="left"></div>
```

[强制] 元素 `id` 必须保证页面唯一。

解释：同一个页面中，不同的元素包含相同的 `id`，不符合 `id` 的属性含义。并且使用 `document.getElementById` 时可能导致难以追查的问题。

[建议] `id` 建议单词全字母小写，单词间以 `-` 分隔。同项目必须保持风格一致。

[建议] `id`、`class` 命名，在避免冲突并描述清楚的前提下尽可能短。



```html
<!-- good -->
<div id="nav"></div>
<!-- bad -->
<div id="navigation"></div>

<!-- good -->
<p class="comment"></p>
<!-- bad -->
<p class="com"></p>

<!-- good -->
<span class="author"></span>
<!-- bad -->
<span class="red"></span>
```

[强制] 禁止为了 `hook 脚本`，创建无样式信息的 `class`。

解释：不允许 `class` 只用于让 JavaScript 选择某些元素，`class` 应该具有明确的语义和样式。否则容易导致 CSS class 泛滥。

使用 `id`、属性选择作为 hook 是更好的方式。

[强制] 同一页面，应避免使用相同的 `name` 与 `id`。

解释：IE 浏览器会混淆元素的 `id` 和 `name` 属性， `document.getElementById` 可能获得不期望的元素。所以在对元素的 `id` 与 `name` 属性的命名需要非常小心。

一个比较好的实践是，为 `id` 和 `name` 使用不同的命名法。



```html
<input name="foo">
<div id="foo"></div>
<script>
// IE6 将显示 INPUT
alert(document.getElementById('foo').tagName);
</script>
```

### 2.3 标签

[强制] 标签名必须使用小写字母。

```html
<!-- good -->
<p>Hello StyleGuide!</p>

<!-- bad -->
<P>Hello StyleGuide!</P>
```

[强制] 对于无需自闭合的标签，不允许自闭合。

解释：常见无需自闭合标签有 `input`、`br`、`img`、`hr` 等。

```html
<!-- good -->
<input type="text" name="title">

<!-- bad -->
<input type="text" name="title" />
```

[强制] 对 `HTML5` 中规定允许省略的闭合标签，不允许省略闭合标签。

解释：对代码体积要求非常严苛的场景，可以例外。比如：第三方页面使用的投放系统。



```html
<!-- good -->
<ul>
    <li>first</li>
    <li>second</li>
</ul>

<!-- bad -->
<ul>
    <li>first
    <li>second
</ul>
```

[强制] 标签使用必须符合标签嵌套规则。

解释：比如 `div` 不得置于 `p` 中，`tbody` 必须置于 `table` 中。详细的标签嵌套规则参见[HTML DTD](http://www.cs.tut.fi/~jkorpela/html5.dtd)中的 `Elements` 定义部分。

[建议] HTML 标签的使用应该遵循标签的语义。

解释：下面是常见标签语义

- p - 段落
- h1,h2,h3,h4,h5,h6 - 层级标题
- strong,em - 强调
- ins - 插入
- del - 删除
- abbr - 缩写
- code - 代码标识
- cite - 引述来源作品的标题
- q - 引用
- blockquote - 一段或长篇引用
- ul - 无序列表
- ol - 有序列表
- dl,dt,dd - 定义列表

```html
<!-- good -->
<p>Esprima serves as an important <strong>building block</strong> for some JavaScript language tools.</p>

<!-- bad -->
<div>Esprima serves as an important <span class="strong">building block</span> for some JavaScript language tools.</div>
```

[建议] 在 CSS 可以实现相同需求的情况下不得使用表格进行布局。

解释：在兼容性允许的情况下应尽量保持语义正确性。对网格对齐和拉伸性有严格要求的场景允许例外，如多列复杂表单。

[建议] 标签的使用应尽量简洁，减少不必要的标签。

```html
<!-- good -->
<img class="avatar" src="image.png">

<!-- bad -->
<span class="avatar">
    <img src="image.png">
</span>
```

### 2.4 属性

[强制] 属性名必须使用小写字母。

```html
<!-- good -->
<table cellspacing="0">...</table>

<!-- bad -->
<table cellSpacing="0">...</table>
```

[强制] 属性值必须用双引号包围。

解释：不允许使用单引号，不允许不使用引号。



```html
<!-- good -->
<script src="esl.js"></script>

<!-- bad -->
<script src='esl.js'></script>
<script src=esl.js></script>
```

[建议] 布尔类型的属性，建议不添加属性值。

```html
<input type="text" disabled>
<input type="checkbox" value="1" checked>
```

[建议] 自定义属性建议以 `xxx-` 为前缀，推荐使用 `data-`。

解释：使用前缀有助于区分自定义属性和标准定义的属性。

