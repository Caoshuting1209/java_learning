### 链表

#### LRU（Least recently used）缓存问题

- 用双链表+哈希Map结合实现

```java
class Node{
  public int key, val;
  public Node next, pre;
  public Node(int key, int val){
    	this.key = key;
    	this.val = val;
  }
}

class DoubleList{
  private Node head, tail;
  private int size;
  public DoubleList(){
      head = new Node(0, 0);
      tail = new Node(0, 0);
      head.next = tail;
      tail.pre = head;
      size = 0;
  }
  public void addLast(Node x){
    //先找到新插入节点的定位
      x.pre = tail.pre;
      x.next = tail;
    //去除旧的节点
      tail.pre.next = x;
      tail.pre = x;
      size++;
  }
  public void remove(Node x){
      x.pre.next = x.next;
      x.next.pre = x.pre;
      size--;
  }
  public Node removeFirst(){
      if(head.next == tail){
        	return null;
      }
      Node first = head.next;
      remove(first);
      return first;
  }
  public int size(){
      return size;
  }
}

public LRUCache{
  private HashMap<Integer, Node> map;
  private DoubleList cache;
  private int cap;
  public LRUCache(int capacity){
    	this.cap = capacity;
      map = new HashMap<>();
    	cache = new DoubleList();
	}
  //以下主要实现HashMap相关代码
  private void makeRecently(int key){
      Node x = map.get(key);
      cache.remove(x);
      cache.addLast(x);
  }
  private void addRecently(int key, int val){
      Node x = new Node(key, val);
      cache.addLast(x);
      map.put(key, x);
  }
  private void deleteKey(int key){
      Node x = map.get(key);
      cache.remove(x);
      map.remove(key);
	}
  private void removeLRU(){
      Node node == cache.removeFirst();
      map.remove(node.key);
  }
  public int get(int key){
    if(!map.containsKey(key)){
      return -1;
    }
    makeRecently(key);
    return map.get(key).val;
  }
  
  public void put(int key, int value){
    	if(!map.containsKey(key)){
          if(cache.size() == cap){
            	removeLRU();
          }
           addRecently(key, value);
        	return;
      }else{
        	deleteKey(key);
        	addRecently(key, value);
        	return;
      }
  }
}
```

- 用java内置的LinkedHashMap类实现

```java
class LRUCache{
  private int cap;
  private LinkedHashMap<Integer, Integer> cache;
  public LRUCache(int capacity){
      this.cap = capacity;
      cache = new LinkedHashMap<>();
  }

  public int get(int key){
    if(!cache.containsKey(key)){
      return -1;
    }
    makeRecently(key);
    return cache.get(key);
  }
  
  public void put(int key, int val){
    	if(!cache.containsKey(key)){
          if(cache.size() == cap){
            //该方法返回链中的第一个key
            	int oldestKey = cache.keySet().iterator().next();
            	cache.remove(oldestKey);
          }
        //默认在链尾插入节点
           cache.put(key, val);
        		return;
      }else{
        //调用put方法会在原节点覆盖val值
        	cache.put(key, val);
        //覆盖后把节点移动到链尾
    			makeRecently(key);
        	return;
      }
  }
  
  private void makeRecently(int key) {
     int val = cache.get(key);
     cache.remove(key);
     cache.put(key, val);
 }
}
```



#### LFU（Least frequently used）缓存问题

```java
class LFUCache{
  HashMap<Integer, Integer> keyToVal;
  HashMap<Integer, Integer> keyToFreq;
  HashMap<Integer, LinkedHashSet<Integer>> freqToKeys;
  int minFreq;
  int cap;
  public LFUCache(int capacity){
      keyToVal = new HashMap<>();
      keyToFreq = new HashMap<>();
      freqToKeys = new HashMap<>();
      this.cap = capacity;
      this.minFreq = 0; 
  }
  public int get(int key){
      if(!keyToVal.containsKey(key)){
          return -1;
      }
      increaseFreq(key);
      return keyToVal.get(key);
	}
  public void put(int key, int val){
    	if(keyToVal.containsKey(key)){
        	keyToVal.put(key, val);
        	increaseFreq(key);
        	return;
      }
    	if(keyToVal.size() == cap){
        removeMinFreq();
      }
    	keyToVal.put(key, val);
    	keyToFreq.put(key, 1);
    	freqToKeys.putIfAbsent(1, new LinkedHashSet<>());
    	freqToKeys.get(1).add(key);
    //当有新的key插入时，肯定新插入的key访问次数是最小的，即为1
    	minFreq = 1;
  }
  private void increaseFreq(key){
    	int oldFreq = keyToFreq.get(key);
    	int currentFreq = oldFreq + 1;
    	keyToFreq.put(key, currentFreq);
    //如果新的frep不存在，要新建一个key-value对
    	freqToKeys.putIfAbsent(currentFreq, new LinkedHashSet<>());
    	freqToKeys.get(currentFreq).add(key);
    //旧的frep表中去除这个key
    	freqToKeys.get(oldFreq).remove(key);
    	if(freqToKeys.get(oldFreq).isEmpty()){
        	freqToKeys.remove(oldFreq);
        	if(oldFreq == minFreq){
           //如果原先的oldFreq是min，但现在这个key列表空了，那么现在的min就变成了oldFreq+1
            	minFreq = currentFreq;
          }
      }
}
  private void removeMinFreq(){
      LinkedHashSet<Integer> list = freqToKeys.get(minFreq);
    //list中的第一个元素就是最早插入的
      int deleteKey = list.iterator().next();
      list.remove(deleteKey);
      if(list.isEmpty()){
        	freqToKeys.remove(minFreq);
        //只有在插入新key时会调用该方法，而插入新key时minFreq的值会变成1，所以这里不需要更新minFreq
      }
      keyToVal.remove(deleteKey);
      keyToFreq.remove(deleteKey);
  }
}
```



### 双指针问题

> 快慢指针：去重
>
> 左右指针：反转字符串

```java
//最长回文子串：左右指针是从中间向两端扩展

// 在 s 中寻找以 s[l] 和 s[r] 为中心的最长回文串
String palindrome(String s, int l, int r) {
    // 防止索引越界
    while (l >= 0 && r < s.length()
            && s.charAt(l) == s.charAt(r)) {
        // 双指针，向两边展开
        l--; r++;
    }
    // 返回以 s[l] 和 s[r] 为中心的最长回文串
    return s.substring(l + 1, r);
}
String longestPalindrome(String s) {
    String res = "";
    for (int i = 0; i < s.length(); i++) {
        // 以 s[i] 为中心的最长回文子串
        String s1 = palindrome(s, i, i);
        // 以 s[i] 和 s[i+1] 为中心的最长回文子串
        String s2 = palindrome(s, i, i + 1);
        // res = longest(res, s1, s2)
        res = res.length() > s1.length() ? res : s1;
        res = res.length() > s2.length() ? res : s2;
    }
    return res;
}

```



### 滑动窗口

```java
    // 滑动窗⼝算法伪码框架
    void slidingWindow(String s) {
     // ⽤合适的数据结构记录窗⼝中的数据，根据具体场景变通
     // ⽐如说，我想记录窗⼝中元素出现的次数，就⽤ map
     // 如果我想记录窗⼝中的元素和，就可以只⽤⼀个 int
     Object window = ...

     int left = 0, right = 0;
     while (right < s.length()) {
     // c 是将移⼊窗⼝的字符
     	char c = s[right];
     	window.add(c)
     // 增⼤窗⼝
     	right++;
     // 进⾏窗⼝内数据的⼀系列更新
     	...
     // *** debug 输出的位置 ***

     // 判断左侧窗⼝是否要收缩
     	while (left < right && window needs shrink) {
     // d 是将移出窗⼝的字符
     		char d = s[left];
     		window.remove(d)
     // 缩⼩窗⼝
     		left++;
     // 进⾏窗⼝内数据的⼀系列更新
             ...
     	}
     }
    }
```



```java
class Solution {
    public String minWindow(String s, String t) {
        Map<Character, Integer> Need = new HashMap<>();
        Map<Character, Integer> Cur = new HashMap<>();
        for(char c : t.toCharArray()){
            Need.put(c, Need.getOrDefault(c, 0) + 1);
        }
        //现在Need已经记录了t中所有字符的数量
        int start, left, right, len, valid;
        start = left = right = 0;
        valid = 0;
        len = Integer.MAX_VALUE;
        while(right < s.length()){
            char a = s.charAt(right);
            right++;
            if(Need.containsKey(a)){
                Cur.put(a, Cur.getOrDefault(a, 0) + 1);
                if(Need.get(a).equals(Cur.get(a))){
                    valid++;
                }
                    
            }

           
            while(valid == Need.size()){
                if(right - left < len){
                    start = left;
                    len = right - left; 
                }
                char b = s.charAt(left);
                left++;
                if(Need.containsKey(b)){
                    if(Need.get(b).equals(Cur.get(b))){
                        valid--;
                    }
                    Cur.put(b, Cur.get(b) - 1);
                }  
            }
        }
        return len == Integer.MAX_VALUE ? "" : s.substring(start, start + len);
    }
}


```

  	

### 单调栈

```java
//Next Greater Element 1
//栈的特性：peek栈顶，先进后出
class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Map<Integer, Integer> hash = new HashMap<>();
        Stack<Integer> nextGreater = new Stack<>();
        int[] result = new int[nums1.length];
        int n = nums2.length;
        for(int i = n - 1; i >= 0; i--){
            while(!nextGreater.isEmpty() && nextGreater.peek() <= nums2[i]){
                nextGreater.pop();
            }
            int res = nextGreater.isEmpty() ? -1 : nextGreater.peek();
            hash.put(nums2[i], res);
            nextGreater.push(nums2[i]);
        }
        for(int i = 0; i < nums1.length; i++){
            result[i] = hash.get(nums1[i]);
        }
        return result;
    }
}
```



### 单调队列

```java
//经典的用单调队列实现滑动窗口求最值问题
//Sliding Windows Maximu
class MonotonicQueue{
    //在尾部增加元素
    LinkedList<Integer> maxq = new LinkedList<>();
    //底层数据结构是双链表
    public void push(int n){
        while(!maxq.isEmpty() && n > maxq.getLast()){
            maxq.removeLast();
        }
        maxq.addLast(n);
    }

    //寻找最大元素
    public int max(){
        return maxq.getFirst();
    };

    //如果头部元素没有被push掉，移除头部元素
    public void poll(int n){
        if(n == maxq.getFirst()){
            maxq.removeFirst();
        }

    }

}

class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        MonotonicQueue window = new MonotonicQueue();
        ArrayList<Integer> res = new ArrayList<>();
        for(int i = 0; i < k - 1; i++){
            window.push(nums[i]);
        }
        int count = k - 1;
        
        while(count < nums.length){
            window.push(nums[count]);
            res.add(window.max());
            window.poll(nums[count - k + 1]);
            count++;
        }
        

        int[] result = new int[res.size()];
        for(int i = 0; i < res.size(); i++){
            result[i] = res.get(i);
        }
        return result;
    }
}
/*
Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
Output: [3,3,5,5,6,7]
*/

```



