package com.badabum007.hell_guardians

object TestingWsheet {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(105); 
  println("Welcome to the Scala worksheet");$skip(119); 
  
    def fib1( n : Int) : Int = {
    if (n < 2){
      1
    }
    else {
      fib1(n - 1) + fib1(n - 2)
    }
  };System.out.println("""fib1: (n: Int)Int""");$skip(10); val res$0 = 
  fib1(5);System.out.println("""res0: Int = """ + $show(res$0));$skip(262); 
    def fib2( n : Int ) : Int = {
      var a = 0
          var b = 1
          var i = 0
          var c = 0

          while( i < n ) {
             c = a + b
                a = b
                b = c
                i = i + 1
          }
      return c
  };System.out.println("""fib2: (n: Int)Int""");$skip(11); val res$1 = 
  fib2(5);;System.out.println("""res1: Int = """ + $show(res$1))}
}
