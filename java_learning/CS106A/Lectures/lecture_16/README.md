### Array

Ordered

Homogeneous

#### Create an array

type[] name = new type[size];

eg:

```java
int[] arr = new int[5];
String[] list = new String[100];
//or:
int[] arr = {2, 4, 6, 8}；
```

index—0 ~ (size - 1)

arr[index] = int a;(直接赋值或修改reference)

eg: 

```java
arr[0] = 3;
```

```java
GOval[] circles = new GOval[10];

circles[0] = new GOval(0, 0, 100, 100);
```

可通过for循环修改整个array

```java
for(int i = 0; i < size; ++i){
    arr[i] = ...;
}
```



#### ++

i++: add 1 after return the value (post increment)

++i: add 1 before return the value (pre increment)



#### Size

Actual size: declared

Effective size: really using

```java
int[] scores = new int[500];//500 is actual size
int num = 0;
while(true){
    int score = readInt("?");
    if(score == SENTINEL) break;
    scores[num++] = score;
    /*
    score[num] = score;
    num += 1;
    */
 }
// num is effective size(used for giving average value)
```



#### Pass array

pass the whole array rather than element

eg:

```java
private void exchange(int[] arr, int pos1, int pos2){
	int temp = arr[pos1];
	arr[pos1] = arr[pos2];
	arr[pos2] = temp;
}
```



#### ArrayList

```java
import java.util.*;
//type name = new type();
ArrayList<String> strlist = new ArrayList<String>();
String line = "hello";
strlist.add(line);
strlist.size();
strlist.get(index); 
```

性能与功能：

- **Array**：通常性能较高，因为数组有固定的内存分配，且数组访问快；是Java的基本数据结构，不是对象，因此没有提供丰富的方法，如添加、删除元素等。

- **ArrayList**：访问和添加元素的操作通常比数组慢，因为ArrayList在内部使用数组，并且可能涉及到数组扩容的额外操作；实现了`List`接口，提供了一系列方法，如`add()`, `remove()`, `clear()`, `size()`等，功能更加强大。

  ```java
  boolean add(<T> element);
  void add(int index, <T> element);
  boolean remove(<T> element);
  void clear();
  int size();
  <T> get(int index);
  <T> set(int index, <T> value);
  int indexOf(<T> value);
  boolean contains(<T> value);
  boolean isEmpty();
  ```

  

接口与功能

- **Array**：在创建时必须指定大小，且未显式初始化的元素将具有默认值（如数字类型的默认值为0，对象的默认值为null）。
- **ArrayList**：不需要指定初始大小，未添加元素的位置默认为null。

内存分配

- **Array**：在栈上分配一段连续的内存空间。
- **ArrayList**：对象存储在堆上，内部使用数组来存储元素集合。



eg:

```java
public void run(){
    ArrayList<String> strlist = new  ArrayList<String>();
    while(true){
    	String line = readline("?");
        if(line.equal("")) break;
    	strlist.add(line);
    }
   for(int i = 0; i < strlist.size(); i++){
       println(strlist.get(i));
   }
}


private void readlist(ArrayList list){
    
}
```

**Arraylist hold objects**: Integer, Double, Boolean, Character

eg:

```java
int x = 5;
//Boxing
Integer y = new Integer(X);
//Unboxing
int z = y.intValue();

ArrayList<Integer> ilist = new ArrayList<Integer>();
int x = 5;
ilist.add(X);
int z = ilist.get(0); 
 
```

eg:

```java
public class GrayImage extends GraphicsProgram{
    public void run(){
        GImage image = new GImage(".gif");
        GImage grayImage = createGrayscaleImage(image);
        
        image.scale(2, 0);
        add(image, 10, 50);
        
        grayImage.scale(2,0);
        add(grayImage,380, 50);
    }
    
    private GImage greateGrayscaleImage(GImage image){
        int[][] array = image.getPixelArray();
        //.length是属性，适用于array；.getlength()是方法，可以适用于String
        int height = array.length;
        int width = array[0].length;
        
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width, j++){
                int pixel = array[i][j];
                
                int r = GImage.getRed(pixel);
                int g = GImage.getGreen(pixel);
                int b = GImage.getBlue(pixel);
                
                int xx = computeluminosity(r, g, b);
                
                array[i][j] = GImage.createRGBPixel(xx, xx, xx);
            }
       }
    }
    
    private int computeluminosity(int r, int g, int b){
        return Math.round(0.299 * r + 0.587 * g + 0.114 * b);
    }
    
}
```





#### Matrix

int[][] matrix = new int

matrix[index]—int[]

```java
int[ ][ ] matrix = new int[2][3];
for(int i = 0; i < 2; i++){
	for(int j = 0; j < 3; j++){
		matrix[i][j]...
	}
}
```



### Multi-dim arrays

````java
````

