# StrToJFree
![]() ![](https://img.shields.io/github/issues/ozebe/strtojfree.svg) ![](https://img.shields.io/badge/license-apache%202-green.svg) ![](https://img.shields.io/github/repo-size/ozebe/strtojfree.svg) ![](https://img.shields.io/github/last-commit/ozebe/strtojfree.svg)

StrToJFree is a java class to import strings and split then in usable JFree Objects

## Getting Started

To use is easy, you just need to clone to your project, and then use it

### Prerequisites

As the class uses jfree libraries, you need to have them in your base project before cloning

## Using

It is currently possible to manipulate data as follows: 
#####JFree Data types
- jfree.data.time.Day
- jfree.data.time.Hour
- jfree.data.time.Minute
- And a lot of other possibilites as we shall see

Consider the input as the following string:
```
        String dado = 
				   "Hora = 17:35; data = 28/05/2019; temperatura = 17; pressao = 870;\n"
                + "Hora = 17:55; data = 28/05/2019; temperatura = 16; pressao = 870;\n"
                + "\nhora = 18:35; data = 29/05/2019; temperatura = 18; pressao = 880;\n"
                + "\nhora = 19:20; data = 30/05/2019; temperatura = 15; pressao = 885;\n"
                + "\nhora = 20:31; data = 31/05/2019; temperatura = 11; pressao = 890;";
```

##### The ``` converter() ``` method return a sliced information
###### Example:



The class will use the "=" to delimit the data and the ";" to cut the information lines

so the code below, using the data limiter as being "temperatura", when using the ```getL ()``` method, would return the values ​​contained after the "="
```
StrToJFree t = new StrToJFree.Builder(dado, "temperatura").converter().build();
```

A simple way to check the ```getL()``` values ​​would be with a for, as follows

```
        for (int i = 0; i < t.getL().size(); i++) {
            System.out.println("temperatura :  " + t.getL().get(i).toString());

        }
```
Returning:
```
temperatura :  17
temperatura :  16
temperatura :  18
temperatura :  15
temperatura :  11
```

##### The ``` converterData() ``` method return a org.jfree.data.time.Day
###### Example:

The class will use the "=" to delimit the data and the ";" to cut the information lines

so the code below, using the data limiter as being "data", when using the ```getDatas()``` method, would return the values in a JFree Day objects list
```
StrToJFree data = new StrToJFree.Builder(dado, "data").converterData().build();
```

A simple way to check the ```getDatas()``` values ​​would be with a for, as follows

```
        for (int i = 0; i < data.getDatas().size(); i++) {
            System.out.println("datas: " + data.getDatas().get(i).toString());

        }
```

Returning:
```
datas: 28-maio-2019
datas: 28-maio-2019
datas: 29-maio-2019
datas: 30-maio-2019
datas: 31-maio-2019
```
As all being JFree Day objects

The ``` converterHora()```  and ```converterMinutos()``` methods returns in sequence a  ```org.jfree.data.time.Hour ``` and a  ```org.jfree.data.time.Minute ``` objects lists

## Versioning

We use [SemVer](http://semver.org/) for versioning. For the versions available, see the [tags on this repository](https://github.com/ozebe/strtojfree/tags). 

## Author

* **Wesley Ozebe** - [Ozebe](https://github.com/ozebe)

## License

This project is licensed under the Apache 2 license - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* JFree creator
