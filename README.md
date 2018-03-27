# NeteaseMusic 仿网易云音乐客户端网站😉

查询接口方面用了自己去年写的一个项目 [NeteaseApi](https://github.com/leftvalue/NeteaseApi),然后仿照[网易云音乐 for mac](http://music.163.com/#/download)的样式写了网页,搜索请求通过后端的加密重新请求网易云音乐的服务器,得到的 json 经由服务端返给前端,而指定歌曲的实际播放地址与 album 封面图均由页面直接解析 json 跨域获取.

### Demo 地址
![截图](/screenshot/demo.png)
[左值的仿网易云音乐🤩](http://www.leftvalue.top:8080/neteasemusic/dev/html/index.html)
### 现已实现
* 页面的初步模仿
* 搜索功能
* 点击搜索歌曲结果的播放功能

### Todo
* 页面细节微调
* 页面的响应式布局
* 播放的进度控制与音量控制
* 实现一个通用的不依赖框架的可拖拽可配置进度条(用于播放进度与音量控制等)
* 主题更换

**注:本项目仅用于学习目的**