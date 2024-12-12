### Array(静态数组)

- 增：

  - 在末尾追加元素：`O(1)`。
  - 在中间（非末尾）插入元素：`O(N)`。

- 删：

  - 删除末尾元素：`O(1)`
  - 删除中间（非末尾）元素：`O(N)`。

- 查：给定指定索引，查询索引对应的元素的值，时间复杂度 `O(1)`。

- 改：给定指定索引，修改索引对应的元素的值，时间复杂度 `O(1)`。

  ```java
  //环形数组原理:优化头部删除和插入的时间复杂度
  // 长度为 5 的数组
  int[] arr = new int[]{1, 2, 3, 4, 5};
  int i = 0;
  // 模拟环形数组，这个循环永远不会结束
  while (i < arr.length) {
      System.out.println(arr[i]);
      i = (i + 1) % arr.length;
  }
  ```
  
  
  
  

### ArrayList(动态数组)

```java
ArrayList<Integer> arr = new ArrayList<>();
arr.add();//O(1)
arr.add(int index, element);//O(N)
arr.add(0, element);//O(N)
arr.remove(arr.size()-1);//O(1)
arr.remove(int index);//O(N)
arr.get(int index);//O(1)
arr.set(int index, element);//O(1)
arr.indexOf(element);//O(N)
```



### SLList(单链表)

```java
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

//SLList
ListNode creatLinkedList(int[] arr){
    if(arr == null || arr.length = 0){
        return null;
    }
    ListNode head = new ListNode(arr[0]);
    ListNode cur = head;
    for(int i = 0; i < arr.length; i++){
        cur.next = new ListNode(arr[i]);
        cur = cur.next;
    }
    return head;
}

*Traverse*
    
	ListNode head = createLinkedList(new int[]{1, 2, 3, 4, 5});
	for (ListNode p = head; p != null; p = p.next) {
   	 	System.out.println(p.val);
	}

*头部插入*
    
	ListNode head = createLinkedList(new int[]{1, 2, 3, 4, 5});
	ListNode newHead = new ListNode(0);
	newHead.next = head;
	head = newHead;
	// 现在链表变成了 0 -> 1 -> 2 -> 3 -> 4 -> 5

*尾部插入*
   
	ListNode head = createLinkedList(new int[]{1, 2, 3, 4, 5});
	// 在单链表尾部插入一个新节点 6
	ListNode p = head;
	// 先走到链表的最后一个节点
	while (p.next != null) {
    	p = p.next;
	}
	// 现在 p 就是链表的最后一个节点
	// 在 p 后面插入新节点
	p.next = new ListNode(6);
	// 现在链表变成了 1 -> 2 -> 3 -> 4 -> 5 -> 6

*中间插入——在第三个位置后插入6*
    
    ListNode head = createLinkedList(new int[]{1, 2, 3, 4, 5});
	ListNode p = head;
	for(int i = 0; i < 2; i++){
        p = p.next;
    }
	//此时P指向第三个位置;
	ListNode newNode = new ListNode(6);
	newNode.next = p.next;
	p.next = newNode;

*删除第四个节点*
    
    ListNode head = createLinkedList(new int[]{1, 2, 3, 4, 5});
	ListNode p = head;
	for(int i = 0; i < 2; i++){
        p = p.next;
    }
	//此时指针指向第三个节点
	p.next = p.next.next;

*删除头部节点*
    
    ListNode head = createLinkedList(new int[]{1, 2, 3, 4, 5});
	ListNode oldNode = head;
	head = head.next;
	//把旧的节点断开
	oldNode.next = null;
    

*删除尾部节点*
     ListNode head = createLinkedList(new int[]{1, 2, 3, 4, 5});
	ListNode p = head;
	while(p.next.next != null){
        p = p.next;
    }
//此时p指向倒数第二个节点
	p.next = null;

```



### DLList(双链表)

