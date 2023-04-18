
# 奇门排盘
# 风水罗盘

源码链接

https://github.com/MartingKing/AAAI


本人为易学爱好者，从师学习奇门遁甲，风水八字等，虽然网上有很多奇门排盘的软件，但是都是非安卓原生代码实现的，所以为了验证自己所学，原生代码写了个自动排盘以及风水罗盘，完整代码可以完美运作，目前排出的奇门盘有时家奇门，道家奇门


Android端具体逻辑是将时间选择转为农历，再根据干支纪年法，五行生克，十二长生，八门九星等宫位排列规则用recyclerview进行计算排序，

![image](https://github.com/MartingKing/xuanxueapp/blob/master/images/1.jpg)
![image](https://github.com/MartingKing/xuanxueapp/blob/master/images/2.jpg)
![image](https://github.com/MartingKing/xuanxueapp/blob/master/images/3.jpg)
![image](https://github.com/MartingKing/xuanxueapp/blob/master/images/4.jpg)
![image](https://github.com/MartingKing/xuanxueapp/blob/master/images/5.jpg)
![image](https://github.com/MartingKing/xuanxueapp/blob/master/images/6.jpg)
![image](https://github.com/MartingKing/xuanxueapp/blob/master/images/7.jpg)
![image](https://github.com/MartingKing/xuanxueapp/blob/master/images/8.jpg)
![image](https://github.com/MartingKing/xuanxueapp/blob/master/images/9.jpeg)
![image](https://github.com/MartingKing/xuanxueapp/blob/master/images/10.jpeg)

奇门遁甲和八字排盘支持原生代码排盘，也支持服务端排盘，服务端为go语言编写，有需要也可以开发java版的服务端，小程，PC客户端等，有志同道合的朋友可以一起研究AI推算
![image](https://github.com/MartingKing/xuanxueapp/blob/master/images/11.png)

使用的第三方库为 https://6tail.cn/calendar/api.html#solar.ymd.html，感谢大佬的无私奉献

所有小程序，服务端，Android端代码均为本人独立开发，因本人目前经济困难，后续考虑全部免费开源，感谢！
合作wx:kotm0066
