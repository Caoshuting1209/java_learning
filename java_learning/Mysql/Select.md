### select语句

#### SQL分类： 

- DDL： 数据定义语言（create, alter, drop, rename, truncate）

- DML：数据操作语言（insert, delete, update, select）

- DCL：数据控制语言（commit, rollback, savepoint, grant, revoke）

  

#### SQL规则和规范

##### SQL语言规则

- SQL可以写一行或者多行，各子句最好分行，必要时缩进
- 命令行中语句以; 或者\g或者\G结束
- 关键字不缩写，不分行

##### SQL大小写规范

- windows大小写不敏感
- linux大小写敏感
- 推荐采用统一的规范：**数据库名、表名、表别名、字段名、字段别名**都小写；**SQL关键字、函数名、绑定变量**都大写

##### 注释

- #注释单行 
- -- 也可以注释单行（注意一定要有空格）
- /*   */注释多行，不能嵌套



#### 数据导入指令

- source 文件的全路径名（命令行）

- 基于图形化管理工具（如SQLyog-工具-执行SQL脚本）

  

#### Selcet语句

##### 基本语句

```mysql
SELECT ... FROM ...(表名)
*表示表中所有字段
eg1. SELECT * FROM ...
eg2. SELECT a, b, c FROM ...(a, b, c是字段名)
```

##### 列的别名

```mysql
SELECT a b, c FROM...;(b是a的别名，查询结果集中列名a会变成b)
或 SELECT a AS b, c FROM...;
或 SELECT a "b" , c FROM...;
#如果b包含空格，必须用双引号(不要用单引号)
```

##### 去除重复行

```mysql
SELECT a FROM...;
#结果集可能包含重复行

SELECT DISTINCT a FROM...;
#只显示唯一结果

#对于多条字段去重
SELECT a, DISTINCT b FROM ...(error)
SELECT DISTINCT b, a FROM ...(可以运行，且只去重a和b都相同的记录)
```

##### 空值参与运算

```mysql
#null不等同于0、''、'null'
#null参与运算结果也为空

#解决方案
SELECT salary, salary * (1 + IFNULL(rate, 0)) * 12 "年工资" FROM ...
#以上语句代表当rate值为null时，按照0来计算
```

##### 着重号

```mysql
#文件名和关键字冲突，用着重号表示(但最好不要冲突)
eg. SELECT * FROM `order`;
```

##### 查询常数

```mysql
SELECT 'abc', a, b FROM ...;
#该语句会给表中每条记录都加一个abc的属性
```

##### 显示表结构

```mysql
DESCRIBE ...(表名);
或者 DESC ...(表名);
#该语句显示了表中字段的详细信息，field, type, null, key, default...
```

##### 过滤数据（WHERE）

```mysql
SELECT * FROM ...
#过滤条件，生命在FROM后边
WHERE a = 90;
#查询字段a值为90的所有记录

WHERE B = 'KING';(字符集需要用单引号引起来)
```



#### 运算符

##### 算数运算符

```mysql
SELECT 100, 100 + 50,
FROM DUAL(伪表);

SELECT 100 + '1'
FROM DUAL;
#输出101，SQL中没有连接符
#但在java中输出1001（连接符）

SELECT 100 + 'a'
FROM DUAL;
#输出100（字符串存在隐式转换，按照0计算）

#null参与运算结果为null

SELECT 100 / 2
FROM DUAL;
#结果是浮点型，不会抹去小数点后的东西
#分母为0结果为null
#/和div可换用

SELECT 12 % 3, 12 % 5
FROM DUAL;
#结果符号与被除数相同
#-12 % -5结果是-2
#%可以和mod换用
```

##### 比较运算符

