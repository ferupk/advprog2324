# Module 01: Coding Standards

Feru Pratama Kartajaya<br>
2106750351

## Reflection 1

```
You already implemented two new features using Spring Boot. Check again your source code
and evaluate the coding standards that you have learned in this module. Write clean code
principles and secure coding practices that have been applied to your code. If you find any
mistake in your source code, please explain how to improve your code.
```

- Meaningful Names<br>
  In my code, I always attempt to give descriptive names to variables and methods.
- Functions<br>
  Functions are made to be small and only do one thing. This can be seen in the separation
  of different methods relating to Product repository and controller.
- Comments<br>
  I attempt to avoid unnecessary comments altogether by using variable names and instantiation,
  as well as descriptive code writing, to write code that is both readable and easy to understand.

## Reflection 2

```
1. After writing the unit test, how do you feel? How many unit tests should be made in a
class? How to make sure that our unit tests are enough to verify our program? It would be
good if you learned about code coverage. Code coverage is a metric that can help you
understand how much of your source is tested. If you have 100% code coverage, does
that mean your code has no bugs or errors?

2. Suppose that after writing the CreateProductFunctionalTest.java along with the
corresponding test case, you were asked to create another functional test suite that
verifies the number of items in the product list. You decided to create a new Java class
similar to the prior functional test suites with the same setup procedures and instance
variables.

What do you think about the cleanliness of the code of the new functional test suite? Will
the new code reduce the code quality? Identify the potential clean code issues, explain
the reasons, and suggest possible improvements to make the code cleaner!
```

1. After writing the unit tests, I feel more confident in the correctness and quality maintenance
   of my code. I think that the amount of unit tests that should be made is as much as it is necessary
   to verify all functions of the code. Test cases that are redundant and do unnecessary checks may
   reduce overall code quality.
   <br>
   100% code coverage only means that every aspect of your code is covered and is able to be tested by unit
   tests. It does not mean that there are no mistakes in the code, or that the test suites that cover
   said code are of good quality.
   
2. In terms of clean code principles, the main problem of creating similar functional test suites is that
   it introduces a lot of code duplication. Multiple test suites will share the same lines of code which
   adds redundancy. A possible improvement to be made is to combine these test suites into one larger test
   suite. This prevents any duplicate code to be shared between test suites. However, the context of each
   test suite should be put into consideration if you were to do this. While the setup procedures and
   instances between test suites might be similar, they might be testing very different functionalities.
   Combining these suites into one might create clutter within the code and be very hard to read and understand.