### 二叉树递归解法解题的思路

*⼆叉树中⽤【遍历思路】解题时函数签名⼀般是 void traverse(...)，没有返回值，靠更新外部变量来计算结果，而用【分解问题思路】解题时函数名根据该函数具体功能而定，⽽且⼀般会有返回值，返回值是⼦问题的计算结果。*

- 是否可以通过遍历⼀遍⼆叉树得到答案？如果可以，⽤⼀个 traverse 函数配合外部变量来实现。

- 是否可以定义⼀个递归函数，通过⼦问题（⼦树）的答案推导出原问题的答案？如果可以，写出这个递归

  函数的定义，并充分利⽤这个函数的返回值。

- ⽆论使⽤哪⼀种思维模式，你都要明⽩⼆叉树的每⼀个节点需要做什么，需要在什么时候（前中后序）

  做。

#### 遍历思路

```java
class Solution {
     // 记录最⼤深度
     int res = 0;
     // 记录遍历到的节点的深度
     int depth = 0;
     public int maxDepth(TreeNode root) {
         traverse(root);
         return res;
     }
     // ⼆叉树遍历框架
     void traverse(TreeNode root) {
     	if (root == null) {
            return;
     	}
     // 前序位置
     depth++;
     if (root.left == null && root.right == null) {
     // 到达叶⼦节点，更新最⼤深度
     	res = Math.max(res, depth);
     }
     traverse(root.left);
     traverse(root.right);
     // 后序位置
     depth--;
     }
    }
```



#### 分解问题思路

```java
class Solution {
// 定义：输⼊根节点，返回这棵⼆叉树的最⼤深度
 public int maxDepth(TreeNode root) {
     if (root == null) {
     	return 0;
 	}
 // 利⽤定义，计算左右⼦树的最⼤深度
 int leftMax = maxDepth(root.left);
 int rightMax = maxDepth(root.right);
 // 整棵树的最⼤深度等于左右⼦树的最⼤深度取最⼤值，
 // 然后再加上根节点⾃⼰
 int res = Math.max(leftMax, rightMax) + 1;
 return res;
 }
}
```



小tips

- *每⼀条⼆叉树的「直径」⻓度，就是⼀个节点的左右⼦树的最⼤深度之和。*

- *动归/DFS/回溯算法都可以看做⼆叉树问题的扩展，只是它们的关注点不同：*

  **动态规划算法**属于分解问题（分治）的思路，它的关注点在整棵「⼦树」。

  **回溯算法**属于遍历的思路，它的关注点在节点间的「树枝」。

  ```java
  // 回溯算法把「做选择」「撤销选择」的逻辑放在 for 循环⾥⾯
  void backtrack(Node root) {
   if (root == null) return;
   for (Node child : root.children) {
       // 做选择
       print("我站在节点 %s 到节点 %s 的树枝上", root, child);
       backtrack(child);
       // 撤销选择
       print("我将要离开节点 %s 到节点 %s 的树枝上", child, root);
   }
  }
  ```

  

  **DFS 算法**属于遍历的思路，它的关注点在单个「节点」。

```java
// DFS 算法把「做选择」「撤销选择」的逻辑放在 for 循环外⾯
void dfs(Node root) {
     if (root == null) return;
     // 做选择
     print("我已经进⼊节点 %s 啦", root);
     for (Node child : root.children) {
     	dfs(child);
     }
     // 撤销选择
     print("我将要离开节点 %s 啦", root);
}
```

### 二叉树的构造

*构造整棵树 = 根节点 + 构造左⼦树 + 构造右⼦树（**分解问题的思想**）*

```java
//给定前序遍历序列和中序遍历序列，构造二叉树
class Solution {
    Map<Integer, Integer> hash = new HashMap<>();
    public TreeNode buildTree(int[] preorder, int[] inorder) {  
        for(int i = 0; i < inorder.length; i++){
            hash.put(inorder[i], i);
        }
        return build(preorder, 0, preorder.length - 1,
                                inorder, 0, inorder.length - 1);
    }

    public TreeNode build(int[] preorder, int preStart, int preEnd,
                            int[] inorder, int inStart, int inEnd){
        if(preStart > preEnd){
            return null;
        }
        
        TreeNode root = new TreeNode(preorder[preStart]);
        //得到中序遍历中root的索引
        int inRoot = hash.get(preorder[preStart]);
        int leftSize = inRoot - inStart;
        root.left = build(preorder, preStart + 1 , preStart + leftSize,
                            inorder, inStart, inRoot - 1);
        root.right = build(preorder, preStart + leftSize + 1, preEnd,
                            inorder, inRoot + 1, inEnd);
        return root;
    }
}
```



> 可以还原二叉树的几种数组：
>
> - 如果数组不包含空指针，前序+中序/后序+中序数组可反序列化为唯一二叉树；中序+层序可反序列化为唯一二叉树
>
> - 如果数组包含空指针，前序/后序数组可反序列化为唯一二叉树
>
>   ```java
>   //以下代码展示了将二叉树序列化为带空指针的前序遍历String,再反序列化为原始二叉树的过程
>   /**
>    * Definition for a binary tree node.
>    * public class TreeNode {
>    *     int val;
>    *     TreeNode left;
>    *     TreeNode right;
>    *     TreeNode(int x) { val = x; }
>    * }
>    */
>   public class Codec {
>                                                             
>       // Encodes a tree to a single string.
>       public String serialize(TreeNode root) {
>           StringBuilder sb = new StringBuilder();
>           traverse(root, sb);
>           return sb.toString();
>       }
>       public void traverse(TreeNode root, StringBuilder sb){
>           if(root == null){
>               sb.append("#").append(",");
>               return;
>           }
>           sb.append(root.val).append(",");
>           traverse(root.left, sb);
>           traverse(root.right, sb);
>       }
>                                                                 
>       // Decodes your encoded data to tree.
>     public TreeNode deserialize(String data) {
>         	if(data.isEmpty()){
>              return null;
>           }
>           int[] rootIndex = new int[1];
>           rootIndex[0] = 0;
>           return des(data, rootIndex);
>     }
>                                                                 
>     public TreeNode des(String data, int[] rootIndex){
>           int nextPos = data.indexOf(",", rootIndex[0]);
>           if(nextPos == -1){
>               return null;
>           }
>           //tmp记录当前root所在的位置,substring左闭右开，所以tmp记录的只有root。记录为String类而不是int，是因为后续涉及到字符串到int的转换
>           String tmp = data.substring(rootIndex[0], nextPos);
>                                                             
>           //更新下一次查询位置
>           rootIndex[0] = nextPos + 1;
>                                                                     
>           if(tmp.charAt(0) == '#'){
>               return null;
>           }else{
>               TreeNode root = new TreeNode(Integer.parseInt(tmp));
>               //这里的转换需要tmp为String类型，否则无法处理负数
>               root.left = des(data, rootIndex);
>               root.right = des(data, rootIndex);
>               return root;
>           }
>       }
>    }
>                                                             
>     // Your Codec object will be instantiated and called as such:
>     // Codec ser = new Codec();
>     // Codec deser = new Codec();
>     // TreeNode ans = deser.deserialize(ser.serialize(root));
>       



### BST的一些特性

#### BST的合法性判断

```java
class Solution {
     public boolean isValidBST(TreeNode root) {
         return _isValidBST(root, null, null);
     }
     // 定义：该函数返回 root 为根的⼦树的所有节点是否满⾜ max.val > root.val > min.val
     public boolean _isValidBST(TreeNode root, TreeNode min, TreeNode max){
         // base case
         if (root == null) return true;
         // 若 root.val 不符合 max 和 min 的限制，说明不是合法 BST
         if (min != null && root.val <= min.val) return false;
         if (max != null && root.val >= max.val) return false;
         // 根据定义，限定左⼦树的最⼤值是 root.val，右⼦树的最⼩值是 root.val
         return _isValidBST(root.left, min, root) && _isValidBST(root.right, root, max);
 	}
}
```



#### BST增加节点

```java
class Solution {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        //该函数的定义是在root中找到一个合适的位置插入val，然后返回根节点
        if(root == null) return new TreeNode(val);
        if(root.val < val){
             root.right = insertIntoBST(root.right, val);
             //在root.right的合适位置插入val,然后返回根节点root.right
        }
        if(root.val > val){
            root.left = insertIntoBST(root.left, val);
        }
        return root;
    }
}
```



#### BST删除节点

```java
class Solution {
    public TreeNode deleteNode(TreeNode root, int key) {
        if(root == null) return null;
        if(root.val == key){
            if(root.left == null) return root.right;
            if(root.right == null) return root.left;
            //用右子树的最小节点代替原root
            TreeNode min = getMin(root.right);
            root.right = deleteNode(root.right, min.val);
            min.left = root.left;
            min.right = root.right;
            root = min;
        }else if(root.val < key){
            root.right = deleteNode(root.right, key);
        }else if(root.val > key){
            root.left = deleteNode(root.left, key);
        }
        return root;
    }

    public TreeNode getMin(TreeNode root){
        while(root.left != null){
            root = root.left;
        }
        return root;
    }
}
```



### DFS/回溯算法

#### 排列、组合、子集

- res记录最终的结果，track记录每条可能路径
- 对于排列来说，判断条件是track的size是否等于数组长度；对于子集来说，需要记录所有可能track
- 都用到for循环
- 排列问题引入boolean[]来判断每个元素是否在当前track中，如果在，则跳过，如果不在，就可以选择，backtrack函数仍然以i作为传入参数；子集问题中引入start参数来固定每个参数的相对位置，backtrack函数引用i+1作为传入，以避免使用前边的重复元素
- 排列问题含有重复元素的剪枝逻辑：nums[i] == nums[i - 1] && !used[i - 1]；子集问题含有重复元素的剪枝逻辑：nums[i] == nums[i - 1]

```java
//以下展示解题框架
//(1)无重复元素，不可复选

// 组合/⼦集问题回溯算法框架
void backtrack(int[] nums, int start) {
     // 回溯算法标准框架
     for (int i = start; i < nums.length; i++) {
         // 做选择
         track.addLast(nums[i]);
         // 注意参数
         backtrack(nums, i + 1);
         // 撤销选择
         track.removeLast();
     }
}