```mysql
#比较结果为真，返回1,比较结果为假，返回0，其他情况返回null
#基本符号
= 等于
如果一侧是数字，另一侧是字符串，字符串隐式转换为0
如果两侧都是字符串，则不会隐式转换
只要有NULL参与比较，结果就为NULL

<=> 安全等于
解决NULL比较问题
SELECT 1 <=> NULL, NULL <=> NULL
FROM DUAL;
返回结果为0和1，而不是NULL

<> 不等于
!= 不等于
<
<=
>
>=

# 关键字运算符
*
IS NULL
IS NOT NULL
ISNULL(); #相当于一个函数
eg:
SELECT a
FROM ...
WHERE a IS NULL/ IS NOT NULL
或 WHERE ISNULL(a);
或 WHERE NOT a <=> NULL;

*
LEAST() / GREATEST()
eg:
SELECT LEAST(a, b)
FROM...
#a和b哪个小就输出哪个

*
BETWEEN ... AND ... / NOT BETWEEN ... AND ...
#查询一个区间，包含边界值
eg:
SELECT a
FROM ...
WHERE a BETWEEN X AND Y;
# X为下界，Y为上界，不能交换，否则查询不到数据

*
IN (SET) / NOT IN (SET)
eg:
SELECT a
FROM ...
WHERE a IN / NOT IN (x, y, z);

*
LIKE
#模糊查询
eg:
SELECT a
FROM ...
WHERE a LIKE '%x%';
#查询a中包含x的记录
# %%代表不确定个数字符
#'x%'代表以x开头，'%x'代表以x结尾

WHERE a LIKE '%x%' AND a LIKE '%y%';
或者 WHERE a LIKE '%x%y%' OR a LIKE '%y%x%';
# a中同时包含x和y的记录

WHERE a LIKE '_x%';
# a中第二个字符为x的记录
#_代表一个不确定字符

WHERE a LIKE '_\_x%';
#\表示转义字符，该字符后边的内容不代表不确定字符，而只代表字符本身是一个下划线
#该语句是查询a中第一个字符不确定，第二个字符为_，第三个字符为x的记录

*
REGEXP
#语法：a REGEXP 匹配条件
#满足条件返回1，不满足条件返回0，匹配条件任意一个为NULL，返回NULL
'^'匹配以该字符后边的字符开头的字符串
'$'匹配以该字符前面的字符结尾的字符串
'.'匹配任意一个字符
"[]"匹配方括号内的任何字符，或者的关系（"[0-9]" "[a-z]"分别匹配任意数字和任意字母）
'*'匹配0个或者多个在它前边的字符

```

##### 逻辑运算符

```mysql
#输出1或者0或者NULL
#AND优先级高于OR
NOT 或 !
AND 或 &&
OR 或 ||
XOR
eg:
WHERE x XOR y
#要么满足x不满足y，要么满足y不满足x，有且只有一个被满足
```

##### 位运算符（使用频率较低）

```mysql
#转化为二进制，运算后结果再转化为十进制
& 和
| 或
^ 异或
~ 取反
>>
<<

eg:
SELECT 12 & 5, 12 | 5, 12 ^ 5
FROM DUAL;
输出：4,13,9

SELECT 4 << 1, 8 >> 1
FROM DUAL;
#4的每一位都向左移动一位，8的每一位都向右移动一位
#在一定范围内满足：左移一位乘以2，右移一位除以2
输出：8，4
```

##### 运算符的优先级

()的优先级最高



#### 排序与分页

##### 排序

- 如果没有使用排序操作，默认返回的数据按照添加数据的顺序排序
- 使用ORDER BY对数据进行排序，升序操作ASC(ascend)，降序操作DESC(descend)

```mysql
SELECT a, b
FROM ...
ORDER BY a DESC;
#如果ORDER BY后没有注明，默认升序
#这里可以使用列的别名进行排序，但过滤条件中不可以使用列的别名

SELECT a, b
FROM ...
WHERE ...
ORDER BY a DESC;
#WHERE 和 ORDER BY 同时出现，一定要先把WHERE放在FROM后边

#二级/多级排序
SELECT a, b
FROM ...
ORDER BY a DESC, b ASC;
#按照a的降序排序，a相同时按照b的升序排列
ORDER BY a, b ASC;
#按照a的升序排列，a相同时按照b的升序排列
```

##### 分页

- 使用LIMIT实现数据的分页显示

```mysql
SELECT a
FROM ...
LIMIT 0, 20;
#0指示开始的位置（偏移量），20指示记录总条数
#当偏移量为0时可省略

#Mysql 8.0的写法
SELECT a
FROM ...
LIMIT 10 OFFSET 0;
#10表示条目数，0表示偏移量

SELECT a
FROM ...
LIMIT (n - 1) * pageSize, pageSize;
#每页显示pageSize条记录，此时显示第n页

#注意声明顺序，LIMIT必须放在最后
SELECT a, b, c
FROM ...
WHERE a > 1000
ORDER BY a DESC
LIMIT 0, 10;
```



#### 多表查询

##### 笛卡尔积错误

- 缺少多表连接条件
- 连接条件无效

