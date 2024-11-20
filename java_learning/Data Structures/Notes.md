### Defining and using classes

static method(general to all, if called, using class name )

e.g. public static void main(String[] args  ){}



non-static method(instance method, if called, using an instance name)

e.g. public void makeNoise(){}

when non-static method was called, you need to new a instance variable first

 

static methods can't access "my" instance variables;



### Type

primitive type: put value in the box 

reference type: put pointer in the box 



### LinkedList and ArrayList

LinkedList：for add and remove

ArrayList：for frequent access



### Testing

```
input = ;
expected = ;
actual = ;
assertThat(actural).isEqualTo(expected);
```



### Sorting

#### Basic sorts

##### Heapsort

```java
public class HeapSort {

    public void sort(int arr[]) {
        int n = arr.length;

        // 构建最大堆
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        // 逐个提取元素
        for (int i = n - 1; i > 0; i--) {
            // 将当前最大元素移到数组末尾
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // 调用 heapify 在减小的堆上调整
            heapify(arr, i, 0);
        }
    }

    // 堆调整函数
    void heapify(int arr[], int n, int i) {
        int largest = i; // 初始化最大元素索引为 i
        int l = 2 * i + 1; // 左子节点索引
        int r = 2 * i + 2; // 右子节点索引

        // 如果左子节点大于根节点
        if (l < n && arr[l] > arr[largest]) {
            largest = l;
        }

        // 如果右子节点大于当前最大元素
        if (r < n && arr[r] > arr[largest]) {
            largest = r;
        }

        // 如果最大元素不是当前根节点
        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            // 递归地在受影响的子堆上调用 heapify
            heapify(arr, n, largest);
        }
    }

    // 打印数组函数
    static void printArray(int arr[]) {
        int n = arr.length;
        for (int i = 0; i < n; ++i) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // 主函数，用于测试
    public static void main(String args[]) {
        int arr[] = {12, 11, 13, 5, 6, 7};
        int n = arr.length;

        HeapSort ob = new HeapSort();
        ob.sort(arr);

        System.out.println("Sorted array is");
        printArray(arr);
    }
}
```

![image-20240913155429813](C:\Users\11942\AppData\Roaming\Typora\typora-user-images\image-20240913155429813.png)



##### Mergesort (fastest stable sort)

```java
public class MergeSort {

    // 主方法，用于调用递归排序
    public void sort(int arr[], int left, int right) {
        if (left < right) {
            // 找到中间索引
            int mid = (left + right) / 2;

            // 递归地对左半部分进行排序
            sort(arr, left, mid);
            // 递归地对右半部分进行排序
            sort(arr, mid + 1, right);

            // 合并两个已排序的部分
            merge(arr, left, mid, right);
        }
    }

    // 合并两个已排序的子数组
    public void merge(int arr[], int left, int mid, int right) {
        // 找出左右子数组的大小
        int n1 = mid - left + 1;
        int n2 = right - mid;

        // 创建临时数组
        int L[] = new int[n1];
        int R[] = new int[n2];

        // 复制数据到临时数组
        for (int i = 0; i < n1; ++i) {
            L[i] = arr[left + i];
        }
        for (int j = 0; j < n2; ++j) {
            R[j] = arr[mid + 1 + j];
        }

        // 合并临时数组回到原数组
        int i = 0, j = 0;
        int k = left;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        // 复制剩余的元素
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    // 打印数组
    static void printArray(int arr[]) {
        int n = arr.length;
        for (int i = 0; i < n; ++i) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // 主函数，用于测试
    public static void main(String args[]) {
        int arr[] = {12, 11, 13, 5, 6, 7};

        System.out.println("Given Array");
        printArray(arr);

        MergeSort ob = new MergeSort();
        ob.sort(arr, 0, arr.length - 1);

        System.out.println("\nSorted array");
        printArray(arr);
    }
}
```

![image-20240913155231920](C:\Users\11942\AppData\Roaming\Typora\typora-user-images\image-20240913155231920.png)

![image-20240913155132474](C:\Users\11942\AppData\Roaming\Typora\typora-user-images\image-20240913155132474.png)



##### insertion (efficient for small N or almost sorted arrays)

