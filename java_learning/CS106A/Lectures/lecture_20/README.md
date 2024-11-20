### GUI（Graphical User Interface）

sliders

button

check box

radio buttons

text box

combo box

### JButton

```java
import java.awt.event.*;
import javax.swing.*;

//type name = new type(String)
/*
JButton but = new Jbutton("Hi");
add(but, SOUTH);
*/
public void init(){
    //Private JButton Hi;
    Hi = New JButton("Hi");
    add(Hi,SOUTH);
    addMouseListeners();
    addActionListeners();
}

//click
public void actionPerformed(ActionEvent e){
    String cmd = e.getActionCommand();
    //cmd == "Hi" in this example
    if(cmd.equals("Hi")){
        println("Hello");
    }
    //if there are more buttons,just add "else if" code
}

//or
 public void actionPerformed(ActionEvent e){
     add(new JButton("Hi"), SOUTH);
     if(e.getSource == Hi){
         println("Hello");
     }
 }

	Private JButton Hi;
```



### JCheckBox

```java
public void init(){
//CheckBox
	check = new JCheckBox("Front");
//initial state
	check.setSelected(true);
	add.(check, SOUTH);
	boolean p = check.isSelected()
}
   
private JCheckBox check;
```



### JRadioButton

```java
public void init(){
//RadioButton
	sm = new JRadioButton("Small");
	med = ...
	lar = ...
	ButtonGroup size = new ButtonGroup();
    size.add(sm, med, lar);
    //initial state
    med.setSelected(true);
    add(sm, SOUTH);
    add(med, SOUTH);
    add(lar, SOUTH);
    boolean s = sm.isSelected();
    boolean m = med...
    boolean l = lar...
}

private JRadioButton sm;
```



### JComboBox

```java
public void init(){
    pick = new JComboBox();
    pick.addItem("Black");
    pick.addItem("Blue");
    ...
    
    pick.setEditable(false);
    pick.setSelectedItem("Blue");
    add(new JLabel("  Color"), SOUTH);
    
    add(pick, SOUTH);
}

private JComboBox pick;
```



### Text Field

```java
init(){
    tf = new JTextField(10);
	//10 is the size of the text
    add(new JLabel("Name"), SOUTH);
    //label to the text
	tf.addActionListener(this);
	add(tf, SOUTH);
}

run(){
    if(e.getSource == tf){
    	println(tf.getText());
	}
}

private JTextField tf;


//or
init(){
    tf = new JTextField(10);
	//10 is the size of the text
    tf.setActionCommand("name");
    add(new JLabel("Name"), SOUTH);
    //label to the text
	tf.addActionListener(this);
	add(tf, SOUTH);
}

run(){
    if(e.getActionCommand.equals("name")){
    	println(tf.getText());
	}
    
 private JTextField tf;
}

```



### Layout

```java
public classGridLayoutExample extends Program{
    public void init(){
        setLayout(new GridLayout(2,3));
        //Whole region filled
        //TableLayout doesn't fill the whole region
        add(new JButton("1"));
        add(new JButton("2"));
        ...
        addActionListeners();
    }
    
    public void actionPerformed(ActionEvent e){
        //Ignore the buttons
    }
}
```



```java
public classGridLayoutExample extends Program{
    public void init(){
        setLayout(new GridLayout(1,3));
        //Whole region filled
        
        leftGCanvas = new GCanvas();
        add(leftGCanvas);
        
        rightGCanvas = new GCanvas();
        add(rightGCanvas);
        
        //Now we get a three-column layout:first is a console; second is a GCanvas; third is also a GCanvas
    }
    
    public void actionPerformed(ActionEvent e){
        //Ignore the buttons
    }
    
    private GCanvas leftGCanvas;
    private GCanvas rightGCanvas;
}
```