```mysql
eg:错误示例
SELECT a, b
FROM table1, table2;
或 FROM table1 CROSS JOIN table2;
#意图：找到table1中的a字段，并且匹配其在table2中的b字段
#这种错误写法会导致输出条数为table1的条数 * table2的条数

*
#两个表连接条件
SELECT a, b
FROM table1, table2
#连接条件
WHERE table1.c = table2.c;

*
eg: 错误示例
SELECT a, c
FROM table1, table2
#连接条件
WHERE table1.c = table2.c;
#错误原因：table1和table2中都包括c，不明确SELECT的c来自哪个表
#更正为：
SELECT a, table1.c
FROM table1, table2
WHERE table1.c = table2.c;

#从优化的角度来看，建议每个查询字段前都指明其所在的表

*
#可以给表起别名，简化语句;一旦在FROM中起了别名，SELECT和WHERE中必须用别名
SELECT a, table1.c
FROM table1 t1, table2 t2
WHERE t1.c = t2.c;

*
#多表查询:如果有n个表，则至少需要n - 1个连接条件，否则会出现笛卡尔积错误
SELECT t1.a, t2.b, t3.c
FROM table1 t1, table2 t2, table3 t3
WHERE t1.x = t2.x && t2.y = t3.y;
```

##### 多表查询的分类

- 角度1：等值连接 / 非等值连接
- 角度2：自连接 / 非自连接
- 角度3：内连接 / 外连接

```mysql
#非等值连接
SELECT t1.a, t2.b
FROM table1 t1, table2 t2
WHERE t1.a BETWEEN t2.x AND t2.y

#自连接
SELECT tx.a, ty.a
FROM table1 tx, table1 ty
WHERE tx.d = ty.a;

#内连接：结果集中不包含一个表与另一个表不匹配的行
#外连接：结果集中除了包含匹配行，还查询到了左表或右表中不匹配的行
	#左外连接
	#右外连接
	#满外连接

*
#92语法内连接：同上
#92语法外连接(在MySQL不适用)：
	#左外
    SELECT a, b
    FROM table1 t1, table2 t2
    WHERE t1.c = t2.c(+);
    
    #右外
    SELECT a, b
    FROM table1 t1, table2 t2
    WHERE t1.c(+) = t2.c;
    
#SQL99语法中使用JOIN...ON的方式实现多表查询，可以解决MySQL中外连接的问题
#SQL99实现内连接
    SELECT a, b
    FROM table1 t1 JOIN table2 t2
    #连接条件
    ON t1.c = t2.c
    #添加多表
    JOIN...
    ON...

#SQL99实现外连接
    #左外连接
    SELECT a, b
    FROM table1 t1 LEFT JOIN table2 t2
    ON t1.c = t2.c;

    #右外连接
    SELECT a, b
    FROM table1 t1 RIGHT JOIN table2 t2
    ON t1.c = t2.c;
    
    #满外连接（该语法在MySQL中不支持）
    SELECT a, b
    FROM table1 t1 FULL JOIN table2 t2
    ON t1.c = t2.c;
    
#怎样在MySQL中用99语法实现满外连接？
```

##### UNION的使用

- UNION：返回两个查询的并集，并去除重复记录
- UNION ALL：返回并集，不去除重复记录（如果明确知道不存在重复记录，或者不需要去除，推荐用UNION ALL，效率更高）

```mysql
#以下是一个左外连接，包含两个表的并集以及左表额外的部分
SELECT a, b
FROM table1 t1 LEFT JOIN table2 t2
ON t1.c = t2.c
#在此基础上,去除并集
WHERE t2.c IS NULL;

#满外连接的实现
	#方法一
	#先实现左外连接
	SELECT a, b
    FROM table1 t1 LEFT JOIN table2 t2
    ON t1.c = t2.c
    #在合并右边额外部分
    UNION ALL
    SELECT a, b
    FROM table1 t1 RIGHT JOIN table2 t2
    ON t1.c = t2.c
    #在此基础上,去除并集
    WHERE t1.c IS NULL;	
```

##### SQL99语法的一些新特性

- 自然连接：自动查询两种连接表中所有相同字段，然后进行等值连接

  ```mysql
  SELECT a, b
  FROM table1 t1 NATURAL JOIN table2 t2;
  ```

- USING的使用：USING后跟两个table中共有的字段并自行进行匹配（不适用自连接）

  ```mysql
  SELECT a, b
  FROM table1 t1 JOIN table2 t2
  USING c;
  ```



#### 函数

##### MySQL的内置函数及分类