```java
class DoublyListNode {
    int val;
    DoublyListNode next, prev;
    DoublyListNode(int x) { val = x; }
}

DoublyListNode createDoublyLinkedList(int[] arr) {
    if (arr == null || arr.length == 0) {
        return null;
    }
    DoublyListNode head = new DoublyListNode(arr[0]);
    DoublyListNode cur = head;
    // for 循环迭代创建双链表
    for (int i = 1; i < arr.length; i++) {
        DoublyListNode newNode = new DoublyListNode(arr[i]);
        cur.next = newNode;
        newNode.prev = cur;
        cur = cur.next;
    }
    return head;
}


*Traverse*
    
    // 创建一条双链表
	DoublyListNode head = createDoublyLinkedList(new int[]{1, 2, 3, 4, 5});
	DoublyListNode tail = null;

	// 从头节点向后遍历双链表
	for (DoublyListNode p = head; p != null; p = p.next) {
    	System.out.println(p.val);
    	tail = p;
	}

	// 从尾节点向前遍历双链表
	for (DoublyListNode p = tail; p != null; p = p.prev) {
    	System.out.println(p.val);
	}


*头部插入*
    
    // 创建一条双链表
	DoublyListNode head = createDoublyLinkedList(new int[]{1, 2, 3, 4, 5});

 // 在双链表头部插入新节点 0
	DoublyListNode newHead = new DoublyListNode(0);
	newHead.next = head;
	head.prev = newHead;
	head = newHead;
// 现在链表变成了 0 -> 1 -> 2 -> 3 -> 4 -> 5


*尾部插入*
    
     // 创建一条双链表
	DoublyListNode head = createDoublyLinkedList(new int[]{1, 2, 3, 4, 5});

 // 在双链表尾部插入新节点 0
	DoublyListNode newTail = new DoublyListNode(0);
	DoublyListNode tail = head;
	while(tail.next != null){
        tail = tail.next;
    }

	tail.next = newTail;
	newTail.prev = tail;
	tail = newTail;

*中间插入——在第3个节点后插入6*
     // 创建一条双链表
	DoublyListNode head = createDoublyLinkedList(new int[]{1, 2, 3, 4, 5});
	DoublyListNode p = head;
	for(int i = 0; i < 2; i++){
        p = p.next;
    }
	//p指向第3个节点
	DoublyListNode ins = new DoublyListNode(6);

//组装新节点
	ins.next = p.next
	ins.prev = p;

//去除旧链接
	p.next.prev = ins;
	p.next = ins;

*中间删除第4个节点*
    // 创建一条双链表
	DoublyListNode head = createDoublyLinkedList(new int[]{1, 2, 3, 4, 5});
	DoublyListNode p = head;
	for(int i = 0; i < 2; i++){
        p = p.next;
    }
	//p指向第3个节点
	DoublyListNode del = p.next;

	p.next = del.next;
	del.next.prev = p;
	
	del.prev = null;
	del.next = null;
	
*头部删除*
    
  DoublyListNode head = createDoublyLinkedList(new int[]{1, 2, 3, 4, 5});
	DoublyListNode newHead = head.next;
	head.next = null;
	head.prev = null;
	head = newHead;

*尾部删除*
    DoublyListNode head = createDoublyLinkedList(new int[]{1, 2, 3, 4, 5});
	DoublyListNode p = head;
	while(p.next.next != null){
        p = p.next;
    }
//p指向倒数第二个节点
	p.next = null;
	p.next.prev = p;

```



### Queue

```java
// 队列的基本 API
class MyQueue<E> {
    
    int size();
    boolean add(E e)//添加元素，如果已满则抛出IllegalStateException
    boolean offer(E e)//添加元素，如果已满则返回false
    E remove()//移除并返回头部元素，若为空，则抛出NoSuchElementException
    E poll()//移除并返回头部元素，若为空，则返回null
    E element()//返回头部元素，若为空，抛出NoSuchElementException
    E peek() //返回头部元素，若为空，返回null
    boolean isEmpty();
    boolean contains(Object o);
}

// 栈的基本 API
class MyStack<E> {
    // 向栈顶插入元素，时间复杂度 O(1)
    void push(E e);
    // 从栈顶删除元素并返回，时间复杂度 O(1)
    E pop();
    // 查看栈顶元素，时间复杂度 O(1)
    E peek();
    // 返回栈中的元素个数，时间复杂度 O(1)
    int size();
    
    boolean contains(Object o);
}

```



### HashTable

- 负载因子： size / table.length，元素达到负载因子后，哈希表会扩容

- 哈希表的key一定是不可变类型（Integer, String）

- 哈希表的时间复杂度取决于hash()的时间复杂度

- 一般不在for循环中增/删哈希表的key

```java
// 哈希表伪码逻辑
class MyHashMap {

    private Object[] table;

    // 增/改，复杂度 O(1)
    public void put(K key, V value) {
        int index = hash(key);
        table[index] = value;
    }

    // 查，复杂度 O(1)
    public V get(K key) {
        int index = hash(key);
        return table[index];
    }

    // 删，复杂度 O(1)
    public void remove(K key) {
        int index = hash(key);
        table[index] = null;
    }

    // 哈希函数，把 key 转化成 table 中的合法索引
    // 时间复杂度必须是 O(1)，才能保证上述方法的复杂度都是 O(1)
    private int hash(K key) {
        // ...
    }
}
```



### Sorting

#### java标准库中的方法调用

```java
//对数组进行排序
Arrays.sort(); 

//对二维数组依据内层数组的第一个元素大小进行排序
Arrays.sort(nums, Comparator.comparingInt(a -> a[0]));
```



#### 选择排序

> 每次遍历找到未排序部分中的最小值，与当前索引交换位置（不稳定排序）

```java
void sort(int[] nums) {
    int n = nums.length;
    // sortedIndex 是一个分割线
    // 索引 < sortedIndex 的元素都是已排序的
    // 索引 >= sortedIndex 的元素都是未排序的
    // 初始化为 0，表示整个数组都是未排序的
    int sortedIndex = 0;
    while (sortedIndex < n) {
        // 找到未排序部分 [sortedIndex, n) 中的最小值
        int minIndex = sortedIndex;
        for (int i = sortedIndex + 1; i < n; i++) {
            if (nums[i] < nums[minIndex]) {
                minIndex = i;
            }
        }
        // 交换最小值和 sortedIndex 处的元素
        int tmp = nums[sortedIndex];
        nums[sortedIndex] = nums[minIndex];
        nums[minIndex] = tmp;

        // sortedIndex 后移一位
        sortedIndex++;
    }
}
```



#### 冒泡排序

> 倒序遍历，交换相邻逆序索引，但不改变相同元素相对位置（稳定性排序）

```java
// 对选择排序进行优化，获得稳定性的同时避免额外的 for 循环
// 这个算法有另一个名字，叫做冒泡排序
void sort(int[] nums) {
    int n = nums.length;
    int sortedIndex = 0;
    while (sortedIndex < n) {
        // 寻找 nums[sortedIndex..] 中的最小值
        // 同时将这个最小值逐步移动到 nums[sortedIndex] 的位置
        boolean swapped = false;
        for (int i = n - 1; i > sortedIndex; i--) {
            if (nums[i] < nums[i - 1]) {
                // swap(nums[i], nums[i - 1])
                int tmp = nums[i];
                nums[i] = nums[i - 1];
                nums[i - 1] = tmp;
                swapped = true;
            }
        }
        if(!swapped){
            //如果一次交换都没有进行，则已经有序，可以提前终止
            break;
        }
        sortedIndex++;
    }
}
```



