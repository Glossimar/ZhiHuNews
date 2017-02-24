# 寒假考核———————知乎日报
##apk下载地址：<br>
                                                                                                                                           
   [知乎日报apk下载](http://pan.baidu.com/s/1o78GbiU "悬停显示")
<br>

##功能：
  * 加入了滑动菜单，方便用户查看专题。并且点击滑动菜单右侧的关注图标可以将喜欢的专题置顶，不再关注时点击图标便可取消置顶；<br>
  * 点击编辑的头像可以查看编辑个人信息；<br>
  * 点击标题栏的评论图标可以查看评论，短评不看时可以折叠起来；<br>
  * 点击标题栏的点赞图标，可（假）以（装）点（赞）赞（了），并显示当前赞的数量，点赞以后图标会变成红色；<br>
  * 在查看具体新闻时可以用回顶部按钮方便回到顶部；<br>
  * 有wifi的时候会进行开启界面和主界面的自动缓存，当网络未连接时会读取之前的缓存信息；<br>

##用到的类以及类的方法
  * 用了HttpURLConnection发送了网络请求，并用Gson解析了json数据；<br>
  * Glide加载图片<br>
  * 继承了handle、ViewPager、PagerAdapter写了ViewPager无限轮播图:<br>
    主要用了viewPager的setCurrentItem()、handle继承后覆写的方法handleMessage()（虽然继承了<br>ViewPager.OnPageChangeListener然而。。。监听依然没加上。。。QWQ）<br>
  * RecyclerView和CardView用来显示新闻列表、主题日报、编辑信息和长评；<br>
  * 用了DrawerLayout实现了侧滑菜单；
  * 用了Bitmap、Convas、Paint、ColorFliter以及关联画笔的BitmapShader写了无法设置头像边界的圆形头像（我一直不是很懂他的平移。。。）；<br>
  * ExpandableListView实现了短评折叠；<br>
  * 在主界面的具体新闻中用了可折叠式标题栏CollaspingToolbarLayout将新闻对应的图片以及标题栏一起折叠；<br>
  * 利用File、FileOutputStream、ObjectOutputStream、FileInputStream、ObjectInputStream写了离线缓存的以及读取缓存的方法：主要为ObjectOutputStreamy的构造方法、该类中的方法writeObject()实现了序列化以及保存对象到文件，然后用ObjectInputStream的构造方法、
  该类中的方法readObject()为实现了反序列化以及读取对象
<br>

##使用的开源库：
<br>
#####Glide加载图片：
```java
compile 'com.github.bumptech.glide:glide:3.7.0'
```
<br>
#####Gson解析json数据：<br>
```java
compile 'com.google.code.gson:gson:2.7'
```

##P.S.
  * 真机调试时会出现点击延迟；<br>
  * 在虚拟机上调试时出现的“今日热闻”里面的新闻会比真机调试时的要少很多；<br>
  
   **虚拟机调试：**<br>
![](https://github.com/SunWanQi/ZhiHuNews/blob/master/app/src/main/res/drawable/jvm.png)<br><br><br>
   **真机调试：**<br>
![](https://github.com/SunWanQi/ZhiHuNews/blob/master/app/src/main/res/drawable/true.png)