- 分类一：数值函数，字符串函数，日期和时间函数，流程控制函数，加密与解密函数、获取MySQL信息函数、聚合函数 / 分组函数 / 多行函数等
- 分类二：单行函数（输入一个输出一个）、聚合函数（输入多个输出一个）

##### 单行函数

- 数值函数：基本函数、三角函数、指数对数函数、进制转换函数

- 字符串函数

  ```mysql
  #java中的字符串用""，但这里用''
  ASCII('abc') ---返回第一个字符(a)的ASCII值
  
  CHAR_LENGTH('我们') ---字符的个数，一个汉字1个字符
  LENGTH('我们') ---字节数，一个汉字三个字节
  
  CONCAT(s1, s2 ...) ---拼接多个字符串
  CONCAT_WS(x, s1, s2 ...) ---用x来拼接后边的所有字符串
  
  #字符串的索引从1开始
  INSERT(str, idx, len, replacestr) ---把从索引idx开始的len个字符替换成replacestr
  REPLACE(str, a, b) ---把str中的a替换成b
  UPPER() / LOWER() ---大小写切换
  
  LEFT(str, n) / RIGHT(str, n) ---去左/右边的n个字符
  LPAD(str, len, pad)：左对齐 / RPAD(str, len, pad)：右对齐 ---最终输出占10个位，不足的在左/右边补充pad字符
  
  TRIM() --- 去除首尾空格
  LTRIM()/RTRIM() --- 去除左/右边空格
  TRIM(s1 FROM s) ---去除s首尾的s1
  TRIM(LEADING s1 FROM s) ---去除头部s1
  TRIM(TRAILING s1 FROM s)---去除尾部s1
  
  REPEAT(str, n) ---重复n次str
  SPACE(n) ---重复n个空格
  
  STRCMP(s1, s2) --- 比较字符串大小，返回s1-s2
  
  SUBSTR(s, index, len) ---子字符串
  
  LOCATE(substr, str) ---定位首次出现的索引，没找到返回0
  ELT(m, s1, s2...) ---返回指定位置的字符串，如果m = 1，返回s1
  FIELD(s, s1, s2...) --- 返回后边字符串中s首次出现的位置索引
  FIND_IN_SET(s1, s2) --- 返回s1在s2集合中首次出现的位置索引
  
  REVERSE(s)---反转
  
  NULLIF(s1, s2) ---如果s1 = s2，返回NULL，否则返回s1
  ```

- 日期和时间类型函数

  - 获取日期、时间

  - 日期与时间戳转换

    ```mysql
    UNIX_TIMESTAMP() ---以时间戳形式返回当前时间
    UNIX_TIMESTAMP(date) ---将date以时间戳形式返回
    FROM_UNIXTIME(timestamp) ---将时间戳传华为普通格式的时间
    ```

  - 获取月份、星期、星期数、天数

  - 日期操作函数

    ```mysql
    EXTRACR(type FROM date)
    ```

  - 时间和秒钟转换

  - 计算日期和时间的函数（较多应用）

    ```mysql
    #ADD/SUB：加减
    DATE_ADD(datetime, INTERVAL expr type) ---在现有的datetime基础上加expr type
    DATE_ADD(datetime, INTERVAL 'expr1_expr2' type1_type2)
    或者
    ADDDATE(date, INTERVAL expr type) ---在现有的date基础上加上expr type
    
    ADDTIME(time1, time2) ---在time1的基础上加time2
    DATEDIFF(date1, date2) ---计算日期差值
    TIMEDIFF(time1, time2) ---计算时间差值
    #如果time只填写一个数，默认为秒，否则写成'a: b: c'表示时分秒
    
    FROM_DAYS(N) ---从0000年1月1日起N天后的日期
    TO_DAYS(date) ---返回日期距离0000年1月1日的天数
    
    LAST_DAY(date) ---返回date所在月份的最后一天日期
    MAKEDATE(year, n) ---返回year年第n天的日期
    MAKETIME(hour, minute, second)
    PERIOD_ADD(time, n) ---返回time加上n后的时间
    
    NOW() + 0 ---将时间转化为数值，比如2022-10-25 23:10:14转化为20221025231014
    ```

  - 日期的格式化与解析

    ```mysql
    #格式化：日期--->字符串
    #解析：字符串--->日期
    DATE_FORMAT(date, fmt)
    eg: DATE_FORMAT(CURDATE(), '%Y-%M-%D')
    #拓展
    年 %Y
    月（大写为月份英文全称，小写为数字） %M %m
    日（大写为日期英文全称，小写为数字） %D %d
    时（24小时制） %H
    分 %i
    秒 %S
    
    #格式化的逆过程，fmt必须和格式化的格式完全一样
    STR_TO_DATE(str, fmt)
    
    TIME_FORMAT(time, fmt)
    
    GET_FORMAT(date_type, format_type) --- 用于便捷提供格式
    eg: GET_FORMAT(DATE, 'USA') ---返回美国一般把日期写成什么形式
    ```