// 排列问题回溯算法框架
void backtrack(int[] nums) {
 	for (int i = 0; i < nums.length; i++) {
         // 剪枝逻辑
         if (used[i]) {
            continue;
         }
         // 做选择
         used[i] = true;
         track.addLast(nums[i]);
         backtrack(nums);
         // 撤销选择
         track.removeLast();
         used[i] = false;
    }
}

//(2)有重复，不可复选
    Arrays.sort(nums);
    // 组合/⼦集问题回溯算法框架
    void backtrack(int[] nums, int start) {
         // 回溯算法标准框架
         for (int i = start; i < nums.length; i++) {
             // 剪枝逻辑，跳过值相同的相邻树枝
             if (i > start && nums[i] == nums[i - 1]) {
             	continue;
         	}
             // 做选择
             track.addLast(nums[i]);
             // 注意参数
             backtrack(nums, i + 1);
             // 撤销选择
             track.removeLast();
        }
    }


    Arrays.sort(nums);
    // 排列问题回溯算法框架
    void backtrack(int[] nums) {
         for (int i = 0; i < nums.length; i++) {
             // 剪枝逻辑
             if (used[i]) {
             	continue;
             }
             // 剪枝逻辑，固定相同的元素在排列中的相对位置
             if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
             	continue;
             }
             // 做选择
             used[i] = true;
             track.addLast(nums[i]);
             backtrack(nums);
             // 撤销选择
             track.removeLast();
             used[i] = false;
         }
    }

//(3)无重复，可复选
    // 组合/⼦集问题回溯算法框架
    void backtrack(int[] nums, int start) {
         // 回溯算法标准框架
         for (int i = start; i < nums.length; i++) {
             // 做选择
             track.addLast(nums[i]);
             // 注意参数
             backtrack(nums, i);
             // 撤销选择
             track.removeLast();
         }
    }
```



##### 排列问题

```java
//全排列问题解法,无重复元素
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

```java
//有重复元素，不可复选
//排序，剪枝
class Solution {
    List<List<Integer>> res = new LinkedList<>();
    
    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        boolean[] used = new boolean[nums.length];
        LinkedList<Integer> track = new LinkedList<>();
        backtrack(nums, used, track);
        return res;
    }
    void backtrack(int[] nums, boolean[] used, LinkedList<Integer> track){
        if(track.size() == nums.length){
            res.add(new LinkedList(track));
            return;
        }
        for(int i = 0; i < nums.length; i++){
            if(used[i]){
                continue;
            }
            //这一步是为了固定相同元素在集合中的相对位置
            //如果前一个元素没有用过，就跳过；如果前一个元素已经用过，则可以使用后一个元素
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }
            used[i] = true;
            track.add(nums[i]);
            backtrack(nums, used, track);
            used[i] = false;
            track.removeLast();

        }
    }
}
```

```java
//用swap方法解决全排列问题
//思路：固定某个元素的位置，得到该情况下所有全排列情况，然后更改固定的元素，得到所有全排列情况
class solution{
    public List<List<Integer>> permute(int[] nums) {
        backtrack(nums, 0);
        return res;
    }

    void backtrack(int[] nums, int start){
        if(start == nums.length){
            List<Integer> track = new ArrayList<>();
            for(int i = 0; i < nums.length; i++){
                track.add(nums[i]);
            }
            res.add(track);
            return;
        }
        for(int i = start; i < nums.length; i++){
            swap(nums, i, start);
            //注意这里必须是start + 1不能是i + 1，i的值在每个循环中在增大，但start的值是确定的，是固定不变的位置
            //当i == 1时，backtrack(nums, 1);当i == 2时，还是从backtrack(nums, 1)开始
            backtrack(nums, start + 1);
            swap(nums, i, start);
        }
    }

    void swap(int[] nums, int i, int j){
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
    
}
    

```





##### 子集问题

```java
//无重复元素，不可复选
class Solution {
    List<List<Integer>> res = new LinkedList<>();
    //如果track不设置成全局变量，而只是subsets下的变量，backtrack函数中就需要将track作为参数引入
    LinkedList<Integer> track = new LinkedList<>();
    
    public List<List<Integer>> subsets(int[] nums) {
        backtrack(nums, 0);
        return res;
    }

    void backtrack(int[] nums, int start){
        //记录每一个节点值，无需满足限定条件
        res.add(new LinkedList(track));
        for(int i = start; i < nums.length; i++){
            track.add(nums[i]);
            backtrack(nums, i + 1);
            track.removeLast();
        }
    }
}
```

```java
//有重复元素，需要给重复元素进行“剪枝”
class Solution {
    List<List<Integer>> res = new LinkedList<>();
    LinkedList<Integer> track = new LinkedList<>();
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        //首先进行一个排序，让两个值相等的元素在相邻位置
        Arrays.sort(nums);
        backtrack(nums, 0);
        return res;
    }
    void backtrack(int[] nums, int start){
        res.add(new LinkedList(track));
        for(int i = start; i < nums.length; i++){
            //如果在做选择时，该元素与前一个元素相等，那么跳过
            if(i > start && nums[i] == nums[i - 1]){
                continue;
            }
            track.add(nums[i]);
            backtrack(nums, i + 1);
            track.removeLast();
        }
    }
}
```

```java
//无重复元素，可复选
class Solution {
    List<List<Integer>> res = new LinkedList<>();
    LinkedList<Integer> track = new LinkedList<>();
    int trackSum = 0;

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        backtrack(candidates, target, 0);
        return res;
    }
    void backtrack(int[] candidates, int target, int start){
        if(trackSum == target){
            res.add(new LinkedList(track));
            return;
        }
        
        //为了防止无限遍历，添加结束条件
        //注意这个return不能放在for循环里，会终止for循环
        if(trackSum > target) return;
        
        for(int i = start; i < candidates.length; i++){
            track.add(candidates[i]);
            trackSum += candidates[i];
            //注意这里，在不可复选时，参数为i+1以防止选择选过的参数，可复选的情况下变成i
            backtrack(candidates, target, i);
            track.removeLast();
            trackSum -= candidates[i];
        }

    }
}
```



##### 确定数量的组合问题（和子集问题等价）

```java
//给定n，针对数组[1,2,3...n],列出所有元素个数为k的组合（无重复元素）
class Solution {
    List<List<Integer>> res = new LinkedList<>();
    LinkedList<Integer> track = new LinkedList<>();
    public List<List<Integer>> combine(int n, int k) {
        backtrack(n, k, 1);
        return res;
    }

    void backtrack(int n, int k, int start){
        if(track.size() == k){
            res.add(new LinkedList(track));
            return;
        }
        for(int i = start; i <= n; i++){
            track.add(i);
            backtrack(n, k, i + 1);
            track.removeLast();
        }
    }
}
```

```java
//求和为target的全部组合（有重复元素）
class Solution {
    List<List<Integer>> res = new LinkedList<>();
    List<Integer> track = new LinkedList<>();
    int trackSum = 0;
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        backtrack(candidates, 0, target);
        return res;
    }

    void backtrack(int[] candidates, int start, int target){
        if(trackSum == target){
            res.add(new LinkedList(track));
            return;
        }
        for(int i = start; i < candidates.length; i++){
            if(i > start && candidates[i] == candidates[i - 1]){
                continue;
            }
            track.add(candidates[i]);
            trackSum += candidates[i];
            backtrack(candidates, i + 1, target);
            track.removeLast();
            trackSum -= candidates[i];
        }
    }
}
```



#### 岛屿问题

- 核心：用DFS/BFS遍历二维数组

```java
// 二维矩阵遍历框架
void dfs(int[][] grid, int i, int j, boolean[][] visited) {
    int m = grid.length, n = grid[0].length;
    if (i < 0 || j < 0 || i >= m || j >= n) {
        // 超出索引边界
        return;
    }
    if (visited[i][j]) {
        // 已遍历过 (i, j)
        return;
    }

    // 进入当前节点 (i, j)
    visited[i][j] = true;

    // 进入相邻节点（四叉树）
    // 上
    dfs(grid, i - 1, j, visited);
    // 下
    dfs(grid, i + 1, j, visited);
    // 左
    dfs(grid, i, j - 1, visited);
    // 右
    dfs(grid, i, j + 1, visited);
}


//一个常用技巧：构建方向数组
// 方向数组，分别代表上、下、左、右
int[][] dirs = new int[][]{{-1,0}, {1,0}, {0,-1}, {0,1}};
void dfs(int[][] grid, int i, int j, boolean[][] visited) {
    //省略同上内容
    // 递归遍历上下左右的节点
    for (int[] d : dirs) {
        int next_i = i + d[0];
        int next_j = j + d[1];
        dfs(grid, next_i, next_j, visited);
    }
    // 离开节点 (i, j)
}
```

```java
//经典岛屿问题解法
class Solution {
    public int numIslands(char[][] grid) {
        int res = 0;
        int m = grid.length;
        int n =grid[0].length;
        
        /*
        //如果要得到封闭岛屿，则需要先把上下左右边缘的岛屿淹没
            for(int i = 0; i < m; i++){
                dfs(grid, i, 0);
                dfs(grid, i, n - 1);
            }
            for(int j = 0; j < n; j++){
                dfs(grid, 0, j);
                dfs(grid, m - 1, j);
            }
        */
        
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == '1'){
                    res++;
//发现岛屿后，res增加，然后把岛屿本身及周围的土地淹没，直到四周都是海水，则相当于完成了一个岛屿的遍历
                    //如果要得到最大面积岛屿，将dfs函数的返回类型改为int记录岛屿面积，并在这里写更新代码
                    dfs(grid, i, j);
                    
                }
            }
        }
        return res;
    }

    void dfs(char[][] grid, int i, int j){
        int m = grid.length;
        int n = grid[0].length;
        if(i < 0 || j < 0 || i >= m || j >= n){
            return;
        }
        if(grid[i][j] == '0'){
            return;
        }
        //以下代码的作用是把原先的岛屿及周围所有相邻土地淹没
        grid[i][j] = '0';
        dfs(grid, i + 1, j);
        dfs(grid, i - 1, j);
        dfs(grid, i, j + 1);
        dfs(grid, i, j - 1);
        /*
        如果要记录最大面积，则为：
        dfs(grid, i + 1, j) + 
        dfs(grid, i - 1, j) + 
        dfs(grid, i, j + 1) + 
        dfs(grid, i, j - 1) + 1;
        */
    }
}


```





### BFS算法解题

#### 求最值（起点到终点求最短路径问题）

