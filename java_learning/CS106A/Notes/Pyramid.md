## Draw a pyramid.

![img](file:///C:\Users\11942\AppData\Local\Temp\ksohtml22096\wps1.jpg) 

编程思路：嵌套两个for循环，一个循环column排序，另一个循环row排序。

Column排序中，初始value为底层方块的数量，每次递减1即可（赋值给a）；row排序中涉及到brick的横坐标，每个row中第一个brick横坐标确定后，后边的brick每次递加一个brick宽度值即可（赋值为b），b的初始value为0，循环条件为**b<a**



Q：brick的纵坐标怎么确定？

A：每个row的brick共享一个纵坐标，所以这个纵坐标的值随column排序的递减而循环，特别注意其嵌套的位置，是在b循环之外，a循环之内。

 

 

 

 