#### 插入排序

> 将当前索引插入到有序数组中的正确位置（稳定性排序）

```java
// 对选择排序进一步优化，想左侧有序数组中插入元素
// 这个算法有另一个名字，叫做插入排序
void sort(int[] nums) {
    int n = nums.length;
    // 维护 [0, sortedIndex) 是有序数组
    int sortedIndex = 0;
    while (sortedIndex < n) {
        // 将 nums[sortedIndex] 插入到有序数组 [0, sortedIndex) 中
        for (int i = sortedIndex; i > 0; i--) {
            if (nums[i] < nums[i - 1]) {
                // swap(nums[i], nums[i - 1])
                int tmp = nums[i];
                nums[i] = nums[i - 1];
                nums[i - 1] = tmp;
            } else {
                break;
            }
        }
        sortedIndex++;
    }
}
```



#### 希尔排序

> 分层排序，不稳定

```java
// 希尔排序，对 h 有序数组进行插入排序
// 逐渐缩小 h，最后 h=1 时，完成整个数组的排序
void sort(int[] nums) {
    int n = nums.length;
    // 我们使用的生成函数是 2^(k-1)
    // 即 h = 1, 2, 4, 8, 16...
    int h = 1;
    while (h < n / 2) {
        h = 2 * h;
    }

    // 改动一，把归并排序的主要逻辑套在 h 的 while 循环中
    while (h >= 1) {
        // 改动二，sortedIndex 初始化为 h，而不是 1
        int sortedIndex = h;
        while (sortedIndex < n) {
            // 改动三，把比较和交换元素的步长设置为 h，而不是相邻元素
            for (int i = sortedIndex; i >= h; i -= h) {
                if (nums[i] < nums[i - h]) {
                    // swap(nums[i], nums[i - h])
                    int tmp = nums[i];
                    nums[i] = nums[i - h];
                    nums[i - h] = tmp;
                } else {
                    break;
                }
            }
            sortedIndex++;
        }

        // 按照递增函数的规则，缩小 h
        h /= 2;
    }
}
```



#### 快排

> 先将一个元素排好序，然后再将剩下的元素排好序。寻找一个切分元素pivot，把比它小的放在左侧，比它大的放在右侧，然后进行递归。时间复杂度O(NlogN)，空间复杂度O(logN)（不稳定排序）

```java
class Quick {

    public static void sort(int[] nums) {
        // 为了避免出现耗时的极端情况，先随机打乱
        shuffle(nums);
        // 排序整个数组（原地修改）
        sort(nums, 0, nums.length - 1);
    }

    private static void sort(int[] nums, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        // 对 nums[lo..hi] 进行切分
        // 使得 nums[lo..p-1] <= nums[p] < nums[p+1..hi]
        int p = partition(nums, lo, hi);

        sort(nums, lo, p - 1);
        sort(nums, p + 1, hi);
    }

    // 对 nums[lo..hi] 进行切分
    private static int partition(int[] nums, int lo, int hi) {
        int pivot = nums[hi];
        int i, j;
        i = j = lo;
        //双指针，i用来存储小于pivot的索引，j只用来遍历，当遍历到比pivot小的元素，就放到i的位置
        while(j < hi){
            if(nums[j] < pivot){
                swap(nums, i, j);
                i++;
            }
            j++;
		}
        //此时i-1存储的都是小于pivot的元素，只需要交换i和hi的位置即可
        swap(nums, i, hi);
    	return i;
    }
    
    //partition函数的另一种写法如下：
    private static int partition(int[] nums, int lo, int hi) {
        int pivot = nums[lo];
        // 关于区间的边界控制需格外小心，稍有不慎就会出错
        // 我这里把 i, j 定义为开区间，同时定义：
        // [lo, i) <= pivot；(j, hi] > pivot
        // 之后都要正确维护这个边界区间的定义
        int i = lo + 1, j = hi;
        // 当 i > j 时结束循环，以保证区间 [lo, hi] 都被覆盖
        while (i <= j) {
            while (i < hi && nums[i] <= pivot) {
                i++;
                // 此 while 结束时恰好 nums[i] > pivot
            }
            while (j > lo && nums[j] > pivot) {
                j--;
                // 此 while 结束时恰好 nums[j] <= pivot
            }

            if (i >= j) {
                break;
            }
            // 此时 [lo, i) <= pivot && (j, hi] > pivot
            // 交换 nums[j] 和 nums[i]
            swap(nums, i, j);
            // 此时 [lo, i] <= pivot && [j, hi] > pivot
        }
        // 最后将 pivot 放到合适的位置，即 pivot 左边元素较小，右边元素较大
        swap(nums, lo, j);
        return j;
    }

    // 洗牌算法，将输入的数组随机打乱
    private static void shuffle(int[] nums) {
        Random rand = new Random();
        int n = nums.length;
        for (int i = 0 ; i < n; i++) {
            // 生成 [i, n - 1] 的随机数
            int r = i + rand.nextInt(n - i);
            swap(nums, i, r);
        }
    }
    
    // 原地交换数组中的两个元素
    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
```

##### 变体：快速选择算法