```java
//一般解题框架
// 计算从起点 start 到终点 target 的最近距离
int BFS(Node start, Node target) {
     // 核⼼数据结构
     Queue<Node> q;
     // 避免⾛回头路
     Set<Node> visited;

     // 将起点加⼊队列
     q.offer(start);
     visited.add(start);
     while (q not empty) {
         int sz = q.size();
         // 将当前队列中的所有节点向四周扩散
         for (int i = 0; i < sz; i++) {
             Node cur = q.poll();
             // 划重点：这⾥判断是否到达终点
             if (cur is target)
             return step;
                 // 将 cur 的相邻节点加⼊队列
             for (Node x : cur.adj()) {
                 if (x not in visited) {
                     q.offer(x);
                     visited.add(x);
                 }
             }
         }
     }
     // 如果⾛到这⾥，说明在图中没有找到⽬标节点
}
```



##### 求二叉树的最小深度

```java
class Solution {
    public int minDepth(TreeNode root) {
        if(root == null){
            return 0;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int depth = 1;
        
        while(!q.isEmpty()){
            int sz = q.size();
            for(int i = 0; i < sz; i++){
                TreeNode cur = q.poll();
                //移除并返回头部元素

                if(cur.left == null && cur.right == null){
                    return depth;
                }
                if(cur.left != null){
                    q.offer(cur.left);
                }
                if(cur.right != null){
                    q.offer(cur.right);
                }
            }
            depth++; 
        }
        return depth;
    }
}
```

##### 求打开密码锁的最小次数

```java
class Solution {
    public int openLock(String[] deadends, String target) {
        Set<String> visited = new HashSet<>();
        for(String s : deadends){
            visited.add(s);
        }
        //注意这个base case
        if(visited.contains("0000")) return -1;
        
        Queue<String> q = new LinkedList<>();
        q.offer("0000");
        visited.add("0000");
        int step = 0;
        while(!q.isEmpty()){
            int sz = q.size();
            for(int i = 0; i < sz; i++){
                String cur = q.poll();
                if(cur.equals(target)){
                    return step;
                }
                for(int j = 0; j < 4; j++){
                    String tmp1 = plusOne(cur, j);
                    //if中的条件保证了q队列中新加入的元素一定不在visited中，否则会直接跳过
                    if(!visited.contains(tmp1)){
                        q.offer(tmp1);
                        visited.add(tmp1);
                    }
                    String tmp2 = minusOne(cur, j);
                    if(!visited.contains(tmp2)){
                        q.offer(tmp2);
                        visited.add(tmp2);
                    }
                }
            }
            step++;
        }
        return -1;    
    }

    public String plusOne(String s, int j){
        char[] arr = s.toCharArray();
        if(arr[j] == '9'){
            arr[j] = '0';
        }else{
            arr[j] += 1;
        }
        return new String(arr);
    }
    public String minusOne(String s, int j){
        char[] arr = s.toCharArray();
        if(arr[j] == '0'){
            arr[j] = '9';
        }else{
            arr[j] -= 1;
        }
        return new String(arr);

    }
}
```



### Graph(图论)

#### 环检测

```java
//课程表问题的DFS解法
class Solution {
    boolean[] onPath;
    boolean hasCycle = false;
    boolean[] visited;

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        //graph建立好以后开始判断有没有环
        List<Integer>[] graph = buildGraph(numCourses, prerequisites);
        onPath = new boolean[numCourses];
        //visited是为了跳过已经遍历过的节点
        visited = new boolean[numCourses];
        //构建的graph不一定所有元素都是连接起来的，所以要遍历每一个独立节点
        for(int i = 0; i < numCourses; i++){
            traverse(graph, i);
        }
        
        return !hasCycle;
    }

    public List<Integer>[] buildGraph(int numCourses, int[][] prerequisites){
        List<Integer>[] graph = new LinkedList[numCourses];
        for(int i = 0; i < numCourses; i++){
            graph[i] = new LinkedList<>();
        }
        
        for(int[] neighbors : prerequisites){
            int from = neighbors[1];
            int to = neighbors[0];
            graph[from].add(to);
        }
        return graph;
    }

    void traverse(List<Integer>[] graph, int s){
        //从s开始遍历所有节点
        if(hasCycle) return;
        if(onPath[s]) {
            hasCycle = true;
            return;
        }
        if(visited[s]) return;

        visited[s] = true;
        onPath[s] = true;
        for(int neighbor : graph[s]){
            traverse(graph, neighbor);
        }
        onPath[s] = false;
    }
}


//BFS解法
        public boolean canFinish(int numCourses, int[][] prerequisites) {
            List<Integer>[] graph = buildGraph(numCourses, prerequisites);
            int[] inDegree = new int[numCourses];
            int count = 0;
            for(int[] neighbors : prerequisites){
                int to = neighbors[0];
                inDegree[to]++;
            }

            Queue<Integer> q = new LinkedList<>();
            //初始化q，把入度为0的元素加入队列
            for(int i = 0; i < numCourses; i++){
                if(inDegree[i] == 0){
                    q.offer(i);
                }
            }
            
            while(!q.isEmpty()){
                int cur = q.poll();
                count ++;
                //count记录的是当前遍历过的节点个数
                for(int i : graph[cur]){
                    //遍历节点cur的每个邻节点
                    inDegree[i]--;
                    if(inDegree[i] == 0){
                        q.offer(i);
                    }
                }
            }

            return count == numCourses;
        }


        public List<Integer>[] buildGraph(int numCourses, int[][] prerequisites){内容同上}
           
```



#### 拓扑排序（有向无环图）

- 如果图中边的指向是a-b表示先a后b（被依赖关系），graph的拓扑排序其实就是后续遍历结果的拟序列，反之，如果是依赖关系，graph的拓扑排序就是后续遍历结果

```java
//课程表进阶：打印顺序的DFS解法
class Solution {
    boolean[] visited;
    boolean[] onPath;
    boolean hasCycle = false;
    List<Integer> res = new ArrayList<>();

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph = buildGraph(numCourses, prerequisites);
        visited = new boolean[numCourses];
        onPath = new boolean[numCourses];
        for(int i = 0; i < numCourses; i++){
            traverse(graph, i);
        }
        if(hasCycle) return new int[]{};
        int[] result = new int[numCourses];
        for(int i = 0; i < numCourses; i++){
            result[i] = res.get(i);
        }
        return result;
    }


    public List<Integer>[] buildGraph(int numCourses, int[][] prerequisites){
        List<Integer>[] graph = new LinkedList[numCourses];
        for(int i = 0; i < numCourses; i++){
            graph[i] = new LinkedList<>();
        }

        for(int[] e : prerequisites){
            int from = e[1];
            int to = e[0];
            //注意这里，如果是无向图，构建的时候要再加一个graph[from].add(to);
            graph[to].add(from);
        }
        return graph;
    }

    public void traverse(List<Integer>[] graph, int s){
        if(hasCycle) return;
        if(onPath[s]) {
            hasCycle = true;
            return;
        }
        if(visited[s]) return;

        visited[s] = true;
        onPath[s] = true;
        for(int neighbor : graph[s]){
            traverse(graph, neighbor);
        }
        res.add(s);
        onPath[s] = false;
    }
}

//BFS解法
//BFS的拓扑排序就是遍历顺序，每次q.offer()的时候，res.add(),最终res就是排序结果
//但要注意，这种情况下，graph的指向关系必须是被依赖关系，即a指向b代表必须先a后b
```



#### 二分图（Bipartite）的判定（无向图）

- 核心就是遍历一遍图，一边遍历一边“染色”，看看能不能用两种颜色给所有节点染色，且相邻节点的颜色都不相同。

```java
//基本代码框架如下：
void traverse(Graph graph, boolean[] visited, int v) {
    visited[v] = true;
    // 遍历节点 v 的所有相邻节点 neighbor
    for (int neighbor : graph.neighbors(v)) {
        if (!visited[neighbor]) {
            // 相邻节点 neighbor 没有被访问过
            // 那么应该给节点 neighbor 涂上和节点 v 不同的颜色
            traverse(graph, visited, neighbor);
        } else {
            // 相邻节点 neighbor 已经被访问过
            // 那么应该比较节点 neighbor 和节点 v 的颜色
            // 若相同，则此图不是二分图
        }
    }
}
```

```java
//二分图的判定DFS解法
class Solution {
    boolean ok = true;
    boolean[] color;
    boolean[] visited;
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        color = new boolean[n];
        visited = new boolean[n];
        //这一步是因为给出的graph无法判定是不是连通图，所以需要每个节点都遍历，如果确定是连通图则不需要
        for(int i = 0; i < n; i++){
            if(!visited[i]){
                traverse(graph, i);
            }
        }
        return ok;
    }


    public void traverse(int[][] graph, int s){
        if(!ok) return;
        visited[s] = true;
        for(int neighbor : graph[s]){
            //对s的每一个相邻元素进行color操作
            if(!visited[neighbor]){
                color[neighbor] = !color[s];
                visited[neighbor] = true;
                traverse(graph, neighbor);
            }else{
                if(color[neighbor] == color[s]){
                    ok = false;
                }
            }
        }
    }
}

//BFS解法
	boolean ok = true;
    boolean[] color;
    boolean[] visited;

    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        color = new boolean[n];
        visited = new boolean[n];
        for(int i = 0; i < n; i++){
            if(!visited[i]){
                bfs(graph, i);
            }
        }
        return ok;
    }

    public void bfs(int[][]graph, int s){
        Queue<Integer> q = new LinkedList<>();
        q.offer(s);
        visited[s] = true;
        while(!q.isEmpty()){
            int cur = q.poll();
            for(int i : graph[cur]){
                if(!visited[i]){
                    color[i] = !color[cur];
                    visited[i] = true;
                    q.offer(i);
                }else{
                    if(color[i] == color[cur]){
                        ok = false;
                        return;
                    }
                }
            }
        }
    }
```



#### Union-Find并查集算法（针对动态连通性）

- 该算法针对的是一维数组（int[] parent记录了每个节点的parent节点），所以涉及到二维数组时可以用技巧转化为一维数组：针对二维数组m * n中的坐标（x, y)，可以用x * n + y来表示，这样一维数组parent的索引就变成[0,1,2...m*n-1]
- 主要思路是适时增加虚拟节点dummy，想办法让元素**分门别类**，建立动态连通关系

