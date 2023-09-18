# ConsoleManager
An easy to use console prototype which allows the out and in not to be mixed and adds format and colors to the console!

To create the console we will simply use the following code and this will format our console and the color format.

```
Console console = new Console();
```

To add colors we can check the class `ChatColors.java` where we have all the colors available, simply using the code it provides us it will replace it with the color that corresponds to it.

For example:

```
System.out.println("&1Blue color!")
System.out.println("&5Purple color!")
System.out.println("&bAqua color!")
System.out.println("&dPink color!")
```

You can also create commands and add them to the console using the following code:

```
new Command("test", this, "This is a test command!") {
    @Override
    public void execute(String[] args) {
        System.out.println("&aTest command works!");
    }           
};
```

I hope it helps you :)