```java
public class InsertionSort {
    // 插入排序方法
    public void sort(int arr[]) {
        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            int key = arr[i];
            int j = i - 1;

            // 将大于key的元素向后移动
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }

    // 打印数组
    static void printArray(int arr[]) {
        int n = arr.length;
        for (int i = 0; i < n; ++i) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // 主函数，用于测试
    public static void main(String args[]) {
        int arr[] = {12, 11, 13, 5, 6};

        System.out.println("Original Array");
        printArray(arr);

        InsertionSort ob = new InsertionSort();
        ob.sort(arr);

        System.out.println("Sorted Array");
        printArray(arr);
    }
}
```

![image-20240913155337994](C:\Users\11942\AppData\Roaming\Typora\typora-user-images\image-20240913155337994.png)![image-20240913155346180](C:\Users\11942\AppData\Roaming\Typora\typora-user-images\image-20240913155346180.png)![image-20240913155353889](C:\Users\11942\AppData\Roaming\Typora\typora-user-images\image-20240913155353889.png)



##### quicksort (fastest sort)

```java
//median finding costs a lot of time
public class QuickSort {

    // 快速排序的主方法
    public void sort(int[] arr, int low, int high) {
        if (low < high) {
            // 找到分区点
            int pi = partition(arr, low, high);

            // 递归排序左半部分
            sort(arr, low, pi - 1);

            // 递归排序右半部分
            sort(arr, pi + 1, high);
        }
    }

    // 分区操作
    private int partition(int[] arr, int low, int high) {
        // 选择最后一个元素作为基准
        int pivot = arr[high];
        int i = (low - 1); // 小于基准的元素的索引

        for (int j = low; j < high; j++) {
            // 如果当前元素小于或等于基准
            if (arr[j] <= pivot) {
                i++;

                // 交换 arr[i] 和 arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // 交换 arr[i+1] 和 arr[high]（基准元素）
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1; // 返回分区点索引
    }

    // 打印数组
    static void printArray(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    // 主函数，用于测试
    public static void main(String[] args) {
        int[] arr = {10, 7, 8, 9, 1, 5};

        System.out.println("Original Array");
        printArray(arr);

        QuickSort ob = new QuickSort();
        ob.sort(arr, 0, arr.length - 1);

        System.out.println("Sorted Array");
        printArray(arr);
    }
}
```



### Array.sort()

```java
import java.util.Arrays;
public class Main {
    public static void main(String[] args) {
        char[] arr = {'b', 'a', 'd', 'c', 'e'};
        Arrays.sort(arr);
        System.out.println(arr); // 输出: [a, b, c, d, e]
    }
}

import java.util.Arrays;
public class Main {
    public static void main(String[] args) {
        String[] arr = {"banana", "apple", "date", "cherry", "elderberry"};
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr)); // 输出: [apple, banana, cherry, date, elderberry]
    }
}

```



### interface

Interface is a specification of what a List is able to do, not how to do it.

```java
public static String longest(List<String> list){
    ...
}
//List is a hypernym

public static void main(String[] args){
    Alist<String> someList = new Alist<>();
    ...
    System.out.println(longest(someList));
    //Alist or SLList are hyponym of List, so the method longest(someList) can work well.
}

```

#### Overriding and overloading

@Override

**Overriding**  If a “subclass” has a method with the exact same signature as in the        “superclass”, we say the subclass overrides the method.

@Overload

**Overloading**  Methods with the same name but different signatures are overloaded.**



#### Interface Inheritance

**Interface inheritance(What)**  Subclass inherits signatures, but NOT implementation.

keyword: implements

*When a class is a hyponym of an interface, we used implements; If you want one class to be a hyponym of another class, you use extends.*



**Implementation inheritance(How)**  Subclasses can inherit signatures AND implementation.

keyword: default

e.g.

```java
public interface List<Item>{
	...
    default public void print(){}
}

public class IsADemo{
    public static void main(String[] args){
        List<String> someList = new SLList<>();
        someList.print();
        //print() can work well
    }
}
```



### Extends

If you want one class to be a hyponym of another class, you use **extends**.







### Static Type vs. Dynamic Type

**static type** (compile-time type): type specified at declaration

**dynamic type** (run-time type): type specified at instantiation



### TreeSet and TreeMap

![image-20240914131400474](C:\Users\11942\AppData\Roaming\Typora\typora-user-images\image-20240914131400474.png)

**Set**  store keys