```java
//针对Leetcode 130题
//建立一个连通图，并新建一个dummy节点（用m*n表示新节点的索引），不需要改动的元素与dummy连通，需要改动的节点与dummy不连通

//首先定义连通图
class UF{
    private int count; //连通分量
    private int[] parent;
    public UF(int n){
        //初始化连通分量为n
        this.count = n;
        parent = new int[n];
        for(int i = 0; i < n; i++){
            parent[i] = i;
        }
    }
    public void union(int p, int q){
        if(find(p) == find(q)) return;
        int rootP = find(p);
        parent[find(q)] = rootP;
        count--;
    }
    public int find(int x){
        if(parent[x] != x){
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    public boolean connected(int p, int q){
        int rootP = find(p);
        int rootQ = find(q);
        return rootP == rootQ;
    }
}

class Solution {
    public void solve(char[][] board) {
        int m = board.length;
        if(m == 0) return;
        int n = board[0].length;
        UF uf = new UF(m * n + 1);//给dummy留一个额外位置
        int dummy = m * n;

        //把边缘的岛屿与dummy连通
        for (int i = 0; i < m; i++) {
            if (board[i][0] == 'O')
                uf.union(i * n, dummy);
            if (board[i][n - 1] == 'O')
                uf.union(i * n + n - 1, dummy);
        }
        for (int j = 0; j < n; j++) {

            if (board[0][j] == 'O')
                uf.union(j, dummy);
            if (board[m - 1][j] == 'O')
                uf.union(n * (m - 1) + j, dummy);
        }

        // 方向数组 d 是上下左右搜索的常用手法
        int[][] d = new int[][]{{1,0}, {0,1}, {0,-1}, {-1,0}};
        for (int i = 1; i < m - 1; i++) 
            for (int j = 1; j < n - 1; j++) 
            //遍历二维数组除外圈之外的部分
                if (board[i][j] == 'O')
                    // 将此 O 与上下左右的 O 连通（用到了连通的传递性，如果有O连接到边缘的O，那么它一定也与dummy相连）
                    for (int k = 0; k < 4; k++) {
                        int x = i + d[k][0];
                        int y = j + d[k][1];
                        if (board[x][y] == 'O')
                            uf.union(x * n + y, i * n + j);
                    }

    //所有不在边缘的岛屿被淹没
        for(int i = 1; i < m - 1; i++){
            for(int j = 1; j < n - 1; j++){
                if(!uf.connected(i * n + j, dummy)){
                    board[i][j] = 'X';
                }
            }
        }

    }
}
```

```java
//Leetcode 990题
class Solution {
    public boolean equationsPossible(String[] equations) {
        boolean res = true;
        UF uf = new UF(26); //26个英文字母

        for(String s : equations){
            if(s.charAt(1) == '='){
                //UF的底层是int数组，所以要把char转化为int
                uf.union(s.charAt(0) - 'a', s.charAt(3) - 'a');
            }
        }

        //检查不等式是否会破坏这种联系
        for(String s : equations){
            if(s.charAt(1) == '!'){
                if(uf.connected(s.charAt(0) - 'a', s.charAt(3) - 'a')){
                    res = false;
                    return res;
                }
            }
        }

        return res;
    }
}

class UF{
    
}
```



### 动态规划

#### 子序列问题

##### 最长递增子序列问题

###### 这里需要回头来看二分法解决问题的思路

> 要点：如何找到正确的状态转移方程？
>
> - 明确dp数组的定义
> - 根据dp数组定义采取归纳的思想：如果知道了dp[0...n-1]，能否得到dp[n]的值

```java
class Solution {
    public int lengthOfLIS(int[] nums) {
        //明确dp含义，dp[i]表示以i结尾的最长子序列的长度
        int n = nums.length;
        if(n == 0) return 0;
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        for(int i = 0; i < nums.length; i++){
            for(int j = 0; j < i; j++){
                if(nums[j] < nums[i]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int res = Integer.MIN_VALUE;
        for(int i = 0; i < nums.length; i++){
            res = Math.max(res, dp[i]);
        }
         return res;
    }
}
```

```java
//进阶：二维数组求最长子序列（leetcode354题，俄罗斯信封套娃问题）
//附上利用快排写的解法，但对于元素非常多的测试用例无法通过，如何利用二分法？
class Solution {
//实际上是一个最长子序列的变形，二维
//怎样排除宽度或者高度相等的情况？
    public int maxEnvelopes(int[][] envelopes) {
    //针对envelopes[i] 来说，宽度是envelopes[i][0], 高度是envelopes[i][1]
    //首先对宽度进行排序
    sort(envelopes, 0, envelopes.length - 1);
    //接下来对高度求最长子串
    return lengthOfLIS(envelopes);
    }

    void sort(int[][] envelopes, int lo, int hi){
        if(lo >= hi) return;
        int p = partition(envelopes, lo, hi);
        sort(envelopes, lo, p - 1);
        sort(envelopes, p + 1, hi);
    }

    int partition(int[][] envelopes, int lo, int hi){
        int p = envelopes[hi][0];
        int i = lo; int j = lo;
        while(j < hi){
            if(envelopes[j][0] < p){
                swap(envelopes, i ,j);
                i++;
            }
            j++;
        }
        swap(envelopes, i, hi);
        return i;
    }

    void shuffle(int[][] envelopes) {
        Random rand = new Random();
        int n = envelopes.length;
        for (int i = 0 ; i < n; i++) {
            // 生成 [i, n - 1] 的随机数
            int r = i + rand.nextInt(n - i);
            swap(envelopes, i, r);
        }
    }
    
    // 原地交换数组中的两个元素
    void swap(int[][] envelopes, int i, int j) {
        int[] temp = envelopes[i];
        envelopes[i] = envelopes[j];
        envelopes[j] = temp;
    }

    public int lengthOfLIS(int[][] envelopes) {
        //明确dp含义，dp[i]表示以索引i结尾的最长递增子序列的长度
        int n = envelopes.length;
        if(n == 0) return 0;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for(int i = 0; i < envelopes.length; i++){
            for(int j = 0; j < i; j++){
                //注意&&后边的代码是为了排除宽度相等的情况
                if(envelopes[j][1] < envelopes[i][1] && envelopes[j][0] != envelopes[i][0]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int res = Integer.MIN_VALUE;
        for(int i = 0; i < envelopes.length; i++){
            res = Math.max(res, dp[i]);
        }
         return res;
    }
}

//排序过程可以直接利用以下这行代码：如果二维数组内层第一个元素相同，则按照第二个元素降序排列；否则则按照第一个元素升序排列所有内层数组
 Arrays.sort(envelopes, (int[] a, int[] b) -> {
            return a[0] == b[0] ? 
                b[1] - a[1] : a[0] - b[0];
        });


```



##### 怎样设计算法的base case和备忘录

```java
//以leetcode 931题为例
class Solution {
    // 备忘录（特别注意这个备忘录是全局变量）
    int[][] memo;

    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;
        int res = Integer.MAX_VALUE;
        // 备忘录里的值初始化为 66666，在主函数进行初始化，而不是在dp函数
        memo = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(memo[i], 66666);
        }
        // 终点可能在 matrix[n-1] 的任意一列
        for (int j = 0; j < n; j++) {
            res = Math.min(res, dp(matrix, n - 1, j));
        }
        return res;
    }

    int dp(int[][] matrix, int i, int j) {
        // 1、索引合法性检查，因为这里调用的是最小值，所以return一个大于最大值的数值
        if (i < 0 || j < 0 ||
            i >= matrix.length ||
            j >= matrix[0].length) {
            return 99999;
        }
        // 2、base case
        if (i == 0) {
            return matrix[0][j];
        }
        // 3、查找备忘录，防止重复计算
        if (memo[i][j] != 66666) {
            return memo[i][j];
        }
        // 进行状态转移
        memo[i][j] = matrix[i][j] + min(
                dp(matrix, i - 1, j), 
                dp(matrix, i - 1, j - 1),
                dp(matrix, i - 1, j + 1)
            );
        return memo[i][j];
    }

    int min(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }
}
```



##### 经典问题：编辑距离

> Tips:
>
> 解决两个字符串的动态规划问题，一般都是用两个指针 `i, j` 分别指向两个字符串的头部或尾部，然后尝试写状态转移方程。
>
> 比方说让 `i, j` 分别指向两个字符串的尾部，把 `dp[i], dp[j]` 定义为 `s1[0..i], s2[0..j]` 子串的编辑距离，那么 `i, j` 一步步往前移动的过程，就是问题规模（子串长度）逐步减小的过程。
>
> 当然，你想让让 `i, j` 分别指向字符串头部，然后一步步往后移动也可以，本质上并无区别，只要改一下 `dp` 函数/数组的定义即可。

```java
//leetcode 72题，备忘录和dp数组迭代的两种解法
class Solution {
    /*
    int[][] memo;
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        memo = new int[m][n];
        for(int[] arr : memo){
            Arrays.fill(arr, -1);
        }
        return dp(word1, m - 1, word2, n - 1);
    }

    int dp(String word1, int i, String word2, int j){
        if(i == -1) return j + 1;
        if(j == -1) return i + 1;
        if(memo[i][j] != -1){
            return memo[i][j];
        }

        if(word1.charAt(i) == word2.charAt(j)){
            memo[i][j] = dp(word1, i - 1, word2, j - 1);
        }else{
            memo[i][j] = min(
                dp(word1, i, word2, j - 1) + 1,
                dp(word1, i - 1, word2, j) + 1,
                dp(word1, i - 1, word2, j - 1) + 1
            );
        }

        return memo[i][j];
    }
*/

    int[][] dp;
    public int minDistance(String word1, String word2) {
        int m = word1.length(); int n = word2.length();
        dp = new int[m + 1][n + 1];
        
        //初始化：当其中一个字符串长度为0时，最短编辑路径为另一个字符串的长度
        for(int i = 1; i <= m ; i++){
            dp[i][0] = i;
        }
        for(int j = 1; j <= n; j++){
            dp[0][j] = j;
        }
       
        //状态转移方程
        
        for(int k = 1; k <= m; k++){
            for(int q = 1; q <= n; q++){
                if(word1.charAt(k - 1) == word2.charAt(q - 1)){
                    dp[k][q] = dp[k - 1][q - 1];
                }else{
                    dp[k][q] = min(
                        dp[k - 1][q] + 1,
                        dp[k][q - 1] + 1,
                        dp[k - 1][q - 1] + 1
                    );
                }
            }   
        }

        return dp[m][n];
    }

    int min(int a, int b, int c){
        return Math.min(a, Math.min(b, c));
    }
}
```



##### 经典问题：最大子数组和

> 关键在于dp数组的定义：dp[i]定义为以i结尾的nums[...i]的最大数组和

```java
class Solution { 
    public int maxSubArray(int[] nums) {
        //base case
        //dp_0用来记录上一个值，dp_1用来记录当前值，res用来更新最大值
        int dp_0 = nums[0];
        int dp_1 = 0; 
        int res = dp_0;

        //状态转移
        for(int i = 1; i < nums.length; i++){
            dp_1 = Math.max(dp_0 + nums[i], nums[i]);
            dp_0 = dp_1;
            res = Math.max(res, dp_1);
        }

        return res;
    }
}
```



##### 经典问题：最长公共子序列