- 流程控制函数

  ```mysql
  *
  IF(value, value1, value2) ---如果value为真，返回value1，否则返回value2
  IFNULL(value1, value2) ---如果value1为NULL,输出value2，否则输出value1
  
  *
  #多选的场景
  CASE WHEN...THEN...
       WHEN...THEN...
       ELSE...(这个可以不要，默认为NULL)
       END
       
  eg: CASE WHEN a < 10 THEN b
  		 WHEN a > 20 THEN c
  		 END
  #表示当a小于10时，输出b，a大于20时，输出c
  		   
  		   
         
  CASE expr(表达式) WHEN...THEN...
       WHEN...THEN...
       ELSE...(这个可以不要，默认为NULL)
       END
       
  eg: CASE a WHEN 10 THEN b * 1.1
  		   WHEN 20 THEN b * 1.2
  		   END
  #表示当a等于10时，输出b*1.1，a为20时，输出b*1.2	   
  ```

- 加密解密函数

  ```mysql
  *加密函数
  #mysql 8.0弃用
  PASSWORD(str) ---把str加密成一个字符串
  
  #mysql 8.0可用，不可逆
  MD5(str) 
  SHA(str)
  
  #mysql 8.0弃用
  #互为逆操作，可以得到乱码的暗文
  ENCODE(value, password_seed) --- 
  DECODE(value, password_seed) ---
  ```

- MySQL信息函数

  ```mysql
  VERSION() ---版本号
  
  CONNECTION_ID() ---连接ID
  
  DATEBASE() ---数据库
  SCHEMA() ---数据库
  
  USER() ---用户名
  CURRENT_USER() ---用户名
  
  CHARSET(str) ---编码集
  COLLATION() ---比较关系
  ```

- 其他函数

  ```mysql
  FORMAT(value, n) ---四舍五入保留小数点后n位（n <= 0，只保留整数位）
  CONV(value, from, to) --- 将value在不同进制间转换
  
  INET_ATON(ipvalue) ---将以点分隔的ip地址转化为一个数字
  INET_NTOA(value) ---将数字形式的ip地址转化为以点分隔
  
  BENCHMARK(n, expr) ---将表达式expr重复执行n次，用于测试MySQL处理expr所耗费的时间
  
  CHARSET(str) ---编码集（会得到str的char_code）
  CONVERT(value USING char_code) ---将value所使用的字符编码修改为char——code
  ```



##### 聚合函数

- 常见的聚合函数

  - AVG / SUM

    ```mysql
    SELECT AVG(a), SUM(b)
    FROM table1;
    #a,b都为数值，否则没有意义
    #这两个函数求值都会过滤NULL，AVG()在除以个数的时候，如果有记录a字段为NULL，则不计入其中
    
    SELECT AVG(IFNULL(a, 0))
    或 SELECT SUM(a) / COUNT(1)
    或 SELECT SUM(a) / COUNT(IFNULL(a, 0))
    FROM table1;
    #这个函数求平均时把空值也计算在内
    ```

  - MAX / MIN

    ```mysql
    SELECT MIN(a), MAX(b)
    FROM table1;
    #a,b可以为字符串，按照char_code规律比较
    #a,b可以为日期时间类型
    ```

  - COUNT：计算指定字段在查询结构中出现的个数

    ```mysql
    SELECT COUNT(*), COUNT(1)
    FORM table1;
    #以上两种方法都可以查询表中所有记录的个数
    
    SELECT COUNT(a)
    FORM table1;
    #查询指定字段出现在多少记录中时，不包含字段为NULL值的记录
    
    #如果该字段没有NULL，那么三种方法中COUNT(*), COUNT(1), COUNT(a)三种方法中：
    #如果使用MyISAM存储引擎，三种方法效率相同
    #如果使用InnoDB存储引擎，COUNT(*) = COUNT(1)> COUNT(a)
    ```