**Map** store key/value pairs

**ArraySet**  Θ(N) operations in the worst case.

**BST**  Θ(log N) operations in the worst case if tree is balanced.



#### TreeSet/HashSet

**TreeSet** The runtime to complete a search on a “bushy” BST in the worst case, where N is the number of nodes is **Θ(log N)**

**HashSet** Easy to add or remove keys



#### BST

The “height” of a tree determines the **worst** case runtime to find a node.

Example: Worst case is contains(s), requires (height +1) comparisons.

The “average depth” determines the **average** case runtime to find a node.

Example: Average case is (average depth + 1) comparisons.



Worst case Θ(N) height.

Best case Θ(log N) height.



#### B-trees(Splitting trees)：B-Trees are a modification of the binary search tree that avoids Θ(N) worst case.

B-trees of order L=3 (like we used today) are also called a 2-3-4 tree or a 2-4 tree.

 “2-3-4” refers to the number of children that a node can have, e.g. a 2-3-4 tree node may have 2, 3, or 4 children.

B-trees of order L=2 are also called a 2-3 tree.

Two nice invariants: (1) All leaves must be the same distance from the root. (2)A non-leaf node with k items must have exactly k+1 children.

**contains and add are both O(log N).**



#### Left-Leaning Red Black Binary Search Tree (LLRB/Red-Black BST)

The corresponding LLRB has height that is never more than ~2 times the 2-3 tree height, so LLRBs also have logarithmic height!

**When inserting: Use a red link. If there is a right leaning “3-node”, we have a Left Leaning Violation.** *Rotate left the appropriate node to fix.*

**If there are two consecutive left links, we have an Incorrect 4 Node Violation.** *Rotate right the appropriate node to fix.*

**If there are any nodes with two red children, we have a Temporary 4 Node.** *Color flip the node to emulate the split operation.*



### 二叉树的遍历

#### DFS(递归遍历)

```java
// 二叉树的遍历框架
void traverse(TreeNode root) {
    if (root == null) {
        return;
    }
    // 前序位置(root-left-right)
    traverse(root.left);
    // 中序位置(left-root-right)
    traverse(root.right);
    // 后序位置(left-right-root)
}
```



#### BFS(层序遍历)

```java
void levelOrderTraverse(TreeNode root) {
    if (root == null) {
        return;
    }
    Queue<TreeNode> q = new LinkedList<>();
    q.offer(root);
    // 记录当前遍历到的层数（根节点视为第 1 层）
    int depth = 1;

    while (!q.isEmpty()) {
        int sz = q.size();
        for (while sz > 0) {
            TreeNode cur = q.poll();
            // 访问 cur 节点，同时知道它所在的层数
            System.out.println("depth = " + depth + ", val = " + cur.val);

            // 把 cur 的左右子节点加入队列
            if (cur.left != null) {
                q.offer(cur.left);
            }
            if (cur.right != null) {
                q.offer(cur.right);
            }
        }
        depth++;
    }
}
```



### 多叉树的遍历

#### DFS

```java
void traverse(Node root) {
    if (root == null) {
        return;
    }
    // 前序位置
    for (Node child : root.children) {
        traverse(child);
    }
    // 后序位置
}
```



#### BFS

```java
void levelOrderTraverse(Node root) {
    if (root == null) {
        return;
    }
    Queue<Node> q = new LinkedList<>();
    q.offer(root);
    // 记录当前遍历到的层数（根节点视为第 1 层）
    int depth = 1;

    while (!q.isEmpty()) {
        int sz = q.size();
        for (int i = 0; i < sz; i++) {
            Node cur = q.poll();
            // 访问 cur 节点，同时知道它所在的层数
            System.out.println("depth = " + depth + ", val = " + cur.val);

            for (Node child : cur.children) {
                q.offer(child);
            }
        }
        depth++;
    }
}
```



### Dynamic Programming/DFS/BFS

#### Dynamic Programming

**重叠子问题**

**最优子结构**

**状态转移方程**



#### DFS算法（回溯算法）



#### BFS算法



### Hashing

**Two important warnings for Hash Tables**

*Never store objects that can change in a HashSet or HashMap*

*Never override equals without also overriding hashCode*

![image-20240918162315773](C:\Users\11942\AppData\Roaming\Typora\typora-user-images\image-20240918162315773.png)