```java
class Solution {
    //这类问题应该有两个指针，分别指向两个string的一个字符

    /*
    int[][] memo;
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length(); int n = text2.length();
        memo = new int[m][n];
        for(int[] arr : memo){
            Arrays.fill(arr, -1);
        }
        return dp(text1, 0, text2, 0);
    }


    int dp(String text1, int i, String text2, int j){
        //这个函数的定义是，text1[i...]和text2[j...]中最长子序列的长度
        //base case
        if(i == text1.length() || j == text2.length()) return 0;
        //查备忘录
        if(memo[i][j] != -1) return memo[i][j];

        //状态转移
        if(text1.charAt(i) == text2.charAt(j)){
            memo[i][j] = 1 + dp(text1, i + 1, text2, j + 1);
        }else{
            memo[i][j] = Math.max(
                dp(text1, i, text2, j + 1),
                dp(text1, i + 1, text2, j)
                //这里有一个两个字符都没匹配上的选项，但包含在以上两个选项中，所以不单独计算
            );
        }
        return memo[i][j];
    }
    */
    int dp[][];
    //含义是text1[...i]和text2[...j]的最长子序列长度
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length(); int n = text2.length();
        dp = new int[m + 1][n + 1];

        for(int i = 1; i <= m; i++){
            dp[i][0] = 0;
        }
        for(int j = 0; j <= n; j++){
            dp[0][j] = 0;
        }

        for(int k = 1; k <= m; k++){
            for(int q = 1; q <= n; q++){
                if(text1.charAt(k - 1) == text2.charAt(q - 1)){
                    dp[k][q] = 1 + dp[k - 1][q - 1];
                }else{
                    dp[k][q] = Math.max(
                        dp[k - 1][q], dp[k][q - 1]
                    );
                }
            }
        }
        return dp[m][n];
    }
}
```



##### 经典问题：最长回文子序列

```java
class Solution {
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        // dp 数组全部初始化为 0
        int[][] dp = new int[n][n];
        // base case
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }
        // 反着遍历保证正确的状态转移
        //注意下方图片，按照i和j的定义，j必须是大于i的，且需要的是dp[0][n - 1]，即红色框，由左、下、左下三个位置决定，所以遍历的时候需要先遍历到这三个为止，反向遍历可以实现
        for (int i = n - 1; i >= 0; i--) {
            //不管i从哪里开始，j开始的位置永远是i+1,j可以向右一直增大，i需要向上逐层减小
            for (int j = i + 1; j < n; j++) {
                // 状态转移方程
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        // 整个 s 的最长回文子串长度
        return dp[0][n - 1];
    }
}
```