- GTOUP BY的使用

  - 单字段分组

    ```mysql
    #查询a字段相同的记录的b的平均值
    eg:
    SELECT a, AVG(b)
    FROM table1
    GROUP BY a;
    ```

  - 使用多字段分组

    ```mysql
    #先按照a字段进行分组，查询a字段中c字段不同的各组的b的平均值
    eg:
    SELECT a, c, AVG(b)
    FROM table1
    GROUP BY a, c;
    或
    SELECT c, a, AVG(b)
    FROM table1
    GROUP BY c, a;
    
    错误示例：
    SELECT a, c, AVG(b)
    FROM table1
    GROUP BY a;
    
    #SELCET后的字段一定要声明在GROUP BY中，反之不一定
    #GROUP BY声明在FROM、WHERE后边，ORDER BY、LIMIT前边
    #MySQL中GROUP BY中使用WITH ROLLUP，不仅展示分组平均，还会展示整体平均；但不能再进行ORDER BY操作
    SELECT a, AVG(b)
    FROM table1
    GROUP BY a WITH ROLLUP;
    ```

- HAVING的使用：用来过滤数据

  ```mysql
  #一旦过滤条件出现聚合函数，则必须使用HAVING替换WHERE
  #HAVING必须声明在GROUP BY后面
  #通常HAVING依托于GROUP BY，不单独使用
  SELECT a, MAX(b)
  FROM table1
  GROUP BY a
  HAVING MAX(b) > 100;
  
  #当两个字段一起过滤，两种方式都可以，但推荐第一种，因为执行效率更高
  #当过滤条件中有聚合函数时，则此过滤条件必须声明在HAVING中
  #当过滤条件中没有聚合函数时，...可以声明在WHERE和HAVING中，但建议声明在WHERE中
  SELECT a, MAX(b)
  FROM table1
  WHERE a IN (set)
  GROUP BY a
  HAVING MAX(b) > 100;
  或
  SELECT a, MAX(b)
  FROM table1
  GROUP BY a
  HAVING MAX(b) > 100 && a IN (set);
  ```

- SQL底层执行原理

  - SELECT语句的完整结构

    ```mysql
    #SQL92
    SELECT ...,...
    FROM..., ...
    WHERE 多表连接条件 AND 不包含聚合函数的过滤条件
    GROUP BY...,...
    HAVING 包含聚合函数的过滤条件
    ORDER BY...
    LIMIT...;
    
    #SQL99
    SELECT ...
    FROM...JOIN...ON 表连接条件
    	   JOIN...ON 表连接条件
    WHERE 不包含聚合函数的过滤条件
    GROUP BY...
    HAVING 包含聚合函数的过滤条件
    ORDER BY...
    LIMIT...;
    ```

  - SQL语句的执行过程

    ```mysql
    (2)SELECT ...
    
    (1)FROM...JOIN...ON 表连接条件
    	   JOIN...ON 表连接条件
        WHERE 不包含聚合函数的过滤条件
        GROUP BY...
        HAVING 包含聚合函数的过滤条件
        
     (3)ORDER BY...
        LIMIT...;
    ```



##### 子查询

> 在SELECT中，除了GROUP BY和LIMIT，都可以用子查询

- 分析一个案例

  ```mysql
  #eg: 查询table1中，哪些记录的a值比b = name的高
  #方法1
  SELECT a
  FROM table1
  WHERE b = name;
  #得到结果n，代入下列语句
  SELECT a
  FROM table1
  WHERE a > n;
  
  #方法2：自连接(效率高)
  SELECT a, b
  FROM table1 tx, table1 ty
  WHERE ty.a > tx.a
  AND tx.b = name
  
  #方法3：子查询
  SELECT a, b
  FROM table1
  WHERE a > (
      		SELECT a
              FROM table1
              WHERE b = name;
  		);
  #这里内查询是在比较关系的右侧
  ```

- 称谓的规范
  - 外查询（主查询）、内查询（子查询）
  - 子查询先执行，结果被主查询使用
  - 子查询要包含在括号内
  - 子查询放在比较条件的右侧
  - 单行操作符对应单行子查询，多行操作符对应多行子查询

- 子查询的分类
  - 单行子查询/多行子查询：子查询返回一个记录/多个记录
  - 相关子查询/不相关子查询：子查询被执行多次/一次

- 单行子查询

  ```mysql
  #操作符
  =
  !=
  <>
  <
  >=
  ...
  
  #如果子查询返回空值，不会报错，但结果也会是空值
  ```

