### Reverse String

```java

```

`class Solution {`

  `public void reverseString(char[] s) {`

​    `int n = s.length;`

​    `int i = 0;`

​    `for(i = 0; i < n / 2; ++i){`

​      	`char a = s[n - 1 - i];`

​     	 `s[n - 1 - i] = s[i];`

​     	 `s[i] = a;`

​    `}`

  `}}`



### Reverse String 2

`class Solution {`

  `public String reverseStr(String s, int k) {`

​    	`int n = s.length();`

​    	`int j = n / (2 * k);`

​    	`int q= n % (2 * k);`

​    	`int i = 0;`

​    	`int m = 0;`

​    	`char[] charArray = s.toCharArray();`

​    	`if(q >= k){`

​      		`j += 1;`

​    	`}`

``  

​    	`for (i = 0; i < j; ++i){`

​      		`for (m = 0; m < k / 2; ++m){`

​        			`char a = charArray[2 * i * k + k - 1 - m];`

​       			 `charArray[2 * i * k + k - 1 - m] = charArray[2 * i * k + m];`

​       			 `charArray[2 * i * k + m] = a;`

​    		  `}`

   	 `}`

``  

​    `if(q < k){`

   	   `for (m = 0; m < q / 2; ++m){`

​     		 `char b = charArray[2 * j * k + q - 1 - m];`

​        		`charArray[2 * j * k + q - 1 - m] = charArray[2 * j * k + m];`

​     		   `charArray[2 * j * k + m] = b;`

​    	`}`

​    `}`

​    `String d = new String(charArray);`

​    `return d;`

  `}`

`}`



### reverse-vowels-of-a-string

`class Solution {`

  `public static char[] VOWELS = { 'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U' };`

  `public String reverseVowels(String s) {`

​    `int n = s.length();`

​    `char[] charArray = s.toCharArray();`

​    `// 提取所有元音字母为新的数组`

​    `char[] newChar = new char[n];`

​    `int j = 0;`

​    `for (int i = 0; i < n; i++) {`

​      `if (isVowel(charArray[i])) {`

​        `newChar[j] = charArray[i];`

​        `j += 1;`

​      `}`

​    `}`

​    `// 数组长度`

​    `int m = j - 1;`

​    `// 在原数列的基础上，如果遇到元音字母，则从新构建数组的最后一位开始向前排列`

​    `for (int i = 0; i < n; i++) {`

​      `if (isVowel(charArray[i])) {`

​        `charArray[i] = newChar[m];`

​        `m--;`

​      `}`

​    `}`

​    `return new String(charArray);`

  `}`

  `public static boolean isVowel(char letter) {`

​    `for (char vowel : VOWELS) {`

​      `if (letter == vowel) {`

​        `return true;`

​      `}`

​    `}`

​    `return false;`

  `}`

`}`

### Keyboard Row

```java
class Solution {
    private char[] row_1;
    private char[] row_2;
    private char[] row_3;

    public String[] findWords(String[] words) {
        int num = words.length;
        //Construct an array for return words
        ArrayList<String> returnWords = new ArrayList<String>();
       
        //Construct arrays of each rows
        String String_1 = "qwertyuiop";
        String String_2 = "asdfghjkl";
        String String_3 = "zxcvbnm";
        row_1 = String_1.toCharArray();
        row_2 = String_2.toCharArray();
        row_3 = String_3.toCharArray();

        //determine
        for(int i = 0; i < num; i++){
            String lowerCase = words[i].toLowerCase();
            char[] wordDecompose = lowerCase.toCharArray();
            if(determine(wordDecompose)){
                returnWords.add(words[i]);
            }
        }

        String[] result = returnWords.toArray(new String[returnWords.size()]);
        return result;
    }

    
    public boolean determineChar(char c, char[] arr){
        for(char r : arr){
            if(c == r){
                return true;
            }
        }
        return false;
    }

    //Determine whther a word contain the letters all from a specified row.
    public boolean determineWord(char[] arr, char[] row){
       int arrLen = arr.length;
       for(int i = 0; i < arrLen; i++){
            if(determineChar(arr[i], row) == false){
                return false;
            }
        }  
        return true;
    }

    public boolean determine(char[] arr){
        return determineWord(arr, row_1) || determineWord(arr, row_2) || determineWord(arr, row_3);
    }
}
```

