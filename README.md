# springboot-redis 实现排序功能
## 组件
  -springboot 2.x
  -redis
  -swagger2
## 功能
  > add
  >> 添加商品的方法，用来将将物品添加到redis中，score默认为 0
  
  > show
  >> 显示所有的商品
  
  > buy
  >> name：商品名称    num ：购买的数量
  >>> 购买商品
  
  > getRank
  >> name : 商品名称
  >>> 根据商品名称获取排行信息
  
  > getRanks
  >> 获取排行榜前五名
  
  > getRanking
  >> 获取排行榜前五名和对应的销量