```java
//寻找第k大元素
class Solution {
    public int findKthLargest(int[] nums, int k) {
        // 首先随机打乱数组
        shuffle(nums);
        int lo = 0, hi = nums.length - 1;
        // 转化成「排名第 k 的元素」
        k = nums.length - k;
        while (lo <= hi) {
            // 在 nums[lo..hi] 中选一个切分点
            int p = partition(nums, lo, hi);
            if (p < k) {
                // 第 k 大的元素在 nums[p+1..hi] 中
                lo = p + 1;
            } else if (p > k) {
                // 第 k 大的元素在 nums[lo..p-1] 中
                hi = p - 1;
            } else {
                // 找到第 k 大元素
                return nums[p];
            }
        }
        return -1;
    }

    // 对 nums[lo..hi] 进行切分
    int partition(int[] nums, int lo, int hi) {
        // 见前文
    }

    // 洗牌算法，将输入的数组随机打乱
    void shuffle(int[] nums) {
        // 见前文
    }
}
```



#### 归并排序

> 把数组切成两半，先把这两半子数组分别排好序，然后再合并这两个有序数组，整个数组就排好序了。

```java
class Merge {

    // 用于辅助合并有序数组
    private static int[] temp;

    public static void sort(int[] nums) {
        // 先给辅助数组开辟内存空间
        temp = new int[nums.length];
        // 排序整个数组（原地修改）
        sort(nums, 0, nums.length - 1);
    }

    // 定义：将子数组 nums[lo..hi] 进行排序
    private static void sort(int[] nums, int lo, int hi) {
        if (lo == hi) {
            // 单个元素不用排序
            return;
        }
        // 这样写是为了防止溢出，效果等同于 (hi + lo) / 2
        int mid = lo + (hi - lo) / 2;
        // 先对左半部分数组 nums[lo..mid] 排序
        sort(nums, lo, mid);
        // 再对右半部分数组 nums[mid+1..hi] 排序
        sort(nums, mid + 1, hi);
        // 将两部分有序数组合并成一个有序数组
        merge(nums, lo, mid, hi);
    }

    // 将 nums[lo..mid] 和 nums[mid+1..hi] 这两个有序数组合并成一个有序数组
    private static void merge(int[] nums, int lo, int mid, int hi) {
        // 先把 nums[lo..hi] 复制到辅助数组中
        // 以便合并后的结果能够直接存入 nums
        for (int i = lo; i <= hi; i++) {
            temp[i] = nums[i];
        }

        // 数组双指针技巧，合并两个有序数组
        int i = lo, j = mid + 1;
        for (int p = lo; p <= hi; p++) {
            if (i == mid + 1) {
                // 左半边数组已全部被合并
                nums[p] = temp[j++];
            } else if (j == hi + 1) {
                // 右半边数组已全部被合并
                nums[p] = temp[i++];
            } else if (temp[i] > temp[j]) {
                nums[p] = temp[j++];
            } else {
                nums[p] = temp[i++];
            }
        }
    }
}
```



#### 堆排序



### 二分搜索法

```java
//把搜索区间全部统一为左右闭合
int binary_search(int[] nums, int target) {
    //这里right指最大索引，所以也是包含的
    int left = 0, right = nums.length - 1; 
    //左右闭合区间决定了循环条件式left<=right
    while(left <= right) {
        //这里的写法是防止溢出
        int mid = left + (right - left) / 2;
        if (nums[mid] < target) {
            left = mid + 1;
        } else if (nums[mid] > target) {
            right = mid - 1; 
        } else if(nums[mid] == target) {
            // 直接返回
            return mid;
        }
    }
    // 直接返回
    return -1;
}

int left_bound(int[] nums, int target) {
    int left = 0, right = nums.length - 1;
    while (left <= right) {
        int mid = left + (right - left) / 2;
        if (nums[mid] < target) {
            left = mid + 1;
        } else if (nums[mid] > target) {
            right = mid - 1;
        } else if (nums[mid] == target) {
            // 别返回，锁定左侧边界
            right = mid - 1;
        }
    }
    // 判断 target 是否存在于 nums 中
    if (left < 0 || left >= nums.length) {
        return -1;
    }
    // 判断一下 nums[left] 是不是 target
    return nums[left] == target ? left : -1;
}

int right_bound(int[] nums, int target) {
    int left = 0, right = nums.length - 1;
    while (left <= right) {
        int mid = left + (right - left) / 2;
        if (nums[mid] < target) {
            left = mid + 1;
        } else if (nums[mid] > target) {
            right = mid - 1;
        } else if (nums[mid] == target) {
            // 别返回，锁定右侧边界
            left = mid + 1;
        }
    }
    // 由于 while 的结束条件是 right == left - 1，且现在在求右边界
    // 所以用 right 替代 left - 1 更好记
    if (right < 0 || right >= nums.length) {
        return -1;
    }
    return nums[right] == target ? right : -1;
}
```



### 图论（Graph)

```java
// 图节点的逻辑结构
class Vertex {
    int id;
    Vertex[] neighbors;
}

// 邻接表
// graph[x] 存储 x 的所有邻居节点
List<Integer>[] graph;
//有向加权
// graph[x] 存储 x 的所有邻居节点以及对应的权重
class Edge {
    int to;
    int weight;
}
List<Edge>[] graph;


// 邻接矩阵
// matrix[x][y] 记录 x 是否有一条指向 y 的边
boolean[][] matrix;
//有向加权
// matrix[x][y] 记录 x 指向 y 的边的权重，0 表示不相邻
int[][] matrix;
```