![img](https://labuladong.online/algo/images/lps/5.jpg)



##### 子序列问题小结

> - 一维dp数组定义：在子数组 `arr[0..i]` 中，以 `arr[i]` 结尾的子序列的长度是 `dp[i]`
>
>   题型：最长递增子序列，最大子数组和
>
> - 二维dp数组定义：
>
>   ​	（1）对应两个数组：在子数组 `arr1[0..i]` 和子数组 `arr2[0..j]` 中，我们要求的子序列长度为 `dp[i][j]`
>
>   ​		题型：编辑距离，最长公共子序列
>
>   ​	（2）对应一个数组：在子数组 `array[i..j]` 中，我们要求的子序列的长度为 `dp[i][j]`
>
>   ​		题型：最长回文子序列



#### 背包问题

> 重点：弄清楚dp的含义是什么

```java
//给你一个可装载重量为 W 的背包和 N 个物品
//dp[i][w]代表在待选择的前i个物品中，当前容量是w，每个物品只能装入一次，背包中可以装入的最大物品价值
//所求值即为dp[N][W];

//以leetcode 416题为例
class Solution {
    int[][] dp;
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for(int num : nums){
            sum += num;
        }

        if(sum % 2 != 0) return false;
        int tar = sum / 2;

        //dp数组代表的含义是对于前i个数，背包容量是tar,那么背包可以装的最大值是多少？
        dp = new int[nums.length + 1][tar + 1];

        //base case
        for(int i = 0; i <= nums.length; i++){
            dp[i][0] = 0;
        }
        for(int j = 0; j <= tar; j++){
            dp[0][j] = 0;
        }
        

        //状态转移方程
        for(int i = 1; i <= nums.length; i++){
            for(int j = 1; j <= tar; j++){
                if(j - nums[i - 1] < 0){
                    dp[i][j] = dp[i - 1][j];
                }else{
                    dp[i][j] = Math.max(
                        dp[i - 1][j], 
                        dp[i - 1][j - nums[i - 1]] + nums[i - 1]);
                }
            }
        }

        if(dp[nums.length][tar] == tar){
            return true;
        }else{
            return false;
        }
    }
}
```



#### 一些有趣的案例

##### 地下城游戏

```java
class Solution {
    int[][] memo;
    //int[][] dp;
    public int calculateMinimumHP(int[][] dungeon) {
        /*
        int m = dungeon.length;
        int n = dungeon[0].length;
        dp = new int[m][n];
        //经过思考发现从dp[i - 1][j]和dp[i][j - 1]无法推断出dp[i][j],因为无法确定上一个状态的生命值剩余
        //所以反向思考，dp[i][j]的定义设置为从位置dungeon[i][j]到终点dungeon[m - 1][n - 1]所需要的最小生命值

        //base case
        dp[m - 1][n - 1] = dungeon[m - 1][n - 1] > 0? 1 : Math.abs(dungeon[m - 1][n - 1]) + 1;

        //状态转移
        for(int i = m - 2; i >= 0; i--){
            for(int j = n - 2; j >= 0; j--){
                //dp[i][j]由它右方和下方的状态得到
                int cur = dungeon[i][j];
                dp[i][j] = Math.min(dp[i + 1][j], dp[i][j + 1]) - cur;
                if(dp[i][j] <= 0) dp[i][j] = 1;
            }
        }
        //这个遍历中无法遍历到dp[m - 1][n - 2]和dp[m - 2][n - 1],且边界问题不好处理，所以不用dp数组，转用带memo的dp函数
        return dp[0][0];
        */

        int m = dungeon.length;
        int n = dungeon[0].length;
        memo = new int[m][n];
        for(int[] arr : memo){
            Arrays.fill(arr, -1);
        }
        //memo[i][j]指的是从dungeon[i][j]到dungeon[m - 1][n - 1]的最小生命值

        return dp(dungeon, 0, 0);
    }

    int dp(int[][] dungeon, int i, int j){
        int m = dungeon.length;
        int n = dungeon[0].length;
        //处理索引越界
        if(i < 0 || j < 0 || i >= m || j >= n){
            return Integer.MAX_VALUE;
        }

        //base case
        if(i == m - 1 && j == n - 1){
            memo[i][j] = dungeon[i][j] >= 0? 1 : Math.abs(dungeon[i][j]) + 1;
        }

        //查询备忘录
        if(memo[i][j] != -1) return memo[i][j];

        //状态转移
        int res = Math.min(dp(dungeon, i + 1, j), dp(dungeon, i, j + 1)) - dungeon[i][j];
        memo[i][j] = res <= 0 ? 1 : res;
        
        return memo[i][j];

    }
}
```



##### 找密码的最小步数

```java
//leetcode 514题
class Solution {
    //题目中的变量有2个，一个是上一个状态到下一个状态的步数，一个是上一个状态是哪个状态（因为可能含有重复字母），所以应该用两个变量去维护，memo[i][j]维护的是当前指针位置在i，遍历完[j...]的最短步数。
    //这是一个反向递推，当前指针在[j],再加上从指针j开始，遍历完[j + 1...]所需要的步数，就是最短步数。
    //这里j可能在ring数组中有不同的对应索引，所以要分开考虑每个[j]和它对应的[j + 1...]，最终再取最小值
    
    
    int[][] memo;
    HashMap<Character, List<Integer>> hash = new HashMap<>();
    public int findRotateSteps(String ring, String key) {
        int m = ring.length(); int n = key.length();
        memo = new int[m][n];
        for(int[] arr : memo){
            Arrays.fill(arr, -1);
        }

        //这个hash表的作用是记录下标为某个字母的全部索引
        for(int i = 0; i < m; i++){
            if(!hash.containsKey(ring.charAt(i))){
                hash.put(ring.charAt(i),new LinkedList<>());
            }
            hash.get(ring.charAt(i)).add(i);
        }

        return dp(ring, 0, key, 0);
}
    int dp(String ring, int i, String key, int j){
        //函数定义：当当前指针在hash.get(i)时，实现key[j..]的最小步数

        //base case
        if(j == key.length()) return 0;
        if(memo[i][j] != -1) return memo[i][j];
        
        int res = Integer.MAX_VALUE;
        for(int h : hash.get(key.charAt(j))){
            //针对同一个h，也可以从两个方向到达目标位点，即顺时针和逆时针
            int distance1 = Math.abs(h - i);
            int distance2 = ring.length() - distance1;
            int distance = Math.min(distance1, distance2);
            res = Math.min(res, 1 + distance + dp(ring, h, key, j + 1));
        } 

        memo[i][j] = res;
        return memo[i][j];
    }
}
```



##### 航班中转站（带权重的最短路径和）

```java
//leetcode 787题，这个解法主要学习带权重的最短路径和
class Solution {
    //首先这个题目要倒着推，先找到dst，然后往前推，看哪一条线可以到达0
    int[][] memo;
    int src;
    int dst;
    HashMap<Integer, List<int[]>> graph;
    
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        //k的意思是中转站，可以经过k个中转站，那么就代表可以走k + 1步，取值范围是0-k+1步
        this.src = src;
        this.dst = dst;
        graph = buildGraph(n, flights);
        memo = new int[n][k + 2];
        for(int[] arr : memo){
            Arrays.fill(arr, -2);
        }

        //此时代表最多走k+1步
        return dp(dst, k + 1);
    }

    int dp(int s, int k){
        //base case
        if(s == src) return 0;
        if(k == 0) return -1;

        if(memo[s][k] != -2) return memo[s][k];

        //函数定义：k步内从起点到达s的最低价格

        int res = Integer.MAX_VALUE;
        if(graph.containsKey(s)){
            for(int[] a: graph.get(s)){
                if(dp(a[0], k - 1) != -1){
                    //递归至k - 1步内从起点到达s的上一点的最低价格
                    res = Math.min(dp(a[0], k - 1) + a[1], res);
                }
            }
        }
        memo[s][k] = res == Integer.MAX_VALUE? -1 : res;
        return memo[s][k];
    }


    HashMap<Integer, List<int[]>>  buildGraph(int n, int[][] flights){
        graph = new HashMap<>();
        for(int[] f : flights){
            int from = f[0];
            int to = f[1];
            int price = f[2];
            graph.putIfAbsent(to, new LinkedList<>());
            graph.get(to).add(new int[] {from, price});
        }
        return graph;
    }
}
```



##### 正则表达式匹配问题

```java
//leetcode第10题
class Solution {
    int[][] memo;
    public boolean isMatch(String s, String p) {
        memo = new int[s.length()][p.length()];
        for(int[] arr : memo){
            Arrays.fill(arr, -1);
        }
        return dp(s, 0, p, 0);
    }
    boolean dp(String s, int i, String p, int j){
        //函数定义：从s[i...]到p[j...]是否可以匹配
        //两个字符串的问题中这是一种常见的函数格式
        int m = s.length(), n = p.length();
        // base case
        //如果j走到末尾，检查i是否走到末尾
        if (j == n) {
            return i == m;
        }
        //如果i走到末尾，j可以没走到末尾，只要后续的全是通配符计算0次即可
        if (i == m) {
            if ((n - j) % 2 == 1) {
                return false;
            }
            for (; j + 1 < n; j += 2) {
                if (p.charAt(j + 1) != '*') {
                    return false;
                }
            }
            return true;
        }

        if(memo[i][j] != -1){
            return memo[i][j] == 1;
        }

        boolean res = false;
        if(s.charAt(i) == p.charAt(j) || p.charAt(j) == '.'){
            if(j + 1 < n && p.charAt(j + 1) == '*'){
                //前者为通配符使用多次，后者为通配符不使用
                res = dp(s, i + 1, p, j) || dp(s, i, p, j + 2);
            }else{
                res = dp(s, i + 1, p, j + 1);
            }
        }else{
            if(j + 1 < n && p.charAt(j + 1) == '*'){
                res = dp(s, i, p, j + 2);
            }else{
                res = false;
            }
        }

        //当前结果记入备忘录
        memo[i][j] = res? 1 : 0;
        return res;
    }
}
```



##### 区间内戳气球获得最大分数问题

```java
//leetcode 312题，思路：以左右区间和最后一个戳破的气球索引为变量倒推
class Solution {
    int[][] memo;
    public int maxCoins(int[] nums) {
        int n = nums.length;
        //把nums两端都设置成1
        int[] numNew = new int[n + 2];
        numNew[0] = 1;
        numNew[n + 1] = 1;
        for(int i = 1; i < n + 1; i++){
            numNew[i] = nums[i - 1];
        }
        //问题转化为戳破numNew从0-n + 1(不包括)的所有气球，从而获得最大得分
        memo = new int[n + 2][n + 2];
        for(int[] arr : memo){
            Arrays.fill(arr, -1);
        }

        return dp(numNew, 0, n + 1);
    }

    int dp(int[] numNew, int i, int j){
        int len = numNew.length;

        //base case: 只有一个气球
        if(len == 3) return numNew[1];
        //没有气球
        if(len == 2) return 0;

        if(memo[i][j] != -1) return memo[i][j];

        int res = 0;
        for(int m = i + 1; m < j; m ++){
            res = Math.max(res, dp(numNew, i, m) + dp(numNew, m, j) + numNew[m] * numNew[i] * numNew[j]);
        }

        memo[i][j] = res;
        return memo[i][j];
    }
}
```



##### 博弈问题

```java
//leetcode 486题：先手的选择会影响后手的选择
class Pair{
    int fir; int sec;
    Pair(int fir, int sec){
        this.fir = fir;
        this.sec = sec;
    }
}

class Solution {
    Pair[][] dp;
    public boolean predictTheWinner(int[] nums) {
        int n = nums.length;
        
        //初始化数组
        dp = new Pair[n][n];
        for(int i = 0; i < n; i++){
            for(int j = i; j < n; j++){
                dp[i][j] = new Pair(0, 0);
            }
        }

        for(int i = 0; i < n; i++){
            dp[i][i].fir = nums[i];
            dp[i][i].sec = 0;
        }
        
        //fir指的是在剩下选择中的先手最优选；sec指得是在剩下选择中的非最优选；
        //如果先手选择了一侧，那么在接下来的选择中将变成后手，即sec
        for(int i = n - 2; i >= 0; i--){
            for(int j = i + 1; j < n; j++){
                //先手选择左边
                int left = dp[i + 1][j].sec + nums[i];
                //先手选择右边
                int right = dp[i][j - 1].sec + nums[j];
                if(left >= right){
                    dp[i][j].fir = left;
                    dp[i][j].sec = dp[i + 1][j].fir;
                }else{
                    dp[i][j].fir = right;
                    dp[i][j].sec = dp[i][j - 1].fir;
                }
            }
        }

        Pair res = dp[0][n - 1];
        return res.fir - res.sec >= 0;
    }
}
```



##### 股票买卖问题

```java
//不限买卖次数的解法
class Solution {
    int[][] dp;
    public int maxProfit(int[] prices) {
        int n = prices.length;
        dp = new int[n][2];
        if(n <= 0) return 0;

        for(int i = 0; i < n; i++){
            if(i - 1 == -1){
                dp[i][0] = 0;
                dp[i][1] = -prices[i];
                continue;
            }
            
            //0和1代表当前是否持有股票，i表示当前的天数
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }

        return dp[n - 1][0];
    }
}

//限制买卖次数的解法
class Solution {
    //限定买卖2次，那么引入一个新的状态，即截至今天可使用的最大交易次数k
    int[][][] dp;
    public int maxProfit(int[] prices) {
        int n = prices.length;
        dp = new int[n][3][2];
        if(n <= 0) return 0;

        for(int i = 0; i < n; i++){
            dp[i][0][0] = 0;
            dp[i][0][1] = Integer.MIN_VALUE;
        }

        for(int i = 0; i < n; i++){
            for(int k = 2; k > 0; k--){
                if(i - 1 == -1){
                    dp[i][k][0] = 0;
                    dp[i][k][1] = -prices[i];
                    continue;
                }
                
                //k只有在买入操作的时候会减小，如果没有涉及到买入操作，只是卖出，k的值不变
                dp[i][k][0] = Math.max(dp[i - 1][k][0], dp[i - 1][k][1] + prices[i]);
                dp[i][k][1] = Math.max(dp[i - 1][k][1], dp[i - 1][k - 1][0] - prices[i]);
            }
        }

        return dp[n - 1][2][0];
    }
}

//如果限制次数为k，把上个案例中的2换成k即可，注意：如果k > n / 2,那么相当于案例1中不限制k的情况，分情况处理可以避免内存超限

//带有冷冻期的情况
class Solution {
    int[][] dp;
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if(n <= 0) return 0;
        dp = new int[n][2];
        
        for(int i = 0; i < n; i++){
            if(i - 1 == -1){
                dp[i][0] = 0;
                dp[i][1] = -prices[i];
                continue;
            }
            if(i - 2 == -1){
                //0天买入1天卖出，或者0天1天都不买入
                dp[i][0] = Math.max(prices[i] - prices[i - 1], 0);
                //0天买入1天持有，或者1天买入
                dp[i][1] = Math.max(-prices[i - 1], -prices[i]);
                continue;
            }
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 2][0] - prices[i]);
        }

        return dp[n - 1][0];
    }
}


```



#### 贪心算法

##### 老司机加油问题

```java
//leetcode 134题
class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        int sum = 0;
        
        //首先如果总加油量小于总消耗量，可以直接输出-1
        for(int i = 0; i < n; i++){
            sum += gas[i] - cost[i];
        }
        if(sum < 0) return -1;
        
        int tank = 0;
        int start = 0;
        for(int i = 0; i < n; i++){
            tank += gas[i] - cost[i];
            //tank表示每一步的净加油量，从0开始计算tank，如果计算到i时tank变负数了，说明从0无法走到i，那么0-i之间的点也无法走到i，所以起点直接从i+1开始计算，省略中间很多计算量
            if(tank < 0){
                tank = 0;
                start = i + 1;
            }
        }
        
        return start == n? -1 : start;
    }
}
```



##### 无重叠区间问题

> 找最多几个区间不重叠的思路：
>
> - 找到所有区间中end最小的区间
> - 把所有与该区间重叠的区间去掉
> - 更新end最小的区间，重复该过程

```java
//leetcode 435题
class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        int n = intervals.length;
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[1]));
        //Arrays.sort(intvs, (a, b) -> Integer.compare(a[1], b[1]));
        int start = 0;
        int count = 0;
        for(int i = 1; i < n; i++){
            //要想区域不重叠，下一个区域的起点要>=上一个区域的终点
            if(intervals[i][0] >= intervals[start][1]){
                start = i;
            }else{
                count++;
            }
        }
        return count;
    }
}
```



##### 视频剪辑问题

> - 将起点按照升序排列，起点相同的按照降序排列
> - 在起点小于上一个片段终点的片段中找到终点最大的片段
> - 判断最终片段的终点与总时长的关系

```java
//leetcode 1024题
class Solution {
    public int videoStitching(int[][] clips, int time) {
        int n = clips.length;
        //按照起点升序，如果起点相同，按照终点降序
        Arrays.sort(clips, (int[] a, int[] b) -> {
            return a[0] == b[0] ? 
                b[1] - a[1] : a[0] - b[0];
        });

        if(clips[0][0] > 0) return -1;

        int count = 0;
        int curEnd = 0;
        int nextEnd = 0;
        int i = 0;

        while(i < n && clips[i][0] <= curEnd){
            while(i < n && clips[i][0] <= curEnd){
                //在起点小于当前终点的元素中选择终点最大的
                nextEnd = Math.max(nextEnd, clips[i][1]);
                i++;
            }
            count++;
            curEnd = nextEnd;
            if(curEnd >= time) return count;
        }
        return -1;
    }
}
```



##### 区间跳跃问题

```java
//leetcode第55题，只需要判断能否走到终点
class Solution {
    public boolean canJump(int[] nums) {
        int n = nums.length;
        int far = 0;
        for(int i = 0; i < n - 1; i++){
            far = Math.max(far, i + nums[i]);
            //注意这里的边界是i < n - 1，也就是倒数第二个位置
            //如果在这段区间内，far == i的情况发生，就说明前期（算上当前位置的跳跃）最远只能到达i点，无法再往下了
            if(far == i) return false;
        }

        //如果顺利走完n - 2,只需要判断选择完这一点以后可到达的最远距离是否能达到n - 1;
        return far >= n - 1;
    }
}

//leetcode第45题，要求给出走到终点的最小jump数
class Solution {
    public int jump(int[] nums) {
        int n = nums.length;
        int end = 0, farthest = 0;
        int jumps = 0;
        for (int i = 0; i < n - 1; i++) {
            //遍历上一次跳跃可以到达的最远落点，当end == i时遍历结束，这时farthest就是上一次跳跃加上本次跳跃可以达到的最远落点
            farthest = Math.max(nums[i] + i, farthest);
            if (end == i) {
                //当上一次跳跃的位置遍历结束后，下一次jump开始
                jumps++;
                //end是上一次跳跃可以达到的最远落点
                end = farthest;
            }
        }
        return jumps;
    }
}
```



### 线段树问题

[参考材料](https://leetcode.cn/circle/discuss/H4aMOn/)

```java
class SegmentTree{
    int[] nums, tree;
    int n;
    public SegmentTree(int[] nums){
      this.nums = nums;
      this.n = nums.length;
      this.tree = new int[4 * n];
      build(0, n - 1, 1);
  }
  
  public void build(int s, int t, int i){
      if(s == t){
        tree[i] = nums[s];
        return;
    	}
      int mid = s + (t - s) / 2;
      build(s, mid, i * 2);
      build(mid + 1, t, i * 2 + 1);
      pushUp(i);
  }
  public void pushUp(int i){
      //求区间和如下：
      tree[i] = tree[2 * i] + tree[2 * i + 1];
      //求区间最值如下：
      tree[i] = Math.max(tree[2 * i], tree[2 * i + 1]);
  }
  
  //给nums[index]的值加val
  public void add(int index, int val){
    add(index, val, 0, n - 1, 1);
	}
  private void add(int index, int val, int s, int t, int i){
      if(s == t){
          tree[i] += val;
          return;
      int mid = s + (t - s) / 2;
      if(index <= mid){
        	add(index, val, s, mid, i * 2);
      }else{
        	add(index, val, mid + 1, t, i * 2 + 1);
      }
      pushUp(i);
  }
  
   public void update(int index, int val){
     	add(index, val - nums[index], 0, n - 1, 1);
     	nums[index] = val;//实时维护nums[index]
   }
}
```

#### 区间和

```java
public int sum(int l, int r){
  return sum(l, r, 0, n - 1, 1);
}
private sum(int l, int r, int s, int t, int i){
  if(l <= s && r >= t) return tree[i];//当前区间包含在所求区间中
  int mid = s + (t - s) / 2;
  int sum = 0;
  if(l <= mid) sum += sum(l, r, s, mid, i * 2);
  if(r > mid) sum += sum(l, r, mid + 1, t, i * 2 + 1);
  return sum;
}
```

#### 区间最值

```java
public int max(int l, int r){
  return max(l, r, 0, n - 1, 1);
}
private max(int l, int r, int s, int t, int i){
  //注意这里的return条件，如果求最值，则是s == t，如果求区间值，则是l <= s && r >= t
  if(s == t) return tree[i];
  int mid = s + (t - s) / 2;
 	lmax = Integer.MIN_VALUE;
  rmax = Integer.MIN_VALUE;
  if(l <= mid) lmax = max(l, r, s, mid, i * 2);
  if(r > mid) rmax = max(l, r, mid + 1, t, i * 2 + 1);
  return Math.max(lmax, rmax);
}
```

#### 区间整段增量式修改

```java
class SegmentTreeAdd{
  //注意这里增加了一个lazy数组，用来向下更新
    int[] nums, tree, lazy;
    int n;
    public SegmentTreeAdd(int[] nums){
        this.nums = nums;
        this.n = nums.length;
        this.tree = new int[4 * n];
        this.lazy = new int[4 * n];
        build(0, n - 1, 1);
    }
  
   	private void build(int s, int t, int i){
        if(s == t) { 
            tree[i] = nums[s];
            return;
        }
        int c = s + (t - s) / 2;
        build(s, c, i * 2);
        build(c + 1, t, i * 2 + 1);
        pushUp(i);  // 后序动作，自底向上更新结点区间和 tree[i]
    }
  
  // pushup: 更新 tree[i]
    private void pushUp(int i){
        tree[i] = tree[i * 2] + tree[i * 2 + 1];
    }
  
   
    public void add(int l, int r, int x){ // 区间修改(驱动): 增量式 [l,r] 区间所有元素加上x
        add(l, r, x, 0, n - 1, 1);
    }
   
   
    // 区间修改: 增量式 [l,r] 区间所有元素加上x
    private void add(int l, int r, int x, int s, int t, int i){
        if(l <= s && t <= r){ // 当前结点代表的区间在所求区间之内
            tree[i] += (t - s + 1) * x; // 结点i的区间和加上t-s+1个x
            if(s != t) lazy[i] += x; // 结点i不是叶子结点，懒标记值加上x
            return;
        }
        int c = s + (t - s) / 2;
        if(lazy[i] != 0) pushDown(s, c, t, i); // 是否推送标记
        if(l <= c) add(l, r, x, s, c, i * 2);
        if(r > c) add(l, r, x, c + 1, t, i * 2 + 1);
        pushUp(i); // 后序动作，自底向上更新结点区间和 tree[i]
    }
    
    // pushdown: 更新当前结点及其左右子结点的懒标记
    private void pushDown(int s, int c, int t, int i){
        tree[i * 2] += (c - s + 1) * lazy[i]; // 更新其左子结点的区间和
        lazy[i * 2] += lazy[i]; // 传递懒标记(增量标记)
        tree[i * 2 + 1] += (t - c) * lazy[i];
        lazy[i * 2 + 1] += lazy[i];
        lazy[i] = 0; // 重置当前结点懒惰标记值（增量标记置0）
    }
}
```

#### 区间整段覆盖式修改

```java
class SegmentTreeUpdate{
    int[] nums, tree, lazy;
    boolean[] updated;
    int n;
    public SegmentTreeUpdate(int[] nums){
        this.nums = nums;
        this.n = nums.length;
        this.tree = new int[4 * n];
        this.lazy = new int[4 * n];
      //注意这里新增了一个updated布尔数组
        this.updated = new boolean[4 * n];
        build(0, n - 1, 1);
    }
   
    public void update(int l, int r, int x){ // 区间修改(驱动): 覆盖式 [l,r] 区间所有元素改为x
        update(l, r, x, 0, n - 1, 1);
    }
  
    // 区间修改: 覆盖式 [l,r] 区间所有元素改为x
    private void update(int l, int r, int x, int s, int t, int i){
        if(l <= s && t <= r){ // 当前结点代表的区间在所求区间之内
            tree[i] = (t - s + 1) * x; // 结点i的区间和等于t-s+1个x
            if(s != t) { // 结点i不是叶子结点
                lazy[i] = x; // 更新懒标记
                updated[i] = true; // 更新updated
            }
            return;
        }
        int c = s + (t - s) / 2;
        if(updated[i]) pushDown(s, c, t, i); // 是否推送标记
        if(l <= c) update(l, r, x, s, c, i * 2);
        if(r > c) update(l, r, x, c + 1, t, i * 2 + 1);
        pushUp(i); // 后序动作，自底向上更新结点区间和 tree[i]
    }
   
  
    // pushdown: 更新当前结点及其左右子结点的懒标记和updated
    private void pushDown(int s, int c, int t, int i){
        tree[i * 2] = (c - s + 1) * lazy[i]; // 更新其左子结点的区间和
        lazy[i * 2] = lazy[i]; // 传递懒标记(覆盖式标记)
        updated[i * 2] = true;
        tree[i * 2 + 1] = (t - c) * lazy[i];
        lazy[i * 2 + 1] = lazy[i];
        updated[i * 2 + 1] = true;
        lazy[i] = 0; // 重置当前结点懒惰标记值
        updated[i] = false; // 重置当前结点updated[i]为false
    }
}

//如果不是修改sum，而是修改最值，则不需要update[]，pushDown的条件改为lazy[i] != 0即可，同时pushDown中也不需要(c - s + 1) *lazy[i],而是lazy[i]
```

#### 小tips：离散化

```java
// 紧离散
private Map<Integer, Integer> discrete(int[] nums){ 
    Map<Integer, Integer> map = new HashMap<>();
    Set<Integer> set = new HashSet<>();
    for(int num : nums) set.add(num);
    List<Integer> list = new ArrayList<>(set);
    Collections.sort(list);
    int idx = 0;
    for(int num : list) map.put(num, ++idx);
    return map;
}

//松离散
private void discrete(int[] nums){ 
    int n = nums.length;
    int[] tmp = new int[n];
    System.arraycopy(nums, 0, tmp, 0, n);
    Arrays.sort(tmp);
    for (int i = 0; i < n; ++i) {
        nums[i] = Arrays.binarySearch(tmp, nums[i]) + 1;
    }
}
```



### 前缀树问题

```java
class TrieNode {
    private TrieNode[] links;
    private final int R = 26;
    private boolean isEnd;
    public TrieNode() {
        links = new TrieNode[R];
    }
    public boolean containsKey(char ch){
        return links[ch - 'a'] != null;
    }
    public TrieNode get(char ch){
        return links[ch - 'a'];
    }
    public void put(char ch, TrieNode node){
        links[ch - 'a'] = node;
    }
    public void setEnd(){
        isEnd = true;
    }
    public boolean isEnd(){
        return isEnd;
    }
}
 
class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }
    
    public void insert(String word) {
        TrieNode current = root;
        for (int i = 0; i < word.length(); i++) {
            if(!current.containsKey(word.charAt(i))){
                current.put(word.charAt(i), new TrieNode());
            }
            current = current.get(word.charAt(i));
        }
        current.setEnd();
    }

    public boolean search(String word) {
        TrieNode current = root;
        for(int i = 0; i < word.length(); i++){
            if(!current.containsKey(word.charAt(i))){
                return false;
            }else{
                TrieNode node = current.get(word.charAt(i));
                current = node;
            }
        }
        return current.isEnd();
    }
    
    public boolean startsWith(String prefix) {
        TrieNode current = root;
        for(int i = 0; i < prefix.length(); i++){
            if(!current.containsKey(prefix.charAt(i))){
                return false;
            }else{
                TrieNode node = current.get(prefix.charAt(i));
                current = node;
            }
        }
        return true;
    }
}

```



### 一些用数学原理解决的问题

#### 字符串匹配问题

```java
class Solution {
   public int repeatedStringMatch(String A, String B) {
        //A作为滚轮
        char[] a = A.toCharArray();
        char[] b = B.toCharArray();
     		int count = 1;
        for(int i = 0;i < a.length;i++){
            int len = loop(a,b,i);
            if(len > 0){
              //说明匹配成功
              int remained = b.length - (a.length - i);
              count += remained / a.length;
              count += remained % a.length == 0? 0 : 1;
            }else if(len + a.length <= 0){
              //这里如果len+a.length > 0，说明前面还没有完整地遍历过一次a，后边的字母可能会匹配成功，所以外部循环继续
                return -1;
            }
        }
        return -1;
        
    }
    //使用a滚轮印刷b，start为起始点
    public int loop(char[] a,char[] b,int start){
        int count = start;
        for(char c : b){
            if(a[start % a.length] != c){
                return count - start;
            }
            start++;
        }
        return 1; 
    }
}
```