- 多行子查询

  - 如果子查询结果中有NULL，主查询返回结果也会是NULL
  
  ```mysql
  #操作符
  IN 等于列表中的任意一个值
  ANY（别名SOME） 需要和单行比较操作符一起使用，和子查询返回的某一个值进行比较
  ALL 需要和单行比较操作符一起使用，和子查询返回的所有制进行比较
  
  #MySQL中聚合函数不能嵌套
  eg:查询平均工资最低的部门id
  #方法1
  SELECT department_id
  FROM table1
  ORDER BY department_id
  HAVING AVG(salary) = (
      SELECT MIN(avg_sal)
      FROM(
           SELECT AVG(salary) "avg_sal"
           FROM employees
           GROUP BY department_id
          )t_dept_avg_sal
  );
  #FROM后边也可以写子查询，新生成一个表，并且命名为t_dept_avg_sal，解决聚合函数嵌套的问题
  
  #方法2
  SELECT department_id
  FROM table1
  ORDER BY department_id
  HAVING AVG(salary) <= ALL(
           SELECT AVG(salary) "avg_sal"
           FROM employees
           GROUP BY department_id
          );
  ```
  

- 相关子查询

  ```mysql
  eg1：查询员工工资中大于本部门平均工资的员工的last_name, salary和department_id
  #方法1：相关子查询
  SELECT last_name, salary, dapartment_id
  FROM table1 tx
  WHERE salary > (
      SELECT AVG(salary)
      FROM table1 ty
      WHERE department_id = tx.department_id
  );
  
  #方式2:先生成一个新的table，然后引入新表作为查询条件
  SELECT tx.last_name, tx.salary, tx.dapartment_id
  FROM table1 tx, (SELECT department_id, AVG(salary) avg_sal
                  FROM table1
                  ORDER BY department_id)t_tmp
  WHERE tx.departmeng_id =  t_tmp.department_id
  AND tx.salary > t_tmp.avg_sal;
  
  
  eg2：查询员工的id，salary，按照department_name（该字段不在table1里）排序
  SELECT employee_id, salary,department_name
  FROM tabal1 tx
  ORDER BY (
      SELECT department_name
      FROM table2 ty
      WHERE tx.department_id = ty.department_id
  );
  
  #EXISTS与NOT EXISTS关键字
  #EXISTS如果子查询中不存在满足条件的行，条件返回false，在子查询中继续查找，反之不在子查询中继续查找，条件返回true
  eg：返回表中所有管理者的...
  SELECT employee_id, last_name, job_id
  FROM employees e1
  WHERE EXISTS(
  			SELECT *
      		FROM employees e2
      		WHERE e1.employee_id = e2.manager_id	
  			);
  			
  select de.de_id, de.de_name
  from de
  WHERE NOT EXISTS(
      SELECT *
      FROM em
      WHERE de.de_id = em.de_id
  )
  			
  ```



### 数据库的创建

#### 基本知识

##### 标识符命名规则

- 数据库、表名不超过30个字符
- 只能包含A-Z，a-z，0-9，_
- 不能重名
- 对象名中间不能有空格
- 不要包含关键字
- 保证字段名和类型的一致性

#### 创建和管理数据库

##### 数据库的创建

```mysql
#使用默认字符集
CREATE DATABASE XXX;
#指明数据库字符集
CREATE DATABASE XXX CHARACTER SET 'gbk';
#如果要创建的数据库已经存在，则创建不成功，但不会报错；否则创建成功（推荐）
CREATE DATABASE IF NOT EXISTS XXX CHARACTER SET 'utf8';
```

##### 数据库的管理

```mysql
#查看当前连接中的数据库
SHOW DATABASE;
#切换数据库
USE XXX;
#查看当前数据库中保存的数据表
SHOW TABLES;
#查看当前使用的数据库
SELECT DATABASE() FROM DUAL;
#查看指定数据库下都有哪些表
SHOW TABLES FROM XXX;
```

##### 数据库的修改（但通常不改）

```mysql
#更改字符集
ALTER DATABASE XXX CHARACTER SET '';
#DATABASE不能改名
```

##### 数据库的删除

```mysql
#方式一
DROP DATABASE XXX;
#方式二：如果数据库存在，则删除成功，否则也不报错
DROP DATABASE IF EXISTS XXX;
```

#### 表的创建及管理

##### 表的创建

```mysql
#方式一：完全新建
#定位数据库
USE XXX;
#创建表（需要具有创建表的权限），如果创建表中没有指明伺服机，则默认使用数据库的字符集
CREATE TABLE IF NOT EXISTS XXX(
#字段名 + 数据类型
id INT,
name VARCHAR(15)
hire_date DATE
);
#查看表结构
DESC XXX;
或
SHOW CREATE TABLE XXX;
#查看表数据
SELECT * FROM XXX;


#方式二：基于现有的表，同时导入数据
CREATE TABLE XXX
AS
#查询语句中的字段别名可以作为新表的字段
SELECT a, b, c
FROM table1;
```

