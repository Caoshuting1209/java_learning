### Interface

set of methods

common functionality among set of classes

```
public class ClassName
implements InterfaceName {
	...
	code
	...
}
```

### Map

key

value

no order

#### HashMap(class)

```java
HashMap<String, String> dict = new HashMap<String, String>();

HashMap<String, Integer>


//method: put and get

dict.put(key, value);
dict.get(key);
dict.remove(key); 
dict.size();
dict.keySet();
dict.containKey();

//Iterator type
list through set of values
eg1:
ArrayList<String> names = new ArrayList<String>();
Iterator<String> it = names.iterator();
while(it.hasNext()){
    println(it.next());
}

eg2:
Iterator<String> i = dict.keySet().iterator();
while(i.hasNext()){
    String name = i.next();
    //Integer instead of int because sometimes the value could be null.
    Integer num = dict.get(name);
    println(name + "=: " + num);
} 
//or
for(String name: Collection){
    Integer num = dict.get(name);
    println(name + "=: " + num);
}


```

