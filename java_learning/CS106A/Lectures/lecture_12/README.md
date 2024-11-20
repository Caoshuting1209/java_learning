# Char and String

### char

```
char ch = 'a';

String s = readLine("");
char ch = s.charAt(0);

public char toLower(char ch){
	if(ch >= 'A' && ch <='Z'){
		return(ch - 'A' + 'a');
	}else{
		return ch;
	}
}
```



```
static boolean isDigit(Char ch);
static boolean isLetter(Char ch);
static boolean isLetterOrDigit(Char ch);
static boolean isLowerCase(Char ch);
static boolean isUpperCase(Char ch);
static char toLowerCase(char Ch);
static char toUpperCase(char Ch);
```



```
Char——plimitive(just like int, double)
String——Class

eg. Char ch; String str;
ch = Character.toUpperCase(ch);
str = str.toUpperCase();
```



```
if(str.equals(s1)){}——case sensitive
```



```
if(Character.isUpperCase(ch)){}
```



### String

```
int length();
char charAt(int index);

String.subString(int a, int b)——contain a but not b
eg. String str = "hello";
	str.subString(1,3)——“el”
	str.subString(1)——"ello";
	
boolean equals(String s2);

int compareTo(String S2);

int indexOf(char ch) or int indexOf(String s);
eg. String str = "hello";
	str.indexOf(char e) = 1;
	str.indexOf(String "ello") = 1;
	str.indexOf(String "elo") = -1; ——not found
	
String toLowerCase();
String toUpperCase();
```



```
reverseString:
for(int i = 0; i < str.length(); i++){
	result = str.charAt(i) + result;
}
```



### Tokenizers

```java
import java.util.*;
StringTokenizer tokenizer = new StringTokenizer(str, ", ");——take both space and "," as tokenizer

boolean hasMoreTokens();
String nextToken();
```



### Encrption

```java
int key = 3;
private char encryptChar(char ch, int key){
	if(key < 0){
		key = 26 - (-key % 26);
	}
	if(Character.isUpperCase(ch)){
		char ch = (char)('A' + (ch - 'A' + key) % 26)
		return ch;
	}
	return ch;
}

(key == -3) equals to (key == 26 + key）
```



### Replace

```java
private void userGuess(){
    	char guessedWord = (readLine("Your guess: ")).charAt(0);
    	int count = 0;
    	for(int j = 0; j < length; ++j){
    		char a = word.charAt(j);
    		if(a == guessedWord){
    			StringBuilder sb = new StringBuilder(current);
                sb.setCharAt(j, a);
                current = sb.toString();
    			count += 1;
    		}
    	}
	}


or 
    
private void userGuess(){
    	char guessedWord = (readLine("Your guess: ")).charAt(0);
    	guessedWord = Character.toUpperCase(guessedWord);
    	int count = 0;
    	for(int j = 0; j < length; ++j){
    		char a = word.charAt(j);
    		if(a == guessedWord){
    			arr[j] = a;
    			current = new String(arr);
    			count += 1;
    		}
    	}
```