#### 基本概念

> 该部分内容引用自网页[图论算法—图(Graph)的入门概念、存储结构、遍历方式以及Java代码的实现](https://juejin.cn/post/7056659970301329444#heading-11)

- 通常，图表示为G(V, E)，V是定点的集合，G是边的集合

- 无向边（Edge）用无序偶对（vi, vj）表示，有向边（弧）（Arc）

- **子图**：若有两个图G=(V1,E1)， G2=(V2,E2)， 满足如下条件：V2⊆V1 ，E2⊆ E1，即V2为V1的子集，E2为E1的子集，则 称图G2为图G1的子图。

- **邻接点、度**：相邻且有边直接连接的两个顶点称为邻接点。顶点所连边的数目称为度，在有向图中，由于边有方向，则顶点的度分为入度和出度。

- **简单路径、连通图**：图中顶点间存在路径，两顶点存在路径则说明是连通的，如果路径最终回到起始点则称为环，当中不重复叫简单路径。若任意两顶点都是连通的，则图就是连通图，有向则称强连通图。图中有子图，若子图极大连通则就是连通分量，有向的则称强连通分量。

- **生成树**：无向图中连通且n个顶点n-1条边叫生成树。有向图中一顶点入度为0其余顶点入度为1的叫有向树。一个有向图由若干棵有向树构成生成森林。

  

#### 存储结构

##### 邻接矩阵

- 采用**两个数组**来存储图，一个一维数组存储图顶点信息，一个二维数组存储图边或弧的信息，二维数组可以看作矩阵（简单，但可能造成空间浪费）

```java
import java.util.ArrayList;
import java.util.List;

// 加权有向图的通用实现（邻接矩阵）
public class WeightedDigraph {
    // 存储相邻节点及边的权重
    public static class Edge {
        int to;
        int weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }


    // 邻接矩阵，matrix[from][to] 存储从节点 from 到节点 to 的边的权重
    // 0 表示没有连接
    private int[][] matrix;

    public WeightedDigraph(int n) {
        matrix = new int[n][n];
    }

    // 增，添加一条带权重的有向边，复杂度 O(1)
    public void addEdge(int from, int to, int weight) {
        matrix[from][to] = weight;
    }

    // 删，删除一条有向边，复杂度 O(1)
    public void removeEdge(int from, int to) {
        matrix[from][to] = 0;
    }

    // 查，判断两个节点是否相邻，复杂度 O(1)
    public boolean hasEdge(int from, int to) {
        return matrix[from][to] != 0;
    }

    // 查，返回一条边的权重，复杂度 O(1)
    public int weight(int from, int to) {
        return matrix[from][to];
    }

    // 查，返回某个节点的所有邻居节点，复杂度 O(V)
    public List<Edge> neighbors(int v) {
        List<Edge> res = new ArrayList<>();
        for (int i = 0; i < matrix[v].length; i++) {
            if (matrix[v][i] > 0) {
                res.add(new Edge(i, matrix[v][i]));
            }
        }
        return res;
    }
}
```







##### 邻接表（稀疏图常用）

- 没有空间浪费

```java
// 加权有向图的通用实现（邻接表）
class WeightedDigraph {
    // 存储相邻节点及边的权重
    public static class Edge {
        int to;
        int weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    // 邻接表，graph[v] 存储节点 v 的所有邻居节点及对应权重
    private List<Edge>[] graph;

    public WeightedDigraph(int n) {
        // 我们这里简单起见，建图时要传入节点总数，这其实可以优化
        // 比如把 graph 设置为 Map<Integer, List<Edge>>，就可以动态添加新节点了
        graph = new List[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
    }

    // 增，添加一条带权重的有向边，复杂度 O(1)
    public void addEdge(int from, int to, int weight) {
        graph[from].add(new Edge(to, weight));
    }

    // 删，删除一条有向边，复杂度 O(V)
    public void removeEdge(int from, int to) {
        for (int i = 0; i < graph[from].size(); i++) {
            if (graph[from].get(i).to == to) {
                graph[from].remove(i);
                break;
            }
        }
    }

    // 查，判断两个节点是否相邻，复杂度 O(V)
    public boolean hasEdge(int from, int to) {
        for (Edge e : graph[from]) {
            if (e.to == to) {
                return true;
            }
        }
        return false;
    }

    // 查，返回一条边的权重，复杂度 O(V)
    public int weight(int from, int to) {
        for (Edge e : graph[from]) {
            if (e.to == to) {
                return e.weight;
            }
        }
        throw new IllegalArgumentException("No such edge");
    }

    // 上面的 hasEdge、removeEdge、weight 方法遍历 List 的行为是可以优化的
    // 比如用 Map<Integer, Map<Integer, Integer>> 存储邻接表
    // 这样就可以避免遍历 List，复杂度就能降到 O(1)

    // 查，返回某个节点的所有邻居节点，复杂度 O(1)
    public List<Edge> neighbors(int v) {
        return graph[v];
    }
}

```



#### 图的遍历

##### 深度优先遍历（DFS）：类似于Tree的前序遍历——一般可以用递归求所有路径

```java
// 遍历图的所有节点
void traverse(Graph graph, int s, boolean[] visited) {
    // base case
    if (s < 0 || s >= graph.size()) {
        return;
    }
    if (visited[s]) {
        // 防止死循环
        return;
    }
    // 前序位置
    visited[s] = true;
    for (Edge e : graph.neighbors(s)) {
        traverse(graph, e.to, visited);
    }
    // 后序位置
}


// 遍历图的所有路径
boolean[] onPath = new boolean[graph.size()];
List<Integer> path = new LinkedList<>();
void traverse(Graph graph, int src, int dest) {
    // base case
    if (src < 0 || src >= graph.size()) {
        return;
    }
    if (onPath[src]) {
        // 防止死循环（成环）
        return;
    }
    // 前序位置
    onPath[src] = true;
    path.add(src);
    if (src == dest) {
        return;
    }
    for (Edge e : graph.neighbors(src)) {
        traverse(graph, e.to, dest);
    }
    // 后序位置
    path.remove(path.size() - 1);
    onPath[src] = false;
}

//如果明确得知Graph中不存在环，则不需要visited和onPath来记录
```



##### 广度优先遍历（BFS）：类似于Tree的层序遍历——一般用来求最短路径

```java
//写法1：不记录step
// 图结构的 BFS 遍历，从节点 s 开始进行 BFS，借助队列
void bfs(Graph graph, int s) {
    boolean[] visited = new boolean[graph.size()];
    Queue<Integer> q = new LinkedList<>();
    q.offer(s);
    visited[s] = true;

    while (!q.isEmpty()) {
        int cur = q.poll();
        System.out.println("visit " + cur);
        for (Edge e : graph.neighbors(cur)) {
            if (!visited[e.to]) {
                q.offer(e.to);
                visited[e.to] = true;
            }
        }
    }
}

//写法2：BFS 遍历图的所有节点，从 s 开始进行 BFS，且记录遍历的步数
void bfs(Graph graph, int s) {
    boolean[] visited = new boolean[graph.size()];
    Queue<Integer> q = new LinkedList<>();
    q.offer(s);
    visited[s] = true;
    // 记录遍历的步数
    int step = 0;
    while (!q.isEmpty()) {
        int sz = q.size();
        for (int i = 0; i < sz; i++) {
            int cur = q.poll();
            System.out.println("visit " + cur + " at step " + step);
            for (Edge e : graph.neighbors(cur)) {
                if (!visited[e.to]) {
                    q.offer(e.to);
                    visited[e.to] = true;
                }
            }
        }
        step++;
    }
}


//写法3：图结构的 BFS 遍历，从节点 s 开始进行 BFS，且记录路径的权重和（暂时不掌握）
// 每个节点自行维护 State 类，记录从 s 走来的权重和
class State {
    // 当前节点 ID
    int node;
    // 从起点 s 到当前节点的权重和
    int weight;

    public State(int node, int weight) {
        this.node = node;
        this.weight = weight;
    }
}


void bfs(Graph graph, int s) {
    boolean[] visited = new boolean[graph.size()];
    Queue<State> q = new LinkedList<>();

    q.offer(new State(s, 0));
    visited[s] = true;

    while (!q.isEmpty()) {
        State state = q.poll();
        int cur = state.node;
        int weight = state.weight;
        System.out.println("visit " + cur + " with path weight " + weight);
        for (Edge e : graph.neighbors(cur)) {
            if (!visited[e.to]) {
                q.offer(new State(e.to, weight + e.weight));
                visited[e.to] = true;
            }
        }
    }
}
```



#### Union-Find并查集算法（图的动态连通性算法）

- 自反性：节点 `p` 和 `p` 是连通的。
- 对称性：如果节点 `p` 和 `q` 连通，那么 `q` 和 `p` 也连通。
- 传递性：如果节点 `p` 和 `q` 连通，`q` 和 `r` 连通，那么 `p` 和 `r` 也连通（等价关系，分门别类）。

```java
class UF {
    // 记录连通分量
    private int count;
    // 节点 x 的父节点是 parent[x]
    private int[] parent;
    // 新增一个数组记录树的“重量”
    private int[] size;

    // 构造函数，n 为图的节点总数
    public UF(int n) {
        // 一开始互不连通
        this.count = n;
        // 父节点指针初始指向自己
        parent = new int[n];
        // 最初每棵树只有一个节点
        // 重量应该初始化 1
        size = new int[n];
        for (int i = 0; i < n; i++)
            parent[i] = i;
        	size[i] = 1
    	}

    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ)
            return;
        // 将两棵树合并为一棵，且尽量保证小树在大树下边，较为平衡
       if (size[rootP] > size[rootQ]) {
            parent[rootQ] = rootP;
            size[rootP] += size[rootQ];
        } else {
            parent[rootP] = rootQ;
            size[rootQ] += size[rootP];
        }
        // 两个分量合二为一
        count--;
    }

    // 返回某个节点 x 的根节点
    private int find(int x) {
        // 根节点的 parent[x] == x
        while (parent[x] != x)
            //以下递归函数可以实现路径压缩(直接压平)，使Tree的高度变低，操作的时间复杂度缩减
            //如果有这部操作，union方法中的size数组优化可以省略
            parent[x] = find(parent[x]);
            /*
            如果不用递归，可以使用如下代码：
                parent[x] = parent[parent[x]];
                x = parent[x]
            实现逐层压缩
            */
        return x;
    }

    // 返回当前的连通分量个数
    public int count() { 
        return count;
    }
    
    public boolean connected(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        return rootP == rootQ;
    }
}
```

> 小结：
>
> 1、用 `parent` 数组记录每个节点的父节点，相当于指向父节点的指针，所以 `parent` 数组内实际存储着一个森林（若干棵多叉树）。
>
> 2、用 `size` 数组记录着每棵树的重量，目的是让 `union` 后树依然拥有平衡性，保证各个 API 时间复杂度为 O(logN)，而不会退化成链表影响操作效率。
>
> 3、在 `find` 函数中进行路径压缩，保证任意树的高度保持在常数，使得各个 API 时间复杂度为 O(1)。使用了路径压缩之后，可以不使用 `size` 数组的平衡优化。



### Binary Tree

#### 二叉树的顺序存储结构

一维**数组**存储节点，存储位置采用完全二叉树的节点层次编号，如果对应的二叉树节点不存在，设置为空节点

**适用类型**：完全二叉树（满二叉树），可以充分利用存储空间

#### 二叉树的链式存储结构(二叉链表)

每个链节点包含一个用于数据域 **val**，存储节点信息；还包含两个指针域**left** 和**right**，分别指向左右两个子节点，当左或右子节点不存在时，相应指针域值为空

**适用类型**：灵活、方便，更节省空间，是二叉树常用的存储结构

```java
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { this.val = x; }
}

// 你可以这样构建一棵二叉树：
TreeNode root = new TreeNode(1);
root.left = new TreeNode(2);
root.right = new TreeNode(3);
root.left.left = new TreeNode(4);
root.right.left = new TreeNode(5);
root.right.right = new TreeNode(6);

// 构建出来的二叉树是这样的：
//     1
//    / \
//   2   3
//  /   / \
// 4   5   6
```

[二叉树原理](https://fhfirehuo.github.io/Attacking-Java-Rookie/Chapter02/BinaryTree.html)



#### Binary Search Tree(BST)

对于树中的每个节点，其**左子树的每个节点**的值都要小于这个节点的值，**右子树的每个节点**的值都要大于这个节点的值（左小右大）

*二叉树解题是不是要先构建BST存储结构再进行搜索？*



#### Binary Tree的遍历

##### 递归遍历（DFS）：依赖函数堆栈

***BST的中序遍历结果是有序的***

```java
// 遍历框架
void traverse(TreeNode root) {
    if (root == null) {
        return;
    }
    // 前序位置
    traverse(root.left);
    // 中序位置
    traverse(root.right);
    // 后序位置
}

//     1
//    / \
//   2   3
//  /   / \
// 4   5   6
//以上二叉树的遍历顺序：1-2-4-2-1-3-5-3-6
/*
前序遍历：根节点—左节点—右节点
中序遍历：左节点—根节点—右节点
后续遍历：左节点—右节点-根节点
*/

```



##### 层序遍历（BFS）：依赖队列

```java
//第一种写法：无法得知cur的深度
void levelOrderTraverse(TreeNode root) {
    if (root == null) {
        return;
    }
    Queue<TreeNode> q = new LinkedList<>();
    q.offer(root);
    while (!q.isEmpty()) {
        TreeNode cur = q.poll();
        // 访问 cur 节点
        System.out.println(cur.val);

        // 把 cur 的左右子节点加入队列
        if (cur.left != null) {
            q.offer(cur.left);
        }
        if (cur.right != null) {
            q.offer(cur.right);
        }
    }
}
//     1
//    / \
//   2   3
//  /   / \
// 4   5   6
//以上二叉树遍历得出的队列是：
/*
1(cur == 1)
2,3(cur == 2)
3,4(cur == 3)
4,5,6(cur == 4)
5,6(cur == 5)
6(cur == 6)
*/


//第二种写法，可以得知当前cur的深度(常用写法)
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
        while(sz > 0) {
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

//     1
//    / \
//   2   3
//  /   / \
// 4   5   6
//以上二叉树遍历得出的队列是：
/*
1(depth == 1)(sz = 1)
(cur == 1)
2,3(depth == 2)
(sz == 2)
(cur == 2)
3,4
(cur == 3)
4, 5, 6
(depth == 3)
(sz == 3)
(cur == 4)
(cur == 5)
(cur == 6)
*/

//第三种写法（有待后续理解）
class State {
    TreeNode node;
    int depth;

    State(TreeNode node, int depth) {
        this.node = node;
        this.depth = depth;
    }
}

void levelOrderTraverse(TreeNode root) {
    if (root == null) {
        return;
    }
    Queue<State> q = new LinkedList<>();
    // 根节点的路径权重和是 1
    q.offer(new State(root, 1));

    while (!q.isEmpty()) {
        State cur = q.poll();
        // 访问 cur 节点，同时知道它的路径权重和
        System.out.println("depth = " + cur.depth + ", val = " + cur.node.val);

        // 把 cur 的左右子节点加入队列
        if (cur.node.left != null) {
            q.offer(new State(cur.node.left, cur.depth + 1));
        }
        if (cur.node.right != null) {
            q.offer(new State(cur.node.right, cur.depth + 1));
        }
    }
}

```



### 回溯算法/DFS

站在回溯树的一个节点上，思考三个问题：

- 路径：已经做出的选择

- 选择列表：当前可以做的选择

- 结束条件：到达决策树底层，无法再做选择的条件

  ```java
  //回溯算法框架如下
  result = []
  def backtrack(路径, 选择列表):
       if 满⾜结束条件:
       result.add(路径)
       return
  
       for 选择 in 选择列表:
       做选择
       backtrack(路径, 选择列表)
       撤销选择
  ```

  ```java
  //全排列问题解法
  class Solution {
       List<List<Integer>> res = new LinkedList<>();
       // 主函数，输⼊⼀组不重复的数字，返回它们的全排列
       List<List<Integer>> permute(int[] nums) {
           // 记录「路径」
           LinkedList<Integer> track = new LinkedList<>();
           // 「路径」中的元素会被标记为 true，避免重复使⽤
           boolean[] used = new boolean[nums.length];
  
           backtrack(nums, track, used);
           return res;
       }
       // 路径：记录在 track 中
       // 选择列表：nums 中不存在于 track 的那些元素（used[i] 为 false）
       // 结束条件：nums 中的元素全都在 track 中出现
       void backtrack(int[] nums, LinkedList<Integer> track, boolean[] used){
           // 触发结束条件
           if (track.size() == nums.length) {
               res.add(new LinkedList(track));
               return;
       	}
  
           for (int i = 0; i < nums.length; i++) {
               // 排除不合法的选择
               if (used[i]) {
                   // nums[i] 已经在 track 中，跳过
                   continue;
               }
               // 做选择
               track.add(nums[i]);
               used[i] = true;
               // 进⼊下⼀层决策树
               backtrack(nums, track, used);
               // 取消选择
               track.removeLast();
               used[i] = false;
          }
       }
  }
  ```

  

### 动态规划（暴力穷举求最值）

- 动态规划三要素：重叠子问题、最优子结构、状态转移方程

- 明确「状态」-> 明确「选择」 -> 定义 `dp` 数组/函数的含义

  > **1、确定「状态」，也就是原问题和子问题中会变化的变量**。
  >
  > **2、确定「选择」，也就是导致「状态」产生变化的行为**。
  >
  > **3、明确 `dp` 函数/数组的定义**，自顶向下的解法一般会有一个递归的 `dp` 函数，一般来说函数的参数就是状态转移中会变化的量，也就是上面说到的「状态」；函数的返回值就是题目要求我们计算的量。

```python
#一般框架
# 自顶向下递归的动态规划
def dp(状态1, 状态2, ...):
    for 选择 in 所有可能的选择:
        # 此时的状态已经因为做了选择而改变
        result = 求最值(result, dp(状态1, 状态2, ...))
    return result

# 自底向上迭代的动态规划
# 初始化 base case
dp[0][0][...] = base case
# 进行状态转移
for 状态1 in 状态1的所有取值：
    for 状态2 in 状态2的所有取值：
        for ...
            dp[状态1][状态2][...] = 求最值(选择1，选择2...)
```



> “剪枝”重叠子问题的两种方法：备忘录memo[]（自顶向下）或dp[] （自下而上）

```java
//以斐波那契数列为例
//备忘录（递归）解法
int fib(int N) {
    // 备忘录全初始化为 0
    int[] memo = new int[N + 1];
    // 进行带备忘录的递归
    return dp(memo, N);
}

// 带着备忘录进行递归
int dp(int[] memo, int n) {
    // base case
    if (n == 0 || n == 1) return n;
    // 已经计算过，不用再计算了
    if (memo[n] != 0) return memo[n];
    memo[n] = dp(memo, n - 1) + dp(memo, n - 2);
    return memo[n];
}

//dp Table迭代（递推）解法
int fib(int N) {
    if (N == 0) return 0;
    int[] dp = new int[N + 1];
    // base case
    dp[0] = 0; dp[1] = 1;
    // 状态转移
    for (int i = 2; i <= N; i++) {
        dp[i] = dp[i - 1] + dp[i - 2];
    }

    return dp[N];
}

//dp Table解法更新（降低空间复杂度）
int fib(int n) {
    if (n == 0 || n == 1) {
        // base case
        return n;
    }
    // 分别代表 dp[i - 1] 和 dp[i - 2]
    int dp_i_1 = 1, dp_i_2 = 0;
    for (int i = 2; i <= n; i++) {
        // dp[i] = dp[i - 1] + dp[i - 2];
        int dp_i = dp_i_1 + dp_i_2;
        // 滚动更新
        dp_i_2 = dp_i_1;
        dp_i_1 = dp_i;
    }
    return dp_i_1;
}
```

```java
//凑零钱问题
//dp数组递推解法
class Solution {
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        //dp中存储的是凑够amount金额需要的硬币数量
        //初始化数组为一个不会取到的值
        Arrays.fill(memo, amount + 1);
        //base case
        dp[0] = 0;

        //外层for循环遍历存储所有可能amount
        for(int i = 0; i < dp.length; i++){
            for(int coin : coins){
                if(i - coin < 0) continue;
                //dp[i]一直在进行最小值的更新
                dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
            }
        }
        
        //如果dp[i]保持原始值没有更新过，说明没有可行解，返回-1
        return dp[amount] == amount + 1? -1 : dp[amount];
    }
}


//备忘录递归解法
class Solution {
    int[] memo;

    public int coinChange(int[] coins, int amount) {
        memo = new int[amount + 1];
        // 备忘录初始化为一个不会被取到的特殊值，代表还未被计算
        Arrays.fill(memo, -666);
        return dp(coins, amount);
    }

    int dp(int[] coins, int amount) {
        if (amount == 0) return 0;
        if (amount < 0) return -1;
        // 查备忘录，防止重复计算
        if (memo[amount] != -666)
            return memo[amount];

        int res = Integer.MAX_VALUE;
        for (int coin : coins) {
            // 计算子问题的结果
            int subProblem = dp(coins, amount - coin);
            // 子问题无解则跳过
            if (subProblem == -1) continue;
            // 在子问题中选择最优解，然后加一
            res = Math.min(res, subProblem + 1);
        }
        // 把计算结果存入备忘录
        memo[amount] = (res == Integer.MAX_VALUE) ? -1 : res;
        return memo[amount];
    }
}
```

