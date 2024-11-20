### Files

#### Reading files

import java.IO.*;

(1) Open(BufferedReader)

```java
BufferedReader rd = new BufferedReader(new FileReader(filename));
```



(2) Read(line by line)

```java
while(true){
    String line = rd.readline();
    if(line == null) break;
    println(line);
}
```



(3) Close

```java
rd.close();
```



#### EXCEPTION

"throw"

```	java
try{
	code for file access
} catch(IOException ex){
	deal with the exception
}
```



eg:

```java
private BufferedReader rd(String a){
    BufferedReader rd = null;
    while(rd = null){
   		try{
        	String name = readline(a);
        	rd = new BufferedReader(new FileReader(name));
    	}catch(IOException ex){
            //We know how to deal with the exception
        	println("Bad file");
    	}
    }
   return rd; 
}

public void run{
    BufferedRead rd = rd("Enter the filename");
    
    try{
        while(true){
            String line = rd.readline();
            if(line == null) break;
            println(line);
        }
        rd.close();
    }catch (IOException ex){
        //We don't know how to deal with the exception
        //import acm.util.*;
        throw new ErrorException(ex);
    }
}
```



#### Write files

(1) Open(PrintWriter)

(2)Write

(3)Close

eg:

```java
private BufferedReader readFile(String a){
    BufferedReader rd = null;
    while(rd = null){
   		try{
        	String name = readline(a);
        	rd = new BufferedReader(new FileReader(name));
    	}catch(IOException ex){
            //We know how to deal with the exception
        	println("Bad file");
    	}
    }
   return rd; 
}

public void run{
    BufferedReader = rd("Enter the filename");
    
    try{
        PrintWriter wr = new PrintWriter(new fileWriter("copy.txt");
       	while(true){
            String line = rd.readline();
            if(line == null) break;
            println(line); //Write to console
            wr.println(line); //write to file
        }
         rd.close();
         wr.close();                              
    }catch (IOException ex){
        //import acm.util.*;
        throw new ErrorException(ex);
    }
}
```