##### 表的管理

- 修改

  - 添加一个字段

    ```mysql
    #默认添加到表中的最后一个字段
    ALTER TABLE XXX
    ADD xx DOUBLE(10, 2);
    
    #指定位置
    ALTER TABLE XXX
    ADD xx VARCHAR(20) FIRST;
    或...AFTER xx;
    ```

  - 修改一个字段（一般不修改数据类型）

    ```mysql
    #修改字段长度
    ALTER TABLE XXX
    MODIFY xx VARCHAR(25) ;
    ```

  - 重命名一个字段

    ```mysql
    ALTER TABLE XXX
    CHANGE old_name new_name DOUBLE(10, 2);
    ```

  - 删除一个字段

    ```mysql
    ALTER TABLE XXX
    DROP COLUMN xx;
    ```

- 重命名

  ```mysql
  #方式一(推荐)
  RENAME TABLE XXX
  TO XXX;
  
  #方式二
  ALTER TABLE XXX
  RENAME TO XXX;
  ```

- 删除（无法撤销，慎用）

  ```mysql
  #删除表结构和数据，释放表空间
  DROP TABLE IF EXISTS XXX;
  ```

- 清空

  ```mysql
  #清空表数据，但表结构保留
  TRUNCATE TABLE XXX;
  
  #DELETE FROM也可以实现对表中数据的删除，同时保留表结构，差异如下：
  （1）TRUNCATE TABLE 不支持回滚
  （2）DELETE FROM 可以实现回滚
  
  /*
  DDL和DML操作的说明
  # DDL（create, alter, drop, rename, truncate）的操作一旦执行，就不可回滚（因为此操作后一定会执行一次COMMIT）
  # DML（insert, update, delete, select）的操作默认情况也是不可回滚的，但如果在执行之前执行了SET autocommit = FALSE，则执行的DML操作可以回滚
  */
  ```

- DCL中的COMMIT和ROLLBACK
  - COMMIT：提交数据。一旦执行，数据永远保存在数据库，不可以回滚
  - ROLLBACK：可以实现数据回滚，回滚到最近的COMMIT之后

- MySQL的原子化（8.0以后）：DDL操作要么成功要不回滚

  ```mysql
  eg:
  DROP TABLE book1, book2;
  #如果book1存在，但book2不存在，那么这个操作报错，book1也不会被删除
  ```



### 数据处理之增删改

> DML操作默认情况下会提交数据，如果希望执行后不自动提交，需使用SET autocommit = FALSE;

#### 添加数据

```mysql
#方式一：逐条添加
（1）没有指明添加字段，必须按照声明字段的先后顺序添加
INSERT INTO XXX
VALUES (value1, value2, value3,...);

（2）指明添加字段，可以不按照声明字段顺序添加（推荐）
#如果字段没写全，没指名的字段默认为NULL
INSERT INTO XXX(a, b, c...)
VALUES (value_a, value_b, value_c,...);

（3）同时添加多条记录
INSERT INTO XXX(a, b, c...)
VALUES(value_a, value_b, value_c,...),
VALUES(value_a, value_b, value_c,...),
...;

#方式二：将SELECT结果插入表中
#SELECT的字段必须与INSERT的字段一一对应，并且声明的INSERT字段范围不能低于SELECT的字段范围，否则有添加不成功的风险
INSERT INTO XXX(a, b, c...)
SELECT a1, b1, c1...
FROM table1
WHERE...
```

#### 更新数据

```mysql
#可以实现批量数据修改
UPDATE ... SET ... WHERE
eg:
UPDATE XX（表名）
SET xx = xx（字段）
WHERE ...（定位记录位置）;

#同时修改一条数据的多个字段
UPDATE ...
SET..., ...
WHERE...;

#修改数据时存在不成功的情况，可能是由于约束的影响
```

#### 删除数据

```mysql
DELETE FROM... WHERE
WHERE...;

#删除数据时存在不成功的情况，可能是由于约束的影响
```

#### MySQL8新特性：计算列

```mysql
CREATE TABLE XXX(
a INT,
b INT,
c INT) GENERATED ALWAYS AS (a + b) VIRTUAL;
```



### MySQL数据类型

